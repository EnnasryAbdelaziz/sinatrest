-- Entete
insert into SIN_ENTETE(id, sous_titre) values(39, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(51, 'LISTE DE CONTROLE  DES QUITTANCES EMISES', 39);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(39, '39', 'LISTE DE CONTROLE  DES QUITTANCES EMISES',
        'select intermediaire.codintermediaire || '' '' || intermediaire.lblintermediaire as intermediaire, reglement.numerobordereau as bordereau, 
        case length(sinistre.numerosinistre)
        when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
        || '' '' || substr(sinistre.numerosinistre, 12) 
        else sinistre.numerosinistre
        end numero_sinistre,
        sinistre.numerograve as numero_grave,
        
       case length(sinistre.numeropolice)
        when 15 then substr(sinistre.numeropolice, 1, 4) || '' '' || substr(sinistre.numeropolice, 5, 3) || '' '' || substr(sinistre.numeropolice, 8, 4)
        || '' '' || substr(sinistre.numeropolice, 12) 
        else sinistre.numeropolice
       end numero_police,
        
        reglement.nombeneficiaire as nom_beneficiaire, 
        reglement.datereglement as date_reglement, reglement.numeroquittance as numero_quittance,
        detail_reglement.codeprestation as rubrique, nvl(detail_reglement.montant, 0) as montant_regle, 
        nvl(detail_reglement.montantrejete, 0) as montant_rejete, reglement.codeusercreateur as redacteur
    
        from sin_reglement reglement, sin_sinistre sinistre, syn_intermediaire intermediaire,
            sin_detail_reglement detail_reglement 
    
        where reglement.idsinistre = sinistre.id
        and reglement.codeintermediaire = to_char(intermediaire.codintermediaire)
        and reglement.id = detail_reglement.idreglement
        and to_date(to_char(reglement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
        and reglement.etatreglement = ''2''
        order by intermediaire, bordereau'
        ,1, 39);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(454, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(455, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 39);

-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, ordre_affichage, id_sin_rapport)
values(9, 'grIntermediaire', '$F{intermediaire}', 1, 39);

insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(10, 'grBordereau', '$F{bordereau}', '1', 2, 39);

-- Champs group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(456, 'CHAMP', 'intermediaire', 'java.lang.String', 'Intermédiaire : ', 1, 10);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(457, 'CHAMP', 'bordereau', 'java.lang.String', 'Bordereau : ', 2, 10);


--Champs
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(458, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 1, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(459, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 34, 2, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(460, 'CHAMP', 'numero_police', 'java.lang.String', 'N° Police', '', 'Center', 106, 3, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(461, 'CHAMP', 'nom_beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 400, 4, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(462, 'CHAMP', 'date_reglement', 'java.sql.Timestamp', 'Date Régl.', 'dd/MM/yyyy', 'Center', 65, 5, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(463, 'CHAMP', 'numero_quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 106, 6, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(464, 'CHAMP', 'rubrique', 'java.lang.String', 'Rub', '', 'Center', 30, 7, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(465, 'CHAMP', 'montant_regle', 'java.lang.Double', 'Mnt Réglé', '#,##0.00', 'Right', 75, 8, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(466, 'CHAMP', 'montant_rejete', 'java.lang.Double', 'Mnt Rejeté', '#,##0.00', 'Right', 75, 9, 39);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(467, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 80, 10, 39);


-- Recap group by

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (468, 'VARIABLE', 'sumReglEffectuesParBord', 'java.lang.Double', 'Réglement Effectués : ', '#,##0.00', 'Right', 'Sum', '$F{montant_regle}', 1, 10);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (469, 'VARIABLE', 'sumReglRejetesParBord', 'java.lang.Double', 'Réglement Rejetés : ',  '#,##0.00', 'Right', 'Sum', '$F{montant_rejete}', 2, 10);  

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (470, 'VARIABLE', 'totalBordereau', 'java.lang.Double', 'Total Bordereau : ',  '#,##0.00', 'Right', 'Nothing', 'new java.lang.Double(  ($V{sumReglEffectuesParBord}.doubleValue()) -($V{sumReglRejetesParBord}.doubleValue()) )', 3, 10);  


insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (471, 'VARIABLE', 'sumReglEffectuesParInter', 'java.lang.Double', 'Réglement Effectués : ',  '#,##0.00', 'Right', 'Sum', '$F{montant_regle}', 1, 9);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (472, 'VARIABLE', 'sumReglRejetesParInter', 'java.lang.Double', 'Réglement Rejetés : ',  '#,##0.00', 'Right', 'Sum', '$F{montant_rejete}', 2, 9);  
                         
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (473, 'VARIABLE', 'totalIntermediaire', 'java.lang.Double', 'Total Intermediaire : ',  '#,##0.00', 'Right', 'Nothing', 'new java.lang.Double(  ($V{sumReglEffectuesParInter}.doubleValue()) -($V{sumReglRejetesParInter}.doubleValue()) )', 3, 9);  

