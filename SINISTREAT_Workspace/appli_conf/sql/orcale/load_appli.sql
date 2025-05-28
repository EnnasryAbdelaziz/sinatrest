delete from ITAR_SECURITY_ACTION  where id > 1000;
delete from ITAR_ASSOC_SEC_ROLE_ACTION  where IDACTION > 1000;
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('199999', '-', 1, 'Consulter module SinistreAT', 'Consulter module SinistreAT', '-', '199999','sinistreAT','sinistreAT','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 199999);

// MODULE 1: SinistreAT 

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10199999', '-', 1, 'Consulter module Victime', 'Consulter module Victime', '-', '10199999','sinistreAT','sinistreAT', '199999');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10199999);

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('1985110918', '-', 0, 'List Action User', 'List Action User', 'client.sipic.sample.metier.services.ListActionUserUC', '1985110918','sipic','sipic','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 1985110918);


// MODULE 101: Victime 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100009', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Consulter Module AyantDroit', 'Consulter Entite AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitRechercherUC', '10100009','sinistreAT','sinistreAT', '10199999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100000', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Ajout de AyantDroit', 'Add AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitAjouterUC', '10100000','sinistreAT','sinistreAT', '10100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100001', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Modification de AyantDroit', 'Update AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitModifierUC', '10100001','sinistreAT','sinistreAT', '10100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100002', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Edition de AyantDroit', 'Edit AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitEditerUC', '10100002','sinistreAT','sinistreAT', '10100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100003', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Recherche de AyantDroit', 'Search AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitRechercherUC', '10100003','sinistreAT','sinistreAT', '10100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100004', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Affichage de AyantDroit', 'Read AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitConsulterUC', '10100004','sinistreAT','sinistreAT', '10100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100005', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Suppression de AyantDroit', 'Delete AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitSupprimerUC', '10100005','sinistreAT','sinistreAT', '10100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100006', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Annuler edition de AyantDroit', 'Cancel Edit AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitAnnulerEditionUC', '10100006','sinistreAT','sinistreAT', '10100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100008', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Imprimer AyantDroit', 'Print AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitPrintUC', '10100008','sinistreAT','sinistreAT', '10100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100010', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.AyantDroitVO', 1, 'Editer PDF de AyantDroit', 'Edit PDF AyantDroit', 'eai.sinistreAT.sinistreAT.victime.metier.services.AyantDroitEditerPdfUC', '10100010','sinistreAT','sinistreAT', '10100009');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100000);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100001);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100002);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100003);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100004);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100005);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100006);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100008);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100010);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100009);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100109', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Consulter Module Victime', 'Consulter Entite Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeRechercherUC', '10100109','sinistreAT','sinistreAT', '10199999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100100', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Ajout de Victime', 'Add Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeAjouterUC', '10100100','sinistreAT','sinistreAT', '10100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100101', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Modification de Victime', 'Update Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeModifierUC', '10100101','sinistreAT','sinistreAT', '10100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100102', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Edition de Victime', 'Edit Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeEditerUC', '10100102','sinistreAT','sinistreAT', '10100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100103', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Recherche de Victime', 'Search Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeRechercherUC', '10100103','sinistreAT','sinistreAT', '10100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100104', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Affichage de Victime', 'Read Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeConsulterUC', '10100104','sinistreAT','sinistreAT', '10100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100105', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Suppression de Victime', 'Delete Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeSupprimerUC', '10100105','sinistreAT','sinistreAT', '10100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100106', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Annuler edition de Victime', 'Cancel Edit Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeAnnulerEditionUC', '10100106','sinistreAT','sinistreAT', '10100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100108', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Imprimer Victime', 'Print Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimePrintUC', '10100108','sinistreAT','sinistreAT', '10100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100110', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Editer PDF de Victime', 'Edit PDF Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeEditerPdfUC', '10100110','sinistreAT','sinistreAT', '10100109');


insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10100107', 'eai.sinistreAT.sinistreAT.victime.metier.valueobjects.VictimeVO', 1, 'Afficher Version de Victime', 'Version Detail of Victime', 'eai.sinistreAT.sinistreAT.victime.metier.services.VictimeAfficherVersionUC', '10100107','sinistreAT','sinistreAT', '10100109');








insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100100);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100101);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100102);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100103);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100104);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100105);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100106);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100108);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100110);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100107);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10100109);

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10299999', '-', 1, 'Consulter module DossierSinistreAT', 'Consulter module DossierSinistreAT', '-', '10299999','sinistreAT','sinistreAT', '199999');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10299999);

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('1985110918', '-', 0, 'List Action User', 'List Action User', 'client.sipic.sample.metier.services.ListActionUserUC', '1985110918','sipic','sipic','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 1985110918);


// MODULE 102: DossierSinistreAT 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200009', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Consulter Module DossierSinistre', 'Consulter Entite DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreRechercherUC', '10200009','sinistreAT','sinistreAT', '10299999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200000', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Ajout de DossierSinistre', 'Add DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreAjouterUC', '10200000','sinistreAT','sinistreAT', '10200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200001', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Modification de DossierSinistre', 'Update DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreModifierUC', '10200001','sinistreAT','sinistreAT', '10200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200002', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Edition de DossierSinistre', 'Edit DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreEditerUC', '10200002','sinistreAT','sinistreAT', '10200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200003', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Recherche de DossierSinistre', 'Search DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreRechercherUC', '10200003','sinistreAT','sinistreAT', '10200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200004', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Affichage de DossierSinistre', 'Read DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreConsulterUC', '10200004','sinistreAT','sinistreAT', '10200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200005', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Suppression de DossierSinistre', 'Delete DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreSupprimerUC', '10200005','sinistreAT','sinistreAT', '10200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200006', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Annuler edition de DossierSinistre', 'Cancel Edit DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreAnnulerEditionUC', '10200006','sinistreAT','sinistreAT', '10200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200008', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Imprimer DossierSinistre', 'Print DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistrePrintUC', '10200008','sinistreAT','sinistreAT', '10200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200010', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Editer PDF de DossierSinistre', 'Edit PDF DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreEditerPdfUC', '10200010','sinistreAT','sinistreAT', '10200009');


insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200007', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.DossierSinistreVO', 1, 'Afficher Version de DossierSinistre', 'Version Detail of DossierSinistre', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.DossierSinistreAfficherVersionUC', '10200007','sinistreAT','sinistreAT', '10200009');








insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200000);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200001);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200002);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200003);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200004);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200005);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200006);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200008);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200010);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200007);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200009);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200109', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Consulter Module Quittance', 'Consulter Entite Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceRechercherUC', '10200109','sinistreAT','sinistreAT', '10299999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200100', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Ajout de Quittance', 'Add Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceAjouterUC', '10200100','sinistreAT','sinistreAT', '10200109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200101', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Modification de Quittance', 'Update Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceModifierUC', '10200101','sinistreAT','sinistreAT', '10200109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200102', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Edition de Quittance', 'Edit Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceEditerUC', '10200102','sinistreAT','sinistreAT', '10200109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200103', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Recherche de Quittance', 'Search Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceRechercherUC', '10200103','sinistreAT','sinistreAT', '10200109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200104', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Affichage de Quittance', 'Read Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceConsulterUC', '10200104','sinistreAT','sinistreAT', '10200109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200105', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Suppression de Quittance', 'Delete Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceSupprimerUC', '10200105','sinistreAT','sinistreAT', '10200109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200106', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Annuler edition de Quittance', 'Cancel Edit Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceAnnulerEditionUC', '10200106','sinistreAT','sinistreAT', '10200109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200108', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Imprimer Quittance', 'Print Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittancePrintUC', '10200108','sinistreAT','sinistreAT', '10200109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200110', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.QuittanceVO', 1, 'Editer PDF de Quittance', 'Edit PDF Quittance', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.QuittanceEditerPdfUC', '10200110','sinistreAT','sinistreAT', '10200109');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200100);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200101);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200102);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200103);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200104);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200105);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200106);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200108);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200110);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200109);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200209', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Consulter Module Ordonnancement', 'Consulter Entite Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementRechercherUC', '10200209','sinistreAT','sinistreAT', '10299999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200200', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Ajout de Ordonnancement', 'Add Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementAjouterUC', '10200200','sinistreAT','sinistreAT', '10200209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200201', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Modification de Ordonnancement', 'Update Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementModifierUC', '10200201','sinistreAT','sinistreAT', '10200209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200202', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Edition de Ordonnancement', 'Edit Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementEditerUC', '10200202','sinistreAT','sinistreAT', '10200209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200203', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Recherche de Ordonnancement', 'Search Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementRechercherUC', '10200203','sinistreAT','sinistreAT', '10200209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200204', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Affichage de Ordonnancement', 'Read Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementConsulterUC', '10200204','sinistreAT','sinistreAT', '10200209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200205', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Suppression de Ordonnancement', 'Delete Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementSupprimerUC', '10200205','sinistreAT','sinistreAT', '10200209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200206', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Annuler edition de Ordonnancement', 'Cancel Edit Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementAnnulerEditionUC', '10200206','sinistreAT','sinistreAT', '10200209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200208', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Imprimer Ordonnancement', 'Print Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementPrintUC', '10200208','sinistreAT','sinistreAT', '10200209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('10200210', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.valueobjects.OrdonnancementVO', 1, 'Editer PDF de Ordonnancement', 'Edit PDF Ordonnancement', 'eai.sinistreAT.sinistreAT.dossierSinistreAT.metier.services.OrdonnancementEditerPdfUC', '10200210','sinistreAT','sinistreAT', '10200209');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200200);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200201);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200202);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200203);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200204);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200205);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200206);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200208);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200210);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 10200209);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('299999', '-', 1, 'Consulter module GestionDesDossiersGSR', 'Consulter module GestionDesDossiersGSR', '-', '299999','sinistreAT','sinistreAT','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 299999);

// MODULE 2: GestionDesDossiersGSR 

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20199999', '-', 1, 'Consulter module DossierRente', 'Consulter module DossierRente', '-', '20199999','sinistreAT','sinistreAT', '299999');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20199999);

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('1985110918', '-', 0, 'List Action User', 'List Action User', 'client.sipic.sample.metier.services.ListActionUserUC', '1985110918','sipic','sipic','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 1985110918);


// MODULE 201: DossierRente 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100009', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Consulter Module Tuteur', 'Consulter Entite Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurRechercherUC', '20100009','sinistreAT','sinistreAT', '20199999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100000', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Ajout de Tuteur', 'Add Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurAjouterUC', '20100000','sinistreAT','sinistreAT', '20100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100001', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Modification de Tuteur', 'Update Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurModifierUC', '20100001','sinistreAT','sinistreAT', '20100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100002', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Edition de Tuteur', 'Edit Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurEditerUC', '20100002','sinistreAT','sinistreAT', '20100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100003', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Recherche de Tuteur', 'Search Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurRechercherUC', '20100003','sinistreAT','sinistreAT', '20100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100004', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Affichage de Tuteur', 'Read Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurConsulterUC', '20100004','sinistreAT','sinistreAT', '20100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100005', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Suppression de Tuteur', 'Delete Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurSupprimerUC', '20100005','sinistreAT','sinistreAT', '20100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100006', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Annuler edition de Tuteur', 'Cancel Edit Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurAnnulerEditionUC', '20100006','sinistreAT','sinistreAT', '20100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100008', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Imprimer Tuteur', 'Print Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurPrintUC', '20100008','sinistreAT','sinistreAT', '20100009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100010', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Editer PDF de Tuteur', 'Edit PDF Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurEditerPdfUC', '20100010','sinistreAT','sinistreAT', '20100009');


insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100007', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.TuteurVO', 1, 'Afficher Version de Tuteur', 'Version Detail of Tuteur', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.TuteurAfficherVersionUC', '20100007','sinistreAT','sinistreAT', '20100009');








insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100000);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100001);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100002);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100003);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100004);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100005);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100006);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100008);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100010);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100007);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100009);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100109', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Consulter Module DossierRente', 'Consulter Entite DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteRechercherUC', '20100109','sinistreAT','sinistreAT', '20199999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100100', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Ajout de DossierRente', 'Add DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteAjouterUC', '20100100','sinistreAT','sinistreAT', '20100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100101', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Modification de DossierRente', 'Update DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteModifierUC', '20100101','sinistreAT','sinistreAT', '20100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100102', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Edition de DossierRente', 'Edit DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteEditerUC', '20100102','sinistreAT','sinistreAT', '20100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100103', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Recherche de DossierRente', 'Search DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteRechercherUC', '20100103','sinistreAT','sinistreAT', '20100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100104', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Affichage de DossierRente', 'Read DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteConsulterUC', '20100104','sinistreAT','sinistreAT', '20100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100105', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Suppression de DossierRente', 'Delete DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteSupprimerUC', '20100105','sinistreAT','sinistreAT', '20100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100106', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Annuler edition de DossierRente', 'Cancel Edit DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteAnnulerEditionUC', '20100106','sinistreAT','sinistreAT', '20100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100108', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Imprimer DossierRente', 'Print DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRentePrintUC', '20100108','sinistreAT','sinistreAT', '20100109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100110', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Editer PDF de DossierRente', 'Edit PDF DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteEditerPdfUC', '20100110','sinistreAT','sinistreAT', '20100109');


insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100107', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.DossierRenteVO', 1, 'Afficher Version de DossierRente', 'Version Detail of DossierRente', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.DossierRenteAfficherVersionUC', '20100107','sinistreAT','sinistreAT', '20100109');








insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100100);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100101);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100102);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100103);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100104);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100105);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100106);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100108);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100110);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100107);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100109);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100209', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Consulter Module Rentier', 'Consulter Entite Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierRechercherUC', '20100209','sinistreAT','sinistreAT', '20199999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100200', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Ajout de Rentier', 'Add Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierAjouterUC', '20100200','sinistreAT','sinistreAT', '20100209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100201', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Modification de Rentier', 'Update Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierModifierUC', '20100201','sinistreAT','sinistreAT', '20100209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100202', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Edition de Rentier', 'Edit Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierEditerUC', '20100202','sinistreAT','sinistreAT', '20100209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100203', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Recherche de Rentier', 'Search Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierRechercherUC', '20100203','sinistreAT','sinistreAT', '20100209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100204', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Affichage de Rentier', 'Read Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierConsulterUC', '20100204','sinistreAT','sinistreAT', '20100209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100205', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Suppression de Rentier', 'Delete Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierSupprimerUC', '20100205','sinistreAT','sinistreAT', '20100209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100206', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Annuler edition de Rentier', 'Cancel Edit Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierAnnulerEditionUC', '20100206','sinistreAT','sinistreAT', '20100209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100208', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Imprimer Rentier', 'Print Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierPrintUC', '20100208','sinistreAT','sinistreAT', '20100209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100210', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Editer PDF de Rentier', 'Edit PDF Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierEditerPdfUC', '20100210','sinistreAT','sinistreAT', '20100209');


insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100207', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.RentierVO', 1, 'Afficher Version de Rentier', 'Version Detail of Rentier', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.RentierAfficherVersionUC', '20100207','sinistreAT','sinistreAT', '20100209');








insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100200);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100201);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100202);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100203);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100204);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100205);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100206);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100208);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100210);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100207);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100209);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100309', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Consulter Module Certificats', 'Consulter Entite Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsRechercherUC', '20100309','sinistreAT','sinistreAT', '20199999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100300', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Ajout de Certificats', 'Add Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsAjouterUC', '20100300','sinistreAT','sinistreAT', '20100309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100301', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Modification de Certificats', 'Update Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsModifierUC', '20100301','sinistreAT','sinistreAT', '20100309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100302', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Edition de Certificats', 'Edit Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsEditerUC', '20100302','sinistreAT','sinistreAT', '20100309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100303', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Recherche de Certificats', 'Search Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsRechercherUC', '20100303','sinistreAT','sinistreAT', '20100309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100304', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Affichage de Certificats', 'Read Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsConsulterUC', '20100304','sinistreAT','sinistreAT', '20100309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100305', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Suppression de Certificats', 'Delete Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsSupprimerUC', '20100305','sinistreAT','sinistreAT', '20100309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100306', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Annuler edition de Certificats', 'Cancel Edit Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsAnnulerEditionUC', '20100306','sinistreAT','sinistreAT', '20100309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100308', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Imprimer Certificats', 'Print Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsPrintUC', '20100308','sinistreAT','sinistreAT', '20100309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100310', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.CertificatsVO', 1, 'Editer PDF de Certificats', 'Edit PDF Certificats', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.CertificatsEditerPdfUC', '20100310','sinistreAT','sinistreAT', '20100309');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100300);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100301);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100302);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100303);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100304);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100305);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100306);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100308);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100310);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100309);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100409', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Consulter Module Protheses', 'Consulter Entite Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesRechercherUC', '20100409','sinistreAT','sinistreAT', '20199999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100400', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Ajout de Protheses', 'Add Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesAjouterUC', '20100400','sinistreAT','sinistreAT', '20100409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100401', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Modification de Protheses', 'Update Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesModifierUC', '20100401','sinistreAT','sinistreAT', '20100409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100402', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Edition de Protheses', 'Edit Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesEditerUC', '20100402','sinistreAT','sinistreAT', '20100409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100403', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Recherche de Protheses', 'Search Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesRechercherUC', '20100403','sinistreAT','sinistreAT', '20100409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100404', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Affichage de Protheses', 'Read Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesConsulterUC', '20100404','sinistreAT','sinistreAT', '20100409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100405', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Suppression de Protheses', 'Delete Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesSupprimerUC', '20100405','sinistreAT','sinistreAT', '20100409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100406', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Annuler edition de Protheses', 'Cancel Edit Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesAnnulerEditionUC', '20100406','sinistreAT','sinistreAT', '20100409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100408', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Imprimer Protheses', 'Print Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesPrintUC', '20100408','sinistreAT','sinistreAT', '20100409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100410', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Editer PDF de Protheses', 'Edit PDF Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesEditerPdfUC', '20100410','sinistreAT','sinistreAT', '20100409');


insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20100407', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.valueobjects.ProthesesVO', 1, 'Afficher Version de Protheses', 'Version Detail of Protheses', 'eai.sinistreAT.gestionDesDossiersGSR.dossierRente.metier.services.ProthesesAfficherVersionUC', '20100407','sinistreAT','sinistreAT', '20100409');








insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100400);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100401);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100402);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100403);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100404);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100405);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100406);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100408);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100410);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100407);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20100409);

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20299999', '-', 1, 'Consulter module Mouvements', 'Consulter module Mouvements', '-', '20299999','sinistreAT','sinistreAT', '299999');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20299999);

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('1985110918', '-', 0, 'List Action User', 'List Action User', 'client.sipic.sample.metier.services.ListActionUserUC', '1985110918','sipic','sipic','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 1985110918);


// MODULE 202: Mouvements 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200009', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Consulter Module ConsomReserveRente', 'Consulter Entite ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteRechercherUC', '20200009','sinistreAT','sinistreAT', '20299999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200000', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Ajout de ConsomReserveRente', 'Add ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteAjouterUC', '20200000','sinistreAT','sinistreAT', '20200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200001', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Modification de ConsomReserveRente', 'Update ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteModifierUC', '20200001','sinistreAT','sinistreAT', '20200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200002', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Edition de ConsomReserveRente', 'Edit ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteEditerUC', '20200002','sinistreAT','sinistreAT', '20200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200003', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Recherche de ConsomReserveRente', 'Search ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteRechercherUC', '20200003','sinistreAT','sinistreAT', '20200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200004', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Affichage de ConsomReserveRente', 'Read ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteConsulterUC', '20200004','sinistreAT','sinistreAT', '20200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200005', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Suppression de ConsomReserveRente', 'Delete ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteSupprimerUC', '20200005','sinistreAT','sinistreAT', '20200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200006', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Annuler edition de ConsomReserveRente', 'Cancel Edit ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteAnnulerEditionUC', '20200006','sinistreAT','sinistreAT', '20200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200008', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Imprimer ConsomReserveRente', 'Print ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRentePrintUC', '20200008','sinistreAT','sinistreAT', '20200009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20200010', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.valueobjects.ConsomReserveRenteVO', 1, 'Editer PDF de ConsomReserveRente', 'Edit PDF ConsomReserveRente', 'eai.sinistreAT.gestionDesDossiersGSR.mouvements.metier.services.ConsomReserveRenteEditerPdfUC', '20200010','sinistreAT','sinistreAT', '20200009');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200000);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200001);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200002);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200003);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200004);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200005);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200006);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200008);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200010);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20200009);

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20399999', '-', 1, 'Consulter module Reglement', 'Consulter module Reglement', '-', '20399999','sinistreAT','sinistreAT', '299999');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20399999);

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('1985110918', '-', 0, 'List Action User', 'List Action User', 'client.sipic.sample.metier.services.ListActionUserUC', '1985110918','sipic','sipic','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 1985110918);


// MODULE 203: Reglement 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300009', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Consulter Module ModePayement', 'Consulter Entite ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementRechercherUC', '20300009','sinistreAT','sinistreAT', '20399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300000', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Ajout de ModePayement', 'Add ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementAjouterUC', '20300000','sinistreAT','sinistreAT', '20300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300001', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Modification de ModePayement', 'Update ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementModifierUC', '20300001','sinistreAT','sinistreAT', '20300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300002', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Edition de ModePayement', 'Edit ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementEditerUC', '20300002','sinistreAT','sinistreAT', '20300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300003', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Recherche de ModePayement', 'Search ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementRechercherUC', '20300003','sinistreAT','sinistreAT', '20300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300004', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Affichage de ModePayement', 'Read ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementConsulterUC', '20300004','sinistreAT','sinistreAT', '20300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300005', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Suppression de ModePayement', 'Delete ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementSupprimerUC', '20300005','sinistreAT','sinistreAT', '20300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300006', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Annuler edition de ModePayement', 'Cancel Edit ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementAnnulerEditionUC', '20300006','sinistreAT','sinistreAT', '20300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300008', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Imprimer ModePayement', 'Print ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementPrintUC', '20300008','sinistreAT','sinistreAT', '20300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300010', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Editer PDF de ModePayement', 'Edit PDF ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementEditerPdfUC', '20300010','sinistreAT','sinistreAT', '20300009');


insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300007', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.ModePayementVO', 1, 'Afficher Version de ModePayement', 'Version Detail of ModePayement', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.ModePayementAfficherVersionUC', '20300007','sinistreAT','sinistreAT', '20300009');








insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300000);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300001);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300002);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300003);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300004);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300005);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300006);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300008);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300010);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300007);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300009);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300109', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Consulter Module OrderesVirements', 'Consulter Entite OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsRechercherUC', '20300109','sinistreAT','sinistreAT', '20399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300100', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Ajout de OrderesVirements', 'Add OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsAjouterUC', '20300100','sinistreAT','sinistreAT', '20300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300101', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Modification de OrderesVirements', 'Update OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsModifierUC', '20300101','sinistreAT','sinistreAT', '20300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300102', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Edition de OrderesVirements', 'Edit OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsEditerUC', '20300102','sinistreAT','sinistreAT', '20300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300103', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Recherche de OrderesVirements', 'Search OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsRechercherUC', '20300103','sinistreAT','sinistreAT', '20300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300104', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Affichage de OrderesVirements', 'Read OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsConsulterUC', '20300104','sinistreAT','sinistreAT', '20300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300105', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Suppression de OrderesVirements', 'Delete OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsSupprimerUC', '20300105','sinistreAT','sinistreAT', '20300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300106', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Annuler edition de OrderesVirements', 'Cancel Edit OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsAnnulerEditionUC', '20300106','sinistreAT','sinistreAT', '20300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300108', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Imprimer OrderesVirements', 'Print OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsPrintUC', '20300108','sinistreAT','sinistreAT', '20300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('20300110', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.valueobjects.OrderesVirementsVO', 1, 'Editer PDF de OrderesVirements', 'Edit PDF OrderesVirements', 'eai.sinistreAT.gestionDesDossiersGSR.reglement.metier.services.OrderesVirementsEditerPdfUC', '20300110','sinistreAT','sinistreAT', '20300109');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300100);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300101);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300102);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300103);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300104);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300105);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300106);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300108);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300110);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 20300109);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('399999', '-', 1, 'Consulter module Parametrage', 'Consulter module Parametrage', '-', '399999','sinistreAT','sinistreAT','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 399999);

// MODULE 3: Parametrage 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300009', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Consulter Module Intermediaire', 'Consulter Entite Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireRechercherUC', '300009','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300000', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Ajout de Intermediaire', 'Add Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireAjouterUC', '300000','sinistreAT','sinistreAT', '300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300001', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Modification de Intermediaire', 'Update Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireModifierUC', '300001','sinistreAT','sinistreAT', '300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300002', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Edition de Intermediaire', 'Edit Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireEditerUC', '300002','sinistreAT','sinistreAT', '300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300003', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Recherche de Intermediaire', 'Search Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireRechercherUC', '300003','sinistreAT','sinistreAT', '300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300004', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Affichage de Intermediaire', 'Read Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireConsulterUC', '300004','sinistreAT','sinistreAT', '300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300005', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Suppression de Intermediaire', 'Delete Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireSupprimerUC', '300005','sinistreAT','sinistreAT', '300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300006', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Annuler edition de Intermediaire', 'Cancel Edit Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireAnnulerEditionUC', '300006','sinistreAT','sinistreAT', '300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300008', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Imprimer Intermediaire', 'Print Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediairePrintUC', '300008','sinistreAT','sinistreAT', '300009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300010', 'eai.sinistreAT.parametrage.metier.valueobjects.IntermediaireVO', 1, 'Editer PDF de Intermediaire', 'Edit PDF Intermediaire', 'eai.sinistreAT.parametrage.metier.services.IntermediaireEditerPdfUC', '300010','sinistreAT','sinistreAT', '300009');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300000);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300001);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300002);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300003);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300004);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300005);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300006);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300008);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300010);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300009);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300109', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Consulter Module AgenceBancaire', 'Consulter Entite AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireRechercherUC', '300109','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300100', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Ajout de AgenceBancaire', 'Add AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireAjouterUC', '300100','sinistreAT','sinistreAT', '300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300101', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Modification de AgenceBancaire', 'Update AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireModifierUC', '300101','sinistreAT','sinistreAT', '300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300102', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Edition de AgenceBancaire', 'Edit AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireEditerUC', '300102','sinistreAT','sinistreAT', '300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300103', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Recherche de AgenceBancaire', 'Search AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireRechercherUC', '300103','sinistreAT','sinistreAT', '300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300104', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Affichage de AgenceBancaire', 'Read AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireConsulterUC', '300104','sinistreAT','sinistreAT', '300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300105', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Suppression de AgenceBancaire', 'Delete AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireSupprimerUC', '300105','sinistreAT','sinistreAT', '300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300106', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Annuler edition de AgenceBancaire', 'Cancel Edit AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireAnnulerEditionUC', '300106','sinistreAT','sinistreAT', '300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300108', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Imprimer AgenceBancaire', 'Print AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancairePrintUC', '300108','sinistreAT','sinistreAT', '300109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300110', 'eai.sinistreAT.parametrage.metier.valueobjects.AgenceBancaireVO', 1, 'Editer PDF de AgenceBancaire', 'Edit PDF AgenceBancaire', 'eai.sinistreAT.parametrage.metier.services.AgenceBancaireEditerPdfUC', '300110','sinistreAT','sinistreAT', '300109');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300100);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300101);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300102);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300103);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300104);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300105);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300106);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300108);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300110);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300109);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300209', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Consulter Module Banques', 'Consulter Entite Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesRechercherUC', '300209','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300200', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Ajout de Banques', 'Add Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesAjouterUC', '300200','sinistreAT','sinistreAT', '300209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300201', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Modification de Banques', 'Update Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesModifierUC', '300201','sinistreAT','sinistreAT', '300209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300202', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Edition de Banques', 'Edit Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesEditerUC', '300202','sinistreAT','sinistreAT', '300209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300203', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Recherche de Banques', 'Search Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesRechercherUC', '300203','sinistreAT','sinistreAT', '300209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300204', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Affichage de Banques', 'Read Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesConsulterUC', '300204','sinistreAT','sinistreAT', '300209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300205', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Suppression de Banques', 'Delete Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesSupprimerUC', '300205','sinistreAT','sinistreAT', '300209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300206', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Annuler edition de Banques', 'Cancel Edit Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesAnnulerEditionUC', '300206','sinistreAT','sinistreAT', '300209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300208', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Imprimer Banques', 'Print Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesPrintUC', '300208','sinistreAT','sinistreAT', '300209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300210', 'eai.sinistreAT.parametrage.metier.valueobjects.BanquesVO', 1, 'Editer PDF de Banques', 'Edit PDF Banques', 'eai.sinistreAT.parametrage.metier.services.BanquesEditerPdfUC', '300210','sinistreAT','sinistreAT', '300209');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300200);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300201);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300202);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300203);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300204);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300205);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300206);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300208);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300210);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300209);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300309', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Consulter Module CentreProthese', 'Consulter Entite CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseRechercherUC', '300309','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300300', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Ajout de CentreProthese', 'Add CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseAjouterUC', '300300','sinistreAT','sinistreAT', '300309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300301', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Modification de CentreProthese', 'Update CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseModifierUC', '300301','sinistreAT','sinistreAT', '300309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300302', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Edition de CentreProthese', 'Edit CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseEditerUC', '300302','sinistreAT','sinistreAT', '300309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300303', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Recherche de CentreProthese', 'Search CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseRechercherUC', '300303','sinistreAT','sinistreAT', '300309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300304', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Affichage de CentreProthese', 'Read CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseConsulterUC', '300304','sinistreAT','sinistreAT', '300309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300305', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Suppression de CentreProthese', 'Delete CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseSupprimerUC', '300305','sinistreAT','sinistreAT', '300309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300306', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Annuler edition de CentreProthese', 'Cancel Edit CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseAnnulerEditionUC', '300306','sinistreAT','sinistreAT', '300309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300308', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Imprimer CentreProthese', 'Print CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProthesePrintUC', '300308','sinistreAT','sinistreAT', '300309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300310', 'eai.sinistreAT.parametrage.metier.valueobjects.CentreProtheseVO', 1, 'Editer PDF de CentreProthese', 'Edit PDF CentreProthese', 'eai.sinistreAT.parametrage.metier.services.CentreProtheseEditerPdfUC', '300310','sinistreAT','sinistreAT', '300309');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300300);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300301);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300302);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300303);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300304);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300305);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300306);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300308);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300310);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300309);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300409', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Consulter Module DegreParente', 'Consulter Entite DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteRechercherUC', '300409','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300400', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Ajout de DegreParente', 'Add DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteAjouterUC', '300400','sinistreAT','sinistreAT', '300409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300401', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Modification de DegreParente', 'Update DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteModifierUC', '300401','sinistreAT','sinistreAT', '300409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300402', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Edition de DegreParente', 'Edit DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteEditerUC', '300402','sinistreAT','sinistreAT', '300409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300403', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Recherche de DegreParente', 'Search DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteRechercherUC', '300403','sinistreAT','sinistreAT', '300409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300404', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Affichage de DegreParente', 'Read DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteConsulterUC', '300404','sinistreAT','sinistreAT', '300409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300405', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Suppression de DegreParente', 'Delete DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteSupprimerUC', '300405','sinistreAT','sinistreAT', '300409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300406', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Annuler edition de DegreParente', 'Cancel Edit DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteAnnulerEditionUC', '300406','sinistreAT','sinistreAT', '300409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300408', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Imprimer DegreParente', 'Print DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParentePrintUC', '300408','sinistreAT','sinistreAT', '300409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300410', 'eai.sinistreAT.parametrage.metier.valueobjects.DegreParenteVO', 1, 'Editer PDF de DegreParente', 'Edit PDF DegreParente', 'eai.sinistreAT.parametrage.metier.services.DegreParenteEditerPdfUC', '300410','sinistreAT','sinistreAT', '300409');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300400);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300401);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300402);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300403);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300404);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300405);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300406);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300408);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300410);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300409);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300509', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Consulter Module EtatRentier', 'Consulter Entite EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierRechercherUC', '300509','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300500', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Ajout de EtatRentier', 'Add EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierAjouterUC', '300500','sinistreAT','sinistreAT', '300509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300501', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Modification de EtatRentier', 'Update EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierModifierUC', '300501','sinistreAT','sinistreAT', '300509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300502', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Edition de EtatRentier', 'Edit EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierEditerUC', '300502','sinistreAT','sinistreAT', '300509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300503', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Recherche de EtatRentier', 'Search EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierRechercherUC', '300503','sinistreAT','sinistreAT', '300509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300504', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Affichage de EtatRentier', 'Read EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierConsulterUC', '300504','sinistreAT','sinistreAT', '300509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300505', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Suppression de EtatRentier', 'Delete EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierSupprimerUC', '300505','sinistreAT','sinistreAT', '300509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300506', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Annuler edition de EtatRentier', 'Cancel Edit EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierAnnulerEditionUC', '300506','sinistreAT','sinistreAT', '300509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300508', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Imprimer EtatRentier', 'Print EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierPrintUC', '300508','sinistreAT','sinistreAT', '300509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300510', 'eai.sinistreAT.parametrage.metier.valueobjects.EtatRentierVO', 1, 'Editer PDF de EtatRentier', 'Edit PDF EtatRentier', 'eai.sinistreAT.parametrage.metier.services.EtatRentierEditerPdfUC', '300510','sinistreAT','sinistreAT', '300509');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300500);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300501);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300502);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300503);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300504);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300505);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300506);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300508);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300510);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300509);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300609', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Consulter Module MotifComplement', 'Consulter Entite MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementRechercherUC', '300609','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300600', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Ajout de MotifComplement', 'Add MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementAjouterUC', '300600','sinistreAT','sinistreAT', '300609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300601', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Modification de MotifComplement', 'Update MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementModifierUC', '300601','sinistreAT','sinistreAT', '300609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300602', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Edition de MotifComplement', 'Edit MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementEditerUC', '300602','sinistreAT','sinistreAT', '300609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300603', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Recherche de MotifComplement', 'Search MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementRechercherUC', '300603','sinistreAT','sinistreAT', '300609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300604', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Affichage de MotifComplement', 'Read MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementConsulterUC', '300604','sinistreAT','sinistreAT', '300609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300605', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Suppression de MotifComplement', 'Delete MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementSupprimerUC', '300605','sinistreAT','sinistreAT', '300609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300606', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Annuler edition de MotifComplement', 'Cancel Edit MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementAnnulerEditionUC', '300606','sinistreAT','sinistreAT', '300609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300608', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Imprimer MotifComplement', 'Print MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementPrintUC', '300608','sinistreAT','sinistreAT', '300609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300610', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifComplementVO', 1, 'Editer PDF de MotifComplement', 'Edit PDF MotifComplement', 'eai.sinistreAT.parametrage.metier.services.MotifComplementEditerPdfUC', '300610','sinistreAT','sinistreAT', '300609');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300600);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300601);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300602);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300603);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300604);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300605);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300606);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300608);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300610);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300609);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300709', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Consulter Module MotifEtat', 'Consulter Entite MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatRechercherUC', '300709','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300700', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Ajout de MotifEtat', 'Add MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatAjouterUC', '300700','sinistreAT','sinistreAT', '300709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300701', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Modification de MotifEtat', 'Update MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatModifierUC', '300701','sinistreAT','sinistreAT', '300709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300702', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Edition de MotifEtat', 'Edit MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatEditerUC', '300702','sinistreAT','sinistreAT', '300709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300703', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Recherche de MotifEtat', 'Search MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatRechercherUC', '300703','sinistreAT','sinistreAT', '300709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300704', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Affichage de MotifEtat', 'Read MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatConsulterUC', '300704','sinistreAT','sinistreAT', '300709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300705', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Suppression de MotifEtat', 'Delete MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatSupprimerUC', '300705','sinistreAT','sinistreAT', '300709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300706', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Annuler edition de MotifEtat', 'Cancel Edit MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatAnnulerEditionUC', '300706','sinistreAT','sinistreAT', '300709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300708', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Imprimer MotifEtat', 'Print MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatPrintUC', '300708','sinistreAT','sinistreAT', '300709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300710', 'eai.sinistreAT.parametrage.metier.valueobjects.MotifEtatVO', 1, 'Editer PDF de MotifEtat', 'Edit PDF MotifEtat', 'eai.sinistreAT.parametrage.metier.services.MotifEtatEditerPdfUC', '300710','sinistreAT','sinistreAT', '300709');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300700);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300701);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300702);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300703);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300704);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300705);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300706);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300708);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300710);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300709);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300809', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Consulter Module NatureProthese', 'Consulter Entite NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseRechercherUC', '300809','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300800', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Ajout de NatureProthese', 'Add NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseAjouterUC', '300800','sinistreAT','sinistreAT', '300809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300801', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Modification de NatureProthese', 'Update NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseModifierUC', '300801','sinistreAT','sinistreAT', '300809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300802', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Edition de NatureProthese', 'Edit NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseEditerUC', '300802','sinistreAT','sinistreAT', '300809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300803', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Recherche de NatureProthese', 'Search NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseRechercherUC', '300803','sinistreAT','sinistreAT', '300809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300804', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Affichage de NatureProthese', 'Read NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseConsulterUC', '300804','sinistreAT','sinistreAT', '300809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300805', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Suppression de NatureProthese', 'Delete NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseSupprimerUC', '300805','sinistreAT','sinistreAT', '300809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300806', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Annuler edition de NatureProthese', 'Cancel Edit NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseAnnulerEditionUC', '300806','sinistreAT','sinistreAT', '300809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300808', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Imprimer NatureProthese', 'Print NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProthesePrintUC', '300808','sinistreAT','sinistreAT', '300809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300810', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureProtheseVO', 1, 'Editer PDF de NatureProthese', 'Edit PDF NatureProthese', 'eai.sinistreAT.parametrage.metier.services.NatureProtheseEditerPdfUC', '300810','sinistreAT','sinistreAT', '300809');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300800);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300801);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300802);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300803);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300804);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300805);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300806);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300808);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300810);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300809);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300909', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Consulter Module NatureQuittance', 'Consulter Entite NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceRechercherUC', '300909','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300900', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Ajout de NatureQuittance', 'Add NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceAjouterUC', '300900','sinistreAT','sinistreAT', '300909');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300901', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Modification de NatureQuittance', 'Update NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceModifierUC', '300901','sinistreAT','sinistreAT', '300909');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300902', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Edition de NatureQuittance', 'Edit NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceEditerUC', '300902','sinistreAT','sinistreAT', '300909');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300903', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Recherche de NatureQuittance', 'Search NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceRechercherUC', '300903','sinistreAT','sinistreAT', '300909');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300904', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Affichage de NatureQuittance', 'Read NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceConsulterUC', '300904','sinistreAT','sinistreAT', '300909');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300905', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Suppression de NatureQuittance', 'Delete NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceSupprimerUC', '300905','sinistreAT','sinistreAT', '300909');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300906', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Annuler edition de NatureQuittance', 'Cancel Edit NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceAnnulerEditionUC', '300906','sinistreAT','sinistreAT', '300909');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300908', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Imprimer NatureQuittance', 'Print NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittancePrintUC', '300908','sinistreAT','sinistreAT', '300909');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('300910', 'eai.sinistreAT.parametrage.metier.valueobjects.NatureQuittanceVO', 1, 'Editer PDF de NatureQuittance', 'Edit PDF NatureQuittance', 'eai.sinistreAT.parametrage.metier.services.NatureQuittanceEditerPdfUC', '300910','sinistreAT','sinistreAT', '300909');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300900);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300901);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300902);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300903);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300904);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300905);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300906);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300908);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300910);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 300909);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001009', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Consulter Module Pays', 'Consulter Entite Pays', 'eai.sinistreAT.parametrage.metier.services.PaysRechercherUC', '3001009','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001000', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Ajout de Pays', 'Add Pays', 'eai.sinistreAT.parametrage.metier.services.PaysAjouterUC', '3001000','sinistreAT','sinistreAT', '3001009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001001', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Modification de Pays', 'Update Pays', 'eai.sinistreAT.parametrage.metier.services.PaysModifierUC', '3001001','sinistreAT','sinistreAT', '3001009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001002', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Edition de Pays', 'Edit Pays', 'eai.sinistreAT.parametrage.metier.services.PaysEditerUC', '3001002','sinistreAT','sinistreAT', '3001009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001003', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Recherche de Pays', 'Search Pays', 'eai.sinistreAT.parametrage.metier.services.PaysRechercherUC', '3001003','sinistreAT','sinistreAT', '3001009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001004', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Affichage de Pays', 'Read Pays', 'eai.sinistreAT.parametrage.metier.services.PaysConsulterUC', '3001004','sinistreAT','sinistreAT', '3001009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001005', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Suppression de Pays', 'Delete Pays', 'eai.sinistreAT.parametrage.metier.services.PaysSupprimerUC', '3001005','sinistreAT','sinistreAT', '3001009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001006', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Annuler edition de Pays', 'Cancel Edit Pays', 'eai.sinistreAT.parametrage.metier.services.PaysAnnulerEditionUC', '3001006','sinistreAT','sinistreAT', '3001009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001008', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Imprimer Pays', 'Print Pays', 'eai.sinistreAT.parametrage.metier.services.PaysPrintUC', '3001008','sinistreAT','sinistreAT', '3001009');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('3001010', 'eai.sinistreAT.parametrage.metier.valueobjects.PaysVO', 1, 'Editer PDF de Pays', 'Edit PDF Pays', 'eai.sinistreAT.parametrage.metier.services.PaysEditerPdfUC', '3001010','sinistreAT','sinistreAT', '3001009');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001000);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001001);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001002);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001003);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001004);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001005);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001006);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001008);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001010);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 3001009);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301109', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Consulter Module SituationRentier', 'Consulter Entite SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierRechercherUC', '301109','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301100', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Ajout de SituationRentier', 'Add SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierAjouterUC', '301100','sinistreAT','sinistreAT', '301109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301101', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Modification de SituationRentier', 'Update SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierModifierUC', '301101','sinistreAT','sinistreAT', '301109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301102', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Edition de SituationRentier', 'Edit SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierEditerUC', '301102','sinistreAT','sinistreAT', '301109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301103', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Recherche de SituationRentier', 'Search SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierRechercherUC', '301103','sinistreAT','sinistreAT', '301109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301104', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Affichage de SituationRentier', 'Read SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierConsulterUC', '301104','sinistreAT','sinistreAT', '301109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301105', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Suppression de SituationRentier', 'Delete SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierSupprimerUC', '301105','sinistreAT','sinistreAT', '301109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301106', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Annuler edition de SituationRentier', 'Cancel Edit SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierAnnulerEditionUC', '301106','sinistreAT','sinistreAT', '301109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301108', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Imprimer SituationRentier', 'Print SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierPrintUC', '301108','sinistreAT','sinistreAT', '301109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301110', 'eai.sinistreAT.parametrage.metier.valueobjects.SituationRentierVO', 1, 'Editer PDF de SituationRentier', 'Edit PDF SituationRentier', 'eai.sinistreAT.parametrage.metier.services.SituationRentierEditerPdfUC', '301110','sinistreAT','sinistreAT', '301109');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301100);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301101);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301102);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301103);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301104);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301105);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301106);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301108);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301110);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301109);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301209', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Consulter Module Societe', 'Consulter Entite Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteRechercherUC', '301209','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301200', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Ajout de Societe', 'Add Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteAjouterUC', '301200','sinistreAT','sinistreAT', '301209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301201', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Modification de Societe', 'Update Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteModifierUC', '301201','sinistreAT','sinistreAT', '301209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301202', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Edition de Societe', 'Edit Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteEditerUC', '301202','sinistreAT','sinistreAT', '301209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301203', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Recherche de Societe', 'Search Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteRechercherUC', '301203','sinistreAT','sinistreAT', '301209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301204', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Affichage de Societe', 'Read Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteConsulterUC', '301204','sinistreAT','sinistreAT', '301209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301205', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Suppression de Societe', 'Delete Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteSupprimerUC', '301205','sinistreAT','sinistreAT', '301209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301206', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Annuler edition de Societe', 'Cancel Edit Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteAnnulerEditionUC', '301206','sinistreAT','sinistreAT', '301209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301208', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Imprimer Societe', 'Print Societe', 'eai.sinistreAT.parametrage.metier.services.SocietePrintUC', '301208','sinistreAT','sinistreAT', '301209');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301210', 'eai.sinistreAT.parametrage.metier.valueobjects.SocieteVO', 1, 'Editer PDF de Societe', 'Edit PDF Societe', 'eai.sinistreAT.parametrage.metier.services.SocieteEditerPdfUC', '301210','sinistreAT','sinistreAT', '301209');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301200);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301201);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301202);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301203);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301204);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301205);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301206);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301208);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301210);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301209);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301309', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Consulter Module Tribuneaux', 'Consulter Entite Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxRechercherUC', '301309','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301300', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Ajout de Tribuneaux', 'Add Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxAjouterUC', '301300','sinistreAT','sinistreAT', '301309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301301', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Modification de Tribuneaux', 'Update Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxModifierUC', '301301','sinistreAT','sinistreAT', '301309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301302', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Edition de Tribuneaux', 'Edit Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxEditerUC', '301302','sinistreAT','sinistreAT', '301309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301303', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Recherche de Tribuneaux', 'Search Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxRechercherUC', '301303','sinistreAT','sinistreAT', '301309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301304', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Affichage de Tribuneaux', 'Read Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxConsulterUC', '301304','sinistreAT','sinistreAT', '301309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301305', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Suppression de Tribuneaux', 'Delete Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxSupprimerUC', '301305','sinistreAT','sinistreAT', '301309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301306', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Annuler edition de Tribuneaux', 'Cancel Edit Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxAnnulerEditionUC', '301306','sinistreAT','sinistreAT', '301309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301308', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Imprimer Tribuneaux', 'Print Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxPrintUC', '301308','sinistreAT','sinistreAT', '301309');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301310', 'eai.sinistreAT.parametrage.metier.valueobjects.TribuneauxVO', 1, 'Editer PDF de Tribuneaux', 'Edit PDF Tribuneaux', 'eai.sinistreAT.parametrage.metier.services.TribuneauxEditerPdfUC', '301310','sinistreAT','sinistreAT', '301309');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301300);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301301);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301302);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301303);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301304);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301305);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301306);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301308);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301310);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301309);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301409', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Consulter Module TypeCertificat', 'Consulter Entite TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatRechercherUC', '301409','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301400', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Ajout de TypeCertificat', 'Add TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatAjouterUC', '301400','sinistreAT','sinistreAT', '301409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301401', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Modification de TypeCertificat', 'Update TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatModifierUC', '301401','sinistreAT','sinistreAT', '301409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301402', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Edition de TypeCertificat', 'Edit TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatEditerUC', '301402','sinistreAT','sinistreAT', '301409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301403', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Recherche de TypeCertificat', 'Search TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatRechercherUC', '301403','sinistreAT','sinistreAT', '301409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301404', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Affichage de TypeCertificat', 'Read TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatConsulterUC', '301404','sinistreAT','sinistreAT', '301409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301405', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Suppression de TypeCertificat', 'Delete TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatSupprimerUC', '301405','sinistreAT','sinistreAT', '301409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301406', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Annuler edition de TypeCertificat', 'Cancel Edit TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatAnnulerEditionUC', '301406','sinistreAT','sinistreAT', '301409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301408', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Imprimer TypeCertificat', 'Print TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatPrintUC', '301408','sinistreAT','sinistreAT', '301409');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301410', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeCertificatVO', 1, 'Editer PDF de TypeCertificat', 'Edit PDF TypeCertificat', 'eai.sinistreAT.parametrage.metier.services.TypeCertificatEditerPdfUC', '301410','sinistreAT','sinistreAT', '301409');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301400);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301401);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301402);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301403);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301404);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301405);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301406);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301408);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301410);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301409);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301509', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Consulter Module TypeCheque', 'Consulter Entite TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeRechercherUC', '301509','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301500', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Ajout de TypeCheque', 'Add TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeAjouterUC', '301500','sinistreAT','sinistreAT', '301509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301501', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Modification de TypeCheque', 'Update TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeModifierUC', '301501','sinistreAT','sinistreAT', '301509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301502', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Edition de TypeCheque', 'Edit TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeEditerUC', '301502','sinistreAT','sinistreAT', '301509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301503', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Recherche de TypeCheque', 'Search TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeRechercherUC', '301503','sinistreAT','sinistreAT', '301509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301504', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Affichage de TypeCheque', 'Read TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeConsulterUC', '301504','sinistreAT','sinistreAT', '301509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301505', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Suppression de TypeCheque', 'Delete TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeSupprimerUC', '301505','sinistreAT','sinistreAT', '301509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301506', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Annuler edition de TypeCheque', 'Cancel Edit TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeAnnulerEditionUC', '301506','sinistreAT','sinistreAT', '301509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301508', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Imprimer TypeCheque', 'Print TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequePrintUC', '301508','sinistreAT','sinistreAT', '301509');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301510', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeChequeVO', 1, 'Editer PDF de TypeCheque', 'Edit PDF TypeCheque', 'eai.sinistreAT.parametrage.metier.services.TypeChequeEditerPdfUC', '301510','sinistreAT','sinistreAT', '301509');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301500);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301501);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301502);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301503);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301504);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301505);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301506);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301508);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301510);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301509);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301609', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Consulter Module TypeMouvement', 'Consulter Entite TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementRechercherUC', '301609','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301600', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Ajout de TypeMouvement', 'Add TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementAjouterUC', '301600','sinistreAT','sinistreAT', '301609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301601', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Modification de TypeMouvement', 'Update TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementModifierUC', '301601','sinistreAT','sinistreAT', '301609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301602', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Edition de TypeMouvement', 'Edit TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementEditerUC', '301602','sinistreAT','sinistreAT', '301609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301603', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Recherche de TypeMouvement', 'Search TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementRechercherUC', '301603','sinistreAT','sinistreAT', '301609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301604', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Affichage de TypeMouvement', 'Read TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementConsulterUC', '301604','sinistreAT','sinistreAT', '301609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301605', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Suppression de TypeMouvement', 'Delete TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementSupprimerUC', '301605','sinistreAT','sinistreAT', '301609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301606', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Annuler edition de TypeMouvement', 'Cancel Edit TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementAnnulerEditionUC', '301606','sinistreAT','sinistreAT', '301609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301608', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Imprimer TypeMouvement', 'Print TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementPrintUC', '301608','sinistreAT','sinistreAT', '301609');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301610', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeMouvementVO', 1, 'Editer PDF de TypeMouvement', 'Edit PDF TypeMouvement', 'eai.sinistreAT.parametrage.metier.services.TypeMouvementEditerPdfUC', '301610','sinistreAT','sinistreAT', '301609');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301600);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301601);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301602);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301603);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301604);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301605);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301606);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301608);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301610);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301609);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301709', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Consulter Module TypeQuittance', 'Consulter Entite TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceRechercherUC', '301709','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301700', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Ajout de TypeQuittance', 'Add TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceAjouterUC', '301700','sinistreAT','sinistreAT', '301709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301701', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Modification de TypeQuittance', 'Update TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceModifierUC', '301701','sinistreAT','sinistreAT', '301709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301702', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Edition de TypeQuittance', 'Edit TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceEditerUC', '301702','sinistreAT','sinistreAT', '301709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301703', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Recherche de TypeQuittance', 'Search TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceRechercherUC', '301703','sinistreAT','sinistreAT', '301709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301704', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Affichage de TypeQuittance', 'Read TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceConsulterUC', '301704','sinistreAT','sinistreAT', '301709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301705', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Suppression de TypeQuittance', 'Delete TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceSupprimerUC', '301705','sinistreAT','sinistreAT', '301709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301706', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Annuler edition de TypeQuittance', 'Cancel Edit TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceAnnulerEditionUC', '301706','sinistreAT','sinistreAT', '301709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301708', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Imprimer TypeQuittance', 'Print TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittancePrintUC', '301708','sinistreAT','sinistreAT', '301709');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301710', 'eai.sinistreAT.parametrage.metier.valueobjects.TypeQuittanceVO', 1, 'Editer PDF de TypeQuittance', 'Edit PDF TypeQuittance', 'eai.sinistreAT.parametrage.metier.services.TypeQuittanceEditerPdfUC', '301710','sinistreAT','sinistreAT', '301709');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301700);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301701);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301702);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301703);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301704);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301705);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301706);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301708);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301710);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301709);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301809', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Consulter Module Ville', 'Consulter Entite Ville', 'eai.sinistreAT.parametrage.metier.services.VilleRechercherUC', '301809','sinistreAT','sinistreAT', '399999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301800', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Ajout de Ville', 'Add Ville', 'eai.sinistreAT.parametrage.metier.services.VilleAjouterUC', '301800','sinistreAT','sinistreAT', '301809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301801', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Modification de Ville', 'Update Ville', 'eai.sinistreAT.parametrage.metier.services.VilleModifierUC', '301801','sinistreAT','sinistreAT', '301809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301802', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Edition de Ville', 'Edit Ville', 'eai.sinistreAT.parametrage.metier.services.VilleEditerUC', '301802','sinistreAT','sinistreAT', '301809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301803', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Recherche de Ville', 'Search Ville', 'eai.sinistreAT.parametrage.metier.services.VilleRechercherUC', '301803','sinistreAT','sinistreAT', '301809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301804', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Affichage de Ville', 'Read Ville', 'eai.sinistreAT.parametrage.metier.services.VilleConsulterUC', '301804','sinistreAT','sinistreAT', '301809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301805', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Suppression de Ville', 'Delete Ville', 'eai.sinistreAT.parametrage.metier.services.VilleSupprimerUC', '301805','sinistreAT','sinistreAT', '301809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301806', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Annuler edition de Ville', 'Cancel Edit Ville', 'eai.sinistreAT.parametrage.metier.services.VilleAnnulerEditionUC', '301806','sinistreAT','sinistreAT', '301809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301808', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Imprimer Ville', 'Print Ville', 'eai.sinistreAT.parametrage.metier.services.VillePrintUC', '301808','sinistreAT','sinistreAT', '301809');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('301810', 'eai.sinistreAT.parametrage.metier.valueobjects.VilleVO', 1, 'Editer PDF de Ville', 'Edit PDF Ville', 'eai.sinistreAT.parametrage.metier.services.VilleEditerPdfUC', '301810','sinistreAT','sinistreAT', '301809');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301800);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301801);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301802);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301803);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301804);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301805);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301806);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301808);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301810);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 301809);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('499999', '-', 1, 'Consulter module Admin', 'Consulter module Admin', '-', '499999','sinistreAT','sinistreAT','');
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 499999);

