package eai.devass.sinistreat.appli.usecase;


import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import eai.devass.edition.manager.RapportManager;
import eai.devass.sinistreat.appli.authentification.Utilisateur;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.manager.bnej.BnejManager;
import eai.devass.sinistreat.appli.manager.conciliation.ConciliationManager;
import eai.devass.sinistreat.appli.manager.contentieux.ContentieuxManager;
import eai.devass.sinistreat.appli.manager.fichemedicale.CertificatMedicalManager;
import eai.devass.sinistreat.appli.manager.instruction.InstructionManager;
import eai.devass.sinistreat.appli.manager.mission.MissionnementManager;
import eai.devass.sinistreat.appli.manager.recours.RecoursManager;
import eai.devass.sinistreat.appli.manager.reglement.ReglementManager;
import eai.devass.sinistreat.appli.manager.rest.RestManager;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.manager.sms.SMSManager;
import eai.devass.sinistreat.appli.restClient.IRestClient;
import eai.devass.sinistreat.appli.restClient.RestClient;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;
import eai.devass.sinistreat.appli.utils.mail.MailUtils;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.businessInterface.impl.MessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import rma.notification.service.impl.ws.GestionSmsWs;

@SuppressWarnings("unchecked")
public abstract class BaseUC extends FacadeServiceUseCase implements IMessageException, 
		IConstantes, IUseCase, IMessageInfo {

	protected ConverterTools converterTools = ConverterTools.getInstance();
	protected IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
	protected static final Logger logger = Logger.getLogger("loggerGSR");
	protected MailUtils mailUtils = new MailUtils();
	

	/** Managers */
	protected ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory.getService(ParametrageManager.class);
	protected SinistreManager sinistreManager = (SinistreManager) ServicesFactory.getService(SinistreManager.class);
	protected ReglementManager reglementManager = (ReglementManager) ServicesFactory.getService(ReglementManager.class);
	protected ContentieuxManager contentieuxManager = (ContentieuxManager) ServicesFactory.getService(ContentieuxManager.class);
	protected RecoursManager recoursManager = (RecoursManager) ServicesFactory.getService(RecoursManager.class);
	protected MissionnementManager missionManager =(MissionnementManager)ServicesFactory.getService(MissionnementManager.class);
	protected CertificatMedicalManager certificatMedicalManager =(CertificatMedicalManager)ServicesFactory.getService(CertificatMedicalManager.class);
	protected RapportManager rapportManager = (RapportManager) ServicesFactory.getService(RapportManager.class);
	protected ConciliationManager conciliationManager = (ConciliationManager) ServicesFactory.getService(ConciliationManager.class);
	protected InstructionManager instructionManager = (InstructionManager) ServicesFactory.getService(InstructionManager.class);
	protected BnejManager bnejManager = (BnejManager) ServicesFactory.getService(BnejManager.class);
	//protected SMSManager smsManager = (SMSManager) ServicesFactory.getService(SMSManager.class);
	//protected RestManager restManager = (RestManager) ServicesFactory.getService(RestManager.class);
	
	//protected GestionSmsWs gestionSmsWs = (GestionSmsWs) ServicesFactory.getService(GestionSmsWs.class);

	/** Variables globale des UCs */
	protected Utilisateur utilisateur;
	
	/** Définir le point d'entrer unique pour toutes les UCs */
	protected abstract void executerUC(IValueObject entite, HashMap params) throws Exception;
	
	protected void doExecuter(IValueObject entite, HashMap params) throws ValidationException {
		
		// Récupérer l'utilisateur via le service SAS
		utilisateur = (Utilisateur) params.get(IParam.UTILISATEUR);
		
		// Service Habilitation
		if(checkHabilitation()) {
			logger.info(" **** checkHabilitation **** ");
		}
		
		try {
			// Serivice WorkFlow
			if(checkWorkFlow()) {
				logger.info(" **** checkWorkFlow **** ");
				
			}
					
			// Exécuter le UC
			executerUC(entite, params);
		
		} catch(ValidationException e) {
			throw e;
			
		} catch(Exception e) {
			throw new ValidationException(e.getMessage());
		}
		
		
				
	}

	/**Récupérer le code message error depuis le IResult
	 * @param result
	 * @return
	 */
	public String getCodeMessageError(IResult result) {
		List<MessageItem> listMessageItem = result.getMessagesItem();
		for (MessageItem messageItem : listMessageItem) {
			if (messageItem instanceof ErrorMessageItem) {
				return ((ErrorMessageItem) messageItem).getCodeMessage();
			}
		}

		return null;
	}


	
	/** Pour vérifier si le contrôle des habilitation sera exécuter */ 
	public boolean checkHabilitation() {
		return false;
	}
	
	/** Pour vérifier si le contrôle du workflow sera exécuter */
	public boolean checkWorkFlow() {
		return false;
	}
	
	/** Pour vérifier si les actions 'ajouter journal' et 'affectation user'
	 * seront appelées */ 
	public boolean addJournalAndAffectationUser() {
		return false;
	}
	
	/** Pour vérifier si l'action 'add Echange' sera appelée */ 
	public boolean addEchange() {
		return false;
	}
	
	
}
