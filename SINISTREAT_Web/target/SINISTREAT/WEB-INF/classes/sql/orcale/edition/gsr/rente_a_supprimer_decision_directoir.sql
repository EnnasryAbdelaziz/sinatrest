--Entete
insert into SIN_ENTETE(id, sous_titre) values(42, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(55, 'Etat des rentes à supprimer suite à une décision du directoire', 42);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(42, '42', 'Etat des rentes à supprimer suite à une décision du directoire',
                'select
                            rentier.id as id ,
                            rente.numero_rente as numero_rente,
                            rentier.lien_parente as classe_rentier,
                            to_number(to_char(rentier.date_naissance, ''yyyy''))  as date_naissance,
                            '' '' || rentier.nom || '' '' || rentier.prenom as nom_rentier,
                            '' '' || tuteur.nom || '' '' || tuteur.prenom as tuteur,
                            rentier.date_etat as date_suspension,
                            motif.libelle as motif_suspension

                        from prm_situation_mouvement situation_mvt_suppression, gsr_mouvement mouvement,
                                prm_situation_mouvement situation_mvt_suspension, prm_motif_situationetat motif,
                                gsr_rente rente, gsr_rentier rentier left outer join gsr_tuteur tuteur on rentier.idtuteur = tuteur.id
                        where to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) > $P{age}
                        and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_etat, ''yyyy'')) = $P{nbre_annee_susp}
                        and rentier.gsr_rente = rente.id
                        and mouvement.id in (select max(id) from gsr_mouvement mvt 
                                                          where mvt.typefille = ''GSR_MVTSUPPRESSION'' 
                                                        and mvt.idsrentier = rentier.id)
                        and mouvement.idsetatmvt = 1
                        and situation_mvt_suppression.idsmouvement = mouvement.id
                        and situation_mvt_suppression.cod_motif = ''20''
                        and situation_mvt_suspension.IDSMOUVEMENT in (select max(id) from gsr_mouvement m 
                                                            where m.typefille = ''GSR_MVTSUSPENSION'' 
                                                            and m.idsrentier = rentier.id)
                        and situation_mvt_suspension.id_etat = 2
                        and situation_mvt_suspension.cod_motif = motif.code'
                               
                                       ,1, 42);  
                                     
                              
                                           
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(497, 'PARAMETRE', 'nbre_annee_susp', 'java.lang.Integer', 'Nombre année suspension : ', 1, 42);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(498, 'PARAMETRE', 'age', 'java.lang.Integer', 'Age rentier supérieur à : ', 2, 42);


-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(499, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 1, 42);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(500, 'CHAMP', 'classe_rentier', 'java.lang.String', 'Classe', '', 'Center', 60, 2, 42);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(501, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Naissance', 'dd/MM/yyyy', 'Center', 80, 3, 42);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(502, 'CHAMP', 'nom_rentier', 'java.lang.String', 'Nom et Prénom', '', 'Left', 200, 4, 42);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(503, 'CHAMP', 'tuteur', 'java.lang.String', 'Tuteur', '', 'Left', 200, 5, 42);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(504, 'CHAMP', 'date_suspension', 'java.sql.Timestamp', 'Date Suspension', 'dd/MM/yyyy', 'Center', 80, 6, 42);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(505, 'CHAMP', 'motif_suspension', 'java.lang.String', 'Motif suspension', '', 'Left', 250, 7, 42);







                
                
                
                
