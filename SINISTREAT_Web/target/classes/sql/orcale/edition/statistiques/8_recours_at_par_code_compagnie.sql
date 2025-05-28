-- Entete
insert into SIN_ENTETE(id, sous_titre) values(26, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(35, 'ETAT DES RECOURS A.T PAR CODE COMPAGNIE', 26);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(26, '26', 'ETAT DES RECOURS A.T PAR CODE COMPAGNIE',
                'select 
                    case length(SIN_RECOURS.NUMEROSINISTRE)
                                when 17 then substr(SIN_RECOURS.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_RECOURS.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_RECOURS.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_RECOURS.NUMEROSINISTRE, 12) 
                                else SIN_RECOURS.NUMEROSINISTRE
                    end NUMEROSINISTRE,
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                    SIN_RECOURS.IDRECOURS as NUM_REF,
                    null as REF,
                    SIN_RECOURS.CODECOMPAGNIEADVERSE as CODECOMPAGNIEADVERSE,
                    null as RGL_ORD_GRAVE,
                    SIN_RECOURS.MONTANTFINAL as COUT_TOTAL,
                    null as CODE_AVOCAT,
                    null as REF2,
                    null as MONTANT_RECUPERE,
                    null as OBSERVATIONS              
                 from SIN_RECOURS SIN_RECOURS, SIN_SINISTRE SIN_SINISTRE, SIN_EVENEMENT SIN_EVENEMENT
                 where    SIN_RECOURS.NUMEROSINISTRE=SIN_SINISTRE.NUMEROSINISTRE                
                 and SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID'
                               
                                       ,1, 26);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(276, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'REFERENCE R.M.A', '', 'Center', 145, 1, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(277, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'Grv.', '', 'Center', 34, 2, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(278, 'CHAMP', 'DATEACCIDENT', 'java.sql.Timestamp', 'DATE  ACCIDENT', 'dd/MM/yyyy', 'Center', 105, 3, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(279, 'CHAMP', 'NUM_REF', 'java.lang.String', 'N° REC.', '', 'Center', 100, 4, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(280, 'CHAMP', 'REF', 'java.lang.String', 'REF.', '', 'Center', 60, 5, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(281, 'CHAMP', 'CODECOMPAGNIEADVERSE', 'java.lang.String', 'CIE   ADVERSE', '', 'Center', 100, 6, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(282, 'CHAMP', 'RGL_ORD_GRAVE', 'java.lang.String', 'REGL. RES. ORD. GRV', '', 'Center', 150, 7, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(283, 'CHAMP', 'COUT_TOTAL', 'java.lang.Double', 'COUT TOTAL', '#,##0.00', 'Right', 120, 8, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(284, 'CHAMP', 'CODE_AVOCAT', 'java.lang.String', 'CODE AVOCAT', '', 'Center', 120, 9, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(285, 'CHAMP', 'REF2', 'java.lang.String', 'REF.', '', 'Center', 80, 10, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(286, 'CHAMP', 'MONTANT_RECUPERE', 'java.lang.Double', 'MONTANT RECUPERE', '#,##0.00', 'Right', 140, 11, 26);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(287, 'CHAMP', 'OBSERVATIONS', 'java.lang.String', 'OBSERVATIONS', '', 'Center', 150, 12, 26);





 				
