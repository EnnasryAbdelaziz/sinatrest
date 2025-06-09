package eai.devass.sinistreat.appli.manager.sms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import rma.notification.service.impl.ws.GestionSmsWs;
import rma.notification.service.impl.ws.GestionSmsWsImplService;
import rma.notification.service.interfaces.ws.SmsResponseVO;
import rma.notification.service.interfaces.ws.SmsVO;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.instruction.Destinataire;
import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.modele.metier.instruction.InstructionPieceAt;
import eai.devass.sinistreat.appli.modele.metier.instruction.InstructionRejetAt;
import eai.devass.sinistreat.appli.modele.metier.instruction.PieceAt;
import eai.devass.sinistreat.appli.modele.metier.instruction.RejetAt;
import eai.devass.sinistreat.appli.modele.metier.instruction.TypeInstruction;
import eai.devass.sinistreat.appli.modele.metier.instruction.TypeLettreInstruction;
import eai.devass.sinistreat.appli.modele.metier.sinistre.NotificationSms;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.EmailIntermediaire;
import eai.devass.sinistreat.appli.modele.parametrage.ExclusionSms;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;
import eai.devass.sinistreat.appli.valueobjects.parametrage.ExclusionSmsVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class SMSManager extends EntiteManagerAbst implements IConstantes, IMessageException, IMessageInfo {

	private final IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(IDate.FORMAT_SIMPLE);

	public DateFormat format = new SimpleDateFormat(IDate.FORMAT_HHMM);
	private final Logger logger = Logger.getLogger("loggerGSR");

	private final SmsVO smsVO = new SmsVO();

	private final GestionSmsWs gestionSmsWs = new GestionSmsWsImplService().getPort(GestionSmsWs.class);

	public Date getDate() throws ParseException {
		return dateFormat.parse(dateFormat.format(new Date()));
	}

	public Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}

	public SmsResponseVO sendSMSImmediateHT(String telephone, String textSMS, String descriptif, Sinistre sinistre)
			throws PersistenceException {

		SmsResponseVO response = new SmsResponseVO();
		boolean envoyer = false;
		for (ExclusionSms excluSms : getlistExclusionSms()) {
			if (excluSms.getNumeropolice().equals(sinistre.getNumeroPolice())) {
				envoyer = true;
				break;
			}
			}
		if (!envoyer) {
			smsVO.setNumeroGSM(telephone);
			smsVO.setMessage(textSMS);
			buildSmsVO();
			creerEvenSms(descriptif, sinistre);
			return gestionSmsWs.sendSMSImmediate(smsVO);
		}

		return response;

	}

	public void creerEvenSms(String descriptif, Sinistre sinistre) throws PersistenceException {
		// TODO Auto-generated method stub
		NotificationSms notif = new NotificationSms();
		notif.setDatEnvoi(new Date());
		notif.setMouvement(descriptif);
		notif.setRefSinistre(sinistre);
		notif.setUserCreateur(sinistre.getUserModificateur());
		try {
			dao.createObject(notif);
		} catch (PersistenceException e) {
			logger.error(EXP_SUPPRESSION_AYANT_DROIT, e);
		}
	}

	public List<ExclusionSms> getlistExclusionSms() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from ExclusionSms";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public void buildSmsVO() {
		smsVO.setExpediteur("WS-RMA");
        smsVO.setObjet("WS-Objet");
        smsVO.setBranche("01");
        smsVO.setService("WS-Service");
        smsVO.setApplication("A01SINAT");
        smsVO.setCodeEvent("E01RSAT");
        smsVO.setCodeEventDec("ED01RMAD");
        smsVO.setCommentaire("WS-Comment");
	}
}
