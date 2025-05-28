package eai.devass.sinistreat.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.DetailReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EvenementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.AssuranceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatRecoursVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeRenteVO;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;

/**
 * Servlet implementation class EpEdition
 */
public class EditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private transient Logger logger = Logger.getLogger("loggerSINAT");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codeAction = (String) request.getParameter("codeAction");
		byte[] bytes = null;
		String isWord = "";
		String isExcel = "";
		EditionVO vo = new EditionVO();

		try {
			OMNIFacade facade = new OMNIFacade();
			OMNIAction action = new OMNIAction();
			action.setActionId(codeAction);
			vo = buildEditionVO(request);
			isWord = vo.getIsWord();
			isExcel = vo.getIsExcel();
			HashMap params = new HashMap();
			IResult resultat = facade.invokeService(null, action, vo, params);
			if (resultat != null && resultat.getValueObject() != null && resultat.getValueObject() instanceof ArrayList
					&& ((ArrayList) resultat.getValueObject()).size() > 0) {
				bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
			}
		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		// PDF or WORD
		if ("1".equals(isWord)) {
			// Ajouter le contenu du Word dans la réponse
			// response.setContentType("application/msword");
			logger.info("Edition en cours: " + vo.getFileName());
			if (vo.getFileName() != null && !"".equals(vo.getFileName())) {
				response.setHeader("Content-Disposition", "inline; filename=" + vo.getFileName() + ".docx");
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=Edition.docx");
			}
		} else if ("1".equals(isExcel)) {
			// Ajouter le contenu du Excel dans la réponse
			response.setContentType("application/vnd.ms-excel");
			logger.info("Edition en cours: " + vo.getFileName());
			if (vo.getFileName() != null && !"".equals(vo.getFileName())) {

				response.setHeader("Content-Disposition", "inline; filename=" + vo.getFileName() + ".xlsx");
				response.flushBuffer();
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=Edition.xlsx");
				response.flushBuffer();
			}
		}

		else {
			// Ajouter le contenu du Pdf dans la réponse
			response.setContentType("Application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=edition.pdf");

		}
		if (bytes != null) {
			OutputStream output = response.getOutputStream();
			output.write(bytes);
			output.flush();
			output.close();
		}

	}

	EditionVO buildEditionVO(HttpServletRequest request) {
		EditionVO vo = new EditionVO();
		// String logoPath = getServletContext().getRealPath("/img/logo.gif");
		String logoPath = getServletContext().getRealPath("/img/Logo_RMA.JPEG");
		vo.setLogoPath(logoPath);
		String jaspersPath = getServletContext().getRealPath("/jaspers");
		if (jaspersPath == null) {
			jaspersPath = "";
		}
		
		vo.setTypeConsignation(request.getParameter("typeConsignation"));
		vo.setModeReglement(request.getParameter("modeReglement"));
		vo.setJaspersPath(jaspersPath);
		vo.setDateJournee(request.getParameter("dateJournee"));
		vo.setDateDebut(request.getParameter("dateDebut"));
		vo.setNomUtilisateur(request.getParameter("nomUtilisateur"));
		vo.setDateFin(request.getParameter("dateFin"));
		vo.setEtat(request.getParameter("etat"));
		vo.setMinima(request.getParameter("minimum"));
		vo.setMaxima(request.getParameter("maximum"));
		vo.setVille(request.getParameter("ville"));
		vo.setNumeroSinistre(request.getParameter("numeroSinistre"));
		vo.setImprimer(request.getParameter("imprimer"));
		vo.setDecision(request.getParameter("decision"));
		vo.setNumeroQtc(request.getParameter("numeroQtc"));
		vo.setAge(request.getParameter("age"));
		vo.setNbreAnnee(request.getParameter("nbreAnnee"));
		String[] codesRubriques = request.getParameterValues("codesRubriques");
		List<String> lstCodesRubriques = new ArrayList<String>();
		if (codesRubriques != null) {
			for (int i = 0; i < codesRubriques.length; i++) {
				lstCodesRubriques.add(codesRubriques[i]);
			}
		}
		vo.setCodesRubriques(lstCodesRubriques);
		RecoursVO refRecoursVO = buildRecoursVO(request);
		vo.setRefRecoursVO(refRecoursVO);
		vo.setUtilisateur(request.getParameter("utilisateur"));
		vo.setNumRente(request.getParameter("numRente"));
		vo.setNumeroPolice(request.getParameter("numeroPolice"));
		vo.setCompagnie((request.getParameter("compagnie")));
		vo.setRentes((request.getParameter("rentes")));
		vo.setLienParente((request.getParameter("lienParente")));
		vo.setAgeMin((request.getParameter("ageMin")));
		vo.setAgeMax((request.getParameter("ageMax")));
		vo.setMode((request.getParameter("mode")));
		vo.setSituationMouvement((request.getParameter("situationMouvement")));
		// Evo VAG 2
		vo.setAnneeRef((request.getParameter("anneeRef")));
		vo.setAnneeRef1((request.getParameter("anneeRef1")));
		vo.setResultat((request.getParameter("resultat")));
		ReglementVO refReglementVO = buildReglementVO(request);
		vo.setRefReglementVO(refReglementVO);
		vo.setInstructionVO(buildInstructionVO(request));
		vo.setIsWord(request.getParameter("docx") != null ? request.getParameter("docx") : "0");
		vo.setIsExcel(request.getParameter("excel") != null ? request.getParameter("excel") : "0");
		vo.setContentieux(request.getParameter("contentieux") != null ? request.getParameter("contentieux") : "0");

		vo.setIdOffre(request.getParameter("offre"));
		vo.setUserConnected(request.getParameter("userConnected"));
		vo.setCodeUserConnected(request.getParameter("codeUserConnected"));
		vo.setDeces(request.getParameter("deces"));
		vo.setBackgroundPath(getServletContext().getRealPath("/img"));
		vo.setDuplicate(request.getParameter("duplicate"));
		vo.setDateAccident(request.getParameter("dateAccident"));
		RelanceConciliationVO rc = new RelanceConciliationVO();
		rc.setId(request.getParameter("relance") != null ? request.getParameter("relance") : "0");
		rc.setTypeRelance(request.getParameter("typeRlc") != null ? request.getParameter("typeRlc") : "");
		vo.setRefRelanceConciliation(rc);
		return vo;
	}

	/**
	 * Construire un objet de type RecoursVO
	 * 
	 * @param request
	 * @return RecoursVO
	 */

	InstructionVO buildInstructionVO(HttpServletRequest request) {
		InstructionVO instructionVO = new InstructionVO();
		instructionVO.setId(request.getParameter("instruction") != null ? request.getParameter("instruction") : "");
		instructionVO.setUserEditeur(
				request.getParameter("userConnected") != null ? request.getParameter("userConnected") : "");
		return instructionVO;
	}

	RecoursVO buildRecoursVO(HttpServletRequest request) {
		RecoursVO vo = new RecoursVO();
		vo.setNumeroRecours(request.getParameter("numeroRecours"));
		vo.setTypeRecours(request.getParameter("typeRecours"));

		EvenementVO refEvenement = new EvenementVO();
		refEvenement.setDateAccident(
				request.getParameter("dateAccident") != null ? request.getParameter("dateAccident") : "");
		refEvenement.setNumeroEvenement(request.getParameter("numeroEvenement"));

		SinistreVO refSinistre = new SinistreVO();
		refSinistre.setNumeroSinistre(
				request.getParameter("numeroSinistre") != null ? request.getParameter("numeroSinistre") : "");
		refSinistre.setNumeroPolice(
				request.getParameter("numeroPolice") != null ? request.getParameter("numeroPolice") : "");
		refSinistre
				.setNumeroGrave(request.getParameter("numeroGrave") != null ? request.getParameter("numeroGrave") : "");
		refSinistre.setNomIntermediaire(
				request.getParameter("nomIntermediaire") != null ? request.getParameter("nomIntermediaire") : "");

		refSinistre.setCodeClient(request.getParameter("assure"));
		refSinistre.setRefEvenement(refEvenement);

		EtatRecoursVO refEtatRecours = new EtatRecoursVO();
		refEtatRecours.setCode(request.getParameter("etatRecours"));

		AssuranceVO refCompagnie = new AssuranceVO();
		refCompagnie.setLibelle(request.getParameter("compagnie") != null ? request.getParameter("compagnie") : "");
		refCompagnie.setCode(request.getParameter("codeCompagnie") != null ? request.getParameter("codeCompagnie") : "");
		
		TypeRenteVO refrentes = new TypeRenteVO();
		refrentes.setLibelle(request.getParameter("rentes") != null ? request.getParameter("rentes") : "");

		vo.setRefEtatRecours(refEtatRecours);
		vo.setRefCompagnie(refCompagnie);
		vo.setDateDebut(request.getParameter("dateDebut"));
		vo.setDateFin(request.getParameter("dateFin"));

		vo.setUserCreateur(request.getParameter("userCreateur") != null ? request.getParameter("userCreateur") : "");

		VictimeVO refVictime = new VictimeVO();
		refVictime.setNomprenom(request.getParameter("nomVictime") != null ? request.getParameter("nomVictime") : "");

		refSinistre.setRefVictime(refVictime);
		vo.setRefSinistre(refSinistre);
		vo.setNomConducteur(request.getParameter("nomConducteur") != null ? request.getParameter("nomConducteur") : "");
		vo.setMarque(request.getParameter("marque") != null ? request.getParameter("marque") : "");
		vo.setImmatriculation(
				request.getParameter("immatriculation") != null ? request.getParameter("immatriculation") : "");

		return vo;
	}

	ReglementVO buildReglementVO(HttpServletRequest request) {
		ReglementVO vo = new ReglementVO();
		vo.setIdReglement(request.getParameter("rgl") != null ? request.getParameter("rgl") : "");
		vo.setTypeDestinataireCheque(
				request.getParameter("typeDestinataireCheque") != null ? request.getParameter("typeDestinataireCheque")
						: "");
		vo.setTypeBeneficiaire(
				request.getParameter("typeBeneficiaire") != null ? request.getParameter("typeBeneficiaire") : "");
		vo.setNumeroQuittance(
				request.getParameter("numeroQuittance") != null ? request.getParameter("numeroQuittance") : "");
		vo.setAdresseBenef(request.getParameter("adresse") != null ? request.getParameter("adresse") : "");
		vo.setVilleBenef(request.getParameter("ville") != null ? request.getParameter("ville") : "");
		vo.setNomIntermediaireRgl(
				request.getParameter("intermediaire") != null ? request.getParameter("intermediaire") : "");
		vo.setMontant(request.getParameter("montantQuittance") != null ? request.getParameter("montantQuittance") : "");
		vo.setAvocatTiers(request.getParameter("nomAvocat") != null ? request.getParameter("nomAvocat") : "");
		vo.setNbrEdition(request.getParameter("duplicate") != null ? request.getParameter("duplicate") : "");
		vo.setCodeUserModificateur(
				request.getParameter("codeUserModificateur") != null ? request.getParameter("codeUserModificateur")
						: "");
		vo.setNomUserModificateur(
				request.getParameter("nomUserModificateur") != null ? request.getParameter("nomUserModificateur") : "");
		EvenementVO refEvenement = new EvenementVO();
		refEvenement.setDateAccident(
				request.getParameter("dateAccident") != null ? request.getParameter("dateAccident") : "");
		refEvenement.setNumeroEvenement(
				request.getParameter("numeroEvenement") != null ? request.getParameter("numeroEvenement") : "");

		DetailReglementVO refDetailReglementVO = new DetailReglementVO();
		refDetailReglementVO.setMontant(
				request.getParameter("montantRubrique") != null ? request.getParameter("montantRubrique") : "");
		refDetailReglementVO.setDateDebutPrestation(
				request.getParameter("dateDebutPrestation") != null ? request.getParameter("dateDebutPrestation") : "");
		refDetailReglementVO.setDateFinPrestation(
				request.getParameter("dateFinPrestation") != null ? request.getParameter("dateFinPrestation") : "");
		List<DetailReglementVO> listDetailReglementVOs = new ArrayList<DetailReglementVO>();
		listDetailReglementVOs.add(refDetailReglementVO);
		vo.setListDetailReglement(listDetailReglementVOs);

		SinistreVO refSinistre = new SinistreVO();
		refSinistre.setNumeroSinistre(
				request.getParameter("numeroSinistre") != null ? request.getParameter("numeroSinistre") : "");
		refSinistre
				.setNumeroGrave(request.getParameter("numeroGrave") != null ? request.getParameter("numeroGrave") : "");
		refSinistre.setRefEvenement(refEvenement);
		refSinistre.setNomClient(request.getParameter("nomClient") != null ? request.getParameter("nomClient") : "");
		refSinistre.setSalaireJugement(
				request.getParameter("salaireJugement") != null ? request.getParameter("salaireJugement") : "");
		refSinistre
				.setIppJugement(request.getParameter("ippJugement") != null ? request.getParameter("ippJugement") : "");

		VictimeVO refVictime = new VictimeVO();
		refVictime.setNomprenom(request.getParameter("nomprenom") != null ? request.getParameter("nomprenom") : "");
		refVictime.setSalaireJournalier(
				request.getParameter("salaireJournalier") != null ? request.getParameter("salaireJournalier") : "");
		refVictime.setSalaireMensuel(
				request.getParameter("salaireMensuel") != null ? request.getParameter("salaireMensuel") : "");

		refSinistre.setRefVictime(refVictime);
		vo.setRefSinistre(refSinistre);

		return vo;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
