package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class ModifierReglementUC extends BaseUC {

	public ModifierReglementUC() {
	}

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		try {
			List listRgl = getItems(Reglement.class);
			List listResult = new ArrayList();
			if (listRgl != null && !listRgl.isEmpty()) {
				Reglement rgl =(Reglement)listRgl.get(0);
				for (Iterator iterator = listRgl.iterator(); iterator.hasNext();) {
					Reglement reglement = (Reglement) iterator.next();
					if(reglement.getRefSinistre()!=null && reglement.getRefSinistre().getReserveOrdinaire()==null){
						reglement.getRefSinistre().setReserveOrdinaire(Double.valueOf(0));
						}
						reglementManager.ajouterLettreReglement(reglement, false);
						listResult.add(reglementManager
								.modifierReglement(reglement));
				}
				Sinistre sinResult = sinistreManager
						.creerMovementReglementModification((Reglement) listResult
								.get(0));
                Reglement reg=(Reglement) listResult.get(0);
                
				if (rgl.getAncienEtat() != null
						&& IConstantes.ETAT_PRE_QUITTANCE_VALIDEE.equals(rgl.getAncienEtat())) {
					reglementManager.addHistoriqueEtat(reg,
							IConstantes.MOTIF_CREATION_REGLEMENT);
				}
				
				if(reg.getRefSinistre()!=null){
				sinResult.setReserveGrave(reg.getRefSinistre().getReserveGrave());
				sinResult.setReserveOrdinaire(reg.getRefSinistre().getReserveOrdinaire());
				sinResult.setReserveProthese(reg.getRefSinistre().getReserveProthese());
				}
				
				for (Iterator iterator1 = listResult.iterator(); iterator1
						.hasNext();) {
					Reglement reglement = (Reglement) iterator1.next();
					
					reglement.setRefSinistre(sinResult);
					addResultItem(reglement);
				}

			}
		}

		catch (Exception e) {
			throw new FonctionnelleException(e.getMessage(), new String[0]);
		}
	}

	public boolean isTransactionnal() {
		return true;
	}
}