// MODULE 4: Admin 
 
 
 

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400000', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Ajout de Utilisateur', 'Add Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurAjouterUC', '400000','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400001', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Modification de Utilisateur', 'Update Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurModifierUC', '400001','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400002', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Edition de Utilisateur', 'Edit Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurEditerUC', '400002','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400003', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Recherche de Utilisateur', 'Search Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurRechercherUC', '400003','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400004', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Affichage de Utilisateur', 'Read Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurConsulterUC', '400004','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400005', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Suppression de Utilisateur', 'Delete Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurSupprimerUC', '400005','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400006', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Annuler edition de Utilisateur', 'Cancel Edit Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurAnnulerEditionUC', '400006','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400008', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Imprimer Utilisateur', 'Print Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurPrintUC', '400008','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400010', 'eai.sinistreAT.admin.metier.valueobjects.UtilisateurVO', 1, 'Editer PDF de Utilisateur', 'Edit PDF Utilisateur', 'eai.sinistreAT.admin.metier.services.UtilisateurEditerPdfUC', '400010','sinistreAT','sinistreAT', '');
 
 
 





insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400000);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400001);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400002);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400003);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400004);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400005);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400006);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400008);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400010);
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400109', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Consulter Module Action', 'Consulter Entite Action', 'eai.sinistreAT.admin.metier.services.ActionRechercherUC', '400109','sinistreAT','sinistreAT', '499999');
 
 
 
 
 
 
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400100', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Ajout de Action', 'Add Action', 'eai.sinistreAT.admin.metier.services.ActionAjouterUC', '400100','sinistreAT','sinistreAT', '400109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400101', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Modification de Action', 'Update Action', 'eai.sinistreAT.admin.metier.services.ActionModifierUC', '400101','sinistreAT','sinistreAT', '400109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400102', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Edition de Action', 'Edit Action', 'eai.sinistreAT.admin.metier.services.ActionEditerUC', '400102','sinistreAT','sinistreAT', '400109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400103', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Recherche de Action', 'Search Action', 'eai.sinistreAT.admin.metier.services.ActionRechercherUC', '400103','sinistreAT','sinistreAT', '400109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400104', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Affichage de Action', 'Read Action', 'eai.sinistreAT.admin.metier.services.ActionConsulterUC', '400104','sinistreAT','sinistreAT', '400109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400105', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Suppression de Action', 'Delete Action', 'eai.sinistreAT.admin.metier.services.ActionSupprimerUC', '400105','sinistreAT','sinistreAT', '400109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400106', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Annuler edition de Action', 'Cancel Edit Action', 'eai.sinistreAT.admin.metier.services.ActionAnnulerEditionUC', '400106','sinistreAT','sinistreAT', '400109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400108', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Imprimer Action', 'Print Action', 'eai.sinistreAT.admin.metier.services.ActionPrintUC', '400108','sinistreAT','sinistreAT', '400109');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400110', 'eai.sinistreAT.admin.metier.valueobjects.ActionVO', 1, 'Editer PDF de Action', 'Edit PDF Action', 'eai.sinistreAT.admin.metier.services.ActionEditerPdfUC', '400110','sinistreAT','sinistreAT', '400109');










insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400100);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400101);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400102);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400103);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400104);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400105);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400106);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400108);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400110);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400109);
 
 
 

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400200', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Ajout de Role', 'Add Role', 'eai.sinistreAT.admin.metier.services.RoleAjouterUC', '400200','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400201', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Modification de Role', 'Update Role', 'eai.sinistreAT.admin.metier.services.RoleModifierUC', '400201','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400202', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Edition de Role', 'Edit Role', 'eai.sinistreAT.admin.metier.services.RoleEditerUC', '400202','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400203', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Recherche de Role', 'Search Role', 'eai.sinistreAT.admin.metier.services.RoleRechercherUC', '400203','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400204', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Affichage de Role', 'Read Role', 'eai.sinistreAT.admin.metier.services.RoleConsulterUC', '400204','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400205', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Suppression de Role', 'Delete Role', 'eai.sinistreAT.admin.metier.services.RoleSupprimerUC', '400205','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400206', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Annuler edition de Role', 'Cancel Edit Role', 'eai.sinistreAT.admin.metier.services.RoleAnnulerEditionUC', '400206','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400208', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Imprimer Role', 'Print Role', 'eai.sinistreAT.admin.metier.services.RolePrintUC', '400208','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400210', 'eai.sinistreAT.admin.metier.valueobjects.RoleVO', 1, 'Editer PDF de Role', 'Edit PDF Role', 'eai.sinistreAT.admin.metier.services.RoleEditerPdfUC', '400210','sinistreAT','sinistreAT', '');
 
 
 





insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400200);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400201);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400202);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400203);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400204);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400205);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400206);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400208);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400210);
 
 
 

insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400300', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Ajout de UnityOrganisationnelle', 'Add UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnelleAjouterUC', '400300','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400301', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Modification de UnityOrganisationnelle', 'Update UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnelleModifierUC', '400301','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400302', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Edition de UnityOrganisationnelle', 'Edit UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnelleEditerUC', '400302','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400303', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Recherche de UnityOrganisationnelle', 'Search UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnelleRechercherUC', '400303','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400304', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Affichage de UnityOrganisationnelle', 'Read UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnelleConsulterUC', '400304','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400305', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Suppression de UnityOrganisationnelle', 'Delete UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnelleSupprimerUC', '400305','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400306', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Annuler edition de UnityOrganisationnelle', 'Cancel Edit UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnelleAnnulerEditionUC', '400306','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400308', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Imprimer UnityOrganisationnelle', 'Print UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnellePrintUC', '400308','sinistreAT','sinistreAT', '');
insert into ITAR_SECURITY_ACTION (ID, VO_CLASS_NAME, SECURE, ACTION_DESCRIPTION, ACTION_NAME, ACTION_CLASS_NAME, ACTION_ID,MODULENAME,SOUSMODULENAME, ACTION_PARENT)
values ('400310', 'eai.sinistreAT.admin.metier.valueobjects.UnityOrganisationnelleVO', 1, 'Editer PDF de UnityOrganisationnelle', 'Edit PDF UnityOrganisationnelle', 'eai.sinistreAT.admin.metier.services.UnityOrganisationnelleEditerPdfUC', '400310','sinistreAT','sinistreAT', '');
 
 
 





insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400300);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400301);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400302);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400303);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400304);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400305);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400306);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400308);
insert into ITAR_ASSOC_SEC_ROLE_ACTION (IDROLE, IDACTION) values (1, 400310);
commit;
