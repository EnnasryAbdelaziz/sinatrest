SET DEFINE OFF;
Insert into HAB_FONCTIONNALITE
   (ID, CODE, LIBELLE, TITRE, ACTIONCLASSNAME, 
    ISSECURE, TYPEFONCTIONNALITE)
 Values
   (1000, 1000, 'RecupererSeuilUC', 'RecupererSeuilUC', 'com.rmawatanya.moyenpaiement.application.metier.usecases.commun.RecupererSeuilUC', 
    0, NULL);
Insert into HAB_FONCTIONNALITE
   (ID, CODE, LIBELLE, TITRE, ACTIONCLASSNAME, 
    ISSECURE, TYPEFONCTIONNALITE)
 Values
   (1001, 1001, 'RecupererSeuiSubordonneUC', 'RecupererSeuiSubordonneUC', 'com.rmawatanya.moyenpaiement.application.metier.usecases.commun.RecupererSeuiSubordonneUC', 
    NULL, NULL);
COMMIT;
