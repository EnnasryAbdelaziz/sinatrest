select * from SIN_LIGNE_TITRE
-- Entete
insert into SIN_ENTETE(id, sous_titre) values(7, null);

-- ligne titre
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(8, 'ETAT DES DECISIONS JUDICIAIRES', 7);
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(52, 'decision', '1', 7);

-- Recap generale
insert into SIN_RECAP(id, type_fille, description) values(3, 'RECAP_GENERALE', 'Summary Etat decisions judiciaires');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(7, '07', 'Etat des decisions judiciares',
        'select 
       proc.idjuridiction as code_trb, tribunal.designation as tribunal,
       case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numero_sinistre, 
       sinistre.numerograve as numero_grave,
       '' '' || victime.nom  || '' '' || victime.prenom as nom_victime,
       jugement.libelle as suite_decision 

        from    sin_recours_audience audience, sin_recours recours, sin_recours_proc proc, 
                syn_tribunal tribunal, prm_suite_jugement jugement, sin_sinistre sinistre, sin_victime victime
                
        where audience.datecreation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')+1
        and audience.iddecision = $P{codeDecision}
        and audience.idsuitejugement = jugement.code
        and audience.idprocedurejudiciaire = proc.id
        and proc.idjuridiction = tribunal.id
        and recours.id = proc.idrecours
        and recours.idsinistre = sinistre.id
        and sinistre.idvictime =  victime.id
        order by code_trb'
        ,1, 7, 3);
 
--Parametres rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(475, 'PARAMETRE', 'codeDecision', 'java.lang.String', 7);
 

--03/03/2014
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(97, 'PARAMETRE', 'dateDebut', 'java.lang.String', 7);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(98, 'PARAMETRE', 'dateFin', 'java.lang.String', 7);
  
   
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(718, 'PARAMETRE', 'periodeEtat', 'java.lang.String', ' ', 1, 7);

-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(5, 'grTribunal', '$F{code_trb}', '1', 2, 7);

 
-- Champs
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(100, 'CHAMP', 'code_trb', 'java.lang.String', 'Code Trb.', '', 'Center', 80, 1, 7);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(101, 'CHAMP', 'tribunal', 'java.lang.String', 'Libelle Tribunal', '', 'Left', 200, 2, 7);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(102, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 190, 3, 7);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(103, 'CHAMP', 'numero_grave', 'java.lang.String', 'N° Grave', '', 'Center', 80, 4, 7);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(104, 'CHAMP', 'nom_victime', 'java.lang.String', 'Nom Victime', '', 'Left', 200, 5, 7);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(105, 'CHAMP', 'suite_decision', 'java.lang.String', 'Suite Décision', '', 'Left', 120, 6, 7);

-- Recap group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (106, 'VARIABLE', 'nbreDossiersTrb', 'java.lang.Integer', 'Nombre dossiers : ', '', 'Left', 'Count', '$F{numero_sinistre}', 1, 5);                               

-- Recap generale            
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (107, 'VARIABLE', 'nbreTotalDossiers', 'java.lang.Integer', 'Total Général : ', 'Left', 'Count', '$F{numero_sinistre}', 1, 3);

      
--28/02/2014
delete from SIN_RAPPORT_ELEMENT where id=101;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(101, 'CHAMP', 'tribunal', 'java.lang.String', 'Libelle Tribunal', '', 'Left', 310, 2, 7);


update SIN_RAPPORT_ELEMENT
set requete_sql='select 
       proc.idjuridiction as code_trb, tribunal.designation as tribunal,
       case length(sinistre.numerosinistre)
        when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
        || '' '' || substr(sinistre.numerosinistre, 12) 
        else sinistre.numerosinistre
        end as numero_sinistre, 
       sinistre.numerograve as numero_grave,
       '' '' || victime.nom  || '' '' || victime.prenom as nom_victime,
       jugement.libelle as suite_decision 

        from    sin_recours_audience audience, sin_recours recours, sin_recours_proc proc, 
                syn_tribunal tribunal, prm_suite_jugement jugement, sin_sinistre sinistre, sin_victime victime
                
        where audience.datecreation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')+1
        and audience.iddecision = $P{codeDecision}
        and audience.idsuitejugement = jugement.code
        and audience.idprocedurejudiciaire = proc.id
        and proc.idjuridiction = tribunal.id
        and recours.id = proc.idrecours
        and recours.idsinistre = sinistre.id
        and sinistre.idvictime =  victime.id
        order by code_trb'
    where id=7








        
        


