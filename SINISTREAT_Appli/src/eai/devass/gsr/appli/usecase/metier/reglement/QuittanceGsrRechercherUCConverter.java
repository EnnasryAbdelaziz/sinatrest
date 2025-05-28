package eai.devass.gsr.appli.usecase.metier.reglement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.reglement.ComplementQuitatnce;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.Societe;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.ModeReglement;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeQuittance;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.reglement.PrergltVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVOConverter;
import eai.devass.gsr.appli.valueobjects.metier.reglement.SituationEtatVO;
import eai.devass.sinistreat.appli.manager.CacheParametrageManager;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.modele.parametrage.Intermediaire;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;

@SuppressWarnings("all")
public class QuittanceGsrRechercherUCConverter implements IValueObjectConverter {

	private QuittanceGsrVOConverter quittanceVOConverter = new QuittanceGsrVOConverter();
	private CommonGsrUtils commonGsrUtils = CommonGsrUtils.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Logger logger = Logger.getLogger("loggerSINAT");
	private RentierManager rentierManager = (RentierManager) ServicesFactory
						.getService(RentierManager.class);
	private ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
						.getService(ParametrageManager.class);
	
	private CacheParametrageManager cacheParametrageManager = new CacheParametrageManager();

	public List<QuittanceGsr> convertValueObjectToItems(Object ovo) throws ValidationException {
		
		QuittanceGsrVO qtcVO = (QuittanceGsrVO) ovo;
		QuittanceGsr item = (QuittanceGsr) quittanceVOConverter.voToItem(qtcVO);
		
		// Pour la rechercher par trimestre et exercice
		if(qtcVO.getExercice() == null) {
			qtcVO.setExercice("");
		}
		if(qtcVO.getTrimestreExercice() == null) {
			qtcVO.setTrimestreExercice("");
		}
		
		
		String exercice = qtcVO.getExercice() + qtcVO.getTrimestreExercice();
		if(exercice.length() == 2 ) {
			exercice =  "%" + exercice;
		} 
		if(exercice.length() == 4 ) {
			exercice = exercice + "%";
		}
		
		// Code socité
		String codeSocite = qtcVO.getCodeSociete();
		if(!commonGsrUtils.isEmpty(codeSocite)) {
			Rentier rentier = new Rentier();
			DossierRente dossierRente = new DossierRente();
			dossierRente.setCompagnieRente(codeSocite);
			rentier.setRefDossierRente(dossierRente);
			item.setRefRentier(rentier);
		}
		
		item.setExercice(exercice);
		List<QuittanceGsr> res = new ArrayList<QuittanceGsr>();
		res.add(item);
		return res;
	}

	
	public Object convertItemsToValueObject(List item) {
		
		Map mapOut = new HashMap();
		List<QuittanceGsrVO> listOut = new ArrayList<QuittanceGsrVO>();
		if(commonGsrUtils.isEmpty(item)) {
			return mapOut;
		}
		
		List<QuittanceGsr> listQtc = (List<QuittanceGsr>) item.get(0);
		Integer nbrResult  = (Integer) item.get(1);
		boolean isCompleteConversion = (Boolean) item.get(2);
		mapOut.put("nbResultats", nbrResult);
		
		// Nbr de pages
		long nbrPages = 0;
		if(nbrResult > 30) {
			nbrPages = ((long) (nbrResult/30)) + 1;
		}
		mapOut.put("nbrPages", nbrPages);
		
		// Liste des quittances
		if(commonGsrUtils.isEmpty(listQtc)) {
			mapOut.put("listQuittanceGsrVO", new ArrayList<QuittanceGsrVO>());
			return mapOut;
		}
		
		QuittanceGsrVO quittanceGsrVO = null;
		List<SituationQuittanceGsr> listSitEtatQuittance = null;
		Intermediaire intermediaire = null;
		PrergltVO prergltVO = null;
		Rentier rentier = null;
		ComplementQuitatnce complementQuitatnce = null;
		Ville ville = null;
		NatureQtcGsr natureQuittance = null;
		
		for(QuittanceGsr cuGsr : listQtc) {
			quittanceGsrVO = (QuittanceGsrVO) quittanceVOConverter.itemToVo((QuittanceGsr) cuGsr);
			natureQuittance = cuGsr.getRefNatureQuittance();
			// PreReglement
			prergltVO = quittanceGsrVO.getRefPrerglt();
			if(prergltVO == null) {
				prergltVO = new PrergltVO();
				quittanceGsrVO.setRefPrerglt(prergltVO);
			}
			
			// Recuperer la societé du rentier
			rentier = cuGsr.getRefRentier();
			
			if(rentier != null && rentier.getRefDossierRente() != null) {
				quittanceGsrVO.setCodeSociete(rentier.getRefDossierRente().getCompagnieRente());
				}
			if(isCompleteConversion) {
				if(rentier != null && rentier.getRefDossierRente() != null) {
					quittanceGsrVO.setLibelleSociete(getLibelleCompagnie(rentier));
				}
				
				// Convertir l'historique situation mouvement
				quittanceGsrVO.setListSituationEtatVO(convertSitEtatQuittance(cuGsr
						.getRefSituationQuittanceGsr()));
				
				// Info intermediaires
				intermediaire = rentierManager.getIntermediaire(cuGsr.getRefRentier());
				if(intermediaire != null) {
					prergltVO.setIdsIntermediaire(intermediaire.getCode());
					prergltVO.setLblIntermediaiare(intermediaire.getLibelle());
				}
				
			}
			
			// Quittance modifiable (seulement le moyen de paiement)
			if(cuGsr.getRefEtatQtc() != null 
					&& (EtatQuittance.Reglee.getId() != cuGsr.getRefEtatQtc().getId()
					&& EtatQuittance.Attente_validation_supp.getId() != cuGsr.getRefEtatQtc().getId())
					&& EtatQuittance.Annulee.getId() != cuGsr.getRefEtatQtc().getId()) {
				quittanceGsrVO.setIsModifiable("true");
			}
			//WMOS 16/02/2016 correction problème mode réglement vir mandat
			if (cuGsr.getRefEtatQtc() != null 
					&& cuGsr.getRefEtatQtc().getId() == EtatQuittance.En_instance.getId()
					&& cuGsr.getRefSituationQuittanceGsr() != null
					&& cuGsr.getRefSituationQuittanceGsr().size() >= 2) {
				if (cuGsr.getRefSituationQuittanceGsr().get(0)
						.getRefEtatQtc().getId() == EtatQuittance.En_instance.getId()
						&& cuGsr.getRefSituationQuittanceGsr().get(1)
								.getRefEtatQtc().getId() == EtatQuittance.Annulee.getId()) {
					quittanceGsrVO.setIsModifiable("false");
				}
			}
			//Fin16/02/2016
			
			// Si elle est reglée, il faut que dateRegelment = date etat
			if(cuGsr.getRefEtatQtc() != null 
					&& (EtatQuittance.Reglee.getId() == cuGsr.getRefEtatQtc().getId())) {
				quittanceGsrVO.setDatePositionnement(dateFormat.format(cuGsr
						.getDatEtat().getTime()));
			}
			
			// Quitatnce creer manuellement peut etre modifier avant emission
			if(cuGsr.getRefComplementQuitatnce() != null 
					&& "true".equals(quittanceGsrVO.getIsModifiable())) {
				if(EtatQuittance.En_instance.getId() == cuGsr.getRefEtatQtc().getId()) {
					quittanceGsrVO.setIsCreationManuel("false");
				} else {
					quittanceGsrVO.setIsCreationManuel("true");
				}
				
				quittanceGsrVO.setIsModifiable("false");
			}
			
			// On peut annuler une quittance si = elle est en instance de
			// réglement ou réglée (SFD FGSR-7)
			// si la quittance est réglée alors sa nature doit etre différente
			// de : 34(Vir)-35(Aug)-36(Dim)-37(Vir trop percu) ou son mode de
			// réglement différent de Mandat
			// "EVOLUTION du 06/01/2016"

			if (cuGsr.getRefEtatQtc() != null
					&& EtatQuittance.En_instance.getId() == cuGsr
							.getRefEtatQtc().getId()) {

				// Seulement pour les quittance de reglement
				if (TypeQuittance.Reglement.getId() == cuGsr
						.getRefTypeQuittance().getId()) {
					quittanceGsrVO.setIsAnnualable("true");
				}
			} 
			else if (cuGsr.getRefEtatQtc() != null
					&& EtatQuittance.Reglee.getId() == cuGsr.getRefEtatQtc()
							.getId()
					&& NatureQuittance.Virement_Capital_constitutif.getId() != natureQuittance
							.getId()
					&& NatureQuittance.Augmentation_Capital_constitutif.getId() != natureQuittance
							.getId()
					&& NatureQuittance.Diminution_Capital_constitutif.getId() != natureQuittance
							.getId()
					&& 37 != natureQuittance.getId() 
					) {
				if(ModeReglement.Mandat.getId() != cuGsr.getRefModeReglement().getId())
				if (TypeQuittance.Reglement.getId() == cuGsr
						.getRefTypeQuittance().getId()) {
					quittanceGsrVO.setIsAnnualable("true");
				}
			}
			
			
			
			
			
			
			
			// Complement quittance
			complementQuitatnce = cuGsr.getRefComplementQuitatnce();
			if(complementQuitatnce != null) {
				quittanceGsrVO.setIdComplementQuittance(String
						.valueOf(complementQuitatnce.getIdentifiant()));
				if(complementQuitatnce.getDateDebut() != null) {
					quittanceGsrVO.setDateDebut(dateFormat
							.format(complementQuitatnce.getDateDebut()));
				}
				if(complementQuitatnce.getDateFin() != null) {
					quittanceGsrVO.setDateFin(dateFormat
							.format(complementQuitatnce.getDateFin()));
				}
				if(complementQuitatnce.getMontantCommission() != null) {
					quittanceGsrVO.setMontantTaxeCommission(String
									.valueOf(complementQuitatnce.getMontantCommission()));
				}
				quittanceGsrVO.setDetailMotif(complementQuitatnce.getDetailMotif());
				
				// Code motif
				listSitEtatQuittance = cuGsr.getRefSituationQuittanceGsr();
				if(!commonGsrUtils.isEmpty(listSitEtatQuittance)) {
					if(listSitEtatQuittance.get(0).getRefMotifSituationEtat() != null) {
						quittanceGsrVO.setCodeMotifCreation(listSitEtatQuittance
							.get(0).getRefMotifSituationEtat().getCode());
					}
				}
			}
			
			// Info ville
			try {
				prergltVO = quittanceGsrVO.getRefPrerglt();
				if(prergltVO != null) {
					ville = cacheParametrageManager.getVille(prergltVO.getCodeVille());
					if(ville != null) {
						prergltVO.setLibelleVille(ville.getLibelle());
					}
				}
			} catch(Exception e) {
				logger.error("problème technique",e);
			}
			
			// Pour le cheque il faut mettre dans le benef à l'ordre de 
			if(cuGsr.getRefModeReglement() != null 
					&& ModeReglement.Cheque.getId() == cuGsr.getRefModeReglement().getId()) {
				if(!commonGsrUtils.isEmpty(prergltVO.getPourLeCompte())) {
					quittanceGsrVO.setBeneficiaire(prergltVO.getPourLeCompte());
				}
			}
			
			listOut.add(quittanceGsrVO);
		}
		mapOut.put("listQuittanceGsrVO", listOut);		
		return mapOut;
	}

