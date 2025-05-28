-- Entete
insert into SIN_ENTETE(id, sous_titre) values(21, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(26, 'ETAT DES EVENEMENTS', 21);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(21, '21', 'ETAT DES EVENEMENTS',
                'select 
                    case length(SIN_SINISTRE.NUMEROSINISTRE)
                                when 17 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12) 
                                else SIN_SINISTRE.NUMEROSINISTRE
                    end NUMEROSINISTRE,
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_SINISTRE.IDEVENEMENT as IDEVENEMENT,
                    SIN_SINISTRE.NUMEROPOLICE as NUMEROPOLICE,                      
                    SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                    case  SIN_ETAT_SINISTRE.CODETAT
                        when ''0'' then ''I''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end CODETAT,
                    SIN_REGLEMENT.MONTANT as CUMUL_RELEMENT,
                    nvl(SIN_SINISTRE.RESERVEGRAVE, 0) + nvl(SIN_SINISTRE.RESERVEORDINAIRE, 0) + nvl(SIN_SINISTRE.RESERVEPROTHESE, 0) as CUMUL_RSVE,
                    nvl(SIN_REGLEMENT.MONTANT, 0) + nvl(SIN_SINISTRE.RESERVEGRAVE, 0) + nvl(SIN_SINISTRE.RESERVEORDINAIRE, 0) + nvl(SIN_SINISTRE.RESERVEPROTHESE, 0) as TOTAL
                 from SIN_SINISTRE SIN_SINISTRE, SIN_EVENEMENT SIN_EVENEMENT, SIN_ETAT_SINISTRE SIN_ETAT_SINISTRE, SIN_REGLEMENT SIN_REGLEMENT
                 
                 where SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID				
                 and   SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID 				
                 and   SIN_SINISTRE.ID =SIN_ETAT_SINISTRE.IDSINISTRE
                 and   SIN_EVENEMENT.DATEACCIDENT between trunc(sysdate, ''YEAR'') and add_months(trunc(sysdate, ''YEAR''), 12)'
                               
                                       ,1, 21);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(255, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRE', '', 'Center', 145, 1, 21);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(256, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'Grv.', '', 'Center', 34, 2, 21);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(257, 'CHAMP', 'IDEVENEMENT', 'java.lang.String', 'N° ENV.', '', 'Center', 80, 3, 21);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(258, 'CHAMP', 'NUMEROPOLICE', 'java.lang.String', 'CODE POLICE', '', 'Center', 150, 4, 21);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(259, 'CHAMP', 'DATEACCIDENT', 'java.sql.Timestamp', 'DATE  ACCIDENT', 'dd/MM/yyyy', 'Center', 105, 5, 21);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(260, 'CHAMP', 'CODETAT', 'java.lang.String', 'ETAT', '', 'Center', 60, 6, 21);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(261, 'CHAMP', 'CUMUL_RELEMENT', 'java.lang.Double', 'CUMUL REGLEMENT', '#,##0.00', 'Right', 140, 7, 21);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(262, 'CHAMP', 'CUMUL_RSVE', 'java.lang.Double', 'CUMUL RESERVE', '#,##0.00', 'Right', 120, 8, 21);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(263, 'CHAMP', 'TOTAL', 'java.lang.Double', 'TOTAL', '#,##0.00', 'Right', 120, 9, 21);


 				
