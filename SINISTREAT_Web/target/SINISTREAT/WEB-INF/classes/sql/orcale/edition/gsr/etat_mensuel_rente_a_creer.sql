--Enete
insert into SIN_ENTETE(id, sous_titre) values(46, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(60, 'Etat mensuel des rentes à créer', 46);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(46, '46', 'Etat mensuel des rentes à créer',
                'select numero_sinistre, numero_grave, nom_prenom, taux_ipp, adresse, ville
                            from (select
                                sinistre.numerosinistre as numero_sinistre,
                                sinistre.numerograve as numero_grave,
                                '' '' || victime.nom || '' '' || victime.prenom as nom_prenom,
                                sinistre.ipp as taux_ipp,
                                victime.adresse as adresse,
                                (select  ville.libelle from syn_ville ville where ville.id=victime.codeville) as ville
                                
                            from sin_sinistre sinistre, sin_victime victime
                            where sinistre.datedepartrente is not null
                            and (sinistre.rente_creee is null or sinistre.rente_creee = 0)
                            and sinistre.idvictime = victime.id
                            and victime.ISDECEDE <> ''1''

                            union

                            select
                                sinistre.numerosinistre as numero_sinistre,
                                sinistre.numerograve as numero_grave,
                                '' '' || ayant_droit.nom || '' '' || ayant_droit.prenom as nom_prenom,
                                ayant_droit.tauxrente as taux_ipp,
                                ayant_droit.adresse as adresse,
                                (select  ville.libelle from syn_ville ville where ville.id=ayant_droit.codeville) as ville

                                
                            from sin_sinistre sinistre, sin_victime victime, sin_ayantdroit ayant_droit
                            where sinistre.datedepartrente is not null
                            and (sinistre.rente_creee is null or sinistre.rente_creee = 0)
                            and sinistre.idvictime = victime.id
                            and victime.ISDECEDE = ''1''
                            and victime.id = ayant_droit.idvictime)'
                               
                                       ,1, 46);                                                            
                  
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(545, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 180, 1, 46);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(546, 'CHAMP', 'numero_grave', 'java.lang.String', 'N° Grv.', '', 'Center', 80, 2, 46);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(547, 'CHAMP', 'nom_prenom', 'java.lang.String', 'Nom Prénom', '', 'Left', 200, 3, 46);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(548, 'CHAMP', 'taux_ipp', 'java.lang.String', 'Taux (%)', '', 'Center', 80, 4, 46);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(549, 'CHAMP', 'adresse', 'java.lang.String', 'Adresse', '', 'Left', 250, 5, 46);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(550, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Left', 120, 6, 46);

