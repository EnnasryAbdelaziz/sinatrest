package eai.devass.sinistreat.appli.modele.metier.contentieux;

import java.util.Date;

/** @pdOid 6fd3d2a1-3df5-43d9-841a-2d6f774d30e5 */
public class PartieAdverseJud  {

	private Long id;
	private String codeTiers;
	private Double montantRequette;
	private Double montantIndemnisation;
	private Double indemnitePrevisionnelle;
	private String substitution;
	private Double mntExecutionProvisoire;
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
    private Long idProcJudiciaire;
    private Date dateCreation;
    private ProcedureJudiciaire refProcJudiciaire;
    private Long idPartieAdverse;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodeTiers() {
		return codeTiers;
	}
	public void setCodeTiers(String codeTiers) {
		this.codeTiers = codeTiers;
	}
	public Double getMontantRequette() {
		return montantRequette;
	}
	public void setMontantRequette(Double montantRequette) {
		this.montantRequette = montantRequette;
	}
	public Double getMontantIndemnisation() {
		return montantIndemnisation;
	}
	public void setMontantIndemnisation(Double montantIndemnisation) {
		this.montantIndemnisation = montantIndemnisation;
	}
	public Double getIndemnitePrevisionnelle() {
		return indemnitePrevisionnelle;
	}
	public void setIndemnitePrevisionnelle(Double indemnitePrevisionnelle) {
		this.indemnitePrevisionnelle = indemnitePrevisionnelle;
	}
	public String getSubstitution() {
		return substitution;
	}
	public void setSubstitution(String substitution) {
		this.substitution = substitution;
	}
	public Double getMntExecutionProvisoire() {
		return mntExecutionProvisoire;
	}
	public void setMntExecutionProvisoire(Double mntExecutionProvisoire) {
		this.mntExecutionProvisoire = mntExecutionProvisoire;
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
	public String getLibelleAssurance() {
		return libelleAssurance;
	}
	public void setLibelleAssurance(String libelleAssurance) {
		this.libelleAssurance = libelleAssurance;
	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
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
	public Long getIdProcJudiciaire() {
		return idProcJudiciaire;
	}
	public void setIdProcJudiciaire(Long idProcJudiciaire) {
		this.idProcJudiciaire = idProcJudiciaire;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setRefProcJudiciaire(ProcedureJudiciaire refProcJudiciaire) {
		this.refProcJudiciaire = refProcJudiciaire;
	}
	public ProcedureJudiciaire getRefProcJudiciaire() {
		return refProcJudiciaire;
	}
	public void setIdPartieAdverse(Long idPartieAdverse) {
		this.idPartieAdverse = idPartieAdverse;
	}
	public Long getIdPartieAdverse() {
		return idPartieAdverse;
	}
	public void setPartieAdverseType(String partieAdverseType) {
		this.partieAdverseType = partieAdverseType;
	}
	public String getPartieAdverseType() {
		return partieAdverseType;
	}
	
}