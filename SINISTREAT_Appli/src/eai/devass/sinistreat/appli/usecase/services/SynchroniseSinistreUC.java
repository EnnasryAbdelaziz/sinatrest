package eai.devass.sinistreat.appli.usecase.services;


import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.synchronisationSinistre.factory.FactorySynchro;
import eai.devass.sinistreat.appli.synchronisationSinistre.valueObjects.PstSynchroChargesVO;
import eai.devass.sinistreat.appli.synchronisationSinistre.valueObjects.PstSynchroSinistreVO;

public class SynchroniseSinistreUC extends FacadeServiceUseCase {

    
	private Logger logger = Logger.getLogger("loggerGSR");
	private Long nbrDossierSinisterASynchro = 0L;
	private Long nbrDossierSinisterKO = 0L;
	private static final int MAX_SELECT = 10;
	private DecimalFormat decimalFormat = new DecimalFormat("#000000000");
	//private IResult result = new Result();
	private PstSynchroChargesVO chargesVO = null;
	private PstSynchroSinistreVO synchroSinistreVO = null;
	private FactorySynchro factorySynchro = FactorySynchro.getInstance();
	private Date date = new Date();
	private Map<String, String> mapQuery = new HashMap<String, String>();
	private Session session = null;
	private Transaction tx = null;
	private SinistreManager sinistreManager;
	
	
	protected void doExecuter(IValueObject vo, HashMap map) throws Exception {
    	
		// Debut traitement
		addMessage("Début synchronisation des dossier sinistre", Level.INFO, null);
		sinistreManager = new SinistreManager();
		
		// Recuperer le nombre des sinistres non synchroiser		
		try {
			nbrDossierSinisterASynchro = sinistreManager.getNbrSinistreToSynchronise();
			addMessage("Le nombre de dossier sinistre à synchroniser pour la journée est : " 
					+ nbrDossierSinisterASynchro, Level.INFO, null);
			if(nbrDossierSinisterASynchro.equals(0L)) {
				addMessage("Aucun dossier sinistre à synchroniser pour la journée ", Level.INFO, null);
				return;
			}
			
			// Recuperer la liste des sinistres non synchroiser 10 par 10 !!
			Long nbrIteration = Long.valueOf(decimalFormat
					.format((nbrDossierSinisterASynchro / MAX_SELECT))) + 1;
			addMessage("Le nombre d'iteration (par 10) est : " + nbrIteration, Level.INFO, null);		
			List<Sinistre> listSinistreASynchroniser = null;
			
			// Debut traitement			
			for(int i = 1; i <= nbrIteration; i++) {
				addMessage(" ******* Nous sommes à l'iteration : " + i, Level.INFO, null);
				
				// Session pour chaque iteration
				session = (Session) factorySynchro.getDao().newSession();
				listSinistreASynchroniser = sinistreManager.getListSinistreToSynchronise(MAX_SELECT, session);
				if(listSinistreASynchroniser == null || listSinistreASynchroniser.isEmpty()) {
					addMessage("La liste des sinistres à synchroniser est vide", Level.INFO, null);
					continue;
				}
				
				try {
					for(Sinistre curSinistre : listSinistreASynchroniser) {
						try {
							// begin transaction							
							tx = session.beginTransaction();
							taritSynchro(curSinistre);
							
							// OK
							//addMessage("Synchronisation ... [OK] : ", Level.INFO, null);
							tx.commit();
								
						} catch(Exception e) {
							addMessage("EXP : lors du taritement du dossier sinistre : " 
									+ curSinistre.getNumeroSinistre(), Level.FATAL, e);
							nbrDossierSinisterKO++;
							tx.rollback();
						} 
					}
				
				} catch(Exception e) {
					nbrDossierSinisterKO++;
					continue;
				}
				finally {
					session.close();
					addMessage(" ******* Fin de l'iteration : " + i, Level.INFO, null);
				}
			}
			
		} catch(Exception e) {
			addMessage("EXP lors de la synchronisation", Level.FATAL, e);
		}
		finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
			
			// Nbr des dossiers KO
			addMessage("Le nombres des dossiers non synchroiser est : "
					+ nbrDossierSinisterKO, Level.FATAL, null);
		}
	
    }

	private void convertSinistre(Sinistre curSinistre) throws Exception {
		
		try {
			synchroSinistreVO = factorySynchro.synchroConverter.chargerInfosSinistre(curSinistre);
			chargesVO = factorySynchro.synchroConverter.chargerInfosCharges(synchroSinistreVO);
			
		} catch(Exception e) {
			//addMessage("Impossible de convertir le sinistre", Level.FATAL, e);
			throw e;
		}
	}
	
	private void getInsertSqlQuery() throws Exception {
		try {
			
			String insertSinistre = factorySynchro.generateSqlQuery
					.generateInsertQuery(synchroSinistreVO);
			String insertCharge = factorySynchro.generateSqlQuery.generateInsertQuery(chargesVO);
//			addMessage("Query SINISTRE : " + insertSinistre, Level.INFO, null);
//			addMessage("Query CHARGE : " + insertCharge, Level.INFO, null);
			mapQuery.put("insertSinistre", insertSinistre);
			mapQuery.put("insertCharge", insertCharge);
			
			
		} catch(Exception e) {
			addMessage("Impossible de construire la requette sql des objets sinistre et charge", 
					Level.FATAL, e);
			throw e;
			
		}
	}
	
	private void synchronisRefeSinistre() throws Exception {
		
		IResult resultRef = factorySynchro.getReferentielDevAssBDC()
				.synchroniseDossierSinistreAT(mapQuery);
		if(resultRef == null) {
			addMessage("Le resultat IResult du REFERENTIEL est null !!", Level.FATAL, null);
			throw new Exception();
		}
		
		if(resultRef.hasErrorMessages()) {
			addMessage("Le resultat IResult du REFERENTIEL à romenté des execptions !! " + ",EXP :" 
					+ factorySynchro.commonGsrUtils.getMessageFromResult(resultRef), Level.FATAL, null);
			listerQuery();
			throw new Exception();
		}
	}
	
	private void taritSynchro(Sinistre curSinistre) throws Exception {
		
		// Debut transaction
		//addMessage("Début traitement du dossier : " + curSinistre.getNumeroSinistre(), Level.INFO, null);
								
		// Construire les objets sinistre et charge
		mapQuery.clear();
		try {
			convertSinistre(curSinistre);
			session.evict(curSinistre);
			
		} catch(Exception e) {
			throw e;
		}
		
		// Construire les query SQL pour les 2 objets
		try {
			getInsertSqlQuery();						
			
		} catch(Exception e) {
			throw e;
		}
		
		// Mettre à jour la date synchronisation du dossier sinistre, 
		// avant l'appel de service, pour gerer la transactionnalite !!!
		sinistreManager.updateDateSynchroSinistre(date,
				curSinistre.getNumeroSinistre(), session);
		session.flush();
		
		// Appel de serice referentiel
		try {
			synchronisRefeSinistre();
			
		} catch(Exception e) {
			throw e;
		}
	}
	
	private void addMessage(String msg, Level level,Exception e) {
		if(Level.INFO.equals(level)) {
			logger.info(msg);
			addMessageItem(new InfoMessageItem(">>> " +msg));
		}
		
		else if(Level.FATAL.equals(level)) {
			if(e == null) {
				logger.fatal(">>> " + msg);
				
			} else {
				logger.fatal(">>> " + msg, e);
			}
			addMessageItem(new ErrorMessageItem(">>> " + msg,
					(e != null) ? e.toString() : ""));
		}
	}
	
	private void listerQuery() {
		Set<Entry<String, String>> listQury = mapQuery.entrySet();
		for(Entry<String, String> curQury : listQury) {
			addMessage(curQury.getValue(), Level.INFO, null);
		}
		
		
	}
}

