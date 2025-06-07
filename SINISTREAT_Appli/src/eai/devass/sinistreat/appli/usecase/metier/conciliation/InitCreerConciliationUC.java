/**
 * 
 */
package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eai.devass.missionnement.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.EtatConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.MotifConvocation;
import eai.devass.sinistreat.appli.modele.parametrage.MotifOffre;
import eai.devass.sinistreat.appli.modele.parametrage.OrigineConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.ResultatOffre;
import eai.devass.sinistreat.appli.modele.parametrage.TypeProcedure;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/**
 * @author karabima
 * 
 */
public class InitCreerConciliationUC extends BaseUC {

	@SuppressWarnings("unused")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		Conciliation conciliation = (Conciliation) this
				.getItem(Conciliation.class);
		Sinistre sinistre = conciliation.getRefSinistre();
		if (sinistre != null) {
			Victime victime = sinistre.getRefVictime();
			Offre of = new Offre();
			List listAyD = new ArrayList();
			List listAydOffre = new ArrayList();
			
			if (sinistre == null) {
				throw new FonctionnelleException(
						"Les informations sinistre sont obligatoire ");
			}

			if (victime == null) {
				throw new FonctionnelleException(
						"Les informations victime sont obligatoire ");
			}
			// récupérer liste Etat
			List<EtatConciliation> listEtat = conciliationManager
					.recupererListEtatConciliation();

			// récupérer liste Motif Offre
			List<MotifOffre> listMotifOffre = conciliationManager
					.getListMotifOffre();
			
			//Evol lot1 01/12/2021
			
			List<TypeProcedure> listTypeProcedure = conciliationManager
					.getListTypeProcedure();

			// récupérer liste pieces selon le cas de la victime (victime ou
			// deces)
			conciliation = conciliationManager.recupererConciliation(sinistre);

			// récupérer liste Etat Resultat Offre
			List<ResultatOffre> listResultat = conciliationManager.recupererListEtatResultat();
			
				if (!victime.getDeces()) {
					// RG4.1.12 récupérer IPP certificat de contre visite, si
					// non
					// certificat de guerison dans le cas Victime
					of = conciliationManager.getIppValide(sinistre);
				} else {
					// récuperer liste ayant droit dans le cas DECES
					// recuperer les ayants droits de l'offre, si aucune offre n'existe récuprer les ayants droit du sinistre
					listAydOffre = conciliationManager.getListAyantDroitByIdSinistre(sinistre);
					listAyD = sinistreManager.getListAyantDroitByIdSinistre(sinistre.getId());				
				}
			// récupérer liste origine conciliation
			List<OrigineConciliation> listOrigineConciliation = conciliationManager.getListOrignineConciliation();
			
			// récupérer liste des villes
			List<Ville> listVille = conciliationManager.getListVille();
			
			// récupérer liste motif convocation
			List<MotifConvocation> listMotifConvocation = conciliationManager.getListMotifConvocation();
			
			addResultItem(conciliation);
			addResultItem(listAydOffre);
			addResultItem(of);
			addResultItem(listAyD);
			addResultItem(listMotifOffre);
			addResultItem(listEtat);
			addResultItem(listResultat);
			addResultItem(listOrigineConciliation);
			addResultItem(listVille);
			addResultItem(listMotifConvocation);
			addResultItem(listTypeProcedure);
		}
	}

	public boolean isTransactionnal() {
		return true;
	}
}
