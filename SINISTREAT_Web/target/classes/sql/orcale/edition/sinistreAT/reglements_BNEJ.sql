--Entete
insert into SIN_ENTETE(id, sous_titre) values(37, null);
 
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(48, 'LISTE REGLEMENTS B.N.E.J', 37);

-- Recap generale
insert into SIN_RECAP(id, type_fille, description) values(16, 'RECAP_GENERALE', 'Summary Etat Liste reglements B.N.E.J');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(37, '37', 'Liste reglements B.N.E.J',
                'select reglement.numeroquittance as quittance, reglement.datereglement as date_reglement,  
        case length(sinistre.numerosinistre)
        when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
        || '' '' || substr(sinistre.numerosinistre, 12) 
        else sinistre.numerosinistre
        end numero_sinistre, 
       sinistre.numerograve as numero_grave, 
       reglement.nombeneficiaire as nom_beneficiaire, nvl(detail_reglement.montant, 0) as montant, 
       detail_reglement.codeprestation as rubrique,
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
            ,(select mouvt.idsinistre as idsinistre, max(mouvt.id) as id_max_mvt from sin_mouvement mouvt
               group by mouvt.idsinistre) max_mvt
            ,(select etat_sinistre.idsinistre as idsinistre, max(etat_sinistre.idetatsinistre) as idnouvetatsinistre, min(etat_sinistre.idetatsinistre) as idancetatsinistre 
              from sin_etat_sinistre etat_sinistre
              group by etat_sinistre.idsinistre) etat_sin
        where to_date(to_char(reglement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
        and reglement.typebeneficiaire = ''5''
        and reglement.idsinistre = sinistre.id
        and reglement.id = detail_reglement.idreglement
        and max_mvt.idsinistre = sinistre.id
        and max_mvt.id_max_mvt = mouvement.id 
        and etat_sin.idsinistre = sinistre.id
        and etat_sin.idnouvetatsinistre = etat_nov.idetatsinistre
        and etat_sin.idancetatsinistre = etat_anc.idetatsinistre
                                       
        order by quittance'
                               
                                       ,1, 37, 16);

 -- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(418, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(419, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 37);

-- Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(420, 'CHAMP', 'quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 106, 1, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(421, 'CHAMP', 'date_reglement', 'java.sql.Timestamp', 'Date Régl.', 'dd/MM/yyyy', 'Center', 80, 2, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(422, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 3, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(423, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 34, 4, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(424, 'CHAMP', 'nom_beneficiaire', 'java.lang.String', 'Nom Bénéficiaire', '', 'Left', 400, 5, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(425, 'CHAMP', 'montant', 'java.lang.Double', 'Montant', '#,##0.00', 'Right', 75, 6, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(426, 'CHAMP', 'rubrique', 'java.lang.String', 'Rub', '', 'Center', 30, 7, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(427, 'CHAMP', 'etat_anci', 'java.lang.String', 'Anc.E', '', 'Center', 35, 8, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(428, 'CHAMP', 'etat_nouv', 'java.lang.String', 'Nov.E', '', 'Center', 35, 9, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(429, 'CHAMP', 'reservegraveold', 'java.lang.Double', 'Anc.Rsv.Grv', '#,##0.00', 'Right', 75,10, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(430, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Nov.Rsv.Grv', '#,##0.00', 'Right', 75, 11, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(431, 'CHAMP', 'reserveordold', 'java.lang.Double', 'Anc.Rsv.Ord', '#,##0.00', 'Right', 75, 12, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(432, 'CHAMP', 'reserveord', 'java.lang.Double', 'Nov.Rsv.Ord', '#,##0.00', 'Right', 75, 13, 37);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(433, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 80, 14, 37);

-- Recap generale             
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (434, 'VARIABLE', 'nbreQuittances', 'java.lang.Integer', 'Nombre Total des Quittances : ', 'Left', 'Count', '$F{quittance}', 1, 16);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (435, 'VARIABLE', 'totalMontantQuittance', 'java.lang.Double', 'Total Montant des Quittances : ', '#,##0.00', 'Right', 'Sum', '$F{montant}', 2, 16);                               


--03/03/2014
update sin_rapport
set requete_sql='select reglement.numeroquittance as quittance, reglement.datereglement as date_reglement,  
        case length(sinistre.numerosinistre)
        when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
        || '' '' || substr(sinistre.numerosinistre, 12) 
        else sinistre.numerosinistre
        end numero_sinistre, 
       sinistre.numerograve as numero_grave, 
       reglement.nombeneficiaire as nom_beneficiaire, nvl(detail_reglement.montant, 0) as montant, 
       detail_reglement.codeprestation as rubrique,
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
            ,(select mouvt.idsinistre as idsinistre, max(mouvt.id) as id_max_mvt from sin_mouvement mouvt
               group by mouvt.idsinistre) max_mvt
            ,(select etat_sinistre.idsinistre as idsinistre, max(etat_sinistre.idetatsinistre) as idnouvetatsinistre, min(etat_sinistre.idetatsinistre) as idancetatsinistre 
              from sin_etat_sinistre etat_sinistre
              group by etat_sinistre.idsinistre) etat_sin
        where to_date(to_char(reglement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
        and reglement.typebeneficiaire = ''5''
        and reglement.idsinistre = sinistre.id
        and reglement.id = detail_reglement.idreglement
        and max_mvt.idsinistre = sinistre.id
        and max_mvt.id_max_mvt = mouvement.id 
        and etat_sin.idsinistre = sinistre.id
        and etat_sin.idnouvetatsinistre = etat_nov.idetatsinistre
        and etat_sin.idancetatsinistre = etat_anc.idetatsinistre
                                       
        order by quittance'
        where id=37;

update SIN_RAPPORT_ELEMENT
set width=80
where id= 421;