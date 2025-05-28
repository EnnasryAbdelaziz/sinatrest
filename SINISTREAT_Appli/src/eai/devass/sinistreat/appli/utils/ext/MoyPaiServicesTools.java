package eai.devass.sinistreat.appli.utils.ext;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IResult;

import com.rmawatanya.moyenpaiement.application.metier.usecases.commun.pub.IReferencementMoyenPaiUC;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.MoyenPaiement;
import eai.devass.sinistreat.appli.utils.BundleTools;
import eai.devass.sinistreat.appli.utils.RmiTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MoyenPaiementVO;

@SuppressWarnings("unchecked")
public class MoyPaiServicesTools implements IMessageException {

	private static MoyPaiServicesTools instance;
	private static IResult result = null;

	/** Service RMI */
	private static RmiTools rmiTools = RmiTools.getInstance();

	/** Constantes */
	private final static String HOST_MP = "missionnement.moypai.host";

	public synchronized static MoyPaiServicesTools getInstance() {
		if (instance == null) {
			instance = new MoyPaiServicesTools();
		}

		return instance;
	}

	public List getListMoyenPaiment() throws FonctionnelleException, Exception {
		try {
			List<MoyenPaiementVO> listMoyenPaie = getCallServicesMoyPai();
			List listMoyenPaiement = convertToMoyenPaiementBO(listMoyenPaie);
			return listMoyenPaiement;
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
	}

	private List convertToMoyenPaiementBO(List<MoyenPaiementVO> listMoyenPaie)
			throws Exception {
		List listMoyenPaiement = new ArrayList();
		for (MoyenPaiementVO modePaiementVO : listMoyenPaie) {
			MoyenPaiement moyenPaiement = new MoyenPaiement();
			moyenPaiement.setCode(modePaiementVO.getCode());
			moyenPaiement.setLibelle(modePaiementVO.getLibelle());
			listMoyenPaiement.add(moyenPaiement);
		}
		return listMoyenPaiement;
	}

	private static List getCallServicesMoyPai() throws FonctionnelleException {
		try {
			IReferencementMoyenPaiUC referencementMoyenPaiUC = (IReferencementMoyenPaiUC) rmiTools
					.callService(IReferencementMoyenPaiUC.class,
							IReferencementMoyenPaiUC.SERVICE_NAME, BundleTools
									.getInstance().getDefaultMessage(HOST_MP));
			result = referencementMoyenPaiUC.listMoyenPaiementReglement(1);
			List<MoyenPaiementVO> listMoyPai = (List) ((List) result.getValueObject()).get(0);
			return listMoyPai;
		} catch (Exception e) {
			throw new FonctionnelleException(
					EXP_SERVICE_MOYEN_PAIE_INDISPONIBLE);
		}

		
	}

}
