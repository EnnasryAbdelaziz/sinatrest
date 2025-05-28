package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitListeConciliationUC  extends BaseUC{

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Sinistre s = (Sinistre) this.getItem(Sinistre.class);
		if (s == null) {
			throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
		}
        List<Conciliation> listConciliation = conciliationManager
                .getConciliationByIdSinistre(s);
        if (listConciliation != null) {
            for (int i = 0; i < listConciliation.size(); i++) {
				Conciliation conci = listConciliation.get(0);
				if (conci.getListOffre().size()!=0){
					conci.setOffre(conci.getListOffre().get(0));
				}else{
					conci.setOffre(conci.getOffre());
				}
			}			
		}
		addResultItem(listConciliation);
		
	}

}
