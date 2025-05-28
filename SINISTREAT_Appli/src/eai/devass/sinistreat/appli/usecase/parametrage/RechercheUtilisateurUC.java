package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.HashMap;

import com.eai.org.accesseur.valueObject.ConsultationVO;
import com.eai.org.accesseur.valueObject.PointVenteVO;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.Intermediaire;
import eai.devass.sinistreat.appli.restClient.IRestClient;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class RechercheUtilisateurUC extends BaseUC{
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		
		Intermediaire intermediaire = (Intermediaire) this.getItem(Intermediaire.class);
	
		String typeIntermidiaire = "";
		ConsultationVO consultationVO = new ConsultationVO();
		consultationVO.setCodeUtilisateur("test");
		consultationVO.setCodeApplication("test");
		consultationVO.setModeAppel("T");
		consultationVO.setTypeAcces("U");
		consultationVO.setRadObjetInterroge("PDV");
		if (intermediaire.getCodeGuichet() != null && !intermediaire.getCodeGuichet().equals("")) {
			consultationVO.setIdBorneInf(IConstantes.CODE_POINT_VENTE_RMA + intermediaire.getCodeGuichet());
		} else {
			throw new FonctionnelleException("CODEGUICHET_OBLIGATOIRE");
		}
		
		//PointVenteVO pdv = ConsulterOrgService(consultationVO);
		IRestClient restClient = (IRestClient) ServicesFactory.getService(IRestClient.class);
		PointVenteVO pdv = restClient.ConsulterOrgService(consultationVO);
		if (pdv.getAbrTypePdv().equals("AGG")) {
			typeIntermidiaire = "A";
		}
		if (pdv.getAbrTypePdv().equals("BGD")) {
			typeIntermidiaire = "B";
		}
		if (pdv.getAbrTypePdv().equals("COU")) {
			typeIntermidiaire = "C";
		}
		if (pdv.getAbrTypePdv().equals("GUI")) {
			typeIntermidiaire = "W";
		}

		intermediaire.setCodeTypeIntermediaire(typeIntermidiaire);
		intermediaire.setCode(pdv.getCodeService().substring(1, 5));
		intermediaire.setLibelle(pdv.getAbrObjet());
		

		//intermediaire = parametrageManager.getIntermediaire(intermediaire);

		addResultItem(intermediaire);
		
	}
	public boolean isTransactionnal() {
		return true;
	}
}