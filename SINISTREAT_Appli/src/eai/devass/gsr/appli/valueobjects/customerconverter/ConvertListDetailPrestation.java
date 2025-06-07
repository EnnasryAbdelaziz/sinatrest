package eai.devass.gsr.appli.valueobjects.customerconverter;

import java.util.ArrayList;
import java.util.List;

import com.rmawatanya.reglement.application.metier.valueobjects.DetailQuittanceVO;

import eai.devass.commun.appli.converter.ICustomerConverter;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;

public class ConvertListDetailPrestation implements ICustomerConverter {

	public Object getValuePropertyDest(Object entiteOrig) throws Exception {

		if (entiteOrig == null) {
			return null;

		} else {
			List listDetailPrestationVO = new ArrayList();
			DetailQuittanceVO detail = new DetailQuittanceVO();
			QuittanceGsr quittance = (QuittanceGsr) entiteOrig;
			detail.setCodePrestation(quittance.getCodePrestation());
			detail.setMntPrestation(String.valueOf(quittance.getMontant()));
			detail.setNumQuittance(quittance.getNumeroQuittance());
			detail.setType("PRESTATION");
			
			// A verifier
			String codeGarantie = quittance.getCodeCategorie();
			if(codeGarantie == null) {
				Rentier rentier = quittance.getRefRentier();
				if(rentier != null) {
					DossierRente dossierRente = rentier.getRefDossierRente();
					if(dossierRente != null) {
						Sinistre sinistre = dossierRente.getRefSinistre();
						if(sinistre != null) {
							if(sinistre.getRefTypeGarantie() != null) {
								codeGarantie = sinistre.getRefTypeGarantie().getCode();
							}
						}
					}
				}
			}
			
			
			detail.setCodeGarantie(codeGarantie);
			listDetailPrestationVO.add(detail);
			return listDetailPrestationVO;
		}
	}

	public Object getValuePropertyOrig(Object entiteDest) throws Exception {

		return null;

	}

}
