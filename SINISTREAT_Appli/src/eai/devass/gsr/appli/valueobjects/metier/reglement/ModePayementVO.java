
package eai.devass.gsr.appli.valueobjects.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;


/**
Value Object de ModePayement
@author Nom Prenom (email)
*/
public class ModePayementVO extends   RechListeVO implements ITracable{

	private String id;
	private static final long serialVersionUID = 1L;
	
//Historique
	@Indexation(label="idHistorisable",analyzed=false)
	private long idHistorisable;	
	@Indexation(label="numVersion",analyzed=false)
	private long numVersion;
	@Indexation(label="utilisateurVersion",analyzed=false)
	private String utilisateurVersion;
	@Indexation(label="dateVersion",analyzed=false)
	private String dateVersion;



	@Indexation(label="idModePayement",analyzed=false) 
	private String idModePayement;
	@Indexation(label="description",analyzed=false) 
	private String description;
	@Indexation(label="beneficiaire",analyzed=false) 
	private String beneficiaire;
	@Indexation(label="numeroCIN",analyzed=false) 
	private String numeroCIN;
	@Indexation(label="adresse",analyzed=false) 
	private String adresse;
	@Indexation(label="codePostale",analyzed=false) 
	private String codePostale;
	@Indexation(label="ville",analyzed=false) 
	private String ville;
	@Indexation(label="pays",analyzed=false) 
	private String pays;
	@Indexation(label="numeroRIB",analyzed=false) 
	private String numeroRIB;
	@Indexation(label="banque",analyzed=false) 
	private String banque;
	@Indexation(label="agenceBancaire",analyzed=false) 
	private String agenceBancaire;
	@Indexation(label="etat",analyzed=false) 
	private String etat;
	@Indexation(label="dateEtat",analyzed=false) 
	private String dateEtat;
	@Indexation(label="validation",analyzed=false) 
	private String validation;
	@Indexation(label="dateValidation",analyzed=false) 
	private String dateValidation;
	@Indexation(label="dateCreation",analyzed=false) 
	private String dateCreation;
	@Indexation(label="virmentOMC",analyzed=false) 
	private String virmentOMC;
	private String typeParentA;
	private String typeParentB;



	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getIdModePayement() {
		return this.idModePayement;
	}

	public void setIdModePayement(String idModePayement) {
		this.idModePayement = idModePayement;
	}
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getBeneficiaire() {
		return this.beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
	public String getNumeroCIN() {
		return this.numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostale() {
		return this.codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getNumeroRIB() {
		return this.numeroRIB;
	}

	public void setNumeroRIB(String numeroRIB) {
		this.numeroRIB = numeroRIB;
	}
	public String getBanque() {
		return this.banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}
	public String getAgenceBancaire() {
		return this.agenceBancaire;
	}

	public void setAgenceBancaire(String agenceBancaire) {
		this.agenceBancaire = agenceBancaire;
	}
	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getDateEtat() {
		return this.dateEtat;
	}

	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}
	public String getValidation() {
		return this.validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}
	public String getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}
	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("idModePayement");
			listAttributes.add("description");
			listAttributes.add("beneficiaire");
			listAttributes.add("numeroCIN");
			listAttributes.add("adresse");
			listAttributes.add("codePostale");
			listAttributes.add("ville");
			listAttributes.add("pays");
			listAttributes.add("numeroRIB");
			listAttributes.add("banque");
			listAttributes.add("agenceBancaire");
			listAttributes.add("etat");
			listAttributes.add("dateEtat");
			listAttributes.add("validation");
			listAttributes.add("dateValidation");
			listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this,listAttributes);
		} catch (Exception e) {
			
		}	
		return rslt;
	}

	public String getTypeParentA(){
		return typeParentA; 
	}
	
	public void setTypeParentA (String typeParentA){
		this.typeParentA = typeParentA;
		this.typeParentB = typeParentA;
	}

	public String getTypeParentB(){
		return typeParentB; 
	}
	
	public void setTypeParentB (String typeParentB){
		this.typeParentB = typeParentB;
	}


	
	

//Historique
public long getNumVersion() {
		return numVersion;
	}

	public void setNumVersion(long numVersion) {
		this.numVersion = numVersion;
	}

	public String getUtilisateurVersion() {
		return utilisateurVersion;
	}

	public void setUtilisateurVersion(String utilisateurVersion) {
		this.utilisateurVersion = utilisateurVersion;
	}

	public String getDateVersion() {
		return dateVersion;
	}

	public void setDateVersion(String dateVersion) {
		this.dateVersion = dateVersion;
	}

	public long getIdHistorisable() {
		return idHistorisable;
	}

	public void setIdHistorisable(long idHistorisable) {
		this.idHistorisable = idHistorisable;
	}

	public String getVirmentOMC() {
		return virmentOMC;
	}

	public void setVirmentOMC(String virmentOMC) {
		this.virmentOMC = virmentOMC;
	}
	


}

