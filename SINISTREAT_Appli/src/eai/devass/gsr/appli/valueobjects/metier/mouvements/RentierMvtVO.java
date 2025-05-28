/**
 * 
 */
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.modele.EntiteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.parametrage.MvtEtatRentierVO;

/**
 * @author elfaismo
 *
 */
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt")
public class RentierMvtVO extends EntiteVO {
	
	private static final long serialVersionUID = 1L;
	
	@AConverter(propertyDist = "id")
	private long id;
	@AConverter(propertyDist = "mntAncienneRente")
	private String	mntAncienneRente;
	@AConverter(propertyDist = "mntNouvelleRente")
	private String	mntNouvelleRente;
	@AConverter(propertyDist = "nouveauIPP")
	private String	nouveauIPP;
	@AConverter(propertyDist = "ancienIPP")
	private String	ancienIPP;
	@AConverter(propertyDist = "mntTropMoinsPercu")
	private String	mntTropMoinsPercu;
	@AConverter(propertyDist = "montantPercu")
	private String montantPercu;
	@AConverter(propertyDist = "mntQuittancesRegles")
	private String mntQuittancesRegles;
	@AConverter(propertyDist = "refMouvement")
	private MvtAnnulationVO refMouvement ;
	@AConverter(propertyDist = "refRentier")
	private RentierVO refRentier;
	
	@AConverter(propertyDist = "refAncienEtatRentier")
	private MvtEtatRentierVO refAncienEtatRentier;
	@AConverter(propertyDist = "ancienEtatDossierRente")
	private String ancienEtatDossierRente;
	@AConverter(propertyDist = "ancienneReserveMathematique")
	private String ancienneReserveMathematique;
	
	

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the nouveauIPP
	 */
	public String getNouveauIPP() {
		return nouveauIPP;
	}

	/**
	 * @param nouveauIPP the nouveauIPP to set
	 */
	public void setNouveauIPP(String nouveauIPP) {
		this.nouveauIPP = nouveauIPP;
	}

	/**
	 * @return the ancienIPP
	 */
	public String getAncienIPP() {
		return ancienIPP;
	}

	/**
	 * @param ancienIPP the ancienIPP to set
	 */
	public void setAncienIPP(String ancienIPP) {
		this.ancienIPP = ancienIPP;
	}

	/**
	 * @return the mntAncienneRente
	 */
	public String getMntAncienneRente() {
		return mntAncienneRente;
	}

	/**
	 * @param mntAncienneRente the mntAncienneRente to set
	 */
	public void setMntAncienneRente(String mntAncienneRente) {
		this.mntAncienneRente = mntAncienneRente;
	}

	/**
	 * @return the mntNouvelleRente
	 */
	public String getMntNouvelleRente() {
		return mntNouvelleRente;
	}

	/**
	 * @param mntNouvelleRente the mntNouvelleRente to set
	 */
	public void setMntNouvelleRente(String mntNouvelleRente) {
		this.mntNouvelleRente = mntNouvelleRente;
	}

	/**
	 * @return the mntTropMoinsPercu
	 */
	public String getMntTropMoinsPercu() {
		return mntTropMoinsPercu;
	}

	/**
	 * @param mntTropMoinsPercu the mntTropMoinsPercu to set
	 */
	public void setMntTropMoinsPercu(String mntTropMoinsPercu) {
		this.mntTropMoinsPercu = mntTropMoinsPercu;
	}

	/**
	 * @return the refRentier
	 */
	public RentierVO getRefRentier() {
		return refRentier;
	}

	/**
	 * @param refRentier the refRentier to set
	 */
	public void setRefRentier(RentierVO refRentier) {
		this.refRentier = refRentier;
	}


	/**
	 * @return the refMouvement
	 */
	public MvtAnnulationVO getRefMouvement() {
		return refMouvement;
	}



	/**
	 * @param refMouvement the refMouvement to set
	 */
	public void setRefMouvement(MvtAnnulationVO refMouvement) {
		this.refMouvement = refMouvement;
	}

	/**
	 * @return the montantPercu
	 */
	public String getMontantPercu() {
		return montantPercu;
	}



	/**
	 * @param montantPercu the montantPercu to set
	 */
	public void setMontantPercu(String montantPercu) {
		this.montantPercu = montantPercu;
	}



	/**
	 * @return the mntQuittancesRegles
	 */
	public String getMntQuittancesRegles() {
		return mntQuittancesRegles;
	}



	/**
	 * @param mntQuittancesRegles the mntQuittancesRegles to set
	 */
	public void setMntQuittancesRegles(String mntQuittancesRegles) {
		this.mntQuittancesRegles = mntQuittancesRegles;
	}
	
	/**
	 * @return the refAncienEtatRentier
	 */
	public MvtEtatRentierVO getRefAncienEtatRentier() {
		return refAncienEtatRentier;
	}



	/**
	 * @param refAncienEtatRentier the refAncienEtatRentier to set
	 */
	public void setRefAncienEtatRentier(MvtEtatRentierVO refAncienEtatRentier) {
		this.refAncienEtatRentier = refAncienEtatRentier;
	}



	/**
	 * @return the ancienEtatDossierRente
	 */
	public String getAncienEtatDossierRente() {
		return ancienEtatDossierRente;
	}



	/**
	 * @param ancienEtatDossierRente the ancienEtatDossierRente to set
	 */
	public void setAncienEtatDossierRente(String ancienEtatDossierRente) {
		this.ancienEtatDossierRente = ancienEtatDossierRente;
	}



	/**
	 * @return the ancienneReserveMathematique
	 */
	public String getAncienneReserveMathematique() {
		return ancienneReserveMathematique;
	}



	/**
	 * @param ancienneReserveMathematique the ancienneReserveMathematique to set
	 */
	public void setAncienneReserveMathematique(String ancienneReserveMathematique) {
		this.ancienneReserveMathematique = ancienneReserveMathematique;
	}





}
