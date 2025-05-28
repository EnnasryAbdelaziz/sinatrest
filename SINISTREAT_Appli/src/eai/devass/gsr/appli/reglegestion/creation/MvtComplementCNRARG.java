package eai.devass.gsr.appli.reglegestion.creation;

import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.manager.metier.mouvements.MouvementManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtComplementCNRA;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

public class MvtComplementCNRARG extends MouvementRG {
	public void regleGestion000verifierMvtConsignation(EntiteBO entiteBO,
			Map params) throws ExceptionMetier, EntiteException, FonctionnelleException {

		MvtComplementCNRA mvtComplementCNRA = (MvtComplementCNRA) entiteBO;
		Rentier rentier = mvtComplementCNRA.getRefRentier();
		MouvementManager mouvementManager = (MouvementManager) ServicesFactory.getService(MouvementManager.class);
		List<MvtConsignCNRA> listMvtsConsignation = mouvementManager.getListMvtsConsignation(String.valueOf(rentier.getId()));
		if(listMvtsConsignation == null || listMvtsConsignation.size() == 0){
			throw new ExceptionMetier("Un mouvement de consignation CNRA est obligatoire avant d'appliquer ce mouvement.");
		}
	}
	
	public void regleGestion001genererPreQuittance(EntiteBO entiteBO,
			Map params) throws ExceptionMetier, EntiteException {
	}
}
