package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import eai.devass.sinistreat.appli.modele.parametrage.DegreParente;
import eai.devass.sinistreat.appli.modele.parametrage.NatureEcheance;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;

public class AyantDroit implements IEntite {

	private static final long serialVersionUID = 1L;

	private long id;
	private Victime refVictime;
	private String nom;
	private String prenom;
	private String nomprenom;
	private Boolean handicape ;
	private Date dateNaissance;
	private DegreParente refDegreParente;
	private String adresse;
	private Boolean deces;
	private Date dateDeces;
	private String numeroCIN;
	private String profession;
	private String rib;
	private String telephone;
	private String civilite;
	private String sexe;
	private Date dateCreation;
	private Ville refVille;
	private Double tauxIPP;
	private Double reserve;
	private NatureEcheance refNatureEcheance;
	private Date dateEcheance;
	private Date dateDecesDeuxParent;
	private String libelleDegreParente;
	private Boolean orphelinPur;
	private Double rente;
	private Double coef;
	private Double capitalAvantConstitution;
	private Double capitalApresConstitution;
	private Date dateConstitution;
	private Date dateFinArrerage;
	private Date dateDepartRente;
	private String nationalite;
	private String codePays;
	private Map param;
	private Double tauxRenteJugement;
	private Double montantRenteJugement;
	private Double montantReserveJugement;
	
	public Double getMontantReserveJugement() {
		return montantReserveJugement;
	}

	public void setMontantReserveJugement(Double montantReserveJugement) {
		this.montantReserveJugement = montantReserveJugement;
	}

	public Double getMontantRenteJugement() {
		return montantRenteJugement;
	}

	public void setMontantRenteJugement(Double montantRenteJugement) {
		this.montantRenteJugement = montantRenteJugement;
	}

	public Double getTauxRenteJugement() {
		return tauxRenteJugement;
	}

	public void setTauxRenteJugement(Double tauxRenteJugement) {
		this.tauxRenteJugement = tauxRenteJugement;
	}
	
	public String getNomprenom() {
		return nomprenom;
	}

	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}

	public String getLibelleDegreParente() {
		return libelleDegreParente;
	}

	public void setLibelleDegreParente(String libelleDegreParente) {
		this.libelleDegreParente = libelleDegreParente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Victime getRefVictime() {
		return refVictime;
	}

	public void setRefVictime(Victime refVictime) {
		this.refVictime = refVictime;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public DegreParente getRefDegreParente() {
		return refDegreParente;
	}

	public void setRefDegreParente(DegreParente refDegreParente) {
		this.refDegreParente = refDegreParente;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Map getParam() {
		return param;
	}

	public void setParam(Map param) {
		this.param = param;
	}

	public Boolean getDeces() {
		if (this.deces == null) {
			return false;
		}
		return this.deces;
	}

	public void setDeces(Boolean deces) {
		Boolean result = deces;
		if (deces == null) {
			result = false;
		}
		this.deces = result;
	}

	public String getNumeroCIN() {
		return numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Ville getRefVille() {
		return refVille;
	}

	public void setRefVille(Ville refVille) {
		this.refVille = refVille;
	}

	public NatureEcheance getRefNatureEcheance() {
		return refNatureEcheance;
	}

	public void setRefNatureEcheance(NatureEcheance refNatureEcheance) {
		this.refNatureEcheance = refNatureEcheance;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public Date getDateDecesDeuxParent() {
		return dateDecesDeuxParent;
	}

	public void setDateDecesDeuxParent(Date dateDecesDeuxParent) {
		this.dateDecesDeuxParent = dateDecesDeuxParent;
	}



	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getDateDeces() {
		return dateDeces;
	}

	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}

	public Double getTauxIPP() {
		return tauxIPP;
	}

	public void setTauxIPP(Double tauxIPP) {
		this.tauxIPP = tauxIPP;
	}

	public Double getReserve() {
		if (this.reserve == null) {
			return Double.valueOf(0);
		}
		if (this.reserve <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.reserve;
	}

	public void setReserve(Double reserve) {
		Double result = reserve;
		if (reserve == null) {
			result = Double.valueOf(0);
		}
		if (reserve <= Double.valueOf(0)) {
			result = Double.valueOf(0);
		}
		this.reserve = result;
	}

	public Boolean getOrphelinPur() {
		if (this.orphelinPur == null) {
			return false;
		}
		return this.orphelinPur;
	}

	public void setOrphelinPur(Boolean orphelinPur) {
		Boolean result = orphelinPur;
		if (orphelinPur == null) {
			result = false;
		}
		this.orphelinPur = result;
	}

	public int getAge(Date date) {
		int age = 0;
		if (this.getDateNaissance() != null) {
			Date dateNaissance = this.getDateNaissance();
			Calendar naissance = Calendar.getInstance();
			naissance.setTime(dateNaissance);
			// Calendar today = Calendar.getInstance();
			Calendar dateCalcul = Calendar.getInstance();
			dateCalcul.setTime(date);
//			age = dateCalcul.get(Calendar.YEAR) - naissance.get(Calendar.YEAR);
//
//			dateCalcul.add(Calendar.YEAR, -age);
//			if (naissance.after(dateCalcul)) {
//				age = age - 1;
//			}
			
			//Evol 15/10/2020: Arrondir l'age
			int timeDebut = (int) ((dateNaissance.getTime())/ (24 * 60 * 60 * 1000));
			int timeFin = (int) (date.getTime()/ (24 * 60 * 60 * 1000));
			age = (int) Math.round((timeFin - timeDebut)/365d);
		}

//		return (age >= 0) ? age : 0;
		//Evol 15/10/2020: Arrondir l'age
		return age;
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
		Double result = rente;
		if (rente == null) {
			result = Double.valueOf(0);
		}
		if (rente <= Double.valueOf(0)) {
			result = Double.valueOf(0);
		}
		this.rente = result;
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
		Double result = coef;
		if (coef == null) {
			result = Double.valueOf(0);
		}
		if (coef <= Double.valueOf(0)) {
			result = Double.valueOf(0);
		}
		this.coef = result;
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
		Double result = capitalAvantConstitution;
		if (capitalAvantConstitution == null) {
			result = Double.valueOf(0);
		}
		if (capitalAvantConstitution <= Double.valueOf(0)) {
			result = Double.valueOf(0);
		}
		this.capitalAvantConstitution = result;
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
		Double result = capitalApresConstitution;
		if (capitalApresConstitution == null) {
			result = Double.valueOf(0);
		}
		if (capitalApresConstitution <= Double.valueOf(0)) {
			result = Double.valueOf(0);
		}
		this.capitalApresConstitution = result;
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

	public IEntiteFactory getFactory() {
		return null;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setHandicape(Boolean handicape) {
		this.handicape = handicape;
	}

	public Boolean getHandicape() {
		return handicape;
	}
}