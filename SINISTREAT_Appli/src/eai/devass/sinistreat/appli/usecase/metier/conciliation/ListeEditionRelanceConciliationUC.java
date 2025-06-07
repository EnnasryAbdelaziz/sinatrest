package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ListeEditionRelanceConciliationUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		
		RelanceConciliation relanceConciliation = (RelanceConciliation) this.getItem(RelanceConciliation.class);
		HashMap map = (HashMap) this.getItem(HashMap.class);
		Boolean editer = Boolean.TRUE;
		try {


			List<RelanceConciliation> listRelanceConciliation = new ArrayList<RelanceConciliation>();
			
            Iterator itr = ((List) conciliationManager.getListRelanceConciliation(relanceConciliation, map)).iterator();
			while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   RelanceConciliation rel = (RelanceConciliation) obj[0];
			   listRelanceConciliation.add(rel);
			}

			addResultItem(listRelanceConciliation);

			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return false;
	}
		
		
		
	}