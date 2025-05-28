-- Entete
insert into SIN_ENTETE(id, sous_titre) values(1, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(1, 'ETAT DES DOSSIERS OUVERTS', 1);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(1, '01', 'Etat des dossiers ouverts',
        'select 
                
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
        from sin_sinistre sinistre, sin_victime victime, sin_evenement evenement, sin_etat_sinistre etat
        where sinistre.idvictime =  victime.id
        and sinistre.idevenement = evenement.id
        and sinistre.id = etat.idsinistre
        and etat.codetat = ''1''
        and to_date(to_char(sinistre.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')'
        ,1, 1);
      

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(1, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(2, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 1);

--Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(3, 'CHAMP', 'numeropolice', 'java.lang.String', 'N° Police', '', 'Center', 120, 1, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(4, 'CHAMP', 'codeintermediaire', 'java.lang.String', 'Inter.', '', 'Center', 80, 2, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(5, 'CHAMP', 'numerosinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 160, 3, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(6, 'CHAMP', 'numerograve', 'java.lang.String', 'Grv.', '', 'Center', 34, 4, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(7, 'CHAMP', 'dateaccident', 'java.sql.Timestamp', 'Date Acci.', 'dd/MM/yyyy', 'Center', 80, 5, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(8, 'CHAMP', 'datedeclaration', 'java.sql.Timestamp', 'Date Décl.', 'dd/MM/yyyy', 'Center', 80, 6, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(9, 'CHAMP', 'reserveordinaire', 'java.lang.Double', 'Eval. Ord.', '#,##0.00', 'Right', 100, 7, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(10, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Eval. Grave', '#,##0.00', 'Right', 100, 8, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(11, 'CHAMP', 'nom_victime', 'java.lang.String', 'Nom Victime', '', 'Left', 200, 9, 1);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(12, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 120, 10, 1);



--28/02/2014
delete from SIN_RAPPORT_ELEMENT where id=5;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(5, 'CHAMP', 'numerosinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 3, 1);

delete from SIN_RAPPORT_ELEMENT where id=7;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(7, 'CHAMP', 'dateaccident', 'java.sql.Timestamp', 'Date Acci.', 'dd/MM/yyyy', 'Center', 80, 5, 1);

delete from SIN_RAPPORT_ELEMENT where id=8;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(8, 'CHAMP', 'datedeclaration', 'java.sql.Timestamp', 'Date Décl.', 'dd/MM/yyyy', 'Center', 80, 6, 1);

delete from SIN_RAPPORT_ELEMENT where id=6;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(6, 'CHAMP', 'numerograve', 'java.lang.String', 'Grv.', '', 'Center', 60, 4, 1);

delete from SIN_RAPPORT_ELEMENT where id=3;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(3, 'CHAMP', 'numeropolice', 'java.lang.String', 'N° Police', '', 'Center', 140, 1, 1);

delete from SIN_RAPPORT_ELEMENT where id=12;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(12, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 120, 10, 1);

 