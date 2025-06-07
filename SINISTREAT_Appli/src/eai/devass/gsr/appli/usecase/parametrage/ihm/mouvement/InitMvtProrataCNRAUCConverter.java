
package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtConsignCNRAVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVO;




@SuppressWarnings("all")
public class InitMvtProrataCNRAUCConverter extends InitMouvementUCConverter {
	
	
public Object doConvertItemsToValueObject(List listeItems) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		if (CommonUtils.getInstance().isEmpty(listeItems)) {
			return null;
		}
		
		Map map = (Map) listeItems.get(0);
		List<Rentier> listRentier = (List<Rentier>) map.get("listeRentier");
//		if (CommonUtils.getInstance().isEmpty(listRentier)) {
//			return map;
//		}
		
		Map mapOut = new HashMap();
		//mapOut.put("listeRentier", listRentier);
		mapOut.put("dateDerniereEcheance", map.get("dateDerniereEcheance"));
		mapOut.put("dateDerniereQuittanceEmise", map.get("dateDerniereQuittanceEmise"));
		
		mapOut.put("sommeCapitalCalcule", map.get("sommeCapitalCalcule"));
		mapOut.put("sommeMntCNRA", map.get("sommeMntCNRA"));
		
		List<RentierVO> listRentierVO = new ArrayList<RentierVO>();
		RentierVO rentierVO = null;
		for (Rentier curRentier : listRentier) {

			EtatRentierVO etatRentierVO = new EtatRentierVO();
			rentierVO = new RentierVO();
			rentierVO.setId(String.valueOf(curRentier.getId()));
			rentierVO.setNom(curRentier.getNom());
			rentierVO.setPrenom(curRentier.getPrenom());
			rentierVO
					.setLienParente(String.valueOf(curRentier.getLienParente()));
			rentierVO.setMontantRenteTrimestrielle(String.valueOf(curRentier
					.getMontantRenteTrimestrielle()));
			if (curRentier.getDateNaissance() != null) {
				rentierVO.setDateNaissance(dateFormat.format(curRentier
						.getDateNaissance().getTime()));
			}
			rentierVO.setAdresse(curRentier.getAdresse());
			rentierVO.setNumeroCIN(curRentier.getNumeroCIN());
			rentierVO.setMntCapitalCnra(String.valueOf(curRentier
					.getMntCapitalCnra()));
			rentierVO.setMntCapitalCnraCalcule(String.valueOf(curRentier
					.getMntCapitalCnraCalcule()));
			rentierVO.setProrataCNRA(String.valueOf(curRentier
					.getProrataCNRA()));
			etatRentierVO.setId(curRentier.getRefEtatRentier().getId());
			etatRentierVO.setLibelle(curRentier.getRefEtatRentier()
					.getLibelle());
			rentierVO.setRefEtatRentier(etatRentierVO);

			if (rentierVO.getRefEtatRentier().getId() == 10) {
				listRentierVO.add(rentierVO);
			}
		}
		
		mapOut.put("listRentierVO", listRentierVO);	
		//map.remove("listRentier");
		
		// Convert mouvement consigniation CNRA
		MvtConsignCNRA consignCNRA = (MvtConsignCNRA) map.get("mouvementVO");
		MvtConsignCNRAVO consignCNRAVO = new MvtConsignCNRAVO();
		consignCNRAVO.setRefDossierCNRA(consignCNRA.getRefDossierCNRA());
		if(consignCNRA.getDatRcptCNRA() != null) {
			consignCNRAVO.setDatRcptCNRA(dateFormat.format(consignCNRA.getDatRcptCNRA().getTime()));
		}
		if(consignCNRA.getDatLimtePaiement() != null) {
			consignCNRAVO.setDatLimtePaiement(dateFormat.format(consignCNRA.getDatLimtePaiement().getTime()));
		}
		//consignCNRAVO.setDatRcptCNRA(dateFormat.format(consignCNRA.getDatRcptCNRA().getTime()));
		//consignCNRAVO.setDatLimtePaiement(dateFormat.format(consignCNRA.getDatLimtePaiement().getTime()));
		consignCNRAVO.setMntCNRA(String.valueOf(consignCNRA.getMntCNRA()));
		consignCNRAVO.setCapitalCalcule(String.valueOf(consignCNRA.getCapitalCalcule()));
		mapOut.put("mouvementVO",consignCNRAVO);
		
		return mapOut;
		
	}
	
		
		
}
