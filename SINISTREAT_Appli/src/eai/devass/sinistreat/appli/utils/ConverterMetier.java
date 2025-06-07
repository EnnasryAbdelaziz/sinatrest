package eai.devass.sinistreat.appli.utils;

import java.sql.Blob;

import org.apache.commons.beanutils.BeanUtils;

import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideQuittance;
import eai.devass.sinistreat.appli.utils.auth.OrgServicesTools;

public class ConverterMetier {

	private static ConverterMetier instance;
	protected OrgServicesTools orgServicesTools = OrgServicesTools.getInstance();
	
	public static ConverterMetier getInstance() {
		if(instance == null) {
			return new ConverterMetier();
		} else {
			return instance;
		}
	}
	

	// copy modele
	public void copySinistre(Sinistre sindb,Sinistre sin) throws Exception {
		Long id=sindb.getId();
		BeanUtils.copyProperties(sindb, sin);
		sindb.setId(id);
		
		
	}	
	public void copyEvenement(Evenement evenDB,Evenement even) throws Exception {
		Long id=evenDB.getId();
		BeanUtils.copyProperties(evenDB, even);
		evenDB.setId(id);
		
	}
	
	public void copyVictime(Victime vicDB,Victime vic) throws Exception {
		
		Long id=vicDB.getId();
		BeanUtils.copyProperties(vicDB, vic);
		vicDB.setId(id);
		
	}
	public void copyAyD(AyantDroit ayd,AyantDroit ay) throws Exception {
		Long id=ayd.getId();
		BeanUtils.copyProperties(ayd, ay);
		ayd.setId(id);
		
	}

	public void copyRecours(Recours recoursDB,Recours recours) throws Exception {
		Long id=recoursDB.getId();
		BeanUtils.copyProperties(recoursDB, recours);
		recoursDB.setId(id);
		
	}

	//copyCertificatMedical
	public void copyCertificatMedical(CertificatMedical certificatdb,CertificatMedical certificat) throws Exception {
		Long id=certificatdb.getId();

		BeanUtils.copyProperties(certificatdb, certificat);
		certificatdb.setId(id);
	}	
//	private String getLibelleEntiteFromCode(String codeEntite)throws FonctionnelleException {
//		String libelle=null;
//		if (codeEntite == null || codeEntite.equals("")) 
//			return "";
//		
//		List<EntiteOrgVO> listEntites=null;
//		try{
//			listEntites= orgServicesTools.getListEntite();
//		}
//		catch (Exception e) {
//			
//			return codeEntite;
//		}			
//		if(listEntites!=null){
//			for (Iterator iter = listEntites.iterator(); iter.hasNext();) {
//				EntiteOrgVO ent = (EntiteOrgVO) iter.next();
//				if(ent.getCode().equals(codeEntite))
//				{
//					libelle=ent.getLibelle();
//					break;
//				}
//			}
//		}
//
//		return (libelle!=null && !libelle .equals(""))? libelle : codeEntite ;
//	}
	

	public byte[] convertBlobToByte(Blob blob) {
		
		try {
			if(blob != null) {
				return blob.getBytes(1, (int) blob.length());
			}
			
		}catch(Exception e) {
			return null;
		}
		
		return null;
	}
	
	public void copyChQ(CourrierHybrideQuittance chqt,CourrierHybrideQuittance chq) throws Exception {
		String idLRH=chqt.getIdLRH();
		BeanUtils.copyProperties(chqt, chq);
		chqt.setIdLRH(idLRH);
	}	

	
	
}
