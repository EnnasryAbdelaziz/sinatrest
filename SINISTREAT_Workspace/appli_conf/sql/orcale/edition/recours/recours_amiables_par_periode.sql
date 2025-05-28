-- Entete
insert into SIN_ENTETE(id, sous_titre) values(55, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(69, 'Etat des recours amiables', 55);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(55, '55', 'Etat des recours amiables',
        'select 
            (select case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end from sin_sinistre sinistre where sinistre.id = recours.idsinistre) as numero_sinistre,
            (select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
            recours.codecompagnieadverse as ref_societe_adv,
            recours.mntdeboursloi as montant_debours_loi,
            amiable.dateproposition as date_lettre_proposition,
            amiable.partageresponsabilite as partage_resp_adv,
            amiable.montantquitance as montant_quittance,
            amiable.datequitance as date_quittance,
            amiable.numerocheque as numero_cheque,
            null as date_cheque
        from sin_recours_amiable amiable, sin_recours recours
        where amiable.datequitance between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
        and (recours.codecompagnieadverse = $P{codeCompagnieAdv} or ''0'' = $P{byCompagnieAdv})
        and amiable.idrecours = recours.id'
        ,1, 55);
      

-- Parametres rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(677, 'PARAMETRE', 'codeCompagnieAdv', 'java.lang.String', 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(678, 'PARAMETRE', 'byCompagnieAdv', 'java.lang.String', 55);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(665, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(666, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 55);

--Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(667, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 1, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(668, 'CHAMP', 'societe_adv', 'java.lang.String', 'Société Adverse', '', 'Left', 300, 2, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(669, 'CHAMP', 'ref_societe_adv', 'java.lang.String', 'Ref. Cie Adv', '', 'Center', 80, 3, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(670, 'CHAMP', 'montant_debours_loi', 'java.lang.Double', 'Mnt. Debrs. Loi', '#,##0.00', 'Right', 80, 4, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(671, 'CHAMP', 'date_lettre_proposition', 'java.sql.Timestamp', 'Date Prop.', 'dd/MM/yyyy', 'Center', 80, 5, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(672, 'CHAMP', 'partage_resp_adv', 'java.lang.String', 'Partage Resp. ADV', '', 'Center', 100, 6, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(673, 'CHAMP', 'montant_quittance', 'java.lang.Double', 'Mnt. Qtc', '#,##0.00', 'Right', 80, 7, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(674, 'CHAMP', 'date_quittance', 'java.sql.Timestamp', 'Date Qtc', 'dd/MM/yyyy', 'Center', 80, 8, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(675, 'CHAMP', 'numero_cheque', 'java.lang.String', 'N° Chèque', '', 'Center', 100, 9, 55);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(676, 'CHAMP', 'date_cheque', 'java.sql.Timestamp', 'Date Chq.', 'dd/MM/yyyy', 'Center', 80, 10, 55);


--24/03/2014
update SIN_RAPPORT_ELEMENT
set width=200
where id=667;

update SIN_RAPPORT_ELEMENT
set width=80
where id in (671, 674, 676);

update SIN_RAPPORT_ELEMENT
set width=100
where id in (675);

update sin_rapport
set requete_sql='select 
            (select case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end from sin_sinistre sinistre where sinistre.id = recours.idsinistre) as numero_sinistre,
            (select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
            recours.codecompagnieadverse as ref_societe_adv,
            recours.mntdeboursloi as montant_debours_loi,
            amiable.dateproposition as date_lettre_proposition,
            amiable.partageresponsabilite as partage_resp_adv,
            amiable.montantquitance as montant_quittance,
            amiable.datequitance as date_quittance,
            amiable.numerocheque as numero_cheque,
            null as date_cheque
        from sin_recours_amiable amiable, sin_recours recours
        where amiable.datequitance between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
        and (recours.codecompagnieadverse = $P{codeCompagnieAdv} or ''0'' = $P{byCompagnieAdv})
        and amiable.idrecours = recours.id'
 where id=55;

