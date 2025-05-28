package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.manager.CacheParametrageManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.Pays;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.utils.ConverterTools;

public class VictimeVOConverter implements IValueObjectConverter  {

	private CacheParametrageManager cacheParametrageManager = new CacheParametrageManager();
	private Logger logger= Logger.getLogger("loggerSINAT");

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		VictimeVO vo = new VictimeVO();
		if (itemList.get(0) instanceof Victime) {
			itemToVo(vo, (Victime) itemList.get(0), itemList);
		}

		return vo;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException, ValidationUnitaireException {
		VictimeVO vo = (VictimeVO) ovo;
	
		Victime item = new Victime();
		voToItem(vo, item);
		List<Victime> itemList = new ArrayList<Victime>();
		itemList.add(item);
		return itemList;
	}

	public IValidator getValidator() {
		return null;
	}

	public IValueObject itemToVo(VictimeVO vo, Victime item, List itemList) {
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
            vo = (VictimeVO) converterTools.getInstance().convertToObjectVO(
                    item);
			
			// Libelle ville
			try {
                Ville ville = cacheParametrageManager.getVille(vo
                        .getCodeVille());
				if(ville != null) {
					vo.setVilleLibelle(ville.getLibelle());
				}
			
			} catch(Exception e) {
				Logger.getLogger("loggerGSR").fatal(
						"Erreur lors de la conversion !", e);
			}
			
			// Libelle pays
			try {
				Pays pays = cacheParametrageManager.getPays(vo.getCodePays());
				if(pays != null) {
					vo.setLibellePays(pays.getLibelle());
				}
			
			} catch(Exception e) {
				Logger.getLogger("loggerGSR").fatal(
						"Erreur lors de la conversion !", e);
			}
			
			return vo;
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return null;
	}

	public IEntite voToItem(VictimeVO vo, Victime item){
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
			item= (Victime) converterTools.getInstance().convertToObjectBO(vo);
			return (IEntite) item;
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return null;
	}

}