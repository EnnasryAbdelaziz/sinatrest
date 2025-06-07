package eai.devass.gsr.appli.usecase.metier.reglement;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.utile.Fonctions;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;

public class TotalQuittanceGsrUC extends FacadeServiceUseCase {
	/**
	Methode qui execute le use case de consultation d' un objet
	@param entite l' objet Ã  consulter
	@param params paramatere additionel qu 'on peut passer au Use Case
	@throws Exception Probleme lors de l' execution du Use case
	*/
		protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
			QuittanceGsr qtc = (QuittanceGsr) this.getItem(QuittanceGsr.class);
			
			/* il y a 2 possiblité pour calculer le total  de quitttances
			 *  - dirctement en utilisant  une requette hql "sum ..."
			 *  - en sélectionnant les quittance puis on les additionne
			 *  
			 *  pour le moment je utilise la 2eme option 
			*/
			
			TransverseManager transverseManager = new TransverseManager();
			List<QuittanceGsr> listQtc =  transverseManager.getSimilarObject(qtc); // (new QuittanceGsrManager()).lookupEntite(qtc);
			double total = 0;
			QuittanceGsr  totalQTC = new QuittanceGsr();
			if(!Fonctions.isEmpty(listQtc))
			{
				for (QuittanceGsr quittanceGsr : listQtc) {
					total += quittanceGsr.getMontant();
				}
			}
			totalQTC.setMontant(total);
			this.addResultItem(totalQTC);
		}

	/**
	Methode pour activer le service de transaction
	@returns soit true pour activer le service de transaction ou false pour le desactiver
	*/
		public boolean isTransactionnal() {
			return false;
		}
		
	/**
	Methode pour activer le service de trace
	@returns soit true pour activer le service de trace ou false pour le desactiver
	*/
		public boolean isTracable() {
			return false;
		}

	}
