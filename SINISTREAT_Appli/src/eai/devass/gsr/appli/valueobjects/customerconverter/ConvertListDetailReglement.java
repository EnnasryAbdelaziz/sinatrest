package eai.devass.gsr.appli.valueobjects.customerconverter;

import java.util.ArrayList;
import java.util.List;

import com.rmawatanya.reglement.application.metier.valueobjects.DetailQuittanceVO;

import eai.devass.commun.appli.converter.ICustomerConverter;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.QuittanceMoyenPaiement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;

@SuppressWarnings("all")
public class ConvertListDetailReglement implements ICustomerConverter {

	
	public Object getValuePropertyDest(Object entiteOrig) throws Exception {

		if (entiteOrig == null) {
			return null;

		} else {
			List listDetailPrestationVO = new ArrayList();
			DetailQuittanceVO detail = null;
			List<DetailReglement> listDetailRgl = ((QuittanceMoyenPaiement) entiteOrig).getListDetailReglement();
			if(CommonUtils.getInstance().isEmpty(listDetailRgl)) {
				return null;
			}
			
			for(DetailReglement curDetailReglement : listDetailRgl) {
				detail = new DetailQuittanceVO();
				detail.setPrestation(curDetailReglement.getLibellePrestation());
				listDetailPrestationVO.add(detail);
			}
						
			return listDetailPrestationVO;
		}
	}

	public Object getValuePropertyOrig(Object entiteDest) throws Exception {

		return null;

	}

}
