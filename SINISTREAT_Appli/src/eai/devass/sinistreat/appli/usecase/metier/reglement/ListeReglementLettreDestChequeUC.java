package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class ListeReglementLettreDestChequeUC  extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		HashMap map = (HashMap) this.getItem(HashMap.class);
		Boolean editer = Boolean.TRUE;
		try {


			List<Reglement> listReglement = new ArrayList<Reglement>();
			
            Iterator itr = ((List) reglementManager.getListReglementDirect(reglement, map)).iterator();
			while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   Reglement reg = (Reglement) obj[0];
			   listReglement.add(reg);
			}

			addResultItem(listReglement);

			
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


