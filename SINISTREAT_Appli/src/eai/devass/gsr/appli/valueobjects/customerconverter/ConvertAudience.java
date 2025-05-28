package eai.devass.gsr.appli.valueobjects.customerconverter;

import eai.devass.commun.appli.converter.ICustomerConverter;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class ConvertAudience implements ICustomerConverter {
	// Vo to Bo
	private ConverterTools converterTools = ConverterTools.getInstance();
	public Object getValuePropertyDest(Object entiteOrig) throws Exception {
		if (entiteOrig == null) {
			return null;
		}
		MouvementVO mouvementVO = (MouvementVO) entiteOrig;
		AudienceJugement audience = null;
		if(mouvementVO.getRefAudience()!=null){
			AudienceJugementVO audienceVO = mouvementVO.getRefAudience();
			audience = (AudienceJugement) converterTools.convertToObjectBO(audienceVO);
		}
		return audience;
	}
	// Bo to VO
	public Object getValuePropertyOrig(Object entiteDest) throws Exception {
		if (entiteDest == null) {
			return null;
		}
		Mouvement mouvement = (Mouvement) entiteDest;
		AudienceJugementVO audienceVO = null;
		if(mouvement.getRefAudience()!=null){
			AudienceJugement audience = mouvement.getRefAudience();
			audienceVO = (AudienceJugementVO) converterTools.convertToObjectVO(audience);
		}
		return audienceVO;
	}
}
