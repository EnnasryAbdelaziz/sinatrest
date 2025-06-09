package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;

import eai.devass.sinistreat.appli.valueobjects.parametrage.DegreParenteVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.NatureEcheanceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;

public class AyantDroitVO implements IValueObject  {

	private static final long serialVersionUID = 1L;
	private String id;                   
	private String idVictime;      
	private String nom;            
	private String prenom;
	private String nomprenom; 
	private String dateNaissance;  
	private DegreParenteVO refDegreParente;   
	private String adresse;        
	private String deces;       
	private String dateDeces;      
	private String numeroCIN;
	private String numeroSinistre;    
	private String profession;
	private String rib;
	private String telephone;
	private String civilite;
	private String sexe;
	private String dateCreation;
	private VilleVO refVille;
	private String tauxIPP;
	private String reserve;
	private String rente;
	private String coef;
	private NatureEcheanceVO refNatureEcheance;
	private String dateEcheance;
	private String dateDecesDeuxParent;
	private String libelleDegreParente;
	private String orphelinPur;	
	private String capitalAvantConstitution;
	private String capitalApresConstitution;
	private String dateConstitution;
	private String dateFinArrerage;
	private String dateDepartRente;	
	private String nationalite;
	private String codePays;
	private String handicape ;
	private String tauxRenteJugement;
	private String montantRenteJugement;
	private String montantReserveJugement;
	
	public String getTauxRenteJugement() {
		return tauxRenteJugement;
	}
	public void setTauxRenteJugement(String tauxRenteJugement) {
		this.tauxRenteJugement = tauxRenteJugement;
	}

	public String getMontantReserveJugement() {
		return montantReserveJugement;
	}
	
	public void setMontantReserveJugement(String montantReserveJugement) {
		this.montantReserveJugement = montantReserveJugement;
	}
	
	public String getMontantRenteJugement() {
		return montantRenteJugement;
	}
	
	public void setMontantRenteJugement(String montantRenteJugement) {
		this.montantRenteJugement = montantRenteJugement;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdVictime() {
		return idVictime;
	}
	public void setIdVictime(String idVictime) {
		this.idVictime = idVictime;
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
	

	public DegreParenteVO getRefDegreParente() {
		return refDegreParente;
	}
	public void setRefDegreParente(DegreParenteVO refDegreParente) {
		this.refDegreParente = refDegreParente;
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
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
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
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}


	public VilleVO getRefVille() {
		return refVille;
	}
	public void setRefVille(VilleVO refVille) {
		this.refVille = refVille;
	}
	public String getDateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(String dateEcheance) {
		this.dateEcheance = dateEcheance;
	}
	public String getDateDecesDeuxParent() {
		return dateDecesDeuxParent;
	}
	public void setDateDecesDeuxParent(String dateDecesDeuxParent) {
		this.dateDecesDeuxParent = dateDecesDeuxParent;
	}
	public String getLibelleDegreParente() {
		if(getRefDegreParente()!=null) {
			return getRefDegreParente().getLibelle();
		}
		return null;
	}
	public void setLibelleDegreParente(String libelleDegreParente) {
		this.libelleDegreParente = libelleDegreParente;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getNomprenom() {
		return nomprenom;
	}
	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}
	public String getTauxIPP() {
		return tauxIPP;
	}
	public void setTauxIPP(String tauxIPP) {
		this.tauxIPP = tauxIPP;
	}

	public NatureEcheanceVO getRefNatureEcheance() {
		return refNatureEcheance;
	}
	public void setRefNatureEcheance(NatureEcheanceVO refNatureEcheance) {
		this.refNatureEcheance = refNatureEcheance;
	}
	public String getOrphelinPur() {
		return orphelinPur;
	}
	public void setOrphelinPur(String orphelinPur) {
		this.orphelinPur = orphelinPur;
	}
	
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		if(StringUtils.isEmpty(reserve)) {
			reserve="0";
		}			
		this.reserve = reserve;
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
	public String getCapitalAvantConstitution() {
		return capitalAvantConstitution;
	}
	public void setCapitalAvantConstitution(String capitalAvantConstitution) {
		if(StringUtils.isEmpty(capitalAvantConstitution)) {
			capitalAvantConstitution="0";
		}			
		this.capitalAvantConstitution = capitalAvantConstitution;
	}
	public String getCapitalApresConstitution() {
		return capitalApresConstitution;
	}
	public void setCapitalApresConstitution(String capitalApresConstitution) {
		if(StringUtils.isEmpty(capitalApresConstitution)) {
			capitalApresConstitution="0";
		}			
		this.capitalApresConstitution = capitalApresConstitution;
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
	public void setHandicape(String handicape) {
		this.handicape = handicape;
	}
	public String getHandicape() {
		return handicape;
	}	
	
	
	
}