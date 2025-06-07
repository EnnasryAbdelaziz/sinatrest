package eai.devass.sinistreat.appli.manager.instruction;

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
import eai.devass.sinistreat.appli.modele.parametrage.DelegationRegionale;
import eai.devass.sinistreat.appli.modele.parametrage.EmailIntermediaire;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class InstructionManager extends EntiteManagerAbst implements
		IConstantes, IMessageException, IMessageInfo {

	private final IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);

	public DateFormat format = new SimpleDateFormat(IDate.FORMAT_HHMM);
	private final Logger logger = Logger.getLogger("loggerGSR");

	public Date getDate() throws ParseException {
		return dateFormat.parse(dateFormat.format(new Date()));
	}

	public void creerInstruction(Instruction instruction)
			throws PersistenceException, ParseException {
		instruction.setDateCreation(getDate());
		instruction.setEtatInstruction(ETAT_INSTRUCTION_CREATION);
		if (instruction.getInstructionPieceAt() != null) {
			for (int i = 0; i < instruction.getInstructionPieceAt().size(); i++) {
				InstructionPieceAt instructionPieceAt = instruction
						.getInstructionPieceAt().get(i);
								
				instructionPieceAt.setInstruction(instruction);
			}
		}
		if (instruction.getInstructionRejetAt() != null) {
			for (int i = 0; i < instruction.getInstructionRejetAt().size(); i++) {
				InstructionRejetAt instructionRejetAt = instruction
						.getInstructionRejetAt().get(i);
				
				instructionRejetAt.setInstruction(instruction);
			}
		}

		dao.createObject(instruction);
	}

	public Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}

	public List<PieceAt> getAllPieceAt() {
		List<PieceAt> listPieces = new ArrayList<PieceAt>();
		try {
			String requete = new String("from PieceAt");
			Query query = getSession().createQuery(requete);
			listPieces = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listPieces;

	}
	
	public List<RejetAt> getAllRejetsAt() {
		List<RejetAt> listRejets = new ArrayList<RejetAt>();
		try {
			String requete = new String("from RejetAt");
			Query query = getSession().createQuery(requete);
			listRejets = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listRejets;

	}

	public List<TypeLettreInstruction> getListTypeLettreInstruction() {
		List<TypeLettreInstruction> listTypeLettreInstruction = new ArrayList<TypeLettreInstruction>();
		try {
			String requete = new String("from TypeLettreInstruction");
			Query query = getSession().createQuery(requete);
			listTypeLettreInstruction = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listTypeLettreInstruction;
	}

	public List<TypeInstruction> getListInstruction() {
		List<TypeInstruction> listTypeInstruction = new ArrayList<TypeInstruction>();
		try {
			String requete = new String("from TypeInstruction");
			Query query = getSession().createQuery(requete);
			listTypeInstruction = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listTypeInstruction;
	}

	public List<DelegationRegionale> getListDelegation(){
		List<DelegationRegionale> listDelegationRegionale = new ArrayList<DelegationRegionale>();
		try {
			String requete = new String("from DelegationRegionale");
			Query query = getSession().createQuery(requete);
			listDelegationRegionale = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listDelegationRegionale;
	}
	public List<Destinataire> getListDestinataire() {

		List<Destinataire> listDestinataire = new ArrayList<Destinataire>();
		try {
			String requete = new String("from Destinataire");
			Query query = getSession().createQuery(requete);
			listDestinataire = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listDestinataire;
	}

	public List<Instruction> getInstructionByIdSinistre(Instruction i,
			PagerVO pagerVO) throws FonctionnelleException,
			PersistenceException {
		List<Instruction> listInstruction;

		if (i.getSinistre() == null) {
			throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
		}
		String sql = "from Instruction where sinistre.id ='"
				+ String.valueOf(i.getSinistre().getId())
				+ "' and etatInstruction <> '2' and categorieInstruction.code ='"
				+ i.getCategorieInstruction().getCode()
				+ "' Order by dateCreation DESC";
		Query q = getSession().createQuery(sql.toString());

		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}
			listInstruction = this.getPartCollectionByCondition(q, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
			return listInstruction;
		} else {
			q.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
			listInstruction = q.list();
			return listInstruction;
		}
	}

	public Integer getNombreInstructionByIdSinistre(Instruction i)
			throws FonctionnelleException, PersistenceException {
		Integer nbr = 0;
		Session sessionH = (Session) dao.getSession();
		if (i.getSinistre() == null) {
			throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
		}
		StringBuffer sql = new StringBuffer(
				"Select count (id) from Instruction where sinistre.id ='"
						+ String.valueOf(i.getSinistre().getId())
						+ "' and etatInstruction <> '2' and categorieInstruction.code ='"
						+ i.getCategorieInstruction().getCode() + "'");

		Query q = sessionH.createQuery(sql.toString());
		nbr = Integer.valueOf(((Long) q.uniqueResult()).intValue());
		return nbr;
	}

	private List getPartCollectionByCondition(Query query, int page,
			int pageSize) throws PersistenceException {
		if (query != null) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} else {
			return null;
		}
	}

	public void annulerInsruction(Instruction instruction)
			throws HibernateException, PersistenceException,
			FonctionnelleException, ParseException {
		Instruction instrDB = (Instruction) getSession().get(Instruction.class,
				instruction.getId());
		if (instrDB == null) {
			throw new FonctionnelleException(EXP_INSTRUCTION_ANNULATION);
		} else {
			instrDB.setUserModificateur(instruction.getUserModificateur());
			instrDB.setDateModification(getDate());
			instrDB.setEtatInstruction(ETAT_INSTRUCTION_ANNULATION);
			dao.updateObject(instrDB);
		}
	}

	public void editerInsruction(Instruction instruction)
			throws HibernateException, PersistenceException,
			FonctionnelleException, ParseException {
		Instruction instrDB = (Instruction) getSession().get(Instruction.class,
				instruction.getId());
		if (instrDB == null) {
			throw new FonctionnelleException(EXP_INSTRUCTION_EDITION);
		} else {
			instrDB.setEditer(Boolean.TRUE);
			instrDB.setUserEditeur(instruction.getUserEditeur());
			dao.updateObject(instrDB);
		}
	}

	public List<String> getSelectedPiecesByInstruction(String id) {
		List<String> selectedPieces = null;
		try {
			String sql = "Select i.pieceAt.libelle from InstructionPieceAt i where i.cocher= '1' and i.instruction.id ='"
					+ String.valueOf(id) + "'";
			Query q = getSession().createQuery(sql.toString());
			selectedPieces = q.list();
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_REGLEMENT, e);
		}

		return selectedPieces;
	}
	
	public List<String> getSelectedRejetsByInstruction(String id) {
		List<String> selectedRejets = null;
		try {
			String sql = "Select i.rejetAt.libelle from InstructionRejetAt i where i.cocher= '1' and i.instruction.id ='"
					+ String.valueOf(id) + "'";
			Query q = getSession().createQuery(sql.toString());
			selectedRejets = q.list();
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_REGLEMENT, e);
		}

		return selectedRejets;
	}

	public Integer getNombreInstructionByIdProcJud(Instruction i)
			throws FonctionnelleException, PersistenceException {
		Integer nbr = 0;
		Session sessionH = (Session) dao.getSession();
		if (i.getProcedureJudiciaire() == null) {
			throw new FonctionnelleException(EXP_PROC_JUD_INEXISTANTE);
		}
		StringBuffer sql = new StringBuffer(
				"Select count (id) from Instruction where procedureJudiciaire.id ='"
						+ String.valueOf(i.getProcedureJudiciaire().getId())
						+ "' and etatInstruction <> '2' and categorieInstruction.code ='"
						+ i.getCategorieInstruction().getCode() + "'");

		Query q = sessionH.createQuery(sql.toString());
		nbr = Integer.valueOf(((Long) q.uniqueResult()).intValue());
		return nbr;
	}

	public List<Instruction> getInstructionByIdProcJud(Instruction i,
			PagerVO pagerVO) throws FonctionnelleException,
			PersistenceException {
		List<Instruction> listInstruction;

		if (i.getProcedureJudiciaire() == null) {
			throw new FonctionnelleException(EXP_PROC_JUD_INEXISTANTE);
		}
		String sql = "from Instruction where procedureJudiciaire.id ='"
				+ String.valueOf(i.getProcedureJudiciaire().getId())
				+ "' and etatInstruction <> '2' and categorieInstruction.code ='"
				+ i.getCategorieInstruction().getCode()
				+ "' Order by dateCreation DESC";
		Query q = getSession().createQuery(sql.toString());

		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}
			listInstruction = this.getPartCollectionByCondition(q, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
			return listInstruction;
		} else {
			q.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
			listInstruction = q.list();
			return listInstruction;
		}
	}

	public Instruction getInstructionById(String  id)
			throws FonctionnelleException, PersistenceException {
		Instruction instruction;

		String sql = "from Instruction where id ='" + String.valueOf(id)
				+ "' ";
		Query q = getSession().createQuery(sql.toString());
		instruction = (Instruction) q.uniqueResult();
		return instruction;
	}
	
	//ajout methode de persistance du email intermediaire ENNASRY 12/2021 Lot1
	public void ajouterIntermediaire(EmailIntermediaire emailIntermediaire)
			throws PersistenceException, ParseException {		
		dao.createObject(emailIntermediaire);
	}
	
	public void updateIntermediaire(EmailIntermediaire emailIntermediaire) throws PersistenceException, ParseException {
		dao.updateObject(emailIntermediaire);
	}

}
