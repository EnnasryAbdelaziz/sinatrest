package eai.devass.sinistreat.appli.usecase.parametrage.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;

public class InitRechercherAvocatsConseilsUCConverter extends
		ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	//ConverterTools converterTools = ConverterTools.getInstance();

	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();

		try {
			List listProcedureJudiciaire = (List) listeItems.get(0);
			List listProcedureJudiciaireVO = new ArrayList();
			if(listProcedureJudiciaire != null && listProcedureJudiciaire.size() > 0){
				for(int i = 0; i < listProcedureJudiciaire.size(); i++){
					ProcedureJudiciaireVO vo = new ProcedureJudiciaireVO();
					Object[] a = (Object[])listProcedureJudiciaire.get(i);
					vo.setCodeAvocatConseil(String.valueOf(a[0]));
					vo.setNomAvocatConseil(String.valueOf(a[1]));
					listProcedureJudiciaireVO.add(vo);
				}
			}
			map.put("listProcedureJudiciaireVO", listProcedureJudiciaireVO);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return map;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		return null;
	}
}