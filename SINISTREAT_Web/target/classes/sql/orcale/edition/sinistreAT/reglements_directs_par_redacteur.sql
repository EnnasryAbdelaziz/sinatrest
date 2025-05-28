--Entete
insert into SIN_ENTETE(id, sous_titre) values(34, null);
 
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(44, 'LISTE DE CONTROLE DES REGLEMENTS DIRECTS', 34);
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(45, 'redacteur', '1', 34);
-- Recap generale
insert into SIN_RECAP(id, type_fille, description) values(13, 'RECAP_GENERALE', 'Summary Etat Liste de controle des reglements directs par rédacteur');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(34, '34', 'Liste des controles des reglements directs par rédacteur',
                'select reglement.numeroquittance as quittance, reglement.datereglement as date_reglement,  
        case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                       || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
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
        mouvement.reservegraveold as reservegraveold, 
        mouvement.reservegrave as reservegrave,
       mouvement.reserveordold as reserveordold, 
       mouvement.reserveord as reserveord,
       reglement.codeusercreateur as redacteur 
        

    
        from sin_sinistre sinistre, sin_detail_reglement detail_reglement, sin_etat_sinistre etat_nov, sin_etat_sinistre etat_anc,  
            sin_reglement reglement left outer join sin_mouvement mouvement on reglement.id = mouvement.idreglement
            
        where to_date(to_char(reglement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
        and reglement.typereglement = ''1''
        and reglement.etatreglement = ''1''
        and reglement.codeusercreateur = $P{codeRedacteur}
        and reglement.idsinistre = sinistre.id
        and reglement.id = detail_reglement.idreglement
        and etat_nov.idetatsinistre in (select max(etat_sinistre.idetatsinistre) from sin_etat_sinistre etat_sinistre where etat_sinistre.idsinistre = sinistre.id)
        and etat_anc.idetatsinistre in (select max(etat_sinistre.idetatsinistre) from sin_etat_sinistre etat_sinistre where etat_sinistre.idsinistre = sinistre.id and etat_sinistre.idetatsinistre < etat_nov.idetatsinistre)                                                                 
        order by quittance'
                               
                                       ,1, 34, 13);
 -- Parametres rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(371, 'PARAMETRE', 'codeRedacteur', 'java.lang.String', 34);
 
 -- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(372, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(373, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 34);

-- Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(374, 'CHAMP', 'quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 106, 1, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(375, 'CHAMP', 'date_reglement', 'java.sql.Timestamp', 'Date Régl.', 'dd/MM/yyyy', 'Center', 65, 2, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(376, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 3, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(377, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 34, 4, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(378, 'CHAMP', 'nom_beneficiaire', 'java.lang.String', 'Nom Bénéficiaire', '', 'Left', 400, 5, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(379, 'CHAMP', 'montant', 'java.lang.Double', 'Montant', '#,##0.00', 'Right', 75, 6, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(380, 'CHAMP', 'rubrique', 'java.lang.String', 'Rub.', '', 'Center', 30, 7, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(381, 'CHAMP', 'etat_anci', 'java.lang.String', 'Anc.E', '', 'Center', 35, 8, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(382, 'CHAMP', 'etat_nouv', 'java.lang.String', 'Nov.E', '', 'Center', 35, 9, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(383, 'CHAMP', 'reservegraveold', 'java.lang.Double', 'Anc.Rsv.Grv', '#,##0.00', 'Right', 78,10, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(384, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Nov.Rsv.Grv', '#,##0.00', 'Right', 78, 11, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(385, 'CHAMP', 'reserveordold', 'java.lang.Double', 'Anc.Rsv.Ord', '#,##0.00', 'Right', 78, 12, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(386, 'CHAMP', 'reserveord', 'java.lang.Double', 'Nov.Rsv.Ord', '#,##0.00', 'Right', 78, 13, 34);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(508, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 80, 14, 34);

-- Recap generale             
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (387, 'VARIABLE', 'nbreQuittances', 'java.lang.Integer', 'Nombre Total des Quittances : ', 'Left', 'Count', '$F{quittance}', 1, 13);                               

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (388, 'VARIABLE', 'totalMontantQuittance', 'java.lang.Double', 'Total Montant des Quittances : ', '#,##0.00', 'Right', 'Sum', '$F{montant}', 2, 13);                               


