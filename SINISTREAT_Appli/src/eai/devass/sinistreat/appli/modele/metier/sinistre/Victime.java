package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.modele.parametrage.Pays;
import eai.devass.sinistreat.appli.modele.parametrage.TypeMaladie;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;

public class Victime implements IEntite, Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String nom;
	private String prenom;
	private transient Boolean traitementSpecialDeletAyDroit=false;

	public  Boolean getTraitementSpecialDeletAyDroit() {
		return traitementSpecialDeletAyDroit;
	}

	public void setTraitementSpecialDeletAyDroit(
			Boolean traitementSpecialDeletAyDroit) {
		this.traitementSpecialDeletAyDroit = traitementSpecialDeletAyDroit;
	}

	private String nomprenom;
	private Date dateNaissance;
	private String adresse;
	private Boolean deces;
	private Date dateDeces;
	private Boolean dateDecesEstime;
	private String numeroCIN;
	private String numeroCNSS;
	private Double salaireUtile;
	private Double salaireHoraire;
	private Double salaireJournalier;
	private Double salaireMensuel;
	private Double salaireAnnuel;
	private Boolean salaireHorEstime;
	private Boolean salaireJourEstime;
	private Boolean salaireMensEstime;
	private Boolean salaireAnnEstime;
	private TypeMaladie refTypeMaladie;
	private String diagnostique;
	private String observation;
	private String profession;
	private Date dateEmbauche;
	private String rib;
	private String telephone;
	private String civilite;
	private Boolean dateNaissEstime;
	private Date dateCreation;
	private Long codeVille;
	private Long codePays;
	private Double salaireAnnuelActuel;
	private Double salaireUtileActuel;
	private Double rente;
	private Double coef;
	private Ville refVille;
	private String nationalite;
	private String sexe;
	/* MFBO@Evolution 455 */
	private Pays refPays;

	private List<AyantDroit> listAyantDroit;
	
	//05-12-2021: Début  Evol Sinistre Anterieur autocollant	
		@AConverter(propertyDist = "listSinistreAnterieur")
		private List<SinAnterieurVictime> listSinistreAnterieur;

	public List<SinAnterieurVictime> getListSinistreAnterieur() {
			return listSinistreAnterieur;
		}

		public void setListSinistreAnterieur(List<SinAnterieurVictime> listSinistreAnterieur) {
			this.listSinistreAnterieur = listSinistreAnterieur;
		}

	// POur la convertion (VO BO)
	private transient String[] propertiesToConvert;

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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getDateDeces() {
		return dateDeces;
	}

	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}

	public String getNumeroCIN() {
		return numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}

	public Double getSalaireUtile() {
		if (this.salaireUtile == null) {
			return Double.valueOf(0);
		}
		if (this.salaireUtile <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.salaireUtile;
	}

	public void setSalaireUtile(Double salaireUtile) {
		if (salaireUtile == null) {
			salaireUtile = Double.valueOf(0);
		}
		if (salaireUtile <= Double.valueOf(0)) {
			salaireUtile = Double.valueOf(0);
		}
		this.salaireUtile = salaireUtile;
	}

	public Double getSalaireHoraire() {
		if (this.salaireHoraire == null) {
			return Double.valueOf(0);
		}
		if (this.salaireHoraire <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.salaireHoraire;
	}

	public void setSalaireHoraire(Double salaireHoraire) {
		if (salaireHoraire == null) {
			salaireHoraire = Double.valueOf(0);
		}
		if (salaireHoraire <= Double.valueOf(0)) {
			salaireHoraire = Double.valueOf(0);
		}
		this.salaireHoraire = salaireHoraire;
	}

	public Double getSalaireJournalier() {
		if (this.salaireJournalier == null) {
			return Double.valueOf(0);
		}
		if (this.salaireJournalier <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.salaireJournalier;
	}

	public void setSalaireJournalier(Double salaireJournalier) {
		if (salaireJournalier == null) {
			salaireJournalier = Double.valueOf(0);
		}
		if (salaireJournalier <= Double.valueOf(0)) {
			salaireJournalier = Double.valueOf(0);
		}
		this.salaireJournalier = salaireJournalier;
	}

	public Double getSalaireMensuel() {
		if (this.salaireMensuel == null) {
			return Double.valueOf(0);
		}
		if (this.salaireMensuel <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.salaireMensuel;
	}

	public void setSalaireMensuel(Double salaireMensuel) {
		if (salaireMensuel == null) {
			salaireMensuel = Double.valueOf(0);
		}
		if (salaireMensuel <= Double.valueOf(0)) {
			salaireMensuel = Double.valueOf(0);
		}
		this.salaireMensuel = salaireMensuel;
	}

	public Double getSalaireAnnuel() {
		if (this.salaireAnnuel == null) {
			return Double.valueOf(0);
		}
		if (this.salaireAnnuel <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.salaireAnnuel;
	}

	public void setSalaireAnnuel(Double salaireAnnuel) {
		if (salaireAnnuel == null) {
			salaireAnnuel = Double.valueOf(0);
		}
		if (salaireAnnuel <= Double.valueOf(0)) {
			salaireAnnuel = Double.valueOf(0);
		}
		this.salaireAnnuel = salaireAnnuel;
	}

	public TypeMaladie getRefTypeMaladie() {
		return refTypeMaladie;
	}

	public void setRefTypeMaladie(TypeMaladie refTypeMaladie) {
		this.refTypeMaladie = refTypeMaladie;
	}

	public List<AyantDroit> getListAyantDroit() {
		return listAyantDroit;
	}

	public void setListAyantDroit(List<AyantDroit> listAyantDroit) {
		this.listAyantDroit = listAyantDroit;
	}

	public String getDiagnostique() {
		return diagnostique;
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

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Date getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
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

	public Boolean getDeces() {
		if (this.deces == null) {
			return false;
		}
		return this.deces;
	}

	public void setDeces(Boolean deces) {
		if (deces == null) {
			deces = false;
		}
		this.deces = deces;
	}

	public void addAyant(AyantDroit ay) {

		if (ay.getDateCreation() == null) {
			ay.setDateCreation(new Date());
		}

		if (getListAyantDroit() == null) {
			List<AyantDroit> listAy = new ArrayList<AyantDroit>();
			if (ay.getRefVictime() == null) {
				ay.setRefVictime(this);
			}

			listAy.add(ay);
			setListAyantDroit(listAy);

		} else {
			if (ay.getRefVictime() == null) {
				ay.setRefVictime(this);
			}

			getListAyantDroit().add(ay);
		}
	}

	public String getNomprenom() {
		if (this.nomprenom != null && !"".equals(this.nomprenom)) {
			return this.nomprenom;
		} else {
			this.prenom = (this.prenom == null) ? "" : this.prenom;
			this.nom = (this.nom == null) ? "" : this.nom;
			return this.nom + " " + this.prenom;
		}
	}

	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}

	public String getNumeroCNSS() {
		return numeroCNSS;
	}

	public void setNumeroCNSS(String numeroCNSS) {
		this.numeroCNSS = numeroCNSS;
	}

	public Boolean getSalaireHorEstime() {
		return salaireHorEstime;
	}

	public void setSalaireHorEstime(Boolean salaireHorEstime) {
		this.salaireHorEstime = salaireHorEstime;
	}

	public Boolean getSalaireJourEstime() {
		return salaireJourEstime;
	}

	public void setSalaireJourEstime(Boolean salaireJourEstime) {
		this.salaireJourEstime = salaireJourEstime;
	}

	public Boolean getSalaireMensEstime() {
		return salaireMensEstime;
	}

	public void setSalaireMensEstime(Boolean salaireMensEstime) {
		this.salaireMensEstime = salaireMensEstime;
	}

	public Boolean getSalaireAnnEstime() {
		return salaireAnnEstime;
	}

	public void setSalaireAnnEstime(Boolean salaireAnnEstime) {
		this.salaireAnnEstime = salaireAnnEstime;
	}

	public Boolean getDateNaissEstime() {
		return dateNaissEstime;
	}

	public void setDateNaissEstime(Boolean dateNaissEstime) {
		this.dateNaissEstime = dateNaissEstime;
	}

	public int getAge(Date date) {
		int age = 0;
		if (this.getDateNaissance() != null) {
			Date dateNaissance = this.getDateNaissance();
			Calendar naissance = Calendar.getInstance();
			naissance.setTime(dateNaissance);
			// Calendar today = Calendar.getInstance();

			// age de la victime = date accident/ date gérison - date naissance
			Calendar dateCalcul = Calendar.getInstance();
			dateCalcul.setTime(date);
//			age = dateCalcul.get(Calendar.YEAR) - naissance.get(Calendar.YEAR);
//			dateCalcul.add(Calendar.YEAR, -age);
//			if (naissance.after(dateCalcul)) {
//				age = age - 1;
//			}
			
			//Evol 15/10/2020: Arrondir l'age
			int timeDebut = (int) ((dateNaissance.getTime())/ (24 * 60 * 60 * 1000));
			int timeFin = (int) ((date.getTime())/ (24 * 60 * 60 * 1000));
			age = (int) Math.round((timeFin - timeDebut)/365d);
		}

//		return (age >= 0) ? age : 0;
		//Evol 15/10/2020: Arrondir l'age
		return age;
	}

	public Double getSalaireAnnuelActuel() {
		if (this.salaireAnnuelActuel == null) {
			return Double.valueOf(0);
		}
		if (this.salaireAnnuelActuel <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.salaireAnnuelActuel;
	}

	public void setSalaireAnnuelActuel(Double salaireAnnuelActuel) {
		if (salaireAnnuelActuel == null) {
			salaireAnnuelActuel = Double.valueOf(0);
		}
		if (salaireAnnuelActuel <= Double.valueOf(0)) {
			salaireAnnuelActuel = Double.valueOf(0);
		}
		this.salaireAnnuelActuel = salaireAnnuelActuel;
	}

	public Double getSalaireUtileActuel() {
		if (this.salaireUtileActuel == null) {
			return Double.valueOf(0);
		}
		if (this.salaireUtileActuel <= Double.valueOf(0)) {
			return Double.valueOf(0);
		}
		return this.salaireUtileActuel;
	}

	public void setSalaireUtileActuel(Double salaireUtileActuel) {
		if (salaireUtileActuel == null) {
			salaireUtileActuel = Double.valueOf(0);
		}
		if (salaireUtileActuel <= Double.valueOf(0)) {
			salaireUtileActuel = Double.valueOf(0);
		}
		this.salaireUtileActuel = salaireUtileActuel;
	}

	public EntiteFactory getFactory() {
		return null;
	}

	public void setId(long arg0) {
		this.id = arg0;
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

	public Ville getRefVille() {
		return refVille;
	}

	public void setRefVille(Ville refVille) {
		this.refVille = refVille;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public void setDateDecesEstime(Boolean dateDecesEstime) {
		this.dateDecesEstime = dateDecesEstime;
	}

	public Boolean getDateDecesEstime() {
		return dateDecesEstime;
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
	 * @return the refPays
	 */
	public Pays getRefPays() {
		return refPays;
	}

	/**
	 * @param refPays
	 *            the refPays to set
	 */
	public void setRefPays(Pays refPays) {
		this.refPays = refPays;
	}

	/**
	 * @return the codeVille
	 */
	public Long getCodeVille() {
		return codeVille;
	}

	/**
	 * @param codeVille
	 *            the codeVille to set
	 */
	public void setCodeVille(Long codeVille) {
		this.codeVille = codeVille;
	}

	/**
	 * @return the codePays
	 */
	public Long getCodePays() {
		return codePays;
	}

	/**
	 * @param codePays
	 *            the codePays to set
	 */
	public void setCodePays(Long codePays) {
		this.codePays = codePays;
	}

}