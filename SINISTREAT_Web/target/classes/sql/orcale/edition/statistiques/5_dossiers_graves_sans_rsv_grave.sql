-- Entete
insert into SIN_ENTETE(id, sous_titre) values(23, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(29, 'ETAT DES DOSSIERS GRAVES SANS', 23);
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(30, 'RESERVE GRAVE', 23);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(23, '23', 'ETAT DES DOSSIERS GRAVES SANS RESERVE GRAVE',
                'select 
                    case length(SIN_SINISTRE.NUMEROSINISTRE)
                                when 17 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12) 
                                else SIN_SINISTRE.NUMEROSINISTRE
                    end NUMEROSINISTRE,
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE
                    
                from SIN_SINISTRE SIN_SINISTRE, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, SIN_REGLEMENT SIN_REGLEMENT
                where SIN_SINISTRE.RESERVEGRAVE is null	
                and SIN_DETAIL_REGLEMENT.CODEGARANTIE = 22			
                and SIN_DETAIL_REGLEMENT.IDREGLEMENT=SIN_REGLEMENT.ID				
                and SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID'
                               
                                       ,1, 23);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(267, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRE', '', 'Center', 160, 1, 23);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(268, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'N° GRAVE', '', 'Center', 100, 2, 23);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(269, 'CHAMP', 'RESERVEGRAVE', 'java.lang.Double', 'RESERVE GRAVE', '#,##0.00', 'Right', 140, 3, 23);



               


 				
				
