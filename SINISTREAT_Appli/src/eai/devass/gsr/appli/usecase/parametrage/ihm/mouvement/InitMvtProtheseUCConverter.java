/**
 * 
 */
package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVOConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVOConverter;

/**
 * @author elfaismo
 *
 */
@SuppressWarnings("unchecked")
public class InitMvtProtheseUCConverter extends
ValueObjectConverterAbst {

	ProtheseVOConverter proheseVOConverter = new ProtheseVOConverter();
	
	RentierVOConverter rentierVOConverter = new RentierVOConverter();
	private Logger logger = Logger.getLogger("loggerSINAT");
	/**
	 * Methode qui convertit une entité à un Value Object
	 * 
	 * @param item
	 *            entité à convertir
	 * @returns Value Object resultat converti
	 */

	public Object doConvertItemsToValueObject(List item) {

		
		List<ProtheseVO> listProheseVO = null;
		try
		{
			List<IEntite> listProhese = (List<IEntite>) item.get(0);
			
		
			// convertir liste Rentier Mouvement
				listProheseVO = (List<ProtheseVO>) proheseVOConverter
					.convertItemsToVos(listProhese);
			
		//	mouvementVO.setRefsRentierMvt(listRentietMvtVO);
			
		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		return listProheseVO;
	}

	/**
	 * Methode qui convertit un Value Object à une liste d' entités
	 * 
	 * @param ovo
	 *            value object à convertir
	 * @returns Listes des entités resultats convertis
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	public List<Rentier> doConvertValueObjectToItems(Object ovo)
	throws ValidationException {

		if(ovo instanceof RentierVO ){
			RentierVO vo = (RentierVO) ovo;
			Rentier item = new Rentier();
			rentierVOConverter.voToItem(vo, item);
			List<Rentier> res = new ArrayList<Rentier>();
			res.add(item);
			return res;
			}else{
				
				return null;
			}
		
		
		
	}

}
