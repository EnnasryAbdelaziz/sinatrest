delete from SIN_RAPPORT_ELEMENT where id=158;

update SIN_RAPPORT
    set requete_sql = 'select certificat, numero_rente, classe, beneficiaire, ipp, rente_trimestriel, ville from
                (select 
                        ''Certificat de vie'' as certificat,
                        rente.numero_rente as numero_rente,
                        rentier.lien_parente as classe,
                        '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                        rentier.ipp_taux_rente as ipp,
                        rentier.montant_rente_trimest as rente_trimestriel,
                        ville.lblville as ville
                from gsr_rentier rentier, gsr_rente rente, syn_ville ville
                where rentier.id not in (select idsrentier from gsr_mouvement mvt
                    where mvt.typefille = ''GSR_MVTRCPTCERTIF''
                    and mvt.idstype_certificat = 1
                    and mvt.idsetatmvt = 4
                    and sysdate between mvt.dat_du and mvt.dat_au)
                and rente.id = rentier.gsr_rente
                and rentier.ville = ville.codville
                
                union
                
                select 
                        ''Certificat de non remariage'' as certificat,
                        rente.numero_rente as numero_rente,
                        rentier.lien_parente as classe,
                        '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                        rentier.ipp_taux_rente as ipp,
                        rentier.montant_rente_trimest as rente_trimestriel,
                        ville.lblville as ville
                from gsr_rentier rentier, gsr_rente rente, syn_ville ville
                where rentier.LIEN_PARENTE in (10, 11, 12, 13)
                and rentier.id not in (select idsrentier from gsr_mouvement mvt
                    where mvt.typefille = ''GSR_MVTRCPTCERTIF''
                    and mvt.idstype_certificat = 3
                    and mvt.idsetatmvt = 4
                    and sysdate between mvt.dat_du and mvt.dat_au)
                and rente.id = rentier.gsr_rente
                and rentier.ville = ville.codville
                
                union
                
                select 
                        ''Certificat de scolarité'' as certificat,
                        rente.numero_rente as numero_rente,
                        rentier.lien_parente as classe,
                        '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                        rentier.ipp_taux_rente as ipp,
                        rentier.montant_rente_trimest as rente_trimestriel,
                        ville.lblville as ville
                from gsr_rentier rentier, gsr_rente rente, syn_ville ville
                where rentier.LIEN_PARENTE >= 20
                and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) >=16
                and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) <=21
                and rentier.id not in (select idsrentier from gsr_mouvement mvt
                    where mvt.typefille = ''GSR_MVTRCPTCERTIF''
                    and mvt.idstype_certificat = 4
                    and mvt.idsetatmvt = 4
                    and sysdate between mvt.dat_du and mvt.dat_au)
                and rente.id = rentier.gsr_rente
                and rentier.ville = ville.codville)
                
                order by certificat'
                
where id=12;


-- Group by rente en attente certificat
insert into SIN_GROUP_BY(id, name, groupby_expression, avec_colonnes_in_header, ordre_affichage, id_sin_rapport)
values(12, 'grTypeCertificat', '$F{certificat}', '1', 1, 12);

-- Champs group by rente en attente certificat
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(513, 'CHAMP', 'certificat', 'java.lang.String', 'Type Certificat : ', 1, 12);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_group_by)
values(513, 'CHAMP', 'certificat', 'java.lang.String', ' ', 1, 12);

delete from SIN_LIGNE_TITRE where id=16;



--Rente constitués non encore validés

update SIN_RAPPORT
    set requete_sql = 'select          
                case length(sinistre.numerosinistre)
                    when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                    || '' '' || substr(sinistre.numerosinistre, 12) 
                    else sinistre.numerosinistre
                    end numero_sinistre, 
                rente.numero_rente as numero_rente,
                rentier.lien_parente as classe,
                '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                rentier.numero_cin as cin,
                rentier.date_naissance as date_naissance,               
               to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) as age,
               rentier.adresse as adresse,
               ville.lblville as ville,
               rentier.ipp_taux_rente as ipp,
               rentier.date_constitution as date_constitution,
               rentier.capital_constitutif as capital_cons,
              rentier.salaire_utile as salaire_utile,
              (case  rentier.prothese
                        when ''1'' then ''Oui''
                        else ''Non''
                        
              end) pr , 
               nationalite.libelle as nationalite,
               (select s_modepaiement.libelle from syn_modepaiement s_modepaiement where modepai.id_mode_payement = s_modepaiement.code) as edt,
               modepai.numero_rib as rib,
               (select banque.libabreg from syn_banque banque where banque.codbanque = modepai.banque) as banque
               
               from gsr_rente rente, gsr_rentier rentier, sin_sinistre sinistre, syn_ville ville, prm_nationalite nationalite, 
               gsr_modepayement modepai
            
                where rentier.validation <> ''1''
                and rentier.gsr_rente = rente.id
                and rente.dossier_sinistre = sinistre.id
                and rentier.ville = ville.codville
                and rentier.nationalite = nationalite.code
                and rentier.id_mode_payement = modepai.id'
