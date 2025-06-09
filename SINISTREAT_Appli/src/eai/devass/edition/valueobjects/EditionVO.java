package eai.devass.edition.valueobjects;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class EditionVO implements IValueObject {
	private String fileName;
	private String logoPath;
	private String jaspersPath;
	private String numeroSinistre;
	private String codeRapport;
	private String dateJournee;
	private String dateDebut;
	private String dateFin;
	private String etat;
	private String ville;
	private String imprimer;
	private List<String> codesRubriques;
	private RecoursVO refRecoursVO;
	private String decision;
	private String numeroQtc;
	private String age;
	private String nbreAnnee;
	private String utilisateur;
	private String nomUtilisateur;
	private String nombreResultat;
	private String codeRedacteur;
	private String minima;
	private String maxima;
	private String modeReglement;
	private String modeReglement1;
	private String modeReglement2;
	private String modeReglementLibelle;
	private String situationMouvement;
	private String situationMouvement1;
	private String situationMouvement2;
	private String resultat;
	// Evo VAG 2
	private String anneeRef;
	private String anneeRef1;

	private String isWord ;

	private ReglementVO refReglementVO;
	private InstructionVO instructionVO;
	private String contentieux;
	
	private String idOffre;
	private String userConnected;
	private String codeUserConnected;
	private String deces;
	private String backgroundPath;
	private String duplicate;
	private String dateAccident;
	private RelanceConciliationVO refRelanceConciliation;
	private String rentes;
	private String isExcel;
	private String typeConsignation;
	public String getTypeConsignation() {
		return typeConsignation;
	}

	public void setTypeConsignation(String typeConsignation) {
		this.typeConsignation = typeConsignation;
	}
	public String getMinima() {
		return minima;
	}
	
	public String getRentes() {
		return rentes;
	}

	public void setRentes(String rentes) {
		this.rentes = rentes;
	}
	public String getIsExcel() {
		return isExcel;
	}

	public void setIsExcel(String isExcel) {
		this.isExcel = isExcel;
	}
	public void setMinima(String minima) {
		this.minima = minima;
	}

	public String getMaxima() {
		return maxima;
	}

	public void setMaxima(String maxima) {
		this.maxima = maxima;
	}

	public String getCodeRedacteur() {
		return codeRedacteur;
	}

	public void setCodeRedacteur(String codeRedacteur) {
		this.codeRedacteur = codeRedacteur;
	}

	public String getNombreResultat() {
		return nombreResultat;
	}

	public void setNombreResultat(String nombreResultat) {
		this.nombreResultat = nombreResultat;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	private String numRente;
	private String numeroPolice;
	private String compagnie;
	private String lienParente;
	private String ageMin;
	private String ageMax;
	private String mode;
	private String modeEdition = "0";

	public String getModeEdition() {
		return modeEdition;
	}

	public void setModeEdition(String modeEdition) {
		this.modeEdition = modeEdition;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getJaspersPath() {
		return jaspersPath;
	}

	public void setJaspersPath(String jaspersPath) {
		this.jaspersPath = jaspersPath;
	}

	public String getCodeRapport() {
		return codeRapport;
	}

	public void setCodeRapport(String codeRapport) {
		this.codeRapport = codeRapport;
	}

	public String getNumeroSinistre() {
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getDateJournee() {
		return dateJournee;
	}

	public void setDateJournee(String dateJournee) {
		this.dateJournee = dateJournee;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<String> getCodesRubriques() {
		return codesRubriques;
	}

	public void setCodesRubriques(List<String> codesRubriques) {
		this.codesRubriques = codesRubriques;
	}

	public RecoursVO getRefRecoursVO() {
		return refRecoursVO;
	}

	public void setRefRecoursVO(RecoursVO refRecoursVO) {
		this.refRecoursVO = refRecoursVO;
	}

	public String getImprimer() {
		return imprimer;
	}

	public void setImprimer(String imprimer) {
		this.imprimer = imprimer;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getNumeroQtc() {
		return numeroQtc;
	}

	public void setNumeroQtc(String numeroQtc) {
		this.numeroQtc = numeroQtc;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNbreAnnee() {
		return nbreAnnee;
	}

	public void setNbreAnnee(String nbreAnnee) {
		this.nbreAnnee = nbreAnnee;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getNumRente() {
		return numRente;
	}

	public void setNumRente(String numRente) {
		this.numRente = numRente;
	}

	public String getNumeroPolice() {
		return numeroPolice;
	}

	public void setNumeroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public String getCompagnie() {
		return compagnie;
	}

	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}

	public String getLienParente() {
		return lienParente;
	}

	public void setLienParente(String lienParente) {
		this.lienParente = lienParente;
	}

	public String getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(String ageMin) {
		this.ageMin = ageMin;
	}

	public String getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(String ageMax) {
		this.ageMax = ageMax;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglementLibelle(String modeReglementLibelle) {
		this.modeReglementLibelle = modeReglementLibelle;
	}

	public String getModeReglementLibelle() {
		return modeReglementLibelle;
	}

	public void setModeReglement1(String modeReglement1) {
		this.modeReglement1 = modeReglement1;
	}

	public String getModeReglement1() {
		return modeReglement1;
	}

	public void setModeReglement2(String modeReglement2) {
		this.modeReglement2 = modeReglement2;
	}

	public String getModeReglement2() {
		return modeReglement2;
	}

	public void setSituationMouvement(String situationMouvement) {
		this.situationMouvement = situationMouvement;
	}

	public String getSituationMouvement() {
		return situationMouvement;
	}

	public void setSituationMouvement1(String situationMouvement1) {
		this.situationMouvement1 = situationMouvement1;
	}

	public String getSituationMouvement1() {
		return situationMouvement1;
	}

	public void setSituationMouvement2(String situationMouvement2) {
		this.situationMouvement2 = situationMouvement2;
	}

	public String getSituationMouvement2() {
		return situationMouvement2;
	}

	public void setAnneeRef(String anneeRef) {
		this.anneeRef = anneeRef;
	}

	public String getAnneeRef() {
		return anneeRef;
	}

	public void setAnneeRef1(String anneeRef1) {
		this.anneeRef1 = anneeRef1;
	}

	public String getAnneeRef1() {
		return anneeRef1;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public String getResultat() {
		return resultat;
	}

	public ReglementVO getRefReglementVO() {
		return refReglementVO;
	}

	public void setRefReglementVO(ReglementVO refReglementVO) {
		this.refReglementVO = refReglementVO;
	}

	public InstructionVO getInstructionVO() {
		return instructionVO;
	}

	public void setInstructionVO(InstructionVO instructionVO) {
		this.instructionVO = instructionVO;
	}

	public String getIsWord() {
		return isWord;
	}

	public void setIsWord(String isWord) {
		this.isWord = isWord;
	}
	
	public void setContentieux(String contentieux) {
		this.contentieux = contentieux;
	}

	public String getContentieux() {
		return contentieux;
	}

	public String getIdOffre() {
		return idOffre;
	}

	public void setIdOffre(String idOffre) {
		this.idOffre = idOffre;
	}

	public String getUserConnected() {
		return userConnected;
	}

	public void setUserConnected(String userConnected) {
		this.userConnected = userConnected;
	}

	public String getCodeUserConnected() {
		return codeUserConnected;
	}

	public void setCodeUserConnected(String codeUserConnected) {
		this.codeUserConnected = codeUserConnected;
	}

	public String getDeces() {
		return deces;
	}

	public void setDeces(String deces) {
		this.deces = deces;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public String getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}

	public String getDateAccident() {
		return dateAccident;
	}

	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public RelanceConciliationVO getRefRelanceConciliation() {
		return refRelanceConciliation;
	}

	public void setRefRelanceConciliation(
			RelanceConciliationVO refRelanceConciliation) {
		this.refRelanceConciliation = refRelanceConciliation;
	}

}
