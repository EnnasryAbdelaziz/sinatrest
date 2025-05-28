package eai.devass.gsr.appli.usecase.metier.dossierRente;

import eai.devass.gsr.appli.modele.metier.dossierRente.Tuteur;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.TuteurVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.TuteurVOConverter;



/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TuteurConsulterUCConverter extends TuteurVOConverter {

	public void voToItem(TuteurVO vo, Tuteur item) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
	}

}