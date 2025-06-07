package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.HashMap;

import org.hibernate.exception.ConstraintViolationException;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale.CertificatMedicalVO;

public class SupprimerCertificatMedicalUC extends BaseUC{

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		// TODO Auto-generated method stub
		
		CertificatMedicalVO certificatMedicalVO=(CertificatMedicalVO)entite;
		//CertificatMedical certificatMedical=(CertificatMedical)this.getItems(CertificatMedical.class);
		CertificatMedical certificatMedical = (CertificatMedical) converterTools.convertToObjectBO(certificatMedicalVO);
		
		CertificatMedical certificatResult=null;
		try{
			certificatResult=(CertificatMedical)certificatMedicalManager.supprimerCertificatMedical(certificatMedical);
		
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
