--Entete
insert into SIN_ENTETE(id, sous_titre) values(12, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(15, 'Etat des rentes à mettre en attente pour défaut de certificats', 12);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(12, '12', 'Etat des rentes à mettre en attente pour défaut de certificats de Vie/Non Remariage/Scolarité',
                'select certificat, numero_rente, classe, beneficiaire, ipp, rente_trimestriel, ville from
                (select 
                        ''Certificat de vie'' as certificat,
                        rente.numero_rente as numero_rente,
                        rentier.lien_parente as classe,
                        '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                        rentier.ipp_taux_rente as ipp,
                        rentier.montant_rente_trimest as rente_trimestriel,
                        (select ville.libelle from syn_ville ville where rentier.ville = ville.id) as ville
                from gsr_rentier rentier, gsr_rente rente
                where rentier.id not in (select idsrentier from gsr_mouvement mvt
                    where mvt.typefille = ''GSR_MVTRCPTCERTIF''
                    and mvt.idstype_certificat = 1
                    and mvt.idsetatmvt = 4
                    and sysdate between mvt.dat_du and mvt.dat_au)
                and rente.id = rentier.gsr_rente
              
                
                union
                
                select 
                        ''Certificat de non remariage'' as certificat,
                        rente.numero_rente as numero_rente,
                        rentier.lien_parente as classe,
                        '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                        rentier.ipp_taux_rente as ipp,
                        rentier.montant_rente_trimest as rente_trimestriel,
                        (select ville.libelle from syn_ville ville where rentier.ville = ville.id) as ville
                from gsr_rentier rentier, gsr_rente rente
                where rentier.LIEN_PARENTE in (10, 11, 12, 13)
                and rentier.id not in (select idsrentier from gsr_mouvement mvt
                    where mvt.typefille = ''GSR_MVTRCPTCERTIF''
                    and mvt.idstype_certificat = 3
                    and mvt.idsetatmvt = 4
                    and sysdate between mvt.dat_du and mvt.dat_au)
                and rente.id = rentier.gsr_rente
                
                
                union
                
                select 
                        ''Certificat de scolarité'' as certificat,
                        rente.numero_rente as numero_rente,
                        rentier.lien_parente as classe,
                        '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                        rentier.ipp_taux_rente as ipp,
                        rentier.montant_rente_trimest as rente_trimestriel,
                        (select ville.libelle from syn_ville ville where rentier.ville = ville.id) as ville
                from gsr_rentier rentier, gsr_rente rente
                where rentier.LIEN_PARENTE >= 20
                and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) >=16
                and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) <=21
                and rentier.id not in (select idsrentier from gsr_mouvement mvt
                    where mvt.typefille = ''GSR_MVTRCPTCERTIF''
                    and mvt.idstype_certificat = 4
                    and mvt.idsetatmvt = 4
                    and sysdate between mvt.dat_du and mvt.dat_au)
                and rente.id = rentier.gsr_rente)
                
                order by certificat'
                               
                                       ,1, 12);
                               
-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(12, 'grTypeCertificat', '$F{certificat}', '1', 1, 12);

-- Champs group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(513, 'CHAMP', 'certificat', 'java.lang.String', ' ', 1, 12);
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(159, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 2, 12);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(160, 'CHAMP', 'classe', 'java.lang.String', 'Classe', '', 'Center', 70, 3, 12);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(161, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 200, 4, 12);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(162, 'CHAMP', 'ipp', 'java.lang.String', 'IPP (%)', '', 'Center', 70, 5, 12);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(163, 'CHAMP', 'rente_trimestriel', 'java.lang.Double', 'Rente Trimestriel', '#,##0.00', 'Right', 120, 6, 12);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(164, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Left', 180, 7, 12);


                  

