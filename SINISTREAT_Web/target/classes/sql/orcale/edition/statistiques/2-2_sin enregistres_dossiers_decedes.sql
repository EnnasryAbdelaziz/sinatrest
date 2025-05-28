-- Entete
insert into SIN_ENTETE(id, sous_titre) values(19, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(24, 'Etat des Sinistres enregistrés des dossiers décédés', 19);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(19, '19', 'Etat des Sinistres enregistrés des dossiers décédés  ',
                'select 
                    case length(SIN_SINISTRE.NUMEROSINISTRE)
                                when 17 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12) 
                                else SIN_SINISTRE.NUMEROSINISTRE
                    end NUMEROSINISTRE,                              
                   SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                   SIN_SINISTRE.IPP as IPP, 
                   SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                   SIN_SINISTRE.ITT as ITT,
                   SIN_DETAIL_REGLEMENT.MONTANT as MONTANT,
                   null as ACHAT,
                   null as CAPITAUX_GSR,
                   SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE,
                   SIN_SINISTRE.RESERVEORDINAIRE as RESERVEORDINAIRE,
                   nvl(SIN_SINISTRE.RESERVEGRAVE, 0) + nvl(SIN_SINISTRE.RESERVEORDINAIRE, 0) as TOTAL,
                   case  SIN_ETAT_SINISTRE.CODETAT
                        when ''0'' then ''I''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end CODETAT
                               
                FROM SIN_SINISTRE SIN_SINISTRE, SIN_EVENEMENT SIN_EVENEMENT, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, 
                SIN_REGLEMENT SIN_REGLEMENT, SIN_ETAT_SINISTRE SIN_ETAT_SINISTRE, SIN_VICTIME SIN_VICTIME
                where SIN_DETAIL_REGLEMENT.CODEGARANTIE in(24,21,32,23,27)				
                AND SIN_DETAIL_REGLEMENT.IDREGLEMENT = SIN_REGLEMENT.ID				
                AND SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID				
                AND SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID 				
                AND SIN_SINISTRE.ID =SIN_ETAT_SINISTRE.IDSINISTRE 				
                AND SIN_VICTIME.ISDECEDE=1 
                AND SIN_VICTIME.ID=SIN_SINISTRE.IDVICTIME'
                               
                                       ,1, 19);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(231, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRE', '', 'Center', 145, 1, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(232, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'Grv.', '', 'Center', 100, 2, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(233, 'CHAMP', 'IPP', 'java.lang.String', 'TAUX D''IPP', '', 'Center', 80, 3, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(234, 'CHAMP', 'DATEACCIDENT', 'java.sql.Timestamp', 'DATE  ACCIDENT', 'dd/MM/yyyy', 'Center', 105, 4, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(235, 'CHAMP', 'ITT', 'java.lang.String', 'I.T.T', '', 'Center', 60, 5, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(236, 'CHAMP', 'MONTANT', 'java.lang.Double', 'MONTANT', '#,##0.00', 'Right', 120, 6, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(237, 'CHAMP', 'ACHAT', 'java.lang.Double', 'ACHAT', '#,##0.00', 'Right', 120, 7, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(238, 'CHAMP', 'CAPITAUX_GSR', 'java.lang.Double', 'CAPITAUX GSR', '#,##0.00', 'Right', 120, 8, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(239, 'CHAMP', 'RESERVEGRAVE', 'java.lang.Double', 'GRAVES', '#,##0.00', 'Right', 120, 9, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(240, 'CHAMP', 'RESERVEORDINAIRE', 'java.lang.Double', 'ORDINAIRES', '#,##0.00', 'Right', 120, 10, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(241, 'CHAMP', 'TOTAL', 'java.lang.Double', 'COUT TOTAL', '#,##0.00', 'Right', 120, 11, 19);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(242, 'CHAMP', 'CODETAT', 'java.lang.String', 'ETAT SIN', '', 'Center', 60, 12, 19);