where id=15;


update sin_rapport
set requete_sql='select 
                case length(sinistre.numerosinistre)
                when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                || '' '' || substr(sinistre.numerosinistre, 12) 
                else sinistre.numerosinistre
                end numero_sinistre,
                rente.numero_rente as numero_rente,
                lien_parente as classe,
               '' '' || rentier.nom || '' '' || rentier.prenom as nom_rentier,
               rentier.date_naissance as date_naissance,
               to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) as age,
               ville.lblville as ville,
               4 * rentier.montant_rente_trimest as rente_annuelle,
               prothese.date_creation as date_prothese,
               nvl(prothese.reserve_prothese, 0) as rserve_math,
               rentier.ipp_taux_rente as taux,
               null as utilisateur
               
               from gsr_rente rente, gsr_rentier rentier, gsr_prothese prothese, syn_ville ville, sin_sinistre sinistre
                where rentier.date_constitution between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') + 1
                and rentier.etat_rente = $P{codeEtatRente}
                and prothese.gsr_rentier = rentier.id
                and rentier.ville = ville.codville
                and rentier.gsr_rente = rente.id
                and rente.dossier_sinistre = sinistre.id'
where id=11;


update sin_rapport
set requete_sql='select
                    case length(sinistre.numerosinistre)
                    when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                    || '' '' || substr(sinistre.numerosinistre, 12) 
                    else sinistre.numerosinistre
                    end numero_sinistre,  
                    rente.numero_rente as numero_rente,
                    rentier.lien_parente as classe,
                    '' '' || rentier.nom || '' '' || rentier.prenom as beneficiaire,
                    '' '' || tuteur.nom || '' '' || tuteur.prenom as tuteur,
                    rentier.adresse as adresse, 
                    rentier.date_naissance as date_naissance,                   
                    rentier.montant_rente_trimest as rente_trimestriel,
                    rentier.ipp_taux_rente as taux,
                    situation.libelle as situation
                    
                    from gsr_rente rente, gsr_rentier rentier, gsr_tuteur tuteur, prm_situation situation, sin_sinistre sinistre
                    where rentier.etat_rente in (4, 6)
                    and rentier.lien_parente >= 20
                    and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) >= 16
                    and to_number(to_char(sysdate, ''yyyy'')) - to_number(to_char(rentier.date_naissance, ''yyyy'')) <= 20 
                    and rente.id = rentier.gsr_rente
                    and rentier.idtuteur = tuteur.id
                    and rentier.situation_rentier = situation.code
                    and rente.dossier_sinistre = sinistre.id'
where id=14;



--Evolution Recuperation QC 333
insert into SIN_LIGNE_TITRE(id, libelle, is_dynamique, id_sin_entete) values(58, 'redacteur', '1', 35);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, id_sin_rapport)
values(520, 'PARAMETRE', 'codeRedacteur', 'java.lang.String', 35);

update sin_rapport
set description = 'Liste des controles des récuperation par rédacteur', requete_sql='select reglement.numeroquittance as quittance, 
                reglement.datereglement as date_reglement,  
                case length(sinistre.numerosinistre)
                when 17 then substr(sinistre.numerosinistre, 1, 4) || '' '' || substr(sinistre.numerosinistre, 5, 3) || '' '' || substr(sinistre.numerosinistre, 8, 4)
                || '' '' || substr(sinistre.numerosinistre, 12) 
                else sinistre.numerosinistre
                end numero_sinistre, 
               sinistre.numerograve as numero_grave, 
               reglement.nombeneficiaire as nom_beneficiaire,
                reglement.naturerecuperation as nature_recup,
                    reglement.montant as montant

    
        from sin_reglement reglement, sin_sinistre sinistre 
        
        where to_date(to_char(reglement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'') 
        and reglement.codeusercreateur = $P{codeRedacteur}
        and reglement.typereglement = ''4''
        and reglement.idsinistre = sinistre.id
                         
        order by quittance'
where id=35;