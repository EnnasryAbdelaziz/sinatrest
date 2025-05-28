--Entete
insert into SIN_ENTETE(id, sous_titre) values(4, null);
 
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(4, 'LISTE DES CONTROLES DES REGLEMENTS AUXILIAIRES', 4);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(4, '04', 'Liste des controles des reglements auxiliaires',
                'select reglement.numeroquittance as quittance, 
                   reglement.codemandataire as code_mandataire, reglement.nommandataire as nom_mandataire,
                   reglement.codeauxiliaire as code_auxiliaire, reglement.nomauxiliaire as nom_auxiliaire,
                   reglement.reference as ref_auxiliaire,
                   reglement.referenceauxiliaire as reference_auxiliaire,
                   case length(sinistre.numerosinistre)
                    when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                    || '' '' || substr(sinistre.numerosinistre, 12) 
                    else sinistre.numerosinistre
                    end numerosinistre, 
                   sinistre.numerograve as numerograve,
                   detail_reglement.codeprestation as rubrique, detail_reglement.montant as montant_regle, 
                   detail_reglement.montantrejete as montant_rejete,
                   (case  etat_anc.codetat
                        when ''0'' then ''E''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end) etat_anci , 
                     (case  etat_nov.codetat
                        when ''0'' then ''E''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end) etat_nouv,
                   mouvement.reservegraveold as reservegraveold, mouvement.reservegrave as reservegrave,
                   mouvement.reserveordold as reserveordold, mouvement.reserveord as reserveord,
                   reglement.codeusercreateur as redacteur
                   
                    from sin_reglement reglement, sin_sinistre sinistre, sin_detail_reglement detail_reglement,
                         sin_mouvement mouvement, sin_etat_sinistre etat_nov, sin_etat_sinistre etat_anc
                    where reglement.datecreation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                    and reglement.typereglement = ''2''
                    and reglement.etatreglement = ''1''
                    and reglement.idsinistre = sinistre.id
                    and reglement.id = detail_reglement.idreglement
                    and mouvement.id in (select max(mouvt.id) from sin_mouvement mouvt where mouvt.idsinistre = sinistre.id) 
                    and etat_nov.idetatsinistre in (select max(etat_sinistre.idetatsinistre) from sin_etat_sinistre etat_sinistre where etat_sinistre.idsinistre = sinistre.id)
                    and etat_anc.idetatsinistre in (select max(etat_sinistre.idetatsinistre) from sin_etat_sinistre etat_sinistre where etat_sinistre.idsinistre = sinistre.id and etat_sinistre.idetatsinistre < etat_nov.idetatsinistre)
                    order by  nom_auxiliaire'
                               
                                       ,1, 4);
     
 -- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(41, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(42, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 4);


-- Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(43, 'CHAMP', 'quittance', 'java.lang.String', 'N° quittance', '', 'Center', 110, 1, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(44, 'CHAMP', 'code_mandataire', 'java.lang.String', 'Mand.', '', 'Center', 70, 2, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(544, 'CHAMP', 'nom_mandataire', 'java.lang.String', 'Nom Mandataire', '', 'Left', 200, 3, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(45, 'CHAMP', 'code_auxiliaire', 'java.lang.String', 'Auxil.', 'dd/MM/yyyy', 'Center', 70, 4, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(46, 'CHAMP', 'nom_auxiliaire', 'java.lang.String', 'Nom Auxiliaire', '', 'Left', 200, 5, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(543, 'CHAMP', 'reference_auxiliaire', 'java.lang.String', 'Réf. Auxil', '', 'Center', 60, 6, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(47, 'CHAMP', 'numerosinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 7, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(48, 'CHAMP', 'numerograve', 'java.lang.String', 'Grv.', '', 'Center', 34, 8, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(49, 'CHAMP', 'rubrique', 'java.lang.String', 'Rub.', '', 'Center', 30, 9, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(50, 'CHAMP', 'montant_regle', 'java.lang.Double', 'Mnt Réglé', '#,##0.00', 'Right', 75, 10, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(52, 'CHAMP', 'etat_anci', 'java.lang.String', 'Anc.E', '', 'Center', 35, 12, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(53, 'CHAMP', 'etat_nouv', 'java.lang.String', 'Nov.E', '', 'Center', 35, 13, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(54, 'CHAMP', 'reservegraveold', 'java.lang.Double', 'Anc.Rsv.Grv', '#,##0.00', 'Right', 75, 14, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(55, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Nov.Rsv.Grv', '#,##0.00', 'Right', 75, 15, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(56, 'CHAMP', 'reserveordold', 'java.lang.Double', 'Anc.Rsv.Ord', '#,##0.00', 'Right', 75, 16, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(57, 'CHAMP', 'reserveord', 'java.lang.Double', 'Nov.Rsv.Ord', '#,##0.00', 'Right', 75, 17, 4);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(58, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 80, 18, 4);


--03/03/2014
update SIN_RAPPORT_ELEMENT rapport_element
set rapport_element.libelle = 'N° quittance' 
where id=43;


