-- Entete
insert into SIN_ENTETE(id, sous_titre) values(10, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(12, 'Etat des Résérves Mathématiques des Rentes Constitués avec Prothèses', 10);

insert into SIN_RECAP(id, type_fille, description) values(6, 'RECAP_GENERALE', 'Summary Etat de reserves Mathematiques des Rentes constitues Protheses');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(10, '10', 'Etat de reserves Mathematiques des Rentes constitues avec Protheses',
                'select                 case rente.compagnie_rente
                when ''1'' then ''RMA WATANYA''
                when ''2'' then ''EL WATANYA''
                when ''3'' then ''RMA''
                when ''4'' then ''MGF''
                end nom_societe,
                case length(sinistre.numerosinistre)
                when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                || '' '' || substr(sinistre.numerosinistre, 12) 
                else sinistre.numerosinistre
                end numero_sinistre, 
                rente.numero_rente as numero_rente,
               '' '' || rentier.nom || '' '' || rentier.prenom as nom_rentier,
               rentier.date_naissance as date_naissance,
               4 * rentier.montant_rente_trimest as rente_annuelle,
               prothese.date_prothese as date_prothese,
               prothese.reserve_prothese as rserve_math_encours,
               prothese.montant_prothese as montant_prothese,
               null as rserve_math_precedente,
               nvl(prothese.reserve_prothese, 0) - nvl(null, 0) as ecart_reserve 
               from gsr_rente rente, gsr_rentier rentier, gsr_prothese prothese, sin_sinistre sinistre
               where rentier.date_constitution between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
               and rentier.prothese = ''1''
               and prothese.gsr_rentier = rentier.id
               and rentier.gsr_rente = rente.id
               and rente.dossier_sinistre = sinistre.id
               order by rente.compagnie_rente'
                               
                                       ,1, 10, 6);  
                                     
                              
                                           
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(128, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(129, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 10);

-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, id_sin_rapport)
values(7, 'grSocieteRente', '$F{nom_societe}', '1', 10);

-- Champs group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(130, 'CHAMP', 'nom_societe', 'java.lang.String', 'Société Rente : ', 1, 7);

-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(131, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 160, 1, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(132, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 2, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(133, 'CHAMP', 'nom_rentier', 'java.lang.String', 'Nom Rentier', '', 'Left', 200, 3, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(134, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Naissance', 'dd/MM/yyyy', 'Center', 80, 4, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(135, 'CHAMP', 'date_prothese', 'java.sql.Timestamp', 'Date Prothèse', 'dd/MM/yyyy', 'Center', 80, 5, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(136, 'CHAMP', 'rente_annuelle', 'java.lang.Double', 'Rente Annuelle', '#,##0.00', 'Right', 120, 6, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(137, 'CHAMP', 'montant_prothese', 'java.lang.Double', 'Mnt Prothèse', '#,##0.00', 'Right', 120, 7, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(138, 'CHAMP', 'rserve_math_encours', 'java.lang.Double', 'Rsv. Math. en Cours', '#,##0.00', 'Right', 120, 8, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(139, 'CHAMP', 'rserve_math_precedente', 'java.lang.Double', 'Rsv. Math. Préc', '#,##0.00', 'Right', 120, 9, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(140, 'CHAMP', 'ecart_reserve', 'java.lang.Double', 'Ecart', '#,##0.00', 'Right', 100, 10, 10);



--Recap group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (141, 'VARIABLE', 'nbreDossiersParSociete', 'java.lang.Integer', 'Nbre de Dossiers par scté : ', '', 'Left', 'Count', '$F{numero_sinistre}', 1, 7);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (142, 'VARIABLE', 'totalDossiersParSociete', 'java.lang.Double', 'Total Dossiers par scté : ',  '#,##0.00', 'Left', 'Sum', '$F{rserve_math_encours}', 2, 7);  








