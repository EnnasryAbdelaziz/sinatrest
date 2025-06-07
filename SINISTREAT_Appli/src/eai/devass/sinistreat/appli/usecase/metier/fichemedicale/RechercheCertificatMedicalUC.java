package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercheCertificatMedicalUC extends BaseUC{

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		// TODO Auto-generated method stub
		List<CertificatMedical> listCertificatMedical = null;
		
		//CertificatMedical certificatMedical=(CertificatMedical)this.getItems(CertificatMedical.class);
		CertificatMedical certificatMedical = (CertificatMedical) getItem(CertificatMedical.class);
		
		//CertificatMedical certificatResult=null;
		try{
			//certificatResult=(CertificatMedical)certificatMedicalManager.rechercherCertificatMedical(certificatMedical);
			listCertificatMedical=(List)certificatMedicalManager.getListCertificat(certificatMedical);
			} catch (ConstraintViolationException e) {
				throw new FonctionnelleException(e);
			} catch (Exception e) {
				throw new FonctionnelleException(e.getMessage());
			}
			
			addResultItem(listCertificatMedical);
		
	}
	
	public boolean isTransactionnal() {
		return true;
	}
	
	

}