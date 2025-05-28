--Entete
insert into SIN_ENTETE(id, sous_titre) values(41, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(54, 'Etat des rentes suspendus pour motif (hors défaut de certificat)', 41);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(41, '41', 'Etat des rentes suspendus pour défaut de certificat par type',
                'select  
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
                        syn_ville ville
                        gsr_rentier rentier left outer join gsr_tuteur tuteur on rentier.idtuteur = tuteur.id
                where situation_mvt.dat_etat between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and situation_mvt.id_etat = 2
                and situation_mvt.cod_motif != ''4''
                and situation_mvt.idsmouvement = mouvement.id
                and mouvement.typefille = ''GSR_MVTSUSPENSION''
                and mouvement.idsrentier = rentier.id
                and rentier.gsr_rente = rente.id
                and rentier.ville = ville.codville
                order by type_certificat'
                               
                                       ,1, 41);  
                                     
                              
                                           
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(487, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 41);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(488, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 41);


-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(489, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 1, 41);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(490, 'CHAMP', 'classe', 'java.lang.String', 'Classe', '', 'Center', 60, 2, 41);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(491, 'CHAMP', 'nom_rentier', 'java.lang.String', 'Nom Rentier', '', 'Left', 200, 3, 41);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(492, 'CHAMP', 'tuteur', 'java.lang.String', 'Tuteur', '', 'Left', 200, 4, 41);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(493, 'CHAMP', 'adresse', 'java.lang.String', 'Adresse', '', 'Left', 250, 5, 41);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(494, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Left', 100, 6, 41);


insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(495, 'CHAMP', 'date_suspension', 'java.sql.Timestamp', 'Date Suspension', 'dd/MM/yyyy', 'Center', 80, 7, 41);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(496, 'CHAMP', 'mnt_emis', 'java.lang.Double', 'Rentes emises', '#,##0.00', 'Right', 120, 8, 41);



