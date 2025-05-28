--Entete
insert into SIN_ENTETE(id, sous_titre) values(38, null);
 
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(49, 'LISTE DES CONTROLES DES REGLEMENTS AUXILIAIRES', 38);
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(50, 'redacteur', '1', 38);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(38, '38', 'Liste des controles des reglements auxiliaires par redacteur',
                'select reglement.numeroquittance as quittance, 
                    reglement.codemandataire as code_mandataire, reglement.nommandataire as nom_mandataire,
                   reglement.codeauxiliaire as code_auxiliaire, reglement.nomauxiliaire as nom_auxiliaire,
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
                         sin_mouvement mouvement, sin_etat_sinistre etat_nov, sin_etat_sinistre etat_anc                    where reglement.datecreation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                    and reglement.typereglement = ''2''
                    and reglement.etatreglement = ''1''
                    and reglement.codeusercreateur = $P{codeRedacteur}
                    and reglement.idsinistre = sinistre.id
                    and reglement.id = detail_reglement.idreglement
                    and reglement.id = detail_reglement.idreglement
                    and mouvement.id in (select max(mouvt.id) from sin_mouvement mouvt where mouvt.idsinistre = sinistre.id) 
                    and etat_nov.idetatsinistre in (select max(etat_sinistre.idetatsinistre) from sin_etat_sinistre etat_sinistre where etat_sinistre.idsinistre = sinistre.id)
                    and etat_anc.idetatsinistre in (select max(etat_sinistre.idetatsinistre) from sin_etat_sinistre etat_sinistre where etat_sinistre.idsinistre = sinistre.id and etat_sinistre.idetatsinistre < etat_nov.idetatsinistre)
                    order by  nom_auxiliaire'
                               
                                       ,1, 38);
 
 -- Parametres de rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(436, 'PARAMETRE', 'codeRedacteur', 'java.lang.String', 38);
    
 -- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(437, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(438, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 38);


-- Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(439, 'CHAMP', 'quittance', 'java.lang.String', 'N° quittance', '', 'Center', 106, 1, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(440, 'CHAMP', 'code_mandataire', 'java.lang.String', 'Mand.', '', 'Center', 70, 2, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(441, 'CHAMP', 'nom_mandataire', 'java.lang.String', 'Nom Mandataire', '', 'Left', 200, 3, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(442, 'CHAMP', 'reference_auxiliaire', 'java.lang.String', 'Réf. Auxil', '', 'Center', 60, 4, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(443, 'CHAMP', 'code_auxiliaire', 'java.lang.String', 'Auxil.', '', 'Center', 70, 5, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(444, 'CHAMP', 'nom_auxiliaire', 'java.lang.String', 'Nom Auxiliaire', '', 'Left', 200, 6, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(445, 'CHAMP', 'numerosinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 7, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(446, 'CHAMP', 'numerograve', 'java.lang.String', 'Grv.', '', 'Center', 34, 8, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(447, 'CHAMP', 'rubrique', 'java.lang.String', 'Rub', '', 'Center', 80, 9, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(448, 'CHAMP', 'montant_regle', 'java.lang.Double', 'Mnt Réglé', '#,##0.00', 'Right', 75, 10, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(449, 'CHAMP', 'etat_anci', 'java.lang.String', 'Anc.E', '', 'Center', 35, 11, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(450, 'CHAMP', 'etat_nouv', 'java.lang.String', 'Nov.E', '', 'Center', 35, 12, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(451, 'CHAMP', 'reservegraveold', 'java.lang.Double', 'Anc.Rsv.Grv', '#,##0.00', 'Right', 78, 13, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(452, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Nov.Rsv.Grv', '#,##0.00', 'Right', 78, 14, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(453, 'CHAMP', 'reserveordold', 'java.lang.Double', 'Anc.Rsv.Ord', '#,##0.00', 'Right', 78, 15, 38);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(542, 'CHAMP', 'reserveord', 'java.lang.Double', 'Nov.Rsv.Ord', '#,##0.00', 'Right', 78, 16, 38);




--03/03/2014
update SIN_RAPPORT_ELEMENT rapport_element
set rapport_element.libelle = 'N° quittance' 
where id=439;