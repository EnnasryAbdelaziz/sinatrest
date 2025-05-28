--Entete
insert into SIN_ENTETE(id, sous_titre) values(51, null);

insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(65, 'titre', '1', 51);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(51, '51', 'Etat récaupitulatif des Mandats emis par bénéficiaire',
                'select 
                        quittance.exercice as echeance,
                        quittance.beneficiaire as beneficiaire,
                        rentier.numero_cin as cin,
                        prerglt.adresse as adresse,
                        (select ville.libelle from syn_ville ville where ville.id = prerglt.codeville) as ville,
                        quittance.ref_reglement as num_emission,
                        quittance.montant as mnt_mondat,
                        rentier.montant_rente_trimest as rente_trim,
                        quittance.dat_emission as date_emission,
                        (select etat_qtc.libelle from prm_etatqtc etat_qtc where etat_qtc.id = quittance.idsetat_qtc) as etat_qtc
                        
                    from gsr_quittance quittance, gsr_prerglt prerglt, gsr_rentier rentier
                    
                    
                    where quittance.date_creation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
                    and quittance.idsmode_reglement=3 
                    and quittance.idsmouvement is null
                    and quittance.idcomplement is null
                    and quittance.idsprerglt = prerglt.id
                    and quittance.idsrentier = rentier.id
                    and quittance.numero_rente = $P{numRente}
                    and rentier.lien_parente = $P{classe}'
                               
                                       ,1, 51);
                                       
  -- Parametres de rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(600, 'PARAMETRE', 'numRente', 'java.lang.String', 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(601, 'PARAMETRE', 'classe', 'java.lang.String', 51);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(602, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(603, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 51);


-- Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(604, 'CHAMP', 'echeance', 'java.lang.String', 'Echeance', '', 'Center', 80, 1, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(605, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 200, 2, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(606, 'CHAMP', 'cin', 'java.lang.String', 'CIN', '', 'Left', 100, 3, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(607, 'CHAMP', 'adresse', 'java.lang.String', 'Adresse', '', 'Left', 250, 4, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(608, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Left', 120, 5, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(609, 'CHAMP', 'num_emission', 'java.lang.String', 'Num Emission', '', 'Center', 120, 6, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(610, 'CHAMP', 'mnt_mondat', 'java.lang.Double', 'Mnt mondat', '#,##0.00', 'Right', 100, 7, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(611, 'CHAMP', 'rente_trim', 'java.lang.Double', 'Mnt rente', '#,##0.00', 'Right', 100, 8, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(612, 'CHAMP', 'date_emission', 'java.sql.Timestamp', 'Date Emi.', 'dd/MM/yyyy', 'Center', 80, 9, 51);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(613, 'CHAMP', 'etat_qtc', 'java.lang.String', 'Etat qtc', '', 'Center', 100, 10, 51);

