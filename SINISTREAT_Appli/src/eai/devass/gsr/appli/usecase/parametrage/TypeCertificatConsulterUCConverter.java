package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.TypeCertificat;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeCertificatVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeCertificatVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TypeCertificatConsulterUCConverter extends TypeCertificatVOConverter {

	public void voToItem(TypeCertificatVO vo, TypeCertificat item) {
		item.setId(vo.getId());
	}

}