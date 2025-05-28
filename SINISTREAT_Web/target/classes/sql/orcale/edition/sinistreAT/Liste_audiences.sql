-- Entete
insert into SIN_ENTETE(id, sous_titre) values(8, null);

-- ligne titre
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(9, 'LISTE DES AUDIENCES', 8);

-- Recap generale
insert into SIN_RECAP(id, type_fille, description) values(4, 'RECAP_GENERALE', 'Summary liste des audiences');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(8, '08', 'Liste des audiences',
                'select audience.dateconvocation as date_audience,
                       proc.idjuridiction as code_trb, tribunal.designation as tribunal,
                       proc.numerodossiertribunal as numero_dossier,
                       to_char(audience.heureconvocation, ''HH24:MI'') as heure, audience.numerosalleaudience as salle,  
                       case length(sinistre.numerosinistre)
                        when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                        || '' '' || substr(sinistre.numerosinistre, 12) 
                        else sinistre.numerosinistre
                        end numero_sinistre, 
                       sinistre.numerograve as numero_grave,
                       '' '' || victime.nom  || '' '' || victime.prenom as nom_victime,
                       audience.motifconvocation as motif_convocation
                       

                from    sin_recours_audience audience, sin_recours recours, sin_recours_proc proc, 
                        syn_tribunal tribunal, sin_sinistre sinistre, sin_victime victime
                        
                        
                where to_date(to_char(audience.dateconvocation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
                and audience.idprocedurejudiciaire = proc.id
                and proc.idjuridiction = tribunal.id
                and recours.id = proc.idrecours
                and recours.idsinistre = sinistre.id
                and sinistre.idvictime =  victime.id
                order by code_trb, date_audience'
        ,1, 8, 4);
        

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(108, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(109, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 8);

-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(6, 'grTribunal', '$F{code_trb}', '1', 1, 8);


-- Champ
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(110, 'CHAMP', 'date_audience', 'java.sql.Timestamp', 'Date Audi.', 'dd/MM/yyyy', 'Center', 65, 1, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(111, 'CHAMP', 'code_trb', 'java.lang.String', 'Code.Tr', '', 'Center', 50, 2, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(112, 'CHAMP', 'tribunal', 'java.lang.String', 'Libelle Tribunal', '', 'Left', 250, 3, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(113, 'CHAMP', 'numero_dossier', 'java.lang.String', 'N° Dossier', '', 'Center', 60, 4, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(114, 'CHAMP', 'heure', 'java.lang.String', 'Heure', '', 'Center', 40, 5, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(115, 'CHAMP', 'salle', 'java.lang.String', 'Salle', '', 'Center', 34, 6, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(116, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 7, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(117, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 34, 8, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(118, 'CHAMP', 'nom_victime', 'java.lang.String', 'Nom Victime', '', 'Left', 200, 9, 8);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(119, 'CHAMP', 'motif_convocation', 'java.lang.String', 'Motif Convocation', '', 'Left', 200, 10, 8);


-- Recap group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (120, 'VARIABLE', 'nbreDossiersTrb', 'java.lang.Integer', 'Nombre dossiers : ', '', 'Left', 'Count', '$F{numero_sinistre}', 1, 6);                               

-- Recap generale            
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (121, 'VARIABLE', 'nbreTotalDossiers', 'java.lang.Integer', 'Total Général : ', 'Left', 'Count', '$F{numero_sinistre}', 1, 4);






