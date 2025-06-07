package eai.devass.gsr.appli.usecase.metier.reglement;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rmawatanya.moyenpaiement.application.metier.usecases.commun.pub.INatureQuittance.NatureQuittance;
import com.rmawatanya.moyenpaiement.application.metier.usecases.commun.pub.IPositionnementUC;
import com.rmawatanya.moyenpaiement.application.metier.util.IConstants;
import com.rmawatanya.moyenpaiement.application.metier.util.IEtatOrdonnoncement;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.ChequeSinistreVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.MondatPostalSinistreVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.OrdOrdonnoncementVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.PositionnementVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.PositionnementVO.TypeDestinataire;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.QuittanceVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.VirementSinistreVO;
import com.rmawatanya.reglement.application.metier.usecases.services.quittance.IEmissionQuittance;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.ModeReglement;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.utile.RibTools;
import eai.devass.gsr.appli.valueobjects.metier.reglement.PrergltVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittancePositionnementVO;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;

public class PostionnerQuittanceGsrUC extends SimpleBaseUC {

	private final static String ACTION_VALIDER_DATEREGLEMENT = "V";
	private final static String ACTION_VALIDER_POSTIONNEMENT = "P";
	private QuittanceGsrManager quittanceGsrManager;
	private IPositionnementUC positionnementUC;
	private String operateur;
	private List<SituationQuittanceGsr> listSitQuittanceGsrs;
	private Session session = null;

	protected void execute(IValueObject entite, HashMap params)
			throws Exception {

		quittanceGsrManager = (QuittanceGsrManager) ServicesFactory
				.getService(QuittanceGsrManager.class);
		positionnementUC = (IPositionnementUC) ServicesFactory
				.getService(IPositionnementUC.class);

		QuittancePositionnementVO positionnementVO = (QuittancePositionnementVO) entite;
		if (positionnementVO == null) {
			throw new ExceptionMetier(
					"La liste des quittances à traiter est obligatoire !!!");
		}

		operateur = positionnementVO.getOperateur();
		List<QuittanceGsrVO> listQuittance = positionnementVO
				.getListQuittanceGsrVO();
		if (commonUtils.isEmpty(listQuittance)) {
			throw new ExceptionMetier(
					"La liste des quittances à traiter est obligatoire !!!");
		}

		String action = positionnementVO.getAction();
		if (ACTION_VALIDER_DATEREGLEMENT.equals(action)) {
			traiterDateReglement(listQuittance);

		} else if (ACTION_VALIDER_POSTIONNEMENT.equals(action)) {
			traiterPositionnement(listQuittance);
		}

	}

