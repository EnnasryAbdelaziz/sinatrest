-- Entete
insert into SIN_ENTETE(id, sous_titre) values(33, null);

-- ligne titre
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(43, 'titre', '1', 33);

-- Recap generale
insert into SIN_RECAP(id, type_fille, description) values(12, 'RECAP_GENERALE', 'Summary etat de convocation (journalier, mensuel et annuel)');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(33, '33', 'Etat de convocation (journalier, mensuel et annuel)',
                'select
                        audience.dateconvocation as date_audience, 
                       proc.idjuridiction as code_trb, 
                       tribunal.designation as tribunal,
                       case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numero_sinistre, 
                        sinistre.numerograve as numero_grave,
                        '' '' || victime.nom  || '' '' || victime.prenom as nom_victime,
                        ''  '' || to_char(audience.dateconvocation, ''dd/MM/yyyy'') || ''  '' || to_char(audience.heureconvocation, ''HH24:MI'') as date_heure_aud,
                        audience.numerosalleaudience as salle,  
                        proc.numerodossiertribunal as numero_dossier
                       
                       

                from    sin_recours_audience audience, sin_recours recours, sin_recours_proc proc, 
                        syn_tribunal tribunal, sin_sinistre sinistre, sin_victime victime
                        
                        
                where audience.datecreation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and audience.idprocedurejudiciaire = proc.id
                and proc.idjuridiction = tribunal.id
                and recours.id = proc.idrecours
                and recours.idsinistre = sinistre.id
                and sinistre.idvictime =  victime.id
                order by code_trb, date_audience'
        ,1, 33, 12);
        
--Parametres rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(358, 'PARAMETRE', 'dateDebut', 'java.lang.String', 33);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(359, 'PARAMETRE', 'dateFin', 'java.lang.String', 33);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(360, 'PARAMETRE', 'paramTraitement', 'java.lang.String', ' ', 1, 33);

-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(8, 'grTribunal', '$F{code_trb}', '1', 1, 33);


-- Champ
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(361, 'CHAMP', 'code_trb', 'java.lang.String', 'Code.Tr', '', 'Center', 50, 1, 33);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(362, 'CHAMP', 'tribunal', 'java.lang.String', 'Libelle Tribunal', '', 'Left', 300, 2, 33);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(363, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 160, 3, 33);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(364, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 60, 4, 33);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(365, 'CHAMP', 'nom_victime', 'java.lang.String', 'Nom Victime', '', 'Left', 200, 5, 33);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(366, 'CHAMP', 'date_heure_aud', 'java.lang.String', 'Date et Heure Aud.', '', 'Left', 120, 6, 33);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(367, 'CHAMP', 'salle', 'java.lang.String', 'Salle', '', 'Center', 34, 7, 33);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(368, 'CHAMP', 'numero_dossier', 'java.lang.String', 'N° Dossier', '', 'Center', 70, 8, 33);



-- Recap group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (369, 'VARIABLE', 'nbreDossiersTrb', 'java.lang.Integer', 'Nombre dossiers :  ', '', 'Left', 'Count', '$F{numero_sinistre}', 1, 8);                               

-- Recap generale            
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (370, 'VARIABLE', 'nbreTotalDossiers', 'java.lang.Integer', 'Total Général : ', 'Left', 'Count', '$F{numero_sinistre}', 1, 12);





--28/02/2014
delete from SIN_RAPPORT_ELEMENT where id=363;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(363, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 3, 33);

delete from SIN_RAPPORT_ELEMENT where id=362;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(362, 'CHAMP', 'tribunal', 'java.lang.String', 'Libelle Tribunal', '', 'Left', 310, 2, 33);

delete from SIN_RAPPORT_ELEMENT where id=364;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(364, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 60, 4, 33);

delete from SIN_RAPPORT_ELEMENT where id=366;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(366, 'CHAMP', 'date_heure_aud', 'java.lang.String', 'Date et Heure Aud.', '', 'Left', 130, 6, 33);

delete from SIN_RAPPORT_ELEMENT where id=368;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(368, 'CHAMP', 'numero_dossier', 'java.lang.String', 'N° Dossier', '', 'Center', 70, 8, 33);


