package eai.devass.gsr.appli.usecase.metier.dossierRente;

import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVOConverter;



/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class ProthesesConsulterUCConverter extends ProtheseVOConverter {

	public void voToItem(ProtheseVO vo, Prothese item) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
	}

}