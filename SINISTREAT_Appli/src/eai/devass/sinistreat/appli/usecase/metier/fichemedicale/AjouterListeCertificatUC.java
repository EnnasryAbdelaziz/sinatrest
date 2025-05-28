package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class AjouterListeCertificatUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<CertificatMedical> listCertificatMedical = this.getItems(CertificatMedical.class);
		try {
			List<CertificatMedical> listCertificatResult = certificatMedicalManager
					.ajouterListeCertificats(listCertificatMedical);

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
