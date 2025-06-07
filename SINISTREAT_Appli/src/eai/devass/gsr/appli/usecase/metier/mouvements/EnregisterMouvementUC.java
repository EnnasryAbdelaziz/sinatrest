package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.commun.appli.uc.MouvementBaseUC;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.sinistreat.appli.utils.Message.IMessage;

/** @author kchakib */

@SuppressWarnings("all")
public class EnregisterMouvementUC extends MouvementBaseUC {

	@Override
	protected void execute(Map params) throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);

		// Recuperer les objets
		Set<Entry> setObjects = params.entrySet();
		Boolean isPersist = true;
		for (Entry curEntry : setObjects) {
			if (!(curEntry.getValue() instanceof EntiteBO)) {
				continue;
			}

			isPersist = ((EntiteBO) curEntry.getValue()).getPersistObject();
			if (isPersist == null || isPersist) {
				getSession().saveOrUpdate(curEntry.getValue());

				if (curEntry.getValue() instanceof IHistorisable) {
					transverseManager.historiser((IHistorisable) 
							curEntry.getValue(), 1);
				}
			}

		}

		// Set error,warning and message
		Map<String, IMessageItem> message = (Map<String, IMessageItem>) params
				.get("outMapMessage");
		if (!message.isEmpty()) {
			Set<Entry<String, IMessageItem>> setEntry = message.entrySet();
			for (Entry<String, IMessageItem> curEntry : setEntry) {
				if (curEntry.getKey().equals(IMessage.INFO)) {
					// Cas message d'info: ajouter directement dans ResultVo
					this.addResultItem(curEntry.getValue());
				} else {
					// Cas message erreur: afficher dans l'entête
					addMessageItem(curEntry.getValue());
				}
			}
		}
	}
	
	@Override
	public boolean isTransactionnal() {
		return true;
	}

}
