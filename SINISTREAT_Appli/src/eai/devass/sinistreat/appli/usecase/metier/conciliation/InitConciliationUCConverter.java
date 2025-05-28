package eai.devass.sinistreat.appli.usecase.metier.conciliation;

/* @author joundi : 1 Nov. 10 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.conciliation.User;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.UserVO;

@SuppressWarnings("unchecked")
public class InitConciliationUCConverter extends ValueObjectConverterAbst {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	public Object doConvertItemsToValueObject(List listeItems) {

		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<UserVO> listUserVO = new ArrayList<UserVO>();
		try {
			List<User> listUser = (List<User>) listeItems.get(0);
			for (User user: listUser){
				if (user != null && user instanceof User) {
					((User)user).setPropertiesToConvert(new String[] {"codeSas","id","nom","prenom"});
					UserVO userVO = (UserVO) ConverterTools.getInstance().convertToObjectVOWithoutList(user);
					listUserVO.add(userVO);
					if (listUserVO != null && !listUserVO.isEmpty()) {
						map.put("listUserVO",listUserVO);
					}
				}

			}
			return map;

		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return null;
	}
	
	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		UserVO userVO = (UserVO) vo;

		try {
			User user = (User) converterTools
					.convertToObjectBO(userVO);
			listOut.add(user);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
