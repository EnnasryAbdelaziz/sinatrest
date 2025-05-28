package eai.devass.gsr.appli.manager.metier.transverse;

import java.math.BigDecimal;
import java.util.Calendar;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;

public class CalculTransverses {

	
	private final int QUATRE = 4;
	private final int VINGT_QUATRE= 24;
	private final int TRENTE = 30;
	private final int SOIXANTE = 60;
	private final int QUATRE_VINGT_DIX = 90;
	private final int TROIS_CENT_SOIXANTE = 360;
	private final int MILLE = 1000;
	
	private TransverseManager transverseManager = (TransverseManager) ServicesFactory
			.getService(TransverseManager.class);
	
	/**
	 * Prorata= Rente trimestrielle * nombre de jours entre [Date début, Date fin]/ 90
	 *
	 *
	 ************************Param Prorata de rente pour chaque mouvement******************
	 *                  *                                  *                              *
	 *      Mouvement   *   Date début                     *   Date fin                   *
	 **************************************************************************************
	 * Décès rentier    * Date de départ de la rente       * Date décès                   *
	 **************************************************************************************
	 * Prorata de rente * date de fin de période           * Date de prise en charge CNRA *
	 *                  * de la dernière quittance émise   *                              *
	 **************************************************************************************
	 * Rente échue      * Date où la rente                 * Date de fin du trimestre qui *
	 *                  * arrive à échéance                * précède la date d'échéance   *
	 **************************************************************************************
	 */
	public Double calculerProrata(Double MontantRenteTrimestrielle,
			Calendar dateDebut, Calendar dateFin) throws ExceptionMetier {
		Double prorata = 0D;
		Integer jours = 0;
		Double resultProrata = 0D;
		if (MontantRenteTrimestrielle != null
				&& dateDebut != null
				&& dateFin != null) {

			if(dateFin.after(dateDebut)){
				jours = getDifDate(dateFin.getTimeInMillis(),
						dateDebut.getTimeInMillis(), true);

			}
			

			prorata = (MontantRenteTrimestrielle * jours) / QUATRE_VINGT_DIX;
			
			resultProrata = new BigDecimal(prorata).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
		}

		return (resultProrata >= 0) ? resultProrata : 0;
	}

	/**
	 * calcul des arrérages avant constitution de rente, avec dates de références.
	 *
	 *
	 ****************************************Param arrérages avant constitution***************************************
	 *                                     *                                  *                                      *
	 *      Mouvement                      *   Date début                     *   Date fin                           *
	 *****************************************************************************************************************
	 * Rachat à la demande de la victime   * Date d'expertise ou de requête   * Date dernier règlement               *
	 * ***************************************************************************************************************
	 * Rachat à la demande de la compagnie * Date de départ de la rent        * Date dernier règlement               *
	 * ***************************************************************************************************************
	 * Décès rentier                       * Date de départ de la rente       * Date décès                           *
	 *****************************************************************************************************************
	 * Consignation CNRA                   * Règlement des rentes trimestrielles jusqu'à réception de la             *
	 *                                     * date de prise en charge CNRA                                            *
	 *****************************************************************************************************************
	 * Mise à jour capital                 * Calcul des Arrérages de rente    * Date fin: dernier trimestre émis     *
	 *                                     * avec ancienne rente et des       *                                      *
	 *                                     * Arrérages de rente avec nouvelle *                                      *
	 *                                     * rente                            *                                      *
     *                                     * Date début: date de départ       *                                      *
     *                                     * de la nouvelle rente             *                                      *
     *****************************************************************************************************************
	 * Défaut de remise                    * trimestre du 1er mandat retourné * trimestre du dernier mandat retourné *
	 *****************************************************************************************************************
	 */
	public Double calculerArrerage(Double MontantRenteTrimestrielle,
			Calendar dateDebut, Calendar dateFin) throws ExceptionMetier {
		Double arrerage = 0D;
		Integer jours = 0;
		Double resultArrerage = 0D;
		if (MontantRenteTrimestrielle != null 
				&& dateDebut != null 
				&& dateFin != null ) {

			jours = getDifDate(dateFin.getTimeInMillis(),
					dateDebut.getTimeInMillis(), false);
			arrerage = ((MontantRenteTrimestrielle * QUATRE) * jours) / TROIS_CENT_SOIXANTE;
			
			resultArrerage = new BigDecimal(arrerage).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
		}

		return (resultArrerage >= 0) ? resultArrerage : 0;
	}

	/**
	 * Dif date fin et début
	 * 
	 * @throws ExceptionMetier
	 * 
	 */
	private Integer getDifDate(Long dateFin, Long dateDebut, Boolean test)
			throws ExceptionMetier {

		int jours = 0;
		Long diffMillis = 0L;
		int diffenjours = 0;
		int nbrMois = 0;
		int restJour = 0;
		diffMillis = Math.abs(dateFin - dateDebut);

		diffenjours = (int) (diffMillis / (VINGT_QUATRE * SOIXANTE * SOIXANTE * MILLE));

	//MFBO@Correction suite réponse DOSI
		if (test == true) {
			
			if(diffenjours<28){
				
				jours = diffenjours;
				
			}else if(diffenjours == 28 || diffenjours == 29 || diffenjours == 30 || diffenjours == 31 ){
				
				jours = 1 * TRENTE;
				
			}else if(diffenjours > 31){
				
			nbrMois = (diffenjours / TRENTE);
			restJour = diffenjours % TRENTE;

				jours = nbrMois * TRENTE + restJour;
			}

		} else {

			jours = diffenjours;
		}

		return jours;

	}
	
	public Double calculerMntEquilibreParAyantDroit(Rentier rentierIn, Calendar dateCalculCoefAge)throws ExceptionMetier{
		
		Double mntQuittanceEquilibre = 0D;		
		Double ancienneRente = rentierIn.getMntAncienneRente();		
		Double nouvelleRente = rentierIn.getMontantRenteTrimestrielle();		
		Double coeffAge = transverseManager.getCoefficientAge(rentierIn, dateCalculCoefAge.getTime());		
		mntQuittanceEquilibre= (nouvelleRente - ancienneRente) * 4 * coeffAge;					
		return mntQuittanceEquilibre;
			
	}
}

