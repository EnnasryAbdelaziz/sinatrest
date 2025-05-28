-- Entete
insert into SIN_ENTETE(id, sous_titre) values(45, null);

insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(59, 'titre', '1', 45);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(45, '45', 'Etat des Sinistres enregistrés par police',
                'select assure, numero_sinistre, numero_grave, nom_victime, ipp, itt,
frais_medicaux, rachat, arrerages, capital_cdg,
rub28_29 - rub30 as capitaux_gsr, frais_divers, ordinaire, grave,
prothese, cout_total, date_accident,
(case  codetat
 when ''0'' then ''I''
when ''1'' then ''E''
when ''2'' then ''J'' 
when ''3'' then ''T''
when ''4'' then ''S''
end) etat from (
select 
sn.codeclient || '' '' || sn.nomclient as assure,sn.numerosinistre as numero_sinistre,sn.numerograve as numero_grave,'' '' || vt.nom || '' '' || vt.prenom as nom_victime,
sn.ipp as ipp, sn.ipp as itt,
(select sum(dr.montant) from sin_detail_reglement dr, sin_reglement rg where dr.codeprestation = ''24'' and dr.idreglement = rg.id and rg.idsinistre = sn.id) as frais_medicaux,
(select sum(dr.montant) from sin_detail_reglement dr, sin_reglement rg where dr.codeprestation = ''22''and dr.idreglement = rg.id and rg.idsinistre = sn.id) as rachat,
(select sum(dr.montant) from sin_detail_reglement dr, sin_reglement rg where dr.codeprestation in (''21'', ''32'') and dr.idreglement = rg.id and rg.idsinistre = sn.id) as arrerages,
(select sum(dr.montant) from sin_detail_reglement dr, sin_reglement rg where dr.codeprestation = ''23'' and dr.idreglement = rg.id and rg.idsinistre = sn.id) as capital_cdg,
(select sum(dr.montant) from sin_detail_reglement dr, sin_reglement rg where dr.codeprestation in (''28'', ''29'') and dr.idreglement = rg.id and rg.idsinistre = sn.id) as rub28_29,
(select sum(dr.montant) from sin_detail_reglement dr, sin_reglement rg where dr.codeprestation = ''30'' and dr.idreglement = rg.id and rg.idsinistre = sn.id) as rub30,
(select sum(dr.montant) from sin_detail_reglement dr, sin_reglement rg where dr.codeprestation = ''27'' and dr.idreglement = rg.id and rg.idsinistre = sn.id) as frais_divers,   
sn.reserveordinaire as ordinaire,sn.reservegrave as grave, sn.prothese as prothese, 
nvl((select sum(rg.montant) from sin_reglement rg where rg.idsinistre = sn.id), 0) + nvl(sn.reserveordinaire, 0) + nvl(sn.reservegrave, 0) + nvl(sn.prothese, 0)  as cout_total,
(select etat_sn.codetat from sin_etat_sinistre etat_sn where etat_sn.idetatsinistre in (select max(etat_sin.idetatsinistre) from sin_etat_sinistre etat_sin where etat_sin.idsinistre = sn.id)) as codetat,
eve.dateaccident as date_accident
from sin_sinistre sn, sin_victime vt, sin_evenement eve
where sn.numeropolice = $P{numeroPolice}
and  eve.dateaccident between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
and sn.idvictime = vt.id
and sn.idevenement = eve.id)
order by assure'
                               
                                       ,1, 45);

-- Parametres de rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(521, 'PARAMETRE', 'numeroPolice', 'java.lang.String', 45);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(522, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(523, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 45);


-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(13, 'grAssure', '$F{assure}', '1', 1, 45);


-- Champs group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(524, 'CHAMP', 'assure', 'java.lang.String', ' NOM ASSURE / SOUSCRIPTEUR : ', 1, 13);




                                                                                                 
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(525, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° SINISTRE', '', 'Center', 145, 1, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(526, 'CHAMP', 'numero_grave', 'java.lang.String', 'Grv.', '', 'Center', 40, 2, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(527, 'CHAMP', 'nom_victime', 'java.lang.String', 'NOM VICTIME', '', 'Left', 200, 3, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(528, 'CHAMP', 'ipp', 'java.lang.String', 'I.P.P', '', 'Center', 40, 4, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(529, 'CHAMP', 'itt', 'java.lang.String', 'I.T.T', '', 'Center', 40, 5, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(530, 'CHAMP', 'frais_medicaux', 'java.lang.Double', 'FRAIS MEDICAUX', '#,##0.00', 'Right', 80, 6, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(531, 'CHAMP', 'rachat', 'java.lang.Double', 'RACHAT', '#,##0.00', 'Right', 80, 7, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(532, 'CHAMP', 'arrerages', 'java.lang.Double', 'ARRERAGES', '#,##0.00', 'Right', 80, 8, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(533, 'CHAMP', 'capital_cdg', 'java.lang.Double', 'CAPITAL C.D.G', '#,##0.00', 'Right', 80, 9, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(534, 'CHAMP', 'capitaux_gsr', 'java.lang.Double', 'CAPITAUX GSR', '#,##0.00', 'Right', 80, 10, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(535, 'CHAMP', 'frais_divers', 'java.lang.Double', 'FRAIS DIVERS', '#,##0.00', 'Right', 80, 11, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(536, 'CHAMP', 'ordinaire', 'java.lang.Double', 'ORDINAIRE', '#,##0.00', 'Right', 80, 12, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(537, 'CHAMP', 'grave', 'java.lang.Double', 'GRAVE', '#,##0.00', 'Right', 80, 13, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(538, 'CHAMP', 'prothese', 'java.lang.Double', 'PROTHESE', '#,##0.00', 'Right', 80, 14, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(539, 'CHAMP', 'cout_total', 'java.lang.Double', 'COUT TOTAL', '#,##0.00', 'Right', 80, 15, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(540, 'CHAMP', 'etat', 'java.lang.String', 'ETAT', '', 'Center', 40, 16, 45);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(541, 'CHAMP', 'date_accident', 'java.sql.Timestamp', 'DATE ACC', 'dd/MM/yyyy', 'Center', 80, 17, 45);

  


