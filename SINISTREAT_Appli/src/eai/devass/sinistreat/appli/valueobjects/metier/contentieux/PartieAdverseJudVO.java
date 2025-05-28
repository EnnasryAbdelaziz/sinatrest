package eai.devass.sinistreat.appli.valueobjects.metier.contentieux;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/** @pdOid 6fd3d2a1-3df5-43d9-841a-2d6f774d30e5 */
public class PartieAdverseJudVO implements IValueObject {

	private String id;
	private String codeTiers;
	private String montantRequette;
	private String montantIndemnisation;
	private String indemnitePrevisionnelle;
	private String substitution;
	private String mntExecutionProvisoire;
    private String reference;
    private String codeAssurance;     
    private String libelleAssurance;
    private String partieAdverseType;
    private String nom;
    private String prenom;
    private String raisonSociale;
    private String telephone;     
    private String email;
    private String numeroCIN;
    private String adresse;
    private String ville;
    private String numeroPolice;
    private ProcedureJudiciaireVO refProcJudiciaire;
    private String idPartieAdverse;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeTiers() {
		return codeTiers;
	}

	public void setCodeTiers(String codeTiers) {
		this.codeTiers = codeTiers;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCodeAssurance() {
		return codeAssurance;
	}

	public void setCodeAssurance(String codeAssurance) {
		this.codeAssurance = codeAssurance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroCIN() {
		return numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNumeroPolice() {
		return numeroPolice;
	}

	public void setNumeroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public String getMontantRequette() {
		return montantRequette;
	}

	public void setMontantRequette(String montantRequette) {
		this.montantRequette = montantRequette;
	}

	public String getMontantIndemnisation() {
		return montantIndemnisation;
	}

	public void setMontantIndemnisation(String montantIndemnisation) {
		this.montantIndemnisation = montantIndemnisation;
	}

	public String getSubstitution() {
		return substitution;
	}

	public void setSubstitution(String substitution) {
		this.substitution = substitution;
	}

	public String getMntExecutionProvisoire() {
		return mntExecutionProvisoire;
	}

	public void setMntExecutionProvisoire(String mntExecutionProvisoire) {
		this.mntExecutionProvisoire = mntExecutionProvisoire;
	}

	public String getLibelleAssurance() {
		return libelleAssurance;
	}

	public void setLibelleAssurance(String libelleAssurance) {
		this.libelleAssurance = libelleAssurance;
	}

	public String getIndemnitePrevisionnelle() {
		return indemnitePrevisionnelle;
	}

	public void setIndemnitePrevisionnelle(String indemnitePrevisionnelle) {
		this.indemnitePrevisionnelle = indemnitePrevisionnelle;
	}

	public void setIdPartieAdverse(String idPartieAdverse) {
		this.idPartieAdverse = idPartieAdverse;
	}

	public String getIdPartieAdverse() {
		return idPartieAdverse;
	}

	public void setRefProcJudiciaire(ProcedureJudiciaireVO refProcJudiciaire) {
		this.refProcJudiciaire = refProcJudiciaire;
	}

	public ProcedureJudiciaireVO getRefProcJudiciaire() {
		return refProcJudiciaire;
	}

	public void setPartieAdverseType(String partieAdverseType) {
		this.partieAdverseType = partieAdverseType;
	}

	public String getPartieAdverseType() {
		return partieAdverseType;
	}

}