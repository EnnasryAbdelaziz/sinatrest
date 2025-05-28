package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.utile.DateUtile;
import eai.devass.gsr.appli.manager.metier.reglement.PrergltManager;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;

/**
Service de recherche d' une entitÃ©
@author Nom Prenom (email)
*/
public class MouvementAjouterUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject entite, HashMap arg1) throws Exception {
		// récupération de l'objet Mouvement provenant de l'HIM
		Mouvement lToCreate = (Mouvement) this.getItem(Mouvement.class);
		// insérer  l'entité dans la base de données
		Mouvement lCreated = (Mouvement) lToCreate.getFactory().newEntiteManager().createEntite(lToCreate);
		this.addResultItem(lCreated);
	}
	
	protected void creatQtc(Mouvement lToCreate) throws Exception{
		//vérifier les info de qtc et de reglement
		List<QuittanceGsr> listQtc = lToCreate.getRefsQuittance();
		if(listQtc == null || listQtc.isEmpty()) {
			throw new EntiteException("Les informations de réglement sont obligatoir");
		}
		Prerglt preRglt = listQtc.get(0).getRefPrerglt();
		if(preRglt == null) {
			throw new EntiteException("Les informations de réglement sont obligatoir");
		}
		
		// on ajoute Prerglt si l'entité n'existe pas
		PrergltManager prergltManager = new PrergltManager();
		List<Prerglt> listPreRglt = prergltManager.lookupEntite(preRglt);
		if(listPreRglt == null || listPreRglt.isEmpty()) {
			preRglt = (Prerglt) prergltManager.createEntite(preRglt);
		} else {
			preRglt = listPreRglt.get(0);
		}
		
		// calcule exercice
		Calendar toDay = DateUtile.dateCalendarCourante();
		String str = toDay.get(Calendar.YEAR) + "t";
		int month = toDay.get(Calendar.MONTH);
		if(month < 4) {
			str += "1";
		} else if(month < 7) {
			str += "2";
		} else if(month < 10) {
			str += "3";
		} else {
			str += "4";
		}

		Rentier rentier = lToCreate.getRefRentier();
		for(QuittanceGsr qtc : listQtc)
		{
			// insret qtc apres avoir mettre en reference le Mouvement, et le PreReglemt  ainsi les autres info
			//qtc.setRefMouvement(lCreated)
			qtc.setRefPrerglt(preRglt);
			qtc.setBeneficiaire(rentier.getNom() +" "+rentier.getPrenom());
			qtc.setNumeroRente(Long.toString(rentier.getRefDossierRente().getNumeroRente()));
			qtc.setExercice(str);
			qtc.setRefRentier(rentier);
			qtc.setClasse(Long.toString(rentier.getLienParente()));
			(new QuittanceGsrManager()).createEntite(qtc);
		}
	}
	/**
	Methode pour activer le service de transaction
	@returns soit true pour activer le service de transaction ou false pour le desactiver
	*/
	public boolean isTransactionnal() {
		return true;
	}
		
	/**
	Methode pour activer le service de trace
	@returns soit true pour activer le service de trace ou false pour le desactiver
	*/
	public boolean isTracable() {
		return false;
	}
			
}
