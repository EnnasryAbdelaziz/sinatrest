-- Entete
insert into SIN_ENTETE(id, sous_titre) values(18, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(23, 'Etat des sinistres enregistrés des dossiers ordinaires', 18);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(18, '18', 'Etat des Sinistres enregistrés des dossiers ordinaires',
                'select 
                    case length(SIN_SINISTRE.NUMEROSINISTRE)
                                when 17 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12) 
                                else SIN_SINISTRE.NUMEROSINISTRE
                    end NUMEROSINISTRE,
                   SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                   SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                   SIN_SINISTRE.ITT as ITT,
                   SIN_DETAIL_REGLEMENT.MONTANT as MONTANT
                from SIN_SINISTRE SIN_SINISTRE, SIN_EVENEMENT SIN_EVENEMENT, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, SIN_REGLEMENT SIN_REGLEMENT
                where SIN_DETAIL_REGLEMENT.CODEGARANTIE = 24
                and SIN_DETAIL_REGLEMENT.IDREGLEMENT = SIN_REGLEMENT.ID
                and SIN_REGLEMENT.IDSINISTRE = SIN_SINISTRE.ID
                and SIN_SINISTRE.IDEVENEMENT = SIN_EVENEMENT.ID'
                               
                                       ,1, 18);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(226, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRES', '', 'Center', 160, 1, 18);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(227, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'N° Grave', '', 'Center', 100, 2, 18);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(228, 'CHAMP', 'DATEACCIDENT', 'java.sql.Timestamp', 'DATE  SINISTRES', 'dd/MM/yyyy', 'Center', 140, 3, 18);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(229, 'CHAMP', 'ITT', 'java.lang.String', 'I.T.T', '', 'Center', 60, 4, 18);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(230, 'CHAMP', 'MONTANT', 'java.lang.Double', 'FRAIX MEDICAUX HONORAIRES ', '#,##0.00', 'Right', 200, 5, 18);

                              
                               
                               
                               

 
