package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.hibernate.Session;
import org.hibernate.Transaction;

import eai.devass.sinistreat.appli.businessrule.ReglementRG;
import eai.devass.sinistreat.appli.manager.reglement.ReglementManager;
import eai.devass.sinistreat.appli.modele.metier.reglement.EtatReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class ValidateQuittanceITTUC extends BaseUC {

	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		List<Reglement> listRgl = this.getItems(Reglement.class);
		if(listRgl == null || listRgl.isEmpty()) {
			addResultItem(new ArrayList<Reglement>());
		}
		Session session = getNewSession();
		List<String> listErrors = new ArrayList<String>();
		if(listRgl!=null)
		{
		try {
			for(Reglement reglement : listRgl) {
				reglementManager.validerQuittanceITT(reglement);
				reglement.setRefEtatReglement(new EtatRgl(ETAT_PRE_QUITTANCE_EN_INSTANCE));
				reglementManager.addHistoriqueEtat(reglement,IConstantes.MOTIF_CREATION_PREQUITTANCE);
			}
		
		} catch(Exception e) {
			logger.fatal("Erreur GLOBAL lors du validation", e);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}
//		// Add error messages
		if (!listErrors.isEmpty()) {
			StringBuilder errors = new StringBuilder();
			for (String curString : listErrors) {
				errors.append(" * ").append(curString).append("|");
			}
			throw new Exception(errors.toString());
		}

	}

	public boolean isTransactionnal() {
		return true;
	}
	
	private Session getNewSession() throws Exception {

		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		return (Session) dao.newSession();

	}
}
