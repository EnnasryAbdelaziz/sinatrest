package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.AyantDroitOffreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO;

@SuppressWarnings("all")
public class CalculerOffreUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		OffreVO offre = (OffreVO) this.getItem(OffreVO.class);
		if (offre.getIpp() != null) {
			Double ipp = Double.valueOf(offre.getIpp());
			// IPP < 10 : Calculer Capital Rachat
			if (ipp.compareTo(10.00D) < 0) {
				offre = conciliationManager.calculCapitalRachat(offre);
			}
			// IPP > 10 : Calcul Rentre Trimestriel, Montant Reserve et Montant
			// Arrerage Avant Constitution
			else {
				// Calcul montant rente
				offre = conciliationManager.calculRenteAnnuel(offre);
				// Calcul montant reserve
				offre = conciliationManager.calculMontantReserve(offre);
				// Calcul montant arrerage avant const
				offre = conciliationManager
						.calculMontantArrerageAvConsti(offre);
			}
		} else {
			
			/* RG 4.1.19. calcul taux de rente , de rentes trimestrielles,
			 * montant des arrérages avant 
			 * constitution et le montant des réserves*/
			
			List<AyantDroitOffreVO> listAD = offre.getListAyantDroit();
			// Calcul montant rente
			//listAD = conciliationManager.calculRenteTrimestrielListAyD(offre);
			// Calcul montant reserve
			//listAD = conciliationManager.calculMontantReserveListAyD(listAD);
			// Calcul montant arrerage avant const
			listAD = conciliationManager.calculMontantArrerageAvConstiListAyD(offre);
			offre.setListAyantDroit(listAD); 

		}
		addResultItem(offre);
	}

	public boolean isTransactionnal() {
		return true;
	}
}
