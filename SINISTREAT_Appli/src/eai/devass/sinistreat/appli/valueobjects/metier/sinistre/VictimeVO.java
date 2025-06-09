package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PaysVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeMaladieVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;

public class VictimeVO  implements IValueObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nom;                  
	private String prenom;
	private String nomprenom;          
	private String dateNaissance;        
	private String adresse;              
	private String deces;             
	private String dateDeces;
	private String dateDecesEstime; 
	private String numeroCIN;
	private String numeroCNSS;     
	private String salaireUtile;         
	private String salaireHoraire;       
	private String salaireJournalier;    
	private String salaireMensuel;       
	private String salaireAnnuel;        
	private String salaireHorEstime;   
	private String salaireJourEstime;
	private String salaireMensEstime;   
	private String salaireAnnEstime;    
	//Evolution sinistre anterieur autocollant 05/12/2021
	@AConverter(propertyDist = "listSinistreAnterieur")
	private List<SinAnterieurVictimeVO> listSinistreAnterieur;
	
	
	public List<SinAnterieurVictimeVO> getListSinistreAnterieur() {
		return listSinistreAnterieur;
	}
	public void setListSinistreAnterieur(List<SinAnterieurVictimeVO> listSinistreAnterieur) {
		this.listSinistreAnterieur = listSinistreAnterieur;
	}
	public List<AyantDroitVO> getListAyantDroit() {
		return listAyantDroit;
	}
	public void setListAyantDroit(List<AyantDroitVO> listAyantDroit) {
		this.listAyantDroit = listAyantDroit;
	}
	private TypeMaladieVO refTypeMaladie;
	private String diagnostique;
	private String observation;
	private String profession;
	private String dateEmbauche;
	private String rib;
	private String telephone;
	private String civilite;
	private String dateNaissEstime;
	private String dateCreation;
	private String codeVille;
	private String villeLibelle;
	private String codePays;
	private String libellePays;
	private String salaireAnnuelActuel;
	private String salaireUtileActuel;
	private String rente;
	private String coef;
	private VilleVO refVille;
	private String nationalite;
	private String sexe;
	/*MFBO@Evolution 455*/
	private PaysVO refPays;
	
	private List<AyantDroitVO> listAyantDroit;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getDeces() {
		if(StringUtils.isEmpty(this.deces)) {
			return "false";
		}	
		return deces;
	}
	public void setDeces(String deces) {
		if(StringUtils.isEmpty(deces)) {
			deces="false";
		}		
		this.deces = deces;
	}
	public String getDateDeces() {
		return dateDeces;
	}
	public void setDateDeces(String dateDeces) {
		this.dateDeces = dateDeces;
	}
	public String getNumeroCIN() {
		return numeroCIN;
	}
	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}
	public String getSalaireUtile() {
		return salaireUtile;
	}
	public void setSalaireUtile(String salaireUtile) {
		this.salaireUtile = salaireUtile;
	}
	public String getSalaireHoraire() {
		return salaireHoraire;
	}
	public void setSalaireHoraire(String salaireHoraire) {
		this.salaireHoraire = salaireHoraire;
	}
	public String getSalaireJournalier() {
		return salaireJournalier;
	}
	public void setSalaireJournalier(String salaireJournalier) {
		this.salaireJournalier = salaireJournalier;
	}
	public String getSalaireMensuel() {
		return salaireMensuel;
	}
	public void setSalaireMensuel(String salaireMensuel) {
		this.salaireMensuel = salaireMensuel;
	}
	public String getSalaireAnnuel() {
		return salaireAnnuel;
	}
	public void setSalaireAnnuel(String salaireAnnuel) {
		this.salaireAnnuel = salaireAnnuel;
	}

	public TypeMaladieVO getRefTypeMaladie() {
		return refTypeMaladie;
	}
	public void setRefTypeMaladie(TypeMaladieVO refTypeMaladie) {
		this.refTypeMaladie = refTypeMaladie;
	}
	public String getSalaireHorEstime() {
		return salaireHorEstime;
	}
	public void setSalaireHorEstime(String salaireHorEstime) {
		if(StringUtils.isEmpty(salaireHorEstime)) {
			salaireHorEstime="0";
		}		
		this.salaireHorEstime = salaireHorEstime;
	}
	public String getSalaireJourEstime() {
		return salaireJourEstime;
	}
	public void setSalaireJourEstime(String salaireJourEstime) {
		if(StringUtils.isEmpty(salaireJourEstime)) {
			salaireJourEstime="0";
		}	
		this.salaireJourEstime = salaireJourEstime;
	}
	public String getSalaireMensEstime() {
		return salaireMensEstime;
	}
	public void setSalaireMensEstime(String salaireMensEstime) {
		if(StringUtils.isEmpty(salaireMensEstime)) {
			salaireMensEstime="0";
		}	
		this.salaireMensEstime = salaireMensEstime;
	}
	public String getSalaireAnnEstime() {
		return salaireAnnEstime;
	}
	public void setSalaireAnnEstime(String salaireAnnEstime) {
		if(StringUtils.isEmpty(salaireAnnEstime)) {
			salaireAnnEstime="0";
		}	
		this.salaireAnnEstime = salaireAnnEstime;
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
	public String getDateNaissEstime() {
		return dateNaissEstime;
	}
	public void setDateNaissEstime(String dateNaissEstime) {
		if(StringUtils.isEmpty(dateNaissEstime)) {
			dateNaissEstime="0";
		}	
		this.dateNaissEstime = dateNaissEstime;
	}
	public String getCodeVille() {
		return codeVille;
	}
	public void setCodeVille(String codeVille) {
		this.codeVille = codeVille;
	}
	public String getCodePays() {
		return codePays;
	}
	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}
	public String getDateEmbauche() {
		return dateEmbauche;
	}
	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getNomprenom() {
		return nomprenom;
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
	public String getSalaireAnnuelActuel() {
		return salaireAnnuelActuel;
	}
	public void setSalaireAnnuelActuel(String salaireAnnuelActuel) {
		if(StringUtils.isEmpty(salaireAnnuelActuel)) {
			salaireAnnuelActuel="0";
		}			
		this.salaireAnnuelActuel = salaireAnnuelActuel;
	}
	public String getSalaireUtileActuel() {
		return salaireUtileActuel;
	}
	public void setSalaireUtileActuel(String salaireUtileActuel) {
		if(StringUtils.isEmpty(salaireUtileActuel)) {
			salaireUtileActuel="0";
		}			
		this.salaireUtileActuel = salaireUtileActuel;
	}
	public String getRente() {
		return rente;
	}
	public void setRente(String rente) {
		if(StringUtils.isEmpty(rente)) {
			rente="0";
		}			
		this.rente = rente;
	}
	public String getCoef() {
		return coef;
	}
	public void setCoef(String coef) {
		if(StringUtils.isEmpty(coef)) {
			coef="0";
		}			
		this.coef = coef;
	}
	public VilleVO getRefVille() {
		return refVille;
	}
	public void setRefVille(VilleVO refVille) {
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
	public void setDateDecesEstime(String dateDecesEstime) {
		this.dateDecesEstime = dateDecesEstime;
	}
	public String getDateDecesEstime() {
		return dateDecesEstime;
	}
	/**
	 * @return the refPays
	 */
	public PaysVO getRefPays() {
		return refPays;
	}
	/**
	 * @param refPays the refPays to set
	 */
	public void setRefPays(PaysVO refPays) {
		this.refPays = refPays;
	}
	public String getVilleLibelle() {
		return villeLibelle;
	}
	public void setVilleLibelle(String villeLibelle) {
		this.villeLibelle = villeLibelle;
	}
	public String getLibellePays() {
		return libellePays;
	}
	public void setLibellePays(String libellePays) {
		this.libellePays = libellePays;
	}
	
	
}