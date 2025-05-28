-- Entete
insert into SIN_ENTETE(id, sous_titre) values(32, null);

-- ligne titre
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(42, 'titre', '1', 32);

-- Recap generale
insert into SIN_RECAP(id, type_fille, description) values(11, 'RECAP_GENERALE', 'Summary liste des audiences par ville');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(32, '32', 'Liste des audiences par ville',
                'select audience.dateconvocation as date_audience,
                       proc.idjuridiction as code_trb, tribunal.designation as tribunal,
                       proc.numerodossiertribunal as numero_dossier,
                       to_char(audience.heureconvocation, ''HH24:MI'') as heure, audience.numerosalleaudience as salle,  
                       case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numero_sinistre, 
                       sinistre.numerograve as numero_grave,
                       '' '' || victime.nom  || '' '' || victime.prenom as nom_victime,
                       audience.motifconvocation as motif_convocation
                       

                from    sin_recours_audience audience, sin_recours recours, sin_recours_proc proc, 
                        syn_tribunal tribunal, sin_sinistre sinistre, sin_victime victime
                        
                        
                where audience.datecreation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')+1
                and tribunal.id_ville = $P{codeVille} 
                and audience.idprocedurejudiciaire = proc.id
                and proc.idjuridiction = tribunal.id
                and recours.id = proc.idrecours
                and recours.idsinistre = sinistre.id
                and sinistre.idvictime =  victime.id
                order by date_audience, code_trb'
        ,1, 32, 11);
        

--Parametres rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(474, 'PARAMETRE', 'ville', 'java.lang.String', 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(476, 'PARAMETRE', 'codeVille', 'java.lang.String', 32);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(345, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(346, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 32);

-- Champ
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(347, 'CHAMP', 'date_audience', 'java.sql.Timestamp', 'Date Audi.', 'dd/MM/yyyy', 'Center', 70, 1, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(348, 'CHAMP', 'code_trb', 'java.lang.String', 'Code.Tr', '', 'Center', 70, 2, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(349, 'CHAMP', 'tribunal', 'java.lang.String', 'Libelle Tribunal', '', 'Left', 300, 3, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(350, 'CHAMP', 'numero_dossier', 'java.lang.String', 'N° dossier', '', 'Center', 80, 4, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(351, 'CHAMP', 'heure', 'java.lang.String', 'Heure', '', 'Center', 40, 5, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(352, 'CHAMP', 'salle', 'java.lang.String', 'Salle', '', 'Center', 80, 6, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(353, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 7, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(354, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 60, 8, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(355, 'CHAMP', 'nom_victime', 'java.lang.String', 'Nom Victime', '', 'Left', 200, 9, 32);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(356, 'CHAMP', 'motif_convocation', 'java.lang.String', 'Motif Convocation', '', 'Left', 200, 10, 32);


-- Recap generale            
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (357, 'VARIABLE', 'nbreTotalDossiers', 'java.lang.Integer', 'Nombre dossiers : ', 'Left', 'Count', '$F{numero_sinistre}', 1, 11);




--27-02-2014
delete from SIN_RAPPORT_ELEMENT where id=347;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(347, 'CHAMP', 'date_audience', 'java.sql.Timestamp', 'Date Audi.', 'dd/MM/yyyy', 'Center', 80, 1, 32);

delete from SIN_RAPPORT_ELEMENT where id=349;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(349, 'CHAMP', 'tribunal', 'java.lang.String', 'Libelle Tribunal', '', 'Left', 310, 3, 32);

delete from SIN_RAPPORT_ELEMENT where id=348;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(348, 'CHAMP', 'code_trb', 'java.lang.String', 'Code.Tr', '', 'Center', 70, 2, 32);

delete from SIN_RAPPORT_ELEMENT where id=350;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(350, 'CHAMP', 'numero_dossier', 'java.lang.String', 'N° dossier', '', 'Center', 80, 4, 32);

delete from SIN_RAPPORT_ELEMENT where id=353;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(353, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 7, 32);


delete from SIN_RAPPORT_ELEMENT where id=354;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(354, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 60, 8, 32);



