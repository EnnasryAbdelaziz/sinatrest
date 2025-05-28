-- Entete
insert into SIN_ENTETE(id, sous_titre) values(24, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(31, 'ETAT DES DOSSIERS MAINTENANT RESERVE', 24);
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(32, 'GRAVE MALGRE SAISIE (RUB. 23 ou 28)', 24);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(24, '24', 'ETAT DES DOSSIERS MAINTENANT RES. GRAVE MALGRE SAISIE RUB. 23 ou 28',
                'select 
                    case length(SIN_SINISTRE.NUMEROSINISTRE)
                                when 17 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12) 
                                else SIN_SINISTRE.NUMEROSINISTRE
                    end NUMEROSINISTRE,
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE
                    
                from SIN_SINISTRE SIN_SINISTRE, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, SIN_REGLEMENT SIN_REGLEMENT
                where SIN_DETAIL_REGLEMENT.CODEGARANTIE in(23,28)			
	            and SIN_DETAIL_REGLEMENT.IDREGLEMENT=SIN_REGLEMENT.ID				
	            and SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID'
                                       ,1, 24);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(270, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRE', '', 'Center', 160, 1, 24);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(271, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'N° GRAVE', '', 'Center', 100, 2, 24);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(272, 'CHAMP', 'RESERVEGRAVE', 'java.lang.Double', 'RESERVE GRAVE', '#,##0.00', 'Right', 140, 3, 24);



               


 				
				
