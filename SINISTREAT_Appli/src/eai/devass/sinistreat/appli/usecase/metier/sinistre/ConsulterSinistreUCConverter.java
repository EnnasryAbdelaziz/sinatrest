package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;

public class ConsulterSinistreUCConverter extends  ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		SinistreVO sinvo= null;
		Sinistre sin = (Sinistre) listeItems.get(0);
		try {
			if(sin == null) {
				return null;
			}
			
			sinvo = (SinistreVO)ConverterTools.getInstance().convertToObjectVOWithoutList(sin);
			
			//CK : Evolution pour le service RecuperationSiniste (besoin de la GED AT)
			Boolean completeConversion = (Boolean) listeItems.get(1);
			if(CommonGsrUtils.getInstance().isTrue(completeConversion)) {
				// Convertier la liste des ayants droits
				Victime victime = sin.getRefVictime();
				VictimeVO victimeVO = sinvo.getRefVictime();
				if(victime != null && victimeVO != null) {
					List<AyantDroit> listAyantDroit = victime.getListAyantDroit();
					if(!CommonGsrUtils.getInstance().isEmpty(listAyantDroit)) {
						List<AyantDroitVO> listADVO = new ArrayList<AyantDroitVO>();
						AyantDroitVO ayantDroitVO = null;
						for(AyantDroit curAyantDroit : listAyantDroit) {
							ayantDroitVO = new AyantDroitVO();
							ayantDroitVO.setNom(curAyantDroit.getNom());
							ayantDroitVO.setPrenom(curAyantDroit.getPrenom());
							ayantDroitVO.setNumeroCIN(curAyantDroit.getNumeroCIN());
							listADVO.add(ayantDroitVO);
						}
						victimeVO.setListAyantDroit(listADVO);
					}
				}
			}
			
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return sinvo;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		SinistreVO sinvo = (SinistreVO) vo;
		try{
			Sinistre sin=(Sinistre)converterTools.convertToObjectBO(sinvo);
			listOut.add(sin);		
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
						
		return listOut;
	}
	

}
