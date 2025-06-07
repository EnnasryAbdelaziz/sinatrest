package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Delegation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.User;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.MotifOffre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.DelegationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.UserVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MotifOffreVO;

@SuppressWarnings("all")
public class RechercherDelegationUCConverter extends ValueObjectConverterAbst {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	public Object doConvertItemsToValueObject(List listeItems) {

		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<DelegationVO> listDelegationVO = new ArrayList<DelegationVO>();
		try {
			List<Delegation> listDelegation = (List<Delegation>) listeItems.get(0);
			for (Delegation delegation: listDelegation){
				if (delegation != null && delegation instanceof Delegation) {
					User user = delegation.getRefUser();
					((User)user).setPropertiesToConvert(new String[] {"codeSas","id","nom","prenom"});
					UserVO userVO = (UserVO) ConverterTools.getInstance().convertToObjectVOWithoutList(user);
					((Delegation) delegation).setPropertiesToConvert(new String[] { "id","seuilOperation","codeSas" });
					DelegationVO delegationVO = (DelegationVO) ConverterTools.getInstance().convertToObjectVOWithoutList(delegation);
					delegationVO.setRefUser(userVO);
					
					listDelegationVO.add(delegationVO);
					if (listDelegationVO != null && !listDelegationVO.isEmpty()) {
						map.put("listDelegationVO",listDelegationVO);
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
		DelegationVO delegVO = (DelegationVO) vo;

		try {
			Delegation delegation  = (Delegation) converterTools
					.convertToObjectBO(delegVO);
			listOut.add(delegation);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
