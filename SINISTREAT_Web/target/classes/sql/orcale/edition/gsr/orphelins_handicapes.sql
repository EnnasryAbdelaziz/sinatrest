--Entete
insert into SIN_ENTETE(id, sous_titre) values(17, null);  

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(22, 'Etat des orphelins handicapés', 17);

insert into SIN_RECAP(id, type_fille, description) values(9, 'RECAP_GENERALE', 'Summary Etat des orphelins handicapés');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(17, '17', 'Etat des orphelins handicapés',
                'select                  
                        rente.numero_rente as numero_rente,
                        '' '' || rentier.nom || '' '' || rentier.prenom as orphelin,
                        rentier.date_naissance as date_naissance,
                        (select '' '' || tuteur.nom || '' '' || tuteur.prenom from gsr_tuteur tuteur where tuteur.id = rentier.idtuteur) as tuteur,
                        4 * rentier.montant_rente_trimest as mt_rente_annuel,
                        (select sum(prothese.reserve_prothese) from gsr_prothese prothese where rentier.id = prothese.gsr_rentier) as reserve_math
                from            
                        gsr_rente rente, gsr_rentier rentier
                where to_date(to_char(rentier.date_creation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
                and rentier.etat_rente in (4, 6)
                and rentier.lien_parente >= 40
                and rentier.situation_rentier = 3
                and rentier.gsr_rente = rente.id'         
                                       ,1, 17, 9);
                                       
                                                                  
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(216, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 17);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(217, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 17);

-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(219, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 100, 2, 17);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(220, 'CHAMP', 'orphelin', 'java.lang.String', 'Nom Orphelin', '', 'Left', 200, 3, 17);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(221, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Naissance', 'dd/MM/yyyy', 'Center', 120, 4, 17);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(222, 'CHAMP', 'tuteur', 'java.lang.String', 'Tuteur', '', 'Left', 200, 5, 17);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(223, 'CHAMP', 'mt_rente_annuel', 'java.lang.Double', 'Montant Rente Annuel', '#,##0.00', 'Right', 150, 6, 17);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(224, 'CHAMP', 'reserve_math', 'java.lang.Double', 'Réserve Math.', '#,##0.00', 'Right', 150, 7, 17);

-- Recap generale
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (225, 'VARIABLE', 'nbreDossiers', 'java.lang.Integer', 'Nombre de dossiers  : ', 'Left', 'Count', '$F{numero_rente}', 1, 9);                               








