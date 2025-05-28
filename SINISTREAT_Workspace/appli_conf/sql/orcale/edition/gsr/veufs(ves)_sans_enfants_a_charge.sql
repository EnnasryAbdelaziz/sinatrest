--Enete
insert into SIN_ENTETE(id, sous_titre) values(16, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(20, 'Etats des Veufs(ves) sans enfants à charge', 16);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(16, '16', 'Etats des Veufs(ves) sans enfants à charge',
                'select          
                        rente.numero_rente as numero_rente,
                        rentier.lien_parente as classe,
                        '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                        rentier.adresse as adresse,
                        (select libelle from syn_ville where id = rentier.ville) as ville,
                        rentier.date_naissance as date_naissance,
                        (select libelle from prm_etat_rente where code = rentier.etat_rente) as etat_rente
                        
                from            
                        gsr_rente rente, gsr_rentier rentier
                where rentier.lien_parente in (10, 11, 12, 13)
                and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) >= to_number($P{ageMin})
                and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) <= to_number($P{ageMax})
                and rentier.gsr_rente = rente.id
                and rente.id in (
                                    select id_rente from
                                        (select id_rente, min(etat) as etat from
                                        (select distinct rente.id as id_rente, rentier.etat_rente as etat
                                                from gsr_rente rente, gsr_rentier rentier
                                                where rentier.gsr_rente = rente.id
                                                and rentier.lien_parente >= 20) 
                                        group by id_rente
                                        having count(*) = 1)
                                        where etat = 10
                                  )'
                               
                                       ,1, 16);                                                            
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(588, 'PARAMETRE', 'ageMin', 'java.lang.String', 'Age min : ', 1, 16);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(589, 'PARAMETRE', 'ageMax', 'java.lang.String', 'Age max : ', 2, 16);

                 
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(206, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 60, 2, 16);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(207, 'CHAMP', 'classe', 'java.lang.String', 'Cls.', '', 'Center', 40, 3, 16);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(208, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 160, 4, 16);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(209, 'CHAMP', 'adresse', 'java.lang.String', 'Adresse', '', 'Left', 250, 5, 16);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(210, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Left', 170, 6, 16);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(211, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Naiss', 'dd/MM/yyyy', 'Center', 70, 7, 16);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(215, 'CHAMP', 'etat_rente', 'java.lang.String', 'Etat', '', 'Center', 80, 11, 16);


                            
         
               

