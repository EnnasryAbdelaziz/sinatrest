
package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DegreParenteVO;


public class InitMvtRemariageUCConverter extends InitMouvementUCConverter {
	
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (CommonUtils.getInstance().isEmpty(listeItems)) {
			return null;
		}
		
		Map map = (Map) listeItems.get(0);
		List<Rentier> listRentier = (List<Rentier>) map.get("listRentier");
		if (CommonUtils.getInstance().isEmpty(listRentier)) {
			return map;
		}
		
		List<AyantDroitVO> listAD = new ArrayList<AyantDroitVO>();
		AyantDroitVO ayantDroitVO = null;
		for(Rentier curRentier : listRentier) {
			ayantDroitVO = new AyantDroitVO();
			ayantDroitVO.setId(String.valueOf(curRentier.getId()));
			ayantDroitVO.setNom(curRentier.getNom());
			ayantDroitVO.setPrenom(curRentier.getPrenom());
			ayantDroitVO.setRefDegreParente(new DegreParenteVO(String
					.valueOf(curRentier.getLienParente())));
			ayantDroitVO.setRente(String.valueOf(curRentier.getMontantRenteTrimestrielle()));
			ayantDroitVO.setNumeroCIN(curRentier.getNumeroCIN());
			
			if(curRentier.getDateNaissance() != null) {
				ayantDroitVO.setDateNaissance(dateFormat.format(curRentier
						.getDateNaissance().getTime()));
			}
			listAD.add(ayantDroitVO);
		}
		
		map.put("listAyantDroit", listAD);	
		map.remove("listRentier");
		return map;
		
	}
	
		
		
}
