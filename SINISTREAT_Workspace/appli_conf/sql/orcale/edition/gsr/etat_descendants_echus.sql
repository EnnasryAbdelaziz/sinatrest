--Entete
insert into SIN_ENTETE(id, sous_titre) values(13, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(17, 'Etat des descendants Echus.(21ans)', 13);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(13, '13', 'Etat des descendants Echus.(21ans)',
                'select
                    rente.numero_rente as numero_rente,
                    rentier.lien_parente as classe,
                    '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                    (select '' '' || tuteur.nom || '' '' || tuteur.prenom from gsr_tuteur tuteur where tuteur.id=rentier.idtuteur) as tuteur,
                    rentier.date_naissance as date_naissance,
                    
                    
                    rentier.montant_rente_trimest as rente_trimestriel,
                    rentier.ipp_taux_rente as taux,
                    (select etat_rente.libelle from prm_etat_rente etat_rente where etat_rente.code = rentier.etat_rente) as etat_rente,
                    rentier.usersas_createur as utilisateur
                    
                    from gsr_rente rente, gsr_rentier rentier 
                    where to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) >= 21
                    and rentier.etat_rente <> 5
                    and rentier.lien_parente >= 20 
                    and rente.id = rentier.gsr_rente'
                                       ,1, 13); 
                                       
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(165, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 160, 1, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(166, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 2, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(167, 'CHAMP', 'classe', 'java.lang.String', 'Classe', '', 'Center', 70, 3, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(168, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 200, 4, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(169, 'CHAMP', 'tuteur', 'java.lang.String', 'Tuteur', '', 'Left', 200, 5, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(170, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Nais', 'dd/MM/yyyy', 'Center', 80, 6, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(171, 'CHAMP', 'rente_trimestriel', 'java.lang.Double', 'Rente Trimest', '#,##0.00', 'Right', 120, 7, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(172, 'CHAMP', 'taux', 'java.lang.String', 'Taux (%)', '', 'Center', 70, 8, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(173, 'CHAMP', 'etat_rente', 'java.lang.String', 'Etat Rente', '', 'Center', 120, 9, 13);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(175, 'CHAMP', 'utilisateur', 'java.lang.String', 'Utilisateur', '', 'Left', 80, 11, 13);
              



