package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation;
import eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVOConverter;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtAnnulationVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.RentierMvtVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.RentierMvtVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
 */

public class ConsulterMvtAnnulationUCConverter extends
ValueObjectConverterAbst {

	RentierMvtVOConverter rentierMvtVOConverter = new RentierMvtVOConverter();
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
		MvtAnnulationVO mouvementVO = null;
		MouvementVO mvtAnnuleVO = new MouvementVO();
		try
		{
			List<IEntite> listRentietMvt = (List<IEntite>) item.get(0);
			Mouvement mvtAnnule = (Mouvement) item.get(1);
			MvtAnnulation mouvement = (MvtAnnulation) item.get(2);
			
			// convertir mouvement
			mouvementVO = (MvtAnnulationVO) mouvementVOConverter
					.itemToVo(mouvement);
			
			// convertir mouvement anulle
//			mvtAnnuleVO = (MouvementVO) mouvementVOConverter
//					.itemToVo(mvtAnnule);
			
			rentierMvtVOConverter.convertMvtAnnule(mvtAnnuleVO, mvtAnnule);
			mouvementVO.setRefMvtAnnule(mvtAnnuleVO);
			// convertir liste Rentier Mouvement
			List<RentierMvtVO> listRentietMvtVO = (List<RentierMvtVO>) rentierMvtVOConverter
					.convertItemsToVos(listRentietMvt);
			
			mouvementVO.setRefsRentierMvt(listRentietMvtVO);
			
		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		return mouvementVO;
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
	public List<RentierMvt> doConvertValueObjectToItems(Object ovo)
	throws ValidationException {
		if(ovo instanceof RentierMvtVO ){
		RentierMvtVO vo = (RentierMvtVO) ovo;
		RentierMvt item = new RentierMvt();
		rentierMvtVOConverter.voToItem(vo, item);
		List<RentierMvt> res = new ArrayList<RentierMvt>();
		res.add(item);
		return res;
		}else{
			
			return null;
		}
		
	}
	
	

}
