--Enete
insert into SIN_ENTETE(id, sous_titre) values(48, null);

insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(62, 'titre', '1', 48);

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete)
values(48, '48', 'Auto-collant ayants-droit',
                'select 
                    rente.numero_rente,
                        '' '' || rentier.nom || '' '' || rentier.prenom as nom_prenom,
                        rentier.lien_parente as classe,
                        (select '' '' || tuteur.nom || '' '' || tuteur.prenom as nom_prenom from gsr_tuteur tuteur where tuteur.id = rentier.idtuteur) as tuteur,
                        rentier.date_naissance as date_naissance,
                        rentier.ipp_taux_rente as taux,
                        rentier.montant_rente_trimest as rente_trimest
                    from gsr_rentier rentier, gsr_rente rente
                    where rente.numero_rente = $P{numeroRente}
                    and rentier.gsr_rente = rente.id' 
                                       ,1, 48);

--Parametres rapport
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(558, 'PARAMETRE', 'numeroRente', 'java.lang.String', 48);

-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(559, 'PARAMETRE', 'numeroSinistre', 'java.lang.String', 'SINISTRE : ', 1, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(560, 'PARAMETRE', 'adresse', 'java.lang.String', 'ADRESSE : ', 2, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(561, 'PARAMETRE', 'ville', 'java.lang.String', 'VILLE : ', 3, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(562, 'PARAMETRE', 'nationalite', 'java.lang.String', 'NATIONALITE : ', 4, 48);                                                            

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(563, 'PARAMETRE', 'dateDepart', 'java.lang.String', 'DATE DE DEPART : ', 5, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(564, 'PARAMETRE', 'salaireUtile', 'java.lang.String', 'SALAIRE UTILE : ', 6, 48);                                                            
                                                            
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(565, 'PARAMETRE', 'dateConstitution', 'java.lang.String', 'DATE CONSTITUTION : ', 7, 48);   
             
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(566, 'CHAMP', 'nom_prenom', 'java.lang.String', 'NOM/PRENOM', '', 'Left', 200, 1, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(567, 'CHAMP', 'classe', 'java.lang.String', 'CLS.', '', 'Center', 60, 2, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(568, 'CHAMP', 'tuteur', 'java.lang.String', 'TUTEUR', '', 'Left', 200, 3, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(569, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'DATE NAI.', 'dd/MM/yyyy', 'Center', 80, 4, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(570, 'CHAMP', 'taux', 'java.lang.String', 'TAUX', '', 'Center', 70, 5, 48);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(571, 'CHAMP', 'rente_trimest', 'java.lang.Double', 'RTE TRIM', '#,##0.00', 'Right', 120, 6, 48);



