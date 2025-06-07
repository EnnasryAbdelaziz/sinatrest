package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class CreateCertificatMedicalUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		CertificatMedical certificatMedical= (CertificatMedical)this.getItem(CertificatMedical.class);
		CertificatMedical certificatResult = null;
		try {
//			if (certificatMedical.getRefSinistre() != null) {
//				
//				sinistreManager.modifierSinistreCertificat(certificatMedical
//						.getRefSinistre());
//			}
			certificatResult = (CertificatMedical) certificatMedicalManager
					.creerCertificatMedical(certificatMedical);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		addResultItem(certificatResult);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
