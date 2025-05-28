--Entete
insert into SIN_ENTETE(id, sous_titre) values(40, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(53, 'Etat des rentes suspendus pour défaut de certificat par type', 40);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(40, '40', 'Etat des rentes suspendus pour défaut de certificat par type',
                'select  type_certificat.libelle as type_certificat,
                        situation_mvt.id as id_situation_mvt, 
                        mouvement.id as id_mvt,
                        rente.numero_rente as numero_rente,
                        rentier.lien_parente as classe,
                        '' ''|| rentier.nom || '' '' || rentier.prenom as nom_rentier,
                        '' '' || tuteur.nom || '' '' || tuteur.prenom as tuteur,
                        rentier.adresse as adresse,
                        ville.lblville as ville,
                        situation_mvt.dat_etat as date_suspension,
                        null as mnt_emis
                        
                from prm_situation_mouvement situation_mvt, gsr_mouvement mouvement, gsr_rente rente, 
                        syn_ville ville, prm_gsr_TypeCertificat type_certificat,
                        gsr_rentier rentier left outer join gsr_tuteur tuteur on rentier.idtuteur = tuteur.id
                where situation_mvt.dat_etat between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and situation_mvt.id_etat = 2
                and situation_mvt.cod_motif = ''4''
                and situation_mvt.idsmouvement = mouvement.id
                and mouvement.typefille = ''GSR_MVTSUSPENSION''
                and mouvement.idsrentier = rentier.id
                and rentier.gsr_rente = rente.id
                and rentier.ville = ville.codville
                and mouvement.idsType_certificat = type_certificat.id
                order by type_certificat'
                               
                                       ,1, 40);  
                                     
                              
                                           
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(476, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 40);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(477, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 40);

-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, id_sin_rapport)
values(11, 'grTypeCertificat', '$F{type_certificat}', '1', 40);

-- Champs group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(478, 'CHAMP', 'type_certificat', 'java.lang.String', 'Type Certificat : ', 1, 11);

-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(479, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 1, 40);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(480, 'CHAMP', 'classe', 'java.lang.String', 'Classe', '', 'Center', 60, 2, 40);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(481, 'CHAMP', 'nom_rentier', 'java.lang.String', 'Nom Rentier', '', 'Left', 200, 3, 40);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(482, 'CHAMP', 'tuteur', 'java.lang.String', 'Tuteur', '', 'Left', 200, 4, 40);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(483, 'CHAMP', 'adresse', 'java.lang.String', 'Adresse', '', 'Left', 250, 5, 40);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(484, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Left', 100, 6, 40);


insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(485, 'CHAMP', 'date_suspension', 'java.sql.Timestamp', 'Date Suspension', 'dd/MM/yyyy', 'Center', 80, 7, 40);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(486, 'CHAMP', 'mnt_emis', 'java.lang.Double', 'Rentes emises', '#,##0.00', 'Right', 120, 8, 40);



