package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InvaliderListeCertificatUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<CertificatMedical> listCertificatMedical = this.getItems(CertificatMedical.class);
		try {
			List<CertificatMedical> listCertificatResult = certificatMedicalManager
					.invaliderListeCertificats(listCertificatMedical);
//			Double ippvalide = certificatMedicalManager
//					.getIppValide(listCertificatResult.get(0).getRefSinistre()
//							.getId());
//			Sinistre sin = (Sinistre) sinistreManager
//					.getSinistreById(listCertificatResult.get(0)
//							.getRefSinistre().getId());
//			if (ippvalide.compareTo(Double.valueOf(0))!= 0
//					&& ippvalide.compareTo(sin.getIpp()) != 0) {
//				sin.setIpp(ippvalide);
//				if (sin.getRefVictime() != null
//						&& sin.getRefVictime().getDeces()) {
//					sinistreManager.creerMouvementAY(sin);
//				} else {
//					sinistreManager.creerMouvement(sin);
//				}

			addResultItem(listCertificatResult);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
	}

	public boolean isTransactionnal() {
		return true;
	}

}
