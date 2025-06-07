/**
 * 
 */
package eai.devass.gsr.appli.modele.metier.mouvements;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.parametrage.MvtEtatRentier;

/**
 * @author elfaismo
 *
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.RentierMvtVO")
public class RentierMvt  extends  EntiteBO {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Double	mntAncienneRente= 0D;
	private Double	mntNouvelleRente= 0D;
	private Double	nouveauIPP  = 0D;
	private Double	ancienIPP = 0D;
	private Double	mntTropMoinsPercu= 0D;
	/**
	 * Montant des quittances lié au mouvement preçu
	 */
	private Double montantPercu= 0D;
	/**
	 * Montant des quittances trimestrielles réglés
	 */
	private Double mntQuittancesRegles =0D;
	
	private MvtAnnulation refMouvement;
	
	private Rentier refRentier;
	
	private MvtEtatRentier refAncienEtatRentier;
	
	private long ancienEtatDossierRente;
	
	private Double ancienneReserveMathematique;

	/**
	 * @return the refAncienEtatRentier
	 */
	public MvtEtatRentier getRefAncienEtatRentier() {
		return refAncienEtatRentier;
	}

	/**
	 * @param refAncienEtatRentier the refAncienEtatRentier to set
	 */
	public void setRefAncienEtatRentier(MvtEtatRentier refAncienEtatRentier) {
		this.refAncienEtatRentier = refAncienEtatRentier;
	}

	/**
	 * @return the ancienEtatDossierRente
	 */
	public long getAncienEtatDossierRente() {
		return ancienEtatDossierRente;
	}

	/**
	 * @param ancienEtatDossierRente the ancienEtatDossierRente to set
	 */
	public void setAncienEtatDossierRente(long ancienEtatDossierRente) {
		this.ancienEtatDossierRente = ancienEtatDossierRente;
	}

	/**
	 * @return the ancienneReserveMathematique
	 */
	public Double getAncienneReserveMathematique() {
		return ancienneReserveMathematique;
	}

	/**
	 * @param ancienneReserveMathematique the ancienneReserveMathematique to set
	 */
	public void setAncienneReserveMathematique(Double ancienneReserveMathematique) {
		this.ancienneReserveMathematique = ancienneReserveMathematique;
	}

	public RentierMvt() {
		
	}

	public RentierMvt(Long identifiant) {
		super(identifiant);
	
	}
	
	public RentierMvt(MvtAnnulation refMouvement) {
		super();
		this.refMouvement = refMouvement;
	}

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
	public Double getNouveauIPP() {
		return nouveauIPP;
	}

	/**
	 * @param nouveauIPP the nouveauIPP to set
	 */
	public void setNouveauIPP(Double nouveauIPP) {
		this.nouveauIPP = nouveauIPP;
	}

	/**
	 * @return the ancienIPP
	 */
	public Double getAncienIPP() {
		return ancienIPP;
	}

	/**
	 * @param ancienIPP the ancienIPP to set
	 */
	public void setAncienIPP(Double ancienIPP) {
		this.ancienIPP = ancienIPP;
	}

	/**
	 * @return the mntAncienneRente
	 */
	public Double getMntAncienneRente() {
		return mntAncienneRente;
	}

	/**
	 * @param mntAncienneRente the mntAncienneRente to set
	 */
	public void setMntAncienneRente(Double mntAncienneRente) {
		this.mntAncienneRente = mntAncienneRente;
	}

	/**
	 * @return the mntNouvelleRente
	 */
	public Double getMntNouvelleRente() {
		return mntNouvelleRente;
	}

	/**
	 * @param mntNouvelleRente the mntNouvelleRente to set
	 */
	public void setMntNouvelleRente(Double mntNouvelleRente) {
		this.mntNouvelleRente = mntNouvelleRente;
	}

	/**
	 * @return the mntTropMoinsPercu
	 */
	public Double getMntTropMoinsPercu() {
		return mntTropMoinsPercu;
	}

	/**
	 * @param mntTropMoinsPercu the mntTropMoinsPercu to set
	 */
	public void setMntTropMoinsPercu(Double mntTropMoinsPercu) {
		this.mntTropMoinsPercu = mntTropMoinsPercu;
	}



	/**
	 * @return the refRentier
	 */
	public Rentier getRefRentier() {
		return refRentier;
	}

	/**
	 * @param refRentier the refRentier to set
	 */
	public void setRefRentier(Rentier refRentier) {
		this.refRentier = refRentier;
	}
	

	/**
	 * @return the refMouvement
	 */
	public MvtAnnulation getRefMouvement() {
		return refMouvement;
	}

	/**
	 * @param refMouvement the refMouvement to set
	 */
	public void setRefMouvement(MvtAnnulation refMouvement) {
		this.refMouvement = refMouvement;
	}
	

	/**
	 * @return the montantPercu
	 */
	public Double getMontantPercu() {
		return montantPercu;
	}

	/**
	 * @param montantPercu the montantPercu to set
	 */
	public void setMontantPercu(Double montantPercu) {
		this.montantPercu = montantPercu;
	}

	/**
	 * @return the mntQuittancesRegles
	 */
	public Double getMntQuittancesRegles() {
		return mntQuittancesRegles;
	}

	/**
	 * @param mntQuittancesRegles the mntQuittancesRegles to set
	 */
	public void setMntQuittancesRegles(Double mntQuittancesRegles) {
		this.mntQuittancesRegles = mntQuittancesRegles;
	}

}
