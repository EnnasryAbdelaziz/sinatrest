--Entete
insert into SIN_ENTETE(id, sous_titre) values(36, null);
 
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(47, 'LISTE REGLEMENTS FRAIS JUDICIAIRES', 36);

-- Recap generale
insert into SIN_RECAP(id, type_fille, description) values(15, 'RECAP_GENERALE', 'Summary Etat Liste reglements frais judiciaires');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(36, '36', 'Liste reglements frais judiciaires',
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
        and reglement.idsinistre = sinistre.id
        and reglement.id = detail_reglement.idreglement
        and detail_reglement.codeprestation = ''26''
        and max_mvt.idsinistre = sinistre.id
        and max_mvt.id_max_mvt = mouvement.id 
        and etat_sin.idsinistre = sinistre.id
        and etat_sin.idnouvetatsinistre = etat_nov.idetatsinistre
        and etat_sin.idancetatsinistre = etat_anc.idetatsinistre
                                       
        order by quittance'
                               
                                       ,1, 36, 15);
 
 -- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(400, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(401, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 36);

-- Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(402, 'CHAMP', 'quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 106, 1, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(403, 'CHAMP', 'date_reglement', 'java.sql.Timestamp', 'Date Régl.', 'dd/MM/yyyy', 'Center', 70, 2, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(404, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 145, 3, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(405, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 34, 4, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(406, 'CHAMP', 'nom_beneficiaire', 'java.lang.String', 'Nom Bénéficiaire', '', 'Left', 400, 5, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(407, 'CHAMP', 'montant', 'java.lang.Double', 'Montant', '#,##0.00', 'Right', 75, 6, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(408, 'CHAMP', 'rubrique', 'java.lang.String', 'Rub.', '', 'Center', 30, 7, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(409, 'CHAMP', 'etat_anci', 'java.lang.String', 'Anc.E', '', 'Center', 35, 8, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(410, 'CHAMP', 'etat_nouv', 'java.lang.String', 'Nov.E', '', 'Center', 35, 9, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(411, 'CHAMP', 'reservegraveold', 'java.lang.Double', 'Anc.Rsv.Grv', '#,##0.00', 'Right', 78,10, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(412, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Nov.Rsv.Grv', '#,##0.00', 'Right', 78, 11, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(413, 'CHAMP', 'reserveordold', 'java.lang.Double', 'Anc.Rsv.Ord', '#,##0.00', 'Right', 78, 12, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(414, 'CHAMP', 'reserveord', 'java.lang.Double', 'Nov.Rsv.Ord', '#,##0.00', 'Right', 78, 13, 36);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(415, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 80, 14, 36);

-- Recap generale             
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (416, 'VARIABLE', 'nbreQuittances', 'java.lang.Integer', 'Nombre Total des Quittances : ', 'Left', 'Count', '$F{quittance}', 1, 15);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (417, 'VARIABLE', 'totalMontantQuittance', 'java.lang.Double', 'Total Montant des Quittances : ', '#,##0.00', 'Right', 'Sum', '$F{montant}', 2, 15);                               



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
        and reglement.idsinistre = sinistre.id
        and reglement.id = detail_reglement.idreglement
        and detail_reglement.codeprestation = ''26''
        and max_mvt.idsinistre = sinistre.id
        and max_mvt.id_max_mvt = mouvement.id 
        and etat_sin.idsinistre = sinistre.id
        and etat_sin.idnouvetatsinistre = etat_nov.idetatsinistre
        and etat_sin.idancetatsinistre = etat_anc.idetatsinistre
                                       
        order by quittance'
        where id=36;