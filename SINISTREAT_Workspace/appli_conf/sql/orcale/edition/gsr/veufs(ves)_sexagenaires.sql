--Entete
insert into SIN_ENTETE(id, sous_titre) values(49, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(63, 'Etat Majoration des veufs ou veuves sexagénaires', 49);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(49, '49', 'Etat Majoration des veufs ou veuves sexagénaires',
                'select 
                   
                    rente.numero_rente as numero_rente,
                    rentier.lien_parente as classe,
                    '' ''|| rentier.nom || '' '' || rentier.prenom as beneficiaire,
                    rentier.date_naissance as date_naissance,
                    rentier.montant_rente_trimest as rente_trimest_30,
                    1.2 * rentier.montant_rente_trimest as rente_trimest_50,
                    0.2 * rentier.montant_rente_trimest as ecart,
                    (select libelle from prm_etat_rente where code = rentier.etat_rente) as etat_rente
                from gsr_rentier rentier, gsr_rente rente
                where rentier.lien_parente in (10, 11, 12, 13)
                and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) >= 60
                and rentier.date_naissance between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and rentier.gsr_rente = rente.id'
                               
                                       ,1, 49);  
                                     
                              
                                           
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(578, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 49);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(579, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 49);


-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(580, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 1, 49);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(581, 'CHAMP', 'classe', 'java.lang.String', 'Classe', '', 'Center', 70, 2, 49);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(582, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 200, 3, 49);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(583, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Nais.', 'dd/MM/yyyy', 'Center', 80, 4, 49);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(584, 'CHAMP', 'rente_trimest_30', 'java.lang.Double', 'Rente Trim. à 30%', '#,##0.00', 'Right', 120, 5, 49);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(585, 'CHAMP', 'rente_trimest_50', 'java.lang.Double', 'Rente Trim. à 50%', '#,##0.00', 'Right', 120, 6, 49);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(586, 'CHAMP', 'ecart', 'java.lang.Double', 'Ecart', '#,##0.00', 'Right', 120, 7, 49);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(587, 'CHAMP', 'etat_rente', 'java.lang.String', 'Etat Rente', '', 'Center', 120, 8, 49);
