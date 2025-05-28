-- Entete
insert into SIN_ENTETE(id, sous_titre) values(3, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(3, 'ETAT DES DOSSIERS AVEC RESERVE GRAVE ZERO', 3);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(3, '03', 'Etat des dossiers avec réserve grave 0',
        'select prm_etat.libelle as libelle_etat,
        
       case length(sinistre.numeropolice)
        when 15 then substr(sinistre.numeropolice, 1, 4) || '' '' || substr(sinistre.numeropolice, 5, 3) || '' '' || substr(sinistre.numeropolice, 8, 4)
        || '' '' || substr(sinistre.numeropolice, 12) 
        else sinistre.numeropolice
        end numeropolice,
        
       case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numerosinistre,
       sinistre.numerograve as numerograve,
       evenement.dateaccident as dateaccident, sinistre.datedeclaration as datedeclaration, 
       sinistre.reserveordinaire as reserveordinaire, sinistre.reservegrave as reservegrave,
       victime.nom  || '' '' || victime.prenom as nom_victime, sinistre.usercreateur as redacteur
        from sin_sinistre sinistre, sin_victime victime, sin_evenement evenement, sin_etat_sinistre etat, prm_etat_sinistre prm_etat

        where to_date(to_char(sinistre.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
        and sinistre.reservegrave = ''0''
        and sinistre.idvictime =  victime.id
        and sinistre.idevenement = evenement.id
        and sinistre.id = etat.idsinistre
        and etat.codetat = prm_etat.code
        order by libelle_etat'
        ,1, 3);
        
       
-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(1, 'grEtat', '$F{libelle_etat}', '1', 1, 3);

   
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(28, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(29, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 3);

-- champ de group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(30, 'CHAMP', 'libelle_etat', 'java.lang.String', 'Etat : ', 1, 1);

--Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(31, 'CHAMP', 'numeropolice', 'java.lang.String', 'N° Police', '', 'Center', 140, 1, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(32, 'CHAMP', 'codeintermediaire', 'java.lang.String', 'Inter.', '', 'Center', 80, 2, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(33, 'CHAMP', 'numerosinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 3, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(34, 'CHAMP', 'numerograve', 'java.lang.String', 'Grv.', '', 'Center', 60, 4, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(35, 'CHAMP', 'dateaccident', 'java.sql.Timestamp', 'Date Acci.', 'dd/MM/yyyy', 'Center', 80, 5, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(36, 'CHAMP', 'datedeclaration', 'java.sql.Timestamp', 'Date Décl.', 'dd/MM/yyyy', 'Center', 80, 6, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(37, 'CHAMP', 'reserveordinaire', 'java.lang.Double', 'Eval. Ord.', '#,##0.00', 'Right', 78, 7, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(38, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Eval. Grave', '#,##0.00', 'Right', 78, 8, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(39, 'CHAMP', 'nom_victime', 'java.lang.String', 'Nom Victime', '', 'Left', 200, 9, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(40, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 120, 10, 3);




--28/02/2014
delete from SIN_RAPPORT_ELEMENT where id=31;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(31, 'CHAMP', 'numeropolice', 'java.lang.String', 'N° Police', '', 'Center', 140, 1, 3);

delete from SIN_RAPPORT_ELEMENT where id=33;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(33, 'CHAMP', 'numerosinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 3, 3);

delete from SIN_RAPPORT_ELEMENT where id=35;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(35, 'CHAMP', 'dateaccident', 'java.sql.Timestamp', 'Date Acci.', 'dd/MM/yyyy', 'Center', 80, 5, 3);

delete from SIN_RAPPORT_ELEMENT where id=36;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(36, 'CHAMP', 'datedeclaration', 'java.sql.Timestamp', 'Date Décl.', 'dd/MM/yyyy', 'Center', 80, 6, 3);

delete from SIN_RAPPORT_ELEMENT where id=40;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(40, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 120, 10, 3);

delete from SIN_RAPPORT_ELEMENT where id=34;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(34, 'CHAMP', 'numerograve', 'java.lang.String', 'Grv.', '', 'Center', 60, 4, 3);


update sin_rapport
set requete_sql='select prm_etat.libelle as libelle_etat,
        
       case length(sinistre.numeropolice)
        when 15 then substr(sinistre.numeropolice, 1, 4) || '' '' || substr(sinistre.numeropolice, 5, 3) || '' '' || substr(sinistre.numeropolice, 8, 4)
        || '' '' || substr(sinistre.numeropolice, 12) 
        else sinistre.numeropolice
        end numeropolice,
        
       sinistre.codeintermediaire as codeintermediaire, 
       case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numerosinistre,
       sinistre.numerograve as numerograve,
       evenement.dateaccident as dateaccident, sinistre.datedeclaration as datedeclaration, 
       sinistre.reserveordinaire as reserveordinaire, sinistre.reservegrave as reservegrave,
       victime.nom  || '' '' || victime.prenom as nom_victime, sinistre.usercreateur as redacteur
        from sin_sinistre sinistre, sin_victime victime, sin_evenement evenement, sin_etat_sinistre etat, prm_etat_sinistre prm_etat

        where to_date(to_char(sinistre.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
        and sinistre.reservegrave = ''0''
        and sinistre.idvictime =  victime.id
        and sinistre.idevenement = evenement.id
        and sinistre.id = etat.idsinistre
        and etat.codetat = prm_etat.code
        order by libelle_etat'
        where id=3;