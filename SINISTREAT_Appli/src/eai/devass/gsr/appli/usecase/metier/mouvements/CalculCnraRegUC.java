package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

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
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtCnraReglementaireVO;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;


public class CalculCnraRegUC extends FacadeServiceUseCase {

	
	private TransverseManager transverseManager = (TransverseManager) ServicesFactory
			.getService(TransverseManager.class);
	private static final Logger logger = Logger.getLogger("loggerGSR");
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		
		MvtCnraReglementaireVO mouvemenVO = (MvtCnraReglementaireVO) entite;
		
		if(mouvemenVO == null || CommonGsrUtils.isEmpty(mouvemenVO.getRefRentier())) {
			throw new ExceptionMetier("L'identifiant du rentier ne peut être null !!");
		}
		
		if(CommonGsrUtils.isEmpty(mouvemenVO.getRefTypeConsignation())) {
			throw new ExceptionMetier("le Type Consignation ne peut être vide, Obligatoire pour effectuer le calcul !!");
		}
		if(CommonGsrUtils.isEmpty(mouvemenVO.getDatLimtePaiement())) {
			throw new ExceptionMetier("la date de prise en charge ne peut être vide, Obligatoire pour effectuer le calcul !!");
		}
		
		List<RentierVO> listRentier = mouvemenVO.getListRentierVO();
		if(CommonGsrUtils.getInstance().isEmpty(listRentier)) {
			throw new ExceptionMetier("La liste des rentiers est vide !!!");
		}
		
