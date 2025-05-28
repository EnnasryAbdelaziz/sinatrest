

CREATE TABLE GSR_Heritier(
	id NUMBER(8) not null,
	beneficiaire VARCHAR2(250)  ,
	num_cin VARCHAR2(250)  ,
	quote_part decimal(10,3)  ,
	date_creation DATE  ,
	GSR_MvtDecesRentier number(8)  ,
	CONSTRAINT PK_GSR_Heritier PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_Heritier
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSR_Mouvement(
	id NUMBER(8) not null,
	capital_calcule decimal(10,3)  ,
	ref_recours VARCHAR2(250)  ,
	dat_etat DATE  ,
	mnt_rente decimal(10,3)  ,
	num_order number(8)  ,
	ref_judiciaire VARCHAR2(250)  ,
	echeance_effective DATE  ,
	rentier number(8)  ,
	etat_mvt number(8)  ,
	type_mouvement number(8)  ,
	observation VARCHAR2(250)  ,
	dat_effet DATE  ,
	date_creation DATE  ,
	motif_defaut_remise number(8)  ,
	dat_au DATE  ,
	dat_du DATE  ,
	dat_rcpt DATE  ,
	num_certificat number(8)  ,
	type_certificat number(8)  ,
	dat_limte_paiement DATE  ,
	dat_rcpt_cnra DATE  ,
	dat_prise_en_charge DATE  ,
	mnt_cnra decimal(10,3)  ,
	ref_dossier_cnra decimal(10,3)  ,
	mnt_complement_calcule decimal(10,3)  ,
	dat_deces_parent DATE  ,
	dat_depart_augmentation DATE  ,
	mnt_complement_cnra decimal(10,3)  ,
	nouv_mnt_rente decimal(10,3)  ,
	nouv_taux decimal(10,3)  ,
	parent_decede VARCHAR2(250)  ,
	motif_complement_cnra number(8)  ,
	situation_rentier number(8)  ,
	mnt_prorata_cnra decimal(10,3)  ,
	capital_juge decimal(10,3)  ,
	mnt_percu decimal(10,3)  ,
	mnt_diff decimal(10,3)  ,
	dat_deces DATE  ,
	mnt_prorata decimal(10,3)  ,
	dat_remariage DATE  ,
	vof_enfants char(1)  ,
	mnt_rachat_annuitee decimal(10,3)  ,
	dat_fin_rente DATE  ,
	motif_rente_echue number(8)  ,
	motif VARCHAR2(250)  ,
	dat_mise_en_vigeur DATE  ,
	pourcentage decimal(10,3)  ,
	dat_debut DATE  ,
	dat_fin DATE  ,
	dat_remise_bancaire DATE  ,
	mnt_preleve_rente decimal(10,3)  ,
	mnt_prorata_recuper decimal(10,3)  ,
	mnt_recupere decimal(10,3)  ,
	mnt_reliquat decimal(10,3)  ,
	mnt_trop_percu decimal(10,3)  ,
	num_remise_bancaire number(8)  ,
	type_action number(8)  ,
	type_recuperation number(8)  ,
	dat_suspension DATE  ,
	mnt_regle decimal(10,3)  ,
	mnt_indu decimal(10,3)  ,
	cod_action number(8)  ,
	dat_suppression DATE  ,
	dat_remise DATE  ,
	arrerages_dus decimal(10,3)  ,
	arrerages_percus decimal(10,3)  ,
	nouv_salaire decimal(10,3)  ,
	nouv_ipp decimal(10,3)  ,
	dat_debut_arrerage DATE  ,
	dat_fin_arrerage DATE  ,
	nouv_capital_constit decimal(10,3)  ,
	nouv_dat_naissance DATE  ,
	type_maj_capital number(8)  ,
	dat_mvt_prothese DATE  ,
	details_mvt VARCHAR2(250)  ,
	mnt_frais_appareil decimal(10,3)  ,
	mnt_mvt_prothese decimal(10,3)  ,
	num_prothese number(8)  ,
	vof_estimatif char(1)  ,
	centre_prothese number(8)  ,
	nature_prothese number(8)  ,
	type_mvt_prothese number(8)  ,
	vof_en_instance char(1)  ,
	dat_vof_en_instance DATE  ,
	TYPEFILLE VARCHAR2(250)  ,
	CONSTRAINT PK_GSR_Mouvement PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_Mouvement
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE GSR_Prerglt(
	id NUMBER(8) not null,
	pour_le_compte VARCHAR2(250)  ,
	adresse VARCHAR2(250)  ,
	details VARCHAR2(250)  ,
	lbl_virement VARCHAR2(250)  ,
	num_cin VARCHAR2(250)  ,
	num_rib VARCHAR2(250)  ,
	ref_bordereau VARCHAR2(250)  ,
	ref_rglt VARCHAR2(250)  ,
	date_creation DATE  ,
	type_cheque number(8)  ,
	type_virement number(8)  ,
	type_reglement number(8)  ,
	mode_reglement number(8)  ,
	pays number(8)  ,
	ville number(8)  ,
	intermediaire number(8)  ,
	CONSTRAINT PK_GSR_Prerglt PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_Prerglt
INCREMENT BY 1 START WITH 1 ;



CREATE TABLE PRM_GSR_EtatMvt(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_EtatMvt PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_EtatMvt
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_EtatQtc(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_EtatQtc PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_EtatQtc
INCREMENT BY 1 START WITH 1 ;





CREATE TABLE PRM_GSR_ModeRglt(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_ModeRglt PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_ModeRglt
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_MotifDefautRemise(
	id NUMBER(8) not null,
	libelle VARCHAR2(250)  ,
	code decimal(10,3)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_MotifDe PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_MotifDefautRemise
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_MotifRenteEchue(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_MotifRenteEchue PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_MotifRenteEchue
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_Action(
	id NUMBER(8) not null,
	libelle VARCHAR2(250)  ,
	code decimal(10,3)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_Action PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_Action
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_TypeApprobation(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_TypeApprobation PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_TypeApprobation
INCREMENT BY 1 START WITH 1 ;





CREATE TABLE PRM_GSR_MajCapital(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_MajCapital PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_MajCapital
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_TypeMvtProthese(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_TypeMvtProthese PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_TypeMvtProthese
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_TypeRecuperation(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_TypeRec PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_TypeRecuperation
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_TypeRglt(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_TypeRglt PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_TypeRglt
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_TypeVirement(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_TypeVirement PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_TypeVirement
INCREMENT BY 1 START WITH 1 ;















CREATE TABLE PRM_GSR_MotifComplementCNRA(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_MotifComplement PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_MotifComplementCNRA
INCREMENT BY 1 START WITH 1 ;





CREATE TABLE PRM_GSR_NatureQTC(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_NatureQTC PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_NatureQTC
INCREMENT BY 1 START WITH 1 ;




CREATE TABLE PRM_GSR_TypeCertificat(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_TypeCertificat PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_TypeCertificat
INCREMENT BY 1 START WITH 1 ;





CREATE TABLE PRM_GSR_TypeMvt(
	id NUMBER(8) not null,
	code decimal(10,3)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_TypeMvt PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_TypeMvt
INCREMENT BY 1 START WITH 1 ;


CREATE TABLE PRM_GSR_TypeQTC(
	id NUMBER(8) not null,
	code number(8)  ,
	libelle VARCHAR2(250)  ,
	date_creation DATE  ,
	CONSTRAINT PK_PRM_GSR_TypeQTC PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_PRM_GSR_TypeQTC
INCREMENT BY 1 START WITH 1 ;

CREATE TABLE GSR_Quittance(
	id NUMBER(8) not null,
	numero_quittance VARCHAR2(250)  ,
	montant decimal(10,3)  ,
	dat_etat DATE  ,
	beneficiaire VARCHAR2(250)  ,
	numero_rente decimal(10,3)  ,
	dat_emission DATE  ,
	exercice VARCHAR2(250)  ,
	date_creation DATE  ,
	rentier number(8)  ,
	etat_qtc number(8)  ,
	type_quittance number(8)  ,
	nature_quittance number(8)  ,
	degre_parente number(8)  ,
	type_approbation number(8)  ,
	prerglt number(8)  ,
	TB_ordonnancement number(8)  ,
	GSR_Mouvement number(8)  ,
	CONSTRAINT PK_GSR_QTC PRIMARY KEY (id)
);
CREATE SEQUENCE  SEQ_GSR_Quittance
INCREMENT BY 1 START WITH 1 ;

