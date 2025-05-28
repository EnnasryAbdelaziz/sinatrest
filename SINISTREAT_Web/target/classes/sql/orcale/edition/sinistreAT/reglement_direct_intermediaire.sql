-- Entete
insert into SIN_ENTETE(id, sous_titre) values(5, 'Accident de Travail');

insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(5, 'Etat de contrôle des réglements directs intermédiaires', null, 5);
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(6, 'Chèque global hébdomadaire', null, 5);



insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(5, '05', 'Etat de contrôle des réglements directs intermédiaires, Chèque global hébdomadaire, Accidents de Travail',
        'select intermediaire.codintermediaire || '' ('' || intermediaire.codtypeinterm || '') '' || intermediaire.lblintermediaire as intermediaire, 
        nvl(reglement.numerobordereau, '' '') as bordereau, 
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
    
        where reglement.modereglement = $P{modeReglement}
        and reglement.idsinistre = sinistre.id
        and reglement.codeintermediaire = to_char(intermediaire.codintermediaire)
        and reglement.typeintermediaire = intermediaire.codtypeinterm
        and reglement.id = detail_reglement.idreglement
        and to_date(to_char(reglement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
        and reglement.typereglement = ''3''
        order by intermediaire, bordereau'
        ,1, 5);
        

-- Parametres de rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(664, 'PARAMETRE', 'modeReglement', 'java.lang.String', 5);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(59, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(60, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 5);

-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, ordre_affichage, id_sin_rapport)
values(2, 'grIntermediaire', '$F{intermediaire}', 1, 5);

insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(3, 'grBordereau', '$F{bordereau}', '1', 2, 5);

-- Champs group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(61, 'CHAMP', 'intermediaire', 'java.lang.String', 'Intermédiaire : ', 1, 3);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(62, 'CHAMP', 'bordereau', 'java.lang.String', 'Bordereau : ', 2, 3);


--Champs
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(63, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 1, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(64, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 34, 2, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(65, 'CHAMP', 'numero_police', 'java.lang.String', 'N° Police', '', 'Center', 130, 3, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(66, 'CHAMP', 'nom_beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 200, 4, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(67, 'CHAMP', 'date_reglement', 'java.sql.Timestamp', 'Date Régl.', 'dd/MM/yyyy', 'Center', 80, 5, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(68, 'CHAMP', 'numero_quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 106, 6, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(69, 'CHAMP', 'rubrique', 'java.lang.String', 'Rub', '', 'Center', 30, 7, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(70, 'CHAMP', 'montant_regle', 'java.lang.Double', 'Mnt Réglé', '#,##0.00', 'Right', 75, 8, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(71, 'CHAMP', 'montant_rejete', 'java.lang.Double', 'Mnt Rejeté', '#,##0.00', 'Right', 75, 9, 5);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(72, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 80, 10, 5);


-- Recap group by

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (73, 'VARIABLE', 'sumReglEffectuesParBord', 'java.lang.Double', 'Réglement Effectués : ', '#,##0.00', 'Right', 'Sum', '$F{montant_regle}', 1, 3);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (74, 'VARIABLE', 'sumReglRejetesParBord', 'java.lang.Double', 'Réglement Rejetés : ',  '#,##0.00', 'Right', 'Sum', '$F{montant_rejete}', 2, 3);  

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (75, 'VARIABLE', 'totalBordereau', 'java.lang.Double', 'Total Bordereau : ',  '#,##0.00', 'Right', 'Nothing', 'new java.lang.Double(  ($V{sumReglEffectuesParBord}.doubleValue()) -($V{sumReglRejetesParBord}.doubleValue()) )', 3, 3);  


insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (76, 'VARIABLE', 'sumReglEffectuesParInter', 'java.lang.Double', 'Réglement Effectués : ',  '#,##0.00', 'Right', 'Sum', '$F{montant_regle}', 1, 2);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (77, 'VARIABLE', 'sumReglRejetesParInter', 'java.lang.Double', 'Réglement Rejetés : ',  '#,##0.00', 'Right', 'Sum', '$F{montant_rejete}', 2, 2);  
                         
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (78, 'VARIABLE', 'totalIntermediaire', 'java.lang.Double', 'Total Intermediaire : ',  '#,##0.00', 'Right', 'Nothing', 'new java.lang.Double(  ($V{sumReglEffectuesParInter}.doubleValue()) -($V{sumReglRejetesParInter}.doubleValue()) )', 3, 2);  

--25/03/2014
update SIN_RAPPORT_ELEMENT
set width=130
where id=65;

update SIN_RAPPORT_ELEMENT
set width=80
where id=67;

update sin_rapport
set requete_sql='select intermediaire.codintermediaire || '' ('' || intermediaire.codtypeinterm || '') '' || intermediaire.lblintermediaire as intermediaire, 
        nvl(reglement.numerobordereau, '' '') as bordereau, 
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
    
        where reglement.modereglement = $P{modeReglement}
        and reglement.idsinistre = sinistre.id
        and reglement.codeintermediaire = to_char(intermediaire.codintermediaire)
        and reglement.typeintermediaire = intermediaire.codtypeinterm
        and reglement.id = detail_reglement.idreglement
        and to_date(to_char(reglement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
        and reglement.typereglement = ''3''
        order by intermediaire, bordereau'
where id=5;


--QC 628
update SIN_ENTETE set sous_titre = null where id=5;
delete from SIN_LIGNE_TITRE where id in (5, 6);

insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(5, 'titreLigne1', '1', 5);
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(6, 'titreLigne2', '1', 5);
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(78, 'titreLigne3', '1', 5);