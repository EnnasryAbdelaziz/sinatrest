-- Entete
insert into SIN_ENTETE(id, sous_titre) values(20, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(25, 'Etat des Sinistres enregistrés par exercice et intervalle  de taux d''IPP', 20);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(20, '20', 'Etat des Sinistres enregistrés par exercice et intervalle  de taux d''IPP',
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
                SIN_REGLEMENT SIN_REGLEMENT, SIN_ETAT_SINISTRE SIN_ETAT_SINISTRE
                WHERE SIN_DETAIL_REGLEMENT.CODEGARANTIE in(24,21,32,23,27)				
                AND SIN_DETAIL_REGLEMENT.IDREGLEMENT = SIN_REGLEMENT.ID				
                AND SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID				
                AND SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID 				
                AND SIN_SINISTRE.ID =SIN_ETAT_SINISTRE.IDSINISTRE'
                               
                                       ,1, 20);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(243, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRE', '', 'Center', 145, 1, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(244, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'N° GRAVE', '', 'Center', 34, 2, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(245, 'CHAMP', 'IPP', 'java.lang.String', 'TAUX D''IPP', '', 'Center', 80, 3, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(246, 'CHAMP', 'DATEACCIDENT', 'java.sql.Timestamp', 'DATE  ACCIDENT', 'dd/MM/yyyy', 'Center', 105, 4, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(247, 'CHAMP', 'ITT', 'java.lang.String', 'I.T.T', '', 'Center', 60, 5, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(248, 'CHAMP', 'MONTANT', 'java.lang.Double', 'MONTANT', '#,##0.00', 'Right', 75, 6, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(249, 'CHAMP', 'ACHAT', 'java.lang.Double', 'ACHAT', '#,##0.00', 'Right', 120, 7, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(250, 'CHAMP', 'CAPITAUX_GSR', 'java.lang.Double', 'CAPITAUX GSR', '#,##0.00', 'Right', 120, 8, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(251, 'CHAMP', 'RESERVEGRAVE', 'java.lang.Double', 'GRAVES', '#,##0.00', 'Right', 120, 9, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(252, 'CHAMP', 'RESERVEORDINAIRE', 'java.lang.Double', 'ORDINAIRES', '#,##0.00', 'Right', 120, 10, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(253, 'CHAMP', 'TOTAL', 'java.lang.Double', 'COUT TOTAL', '#,##0.00', 'Right', 120, 11, 20);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(254, 'CHAMP', 'CODETAT', 'java.lang.String', 'ETAT SIN', '', 'Center', 60, 12, 20);

