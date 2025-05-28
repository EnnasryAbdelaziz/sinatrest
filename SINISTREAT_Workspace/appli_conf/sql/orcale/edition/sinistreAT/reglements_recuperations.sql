--Entete
insert into SIN_ENTETE(id, sous_titre) values(35, null);
 
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(46, 'LISTE DE CONTROLE DES RECUPERATIONS', 35);
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(58, 'redacteur', '1', 35);

-- Recap generale
insert into SIN_RECAP(id, type_fille, description) values(14, 'RECAP_GENERALE', 'Summary Etat Liste de controle des recuperation');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(35, '35', 'Liste des controles des récuperation par rédacteur',
                'select reglement.numeroquittance as quittance, 
                reglement.datereglement as date_reglement,  
                case length(sinistre.numerosinistre)
                when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                || '' '' || substr(sinistre.numerosinistre, 12) 
                else sinistre.numerosinistre
                end numero_sinistre, 
               sinistre.numerograve as numero_grave, 
               reglement.emetteur as emetteur,
               (select nature_recup.libelle from PRM_NATURE_RECUPERATION nature_recup where nature_recup.code = reglement.naturerecuperation) as nature_recup,
                    reglement.montant as montant

    
        from sin_reglement reglement, sin_sinistre sinistre 
        
        where to_date(to_char(reglement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
        and reglement.codeusercreateur = $P{codeRedacteur}
        and reglement.typereglement = ''4''
        and reglement.etatreglement = ''1''
        and reglement.idsinistre = sinistre.id
                         
        order by quittance'
                               
                                       ,1, 35, 14);

 -- Parametres de rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(514, 'PARAMETRE', 'codeRedacteur', 'java.lang.String', 38);
                                      
 -- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(389, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 35);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(390, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 35);

-- Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(391, 'CHAMP', 'quittance', 'java.lang.String', 'N° Quit', '', 'Center', 150, 1, 35);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(392, 'CHAMP', 'date_reglement', 'java.sql.Timestamp', 'Date Régl.', 'dd/MM/yyyy', 'Center', 80, 2, 35);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(393, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 190, 3, 35);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(394, 'CHAMP', 'numero_grave', 'java.lang.String', 'N° Grave', '', 'Center', 80, 4, 35);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(395, 'CHAMP', 'emetteur', 'java.lang.String', 'Emetteur', '', 'Left', 200, 5, 35);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(396, 'CHAMP', 'nature_recup', 'java.lang.String', 'NAT. RECUP.', '', 'Center', 80, 5, 35);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(397, 'CHAMP', 'montant', 'java.lang.Double', 'Montant', '#,##0.00', 'Right', 100, 6, 35);

-- Recap generale             
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (398, 'VARIABLE', 'nbreQuittances', 'java.lang.Integer', 'Nombre Total des Quittances : ', 'Left', 'Count', '$F{quittance}', 1, 14);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (399, 'VARIABLE', 'totalMontantQuittance', 'java.lang.Double', 'Total Montant des Quittances : ', '#,##0.00', 'Right', 'Sum', '$F{montant}', 2, 14);                               


