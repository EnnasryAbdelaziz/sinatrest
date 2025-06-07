package eai.devass.sinistreat.appli.services.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.securite.IActionSecurise;
import ma.co.omnidata.framework.services.securite.impl.GenericActionImpl;
import ma.co.omnidata.framework.utile.Fonctions;

import org.apache.log4j.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;

import eai.devass.sinistreat.appli.services.IComplementQuittanceService;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.ComplementQuittanceVO;

@SuppressWarnings("all")
public class ComplementQuittanceService extends UnicastRemoteObject implements IComplementQuittanceService{

	private static final long serialVersionUID = 1L;
	
	private transient Logger logger = Logger.getLogger("loggerGSR");
	
	public ComplementQuittanceService() throws RemoteException {
		logger.info("ComplementQuittanceService RMI Service");
	}
	
	
	public IResult getComplementQuittance(String params) throws Exception {
		
		Date dateDebut = new Date();
		logger.info("Début d'appelle du service complement quittance; "+dateDebut.getTime()+" ;ms");
		
		OMNIFacade facade = new OMNIFacade();
		IActionSecurise action = new GenericActionImpl("1030");
		ComplementQuittanceVO quittanceVO = new ComplementQuittanceVO();
		
		//Ck11122014 : Au moment de la synchronisation, on mit à jour la quittance
		// Pour eviter les problemes causés par le Batch : numQuittance|dateSort|numCheque
		StringTokenizer vars = new StringTokenizer(params, "|");
		String numQuittance = vars.nextToken();
		String dateSort = null;
		String numCheque = null;
		HashMap map = null;
		if(vars.hasMoreTokens()) {
			dateSort =  vars.nextToken();
		}
		if(vars.hasMoreTokens()) {
			numCheque =  vars.nextToken();
		}
		
		if(dateSort != null || numCheque != null) {
			map = new HashMap();
			map.put("dateSort", dateSort);
			map.put("numCheque", numCheque);
		}
		
		quittanceVO.setNumeroQuittance(numQuittance);
		IResult result = facade.invokeService(null, action , quittanceVO, map);		
		if (Fonctions.isNotEmpty(result.getMessagesItem())) {
			ResourceBundleMessageSource bundle = (ResourceBundleMessageSource) ServicesFactory
					.getService(ResourceBundleMessageSource.class);
			
			for (IMessageItem messageItem : (List<IMessageItem>) result.getMessagesItem()) {
				String libelleMessage = bundle.getMessage(messageItem.getCodeMessage(), 
						messageItem.getValues(), null);
				
				if (messageItem instanceof InfoMessageItem) {
					((InfoMessageItem) messageItem).setLibelleMessage(libelleMessage);
				}
				if (messageItem instanceof ErrorMessageItem) {
					((ErrorMessageItem) messageItem).setLibelleMessage(libelleMessage);
				}
			}
		}
		
		Date dateFin = new Date();
		logger.info("Fin d'appelle du service complement quittance; "+(dateFin.getTime() - dateDebut.getTime()) + " ;ms");
		
		return result;
	}
	
	
	

}