		RentierVO rentierVO = listRentier.get(0);
		DossierRente dossierRente = getDossierSinistre(rentierVO.getId());		
		Rentier rentier = null;
		Calendar cal = null;
		Date datePriseEnCharge = DateUtils.getDate("yyyyMMdd", mouvemenVO.getDatLimtePaiement()); 
		Double mntCalcule = 0D;
		Double mntCapitalDu = 0D;
		Double mntArrerage = 0D;
		Double totalMntCalcul = 0D;
		Double totalCapitalNet = 0D;
		Double totalReliquat = 0D;
		Double mntCapitalCnra = 0D;
		Double reliquatComplement = 0D;
		Double prorataCNRA = 0D;
		String CONSIGNATION_NOUVELLE_LIQUIDATION = "1";
		Calendar date1 = null;
		Calendar date2 = null;
		String prorataCNRAConv;
		for(RentierVO curRentierVO : listRentier) {
			
			if ( curRentierVO.getRefEtatRentier()!= null 
					&& (curRentierVO.getRefEtatRentier().getId()== EtatRente.Valide_En_Cours.getCode()
					|| curRentierVO.getRefEtatRentier().getId()== EtatRente.Suspendue_Ou_Attente.getCode()
					|| curRentierVO.getRefEtatRentier().getId()== EtatRente.Versee_CNRA.getCode()))
			{
				
				if(!CommonGsrUtils.isNumeric(curRentierVO.getMontantRenteTrimestrielle())) {
				throw new ExceptionMetier("Le montant de la rente du rentier est non valide");
			}
			
		
			if(!CommonGsrUtils.isNumeric(curRentierVO.getQuotePart())) {
				throw new ExceptionMetier("La valeur Quote part du rentier est obligatoire");
			}
			
			if(Double.parseDouble(curRentierVO.getQuotePart()) == 0) {
				throw new ExceptionMetier("La valeur Quote part du rentier doit être différente de Zéro");
			}
			
			
			if(!CommonGsrUtils.isNumeric(curRentierVO.getNbrTrimestreAregler())) {
				throw new ExceptionMetier("Le nombre de trimestres à regler du rentier est obligatoire");
			}

			if(!CommonGsrUtils.isNumeric(curRentierVO.getLienParente())) {
				throw new ExceptionMetier("La classe du rentier est non valide");
			}
		
		
			rentier = new Rentier();
			rentier.setRefDossierRente(dossierRente);
			rentier.setLienParente(Long.valueOf(curRentierVO.getLienParente()));
			cal = new GregorianCalendar();
			cal.setTime(DateUtils.getDate("dd/MM/yyyy", curRentierVO.getDateNaissance()));
			rentier.setDateNaissance(cal);
			rentier.setId(Long.valueOf(curRentierVO.getId()));
			// Calcul Reserve Rentier
			if(mouvemenVO.getRefTypeConsignation().equals(CONSIGNATION_NOUVELLE_LIQUIDATION)){	
				//Evol 02/10/2020: Remplacer getCoefficientAgeCNRA par getCoefficientAge
				Double coefAge_DateSys = transverseManager.getCoefficientAge(rentier,new Date());
				mntCalcule = coefAge_DateSys*CommonUtils.getInstance().stringToDouble(
											curRentierVO.getMontantRenteTrimestrielle())* 4;
				}
				totalMntCalcul += mntCalcule;
				curRentierVO.setMntCapitalCnraCalcule(String
						.valueOf(mntCalcule));
			// Fin		
			// Calcul Capital dû
				Double coefAge_datePriseEnCharge = transverseManager.getCoefficientAgeCNRAReg(rentier,datePriseEnCharge);
				mntCapitalDu = coefAge_datePriseEnCharge*CommonUtils.getInstance().stringToDouble(
						curRentierVO.getMontantRenteTrimestrielle())* 4;
				curRentierVO.setMntCapitalDu(String
						.valueOf(mntCapitalDu));
			// Fin
			// Calcul Montant des arrerages
				mntArrerage = CommonUtils.getInstance().stringToDouble(
						curRentierVO.getMontantRenteTrimestrielle())*
						CommonUtils.getInstance().stringToDouble(curRentierVO.getNbrTrimestreAregler());
				
				curRentierVO.setMntArrerage(String
						.valueOf(mntArrerage));
			// Fin
			
			// Calcul Prorata CNRA
				if(mouvemenVO.getRefTypeConsignation() != null) {
					if(mouvemenVO.getRefTypeConsignation().equals("1") 	|| mouvemenVO.getRefTypeRevision().equals("1") 	|| mouvemenVO.getRefTypeRevision().equals("3"))
					{
						if(( rentier.getLienParente()>=20 && rentier.getLienParente() <=29) || (rentier.getLienParente()>=40 && rentier.getLienParente() <=59) )
						{
							cal = new GregorianCalendar();
							Calendar ca = new GregorianCalendar();
							date2 = new GregorianCalendar();
							int annee = ca.get(Calendar.YEAR);
							cal.setTime(DateUtils.getDate("dd/MM/yyyy", curRentierVO.getDateNaissance()));
							rentier.setDateNaissance(cal);
							date1 = new GregorianCalendar();
							date1.set(annee, rentier.getDateNaissance().get(Calendar.MONTH), rentier.getDateNaissance().get(Calendar.DAY_OF_MONTH));
							
							date2.set(annee, 11, 31);
							System.out.println("date 1 :" + date1 + "date 2 : "+ date2);
							Date dateNaissAnneeEncours = date1.getTime();
							Date dateAnneeEncours = date2.getTime();
							double	nj = CommonGsrUtils.getInstance().getjoursBetweenTwoDates(dateNaissAnneeEncours,  dateAnneeEncours);
							Integer ageCnra =  CommonGsrUtils.getInstance().getAgeBetweenTwoDatesCNRA(
									rentier.getDateNaissance().getTime(), datePriseEnCharge);
							if( ageCnra <= 16){
								prorataCNRA = CommonUtils.getInstance().stringToDouble(curRentierVO.getMontantRenteTrimestrielle())* 4 *(nj/360)*Math.pow(1.035, ageCnra-16);
							} else {
								prorataCNRA = (double) 0;
							}
						}
					}
					else 
					{
				    prorataCNRA = (double) 0;
					}
					prorataCNRAConv = CommonUtils.getInstance().limiterchiffreVirgule(prorataCNRA);
					curRentierVO.setProrataCNRA(prorataCNRAConv);
				}
			//Fin
			// Calcul Capital Net 
				mntCapitalCnra = mntCapitalDu + prorataCNRA - mntArrerage;
				curRentierVO.setMntCapitalCnra(String.valueOf(mntCapitalCnra));
				totalCapitalNet += mntCapitalCnra;
			// Fin
			// Calcul Reliquat Complement
				reliquatComplement = mntCalcule - mntCapitalCnra;
				curRentierVO.setMntReliquat(String.valueOf(new BigDecimal(reliquatComplement).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue()));
				totalReliquat += reliquatComplement;
			// Fin
			}
	}
		mouvemenVO.setCapitalCalcule(String.valueOf(new BigDecimal(totalMntCalcul).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue()));
		mouvemenVO.setTotalCapitalNet(String.valueOf(new BigDecimal(totalCapitalNet).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue()));
		mouvemenVO.setTotalReliquat(String.valueOf(new BigDecimal(totalReliquat).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue()));
		

		this.addResultItem(mouvemenVO);
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

	public boolean isTransactionnal() {
		return false;
	}

	public boolean isTracable() {
		return false;
	}
	

}