package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CategorieExAAAVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.IntermediaireExAAAVO;

public class QuittanceExAAAVO implements IValueObject {

	private String id;
	private String compagnie;
	private String numeroSinistre;
	private String debutCheque;
	private String dateSinistre;
	private String assure;
	private CategorieExAAAVO refCodeCategorie;
	private String codePrestataire;
	private String typePrestataire;
	private IntermediaireExAAAVO refCodeIntermediaire;
	private String dateEffetPolice;
	private String dateReglement;
	private String dateReglementComptable;
	private String montantCheque;
	private String numeroCheque;
	private String numeroPolice;
	private String ordrePolice;
	private String rang;
	private String rubriqueReglement;
	private String trimestre;
	private String sequence;
	private String branche;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompagnie() {
		
		return compagnie;
	}

	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}

	public String getNumeroSinistre() {
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getDebutCheque() {
		return debutCheque;
	}

	public void setDebutCheque(String debutCheque) {
		this.debutCheque = debutCheque;
	}

	public String getDateSinistre() {
		return dateSinistre;
	}

	public void setDateSinistre(String dateSinistre) {
		this.dateSinistre = dateSinistre;
	}

	public String getAssure() {
		return assure;
	}

	public void setAssure(String assure) {
		this.assure = assure;
	}



	public CategorieExAAAVO getRefCodeCategorie() {
		return refCodeCategorie;
	}

	public void setRefCodeCategorie(CategorieExAAAVO refCodeCategorie) {
		this.refCodeCategorie = refCodeCategorie;
	}

	public IntermediaireExAAAVO getRefCodeIntermediaire() {
		return refCodeIntermediaire;
	}

	public void setRefCodeIntermediaire(IntermediaireExAAAVO refCodeIntermediaire) {
		this.refCodeIntermediaire = refCodeIntermediaire;
	}


	public String getCodePrestataire() {
		return codePrestataire;
	}

	public void setCodePrestataire(String codePrestataire) {
		this.codePrestataire = codePrestataire;
	}

	public String getTypePrestataire() {
		return typePrestataire;
	}

	public void setTypePrestataire(String typePrestataire) {
		this.typePrestataire = typePrestataire;
	}

	public String getDateEffetPolice() {
		return dateEffetPolice;
	}

	public void setDateEffetPolice(String dateEffetPolice) {
		this.dateEffetPolice = dateEffetPolice;
	}

	public String getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(String dateReglement) {
		this.dateReglement = dateReglement;
	}

	public String getDateReglementComptable() {
		return dateReglementComptable;
	}

	public void setDateReglementComptable(String dateReglementComptable) {
		this.dateReglementComptable = dateReglementComptable;
	}

	public String getMontantCheque() {
		return montantCheque;
	}

	public void setMontantCheque(String montantCheque) {
		this.montantCheque = montantCheque;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getNumeroPolice() {
		return numeroPolice;
	}

	public void setNumeroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public String getOrdrePolice() {
		return ordrePolice;
	}

	public void setOrdrePolice(String ordrePolice) {
		this.ordrePolice = ordrePolice;
	}

	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}

	public String getRubriqueReglement() {
		return rubriqueReglement;
	}

	public void setRubriqueReglement(String rubriqueReglement) {
		this.rubriqueReglement = rubriqueReglement;
	}

	public String getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getBranche() {
		return branche;
	}

	public void setBranche(String branche) {
		this.branche = branche;
	}

}
