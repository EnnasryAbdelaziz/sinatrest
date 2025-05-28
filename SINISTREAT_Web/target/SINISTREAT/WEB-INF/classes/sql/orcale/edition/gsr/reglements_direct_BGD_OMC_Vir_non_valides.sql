-- Entete
insert into SIN_ENTETE(id, sous_titre) values(54, 'Direct/BGD/OMC/virement Interne Non Validés');

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(68, 'Etat des réglements', 54);

insert into SIN_RECAP(id, type_fille, description) values(20, 'RECAP_GENERALE', 'Summary Etat des réglements Direct/BGD/OMC/virement Interne Non Validés');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(54, '54', 'Etat des réglements Direct/BGD/OMC/virement Interne Non Validés',
        'select mode_regl.libelle as mode_reglment,
                quittance.numero_quittance as numero_quittance,
                quittance.dat_emission as date_sai,
                (select type_qtc.libelle from prm_gsr_typeqtc type_qtc where type_qtc.id=quittance.idstype_qtc) as lbl_tyope_qtc,
                quittance.dat_emission as date_cheque,
                quittance.ref_reglement as num_cheque,
                quittance.montant as montant_regle,
                null as droit_taxe, 
                pre_regl.details as libelle_cheque,
                null as nos_ref, --??
                pre_regl.adresse as adresse,
                pre_regl.pour_le_compte as beneficiaire,
                (select ville.libelle from syn_ville ville where ville.id=pre_regl.codeville) as lbl_ville,
                null as date_annulation
        from gsr_quittance quittance, prm_gsr_moderglt mode_regl, gsr_prerglt pre_regl 

        where quittance.dat_emission between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
        and quittance.idsetat_qtc in (1, 4)
        and (quittance.idsmouvement is not null or quittance.idcomplement is not null)
        and quittance.idsmode_reglement = mode_regl.id
        and quittance.idsprerglt = pre_regl.id
        order by mode_reglment'
        ,1, 54, 20);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(647, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(648, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 54);

-- Group by
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(14, 'grModeReglement', '$F{mode_reglment}', '1', 1, 54);

-- Champs group by
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(649, 'CHAMP', 'mode_reglment', 'java.lang.String', 'Mode Règlement : ', 1, 14);

--Champs
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(650, 'CHAMP', 'numero_quittance', 'java.lang.String', 'N° Quittance', '', 'Center', 110, 1, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(651, 'CHAMP', 'date_sai', 'java.sql.Timestamp', 'Date Sai.', 'dd/MM/yyyy', 'Center', 80, 2, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(652, 'CHAMP', 'lbl_tyope_qtc', 'java.lang.String', 'Type Qtc', '', 'Center', 80, 3, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(653, 'CHAMP', 'date_cheque', 'java.sql.Timestamp', 'Date Cheque', 'dd/MM/yyyy', 'Center', 80, 4, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(654, 'CHAMP', 'num_cheque', 'java.lang.String', 'N° Chèque', '', 'Center', 120, 5, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(655, 'CHAMP', 'montant_regle', 'java.lang.Double', 'Mnt Rég', '#,##0.00', 'Right', 100, 6, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(656, 'CHAMP', 'droit_taxe', 'java.lang.Double', 'Droit/taxe', '#,##0.00', 'Right', 100, 7, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(657, 'CHAMP', 'libelle_cheque', 'java.lang.String', 'Liblle CHQ.', '', 'Center', 120, 8, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(658, 'CHAMP', 'nos_ref', 'java.lang.String', 'Nos ref', '', 'Center', 100, 9, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(659, 'CHAMP', 'adresse', 'java.lang.String', 'Adresse', '', 'Center', 200, 10, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(660, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 200, 11, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(661, 'CHAMP', 'lbl_ville', 'java.lang.String', 'Ville', '', 'Center', 100, 12, 54);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(662, 'CHAMP', 'date_annulation', 'java.sql.Timestamp', 'Date Annul.', 'dd/MM/yyyy', 'Center', 80, 13, 54);

-- Recap generale
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (663, 'VARIABLE', 'totalMntRegle', 'java.lang.Double', 'Total :', 'Right', 'Sum', '$F{montant_regle}', 1, 20); 


