/**
 * 
 */
package eai.devass.gsr.appli.manager.metier.mouvements;

import java.util.List;

import org.hibernate.Query;

import eai.devass.gsr.appli.manager.CommunManager;
import eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;

/**
 * @author elfaismo
 *
 */
public class RentierMvtManager extends CommunManager {
	
	
	
	@SuppressWarnings("unchecked")
	public List<RentierMvt> getListRentierMvt(Long idMouvement)
	throws FonctionnelleException {
		try {
			StringBuilder hql = new StringBuilder(
					"select rentietMvt from eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt rentietMvt "
							+ "where rentietMvt.refMouvement.id=:idMouvement");
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idMouvement", idMouvement);
			return query.list();
		} catch (Exception e) {
			logger.error(IMessageException.EXP_RECHERCHE, e);
			throw new FonctionnelleException(IMessageException.EXP_RECHERCHE);
		}
}

}
