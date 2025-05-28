-- Entete
insert into SIN_ENTETE(id, sous_titre) values(2, null);

insert into SIN_LIGNE_TITRE(id, libelle, id_sin_entete) values(2, 'ETAT DE CHANGEMENT DE RESERVE', 2);

insert into SIN_RECAP(id, type_fille, description) values(1, 'RECAP_GENERALE', 'Summary etat Etat de chagement de reserve');

insert into SIN_RAPPORT(id, code, description, requete_sql, id_sin_template, id_sin_entete, id_sin_recap_generale)
values(2, '02', 'Etat de chagement de reserve',
                'select  
                    case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numerosinistre,
                   sinistre.numerograve as numerograve,
                   evenement.dateaccident as dateaccident, mouvement.reserveordold as reserveordold, mouvement.reserveord as reserveord,
                   mouvement.reservegraveold as reservegraveold, mouvement.reservegrave as reservegrave, 
                    (case  etat_anc.codetat
                        when ''0'' then ''E''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end) etat_anci , 
                     (case  etat_nov.codetat
                        when ''0'' then ''E''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end) etat_nouv,
                   
                    case length(sinistre.numeropolice)
                         when 15 then substr(sinistre.numeropolice, 1, 4) || '' '' || substr(sinistre.numeropolice, 5, 3) || '' '' || substr(sinistre.numeropolice, 8, 4)
                            || '' '' || substr(sinistre.numeropolice, 12) 
                         else sinistre.numeropolice
                    end numeropolice,
                   
                   mouvement.datecreation as date_operation, mouvement.utilisateur as redacteur
                   
            from sin_sinistre sinistre, sin_evenement evenement, sin_mouvement mouvement, 
                sin_etat_sinistre etat_anc, sin_etat_sinistre etat_nov
                
            where mouvement.idmotif in (3, 4, 7, 8, 10)
            and to_date(to_char(mouvement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
            and sinistre.idevenement = evenement.id
            and mouvement.idsinistre=sinistre.id
            and mouvement.id_old_etat=etat_anc.idetatsinistre
            and mouvement.id_new_etat=etat_nov.idetatsinistre'
                               
                                       ,1, 2, 1);
                                                                                                       
-- Parametres de l'entete
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(13, 'PARAMETRE', 'dateDebut', 'java.lang.String', 'DU : ', 1, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, ordre_affichage, id_sin_entete)
values(14, 'PARAMETRE', 'dateFin', 'java.lang.String', 'AU : ', 2, 2);

-- CHAMPS
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(15, 'CHAMP', 'numerosinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 210, 1, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(16, 'CHAMP', 'numerograve', 'java.lang.String', 'Grv.', '', 'Center', 60, 2, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(17, 'CHAMP', 'dateaccident', 'java.sql.Timestamp', 'Date Acci.', 'dd/MM/yyyy', 'Center', 80, 3, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(18, 'CHAMP', 'reserveordold', 'java.lang.Double', 'Anc.Rsv.Ord', '#,##0.00', 'Right', 75, 4, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(19, 'CHAMP', 'reserveord', 'java.lang.Double', 'Nov.Rsv.Ord', '#,##0.00', 'Right', 75, 5, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(20, 'CHAMP', 'reservegraveold', 'java.lang.Double', 'Anc.Rsv.Grv', '#,##0.00', 'Right', 75, 6, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(21, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Nov.Rsv.Grv', '#,##0.00', 'Right', 75, 7, 2);
  
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(22, 'CHAMP', 'etat_anci', 'java.lang.String', 'Anc.E', '', 'Center', 35, 8, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(23, 'CHAMP', 'etat_nouv', 'java.lang.String', 'Nov.E', '', 'Center', 35, 9, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(24, 'CHAMP', 'numeropolice', 'java.lang.String', 'N° Police', '', 'Center', 140, 10, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(25, 'CHAMP', 'date_operation', 'java.sql.Timestamp', 'Date Opé.', 'dd/MM/yyyy', 'Center', 65, 11, 2);

insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(26, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 120, 12, 2);


-- Recap generale                   
insert into SIN_RECAP_COLONNE(id, name, calculation, id_sin_recap, id_champ)
values(1, 'totalResvOrd', 'Sum', 1, 18); 
                          
insert into SIN_RECAP_COLONNE(id, name, calculation, id_sin_recap, id_champ)
values(2, 'totalResvNouv', 'Sum', 1, 19);

insert into SIN_RECAP_COLONNE(id, name, calculation, id_sin_recap, id_champ)
values(3, 'totalResvGraveOld', 'Sum', 1, 20);

insert into SIN_RECAP_COLONNE(id, name, calculation, id_sin_recap, id_champ)
values(4, 'totalResvGraveNouv', 'Sum', 1, 21);       
                      
                               
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, text_alignment, calculation, variable_expression, ordre_affichage, id_sin_recap)
values (27, 'VARIABLE', 'nbre_operations', 'java.lang.Integer', 'Nombre d''opération : ', 'Left', 'Count', '$F{numerosinistre}', 1, 1);                               
                               
                               
               
--28/02/2014
delete from SIN_RAPPORT_ELEMENT where id=15;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(15, 'CHAMP', 'numerosinistre', 'java.lang.String', 'N° Sinistre', '', 'Center', 180, 1, 2);

delete from SIN_RAPPORT_ELEMENT where id=24;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(24, 'CHAMP', 'numeropolice', 'java.lang.String', 'N° Police', '', 'Center', 140, 10, 2);                               
                               
delete from SIN_RAPPORT_ELEMENT where id=17;
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(17, 'CHAMP', 'dateaccident', 'java.sql.Timestamp', 'Date Acci.', 'dd/MM/yyyy', 'Center', 80, 3, 2);                               
                               
delete from SIN_RAPPORT_ELEMENT where id=25;                              
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(25, 'CHAMP', 'date_operation', 'java.sql.Timestamp', 'Date Opé.', 'dd/MM/yyyy', 'Center', 80, 11, 2);

delete from SIN_RAPPORT_ELEMENT where id=16;  
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(16, 'CHAMP', 'numerograve', 'java.lang.String', 'Grv.', '', 'Center', 60, 2, 2);

delete from SIN_RAPPORT_ELEMENT where id=26;  
insert into SIN_RAPPORT_ELEMENT(id,type_fille, name, type, libelle, pattern, text_alignment, width, ordre_affichage, id_sin_rapport)
values(26, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', '', 'Left', 120, 12, 2);


update sin_rapport
set requete_sql='select  
                    case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numerosinistre,
                   sinistre.numerograve as numerograve,
                   evenement.dateaccident as dateaccident, mouvement.reserveordold as reserveordold, mouvement.reserveord as reserveord,
                   mouvement.reservegraveold as reservegraveold, mouvement.reservegrave as reservegrave, 
                    (case  etat_anc.codetat
                        when ''0'' then ''E''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end) etat_anci , 
                     (case  etat_nov.codetat
                        when ''0'' then ''E''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end) etat_nouv,
                   
                    case length(sinistre.numeropolice)
                         when 15 then substr(sinistre.numeropolice, 1, 4) || '' '' || substr(sinistre.numeropolice, 5, 3) || '' '' || substr(sinistre.numeropolice, 8, 4)
                            || '' '' || substr(sinistre.numeropolice, 12) 
                         else sinistre.numeropolice
                    end numeropolice,
                   
                   mouvement.datecreation as date_operation, mouvement.utilisateur as redacteur
                   
            from sin_sinistre sinistre, sin_evenement evenement, sin_mouvement mouvement, 
                sin_etat_sinistre etat_anc, sin_etat_sinistre etat_nov
                
            where mouvement.idmotif in (3, 4, 7, 8, 10)
            and to_date(to_char(mouvement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
            and sinistre.idevenement = evenement.id
            and mouvement.idsinistre=sinistre.id
            and mouvement.id_old_etat=etat_anc.idetatsinistre
            and mouvement.id_new_etat=etat_nov.idetatsinistre'
       where id=2;
       

--03/03/2014
update SIN_RAPPORT_ELEMENT
set width=210
where id=15;

--26/03/2014
Alter table sin_mouvement add id_old_etat number(9);
Alter table sin_mouvement add id_new_etat number(9);