	private void traiterDateReglement(List<QuittanceGsrVO> listQuittance)
			throws ExceptionMetier {

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date dateReglement = null;
		try {
			for (QuittanceGsrVO curQuittanceGsrVO : listQuittance) {
				if (!CommonUtils.isEmpty(curQuittanceGsrVO
						.getDatePositionnement())) {
					dateReglement = dateFormat.parse(curQuittanceGsrVO
							.getDatePositionnement());
				}

				quittanceGsrManager.updateDateReglement(
						Long.valueOf(curQuittanceGsrVO.getId()), dateReglement);
			}

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}

	private void traiterPositionnement(List<QuittanceGsrVO> listQuittance)
			throws ExceptionMetier {

		Transaction tx = null;

		try {
			session = getNewSession();

			// Il faut grouper les quittances suivant : le rentier et le mode
			// reglement (ms ici on traite mode par mode,
			// il st separres depuis la partie presentation
			Map<String, List<QuittanceGsrVO>> groupQtc = groupQuittance(listQuittance);

			// Construire le positionnementVO
			PositionnementVO positionnementVO = null;
			Set<Entry<String, List<QuittanceGsrVO>>> entrys = groupQtc
					.entrySet();
			HashMap params = new HashMap();
			params.put(IConstants.USERID, operateur);
			IResult result = null;
			OrdOrdonnoncementVO ordonnoncementVO = null;
			List<String> listErrors = new ArrayList<String>();
			String refReglement = "";
			boolean isHIERARCHIQUE = false;
			EtatQtc etatQtc = null;

			// Debut transaction On mit a jour l'etat de la quittance à regler,
			// et on positionne via l'appel de service
			// un par un !!
			for (Entry<String, List<QuittanceGsrVO>> cuEntry : entrys) {
				try {
					tx = session.beginTransaction();
					isHIERARCHIQUE = false;
					etatQtc = new EtatQtc(EtatQuittance.Reglee.getId());

					// Construire le positionnement des quittances
					if (commonUtils.isEmpty(cuEntry.getValue())) {
						throw new ExceptionMetier(
								"La liste des quittances est vide, impossible d'effectuer le positionnement !!!");
					}
					positionnementVO = getPositionnementVO(cuEntry);

					// flush la session
					session.flush();

					// Appel de service pour positionnement
					result = positionnementUC.positionnement(positionnementVO, params, 1);
					if (result.hasErrorMessages()) {
						tx.rollback();
						listErrors.add(CommonGsrUtils.getInstance().getMessageFromResult(result));
						continue;
					}

					ordonnoncementVO = (OrdOrdonnoncementVO) ((List) result
							.getValueObject()).get(0);

					// Recuperer la ref reglement
					refReglement = getRegReglement(result);
					if (refReglement == null) {
						throw new ExceptionMetier("La réference de reglement non générée pour le rentier ["
										+ cuEntry.getKey() + "]");
					}

					// Dans le cas ou la validation est passé à son supperieuer
					if (IEtatOrdonnoncement.HIERARCHIQUE
							.equals(ordonnoncementVO.getEtatOrd().getEtat())) {
						listErrors.add("Le réglement ["
										+ refReglement + "] est passé à votre résponsable pour validation");
						etatQtc.setId(EtatQuittance.Attente_validation_supp.getId());
						isHIERARCHIQUE = true;
					}

					// Il faut changer l etat des quittances !!!
					for (SituationQuittanceGsr curSitQuittanceGsr : listSitQuittanceGsrs) {
						if (isHIERARCHIQUE) {
							curSitQuittanceGsr.setRefEtatQtc(etatQtc);
						
						} 
						quittanceGsrManager.updateEtatQuittance(curSitQuittanceGsr
										.getRefQuittanceGsr().getId(), etatQtc, refReglement, session);
					}

					// OK : ordonnoncementVO
					tx.commit();

				} catch (Exception e) {
					tx.rollback();
					listErrors.add("Error lors du positionnement : " + e.getMessage());
					continue;
				}
			}

			// Add error messages
			if (!listErrors.isEmpty()) {
				StringBuilder errors = new StringBuilder("Résultats positionnement : <br>");
				for (String curString : listErrors) {
					errors.append("  *").append(curString).append("<br>");
				}
				addMessageItem(new ErrorMessageItem(errors.toString()));
			}

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	private PositionnementVO getPositionnementVO(
			Entry<String, List<QuittanceGsrVO>> cuEntry) throws Exception {

		// Construire la liste des auittance moy paiement
		List<QuittanceVO> listQtcMoyPai = new ArrayList<QuittanceVO>();
//		Double mntPositionnement = 0D;
		BigDecimal mntPositionnement = new BigDecimal(0);

		QuittanceGsrVO quittanceGsrVO = null;
		SituationQuittanceGsr situationQuittanceGsr = null;
		listSitQuittanceGsrs = new ArrayList<SituationQuittanceGsr>();
		for (QuittanceGsrVO curQuittanceGsrVO : cuEntry.getValue()) {
			listQtcMoyPai.add(convertFrom(curQuittanceGsrVO));
			mntPositionnement = mntPositionnement.add(new BigDecimal(curQuittanceGsrVO.getMontant()));

//			mntPositionnement += Double.valueOf(curQuittanceGsrVO.getMontant());
			quittanceGsrVO = curQuittanceGsrVO;
			situationQuittanceGsr = getSituationQuittanceGsr(curQuittanceGsrVO);
			session.save(situationQuittanceGsr);
			listSitQuittanceGsrs.add(situationQuittanceGsr);
		}
		PositionnementVO positionnementVO = new PositionnementVO();
		positionnementVO.setRefQuittance(listQtcMoyPai);
		positionnementVO
				.setMntTotalQuittance(String.valueOf(mntPositionnement));
		positionnementVO.setCodDevise(IEmissionQuittance.CODE_DEVISE_MAD);
		positionnementVO.setUtilisateur(operateur);
		positionnementVO.setTypeDestinataire(TypeDestinataire.AUTRE);
		positionnementVO.setCodeService(IParam.CODE_SERVICE_ORDONNATEUR_GSR);
		positionnementVO.setNomBeneficiaire(quittanceGsrVO.getBeneficiaire());

		// Construire le moyen de paiement
		if (ModeReglement.Cheque.getId() == quittanceGsrVO
				.getRefModeReglement().getId()) {
			positionnementVO
					.setRefMoyenPaiement(getChequeSinistreVO(quittanceGsrVO));

		} else if (ModeReglement.Mandat.getId() == quittanceGsrVO
				.getRefModeReglement().getId()) {
			positionnementVO
					.setRefMoyenPaiement(getMondatPostalSinistreVO(cuEntry
							.getValue()));

		} else if (ModeReglement.Virement.getId() == quittanceGsrVO
				.getRefModeReglement().getId()) {
			positionnementVO.setRefMoyenPaiement(getVirementSinistreVO(cuEntry
					.getValue()));
		}

		return positionnementVO;
	}

	public QuittanceVO convertFrom(QuittanceGsrVO quittanceGsrVO) {

		QuittanceVO quittanceVOMoyPai = new QuittanceVO();
		quittanceVOMoyPai.setNumQuittance(quittanceGsrVO.getNumeroQuittance());
		quittanceVOMoyPai.setNumDossier(quittanceGsrVO.getNumSinistre());
		quittanceVOMoyPai.setCodeBranche(IConstantes.BRANCHE_AT);
		quittanceVOMoyPai.setMntQuittanceRgl(quittanceGsrVO.getMontant());
		quittanceVOMoyPai.setNatureQuittance(NatureQuittance.REGLEMENT);
		return quittanceVOMoyPai;

	}

	public List<MondatPostalSinistreVO> getMondatPostalSinistreVO(
			List<QuittanceGsrVO> listQuittanceGsrVO) throws ExceptionMetier {
		MondatPostalSinistreVO mondatPostalSinistreVO = new MondatPostalSinistreVO();

		PrergltVO prergltVO = null;
		String beneficaiare = null;
		String numCin = null;
		int i = 0;
		StringBuilder vosRef = null;
		for (QuittanceGsrVO curQuittanceGsrVO : listQuittanceGsrVO) {
			prergltVO = curQuittanceGsrVO.getRefPrerglt();
			if (prergltVO == null) {
				throw new ExceptionMetier(
						"Impossible de trouver les informations du chéque de la quittance ["
								+ curQuittanceGsrVO.getNumeroQuittance()
								+ "], le prergltVO est null");
			}

			if (CommonGsrUtils.isEmpty(prergltVO.getNumCIN())) {
				// throw new
				// ExceptionMetier("Le N° CIN est obligatoire, quittance ["
				// + curQuittanceGsrVO.getNumeroQuittance() + "]");
				/*
				 * EVO : Si le num de CIN est connu, ce champ doit comprendre le
				 * num de CIN Sinon afficher: code société (uneposition)-Numéro
				 * de rente-classe (0,1,2,10,11,12,13,20,21,22,23….,40,41,42…)
				 * Code société: R pour RMA, A pour RMAWATANTA, W pour WATANAYA
				 * et M pour MGF Ex: A-345-0
				 */
				vosRef = new StringBuilder(
						CommonGsrUtils.getInstance().getRefSociter(
							curQuittanceGsrVO.getCodeSociete())).append("-")
							.append(curQuittanceGsrVO.getNumeroRente())
							.append("-").append(curQuittanceGsrVO.getClasse());
				prergltVO.setNumCIN(vosRef.toString());
			}

			beneficaiare = curQuittanceGsrVO.getBeneficiaire();
			if (i == 0) {
				numCin = prergltVO.getNumCIN();

			} else {
				if (!numCin.equals(prergltVO.getNumCIN())) {

					throw new ExceptionMetier("Les N° CIN du bénéficiaire ["
							+ beneficaiare
							+ "] du MONDAT POSTAL sont différents !! ["
							+ numCin + "#" + prergltVO.getNumCIN() + "]");
				}

				numCin = prergltVO.getNumCIN();
			}
			i++;
		}

		mondatPostalSinistreVO.setCinBeneficiaire(numCin);
		mondatPostalSinistreVO.setNomBeneficiaireMAD(beneficaiare);
		mondatPostalSinistreVO.setNomBeneficiairePaiement(beneficaiare);
		mondatPostalSinistreVO.setPeriodicite("Q");
		List<MondatPostalSinistreVO> list = new ArrayList<MondatPostalSinistreVO>();
		list.add(mondatPostalSinistreVO);
		return list;
	}

	public List<ChequeSinistreVO> getChequeSinistreVO(
			QuittanceGsrVO quittanceGsrVO) throws ExceptionMetier {
		ChequeSinistreVO chequeSinistreVO = new ChequeSinistreVO();
		PrergltVO prergltVO = quittanceGsrVO.getRefPrerglt();
		if (prergltVO == null) {
			throw new ExceptionMetier(
					"Impossible de trouver les informations du chéque de la quittance ["
							+ quittanceGsrVO.getNumeroQuittance()
							+ "], le prergltVO est null");
		}

		if (CommonUtils.isEmpty(prergltVO.getPourLeCompte())) {
			throw new ExceptionMetier(
					"Impossible de trouver les informations du chéque de la quittance ["
							+ quittanceGsrVO.getNumeroQuittance()
							+ "], Pour le compte de est obligatoire");
		}

		// RENTE N xxxx-00 R NOM PRENOM
		StringBuilder vosRef = new StringBuilder("RENTE N ")
				.append(quittanceGsrVO.getNumeroRente()).append(" - ")
				.append(quittanceGsrVO.getClasse()).append(" ")
				.append(CommonGsrUtils.getInstance().getRefSociter(
				quittanceGsrVO.getCodeSociete())).append(" ")
				.append(prergltVO.getPourLeCompte());

		// La dail du vor ref ne doit pas depasser 40
		chequeSinistreVO.setVosReference((vosRef.length() >= 40) ? vosRef
				.substring(0, 39).toString() : vosRef.toString());
		chequeSinistreVO.setCompteDe(prergltVO.getPourLeCompte());
		chequeSinistreVO.setEnvoyerA(prergltVO.getPourLeCompte());
		chequeSinistreVO.setAdresse(prergltVO.getAdresse());

		// Nos reference
		String nosRef = prergltVO.getNosReference();
		if (CommonGsrUtils.isEmpty(nosRef)) {
			nosRef = quittanceGsrVO.getNumeroQuittance() + "-"
					+ quittanceGsrVO.getNumSinistre();
		}
		chequeSinistreVO.setNosReference(nosRef);
		chequeSinistreVO.setVille(prergltVO.getLibelleVille());
		chequeSinistreVO.setNomBeneficiaire(quittanceGsrVO.getBeneficiaire());
		List<ChequeSinistreVO> list = new ArrayList<ChequeSinistreVO>();
		list.add(chequeSinistreVO);
		return list;
	}

	public List<VirementSinistreVO> getVirementSinistreVO(
			List<QuittanceGsrVO> listQuittanceGsrVO) throws Exception {

		PrergltVO prergltVO = null;
		String beneficaiare = null;
		String ribRentier = null;
		int i = 0;
		for (QuittanceGsrVO curQuittanceGsrVO : listQuittanceGsrVO) {
			prergltVO = curQuittanceGsrVO.getRefPrerglt();
			if (prergltVO == null) {
				throw new ExceptionMetier(
						"Impossible de trouver les informations du chéque de la quittance ["
								+ curQuittanceGsrVO.getNumeroQuittance()
								+ "], le prergltVO est null");
			}

			if (CommonGsrUtils.isEmpty(prergltVO.getNumRIB())) {
				throw new ExceptionMetier("Le RIB est obligatoire, quittance ["
						+ curQuittanceGsrVO.getNumeroQuittance() + "]");
			}
			beneficaiare = curQuittanceGsrVO.getBeneficiaire();
			if (i == 0) {
				ribRentier = prergltVO.getNumRIB();

			} else {
				if (!ribRentier.equals(prergltVO.getNumRIB())) {
					throw new ExceptionMetier("Les RIB du bénéficiaire ["
							+ beneficaiare
							+ "] du MONDAT POSTAL sont différents !! ["
							+ ribRentier + "#" + prergltVO.getNumRIB() + "]");
				}
				ribRentier = prergltVO.getNumRIB();
			}
			i++;
		}

		VirementSinistreVO virementSinistreVO = new VirementSinistreVO();
		virementSinistreVO.setLibVirement(prergltVO.getLblVirement()); 
		virementSinistreVO.setCodEtabliClient(RibTools.getCodeEtablissement(ribRentier));
		virementSinistreVO.setCodLocaliteClient(RibTools.getCodeLocalite(ribRentier));
		virementSinistreVO.setNumCompteClient(RibTools.getNumCompte(ribRentier));
		virementSinistreVO.setCleRibClient(RibTools.getCodeCle(ribRentier));
		virementSinistreVO.setRibClient(ribRentier);
		virementSinistreVO.setPeriodicite("Q");
		List<VirementSinistreVO> list = new ArrayList<VirementSinistreVO>();
		list.add(virementSinistreVO);
		return list;
	}

	private SituationQuittanceGsr getSituationQuittanceGsr(QuittanceGsrVO quittanceGsrVO) {
		SituationQuittanceGsr situationQuittanceGsr = new SituationQuittanceGsr();
		situationQuittanceGsr.setDateEtat(new Date());
		situationQuittanceGsr.setOperateur(operateur);
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		quittanceGsr.setId(quittanceGsrVO.getId());
		quittanceGsr.setNumeroQuittance(quittanceGsrVO.getNumeroQuittance());
		quittanceGsr.setMontant(commonUtils.stringToDouble(quittanceGsrVO.getMontant()));
		situationQuittanceGsr.setRefQuittanceGsr(quittanceGsr);
		situationQuittanceGsr.setRefEtatQtc(new EtatQtc(EtatQuittance.Reglee
				.getId()));
		return situationQuittanceGsr;
	}

	public String getRegReglement(IResult result) {

		if (!commonUtils.isEmpty(result.getMessagesItem())) {
			IMessageItem messageItem = (IMessageItem) result.getMessagesItem()
					.get(0);
			if (messageItem instanceof InfoMessageItem) {
				if ("refReglement".equals(messageItem.getCodeMessage())) {
					return messageItem.getLibelleMessage();
				}
			}
		}
		return null;
	}

	
	
	
	private Map<String, List<QuittanceGsrVO>> groupQuittance(List<QuittanceGsrVO> listQuittance) {
		
		// Il faut grouper suivant le mode de relements :
		// Cheque : à l'ordre de
		// Virement et mandat : sans groupement
		
		Map<String, List<QuittanceGsrVO>> groupQtc = new HashMap<String, List<QuittanceGsrVO>>();
		List<QuittanceGsrVO> curListQtc = null;
		for (QuittanceGsrVO curQuittanceGsrVO : listQuittance) {
			if (ModeReglement.Cheque.getId() == curQuittanceGsrVO
					.getRefModeReglement().getId()) {
				
				if (groupQtc.get(curQuittanceGsrVO.getBeneficiaire()) != null) {
					groupQtc.get(curQuittanceGsrVO.getBeneficiaire()).add(curQuittanceGsrVO);

				} else {
					curListQtc = new ArrayList<QuittanceGsrVO>();
					curListQtc.add(curQuittanceGsrVO);
					groupQtc.put(curQuittanceGsrVO.getBeneficiaire(), curListQtc);
				}
				
			} else {
				curListQtc = new ArrayList<QuittanceGsrVO>();
				curListQtc.add(curQuittanceGsrVO);
				groupQtc.put(curQuittanceGsrVO.getNumeroQuittance(), curListQtc);
			}
		}
		
		return groupQtc;
	}
	
	@Override
	public boolean isTransactionnal() {
		return false;
	}

}