	public IValidator getValidator() {
		
		return null;
	}
	
	private List<SituationEtatVO> convertSitEtatQuittance(List<SituationQuittanceGsr> listSitEtatQtc) {
		if(commonGsrUtils.isEmpty(listSitEtatQtc)) {
			return null;
		}
		
		List<SituationEtatVO> listSitEtatQtcVO = new ArrayList<SituationEtatVO>();
		SituationEtatVO situationEtatVO = null;
		for(SituationQuittanceGsr curSituationQuittanceGsr : listSitEtatQtc) {
			situationEtatVO = new SituationEtatVO();
			situationEtatVO.setOperateur(curSituationQuittanceGsr.getOperateur());
			
			if(curSituationQuittanceGsr.getRefEtatQtc() != null) {
				situationEtatVO.setLblEtat(curSituationQuittanceGsr
						.getRefEtatQtc().getLibelle());
			}
			
			if(curSituationQuittanceGsr.getDateEtat() != null) {
				situationEtatVO.setDateOperation(dateFormat
						.format(curSituationQuittanceGsr.getDateEtat()));
			}
			listSitEtatQtcVO.add(situationEtatVO);
			
		}
		
		return listSitEtatQtcVO;
		
	}
	
	private String getLibelleCompagnie(Rentier rentier) {
		
		if(rentier == null || rentier.getRefDossierRente() == null) {
			return null;
		}
		
		List<Societe> societes = null;
		try {
			String codeCpm = rentier.getRefDossierRente().getCompagnieRente();
			if(commonGsrUtils.isNumeric(codeCpm)) {
				societes = parametrageManager
						.getListSociete(new Societe(Long.valueOf(codeCpm)));
			} else {
				return null;
			}
			
		} catch(Exception e) {
			return null;
		}
		if(!commonGsrUtils.isEmpty(societes)) {
			return societes.get(0).getLibelle();
		}
		return null;
	}

}
