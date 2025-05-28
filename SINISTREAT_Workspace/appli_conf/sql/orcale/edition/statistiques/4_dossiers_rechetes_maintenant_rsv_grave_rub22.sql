-- Entete
insert into SIN_ENTETE(id, sous_titre) values(22, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(27, 'ETAT DES DOSSIERS RACHETES MAINTENANT', 22);
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(28, 'RESERVE GRAVE (RUB. 22)', 22);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(22, '22', 'ETAT DES DOSSIERS RACHETES MAINTENANT RESERVE GRAVE (RUB. 22)',
                'select 
                    case length(SIN_SINISTRE.NUMEROSINISTRE)
                                when 17 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12) 
                                else SIN_SINISTRE.NUMEROSINISTRE
                    end NUMEROSINISTRE,
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE
                    
                from SIN_SINISTRE SIN_SINISTRE, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, SIN_REGLEMENT SIN_REGLEMENT
                
                where SIN_DETAIL_REGLEMENT.CODEGARANTIE = 22				
                and	SIN_DETAIL_REGLEMENT.IDREGLEMENT=SIN_REGLEMENT.ID				
                and	SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID'
                               
                                       ,1, 22);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(264, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRE', '', 'Center', 160, 1, 22);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(265, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'N° GRAVE', '', 'Center', 100, 2, 22);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(266, 'CHAMP', 'RESERVEGRAVE', 'java.lang.Double', 'RESERVE GRAVE', '#,##0.00', 'Right', 140, 3, 22);


 				
				
