--Entete
insert into SIN_ENTETE(id, sous_titre) values(14, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(18, 'Etat des descendants agés entre 16 et 20 ans', 14);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(14, '14', 'Etat des descendants agés entre 16 et 20 ans pour réclamation de cértificat de scolarité',
                'select
                    case length(sinistre.numerosinistre)
                    when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                    || '' '' || substr(sinistre.numerosinistre, 12) 
                    else sinistre.numerosinistre
                    end numero_sinistre,  
                    rente.numero_rente as numero_rente,
                    rentier.lien_parente as classe,
                    '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                    (select '' '' || tuteur.nom || '' '' || tuteur.prenom from gsr_tuteur tuteur where tuteur.id=rentier.idtuteur) as tuteur,
                    rentier.date_naissance as date_naissance,                   
                    rentier.montant_rente_trimest as rente_trimestriel,
                    rentier.ipp_taux_rente as taux,
                    (select etat_rente.libelle from prm_etat_rente etat_rente where etat_rente.code = rentier.etat_rente) as situation
                    
                    from gsr_rente rente, gsr_rentier rentier, sin_sinistre sinistre
                    where rentier.etat_rente in (4, 6)
                    and rentier.lien_parente >= 20
                    and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) >= 16
                    and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) <= 20 
                    and rente.id = rentier.gsr_rente
                    and rente.dossier_sinistre = sinistre.id'
                                       ,1, 14);
                                                                                               
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(176, 'CHAMP', 'numero_sinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 160, 1, 14);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(177, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 80, 2, 14);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(178, 'CHAMP', 'classe', 'java.lang.String', 'Classe', '', 'Center', 70, 3, 14);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(179, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 200, 4, 14);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(180, 'CHAMP', 'tuteur', 'java.lang.String', 'Tuteur', '', 'Left', 200, 5, 14);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(182, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Naissance', 'dd/MM/yyyy', 'Center', 120, 7, 14);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(183, 'CHAMP', 'rente_trimestriel', 'java.lang.Double', 'Rente Tri.', '#,##0.00', 'Right', 100, 8, 14);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(184, 'CHAMP', 'taux', 'java.lang.String', 'Taux (%)', '', 'Center', 70, 9, 14);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(185, 'CHAMP', 'situation', 'java.lang.String', 'Situation', '', 'Center', 100, 10, 14);

