package eai.devass.gsr.appli.usecase.metier.reglement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import com.rmawatanya.moyenpaiement.application.metier.services.ordonnoncement.pub.IServiceOrdonnoncement;
import com.rmawatanya.moyenpaiement.application.metier.util.IConstants;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.MoyenPaiementVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.OrdOrdonnoncementVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.PositionnementVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittancePositionnementVO;
import eai.devass.sinistreat.appli.utils.entites.IParam;


@SuppressWarnings("all")
public class ChercherReglementAValiderUC extends SimpleBaseUC {
	
	private IServiceOrdonnoncement serviceOrdonnoncement;
	
	
	protected void execute(IValueObject entite, HashMap params) throws Exception {
		
		serviceOrdonnoncement = (IServiceOrdonnoncement) ServicesFactory.getService(IServiceOrdonnoncement.class);
		QuittancePositionnementVO quittancePositionnementVO = (QuittancePositionnementVO) entite;
		if(quittancePositionnementVO == null) {
			throw new ExceptionMetier("La liste des quittances à traiter est obligatoire !!!");
		}
		
		String operateur = quittancePositionnementVO.getOperateur();
		
		// Checher la liste des reglements a valider pour l operateur
		params.put(IConstants.USERID, operateur);
		IResult result = serviceOrdonnoncement.ExtraireOrdonnoncementSuperieur(params, 2);
		if(result.hasErrorMessages()) {
			throw new ExceptionMetier("Error lors de récuperation des reglementrs : " 
					+ CommonGsrUtils.getInstance().getMessageFromResult(result));
		}
		
		List<OrdOrdonnoncementVO> listOrdOrdonnoncementVO = (List<OrdOrdonnoncementVO>) ((List) result
				.getValueObject()).get(0);
		
		if(commonUtils.isEmpty(listOrdOrdonnoncementVO)) {
			return;
		}
		
		List<QuittancePositionnementVO> listQtvPosVO = new ArrayList<QuittancePositionnementVO>();
		List<QuittanceGsrVO> listQuittanceVO = null;
		PositionnementVO positionnementVO = null;
		List<QuittanceVO> listQtcMoyPai = null;
		QuittanceGsrVO quittanceGsrVO = null;
		
		for(OrdOrdonnoncementVO curOrdonnoncementVO : listOrdOrdonnoncementVO) {
			quittancePositionnementVO = new QuittancePositionnementVO();
			positionnementVO = curOrdonnoncementVO.getPositionnement();
			
			// EVO , recuperer seulement les positionnemnt du service GSR
			if (!IParam.CODE_SERVICE_ORDONNATEUR_GSR.equals(positionnementVO
					.getCodeService())) {
				continue;
			}
			quittancePositionnementVO.setMontantPos(positionnementVO.getMntTotalQuittance());
			quittancePositionnementVO.setBeneficaire(positionnementVO.getNomBeneficiaire());
			quittancePositionnementVO.setEtat(curOrdonnoncementVO.getEtatOrd().name());
			quittancePositionnementVO.setDateReglement(positionnementVO.getDatOperation());
			quittancePositionnementVO.setIdReglement(String.valueOf(curOrdonnoncementVO.getId()));
			if(!commonUtils.isEmpty(positionnementVO.getRefMoyenPaiement())) {
				quittancePositionnementVO.setRefReglement(((MoyenPaiementVO) positionnementVO
								.getRefMoyenPaiement().get(0)).getRefReglement());
			}
			
			listQtcMoyPai = (List<QuittanceVO>) positionnementVO.getRefQuittance(); 
			listQuittanceVO = new ArrayList<QuittanceGsrVO>();
			for(QuittanceVO curQuittanceVO : listQtcMoyPai) {
				quittanceGsrVO = new QuittanceGsrVO();
				quittanceGsrVO.setNumeroQuittance(curQuittanceVO.getNumQuittance());
				quittanceGsrVO.setNumSinistre(curQuittanceVO.getNumDossier());
				listQuittanceVO.add(quittanceGsrVO);
			}
			
			quittancePositionnementVO.setListQuittanceGsrVO(listQuittanceVO);
			listQtvPosVO.add(quittancePositionnementVO);
			addResultItem(quittancePositionnementVO);
		}
		
		// EVO
		if(commonUtils.isEmpty(listQuittanceVO)) {
			return;
		}
		
		Map mapOut = new HashMap();
		mapOut.put("listQuittancePositionnementVO", listQtvPosVO);
		//addResultItem(listQtvPosVO);
		
	}

	
	
	
	
	@Override
	public boolean isTransactionnal() {
		return false;
	}

}


