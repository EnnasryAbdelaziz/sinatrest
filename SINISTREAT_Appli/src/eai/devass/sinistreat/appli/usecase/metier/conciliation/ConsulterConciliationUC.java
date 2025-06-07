package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.instruction.PieceAt;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ConsulterConciliationUC extends BaseUC{

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Conciliation conciliation = (Conciliation) this
		.getItem(Conciliation.class);
		
		conciliation = conciliationManager.getConciliationById(conciliation);
		List<PieceAt> listPieceCocher = conciliationManager.getSelectedPiecesByRelance(String.valueOf(conciliation.getId()));

		addResultItem(conciliation);
		addResultItem(listPieceCocher);
	}

}
