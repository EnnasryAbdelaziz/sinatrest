package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.metier.instruction.PieceAt;
import eai.devass.sinistreat.appli.modele.parametrage.CanalConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.DestinataireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;

public class RelancerConciliationUC extends BaseUC {

	@SuppressWarnings("unused")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		RelanceConciliation reconciliation = (RelanceConciliation) this
				.getItem(RelanceConciliation.class);
		RelanceConciliationVO relanceVo = (RelanceConciliationVO) entite;

		// récupérer liste Destinataire relance conciliation
		List<DestinataireRelance> listDestinataireRelance = conciliationManager.getListDestinataireRelance();
		
		// récupérer liste Canal conciliation
		List<CanalConciliation> listCanalConciliation = conciliationManager.getListCanalConciliation();
		
		// récupérer liste ville
		List<Ville> listVille = conciliationManager.getListVille();
		
		// récupérer liste pieces
		List<PieceAt> listPiece = conciliationManager.getListPiece();
		List<PieceAt> listPieceCocher = conciliationManager.getSelectedPiecesByRelance(String.valueOf(reconciliation.getRefConciliation().getId()));
		
		addResultItem(listDestinataireRelance);
		addResultItem(listCanalConciliation);
		addResultItem(listVille);
		addResultItem(listPiece);
		addResultItem(listPieceCocher);

	}

	public boolean isTransactionnal() {
		return true;
	}
}

