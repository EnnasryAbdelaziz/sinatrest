package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.parametrage.SourceDeclaration;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDeclaration;
import eai.devass.sinistreat.appli.modele.parametrage.TypeGarantie;
import eai.devass.sinistreat.appli.modele.parametrage.TypeSuivi;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;

public class Sinistre implements IEntite {

	private static final long serialVersionUID = 1L;

	private long id;
	private transient String infoMessage = "";
	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}

	private String forfaitOurevis = "";
	private Double reserveOrdinaireInit;
	private Double reserveGraveInit;
	private Double reserveProtheseInit;
	private Date dateValidation;
	private Boolean modifiable;
	private String numeroSinistre;
	private String numeroGrave;
	private String numeroPolice;
	private String numeroPoliceUniv;
	private String codeClient;
	private String codeIntermediaire;
	private String nomIntermediaire = "                  ";
	private String nomClient;
	private String codeEtatOuverture = "";
	private Date dateEtatOuverture;

	private String etatSurvenance = "";
	private Date dateSurvenance;
	private Date dateEffet;
	private String typeContrat = "";
	private Boolean recours;
	private String idRecours;
	private String numeroRecours;
	private Boolean evenement;
	private Boolean prothese;
	private String diagnostique;
	private String observation;
	private Long idTransaction;
	private String userCreateur;
	private Date dateCreation;
	private String userModificateur;
	private Date dateModification;
	private TypeGarantie refTypeGarantie;
	private String etatActuel;
	private String etatCible;
	private List<EtatSinistre> refEtatSinistres;
	private String refIntermediaire;
	private String typeIntermediaire;
	private Long codeEntrepriseParticulier;
	private String adresseAssure;

	private String codeActivite;
	private Long codeClassificationPolice;

	private Evenement refEvenement;
	private Victime refVictime;
	private transient EtatSinistre refEtatSinistre;
	private List<Reglement> listReglement;
	private String migFlag;
	private Double ipp;
	private Double ippJugement;
	private Boolean ippCertificat;
	private Long itt;
	private Double ippReduit;
	private Boolean ippEstime;
	private Double reserveGrave;
	private Double reserveOrdinaire;
	private Double reserveProthese;
	private String motifModification;
	private Double prixProthese;
	private Boolean prixProtheseEstime;
	private Double coutSinistre;
	private Double coutSinistreAncien;
	private Double coutSinistreNouveau;
	private Double cumulRecuperation;
	private transient Double cumulReglement;
	private Double cumulReglementAnne;
	private Double cumulReglementAnnePrec;
	private Double cumulReserve;
	private Double cumulReserveActuel;
	private Double reserveGraveActuel;
	private Double reserveOrdinaireActuel;
	private Double reserveProtheseActuel;
	private Double rente;
	private Double coef;
	private String typeCreation;
	private Double capitalAvantConstitution;
	private Double capitalApresConstitution;
	private Date dateConstitution;
	private Date dateFinArrerage;
	private Date dateDepartRente;
	private Date dateDeclaration;
	private Date dateCreationDebut;
	private Date dateCreationFin;
	private Date dateGuerison;
	private Date dateCreationCertificat;
	private Date dateOuvertureGrave;
	private String etatTransaction;
	private String numeroTribunal;
	private String civilResponsable;
	private String numeroRente;
	private Double salaireJugement;
	private Date dateCalculReserve;
	private Boolean renteCreee;
	// Pour le batch synchronisation BDC
	private Date dateSynchronisation;
	private TypeSuivi typeSuivi;
	// POur la convertion (VO BO)
	private transient String[] propertiesToConvert;
	private Boolean decisionCP;
	// Evol ajout bloc type ouverture
	private TypeDeclaration typeDeclaration;
	private SourceDeclaration sourceDeclaration;
	private String refSomaprem;
	public String getRefSomaprem() {
	             return refSomaprem;
	       }

	       public void setRefSomaprem(String refSomaprem) {
	             this.refSomaprem = refSomaprem;
	       }


	public Sinistre() {
		super();
	}

	public Sinistre(Long id) {
		super();
		this.id = id;
	}

	public String getForfaitOurevis() {
		return forfaitOurevis;
	}

	public void setForfaitOurevis(String forfaitOurevis) {
		this.forfaitOurevis = forfaitOurevis;
	}

	public Sinistre(String numeroSinistre) {
		super();
		this.numeroSinistre = numeroSinistre;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id == null) {
			this.id = 0;
		} else {
			this.id = id.longValue();
		}
	}

	public Double getReserveOrdinaireInit() {
		return reserveOrdinaireInit;
	}

	public void setReserveOrdinaireInit(Double reserveOrdinaireInit) {
		this.reserveOrdinaireInit = reserveOrdinaireInit;
	}

	public Double getReserveGraveInit() {
		return reserveGraveInit;
	}

	public void setReserveGraveInit(Double reserveGraveInit) {
		this.reserveGraveInit = reserveGraveInit;
	}

	public Double getReserveProtheseInit() {
		return reserveProtheseInit;
	}

	public void setReserveProtheseInit(Double reserveProtheseInit) {
		this.reserveProtheseInit = reserveProtheseInit;
	}
	

	public Date getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}

	public Boolean getModifiable() {
		return modifiable;
	}

	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}

	public String getNumeroSinistre() {
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
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

	public Long getCodeClassificationPolice() {
		return codeClassificationPolice;
	}

	public void setCodeClassificationPolice(Long codeClassificationPolice) {
		this.codeClassificationPolice = codeClassificationPolice;
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

	public String getTypeCreation() {
		return typeCreation;
	}

	public void setTypeCreation(String typeCreation) {
		this.typeCreation = typeCreation;
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

	public String getMigFlag() {
		return migFlag;
	}

	public void setMigFlag(String migFlag) {
		this.migFlag = migFlag;
	}

	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getDiagnostique() {
		return diagnostique;
	}

	public String getIdRecours() {
		return idRecours;
	}

	public void setIdRecours(String idRecours) {
		this.idRecours = idRecours;
	}

	public Double getIppJugement() {
		return ippJugement;
	}

	public void setIppJugement(Double ippJugement) {
		this.ippJugement = ippJugement;
	}

	public Boolean getIppCertificat() {
		return ippCertificat;
	}

	public void setIppCertificat(Boolean ippCertificat) {
		this.ippCertificat = ippCertificat;
	}

	public void setDiagnostique(String diagnostique) {
		this.diagnostique = diagnostique;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Evenement getRefEvenement() {
		return refEvenement;
	}

	public void setRefEvenement(Evenement refEvenement) {
		this.refEvenement = refEvenement;
	}

	public Victime getRefVictime() {
		return refVictime;
	}

	public void setRefVictime(Victime refVictime) {
		this.refVictime = refVictime;
	}

	public List<Reglement> getListReglement() {
		return listReglement;
	}

	public void setListReglement(List<Reglement> listReglement) {
		this.listReglement = listReglement;
	}

	public Long getIdTransaction() {
		return idTransaction;
	}

	public TypeGarantie getRefTypeGarantie() {
		return refTypeGarantie;
	}

	public void setRefTypeGarantie(TypeGarantie refTypeGarantie) {
		this.refTypeGarantie = refTypeGarantie;
	}

	public EtatSinistre getRefEtatSinistre() {
		if (getRefEtatSinistres() == null || getRefEtatSinistres().isEmpty()) {
			return this.refEtatSinistre;
		} else if (this.refEtatSinistre != null) {
			return this.refEtatSinistre;
		} else {
			return (EtatSinistre) CollectionUtils.get(getRefEtatSinistres(),
					getRefEtatSinistres().size() - 1);
		}
	}

	// public EtatSinistre getRefEtatSinistre() {
	// return this.refEtatSinistre;
	// }
	public void setRefEtatSinistre(EtatSinistre refEtatSinistre) {
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

	public Double getIpp() {
		if (ipp == null) {
			return Double.valueOf(0);
		}
		if (ipp <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return ipp;
	}

	public void setIpp(Double ipp) {
		if (ipp == null) {
			ipp = Double.valueOf(0);
		}
		if (ipp <= Double.valueOf(0)) {
			ipp = Double.valueOf(0);
		}
		this.ipp = ipp;
	}

	public Double getReserveGrave() {
		if (reserveGrave == null) {
			return Double.valueOf(0);
		}
		if (reserveGrave <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return reserveGrave;
	}

	public void setReserveGrave(Double reserveGrave) {
		if (reserveGrave == null) {
			reserveGrave = Double.valueOf(0);
		}
		if (reserveGrave <= Double.valueOf(0)) {
			reserveGrave = Double.valueOf(0);
		}
		this.reserveGrave = reserveGrave;
	}

	public Double getReserveOrdinaire() {
//		if (reserveOrdinaire == null) {
//			return Double.valueOf(0);
//		}
		if (reserveOrdinaire!=null && reserveOrdinaire <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return reserveOrdinaire;
	}

	public void setReserveOrdinaire(Double reserveOrdinaire) {
		if (reserveOrdinaire == null) {
			reserveOrdinaire = Double.valueOf(0);
		}
		if (reserveOrdinaire <= Double.valueOf(0)) {
			reserveOrdinaire = Double.valueOf(0);
		}
		this.reserveOrdinaire = reserveOrdinaire;
	}

	public Double getReserveProthese() {
		if (reserveProthese == null) {
			return Double.valueOf(0);
		}
		if (reserveProthese <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return reserveProthese;
	}

	public void setReserveProthese(Double reserveProthese) {
		if (reserveProthese == null) {
			reserveProthese = Double.valueOf(0);
		}
		if (reserveProthese <= Double.valueOf(0)) {
			reserveProthese = Double.valueOf(0);
		}
		this.reserveProthese = reserveProthese;
	}

	public Long getItt() {
		if (itt == null) {
			return Long.valueOf(0);
		}
		if (itt <= Double.valueOf(0)) {
			return Long.valueOf(0);
		}
		return itt;
	}

	public void setItt(Long itt) {
		if (itt == null) {
			itt = Long.valueOf(0);
		}
		if (itt <= Double.valueOf(0)) {
			itt = Long.valueOf(0);
		}
		this.itt = itt;
	}

	public void addEtatSinistre(EtatSinistre etatSinistre) {

		if (etatSinistre.getDateEtat() == null) {
			etatSinistre.setDateEtat(new Date());
		}

		if (getRefEtatSinistres() == null) {
			List<EtatSinistre> listEtatSinistre = new ArrayList<EtatSinistre>();
			if (etatSinistre.getRefSinistre() == null) {
				etatSinistre.setRefSinistre(this);
			}

			listEtatSinistre.add(etatSinistre);
			setRefEtatSinistres(listEtatSinistre);

		} else {
			if (etatSinistre.getRefSinistre() == null) {
				etatSinistre.setRefSinistre(this);
			}

			getRefEtatSinistres().add(etatSinistre);
		}
	}

	public void addAyant(AyantDroit ay) {

		// if(etatSinistre.getDateEtat() == null)
		// etatSinistre.setDateEtat(new Date());

		if (getRefVictime().getListAyantDroit() == null) {
			List<AyantDroit> listEtatSinistre = new ArrayList<AyantDroit>();
			if (ay.getRefVictime() == null) {
				ay.setRefVictime(this.getRefVictime());
			}

			listEtatSinistre.add(ay);
			getRefVictime().setListAyantDroit(listEtatSinistre);

		} else {
			if (ay.getRefVictime() == null) {
				ay.setRefVictime(this.getRefVictime());
			}

			getRefVictime().getListAyantDroit().add(ay);
		}
	}

	public List<EtatSinistre> getRefEtatSinistres() {
		return refEtatSinistres;
	}

	public void setRefEtatSinistres(List<EtatSinistre> refEtatSinistres) {
		this.refEtatSinistres = refEtatSinistres;
	}

	public String getMotifModification() {
		return motifModification;
	}

	public void setMotifModification(String motifModification) {
		this.motifModification = motifModification;
	}

	public Boolean getRecours() {
		return recours;
	}

	public Boolean isRecours() {
		return recours;
	}

	public void setRecours(Boolean recours) {
		this.recours = recours;
	}

	public Boolean getEvenement() {
		return evenement;
	}

	public void setEvenement(Boolean evenement) {
		this.evenement = evenement;
	}

	public Boolean getIppEstime() {
		return ippEstime;
	}

	public void setIppEstime(Boolean ippEstime) {
		this.ippEstime = ippEstime;
	}

	public Double getPrixProthese() {
		if (this.prixProthese == null) {
			return Double.valueOf(0);
		}
		if (this.prixProthese <= 0) {
			return Double.valueOf(0);
		}
		return this.prixProthese;
	}

	public void setPrixProthese(Double prixProthese) {
		if (prixProthese == null) {
			prixProthese = Double.valueOf(0);
		}
		if (prixProthese <= Double.valueOf(0)) {
			prixProthese = Double.valueOf(0);
		}
		this.prixProthese = prixProthese;
	}

	public Double getCoutSinistre() {
		if (this.coutSinistre == null) {
			return Double.valueOf(0);
		}
		if (this.coutSinistre <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.coutSinistre;
	}

	public void setCoutSinistre(Double coutSinistre) {
		if (coutSinistre == null) {
			coutSinistre = Double.valueOf(0);
		}
		if (coutSinistre <= Double.valueOf(0)) {
			coutSinistre = Double.valueOf(0);
		}
		this.coutSinistre = coutSinistre;
	}

	public Double getCumulRecuperation() {
		if (this.cumulRecuperation == null) {
			return Double.valueOf(0);
		}
		// if (this.cumulRecuperation <= Double.valueOf(0))
		// return Double.valueOf(0);
		return this.cumulRecuperation;
	}

	public void setCumulRecuperation(Double cumulRecuperation) {
		if (cumulRecuperation == null) {
			cumulRecuperation = Double.valueOf(0);
		}
		// if (cumulRecuperation <= Double.valueOf(0))
		// cumulRecuperation = Double.valueOf(0);
		this.cumulRecuperation = cumulRecuperation;
	}

	public Double getCumulReglement() {
		if (this.cumulReglement == null) {
			return Double.valueOf(0);
		}
		// if (this.cumulReglement <= Double.valueOf(0))
		// return Double.valueOf(0);
		return this.cumulReglement;
	}

	public void setCumulReglement(Double cumulReglement) {
		if (cumulReglement == null) {
			cumulReglement = Double.valueOf(0);
		}
		// if (cumulReglement <= Double.valueOf(0))
		// cumulReglement = Double.valueOf(0);
		this.cumulReglement = cumulReglement;
	}

	public Double getCumulReserve() {
		if (this.cumulReserve == null) {
			return Double.valueOf(0);
		}
		if (this.cumulReserve <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.cumulReserve;
	}

	public void setCumulReserve(Double cumulReserve) {
		if (cumulReserve == null) {
			cumulReserve = Double.valueOf(0);
		}
		if (cumulReserve <= Double.valueOf(0)) {
			cumulReserve = Double.valueOf(0);
		}
		this.cumulReserve = cumulReserve;
	}

	public Double getReserveGraveActuel() {
		if (this.reserveGraveActuel == null) {
			return Double.valueOf(0);
		}
		if (this.reserveGraveActuel <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.reserveGraveActuel;
	}

	public void setReserveGraveActuel(Double reserveGraveActuel) {
		if (reserveGraveActuel == null) {
			reserveGraveActuel = Double.valueOf(0);
		}
		if (reserveGraveActuel <= Double.valueOf(0)) {
			reserveGraveActuel = Double.valueOf(0);
		}
		this.reserveGraveActuel = reserveGraveActuel;
	}

	public Double getReserveOrdinaireActuel() {
		if (this.reserveOrdinaireActuel == null) {
			return Double.valueOf(0);
		}
		if (this.reserveOrdinaireActuel <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.reserveOrdinaireActuel;
	}

	public void setReserveOrdinaireActuel(Double reserveOrdinaireActuel) {
		if (reserveOrdinaireActuel == null) {
			reserveOrdinaireActuel = Double.valueOf(0);
		}
		if (reserveOrdinaireActuel <= Double.valueOf(0)) {
			reserveOrdinaireActuel = Double.valueOf(0);
		}
		this.reserveOrdinaireActuel = reserveOrdinaireActuel;
	}

	public Double getReserveProtheseActuel() {
		if (this.reserveProtheseActuel == null) {
			return Double.valueOf(0);
		}
		if (this.reserveProtheseActuel <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.reserveProtheseActuel;
	}

	public void setReserveProtheseActuel(Double reserveProtheseActuel) {
		if (reserveProtheseActuel == null) {
			reserveProtheseActuel = Double.valueOf(0);
		}
		if (reserveProtheseActuel <= Double.valueOf(0)) {
			reserveProtheseActuel = Double.valueOf(0);
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

	public Date getDateEtatOuverture() {
		return dateEtatOuverture;
	}

	public void setDateEtatOuverture(Date dateEtatOuverture) {
		this.dateEtatOuverture = dateEtatOuverture;
	}

	public String getEtatSurvenance() {
		return etatSurvenance;
	}

	public void setEtatSurvenance(String etatSurvenance) {
		this.etatSurvenance = etatSurvenance;
	}

	public Date getDateSurvenance() {
		return dateSurvenance;
	}

	public void setDateSurvenance(Date dateSurvenance) {
		this.dateSurvenance = dateSurvenance;
	}

	public Date getDateEffet() {
		return dateEffet;
	}

	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	public Date getDateCreationDebut() {
		return dateCreationDebut;
	}

	public void setDateCreationDebut(Date dateCreationDebut) {
		this.dateCreationDebut = dateCreationDebut;
	}

	public Date getDateCreationFin() {
		return dateCreationFin;
	}

	public void setDateCreationFin(Date dateCreationFin) {
		this.dateCreationFin = dateCreationFin;
	}

	public Double getIppReduit() {
		if (this.ippReduit == null) {
			return Double.valueOf(0);
		}
		if (this.ippReduit <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return ippReduit;
	}

	public void setIppReduit(Double ippReduit) {
		if (ippReduit == null) {
			ippReduit = Double.valueOf(0);
		}
		if (ippReduit <= Double.valueOf(0)) {
			ippReduit = Double.valueOf(0);
		}
		this.ippReduit = ippReduit;
	}

	public Date getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}

	public Boolean getProthese() {
		return prothese;
	}

	public Boolean isProthese() {
		return prothese;
	}

	public void setProthese(Boolean prothese) {
		this.prothese = prothese;
	}

	public Double getCoutSinistreAncien() {
		if (this.coutSinistreAncien == null) {
			return Double.valueOf(0);
		}
		// if (this.coutSinistreAncien <= Double.valueOf(0))
		// return Double.valueOf(0);
		return coutSinistreAncien;
	}

	public void setCoutSinistreAncien(Double coutSinistreAncien) {
		if (coutSinistreAncien == null) {
			coutSinistreAncien = Double.valueOf(0);
		}
		// if (coutSinistreAncien <= Double.valueOf(0))
		// coutSinistreAncien = Double.valueOf(0);
		this.coutSinistreAncien = coutSinistreAncien;
	}

	public Double getCoutSinistreNouveau() {
		if (this.coutSinistreNouveau == null) {
			return Double.valueOf(0);
		}
		// if (this.coutSinistreNouveau <= Double.valueOf(0))
		// return Double.valueOf(0);
		return this.coutSinistreNouveau;
	}

	public void setCoutSinistreNouveau(Double coutSinistreNouveau) {
		if (coutSinistreNouveau == null) {
			coutSinistreNouveau = Double.valueOf(0);
		}
		// if (coutSinistreNouveau <= Double.valueOf(0))
		// coutSinistreNouveau = Double.valueOf(0);
		this.coutSinistreNouveau = coutSinistreNouveau;
	}

	public Double getCumulReglementAnne() {
		if (this.cumulReglementAnne == null) {
			return Double.valueOf(0);
		}
		return cumulReglementAnne;
	}

	public void setCumulReglementAnne(Double cumulReglementAnne) {
		if (cumulReglementAnne == null) {
			cumulReglementAnne = Double.valueOf(0);
		}
		this.cumulReglementAnne = cumulReglementAnne;
	}

	public Double getCumulReglementAnnePrec() {
		if (this.cumulReglementAnnePrec == null) {
			return Double.valueOf(0);
		}
		return cumulReglementAnnePrec;
	}

	public void setCumulReglementAnnePrec(Double cumulReglementAnnePrec) {
		if (cumulReglementAnnePrec == null) {
			cumulReglementAnnePrec = Double.valueOf(0);
		}
		this.cumulReglementAnnePrec = cumulReglementAnnePrec;
	}

	public Double getCumulReserveActuel() {
		if (this.cumulReserveActuel == null) {
			return Double.valueOf(0);
		}
		if (this.cumulReserveActuel <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.cumulReserveActuel;
	}

	public void setCumulReserveActuel(Double cumulReserveActuel) {
		if (cumulReserveActuel == null) {
			cumulReserveActuel = Double.valueOf(0);
		}
		if (cumulReserveActuel <= Double.valueOf(0)) {
			cumulReserveActuel = Double.valueOf(0);
		}
		this.cumulReserveActuel = cumulReserveActuel;
	}

	public IEntiteFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(long arg0) {
		this.id = arg0;
	}

	public String getNumeroGrave() {
		return numeroGrave;
	}

	public void setNumeroGrave(String numeroGrave) {
		this.numeroGrave = numeroGrave;
	}

	public Double getCapitalAvantConstitution() {
		if (this.capitalAvantConstitution == null) {
			return Double.valueOf(0);
		}
		if (this.capitalAvantConstitution <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.capitalAvantConstitution;
	}

	public void setCapitalAvantConstitution(Double capitalAvantConstitution) {
		if (capitalAvantConstitution == null) {
			capitalAvantConstitution = Double.valueOf(0);
		}
		if (capitalAvantConstitution <= Double.valueOf(0)) {
			capitalAvantConstitution = Double.valueOf(0);
		}
		this.capitalAvantConstitution = capitalAvantConstitution;
	}

	public Double getCapitalApresConstitution() {
		if (this.capitalApresConstitution == null) {
			return Double.valueOf(0);
		}
		if (this.capitalApresConstitution <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.capitalApresConstitution;
	}

	public void setCapitalApresConstitution(Double capitalApresConstitution) {
		if (capitalApresConstitution == null) {
			capitalApresConstitution = Double.valueOf(0);
		}
		if (capitalApresConstitution <= Double.valueOf(0)) {
			capitalApresConstitution = Double.valueOf(0);
		}
		this.capitalApresConstitution = capitalApresConstitution;
	}

	public Date getDateConstitution() {
		return dateConstitution;
	}

	public void setDateConstitution(Date dateConstitution) {
		this.dateConstitution = dateConstitution;
	}

	public Date getDateFinArrerage() {
		return dateFinArrerage;
	}

	public void setDateFinArrerage(Date dateFinArrerage) {
		this.dateFinArrerage = dateFinArrerage;
	}

	public Date getDateDepartRente() {
		return dateDepartRente;
	}

	public void setDateDepartRente(Date dateDepartRente) {
		this.dateDepartRente = dateDepartRente;
	}

	public Double getRente() {
		if (this.rente == null) {
			return Double.valueOf(0);
		}
		if (this.rente <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.rente;
	}

	public void setRente(Double rente) {
		if (rente == null) {
			rente = Double.valueOf(0);
		}
		if (rente <= Double.valueOf(0)) {
			rente = Double.valueOf(0);
		}
		this.rente = rente;
	}

	public Double getCoef() {
		if (this.coef == null) {
			return Double.valueOf(0);
		}
		if (this.coef <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.coef;
	}

	public void setCoef(Double coef) {
		if (coef == null) {
			coef = Double.valueOf(0);
		}
		if (coef <= Double.valueOf(0)) {
			coef = Double.valueOf(0);
		}
		this.coef = coef;
	}

	public String getUserModificateur() {
		return userModificateur;
	}

	public void setUserModificateur(String userModificateur) {
		this.userModificateur = userModificateur;
	}

	public void setDateGuerison(Date dateGuerison) {
		this.dateGuerison = dateGuerison;
	}

	public Date getDateGuerison() {
		return dateGuerison;
	}

	public void setEtatTransaction(String etatTransaction) {
		this.etatTransaction = etatTransaction;
	}

	public String getEtatTransaction() {
		return etatTransaction;
	}

	public void setDateCreationCertificat(Date dateCreationCertificat) {
		this.dateCreationCertificat = dateCreationCertificat;
	}

	public Date getDateCreationCertificat() {
		return dateCreationCertificat;
	}

	public Date getDateOuvertureGrave() {
		return dateOuvertureGrave;
	}

	public void setDateOuvertureGrave(Date dateOuvertureGrave) {
		this.dateOuvertureGrave = dateOuvertureGrave;
	}

	public String getRefIntermediaire() {
		return refIntermediaire;
	}

	public void setRefIntermediaire(String refIntermediaire) {
		this.refIntermediaire = refIntermediaire;
	}

	public String getTypeIntermediaire() {
		return typeIntermediaire;
	}

	public void setTypeIntermediaire(String typeIntermediaire) {
		this.typeIntermediaire = typeIntermediaire;
	}

	public void setNumeroTribunal(String numeroTribunal) {
		this.numeroTribunal = numeroTribunal;
	}

	public String getNumeroTribunal() {
		return numeroTribunal;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getCivilResponsable() {
		return civilResponsable;
	}

	public void setCivilResponsable(String civilResponsable) {
		this.civilResponsable = civilResponsable;
	}

	public void setPrixProtheseEstime(Boolean prixProtheseEstime) {
		this.prixProtheseEstime = prixProtheseEstime;
	}

	public Boolean getPrixProtheseEstime() {
		return prixProtheseEstime;
	}

	public void setNumeroRente(String numeroRente) {
		this.numeroRente = numeroRente;
	}

	public String getNumeroRente() {
		return numeroRente;
	}

	public Double getSalaireJugement() {
		return salaireJugement;
	}

	public void setSalaireJugement(Double salaireJugement) {
		this.salaireJugement = salaireJugement;
	}

	public String getNumeroRecours() {
		return numeroRecours;
	}

	public void setNumeroRecours(String numeroRecours) {
		this.numeroRecours = numeroRecours;
	}

	public Date getDateCalculReserve() {
		return dateCalculReserve;
	}

	public void setDateCalculReserve(Date dateCalculReserve) {
		this.dateCalculReserve = dateCalculReserve;
	}

	public Date getDateSynchronisation() {
		return dateSynchronisation;
	}

	public void setDateSynchronisation(Date dateSynchronisation) {
		this.dateSynchronisation = dateSynchronisation;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	/**
	 * @return the codeEntrepriseParticulier
	 */
	public Long getCodeEntrepriseParticulier() {
		return codeEntrepriseParticulier;
	}

	/**
	 * @param codeEntrepriseParticulier
	 *            the codeEntrepriseParticulier to set
	 */
	public void setCodeEntrepriseParticulier(Long codeEntrepriseParticulier) {
		this.codeEntrepriseParticulier = codeEntrepriseParticulier;
	}

	public void setRenteCreee(Boolean renteCreee) {
		this.renteCreee = renteCreee;
	}

	public Boolean getRenteCreee() {
		return renteCreee;
	}

	public TypeSuivi getTypeSuivi() {
		return typeSuivi;
	}

	public void setTypeSuivi(TypeSuivi typeSuivi) {
		this.typeSuivi = typeSuivi;
	}

	public Boolean getDecisionCP() {
		return decisionCP;
	}

	public void setDecisionCP(Boolean decisionCP) {
		this.decisionCP = decisionCP;
	}

	public TypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}

	public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
		this.typeDeclaration = typeDeclaration;
	}

	public SourceDeclaration getSourceDeclaration() {
		return sourceDeclaration;
	}

	public void setSourceDeclaration(SourceDeclaration sourceDeclaration) {
		this.sourceDeclaration = sourceDeclaration;
	}

	
}