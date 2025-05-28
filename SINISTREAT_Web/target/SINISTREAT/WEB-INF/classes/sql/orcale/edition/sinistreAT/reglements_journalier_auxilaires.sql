-- Entete
insert into SIN_ENTETE(id, sous_titre) values(30, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(39, 'Etat journalier des réglements auxilaires', 30);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(30, '30', 'Etat journalier des réglements auxilaires',
        'select
               case length(sinistre.numerosinistre)
                    when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                    || '' '' || substr(sinistre.numerosinistre, 12) 
                    else sinistre.numerosinistre
               end numero_sinistre,
               reglement.numeroquittance as numero_quittance,
               reglement.referenceAuxiliaire as reference_auxiliaire,
               reglement.codeAuxiliaire as code_auxiliaire,
               reglement.nomAuxiliaire as nom_auxiliaire,
               reglement.numeroMission as numero_mission,
               reglement.dateNote as date_dote,
               reglement.montant as montant,
               '' '' || reglement.codeusercreateur as utilisateur
               
        from sin_reglement reglement, sin_sinistre sinistre
        where to_char(reglement.datecreation, ''dd/MM/yyyy'') = $P{dateJournee}
        and reglement.idsinistre = sinistre.id
        and reglement.typereglement = ''2'''
        ,1, 30);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(322, 'PARAMETRE', 'dateJournee', 'java.lang.String', 'JOURNEE DU : ', 1, 30);


--Champs
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(323, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 190, 1, 30);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(324, 'CHAMP', 'numero_quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 120, 2, 30);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(325, 'CHAMP', 'reference_auxiliaire', 'java.lang.String', 'Référence Auxi.', '', 'Center', 100, 3, 30);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(326, 'CHAMP', 'code_auxiliaire', 'java.lang.String', 'Code Auxi', '', 'Center', 80, 4, 30);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(327, 'CHAMP', 'nom_auxiliaire', 'java.lang.String', 'Nom Auxilaire', '', 'Left', 300, 5, 30);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(328, 'CHAMP', 'numero_mission', 'java.lang.String', 'N° Mission', '', 'Center', 100, 6, 30);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(329, 'CHAMP', 'date_dote', 'java.sql.Timestamp', 'Date Note', 'dd/MM/yyyy', 'Center', 80, 7, 30);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(330, 'CHAMP', 'montant', 'java.lang.Double', 'Montant', '#,##0.00', 'Right', 120, 8, 30);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(331, 'CHAMP', 'utilisateur', 'java.lang.String', 'Utilisateur', '', 'Left', 100, 9, 30);


