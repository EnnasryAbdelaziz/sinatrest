--Entete
insert into SIN_ENTETE(id, sous_titre) values(44, null);

insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(57, 'titre', '1', 44);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(44, '44', 'Etat des Mandats retournés Impayés de la rente:  < num rente>',
                'select 
                        quittance.exercice as echeance,
                        quittance.beneficiaire as beneficiaire,
                        rentier.numero_cin as cin,
                        quittance.montant as mnt_mondat,
                        null as num_emission,
                        quittance.dat_emission as date_emission,
                        prerglt.adresse as adresse,
                        (select ville.libelle from syn_ville ville where ville.id = prerglt.codeville) as ville
                from gsr_quittance quittance, gsr_prerglt prerglt, gsr_rentier rentier
                
                where quittance.idsmode_reglement=3 
                and quittance.idsetat_qtc=3 
                and quittance.ref_reglement is not null
                and quittance.idsprerglt = prerglt.id
                and quittance.idsrentier = rentier.id
                and (quittance.numero_rente = $P{numRente}  or rentier.lien_parente = $P{classe} )'
                               
                                       ,1, 44);
                                      
  -- Parametres de rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(519, 'PARAMETRE', 'numRente', 'java.lang.String', 44);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(574, 'PARAMETRE', 'classe', 'java.lang.String', 44);
                                      
-- Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(575, 'CHAMP', 'echeance', 'java.lang.String', 'Echeance', '', 'Center', 80, 1, 44);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(576, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 200, 2, 44);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(577, 'CHAMP', 'cin', 'java.lang.String', 'CIN', '', 'Left', 100, 3, 44);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(514, 'CHAMP', 'mnt_mondat', 'java.lang.Double', 'Mnt mandat', '#,##0.00', 'Right', 100, 4, 44);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(515, 'CHAMP', 'num_emission', 'java.lang.String', 'Num Emission', '', 'Center', 120, 5, 44);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(516, 'CHAMP', 'date_emission', 'java.sql.Timestamp', 'Date Emi.', 'dd/MM/yyyy', 'Center', 80, 6, 44);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(517, 'CHAMP', 'adresse', 'java.lang.String', 'Adresse', '', 'Left', 250, 7, 44);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(518, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Left', 120, 8, 44);


