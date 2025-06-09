package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import eai.devass.sinistreat.appli.valueobjects.parametrage.SourceDeclarationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeDeclarationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeGarantieVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeSuiviVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SinistreVO implements IValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String infoMessage = "";
    private String refSomaprem;
    public String getRefSomaprem() {
          return refSomaprem;
    }
    public void setRefSomaprem(String refSomaprem) {
          this.refSomaprem = refSomaprem;
    }


	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}

	private String adresseAssure;
	private String forfaitOurevis;
	private String forfaitOurevisLibelle;
	private String dateValidation;

	public String SinistreTestVO() {
		return this.forfaitOurevisLibelle;
	}

	public String getForfaitOurevisLibelle() {

		if ("O".equals(forfaitOurevis)) {
			return "Forfaitaire";
		} else {
			return "Revisable";
		}

	}

	public void setForfaitOurevisLibelle(String forfaitOurevisLibelle) {

		if ("FORFAITAIRE".equalsIgnoreCase(forfaitOurevisLibelle)) {
			forfaitOurevis = "O";
		} else if ("Revisable".equalsIgnoreCase(forfaitOurevisLibelle)) {
			forfaitOurevis = "N";
		} else {

			this.forfaitOurevis = forfaitOurevisLibelle;
		}

		this.forfaitOurevisLibelle = forfaitOurevisLibelle;
	}

	public String getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}

	public String getForfaitOurevis() {
		return forfaitOurevis;
	}

	public void setForfaitOurevis(String forfaitOurevis) {
		this.forfaitOurevis = forfaitOurevis;
	}

	public String getAdresseAssure() {
		return adresseAssure;
	}

	public void setAdresseAssure(String adresseAssurre) {
		this.adresseAssure = adresseAssurre;
	}

	public String getCodeActivite() {
		return codeActivite;
	}

	public void setCodeActivite(String codeActivite) {
		this.codeActivite = codeActivite;
	}

	private String codeActivite;
	private Long codeClassificationPolice;

	public Long getCodeClassificationPolice() {
		return codeClassificationPolice;
	}

	public void setCodeClassificationPolice(Long codeClassificationPolice) {
		this.codeClassificationPolice = codeClassificationPolice;
	}

	private String numeroSinistre;
	private String numeroGrave;
	private String numeroPolice = "";
	private String numeroPoliceUniv = "";
	private String codeClient = "";
	private String codeIntermediaire = "";
	private String nomIntermediaire = "";
	private String nomClient = "";
	private String codeEtatOuverture = "";
	private String dateEtatOuverture = "";
	// etat police a l'ouverture et a la survenance

	private String etatSurvenance = "";
	private String dateSurvenance;
	//
	private String dateEffet = "";
	private String typeContrat = "";
	private String typeCreation;
	private EvenementVO refEvenement;
	private VictimeVO refVictime;
	private TypeGarantieVO refTypeGarantie;
	private EtatSinistreVO refEtatSinistre;
	private String recours;
	private String idRecours;
	private String numeroRecours;
	private String diagnostique;
	private String typeIntermediaire;
	private String codeEntrepriseParticulier = "";
	private String observation;
	private String idTransaction;
	private String userCreateur;
	private String dateCreation;
	private String userModificateur;
	private String dateModification;
	private String evenement;
	private String prothese;
	private String etatActuel;
	private String etatCible;
	private String ipp;
	private String ippEstime;
	private String ippCertificat;
	private String itt;
	private String reserveGrave;
	private String reserveOrdinaire;
	private String reserveProthese;
	private String motifModification;
	private String prixProthese;
	private String prixProtheseEstime;
	private String refIntermediaire;

	private List listAyantDroit;
	private List listReglement;
	private String coutSinistre;
	private String coutSinistreAncien;
	private String coutSinistreNouveau;
	private String cumulReglement;
	private String cumulReglementAnne;
	private String cumulReglementAnnePrec;
	private String cumulReserve;
	private String cumulReserveActuel;
	private String reserveGraveActuel;
	private String reserveOrdinaireActuel;
	private String reserveProtheseActuel;
	private String rente;
	private String coef;
	private String ippReduit;
	private String capitalAvantConstitution;
	private String capitalApresConstitution;
	private String dateConstitution;
	private String dateFinArrerage;
	private String dateDepartRente;
	private String dateDeclaration;
	private String dateCreationDebut;
	private String dateCreationFin;
	private String typeLocalisation;
	private String dateGuerison;
	private String dateCreationCertificat;
	private String dateOuvertureGrave;
	private String etatTransaction;
	private String numeroTribunal;
	private String civilResponsable;
	private String numeroRente;
	private String formatedNumeroSinistre;
	private String formatedNumeroSinistreAvecGrave;
	private String modifiable;
	private String numeroPoliceExRMA;
	private String renteCreee;
	private TypeSuiviVO typeSuivi;
	
	private String salaireJugement;
	private String ippJugement;
	private String decisionCP;
	private TypeDeclarationVO typeDeclaration;
	private SourceDeclarationVO sourceDeclaration;

	public String getCoutSinistre() {
		return coutSinistre;
	}

	public void setCoutSinistre(String coutSinistre) {
		this.coutSinistre = coutSinistre;
	}

	public String getCumulReglement() {
		return cumulReglement;
	}

	public void setCumulReglement(String cumulReglement) {
		this.cumulReglement = cumulReglement;
	}

	public String getCumulReserve() {
		return cumulReserve;
	}

	public void setCumulReserve(String cumulReserve) {
		this.cumulReserve = cumulReserve;
	}

	public String getNumeroRecours() {
		return numeroRecours;
	}

	public void setNumeroRecours(String numeroRecours) {
		this.numeroRecours = numeroRecours;
	}

	public SinistreVO() {
		super();
	}

	public SinistreVO(String numeroSinistre) {
		super();
		this.numeroSinistre = numeroSinistre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumeroSinistre() {
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getNumeroPolice() {
		return numeroPolice;
	}

	public void setNumeroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public String getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}

	public String getCodeIntermediaire() {
		return codeIntermediaire;
	}

	public void setCodeIntermediaire(String codeIntermediaire) {
		this.codeIntermediaire = codeIntermediaire;
	}

	public String getNomIntermediaire() {
		return nomIntermediaire;
	}

	public void setNomIntermediaire(String nomIntermediaire) {
		this.nomIntermediaire = nomIntermediaire;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getIdRecours() {
		return idRecours;
	}

	public void setIdRecours(String idRecours) {
		this.idRecours = idRecours;
	}

	public String getDiagnostique() {
		return diagnostique;
	}

	public void setDiagnostique(String diagnostique) {
		this.diagnostique = diagnostique;
	}

	public String getTypeIntermediaire() {
		return typeIntermediaire;
	}

	public void setTypeIntermediaire(String typeIntermediaire) {
		this.typeIntermediaire = typeIntermediaire;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public EvenementVO getRefEvenement() {
		return refEvenement;
	}

	public void setRefEvenement(EvenementVO refEvenement) {
		this.refEvenement = refEvenement;
	}

	public VictimeVO getRefVictime() {
		return refVictime;
	}

	public void setRefVictime(VictimeVO refVictime) {
		this.refVictime = refVictime;
	}

	public List getListReglement() {
		return listReglement;
	}

	public void setListReglement(List listReglement) {
		this.listReglement = listReglement;
	}

	public List getListAyantDroit() {
		return listAyantDroit;
	}

	public void setListAyantDroit(List listAyantDroit) {
		this.listAyantDroit = listAyantDroit;
	}

	public TypeGarantieVO getRefTypeGarantie() {
		return refTypeGarantie;
	}

	public void setRefTypeGarantie(TypeGarantieVO refTypeGarantie) {
		this.refTypeGarantie = refTypeGarantie;
	}

	public EtatSinistreVO getRefEtatSinistre() {
		return refEtatSinistre;
	}

	public void setRefEtatSinistre(EtatSinistreVO refEtatSinistre) {
		this.refEtatSinistre = refEtatSinistre;
	}

	public String getEtatActuel() {
		return etatActuel;
	}

	public void setEtatActuel(String etatActuel) {
		this.etatActuel = etatActuel;
	}

	public String getEtatCible() {
		return etatCible;
	}

	public void setEtatCible(String etatCible) {
		this.etatCible = etatCible;
	}

	public String getIpp() {
		return ipp;
	}

	public void setIpp(String ipp) {
		this.ipp = ipp;
	}

	public String getItt() {
		return itt;
	}

	public void setItt(String itt) {
		this.itt = itt;
	}

	public String getReserveGrave() {
		return reserveGrave;
	}

	public void setReserveGrave(String reserveGrave) {
		if (StringUtils.isEmpty(reserveGrave)) {
			reserveGrave = "0";
		}
		this.reserveGrave = reserveGrave;
	}

	public String getReserveProthese() {
		return reserveProthese;
	}

	public void setReserveProthese(String reserveProthese) {
		if (StringUtils.isEmpty(reserveProthese)) {
			reserveProthese = "0";
		}
		this.reserveProthese = reserveProthese;
	}

	public String getReserveOrdinaire() {
		return reserveOrdinaire;
	}

	public void setReserveOrdinaire(String reserveOrdinaire) {
		if (StringUtils.isEmpty(reserveOrdinaire)) {
			reserveOrdinaire = "0";
		}
		this.reserveOrdinaire = reserveOrdinaire;
	}

	public String getMotifModification() {
		return motifModification;
	}

	public void setMotifModification(String motifModification) {
		this.motifModification = motifModification;
	}

	public String getRecours() {
		if (StringUtils.isEmpty(this.recours)) {
			return "false";
		}
		return recours;
	}

	public void setRecours(String recours) {
		if (StringUtils.isEmpty(recours)) {
			recours = "false";
		}
		this.recours = recours;
	}

	public String getEvenement() {
		if (StringUtils.isEmpty(this.evenement)) {
			return "false";
		}
		return evenement;
	}

	public void setEvenement(String evenement) {
		if (StringUtils.isEmpty(evenement)) {
			evenement = "false";
		}
		this.evenement = evenement;
	}

	public String getProthese() {
		if (StringUtils.isEmpty(this.prothese)) {
			return "false";
		}
		return this.prothese;
	}

	public void setProthese(String prothese) {
		if (StringUtils.isEmpty(prothese)) {
			prothese = "false";
		}
		this.prothese = prothese;
	}

	public String getIppEstime() {
		return ippEstime;
	}

	public void setIppEstime(String ippEstime) {
		this.ippEstime = ippEstime;
	}

	public String getIppCertificat() {
		return ippCertificat;
	}

	public void setIppCertificat(String ippCertificat) {
		this.ippCertificat = ippCertificat;
	}

	public String getPrixProthese() {
		return prixProthese;
	}

	public void setPrixProthese(String prixProthese) {
		this.prixProthese = prixProthese;
	}

	public String getReserveGraveActuel() {
		return reserveGraveActuel;
	}

	public void setReserveGraveActuel(String reserveGraveActuel) {
		if (StringUtils.isEmpty(reserveGraveActuel)) {
			reserveGraveActuel = "0";
		}
		this.reserveGraveActuel = reserveGraveActuel;
	}

	public String getReserveOrdinaireActuel() {
		return reserveOrdinaireActuel;
	}

	public void setReserveOrdinaireActuel(String reserveOrdinaireActuel) {
		if (StringUtils.isEmpty(reserveOrdinaireActuel)) {
			reserveOrdinaireActuel = "0";
		}
		this.reserveOrdinaireActuel = reserveOrdinaireActuel;
	}

	public String getReserveProtheseActuel() {
		return reserveProtheseActuel;
	}

	public void setReserveProtheseActuel(String reserveProtheseActuel) {
		if (StringUtils.isEmpty(reserveProtheseActuel)) {
			reserveProtheseActuel = "0";
		}
		this.reserveProtheseActuel = reserveProtheseActuel;
	}

	public String getNumeroPoliceUniv() {
		return numeroPoliceUniv;
	}

	public void setNumeroPoliceUniv(String numeroPoliceUniv) {
		this.numeroPoliceUniv = numeroPoliceUniv;
	}

	public String getCodeEtatOuverture() {
		return codeEtatOuverture;
	}

	public void setCodeEtatOuverture(String codeEtatOuverture) {
		this.codeEtatOuverture = codeEtatOuverture;
	}

	public String getDateEtatOuverture() {
		return dateEtatOuverture;
	}

	public void setDateEtatOuverture(String dateEtatOuverture) {
		this.dateEtatOuverture = dateEtatOuverture;
	}

	public String getEtatSurvenance() {
		return etatSurvenance;
	}

	public void setEtatSurvenance(String etatSurvenance) {
		this.etatSurvenance = etatSurvenance;
	}

	public String getDateSurvenance() {
		return dateSurvenance;
	}

	public void setDateSurvenance(String dateSurvenance) {
		this.dateSurvenance = dateSurvenance;
	}

	public String getDateEffet() {
		return dateEffet;
	}

	public void setDateEffet(String dateEffet) {
		this.dateEffet = dateEffet;
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	public String getTypeCreation() {
		return typeCreation;
	}

	public void setTypeCreation(String typeCreation) {
		this.typeCreation = typeCreation;
	}

	public String getDateCreationDebut() {
		return dateCreationDebut;
	}

	public void setDateCreationDebut(String dateCreationDebut) {
		this.dateCreationDebut = dateCreationDebut;
	}

	public String getDateCreationFin() {
		return dateCreationFin;
	}

	public void setDateCreationFin(String dateCreationFin) {
		this.dateCreationFin = dateCreationFin;
	}

	public String getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(String dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}

	public String getCoutSinistreAncien() {
		return coutSinistreAncien;
	}

	public void setCoutSinistreAncien(String coutSinistreAncien) {
		if (StringUtils.isEmpty(coutSinistreAncien)) {
			coutSinistreAncien = "0";
		}
		this.coutSinistreAncien = coutSinistreAncien;
	}

	public String getCoutSinistreNouveau() {
		return coutSinistreNouveau;
	}

	public void setCoutSinistreNouveau(String coutSinistreNouveau) {
		if (StringUtils.isEmpty(coutSinistreNouveau)) {
			coutSinistreNouveau = "0";
		}
		this.coutSinistreNouveau = coutSinistreNouveau;
	}

	public String getCumulReglementAnne() {
		return cumulReglementAnne;
	}

	public void setCumulReglementAnne(String cumulReglementAnne) {
		if (StringUtils.isEmpty(cumulReglementAnne)) {
			cumulReglementAnne = "0";
		}
		this.cumulReglementAnne = cumulReglementAnne;
	}

	public String getCumulReglementAnnePrec() {
		return cumulReglementAnnePrec;
	}

	public void setCumulReglementAnnePrec(String cumulReglementAnnePrec) {
		if (StringUtils.isEmpty(cumulReglementAnnePrec)) {
			cumulReglementAnnePrec = "0";
		}
		this.cumulReglementAnnePrec = cumulReglementAnnePrec;
	}

	public String getCumulReserveActuel() {
		return cumulReserveActuel;
	}

	public void setCumulReserveActuel(String cumulReserveActuel) {
		if (StringUtils.isEmpty(cumulReserveActuel)) {
			cumulReserveActuel = "0";
		}
		this.cumulReserveActuel = cumulReserveActuel;
	}

	public String getNumeroGrave() {
		return numeroGrave;
	}

	public void setNumeroGrave(String numeroGrave) {
		this.numeroGrave = numeroGrave;
	}

	public String getDateConstitution() {
		return dateConstitution;
	}

	public void setDateConstitution(String dateConstitution) {
		this.dateConstitution = dateConstitution;
	}

	public String getDateFinArrerage() {
		return dateFinArrerage;
	}

	public void setDateFinArrerage(String dateFinArrerage) {
		this.dateFinArrerage = dateFinArrerage;
	}

	public String getDateDepartRente() {
		return dateDepartRente;
	}

	public void setDateDepartRente(String dateDepartRente) {
		this.dateDepartRente = dateDepartRente;
	}

	public String getRente() {
		return rente;
	}

	public void setRente(String rente) {
		if (StringUtils.isEmpty(rente)) {
			rente = "0";
		}
		this.rente = rente;
	}

	public String getCoef() {
		return coef;
	}

	public void setCoef(String coef) {
		if (StringUtils.isEmpty(coef)) {
			coef = "0";
		}
		this.coef = coef;
	}

	public String getIppReduit() {
		return ippReduit;
	}

	public void setIppReduit(String ippReduit) {
		if (StringUtils.isEmpty(ippReduit)) {
			ippReduit = "0";
		}
		this.ippReduit = ippReduit;
	}

	public String getCapitalAvantConstitution() {
		return capitalAvantConstitution;
	}

	public void setCapitalAvantConstitution(String capitalAvantConstitution) {
		if (StringUtils.isEmpty(capitalAvantConstitution)) {
			capitalAvantConstitution = "0";
		}
		this.capitalAvantConstitution = capitalAvantConstitution;
	}

	public String getCapitalApresConstitution() {
		return capitalApresConstitution;
	}

	public void setCapitalApresConstitution(String capitalApresConstitution) {
		if (StringUtils.isEmpty(capitalApresConstitution)) {
			capitalApresConstitution = "0";
		}
		this.capitalApresConstitution = capitalApresConstitution;
	}

	public String getUserModificateur() {
		return userModificateur;
	}

	public void setUserModificateur(String userModificateur) {
		this.userModificateur = userModificateur;
	}

	public void setTypeLocalisation(String typeLocalisation) {
		this.typeLocalisation = typeLocalisation;
	}

	public String getTypeLocalisation() {
		return typeLocalisation;

	}

	public void setDateGuerison(String dateGuerison) {
		this.dateGuerison = dateGuerison;
	}

	public String getDateGuerison() {
		return dateGuerison;
	}

	public void setEtatTransaction(String etatTransaction) {
		this.etatTransaction = etatTransaction;
	}

	public String getEtatTransaction() {
		return etatTransaction;
	}

	public void setDateCreationCertificat(String dateCreationCertificat) {
		this.dateCreationCertificat = dateCreationCertificat;
	}

	public String getDateCreationCertificat() {
		return dateCreationCertificat;
	}

	public String getDateOuvertureGrave() {
		return dateOuvertureGrave;
	}

	public void setDateOuvertureGrave(String dateOuvertureGrave) {
		this.dateOuvertureGrave = dateOuvertureGrave;
	}

	public String getRefIntermediaire() {
		return refIntermediaire;
	}

	public void setRefIntermediaire(String refIntermediaire) {
		this.refIntermediaire = refIntermediaire;
	}

	public void setNumeroTribunal(String numeroTribunal) {
		this.numeroTribunal = numeroTribunal;
	}

	public String getNumeroTribunal() {
		return numeroTribunal;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public String getCivilResponsable() {
		return civilResponsable;
	}

	public void setCivilResponsable(String civilResponsable) {
		this.civilResponsable = civilResponsable;
	}

	public void setPrixProtheseEstime(String prixProtheseEstime) {
		this.prixProtheseEstime = prixProtheseEstime;
	}

	public String getPrixProtheseEstime() {
		return prixProtheseEstime;
	}

	public void setNumeroRente(String numeroRente) {
		this.numeroRente = numeroRente;
	}

	public String getNumeroRente() {
		return numeroRente;
	}

	public void setFormatedNumeroSinistre(String formatedNumeroSinistre) {
		this.formatedNumeroSinistre = formatedNumeroSinistre;
	}

	public void setFormatedNumeroSinistreAvecGrave(String formatedNumeroSinistreAvecGrave) {
		this.formatedNumeroSinistreAvecGrave = formatedNumeroSinistreAvecGrave;
	}

	public String getFormatedNumeroSinistre() {
		if (numeroSinistre != null && !StringUtils.isEmpty(numeroSinistre)
				&& numeroSinistre.length() == 21) {
			formatedNumeroSinistre = numeroSinistre.substring(0, 3) + " "
					+ numeroSinistre.charAt(3) + " "
					+ numeroSinistre.substring(4, 8) + " "
					+ numeroSinistre.substring(8, 11) + " "
					+ numeroSinistre.substring(11, 15) + " "
					+ numeroSinistre.substring(15) + " " 
					//Lot 1 l"ajout du numero grave  
					+ "X" + " " + 	this.numeroGrave;

					
		} else {
			this.formatedNumeroSinistre = numeroSinistre;
		}
		return formatedNumeroSinistre;
	}
	
	// Sans numero grave 
	
	public String getFormatedNumeroSinistreAvecGrave() {
		if (numeroSinistre != null && !StringUtils.isEmpty(numeroSinistre)
				&& numeroSinistre.length() == 21) {
			formatedNumeroSinistreAvecGrave = numeroSinistre.substring(0, 3) + " "
					+ numeroSinistre.charAt(3) + " "
					+ numeroSinistre.substring(4, 8) + " "
					+ numeroSinistre.substring(8, 11) + " "
					+ numeroSinistre.substring(11, 15) + " "
					+ numeroSinistre.substring(15)+ " " 
					+ "X" + " " + 	this.numeroGrave;

					
		} else {
			this.formatedNumeroSinistreAvecGrave = numeroSinistre;
		}
		return formatedNumeroSinistreAvecGrave;
	}
	

	public String getModifiable() {
		return modifiable;
	}

	public void setModifiable(String modifiable) {
		this.modifiable = modifiable;
	}

	public String getNumeroPoliceExRMA() {
		return numeroPoliceExRMA;
	}

	public void setNumeroPoliceExRMA(String numeroPoliceExRMA) {
		this.numeroPoliceExRMA = numeroPoliceExRMA;
	}

	/**
	 * @return the codeEntrepriseParticulier
	 */
	public String getCodeEntrepriseParticulier() {
		return codeEntrepriseParticulier;
	}

	/**
	 * @param codeEntrepriseParticulier
	 *            the codeEntrepriseParticulier to set
	 */
	public void setCodeEntrepriseParticulier(String codeEntrepriseParticulier) {
		this.codeEntrepriseParticulier = codeEntrepriseParticulier;
	}

	public void setRenteCreee(String renteCreee) {
		this.renteCreee = renteCreee;
	}

	public String getRenteCreee() {
		return renteCreee;
	}

	public TypeSuiviVO getTypeSuivi() {
		return typeSuivi;
	}

	public void setTypeSuivi(TypeSuiviVO typeSuivi) {
		this.typeSuivi = typeSuivi;
	}

	public String getSalaireJugement() {
		return salaireJugement;
	}

	public void setSalaireJugement(String salaireJugement) {
		this.salaireJugement = salaireJugement;
	}

	public String getIppJugement() {
		return ippJugement;
	}

	public void setIppJugement(String ippJugement) {
		this.ippJugement = ippJugement;
	}

	public String getDecisionCP() {
		return decisionCP;
	}

	public void setDecisionCP(String decisionCP) {
		this.decisionCP = decisionCP;
	}

	public TypeDeclarationVO getTypeDeclaration() {
		return typeDeclaration;
	}

	public void setTypeDeclaration(TypeDeclarationVO typeDeclaration) {
		this.typeDeclaration = typeDeclaration;
	}

	public SourceDeclarationVO getSourceDeclaration() {
		return sourceDeclaration;
	}

	public void setSourceDeclaration(SourceDeclarationVO sourceDeclaration) {
		this.sourceDeclaration = sourceDeclaration;
	}
	

}