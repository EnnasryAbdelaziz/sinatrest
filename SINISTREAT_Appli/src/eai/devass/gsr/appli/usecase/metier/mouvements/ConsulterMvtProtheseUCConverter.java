package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVOConverter;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtProtheseVO;

/**
Converter du use case de recherche
@author Nom Prenom (email)
 */

public class ConsulterMvtProtheseUCConverter extends
ValueObjectConverterAbst {

	MouvementVOConverter mouvementVOConverter = new MouvementVOConverter();
	private Logger logger = Logger.getLogger("loggerSINAT");

	/**
	 * Methode qui convertit une entité à un Value Object
	 * 
	 * @param item
	 *            entité à convertir
	 * @returns Value Object resultat converti
	 */

	public Object doConvertItemsToValueObject(List item) {
		MvtProtheseVO mouvementVO = null;
		try
		{

			MvtProthese mvtProthese = (MvtProthese) item.get(0);
			
			// convertir mouvement
			mouvementVO = (MvtProtheseVO) mouvementVOConverter
					.itemToVo(mvtProthese);
	
			
		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		return mouvementVO;
	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
