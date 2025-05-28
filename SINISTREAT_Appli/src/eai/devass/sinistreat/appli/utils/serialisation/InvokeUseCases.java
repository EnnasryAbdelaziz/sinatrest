package eai.devass.sinistreat.appli.utils.serialisation;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.WarnMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.Entete;
import eai.devass.sinistreat.appli.valueobjects.serialisation.GlobalVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.ResultVO;

@SuppressWarnings("all")
public class InvokeUseCases {

	private static Document xmlDoc = null;
	private static final String TRAITEMENT_OK = "1.111.1111";
	private static final String TRAITEMENT_KO = "0.000.0000";
	private Logger logger = Logger.getLogger("loggerSINAT");

	public String invoke(ByteArrayInputStream stream) {
		IResult lRes = null;
		Entete entete = new Entete();
		String xmlResponse = null;
		GlobalVO globalRetour = new GlobalVO(new ResultVO());
		try {

			String fluxXml = getFlux(stream);
			logger.debug(fluxXml);
			GlobalVO globalIn = SerialisationTools.xmlToObject(fluxXml);
			IValueObject vo = globalIn.getObject();
			entete = globalIn.getEntete();
			globalRetour.setEntete(entete);
			String actionId = entete.getFonction();
			OMNIAction action = new OMNIAction();
			action.setActionId(actionId);
			HashMap params = new HashMap();
			if (globalIn.getPager() != null) {
				params.put(IParam.PAGER, globalIn.getPager());
			}

			if (globalIn.getUser() != null) {
				params.put(IParam.UTILISATEUR, globalIn.getUser());
			}

			// INvok UC
			OMNIFacade facade = new OMNIFacade();
			lRes = facade.invokeService(null, action, vo, params);
			globalRetour.setPager(globalIn.getPager());

			// Tritement erreur messages
			traiteErreurResult(entete, lRes);

			// Traitement retour du UC
			ResultVO resultVO = (ResultVO) globalRetour.getObject();
			traiteReturnResult(lRes, resultVO);

		} catch (Exception e) {
			logger.error(e.toString());
			entete.setCodErreur(TRAITEMENT_KO);
			entete.setLibErreur(e.getMessage());

		} finally {
			try {
				xmlResponse = SerialisationTools.objectToXml(globalRetour);
				logger.debug(xmlResponse);

			} catch (Exception e) {
				logger.fatal(e.getMessage());
			}
		}

		return xmlResponse;
	}

	private static void setGrgInVO(IValueObject vo,
			GroupeDeGesionAdminVO adminVO) throws IllegalArgumentException,
			IllegalAccessException {
		Field[] lstFields = vo.getClass().getFields();
		if (lstFields != null && lstFields.length > 0) {
			for (int i = 0; i < lstFields.length; i++) {
				Field currentField = lstFields[i];
				if (currentField.getType().equals(GroupeDeGesionAdminVO.class)) {
					currentField.set(vo, adminVO);
				}
			}
		}

	}

	private static String getFlux(ByteArrayInputStream stream) throws Exception {

		SAXBuilder sxb = new SAXBuilder();
		Element element = null;
		Element root = null;
		// try {
		Reader reader = new InputStreamReader(stream, "Windows-1252");
		xmlDoc = sxb.build(reader);
		root = xmlDoc.getRootElement();
		Namespace namespace = Namespace.getNamespace("soap",
				"http://www.w3.org/2003/05/soap-envelope");
		Element body = root.getChild("Body", namespace);
		element = body.getChild("flux");

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

	private static WarnMessageItem hasWarnMessageItem(IResult result) {

		List<IMessageItem> listMessage = result.getMessagesItem();
		if (listMessage != null) {
			for (IMessageItem messageItem : listMessage) {
				if (messageItem instanceof WarnMessageItem) {
					return (WarnMessageItem) messageItem;
				}
			}
		}
		return null;
	}

	private void traiteReturnResult(IResult result, ResultVO resultVO)
			throws Exception {

		Object objectResult = result.getValueObject();
		if (objectResult == null) {
			return;
		}

		// for(Object objectResult : listObjectResult) {
		// if(objectResult == null)
		// continue;

		if (objectResult instanceof HashMap) {
			resultVO.setResult((HashMap) objectResult);
			return;

		}

		// List d objets
		if (CommonUtils.getInstance().isCollection(objectResult.getClass())) {
			List list = (List) objectResult;
			// correction sonar :Redundant nullcheck of value known to be
			// non-null
			if (!list.isEmpty()) {
				resultVO.addElement("list"
						+ list.get(0).getClass().getSimpleName(), list);
			}
			return;
		}

		// Si non
		resultVO.addElement(objectResult.getClass().getSimpleName(),
				objectResult);
		// }

	}

	private void traiteErreurResult(Entete entete, IResult lRes)
			throws Exception {

		if (lRes == null) {
			throw new Exception("Le résultat du contenu est null");
		}

		ErrorMessageItem errorMessageItem = hasErrorMessageItem(lRes);
		if (errorMessageItem != null) {
			throw new Exception(errorMessageItem.getCodeMessage());
		}

		InfoMessageItem infoMessageItem = hasInfoMessageItem(lRes);
		if (infoMessageItem != null) {
			entete.setCodErreur(TRAITEMENT_OK);
			entete.setLibErreur(infoMessageItem.getCodeMessage());
		} else {
			entete.setCodErreur(TRAITEMENT_OK);
			entete.setLibErreur("OK");
		}

		WarnMessageItem warnMessageItem = hasWarnMessageItem(lRes);
		if (warnMessageItem != null) {
			entete.setLibWarning(warnMessageItem.getCodeMessage());
		}

	}

}