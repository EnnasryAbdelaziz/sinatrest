package eai.devass.gsr.appli.valueobjects.customerconverter;

import java.util.ArrayList;
import java.util.List;

import eai.devass.commun.appli.converter.ICustomerConverter;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.MotifSituationEtat;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;

public class ConvertListSituationMouvement implements ICustomerConverter {

	public Object getValuePropertyDest(Object entiteOrig) throws Exception {

		if(entiteOrig == null) {
			return null;
		}
		
		MouvementVO mouvementVO = (MouvementVO) entiteOrig;
		String codeSitMouvementSit = mouvementVO.getCodeSituationMotif();
		if(codeSitMouvementSit == null) {
			return null;
		}
		
		MotifSituationEtat motifSituationEtat = new MotifSituationEtat();
		motifSituationEtat.setCode(codeSitMouvementSit);
		
		SituationMouvement situationMouvement  = new SituationMouvement();
		situationMouvement.setRefMotifSituationEtat(motifSituationEtat);		
		
		List<SituationMouvement> list = new ArrayList<SituationMouvement>();
		list.add(situationMouvement);
		return list;
		
		
	}

	public Object getValuePropertyOrig(Object entiteDest) throws Exception {

		if(entiteDest == null) {
			return null;
		}
		
		Mouvement mouvement = (Mouvement) entiteDest;
		List<SituationMouvement> situationMouvements = mouvement.getRefSituationMouvement();
		if(CommonUtils.getInstance().isEmpty(situationMouvements)) {
			return null;
		}
		
		SituationMouvement situationMouvement = situationMouvements.get(0);
		MotifSituationEtat situationEtat = situationMouvement
				.getRefMotifSituationEtat();
		if(situationEtat == null) {
			return null;
		}
		
		return situationEtat.getCode();

	}

}
