--Entete
insert into SIN_ENTETE(id, sous_titre) values(15, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(19, 'Etats des rentes constituées non encore validés', 15);

insert into SIN_RECAP(id, type_fille, description) values(8, 'RECAP_GENERALE', 'Summary Etats des rentes constituées non encore validés');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(15, '15', 'Etats des rentes constituées non encore validés',
                'select          
                
                rente.numero_rente as numero_rente,
                rentier.lien_parente as classe,
                '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                rentier.numero_cin as cin,
                rentier.date_naissance as date_naissance,               
               to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) as age,
               rentier.adresse as adresse,
               (select ville.libelle from syn_ville ville where rentier.ville = ville.id) as ville,
               rentier.ipp_taux_rente as ipp,
               rentier.date_constitution as date_constitution,
               rentier.capital_constitutif as capital_cons,
              rentier.salaire_utile as salaire_utile,
              (case  rentier.prothese
                        when ''1'' then ''Oui''
                        else ''Non''
                        
              end) pr, 
               (select nationalite.libelle from prm_nationalite nationalite where rentier.nationalite = nationalite.code) as nationalite,
               (select s_modepaiement.libelle from syn_modepaiement s_modepaiement where modepai.id_mode_payement = s_modepaiement.code) as edt,
               modepai.numero_rib as rib,
               (select banque.libabreg from syn_banque banque where banque.codbanque = modepai.banque) as banque
               
               from gsr_rente rente, gsr_rentier rentier, gsr_modepayement modepai
            
                where rentier.date_constitution between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and rentier.etat_rente in (''1'', ''2'')
                and rentier.gsr_rente = rente.id
                and rentier.id_mode_payement = modepai.id'
                               
                                       ,1, 15, 8);
                                       
 -- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(625, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(626, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 15);
                                      
                               
-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(187, 'CHAMP', 'numero_rente', 'java.lang.String', 'N° Rente', '', 'Center', 60, 2, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(188, 'CHAMP', 'classe', 'java.lang.String', 'Cls.', '', 'Center', 40, 3, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(189, 'CHAMP', 'beneficiaire', 'java.lang.String', 'Bénéficiaire', '', 'Left', 160, 4, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(190, 'CHAMP', 'cin', 'java.lang.String', 'N° CIN', '', 'Center', 80, 5, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(191, 'CHAMP', 'date_naissance', 'java.sql.Timestamp', 'Date Naiss', 'dd/MM/yyyy', 'Center', 80, 6, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(192, 'CHAMP', 'age', 'java.lang.Integer', 'Age', '', 'Center', 40, 7, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(193, 'CHAMP', 'adresse', 'java.lang.String', 'Adresse', '', 'Left', 200, 8, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(194, 'CHAMP', 'ville', 'java.lang.String', 'Ville', '', 'Center', 100, 9, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(195, 'CHAMP', 'ipp', 'java.lang.String', 'IPP/Taux', '', 'Center', 70, 10, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(196, 'CHAMP', 'date_constitution', 'java.sql.Timestamp', 'Date Cons.', 'dd/MM/yyyy', 'Center', 80, 11, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(197, 'CHAMP', 'capital_cons', 'java.lang.Double', 'Capital Cons.', '#,##0.00', 'Right', 80, 12, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(198, 'CHAMP', 'salaire_utile', 'java.lang.Double', 'Salaire Utile', '#,##0.00', 'Right', 80, 13, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(199, 'CHAMP', 'pr', 'java.lang.String', 'PR', '', 'Center', 40, 14, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(200, 'CHAMP', 'nationalite', 'java.lang.String', 'Nationalite', '', 'Center', 70, 15, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(201, 'CHAMP', 'edt', 'java.lang.String', 'EDT', '', 'Center', 60, 16, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(202, 'CHAMP', 'rib', 'java.lang.String', 'N° RIB', '', 'Center', 150, 17, 15);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(203, 'CHAMP', 'banque', 'java.lang.String', 'Banque', '', 'Left', 60, 18, 15);

-- Recap generale
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (204, 'VARIABLE', 'nbreDossiers', 'java.lang.Integer', 'Nombre de dossier constituées : ', 'Left', 'Count', '$F{numero_rente}', 1, 8);                               


                                       
                                       
                                       
         
               

