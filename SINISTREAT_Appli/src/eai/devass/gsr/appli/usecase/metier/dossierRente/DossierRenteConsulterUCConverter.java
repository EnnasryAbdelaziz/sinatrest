package eai.devass.gsr.appli.usecase.metier.dossierRente;

import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVOConverter;


/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class DossierRenteConsulterUCConverter extends DossierRenteVOConverter {

	public void voToItem(DossierRenteVO vo, DossierRente item) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
	}
	
}