-- Entete
insert into SIN_ENTETE(id, sous_titre) values(29, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(38, 'Etat journalier des réglements intermediaires', 29);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(29, '29', 'Etat journalier des réglements intermediaires',
        'select
               case length(sinistre.numerosinistre)
                    when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                    || '' '' || substr(sinistre.numerosinistre, 12) 
                    else sinistre.numerosinistre
               end numero_sinistre,
               reglement.numeroquittance as numero_quittance,
               reglement.nombeneficiaire as nom_beneficiaire,
               type_beneficiaire.libelle as type_beneficiaire,
               reglement.numeroBordereau as numero_Bordereau,
               reglement.dateBordereau as date_Bordereau,
               reglement.numCheque as num_Cheque,
               reglement.montant as montant,
               reglement.codeusercreateur as utilisateur
               
        from sin_reglement reglement, sin_sinistre sinistre, 
             prm_type_beneficiaire type_beneficiaire
        where to_char(reglement.datecreation, ''dd/MM/yyyy'') = $P{dateJournee}
        and reglement.idsinistre = sinistre.id
        and reglement.typebeneficiaire = type_beneficiaire.code
        and reglement.typereglement = ''3'''
        ,1, 29);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(312, 'PARAMETRE', 'dateJournee', 'java.lang.String', 'JOURNEE DU : ', 1, 29);


--Champs
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(313, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 1, 29);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(314, 'CHAMP', 'numero_quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 106, 2, 29);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(315, 'CHAMP', 'nom_beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 300, 3, 29);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(316, 'CHAMP', 'type_beneficiaire', 'java.lang.String', 'Type Bénéf.', '', 'Center', 100, 4, 29);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(317, 'CHAMP', 'numero_Bordereau', 'java.lang.String', 'N° Bordereau', '', 'Center', 100, 5, 29);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(318, 'CHAMP', 'date_Bordereau', 'java.sql.Timestamp', 'Date Bordereau', 'dd/MM/yyyy', 'Center', 100, 6, 29);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(319, 'CHAMP', 'num_Cheque', 'java.lang.String', 'N° Cheque', '', 'Center', 150, 7, 29);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(320, 'CHAMP', 'montant', 'java.lang.Double', 'Montant', '#,##0.00', 'Right', 75, 8, 29);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(321, 'CHAMP', 'utilisateur', 'java.lang.String', 'Utilisateur', '', 'Left', 80, 9, 29);

