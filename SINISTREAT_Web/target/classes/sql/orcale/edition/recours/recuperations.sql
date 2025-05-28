-- Entete
insert into SIN_ENTETE(id, sous_titre) values(57, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(71, 'Etat des récupérations', 57);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(57, '57', 'Etat des récupérations',
        'select order_affichage, compagnie, 
case length(numero_sinistre)
when 21 then substr(numero_sinistre, 1, 3) || '' '' || substr(numero_sinistre, 4, 1) || '' '' || 
substr(numero_sinistre, 5, 4) || '' '' || substr(numero_sinistre, 9, 3)
|| '' '' || substr(numero_sinistre, 12, 4) || '' '' || substr(numero_sinistre, 16, 6)                         
else numero_sinistre
end numero_sinistre, societe_adv, avocat_conseil, montant_quittance, montant_cheque, date_recuperation, 
case type_recuperation when ''1'' then ''Totale'' else ''Partielle'' end type_recuperation, numero_cheque
from
(select 1 as order_affichage,
''RMA Watanya'' as compagnie,
sinistre.numerosinistre as numero_sinistre,
(select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
recours.nomavocatconseil as avocat_conseil,
(select amiable.montantquitance from sin_recours_amiable amiable where amiable.idrecours=recours.id) as montant_quittance,
recours.mntdeboursloi as montant_cheque,recours.daterecuperation as date_recuperation,recours.isrecuperationtotale as type_recuperation,
recours.numerocheque as numero_cheque
from sin_recours recours, sin_sinistre sinistre
where recours.typerecours = ''2''
and sinistre.id = recours.idsinistre
and to_number(to_char(sinistre.datecreation, ''yyyy'')) >= 2005
union
select 2 as order_affichage,
''RMA'' as compagnie,
sinistre.numerosinistre as numero_sinistre,
(select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
recours.nomavocatconseil as avocat_conseil,
(select amiable.montantquitance from sin_recours_amiable amiable where amiable.idrecours=recours.id) as montant_quittance,
recours.mntdeboursloi as montant_cheque,recours.daterecuperation as date_recuperation,recours.isrecuperationtotale as type_recuperation,recours.numerocheque as numero_cheque
from sin_recours recours, sin_sinistre sinistre
where recours.typerecours = ''2''
and sinistre.id = recours.idsinistre
and length(sinistre.numerosinistre) = 21
and substr(sinistre.numerosinistre, 5, 4) = ''9700''
and to_number(to_char(sinistre.datecreation, ''yyyy'')) <= 2004
union
select 3 as order_affichage,''Watanya'' as compagnie,
sinistre.numerosinistre as numero_sinistre,(select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
recours.nomavocatconseil as avocat_conseil,(select amiable.montantquitance from sin_recours_amiable amiable where amiable.idrecours=recours.id) as montant_quittance,
recours.mntdeboursloi as montant_cheque,recours.daterecuperation as date_recuperation,recours.isrecuperationtotale as type_recuperation,recours.numerocheque as numero_cheque
from sin_recours recours, sin_sinistre sinistre
where recours.typerecours = ''2''
and sinistre.id = recours.idsinistre
and length(sinistre.numerosinistre) = 21
and substr(sinistre.numerosinistre, 5, 4) not in (''9700'', ''9870'',''9880'') 
and to_number(to_char(sinistre.datecreation, ''yyyy'')) <= 2004
union
select 4 as order_affichage,''Alliance'' as compagnie,sinistre.numerosinistre as numero_sinistre,
(select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
recours.nomavocatconseil as avocat_conseil,(select amiable.montantquitance from sin_recours_amiable amiable where amiable.idrecours=recours.id) as montant_quittance,
recours.mntdeboursloi as montant_cheque,recours.daterecuperation as date_recuperation,recours.isrecuperationtotale as type_recuperation,recours.numerocheque as numero_cheque
from sin_recours recours, sin_sinistre sinistre
where recours.typerecours = ''2''
and sinistre.id = recours.idsinistre
and length(sinistre.numerosinistre) = 21
and substr(sinistre.numerosinistre, 5, 4) in (''9870'',''9880'') 
and to_number(to_char(sinistre.datecreation, ''yyyy'')) <= 2004
)
order by order_affichage'
        ,1, 57);
   
  
-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(15, 'grCompagnie', '$F{compagnie}', '1', 1, 57);

-- Champs group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(692, 'CHAMP', 'compagnie', 'java.lang.String', ' ', 1, 15);


-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(693, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 57);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(694, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 57);

--Colonnes
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(695, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 200, 1, 57);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(696, 'CHAMP', 'societe_adv', 'java.lang.String', 'Compagnie Adverse', '', 'Left', 200, 2, 57);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(697, 'CHAMP', 'avocat_conseil', 'java.lang.String', 'Avocat Conseil', '', 'Left', 200, 3, 57);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(698, 'CHAMP', 'montant_quittance', 'java.lang.Double', 'Mnt. Qtc', '#,##0.00', 'Right', 80, 4, 57);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(699, 'CHAMP', 'montant_cheque', 'java.lang.Double', 'Mnt. Chq', '#,##0.00', 'Right', 80, 5, 57);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(700, 'CHAMP', 'date_recuperation', 'java.sql.Timestamp', 'Date Ché', 'dd/MM/yyyy', 'Center', 80, 6, 57);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(701, 'CHAMP', 'type_recuperation', 'java.lang.String', 'Type Rec.', '', 'Center', 80, 7, 57);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(702, 'CHAMP', 'numero_cheque', 'java.lang.String', 'N° Chq', '', 'Center', 100, 8, 57);

-- Recap group by

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_group_by)
values (703, 'VARIABLE', 'totalMntQtc', 'java.lang.Double', 'Total Quittance : ', '#,##0.00', 'Right', 'Sum', '$F{montant_quittance}', 1, 15);                               



--24/03/2014
update SIN_RAPPORT_ELEMENT
set libelle='Date Ché'
where id=700;

update SIN_RAPPORT_ELEMENT
set width=200
where id=695;

update SIN_RAPPORT_ELEMENT
set width=80
where id=700;

update SIN_RAPPORT_ELEMENT
set width=100
where id=702;


update sin_rapport
set requete_sql='select order_affichage, compagnie, 
case length(numero_sinistre)
when 21 then substr(numero_sinistre, 1, 3) || '' '' || substr(numero_sinistre, 4, 1) || '' '' || 
substr(numero_sinistre, 5, 4) || '' '' || substr(numero_sinistre, 9, 3)
|| '' '' || substr(numero_sinistre, 12, 4) || '' '' || substr(numero_sinistre, 16, 6)                         
else numero_sinistre
end numero_sinistre, societe_adv, avocat_conseil, montant_quittance, montant_cheque, date_recuperation, 
case type_recuperation when ''1'' then ''Totale'' else ''Partielle'' end type_recuperation, numero_cheque
from
(select 1 as order_affichage,
''RMA Watanya'' as compagnie,
sinistre.numerosinistre as numero_sinistre,
(select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
recours.nomavocatconseil as avocat_conseil,
(select amiable.montantquitance from sin_recours_amiable amiable where amiable.idrecours=recours.id) as montant_quittance,
recours.mntdeboursloi as montant_cheque,recours.daterecuperation as date_recuperation,recours.isrecuperationtotale as type_recuperation,
recours.numerocheque as numero_cheque
from sin_recours recours, sin_sinistre sinistre
where recours.typerecours = ''2''
and sinistre.id = recours.idsinistre
and to_number(to_char(sinistre.datecreation, ''yyyy'')) >= 2005
union
select 2 as order_affichage,
''RMA'' as compagnie,
sinistre.numerosinistre as numero_sinistre,
(select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
recours.nomavocatconseil as avocat_conseil,
(select amiable.montantquitance from sin_recours_amiable amiable where amiable.idrecours=recours.id) as montant_quittance,
recours.mntdeboursloi as montant_cheque,recours.daterecuperation as date_recuperation,recours.isrecuperationtotale as type_recuperation,recours.numerocheque as numero_cheque
from sin_recours recours, sin_sinistre sinistre
where recours.typerecours = ''2''
and sinistre.id = recours.idsinistre
and length(sinistre.numerosinistre) = 21
and substr(sinistre.numerosinistre, 5, 4) = ''9700''
and to_number(to_char(sinistre.datecreation, ''yyyy'')) <= 2004
union
select 3 as order_affichage,''Watanya'' as compagnie,
sinistre.numerosinistre as numero_sinistre,(select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
recours.nomavocatconseil as avocat_conseil,(select amiable.montantquitance from sin_recours_amiable amiable where amiable.idrecours=recours.id) as montant_quittance,
recours.mntdeboursloi as montant_cheque,recours.daterecuperation as date_recuperation,recours.isrecuperationtotale as type_recuperation,recours.numerocheque as numero_cheque
from sin_recours recours, sin_sinistre sinistre
where recours.typerecours = ''2''
and sinistre.id = recours.idsinistre
and length(sinistre.numerosinistre) = 21
and substr(sinistre.numerosinistre, 5, 4) not in (''9700'', ''9870'',''9880'') 
and to_number(to_char(sinistre.datecreation, ''yyyy'')) <= 2004
union
select 4 as order_affichage,''Alliance'' as compagnie,sinistre.numerosinistre as numero_sinistre,
(select '' '' || lblcompagnie from syn_assurance where codcompagnie = recours.codecompagnieadverse) as societe_adv,
recours.nomavocatconseil as avocat_conseil,(select amiable.montantquitance from sin_recours_amiable amiable where amiable.idrecours=recours.id) as montant_quittance,
recours.mntdeboursloi as montant_cheque,recours.daterecuperation as date_recuperation,recours.isrecuperationtotale as type_recuperation,recours.numerocheque as numero_cheque
from sin_recours recours, sin_sinistre sinistre
where recours.typerecours = ''2''
and sinistre.id = recours.idsinistre
and length(sinistre.numerosinistre) = 21
and substr(sinistre.numerosinistre, 5, 4) in (''9870'',''9880'') 
and to_number(to_char(sinistre.datecreation, ''yyyy'')) <= 2004
)
order by order_affichage'
where id=57;



