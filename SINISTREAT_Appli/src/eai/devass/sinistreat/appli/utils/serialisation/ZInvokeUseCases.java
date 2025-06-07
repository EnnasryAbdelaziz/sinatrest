package eai.devass.sinistreat.appli.utils.serialisation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.Entete;
import eai.devass.sinistreat.appli.valueobjects.serialisation.GlobalVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.ResultVO;

public class ZInvokeUseCases {

	private static Entete entete = new Entete();
	private static GlobalVO globalVO = new GlobalVO();
	private static Document xmlDoc = null;
	private static final String TRAITEMENT_OK = "1.111.1111";
	private static final String TRAITEMENT_KO = "0.000.0000";
	private static Logger logger = Logger.getLogger("loggerSINAT");

	public static String invoke(ByteArrayInputStream stream) {

		String fluxXml = getFlux(stream);
		((ILog) ServicesFactory.getService(ILog.class)).info("Flux en entrée :"
				+ fluxXml);
		logger.info("Flux en entrée :" + fluxXml);

		globalVO = ZSerialisationTools.deserialize(fluxXml);
		IValueObject vo = globalVO.getObject();
		entete = globalVO.getEntete();
		String actionId = entete.getFonction();
		OMNIAction action = new OMNIAction();
		action.setActionId(actionId);
		HashMap params = new HashMap();

		if (globalVO.getPager() != null) {
			params.put(IParam.PAGER, globalVO.getPager());
		}
		if (globalVO.getUser() != null) {
			params.put(IParam.UTILISATEUR, globalVO.getUser());
		}

		OMNIFacade facade = new OMNIFacade();
		IResult lRes = null;

		try {
			lRes = facade.invokeService(null, action, vo, params);

		} catch (Exception e) {
			// correction sonar :Nullcheck of value previously dereferenced 
				entete.setCodErreur(TRAITEMENT_KO);
				entete.setLibErreur(e.getMessage());
			
			// Penser à ajouter le LOG4J
				logger.error("Error invoke", e);
		}

		GlobalVO global = new GlobalVO();
		if (lRes != null) {
			ErrorMessageItem errorMessageItem = hasErrorMessageItem(lRes);
			if(entete==null){
				entete= new Entete();
			}
			if (errorMessageItem != null) {
				entete.setCodErreur(TRAITEMENT_KO);
				entete.setLibErreur(errorMessageItem.getCodeMessage());
			} else {
				InfoMessageItem infoMessageItem = hasInfoMessageItem(lRes);
				if (infoMessageItem != null) {
					entete.setCodErreur(TRAITEMENT_OK);
					entete.setLibErreur(infoMessageItem.getCodeMessage());
				} else {
					entete.setCodErreur(TRAITEMENT_OK);
					entete.setLibErreur("OK");
				}
			}

		}
		global.setEntete(entete);

		// Traitement retour du UC
		ResultVO resultVO = new ResultVO();
		PagerVO pagerVO = null;
		Object objectResult = null;
		if(lRes!=null){
			objectResult = lRes.getValueObject();
		}
		if (objectResult != null) {
			if (objectResult instanceof HashMap) {
				try {
					pagerVO = (PagerVO) ((HashMap) objectResult)
							.get(IParam.PAGER);
					((HashMap) objectResult).remove(IParam.PAGER);
				} catch (RuntimeException e) {
				}
				resultVO.setResult((HashMap) objectResult);
			} else {
				if (objectResult instanceof List) {
					List list = (List) objectResult;
					if (!list.isEmpty()) {
						resultVO.addElement("list"
								+ list.get(0).getClass().getSimpleName(), list);
					}
				} else {
					resultVO.addElement(
							objectResult.getClass().getSimpleName(),
							objectResult);
				}
			}
		}

		global.setObject(resultVO);
		if (pagerVO != null) {
			global.setPager(globalVO.getPager());
		}
		String xmlResponse = null;
		try {
			xmlResponse = ZSerialisationTools.serialize(global);
		} catch (Exception e) {
			entete.setCodErreur(TRAITEMENT_KO);
			entete.setLibErreur(e.getMessage());
			global.setEntete(entete);
			global.setObject(null);
			global.setPager(null);
			global.setUser(null);
			try {
				xmlResponse = ZSerialisationTools.serialize(global);
			} catch (Exception e1) {
				logger.error("Error invoke", e1);
			}
			logger.error("Error invoke", e);
		}
		logger.info("Flux en sortie :" + xmlResponse);
		((ILog) ServicesFactory.getService(ILog.class)).info("Flux en sortie :"
				+ xmlResponse);
		return xmlResponse;
	}

	private static String getFlux(ByteArrayInputStream stream) {
		SAXBuilder sxb = new SAXBuilder();
		Element element = null;
		Element root = null;
		try {
			Reader reader = new InputStreamReader(stream, "Windows-1252");
			xmlDoc = sxb.build(reader);
			root = xmlDoc.getRootElement();
			Namespace namespace = Namespace.getNamespace("soap",
					"http://www.w3.org/2003/05/soap-envelope");
			Element body = root.getChild("Body", namespace);
			element = body.getChild("flux");

		} catch (JDOMException e1) {
			entete.setLibErreur(e1.getMessage());
			logger.error("Error Flux", e1);
		} catch (IOException e1) {
			entete.setLibErreur(e1.getMessage());
			logger.error("Error Flux", e1);
		}
		return new XMLOutputter().outputString(element);
	}

	private static ErrorMessageItem hasErrorMessageItem(IResult result) {

		List<IMessageItem> listMessage = result.getMessagesItem();
		if (listMessage != null) {
			for (IMessageItem messageItem : listMessage) {
				if (messageItem instanceof ErrorMessageItem) {
					return (ErrorMessageItem) messageItem;
				}
			}
		}
		return null;
	}

	private static InfoMessageItem hasInfoMessageItem(IResult result) {

		List<IMessageItem> listMessage = result.getMessagesItem();
		if (listMessage != null) {
			for (IMessageItem messageItem : listMessage) {
				if (messageItem instanceof InfoMessageItem) {
					return (InfoMessageItem) messageItem;
				}
			}
		}
		return null;
	}

}