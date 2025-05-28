--Enete
insert into SIN_ENTETE(id, sous_titre) values(47, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(61, 'Etat des rentes qui n''ont pas pu être créées', 47);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(47, '47', 'Etat des rentes qui n''ont pas pu être créées',
                'select 
                    sinistre.numerosinistre as numero_sinistre,
                    sinistre.numerograve as numero_grave,
                    '' '' || rentier.nom || '' '' || rentier.prenom as nom_prenom,
                    rentier.date_etat as date_,
                    rentier.observation_donnee_conforme as motif_rejet
                from gsr_rentier rentier, gsr_rente rente, sin_sinistre sinistre
                where rentier.etat_rente = ''16''
                and rentier.date_etat between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and rentier.gsr_rente = rente.id
                and rente.dossier_sinistre = sinistre.id' 
                                       ,1, 47);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(551, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 47);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(552, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 47);                                                            
                  
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(553, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 160, 1, 47);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(554, 'CHAMP', 'numero_grave', 'java.lang.String', 'N° Grv.', '', 'Center', 80, 2, 47);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(555, 'CHAMP', 'nom_prenom', 'java.lang.String', 'Nom Prénom', '', 'Left', 200, 3, 47);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(556, 'CHAMP', 'date_', 'java.sql.Timestamp', 'Date', 'dd/MM/yyyy', 'Center', 80, 4, 47);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(557, 'CHAMP', 'motif_rejet', 'java.lang.String', 'Motif rejet', '', 'Left', 250, 5, 47);



