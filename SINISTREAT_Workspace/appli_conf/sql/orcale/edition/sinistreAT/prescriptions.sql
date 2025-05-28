-- Entete
insert into SIN_ENTETE(id, sous_titre) values(58, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(72, 'Etat des prescriptions', 58);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(58, '58', 'Etat des prescriptions',
        'select
            case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end  numero_sinistre, 
            evenement.dateaccident as date_accident,
            (select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as compagnie_adv,
            recours.numerorecours as numero_recours,
            nvl(sinistre.reserveordinaire, 0) + nvl(sinistre.reservegrave, 0) + nvl(sinistre.reserveprothese, 0) + nvl(sinistre.cumulereglexercice, 0) as cout_dossier,
            null as observation
        from sin_recours recours, sin_sinistre sinistre, sin_evenement evenement
        where sinistre.id = recours.idsinistre
        and sinistre.idevenement = evenement.id
        and recours.numerorecours is not null
        and to_number(to_char(evenement.dateaccident, ''yyyy'')) < to_number(to_char(sysdate, ''yyyy'')) - 5'
        ,1, 58);
      
--Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(704, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 1, 58);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(705, 'CHAMP', 'date_accident', 'java.sql.Timestamp', 'Date Accident', 'dd/MM/yyyy', 'Center', 120, 2, 58);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(706, 'CHAMP', 'compagnie_adv', 'java.lang.String', 'Compagnie Adverse', '', 'Left', 250, 3, 58);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(707, 'CHAMP', 'numero_recours', 'java.lang.String', 'N° Recours', '', 'Center', 100, 4, 58);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(708, 'CHAMP', 'cout_dossier', 'java.lang.Double', 'Coût Dossier', '#,##0.00', 'Right', 120, 5, 58);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(709, 'CHAMP', 'observation', 'java.lang.String', 'Observation', '', 'Left', 250, 6, 58);


--24/03/2014 QC 649
update SIN_RAPPORT
set requete_sql='select
            case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end  numero_sinistre, 
            evenement.dateaccident as date_accident,
            (select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as compagnie_adv,
            recours.numerorecours as numero_recours,
            nvl(sinistre.reserveordinaire, 0) + nvl(sinistre.reservegrave, 0) + nvl(sinistre.reserveprothese, 0) + nvl(sinistre.cumulereglexercice, 0) as cout_dossier,
            null as observation
        from sin_recours recours, sin_sinistre sinistre, sin_evenement evenement
        where sinistre.id = recours.idsinistre
        and sinistre.idevenement = evenement.id
        and recours.numerorecours is not null
        and to_number(to_char(evenement.dateaccident, ''yyyy'')) < to_number(to_char(sysdate, ''yyyy'')) - 5'
where id=58;

update SIN_RAPPORT_ELEMENT
set width=200
where id=704;
