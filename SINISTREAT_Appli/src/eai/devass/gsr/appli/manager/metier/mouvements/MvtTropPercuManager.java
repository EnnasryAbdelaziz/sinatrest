package eai.devass.gsr.appli.manager.metier.mouvements;

import eai.devass.gsr.appli.manager.CommunManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;

public class MvtTropPercuManager extends CommunManager {
		
	
	public Object[] getInfoTropPercu(Rentier rentier) throws Exception {
		StringBuilder hql = new StringBuilder("select min(t.dateOperation),sum(t.mntTropPercu)")
			.append("from TropPercu t where t.refRentier=? and t.mntRecuperer is null");
		return (Object[]) getSession().createQuery(hql.toString())
				.setParameter(0, rentier).uniqueResult();
		
	}
	
	public Object[] getInfoTropPercuRecuperer(Rentier rentier) throws Exception {
		StringBuilder hql = new StringBuilder("select max(t.dateOperation),sum(t.mntRecuperer)")
			.append(" from TropPercu t where t.refRentier=? and t.mntTropPercu is null");
		return (Object[]) getSession().createQuery(hql.toString())
				.setParameter(0, rentier).uniqueResult();
		
	}
	
	public Double getRenteTrimestrielle(Rentier rentier) throws Exception {
		
		StringBuilder hql = new StringBuilder("select r.montantRenteTrimestrielle from Rentier r where r=?");
		return (Double) getSession().createQuery(hql.toString())
				.setParameter(0, rentier).uniqueResult();
	}
	
	
}