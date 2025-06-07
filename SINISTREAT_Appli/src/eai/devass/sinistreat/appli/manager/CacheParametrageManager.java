package eai.devass.sinistreat.appli.manager;

import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;

import eai.devass.gsr.appli.utile.cache.SimpleCacheTools;
import eai.devass.sinistreat.appli.modele.parametrage.Pays;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;

@SuppressWarnings("all")
public class CacheParametrageManager  {

	private ParametrageManager parametrageManager = new ParametrageManager();
	private SimpleCacheTools simpleCacheTools = SimpleCacheTools.getInstance();
	
	public Ville getVille(String codeVille) throws Exception {
		
		if(codeVille == null) {
			return null;
		}
		List<Ville> listVille = (List<Ville>) simpleCacheTools
				.getElement("listVille");
		
		if(listVille == null) {
			listVille = parametrageManager.getListVille(new Ville());
			simpleCacheTools.dirtyPut("listVille", listVille);
		}
		
		Ville ville = null;
		if (listVille != null) {
			for (Ville curVille : listVille) {
				if (codeVille.equals(curVille.getCode())) {

					// Trim libelle ville
					String libelle = curVille.getLibelle();
					if (libelle != null) {
						ville = (Ville) BeanUtilsBean.getInstance().cloneBean(curVille);
						ville.setLibelle(libelle.trim());
					}

					return ville;
				}
			}
		}
		
		return null;
	}
	
	public Pays getPays(String codePays) throws Exception {
		
		if(codePays == null) {
			return null;
		}
		List<Pays> listPays = (List<Pays>) simpleCacheTools
				.getElement("listPays");
		
		if(listPays == null) {
			simpleCacheTools.dirtyPut("listPays",
					parametrageManager.getListPays(new Pays()));
		}
		
		Pays pays = null;
		if(listPays!=null){
		for(Pays curpPays : listPays) {
			if(codePays.equals(curpPays.getCode())) {
				return curpPays;
			}
		}
		}
		
		return null;
	}
	
	
	public Ville getVilleByCode(String codeVille) throws Exception {
		
		if(codeVille == null) {
			return null;
		}
		List<Ville> listVille = (List<Ville>) simpleCacheTools
				.getElement("listVille");
		
		if(listVille == null) {
			listVille = parametrageManager.getListVille(new Ville());
			simpleCacheTools.dirtyPut("listVille", listVille);
		}
		
		Ville ville = null;
		if (listVille != null) {
			for (Ville curVille : listVille) {
				if (codeVille.equals(curVille.getCodeVille())) {

					// Trim libelle ville
					String libelle = curVille.getLibelle();
					if (libelle != null) {
						ville = (Ville) BeanUtilsBean.getInstance().cloneBean(curVille);
						ville.setLibelle(libelle.trim());
					}

					return ville;
				}
			}
		}
		
		return null;
	}
	
	
}
