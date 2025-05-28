package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;


@SuppressWarnings("all")
public class InitMouvementUC extends InitAbstractMouvementUC {

	public void executeUC(IValueObject entite, HashMap params) throws Exception {

		

	}
	
	

	public boolean isTransactionnal() {
		return false;
	}

}