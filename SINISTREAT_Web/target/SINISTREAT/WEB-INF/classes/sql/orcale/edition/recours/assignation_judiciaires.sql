-- Entete
insert into SIN_ENTETE(id, sous_titre) values(56, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(70, 'Etat des assignation judicaire par période', 56);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(56, '56', 'Etat des assignation judicaire par période',
        'select
            (select case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end from sin_sinistre sinistre where sinistre.id = recours.idsinistre) as numero_sinistre,
            (select evenement.dateaccident 
                from sin_sinistre sinistre, sin_evenement evenement 
                where sinistre.id = recours.idsinistre
                and sinistre.idevenement = evenement.id) as date_at,
                (select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
                recours.mntdeboursloi as montant_debours_loi,
                recours_proc.nomavocatconseil as avocat_conseil,
                recours_proc.dateassignation as date_assignation,
                (select pronostic.libelle from prm_pronostic_rc pronostic where pronostic.code=recours.pronosticrc) as part_resp,
                recours_audience.montanjuge as montant_juge,
                (select '' '' || designation from syn_tribunal tribunal where tribunal.id = recours_proc.idjuridiction) as tribunal
                
        from sin_recours recours, sin_recours_proc recours_proc left outer join sin_recours_audience recours_audience on recours_audience.idprocedurejudiciaire = recours_proc.id
        where recours_proc.dateassignation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
        and (recours_proc.codeavocatconseil = $P{codeAvocatConseil} or ''0'' = $P{byAvocatConseil}) 
        and recours_proc.idrecours = recours.id'
        ,1, 56);
      

-- Parametres rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(679, 'PARAMETRE', 'codeAvocatConseil', 'java.lang.String', 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(680, 'PARAMETRE', 'byAvocatConseil', 'java.lang.String', 56);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(681, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(682, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 56);

--Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(683, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 1, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(684, 'CHAMP', 'date_at', 'java.sql.Timestamp', 'Date AT.', 'dd/MM/yyyy', 'Center', 80, 2, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(685, 'CHAMP', 'societe_adv', 'java.lang.String', 'Société Adverse', '', 'Left', 200, 3, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(686, 'CHAMP', 'montant_debours_loi', 'java.lang.Double', 'Mnt. Debrs. Loi', '#,##0.00', 'Right', 80, 4, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(687, 'CHAMP', 'avocat_conseil', 'java.lang.String', 'Avocat Conseil', '', 'Left', 200, 5, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(688, 'CHAMP', 'date_assignation', 'java.sql.Timestamp', 'Date Assign.', 'dd/MM/yyyy', 'Center', 80, 6, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(689, 'CHAMP', 'part_resp', 'java.lang.String', 'Part. Resp.', '', 'Center', 75, 7, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(690, 'CHAMP', 'montant_juge', 'java.lang.Double', 'Mnt. jJugé', '#,##0.00', 'Right', 90, 8, 56);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(691, 'CHAMP', 'tribunal', 'java.lang.String', 'Tribunal', '', 'Left', 250, 9, 56);


--03/03/2014
update SIN_RAPPORT_ELEMENT
set width=200
where id=683;

update SIN_RAPPORT_ELEMENT
set width=80
where id=684;

update sin_rapport
set requete_sql='select
            (select case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end from sin_sinistre sinistre where sinistre.id = recours.idsinistre) as numero_sinistre,
            (select evenement.dateaccident 
                from sin_sinistre sinistre, sin_evenement evenement 
                where sinistre.id = recours.idsinistre
                and sinistre.idevenement = evenement.id) as date_at,
                (select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
                recours.mntdeboursloi as montant_debours_loi,
                recours_proc.nomavocatconseil as avocat_conseil,
                recours_proc.dateassignation as date_assignation,
                (select pronostic.libelle from prm_pronostic_rc pronostic where pronostic.code=recours.pronosticrc) as part_resp,
                recours_audience.montanjuge as montant_juge,
                (select '' '' || designation from syn_tribunal tribunal where tribunal.id = recours_proc.idjuridiction) as tribunal
                
        from sin_recours recours, sin_recours_proc recours_proc left outer join sin_recours_audience recours_audience on recours_audience.idprocedurejudiciaire = recours_proc.id
        where recours_proc.dateassignation between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
        and (recours_proc.codeavocatconseil = $P{codeAvocatConseil} or ''0'' = $P{byAvocatConseil}) 
        and recours_proc.idrecours = recours.id'
where id=56;