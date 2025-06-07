package eai.devass.gsr.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatMandatsImpayesParRente extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_MANDATS_IMPAYES_PAR_RENTE;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		String titre = "Etat des Mandats retournés impayés : ";
		EditionVO editionVO = (EditionVO) vo;
		String numRente = editionVO.getNumRente() != null ? editionVO.getNumRente() : "";
		String lienParente = editionVO.getLienParente() != null ? editionVO.getLienParente() : "";
		if(!numRente.equals("")){
			titre += "rente " + numRente;
			if(!lienParente.equals("")){
				titre += ", classe " + lienParente;
			}
		}else if(!lienParente.equals("")){
			titre += "classe " + lienParente;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("numRente", numRente);
		params.put("classe", lienParente);
		params.put("titre", titre);
		return params;
	}

}
