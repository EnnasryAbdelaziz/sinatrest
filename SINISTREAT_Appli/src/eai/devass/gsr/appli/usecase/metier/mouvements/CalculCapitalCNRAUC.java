package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.commun.appli.util.DateUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtConsignCNRAVO;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;


public class CalculCapitalCNRAUC extends FacadeServiceUseCase {

	
	private TransverseManager transverseManager = (TransverseManager) ServicesFactory
			.getService(TransverseManager.class);
	private static final Logger logger = Logger.getLogger("loggerGSR");
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		
		MvtConsignCNRAVO mouvemenVO = (MvtConsignCNRAVO) entite;
		if(mouvemenVO == null || CommonGsrUtils.isEmpty(mouvemenVO.getRefRentier())) {
			throw new ExceptionMetier("L'identifiant du rentier ne peut être null !!");
		}
		
		List<RentierVO> listRentier = mouvemenVO.getListRentierVO();
		if(CommonGsrUtils.getInstance().isEmpty(listRentier)) {
			throw new ExceptionMetier("La liste des rentiers est vide !!!");
		}
		
		logger.error("########################################### POUR test @NY! : "+ mouvemenVO.getDatLimtePaiement());
		
		// Recuperer la date accident
		RentierVO rentierVO = listRentier.get(0);
		DossierRente dossierRente = getDossierSinistre(rentierVO.getId());		
		Date dateVersement = DateUtils.getDate("yyyyMMdd", mouvemenVO.getDatLimtePaiement()); 
		Rentier rentier = null;
		Double coefAge = null;
		Calendar cal = null;
		Double mntCalcule = 0D;
		Double mntCnra = 0D;
		Double totalMntCalcul = 0D;
		Double totalMntCnra = 0D;		
		for(RentierVO curRentierVO : listRentier) {
			
			if ( curRentierVO.getRefEtatRentier()!= null 
					&& (curRentierVO.getRefEtatRentier().getId()== EtatRente.Valide_En_Cours.getCode()
					|| curRentierVO.getRefEtatRentier().getId()== EtatRente.Suspendue_Ou_Attente.getCode()))
			{
			if(!DateUtils.isDate(curRentierVO.getDateNaissance())) {
				throw new ExceptionMetier("La date de naissaince du rentier : " 
						+ curRentierVO.getId() + " est non valide");
			}
			if(!CommonGsrUtils.isNumeric(curRentierVO.getMontantRenteTrimestrielle())) {
				throw new ExceptionMetier("Le montant de la rente du rentier : " 
						+ curRentierVO.getId() + " est non valide");
			}
			
			if(!CommonGsrUtils.isNumeric(curRentierVO.getMntCapitalCnra())) {
				throw new ExceptionMetier("Le montant capital CNRA du rentier : " 
						+ curRentierVO.getId() + " est non valide");
			}
			
			if(!CommonGsrUtils.isNumeric(curRentierVO.getLienParente())) {
				throw new ExceptionMetier("La classe du rentier : " 
						+ curRentierVO.getNom() + " est non valide");
			}
						
			rentier = new Rentier();
			rentier.setRefDossierRente(dossierRente);
			rentier.setLienParente(Long.valueOf(curRentierVO.getLienParente()));
			cal = new GregorianCalendar();
			cal.setTime(DateUtils.getDate("dd/MM/yyyy", curRentierVO.getDateNaissance()));
			rentier.setDateNaissance(cal);
			//ANO VERSEMENT DES CAPITAUX A LA CNRA 23/06/2016
			rentier.setId(Long.valueOf(curRentierVO.getId()));
			//Fin
			//Evol 02/10/2020: Remplacer getCoefficientAgeCNRA par getCoefficientAge
			coefAge = transverseManager.getCoefficientAge(rentier, dateVersement);
			mntCalcule = coefAge * CommonUtils.getInstance().stringToDouble(curRentierVO
							.getMontantRenteTrimestrielle()) * 4;
			totalMntCalcul += mntCalcule;
			mntCnra = CommonUtils.getInstance().stringToDouble(
					curRentierVO.getMntCapitalCnra());
			totalMntCnra += mntCnra;
			curRentierVO.setMntCapitalCnraCalcule(String.valueOf(mntCalcule));
			curRentierVO.setMntCapitalCnraDiff(String.valueOf(mntCnra - mntCalcule));
		}
	}

		mouvemenVO.setMntCNRA(String.valueOf(new BigDecimal(totalMntCnra).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue()));
		mouvemenVO.setCapitalCalcule(String.valueOf(new BigDecimal(totalMntCalcul).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue()));
		mouvemenVO.setDiffMntCapitalCnra(String.valueOf(new BigDecimal(totalMntCnra - totalMntCalcul).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue()));
		this.addResultItem(mouvemenVO);
	}

	public boolean isTransactionnal() {
		return false;
	}

	public boolean isTracable() {
		return false;
	}
	
	private DossierRente getDossierSinistre(String idRentier) throws Exception  {
		if(!CommonGsrUtils.isNumeric(idRentier)) {
			throw new ExceptionMetier("L'identifiant du rentier est obligatoire !!");
		}
		
		Rentier rentier = new Rentier(Long.valueOf(idRentier));
		Date dateAccident = transverseManager.getDateAccident(rentier);
		if(dateAccident == null) {
			throw new ExceptionMetier("Impossible de récuperer la date accident du sinistre");
		}
		
		Evenement evenement = new Evenement();
		evenement.setDateAccident(dateAccident);
		Sinistre sinistre = new Sinistre();
		sinistre.setRefEvenement(evenement);
		DossierRente dossierRente = new DossierRente();
		dossierRente.setRefSinistre(sinistre);
		return dossierRente;
		
	}

}