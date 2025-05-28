-- Entete
insert into SIN_ENTETE(id, sous_titre) values(25, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(33, 'ETAT DES DOSSIERS MAINTENANT RESERVE', 25);
insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(34, 'GRAVE MALGRE SAISIE (RUB. 21)', 25);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(25, '25', 'ETAT DES DOSSIERS MAINTENANT RES. GRAVE MALGRE SAISIE RUB. 21',
                'select 
                    SIN_SINISTRE.NUMEROSINISTRE as NUMEROSINISTRE,
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE
                    
                from SIN_SINISTRE SIN_SINISTRE, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, SIN_REGLEMENT SIN_REGLEMENT
                where SIN_DETAIL_REGLEMENT.CODEGARANTIE =21				
	            and SIN_DETAIL_REGLEMENT.IDREGLEMENT=SIN_REGLEMENT.ID				
	            and SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID'
                               
                                       ,1, 25);
                                                                                                   
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(273, 'CHAMP', 'NUMEROSINISTRE', 'java.lang.String', 'N° SINISTRE', '', 'Center', 160, 1, 25);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(274, 'CHAMP', 'NUMEROGRAVE', 'java.lang.String', 'N° GRAVE', '', 'Center', 100, 2, 25);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(275, 'CHAMP', 'RESERVEGRAVE', 'java.lang.Double', 'RESERVE GRAVE', '#,##0.00', 'Right', 140, 3, 25);


 				
				
