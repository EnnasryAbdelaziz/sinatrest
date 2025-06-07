package eai.devass.gsr.appli.usecase.metier.dossierRente;

import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class RentierConsulterUCConverter extends RentierVOConverter {

	public void voToItem(RentierVO vo, Rentier item) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
	}

}