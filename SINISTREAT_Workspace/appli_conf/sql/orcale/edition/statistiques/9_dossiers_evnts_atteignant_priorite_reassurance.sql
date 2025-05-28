-- Entete
insert into SIN_ENTETE(id, sous_titre) values(27, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(36, 'ETAT DES DOSSIERS ET EVENEMENTS ATTEIGNANT LA PRIORITE REASSURANCE', 27);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(27, '27', 'ETAT DES DOSSIERS ET EVENEMENTS ATTEIGNANT LA PRIORITE REASSURANCE',
                'select 
                    SIN_EVENEMENT.DATEACCIDENT as AA_ACC,
                    SIN_SINISTRE.IDEVENEMENT as NUM_EVNT,
                    case length(SIN_SINISTRE.NUMEROSINISTRE)
                                when 17 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12) 
                                else SIN_SINISTRE.NUMEROSINISTRE
                    end NUMEROSINISTRE,
                    SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                    null as ORDRE_GRAVE,
                    SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                    SIN_SINISTRE.NUMEROPOLICE as NUMEROPOLICE,
                    SIN_SINISTRE.RESERVEORDINAIRE as RSV_ORD,
                    SIN_SINISTRE.RESERVEGRAVE as RSV_GRV,
                    SIN_SINISTRE.RESERVEPROTHESE as RSV_PROTH,
                    nvl(SIN_SINISTRE.RESERVEORDINAIRE, 0) + nvl(SIN_SINISTRE.RESERVEGRAVE, 0) + nvl(SIN_SINISTRE.RESERVEPROTHESE, 0) as TOTAL_RSVE,
                    SIN_REGLEMENT.MONTANT as TOTAL_RGL,
                    nvl(SIN_REGLEMENT.MONTANT, 0) + nvl(SIN_SINISTRE.RESERVEORDINAIRE, 0) + nvl(SIN_SINISTRE.RESERVEGRAVE, 0) + nvl(SIN_SINISTRE.RESERVEPROTHESE, 0) as COUT_TOTAL,
                    case  SIN_ETAT_SINISTRE.CODETAT
                        when ''0'' then ''I''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end ETAT
                    
                                
                 from SIN_EVENEMENT SIN_EVENEMENT, SIN_SINISTRE SIN_SINISTRE, SIN_REGLEMENT SIN_REGLEMENT, SIN_ETAT_SINISTRE SIN_ETAT_SINISTRE
                 where SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID				
                 and SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID 				
                 and SIN_SINISTRE.ID =SIN_ETAT_SINISTRE.IDSINISTRE'
                              
                                       ,1, 27);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(288, 'CHAMP', 'AA_ACC', 'java.sql.Timestamp', 'AA.ACC.', 'dd/MM/yyyy', 'Center', 100, 1, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(289, 'CHAMP', 'NUM_EVNT', 'java.lang.String', 'N° EVEN', '', 'Center', 80, 2, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(290, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRE', '', 'Center', 145, 3, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(291, 'CHAMP', 'ORDRE_GRAVE', 'java.lang.String', 'ORDRE GRAVE', '', 'Center', 100, 4, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(292, 'CHAMP', 'DATEACCIDENT', 'java.sql.Timestamp', 'DATE ACC.', 'dd/MM/yyyy', 'Center', 100, 5, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(293, 'CHAMP', 'NUMEROPOLICE', 'java.lang.String', 'CODE POLICE', '', 'Center', 106, 6, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(294, 'CHAMP', 'RSV_ORD', 'java.lang.Double', 'RESERVE ORD.', '#,##0.00', 'Right', 75, 7, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(295, 'CHAMP', 'RSV_GRV', 'java.lang.Double', 'RESERVE GRV.', '#,##0.00', 'Right', 75, 8, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(296, 'CHAMP', 'RSV_PROTH', 'java.lang.Double', 'RESERVE PROT.', '#,##0.00', 'Right', 75, 9, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(297, 'CHAMP', 'TOTAL_RSVE', 'java.lang.Double', 'TOTAL RES.', '#,##0.00', 'Right', 75, 10, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(298, 'CHAMP', 'TOTAL_RGL', 'java.lang.Double', 'TOTAL REGL.', '#,##0.00', 'Right', 75, 11, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(299, 'CHAMP', 'COUT_TOTAL', 'java.lang.Double', 'COUT TOTAL', '#,##0.00', 'Right', 75, 12, 27);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(300, 'CHAMP', 'ETAT', 'java.lang.String', 'ETAT', '', 'Center', 60, 13, 27);





 				
