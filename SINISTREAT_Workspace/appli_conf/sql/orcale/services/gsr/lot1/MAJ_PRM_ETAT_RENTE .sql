/*
 * Ajouter les lignes suivante dans la table PRM_ETAT_RENTE 
 */

Insert into PRM_ETAT_RENTE (CODE,DATE_CREATION,ID,LIBELLE,TYPEETAT) values (5, to_date('05/12/12','DD/MM/RR'), 5, 'Echue', '1');
Insert into PRM_ETAT_RENTE (CODE,DATE_CREATION,ID,LIBELLE,TYPEETAT) values (6, to_date('18/03/13','DD/MM/RR'), 6, 'Suspendue ou Attente', '1');
Insert into PRM_ETAT_RENTE (CODE,DATE_CREATION,ID,LIBELLE,TYPEETAT) values (7, to_date('18/03/13','DD/MM/RR'), 7, 'Supprimée par jugement', '1');
Insert into PRM_ETAT_RENTE (CODE,DATE_CREATION,ID,LIBELLE,TYPEETAT) values (8, to_date('18/03/13','DD/MM/RR'), 8, 'Décès', '1');
Insert into PRM_ETAT_RENTE (CODE,DATE_CREATION,ID,LIBELLE,TYPEETAT) values (9, to_date('18/03/13','DD/MM/RR'), 9, 'Rachat', '1');
Insert into PRM_ETAT_RENTE (CODE,DATE_CREATION,ID,LIBELLE,TYPEETAT) values (10,to_date('18/03/13','DD/MM/RR'), 10,'Versée à la CNRA', '1');

/*
 * Ajouter les lignes suivante dans la table PRM_ETAT_RENTE 18/06/2013
 */

Insert into PRM_ETAT_RENTE (CODE,DATE_CREATION,ID,LIBELLE,TYPEETAT) values (15,to_date('18/06/13','DD/MM/RR'), 15,'Non conforme', '1');
Insert into PRM_ETAT_RENTE (CODE,DATE_CREATION,ID,LIBELLE,TYPEETAT) values (16,to_date('18/06/13','DD/MM/RR'), 16,'En attente de création', '1');
/*
 * Modifier la ligne suivante dans la table PRM_ETAT_RENTE : 
 */

UPDATE PRM_ETAT_RENTE SET LIBELLE = 'Validé-En cours' WHERE ID = 4;

