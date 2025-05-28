package eai.devass.gsr.appli.usecase.metier.reglement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.Query;

import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;



public class QuittanceGsrRechercherUC extends SimpleBaseUC {

	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	@Override
	protected void execute(IValueObject valueObject, HashMap patams) throws Exception {
		
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		QuittanceGsrVO quittanceGsrVO = (QuittanceGsrVO) valueObject; 
		int numPage = quittanceGsrVO.getNumPage();
		int nbrRes = quittanceGsrVO.getNbResultats();
		Calendar dateEmissionFin = null;
		String dateEmissionFinSt = quittanceGsrVO.getDateEmissionQuittanceAu();
		if(!CommonUtils.isEmpty(dateEmissionFinSt)) {
			dateEmissionFin = new GregorianCalendar();
			dateEmissionFin.setTime(dateFormat.parse(dateEmissionFinSt));
		}
				
		QuittanceGsr quittanceGsr = (QuittanceGsr) getItem(QuittanceGsr.class);
		
		// Date emission		
		Calendar dateEmissionDebut = quittanceGsr.getDatEmission();
		quittanceGsr.setDatEmission(null);
		
		// Date creation prergl
		Calendar dateCreationPrerglDebut = null;
		Calendar dateCreationPrerglFin = null;
		if(quittanceGsr.getRefPrerglt() != null) {
			dateCreationPrerglDebut = quittanceGsr.getRefPrerglt().getDateCreation();
			quittanceGsr.getRefPrerglt().setDateCreation(null);
			if(quittanceGsrVO.getRefPrerglt().getDateCreationFin() != null) {
				dateCreationPrerglFin = new GregorianCalendar();
				dateCreationPrerglFin.setTime(dateFormat.parse(quittanceGsrVO
						.getRefPrerglt().getDateCreationFin()));
			}
		}
		
		// Construire la req HQL manuellement
		Object[] objects = transverseManager.getHqlQuery(quittanceGsr);
		TreeMap<Integer, Object> values = (TreeMap<Integer, Object>) objects[1];
		StringBuilder hql = (StringBuilder) objects[0];
		
		// Ajouter le critere de la date emission
		hql.append(addCritereDate(dateEmissionDebut, dateEmissionFin, "datEmission", values));
		
		// Ajouter le critere de la creation manuelle 
		addCritereCreationManuelle(hql, quittanceGsrVO.getIsCreationManuel());
		
		// Ajouter le critere de la date creation PRERGL
		hql.append(addCritereDate(dateCreationPrerglDebut,
				dateCreationPrerglFin, "refPrerglt.dateCreation", values));
		
		// Ajouter le critere de l'utilisateur !!!!
		if(!CommonUtils.isEmpty(quittanceGsrVO.getUtilisateur())) {
			hql = addCritereUtilisateur(hql, quittanceGsrVO.getUtilisateur(), values);
		}
		
		// Ck : EVO 03122014
		if(!CommonUtils.isEmpty(quittanceGsrVO.getEtatRentierNotIn())) {
			hql = addCritereEtatRentierNoIn(hql,
					quittanceGsrVO.getEtatRentierNotIn());
		}
		//wmos : Evo Quittances supprimées id :25 -> 13/01/2015 
		if(!CommonUtils.isEmpty(quittanceGsrVO.getEtatQtcNotIn())) {
			hql = addCritereEtatQtcNoIn(hql,
					quittanceGsrVO.getEtatQtcNotIn());
		}
		// CAlculer le nbr total des quittances
		if(nbrRes == 0) {
			nbrRes = ((Long) getResult(hql.toString(), values, true)).intValue();			
		}
		
		// Add order by ID
		hql.append((hql.indexOf("qtc") > 0) ?  " order by qtc.exercice desc" : " order by exercice desc");
		
		Query query = (Query) getResult(hql.toString(), values, false);
		query.setFirstResult(30 * (numPage - 1)).setMaxResults(30);		
		List<QuittanceGsr> listQtc = query.list();
		addResultItem(listQtc);
		addResultItem(nbrRes);
		
		boolean convertionCompete = true;
		if("true".equals(quittanceGsrVO.getConversionLight())) {
			convertionCompete = false;
		}
		addResultItem(convertionCompete);
		
		
	}
	
	
	@Override
	public boolean isTransactionnal() {
		return false;
	}
	
