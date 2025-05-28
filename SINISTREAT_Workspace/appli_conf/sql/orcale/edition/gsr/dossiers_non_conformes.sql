--Entete
insert into SIN_ENTETE(id, sous_titre) values(43, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(56, 'Etat des dossiers non conformes', 43);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(43, '43', 'Etat des dossiers non conformes',
                'select 
                    case length(sinistre.numerosinistre)
                    when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                    || '' '' || substr(sinistre.numerosinistre, 12) 
                    else sinistre.numerosinistre
                    end numero_sinistre,
                    sinistre.numerograve as numero_grave,
                    '' ''|| rentier.nom || '' '' || rentier.prenom as nom_prenom,
                    rentier.date_creation as date_,
                    null as motif_rejet
                    
                from gsr_rentier rentier, gsr_rente rente, sin_sinistre sinistre
                where rentier.etat_rente = 16
                and rentier.date_creation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and rentier.gsr_rente = rente.id
                and rente.dossier_sinistre = sinistre.id'
                               
                                       ,1, 43);  
                                     
                              
                                           
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(506, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 43);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(507, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 43);


-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(508, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 180, 1, 43);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(509, 'CHAMP', 'numero_grave', 'java.lang.String', 'N° Grave', '', 'Center', 80, 2, 43);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(510, 'CHAMP', 'nom_prenom', 'java.lang.String', 'Nom Rentier', '', 'Left', 200, 3, 43);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(511, 'CHAMP', 'date_', 'java.sql.Timestamp', 'Date', 'dd/MM/yyyy', 'Center', 80, 4, 43);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(512, 'CHAMP', 'motif_rejet', 'java.lang.String', 'Motif rejet', '', 'Left', 250, 5, 43);


