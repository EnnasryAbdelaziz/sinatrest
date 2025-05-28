--Entete
insert into SIN_ENTETE(id, sous_titre) values(50, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(64, 'Etat des dossiers rente  remis en cours après suspension', 50);

insert into SIN_RECAP(id, type_fille, description) values(17, 'RECAP_GENERALE', 'Summary Etat des dossiers rente  remis en cours après suspension');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(50, '50', 'Etat des dossiers rente  remis en cours après suspension',
                'select rente.numero_rente as numero_rente,
                           '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                           (select nvl(sum(quittance.montant), 0) 
                           from gsr_quittance quittance 
                           where quittance.idsrentier = rentier.id 
                           and quittance.dat_emission between mvt_remise.dat_suspension and  mvt_remise.dat_mise_en_vigeur) as cumul_arrerage,
                           situation_mvt_remise.operateur as utilisateur,
                           mvt_remise.dat_suspension as date_suspension,
                           (select motif.libelle from prm_motif_situationetat motif where motif.code = situation_mvt_suspension.cod_motif) as motif_supension,
                           mvt_remise.dat_mise_en_vigeur as date_remise_en_cours
                    from gsr_mouvement mvt_remise, gsr_rentier rentier, gsr_rente rente, 
                        prm_situation_mouvement situation_mvt_remise, prm_situation_mouvement situation_mvt_suspension
                        
                    where mvt_remise.dat_mise_en_vigeur between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
                    and mvt_remise.idstypemvt = 11
                    and mvt_remise.idsetatmvt = 2
                    and mvt_remise.idsrentier = rentier.id
                    and rentier.gsr_rente = rente.id
                    and situation_mvt_remise.id in (select min(sit_mvt.id) from prm_situation_mouvement sit_mvt where sit_mvt.idsmouvement = mvt_remise.id)
                    and situation_mvt_suspension.id in (select min(sit_mvt_suspension.id) 
                                                        from prm_situation_mouvement sit_mvt_suspension, gsr_mouvement mvt_suspension 
                                                        where sit_mvt_suspension.idsmouvement = mvt_suspension.id
                                                        and mvt_suspension.idstypemvt = 13
                                                        and mvt_suspension.idsrentier = rentier.id
                                                        and to_date(sit_mvt_suspension.dat_etat, ''dd/MM/yyyy'') <= to_date(mvt_remise.dat_mise_en_vigeur, ''dd/MM/yyyy''))'         
                                       ,1, 50, 17);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(590, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 50);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(591, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 50);


-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(592, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 100, 1, 50);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(593, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 250, 2, 50);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(594, 'CHAMP', 'cumul_arrerage', 'java.lang.Double', 'Arrérages impayés', '#,##0.00', 'Right', 100, 3, 50);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(595, 'CHAMP', 'utilisateur', 'java.lang.String', 'Utilisateur', '', 'Left', 100, 4, 50);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(596, 'CHAMP', 'date_suspension', 'java.sql.Timestamp', 'Date suspension', 'dd/MM/yyyy', 'Center', 120, 5, 50);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(597, 'CHAMP', 'motif_supension', 'java.lang.String', 'Motif suspension', '', 'Left', 200, 6, 50);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(598, 'CHAMP', 'date_remise_en_cours', 'java.sql.Timestamp', 'Date remise', 'dd/MM/yyyy', 'Center', 120, 7, 50);
                                 
-- Recap generale
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (599, 'VARIABLE', 'nbreDossiers', 'java.lang.Integer', 'Nombre de dossiers  : ', 'Left', 'Count', '$F{numero_rente}', 1, 17);                               

