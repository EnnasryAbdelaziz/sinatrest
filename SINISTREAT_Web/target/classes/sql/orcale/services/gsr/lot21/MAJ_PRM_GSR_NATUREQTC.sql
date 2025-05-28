/*
 * Ajouter les lignes suivante dans la table PRM_GSR_NATUREQTC 18/06/2013
 */

Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (6,'13/06/2013',7,'Versement complément Capital Constitutif  à la CNRA après constitution');
Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (7,'13/06/2013',8,'Versement prorata capital constitutif à la CNRA');
Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (8,'13/06/2013',9,'Capital remariage');
Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (9,'13/06/2013',10,'Virement Capital constitutif');
Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (10,'13/06/2013',11,'Augmentation Capital constitutif');
Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (11,'13/06/2013',12,'Diminution Capital constitutif');
Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (12,'13/06/2013',13,'Diminution Capital Constitutif  pour trop perçu');
Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (13,'13/06/2013',14,'Remboursement par chèque');
Insert into PRM_GSR_NATUREQTC (CODE,DATE_CREATION,ID,LIBELLE) values (14,'13/06/2013',15,'Remboursement par prélèvement');


/*
 * Modifier les lignes suivante dans la table PRM_GSR_NATUREQTC 18/06/2013 
 */

UPDATE PRM_GSR_NATUREQTC SET LIBELLE = 'Rente périodique',DATE_CREATION = '13/06/2013'  WHERE ID = 1;
UPDATE PRM_GSR_NATUREQTC SET LIBELLE = 'Prorata rente',DATE_CREATION = '13/06/2013' WHERE ID = 2;
UPDATE PRM_GSR_NATUREQTC SET LIBELLE = 'Complément rente',DATE_CREATION = '13/06/2013' WHERE ID = 3;
UPDATE PRM_GSR_NATUREQTC SET LIBELLE = 'Rachat après constitution',DATE_CREATION = '13/06/2013' WHERE ID = 4;
UPDATE PRM_GSR_NATUREQTC SET LIBELLE = 'Complément Rachat',DATE_CREATION = '13/06/2013' WHERE ID = 5;
UPDATE PRM_GSR_NATUREQTC SET LIBELLE = 'Versement Capital Constitutif  à la CNRA après constitution',DATE_CREATION = '13/06/2013' WHERE ID = 6;
