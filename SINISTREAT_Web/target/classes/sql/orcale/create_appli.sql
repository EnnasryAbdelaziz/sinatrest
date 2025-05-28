
CREATE TABLE SIN_AYANTDROIT(
	id NUMBER(8) not null,
	date_naissance DATE  ,
	email VARCHAR2(250)  ,
	gsm VARCHAR2(250)  ,
	nationalite VARCHAR2(250)  ,
	nom VARCHAR2(250)  ,
	num_cin VARCHAR2(250)  ,
	numero_ordre_descendant VARCHAR2(250)  ,
	prenom VARCHAR2(250)  ,
	reserve_grave decimal(10,3)  ,
	rib VARCHAR2(250)  ,
	sexe VARCHAR2(250)  ,
	taux_rente decimal(10,3)  ,
	telephone VARCHAR2(250)  ,
	adresse VARCHAR2(250)  ,
	civilite VARCHAR2(250)  ,
	date_creation DATE  ,
	pays number(8)  ,
	degre_parente number(8)  ,
	SIN_VICTIME number(8)  ,
	CONSTRAINT PK_SIN_AYANTDROIT PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_SIN_AYANTDROIT
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE SIN_VICTIME(
	id NUMBER(8) not null,
	adresse VARCHAR2(250)  ,
	civilite VARCHAR2(250)  ,
	date_embauche DATE  ,
	date_naissance DATE  ,
	date_naiss_estime char(1)  ,
	deces char(1)  ,
	email VARCHAR2(250)  ,
	gsm VARCHAR2(250)  ,
	nationalite VARCHAR2(250)  ,
	nom VARCHAR2(250)  ,
	num_cin VARCHAR2(250)  ,
	num_cnss VARCHAR2(250)  ,
	prenom VARCHAR2(250)  ,
	profession VARCHAR2(250)  ,
	rib VARCHAR2(250)  ,
	salaire_an_estime char(1)  ,
	salaire_annuel decimal(10,3)  ,
	salaire_horaire decimal(10,3)  ,
	salaire_hor_estime char(1)  ,
	salaire_jour_estime char(1)  ,
	salaire_journalier decimal(10,3)  ,
	sexe VARCHAR2(250)  ,
	telephone VARCHAR2(250)  ,
	date_creation DATE  ,
	pays number(8)  ,
	CONSTRAINT PK_SIN_VICTIME PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_SIN_VICTIME
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE SIN_SINISTRE(
	id NUMBER(8) not null,
	garantie_touche VARCHAR2(250)  ,
	ipp_estime char(1)  ,
	is_evenementiel char(1)  ,
	is_recours char(1)  ,
	motif VARCHAR2(250)  ,
	observations VARCHAR2(250)  ,
	reference_intermediaire VARCHAR2(250)  ,
	code_garantie VARCHAR2(250)  ,
	code_intermediaire VARCHAR2(250)  ,
	ipp decimal(10,3)  ,
	itt decimal(10,3)  ,
	nom_client VARCHAR2(250)  ,
	nom_intermediaire VARCHAR2(250)  ,
	numero_grave VARCHAR2(250)  ,
	numero_police VARCHAR2(250)  ,
	numero_sinistre VARCHAR2(250) unique ,
	reserve_grave decimal(10,3)  ,
	reserve_ordinaire decimal(10,3)  ,
	reserve_prothese decimal(10,3)  ,
	salaire_utile decimal(10,3)  ,
	code_client VARCHAR2(250)  ,
	code_societe VARCHAR2(250)  ,
	date_creation DATE  ,
	salaire_Jugement VARCHAR2,
	CONSTRAINT PK_SIN_SINISTRE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_SIN_SINISTRE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE SINAT_REGLEMENT(
	id NUMBER(8) not null,
	date_echeance DATE  ,
	numero_quittance VARCHAR2(250)  ,
	date_creation DATE  ,
	rentier number(8)  ,
	TB_ordonnancement number(8)  ,
	CONSTRAINT PK_SINAT_REGLEMENT PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_SINAT_REGLEMENT
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE TB_ordonnancement(
	id NUMBER(8) not null,
	numero_ordonnancement VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_TB_ordonnancement PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_TB_ordonnancement
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSR_TUTEUR(
	id NUMBER(8) not null,
	id_tuteur decimal(10,3)  ,
	nom VARCHAR2(250)  ,
	numero_cin VARCHAR2(250)  ,
	lien_parente decimal(10,3)  ,
	adresse VARCHAR2(250)  ,
	code_postale decimal(10,3)  ,
	ville decimal(10,3)  ,
	pays decimal(10,3)  ,
	etat decimal(10,3)  ,
	date_etat DATE  ,
	validation char(1)  ,
	date_validation DATE  ,
	prenom VARCHAR2(250)  ,
	sexe VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_GSR_TUTEUR PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_TUTEUR
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSR_RENTE(
	id NUMBER(8) not null,
	compagnie_rente VARCHAR2(250)  ,
	numero_rente decimal(10,3)  ,
	id_dossier_rente decimal(10,3)  ,
	etat decimal(10,3)  ,
	date_etat DATE  ,
	date_validation DATE  ,
	validation char(1)  ,
	date_creation DATE  ,
	dossier_sinistre number(8)  ,
	CONSTRAINT PK_GSR_RENTE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_RENTE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSRRENTI_etatrenti(
	GSR_RENTIER number(8)    ,
	etat_rentier number(8)   
);


CREATE TABLE GSRRENTI_modepayem(
	GSR_RENTIER number(8)    ,
	mode_payement number(8)   
);


CREATE TABLE GSRRENTI_protheses(
	GSR_RENTIER number(8)    ,
	protheses number(8)   
);


CREATE TABLE GSRRENTI_certificats(
	GSR_RENTIER number(8)    ,
	certificats number(8)   
);


CREATE TABLE GSR_RENTIER(
	id NUMBER(8) not null,
	lien_parente decimal(10,3)  ,
	numero_cin VARCHAR2(250)  ,
	date_naissance DATE  ,
	nationalite decimal(10,3)  ,
	numero_telephone VARCHAR2(250)  ,
	numero_gsm VARCHAR2(250)  ,
	email VARCHAR2(250)  ,
	adresse VARCHAR2(250)  ,
	code_postale decimal(10,3)  ,
	ville decimal(10,3)  ,
	pays decimal(10,3)  ,
	prothese char(1)  ,
	situation_rentier decimal(10,3)  ,
	rentier_arisque char(1)  ,
	rente_conforme char(1)  ,
	etat_rente decimal(10,3)  ,
	date_etat DATE  ,
	validation char(1)  ,
	capital_constitutif decimal(10,3)  ,
	date_constitution DATE  ,
	date_depart_rente DATE  ,
	ipp_taux_rente decimal(10,3)  ,
	mode_payement decimal(10,3)  ,
	reserve_mathematique decimal(10,3)  ,
	date_validation DATE  ,
	salaire_utile decimal(10,3)  ,
	approbation_quittance char(1)  ,
	montant_rente_trimest decimal(10,3)  ,
	periodicite VARCHAR2(250)  ,
	tuteur char(1)  ,
	id_rentier decimal(10,3)  ,
	civilite VARCHAR2(250)  ,
	nom VARCHAR2(250)  ,
	prenom VARCHAR2(250)  ,
	sexe VARCHAR2(250)  ,
	date_creation DATE  ,
	victime number(8)  ,
	motif_etat VARCHAR2(250)  ,
	GSR_RENTE number(8)  ,
	CONSTRAINT PK_GSR_RENTIER PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_RENTIER
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSR_CERTIFICATS(
	id NUMBER(8) not null,
	date_reception DATE  ,
	observation VARCHAR2(250)  ,
	type_certificat decimal(10,3)  ,
	id_certificat decimal(10,3)  ,
	numero_certificat decimal(10,3)  ,
	periode_du DATE  ,
	periode_au DATE  ,
	etat decimal(10,3)  ,
	date_etat DATE  ,
	validation char(1)  ,
	date_validation DATE  ,
	date_creation DATE  ,
	CONSTRAINT PK_GSR_CERTIFICATS PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_CERTIFICATS
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSR_PROTHESE(
	id NUMBER(8) not null,
	nature_prothese decimal(10,3)  ,
	reserve_prothese decimal(10,3)  ,
	etat_prothese decimal(10,3)  ,
	date_etat DATE  ,
	validation char(1)  ,
	date_validation DATE  ,
	centre_prothese decimal(10,3)  ,
	date_prothese DATE  ,
	montant_prothese decimal(10,3)  ,
	id_prothese decimal(10,3)  ,
	numero_prothese decimal(10,3)  ,
	date_creation DATE  ,
	CONSTRAINT PK_GSR_PROTHESE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_PROTHESE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE TB_consom_reserve_rente(
	id NUMBER(8) not null,
	numero_quittance VARCHAR2(250)  ,
	reserve_apres decimal(10,3)  ,
	reserve_avant decimal(10,3)  ,
	date_creation DATE  ,
	CONSTRAINT PK_TB_consom_reserve_rente PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_TB_consom_reserve_rente
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSR_MODEPAYEMENT(
	id NUMBER(8) not null,
	id_mode_payement decimal(10,3)  ,
	description VARCHAR2(250)  ,
	beneficiaire VARCHAR2(250)  ,
	numero_cin VARCHAR2(250)  ,
	adresse VARCHAR2(250)  ,
	code_postale decimal(10,3)  ,
	ville decimal(10,3)  ,
	pays decimal(10,3)  ,
	numero_rib VARCHAR2(250)  ,
	banque decimal(10,3)  ,
	agence_bancaire decimal(10,3)  ,
	etat decimal(10,3)  ,
	date_etat DATE  ,
	validation char(1)  ,
	date_validation DATE  ,
	date_creation DATE  ,
	CONSTRAINT PK_GSR_MODEPAYEMENT PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_MODEPAYEMENT
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSR_ORDREVIREMENT(
	id NUMBER(8) not null,
	annee decimal(10,3)  ,
	commission decimal(10,3)  ,
	commission_h decimal(10,3)  ,
	date_edition DATE  ,
	edit char(1)  ,
	montant decimal(10,3)  ,
	nombre decimal(10,3)  ,
	nom_fichier VARCHAR2(250)  ,
	numero_virement VARCHAR2(250)  ,
	trimestre VARCHAR2(250)  ,
	type_rente VARCHAR2(250)  ,
	type_virement VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_GSR_ORDREVIREMENT PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_ORDREVIREMENT
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_INTERMEDIAIRE(
	id NUMBER(8) not null,
	codetypeinterm VARCHAR2(250)  ,
	codeintermediaire decimal(10,3)  ,
	lblintermediaire VARCHAR2(250)  ,
	etat decimal(10,3)  ,
	dateetat DATE  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_INTERMEDIAIRE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_INTERMEDIAIRE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_AGENCE_BANCAIRE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_AGENCE_BANCAIRE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_AGENCE_BANCAIRE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_BANQUES(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_BANQUES PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_BANQUES
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_CENTRE_PROTHESE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_CENTRE_PROTHESE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_CENTRE_PROTHESE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE REF_DEGRE_PARENTE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_REF_DEGRE_PARENTE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_REF_DEGRE_PARENTE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_ETAT_RENTE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_ETAT_RENTE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_ETAT_RENTE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_MOTIF_COMPLEMENT(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_MOTIF_COMPLEMENT PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_MOTIF_COMPLEMENT
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_MOTIF_ETAT(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_MOTIF_ETAT PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_MOTIF_ETAT
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_NATURE_PROTHESE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_NATURE_PROTHESE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_NATURE_PROTHESE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_NATURE_QUITTANCE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_NATURE_QUITTANCE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_NATURE_QUITTANCE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_PAYS(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	ville number(8)  ,
	CONSTRAINT PK_PRM_PAYS PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_PAYS
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_SITUATION_RENTIER(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_SITUATION_RENTIER PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_SITUATION_RENTIER
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_SOCIETE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_SOCIETE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_SOCIETE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_TRIBUNEAUX(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_TRIBUNEAUX PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_TRIBUNEAUX
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_TYPE_CERTIFICAT(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_TYPE_CERTIFICAT PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_TYPE_CERTIFICAT
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_TYPE_CHEQUE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_TYPE_CHEQUE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_TYPE_CHEQUE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_TYPE_MOUVEMENT(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_TYPE_MOUVEMENT PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_TYPE_MOUVEMENT
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_TYPE_QUITTANCE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_TYPE_QUITTANCE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_TYPE_QUITTANCE
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_VILLE(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_VILLE PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_VILLE
INCREMENT BY 1 START WITH 1 ;

ALTER TABLE ITAR_SECURITY_USER
	ADD mail VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_USER
	ADD motdepasse_clair VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_USER
	ADD connected char(1)   ;
ALTER TABLE ITAR_SECURITY_USER
	ADD blocked char(1)   ;
ALTER TABLE ITAR_SECURITY_ACTION
	ADD action_description VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_ACTION
	ADD action_name VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_ACTION
	ADD action_class_name VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_ACTION
	ADD secure char(1)   ;
ALTER TABLE ITAR_SECURITY_ACTION
	ADD module_name VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_ACTION
	ADD sous_module_name VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_ACTION
	ADD timbre VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_ACTION
	ADD vo_class_name VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_ROLE
	ADD code_role VARCHAR2(250)   ;
ALTER TABLE ITAR_SECURITY_ROLE
	ADD libelle_role VARCHAR2(250)   ;
ALTER TABLE ITAR_UNITEORG
	ADD date_debut_effet DATE   ;
ALTER TABLE ITAR_UNITEORG
	ADD date_fin_effet DATE   ;
ALTER TABLE ITAR_UNITEORG
	ADD email VARCHAR2(250)   ;
	

	
CREATE TABLE GSR_RENTIER_H
   (ID NUMBER(8,0) NOT NULL ENABLE, 
    GSR_RENTIER NUMBER(8,0),
	LIEN_PARENTE NUMBER(10,3), 
	NUMERO_CIN VARCHAR2(250 BYTE), 
	DATE_NAISSANCE DATE, 
	NATIONALITE NUMBER(10,3), 
	NUMERO_TELEPHONE VARCHAR2(250 BYTE), 
	NUMERO_GSM VARCHAR2(250 BYTE), 
	EMAIL VARCHAR2(250 BYTE), 
	ADRESSE VARCHAR2(250 BYTE), 
	CODE_POSTALE NUMBER(10,3), 
	VILLE VARCHAR2(255 BYTE), 
	PAYS VARCHAR2(255 BYTE), 
	PROTHESE CHAR(1 BYTE), 
	SITUATION_RENTIER NUMBER, 
	RENTIER_ARISQUE CHAR(1 BYTE), 
	RENTE_CONFORME CHAR(1 BYTE), 
	ETAT_RENTE NUMBER(10,3), 
	DATE_ETAT DATE, 
	VALIDATION CHAR(1 BYTE), 
	CAPITAL_CONSTITUTIF NUMBER(10,3), 
	DATE_CONSTITUTION DATE, 
	DATE_DEPART_RENTE DATE, 
	IPP_TAUX_RENTE NUMBER(10,3), 
	RESERVE_MATHEMATIQUE NUMBER(10,3), 
	DATE_VALIDATION DATE, 
	SALAIRE_UTILE NUMBER(10,3), 
	APPROBATION_QUITTANCE CHAR(1 BYTE), 
	MONTANT_RENTE_TRIMEST NUMBER(10,3), 
	PERIODICITE VARCHAR2(250 BYTE), 
	TUTEUR CHAR(1 BYTE), 
	ID_RENTIER NUMBER(10,3), 
	CIVILITE VARCHAR2(250 BYTE), 
	NOM VARCHAR2(250 BYTE), 
	PRENOM VARCHAR2(250 BYTE), 
	SEXE VARCHAR2(250 BYTE), 
	DATE_CREATION DATE, 
	VICTIME NUMBER(8,0), 
	MOTIF_ETAT VARCHAR2(250 BYTE), 
	GSR_RENTE NUMBER(8,0), 
	IDTUTEUR NUMBER, 
	OBSERVATION_CONFORME VARCHAR2(200 BYTE), 
	ID_MODE_PAYEMENT NUMBER(7,2), 
	ORPHELIN_PUR CHAR(1 BYTE),
  DATE_MODIFICATION DATE,
USERSAS VARCHAR2(200 BYTE),
CONSTRAINT PK_GSR_RENTIER_H PRIMARY KEY (ID)
USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE SINAUTO  ENABLE
  )
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE SINAUTO ;
  
CREATE TABLE SINISTREAT.GSR_ALERTE 
   (ID NUMBER(8,0) NOT NULL ENABLE, 
  	NATURE_ALERTE VARCHAR2(200 BYTE),
  	DESTINATION VARCHAR2(200 BYTE),
	DATE_CREATION DATE,
  	NUMERO_SINISTRE VARCHAR2(250 BYTE),
	NUMERO_RENTE NUMBER(10,3),
 	IPP NUMBER(19,2),
  	DATE_NAISSANCE DATE,
  	SALAIRE_UTILE NUMBER(19,2),
  	DATE_DEPARTRENTE DATE,
  	DATE_CONSTITUTION DATE,
  	CAPITAL_CONSTITUTIF NUMBER(10,3),
  	RESERVEGRAVE NUMBER(19,2),  
	USERSAS VARCHAR2(200 BYTE), 
CONSTRAINT PK_GSR_ALERTE PRIMARY KEY (ID)
USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE SINAUTO  ENABLE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE SINAUTO ;
  
  /*
   * Création d'une nouvelle table GSR_RENTE_H 
   */
 CREATE TABLE GSR_RENTE_H
  (
  ID                   NUMBER(8)                NOT NULL,
  GSR_RENTE NUMBER(8,0),
  COMPAGNIE_RENTE      VARCHAR2(250 BYTE),
  NUMERO_RENTE         NUMBER(10,3),
  ID_DOSSIER_RENTE     NUMBER(10,3),
  ETAT                 NUMBER(10,3),
  DATE_ETAT            DATE,
  DATE_VALIDATION      DATE,
  VALIDATION           CHAR(1 BYTE),
  DOSSIER_SINISTRE     NUMBER(8),
  USERSAS_CREATEUR     VARCHAR2(200 BYTE),
  DATE_CREATION        DATE,
  USERSAS_MODIFICATUR  VARCHAR2(200 BYTE),
  DATE_MODIFICATION    DATE,
  CONSTRAINT PK_GSR_RENTE_H PRIMARY KEY (ID)
  );
CREATE SEQUENCE  SEQ_GSR_RENTE_H
INCREMENT BY 1 START WITH 1 ;
