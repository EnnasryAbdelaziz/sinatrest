--Entete
insert into SIN_ENTETE(id, sous_titre) values(11, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(13, 'Etat des réserves Mathématiques  des rentes constituées', 11);
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(14, 'titreEtatRente', '1', 11);

insert into SIN_RECAP(id, type_fille, description) values(7, 'RECAP_GENERALE', 'Summary Etat des Reserves Mathematiques par Etat Rente');
      
insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(11, '11', 'Etat des Reserves Mathematiques par Etat Rente',
                'select 
                rente.numero_rente as numero_rente,
                lien_parente as classe,
               '' '' || rentier.nom || '' '' || rentier.prenom as nom_rentier,
               rentier.date_naissance as date_naissance,
               to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) as age,
               (select syn_ville.libelle from syn_ville syn_ville where rentier.ville = syn_ville.id) as ville,
               4 * rentier.montant_rente_trimest as rente_annuelle,
               
               
               rentier.reserve_mathematique as rserve_math,
               rentier.ipp_taux_rente as taux,
               rentier.usersas_createur as utilisateur
               
               from gsr_rente rente, gsr_rentier rentier
                where rentier.date_constitution between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and rentier.etat_rente = $P{codeEtatRente}
                and rentier.gsr_rente = rente.id'
                               
                                       ,1, 11, 7);
                                       
-- Parametres du rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, id_sin_rapport)
values(143, 'PARAMETRE', 'codeEtatRente', 'java.lang.String', '', 11);
                                      

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(144, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(145, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 11);

-- CHAMPS delete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(146, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 160, 1, 11); 

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(147, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 2, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(148, 'CHAMP', 'classe', 'java.lang.String', 'Classe', '', 'Center', 60, 3, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(149, 'CHAMP', 'nom_rentier', 'java.lang.String', 'Nom Rentier', '', 'Left', 200, 4, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(150, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Nais', 'dd/MM/yyyy', 'Center', 80, 5, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(151, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Left', 170, 6, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(152, 'CHAMP', 'age', 'java.lang.Integer', 'Age', '', 'Center', 40, 7, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(153, 'CHAMP', 'rente_annuelle', 'java.lang.Double', 'Rente Annuelle', '#,##0.00', 'Right', 100, 8, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(154, 'CHAMP', 'rserve_math', 'java.lang.Double', 'Réserve Math.', '#,##0.00', 'Right', 100, 9, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(155, 'CHAMP', 'taux', 'java.lang.String', 'Taux (%)', '', 'Center', 60, 10, 11);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(156, 'CHAMP', 'utilisateur', 'java.lang.String', 'Utilisateur', '', 'Left', 100, 11, 11);

--Variables recap generale
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (157, 'VARIABLE', 'nbreDossiers', 'java.lang.Integer', 'Nombre de dossiers  : ', 'Left', 'Count', '$F{numero_rente}', 12, 7);

--recap colonnes
insert into SIN_RECAP_COLONNE(id, name, calculation, id_sin_recap, id_champ)
values(8, 'totalRenteAnnuelle', 'Sum', 7, 153); 
                          
insert into SIN_RECAP_COLONNE(id, name, calculation, id_sin_recap, id_champ)
values(9, 'totalReserve', 'Sum', 7, 154);



   
                           

update sin_rapport
set requete_sql='select 
                rente.numero_rente as numero_rente,
                lien_parente as classe,
               '' '' || rentier.nom || '' '' || rentier.prenom as nom_rentier,
               rentier.date_naissance as date_naissance,
               to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) as age,
               (select syn_ville.libelle from syn_ville syn_ville where rentier.ville = syn_ville.id) as ville,
               4 * rentier.montant_rente_trimest as rente_annuelle,
               
               
               rentier.reserve_mathematique as rserve_math,
               rentier.ipp_taux_rente as taux,
               rentier.usersas_createur as utilisateur
               
               from gsr_rente rente, gsr_rentier rentier
                where rentier.date_constitution between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and rentier.etat_rente = $P{codeEtatRente}
                and rentier.gsr_rente = rente.id'
                where id=11;