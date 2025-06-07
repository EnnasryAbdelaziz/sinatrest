package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InvaliderListeCertifContreVisiteUC extends BaseUC {

	// Recherche par num. de sinistre
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		CertificatMedical certificatMedical = (CertificatMedical) this
			.getItem(CertificatMedical.class);
		
		try {
			if(certificatMedicalManager.invaliderCertificatParIpp(certificatMedical)) {
				addResultItem(new InfoMessageItem("SUUCCES_INVALIDER_CERTIFICAT"));
			} else {
				addResultItem(new ErrorMessageItem("ECHEC_INVALIDER_CERTIFICAT"));
			}
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
