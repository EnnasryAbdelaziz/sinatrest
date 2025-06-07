package eai.devass.gsr.appli.usecase.metier.reglement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.MessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rmawatanya.moyenpaiement.application.metier.services.ordonnoncement.pub.IServiceOrdonnoncement;
import com.rmawatanya.moyenpaiement.application.metier.util.IConstants;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.OrdOrdonnoncementVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittancePositionnementVO;


@SuppressWarnings("all")
public class ValiderReglementSupUC extends SimpleBaseUC {
	
	private IServiceOrdonnoncement serviceOrdonnoncement;
	private String operateur;
	//private QuittanceGsrManager quittanceGsrManager;
	private Session session;
	static final String  ORDONNANCEMENT_SOUMIS_DELEGATION = "ORDONNANCEMENT.SOUMIS.DELEGATION";
	static final String UTILISATEUR_NON_ELIGIBLE = "UTILISATEUR.NON.ELIGIBLE";
	static final String ORD_ANNULE_OK = "ORD.ANNULE.OK";
	private List<SituationQuittanceGsr> listSitQtc = null;
	
	
	protected void execute(IValueObject entite, HashMap params) throws Exception {
		
		//quittanceGsrManager = (QuittanceGsrManager) ServicesFactory.getService(QuittanceGsrManager.class);
		serviceOrdonnoncement = (IServiceOrdonnoncement) ServicesFactory.getService(IServiceOrdonnoncement.class);
		QuittancePositionnementVO quittancePositionnementVO = (QuittancePositionnementVO) entite;
		if(quittancePositionnementVO == null) {
			throw new ExceptionMetier("La liste des réglement à valider est obligatoire !!!");
		}
		
		operateur = quittancePositionnementVO.getOperateur();
		List<QuittancePositionnementVO> listRegAValider = quittancePositionnementVO.getListQuittancePositionnementVO();
		if(commonUtils.isEmpty(listRegAValider)) {
			throw new ExceptionMetier("La liste des réglement à valider est obligatoire !!!");
		}
		
		List<String> errorMsg = new ArrayList<String>();
		
		// Valider la liste des reglement pour l operateur en cours
		session = getNewSession();
		Transaction tx = null;
		IResult result = null;
		boolean isValide = false;
		OrdOrdonnoncementVO ordOrdonnoncementVO = null;
		params.put(IConstants.USERID, operateur);
		
		EtatQtc etatQtc = new EtatQtc(EtatQuittance.Reglee.getId());
		try {
			for(QuittancePositionnementVO curPositionnementVO : listRegAValider) {
				try {
					tx = session.beginTransaction();
					listSitQtc = new ArrayList<SituationQuittanceGsr>();
					upDateQuittance(curPositionnementVO.getRefReglement(), etatQtc);
					session.flush();
					params.put(IConstants.ID_ORDONNONCEMENT, Long.valueOf(curPositionnementVO.getIdReglement()));
					result = serviceOrdonnoncement.soumettreOrdonnoncement(params, 2);
					if(result.hasErrorMessages()) {
						errorMsg.add("Error lors de validation du réglementr [" + curPositionnementVO.getRefReglement() + "], " 
								+ CommonGsrUtils.getInstance().getMessageFromResult(result));
						tx.rollback();
						continue;
					}
					
					// Dans le cas ou la validation est passé à son supperieuer
					isValide = ordonnancementValider(result);
					
					// Il faut changer l etat des quittances !!!
					for(SituationQuittanceGsr curSitQuittanceGsr : listSitQtc) {
						if(isValide) {
							// Mettre a jour l'etat de la quittance
							curSitQuittanceGsr.getRefQuittanceGsr().setRefEtatQtc(
									new EtatQtc(EtatQuittance.Reglee.getId()));
							
						} else {
							curSitQuittanceGsr.setRefEtatQtc(new EtatQtc(
									EtatQuittance.Attente_validation_supp.getId()));
						}
					}
					
					// OK
					tx.commit();
					
				} catch(Exception e) {
					tx.rollback();
					errorMsg.add("Error lors de validation du réglementr [" + curPositionnementVO.getRefReglement() + "], "
							+ e.getMessage());
				}
			}
		} catch(Exception e) {
			errorMsg.add("Error lors de validation du réglementr " + e.getMessage());
			
		} finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		
		// Add error messages
		if(!errorMsg.isEmpty()) {
			StringBuilder errors = new StringBuilder("Résulats positionnement : <br>");
			for(String curString : errorMsg) {
				errors.append("  *").append(curString).append("<br>");
			}
			addMessageItem(new ErrorMessageItem(errors.toString()));
		}
		
	}

	
	private void upDateQuittance(String refReglement, EtatQtc etatQtc) throws Exception {
		
		SituationQuittanceGsr situationQuittanceGsr = null;		
		List<QuittanceGsr> listQuittanceGsr = getQuittancesGsrRefReglement(refReglement);
		if(commonUtils.isEmpty(listQuittanceGsr)) {
			throw new Exception("Impossible de récuperer les quittances dont la ref. réglement est : " 
					+ refReglement);
		}
		
		for(QuittanceGsr curQuittanceGsr : listQuittanceGsr) {
			situationQuittanceGsr = new SituationQuittanceGsr();
			situationQuittanceGsr.setDateEtat(new Date());
			situationQuittanceGsr.setOperateur(operateur);
			situationQuittanceGsr.setRefQuittanceGsr(curQuittanceGsr);
			situationQuittanceGsr.setRefEtatQtc(etatQtc);
			session.save(situationQuittanceGsr);
			curQuittanceGsr.setDatEtat(new GregorianCalendar());
			listSitQtc.add(situationQuittanceGsr);
		}
	}
	
	private boolean ordonnancementValider(IResult result) throws Exception {
		
		// OK : ORDONNANCEMENT.SOUMIS.DELEGATION
		// NOK : UTILISATEUR.NON.ELIGIBLE
		
		MessageItem messageItem = (MessageItem) result.getMessagesItem().get(0);
		if(!(messageItem instanceof InfoMessageItem)) {
			throw new Exception("Impossible de récupérer l'etat de la validation");
		}
		
		if(ORDONNANCEMENT_SOUMIS_DELEGATION.equals(messageItem.getCodeMessage())) {
			return true;
		}
		
		else if(UTILISATEUR_NON_ELIGIBLE.equals(messageItem.getCodeMessage())) {
			return false;
		}
		
		else if(ORD_ANNULE_OK.equals(messageItem.getCodeMessage())) {
			return true;
		}
		
		else {
			return false;
		}
			
	}
	
	private List<QuittanceGsr> getQuittancesGsrRefReglement(String refRgl) throws Exception {
		
		return (List<QuittanceGsr>) session.createQuery("from QuittanceGsr where refReglement=?")
				.setString(0, refRgl).list();
	}
	
	@Override
	public boolean isTransactionnal() {
		return true;
	}

}