	public String addCritereDate(Calendar dateDebut, Calendar dateFin, String property, 
			TreeMap<Integer, Object> values) throws Exception {
		
		if(dateDebut == null && dateFin == null) {
			return "";
		}
		
		StringBuilder whereBetween = new StringBuilder("");		
		String dateFinSt = null;
		try {
			if(dateFin != null) {
				whereBetween.append(" and ").append(property).append(" <= ?");
				dateFinSt = dateFormat.format(dateFin.getTime()) + "  23:59:59";
				Calendar cal = new GregorianCalendar();
				cal.setTime((new SimpleDateFormat("yyyyMMdd HH:mm:ss")).parse(dateFinSt));
				values.put(values.lastKey() + 1, cal);
				
			} 
			if(dateDebut != null) {
				whereBetween.append(" and ").append(property).append(" >= ?");
				values.put(values.lastKey() + 1, dateDebut);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return whereBetween.toString();
	}
	
	private StringBuilder addCritereUtilisateur(StringBuilder hql, String user, TreeMap<Integer, Object> values) {
		
		String hqlSt = hql.toString();
		hqlSt = hqlSt.replaceAll("and ", "and qtc.");
		hqlSt = hqlSt.replaceAll("where", "qtc join qtc.refSituationQuittanceGsr refSit where");
		hql = new StringBuilder(hqlSt);
		hql.append(" and maxelement(qtc.refSituationQuittanceGsr)=refSit and refSit.operateur=?");
		values.put(values.lastKey() + 1, user);
		return hql;
		
	}
	
	private StringBuilder addCritereEtatRentierNoIn(StringBuilder hql, String etatRentierNotIn) {
		
		hql.append(" and refRentier.refEtatRentier.id not in (");
		StringTokenizer etatNotIn = new StringTokenizer(etatRentierNotIn, "|");
		while(etatNotIn.hasMoreTokens()) {
			hql.append(etatNotIn.nextToken()).append(",");
		}
		hql.replace(hql.length() - 1, hql.length(), ")");		
		return hql;
		
	}
	
	//wmos evo 25 prod 13/01/2015
	private StringBuilder addCritereEtatQtcNoIn(StringBuilder hql, String etatQtcNotIn) {
		
		hql.append(" and refEtatQtc.id not in (");
		StringTokenizer etatNotIn = new StringTokenizer(etatQtcNotIn, "|");
		while(etatNotIn.hasMoreTokens()) {
			hql.append(etatNotIn.nextToken()).append(",");
		}
		hql.replace(hql.length() - 1, hql.length(), ")");		
		return hql;
		
	}
	
	private StringBuilder addCritereCreationManuelle(StringBuilder hql, String isCreation) {
		
		// Oui
		if("1".equals(isCreation)) {
			hql.append(" and refComplementQuitatnce is not null");
			
		} else if("0".equals(isCreation)) {
			hql.append(" and refComplementQuitatnce is null");
		}
		
		return hql;
		
	}
	
	private Object getResult(String hql, TreeMap<Integer, Object> values, boolean isNbrResult) throws Exception {

		if(isNbrResult) {
			hql ="select count(*) " + hql;
		}
		
		// Get query
		Query query = getSession().createQuery(hql);
		Set<Entry<Integer, Object>> entrys = values.entrySet();
		for (Entry<Integer, Object> curEntry : entrys) {
			query.setParameter(curEntry.getKey(), curEntry.getValue());
		}
		if(isNbrResult) {
			return (Long) query.uniqueResult();
			
		} else {
			return query;
		}
	}

	

}
