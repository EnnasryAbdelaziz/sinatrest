-- Entete
insert into SIN_ENTETE(id, sous_titre) values(28, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(37, 'Etat journalier des réglements directs', 28);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(28, '28', 'Etat journalier des réglements directs',
        'select
               case length(sinistre.numerosinistre)
                    when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                    || '' '' || substr(sinistre.numerosinistre, 12) 
                    else sinistre.numerosinistre
               end numero_sinistre,
               reglement.numeroquittance as numero_quittance,
               reglement.nombeneficiaire as nom_beneficiaire,
               type_beneficiaire.libelle as type_beneficiaire,
               reglement.codechefGreffier as code_chefGreffier,
               reglement.nomchefGreffier as nom_chefGreffier,
               reglement.codeintermediaire as code_intermediaire,
               reglement.nomintermediaire as nom_intermediaire,
               reglement.codebarreau as code_barreau,
               reglement.nombarreau as nom_barreau,
               reglement.montant as montant,
               reglement.codeusercreateur as utilisateur
               
        from sin_reglement reglement, sin_sinistre sinistre, 
             prm_type_beneficiaire type_beneficiaire
        where to_char(reglement.datecreation, ''dd/MM/yyyy'') = $P{dateJournee}
        and reglement.idsinistre = sinistre.id
        and reglement.typebeneficiaire = type_beneficiaire.code
        and reglement.typereglement = ''1'''
        ,1, 28);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(301, 'PARAMETRE', 'dateJournee', 'java.lang.String', 'JOURNEE DU : ', 1, 28);


--Champs
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(302, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 1, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(303, 'CHAMP', 'numero_quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 106, 2, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(304, 'CHAMP', 'nom_beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 400, 3, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(305, 'CHAMP', 'type_beneficiaire', 'java.lang.String', 'Type Bénéf.', '', 'Center', 80, 4, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(306, 'CHAMP', 'code_chefGreffier', 'java.lang.String', 'Code Chef Gref.', '', 'Center', 100, 5, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(307, 'CHAMP', 'nom_chefGreffier', 'java.lang.String', 'Chef Greffier.', '', 'Left', 300, 6, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(308, 'CHAMP', 'code_intermediaire', 'java.lang.String', 'Code Inter.', '', 'Center', 80, 7, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(309, 'CHAMP', 'nom_intermediaire', 'java.lang.String', 'Inetermediaire', '', 'Left', 200, 8, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(310, 'CHAMP', 'montant', 'java.lang.Double', 'Montant', '#,##0.00', 'Right', 75, 9, 28);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(311, 'CHAMP', 'utilisateur', 'java.lang.String', 'Utilisateur', '', 'Left', 80, 10, 28);
