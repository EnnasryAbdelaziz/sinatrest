package eai.devass.gsr.appli.usecase.metier.dossierRente;

import eai.devass.gsr.appli.modele.metier.dossierRente.Certificats;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.CertificatsVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.CertificatsVOConverter;



/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class CertificatsConsulterUCConverter extends CertificatsVOConverter {

	public void voToItem(CertificatsVO vo, Certificats item) {
		item.setId(vo.getId());
	}

}