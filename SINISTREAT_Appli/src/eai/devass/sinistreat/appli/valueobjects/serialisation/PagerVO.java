package eai.devass.sinistreat.appli.valueobjects.serialisation;
 
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/* @author kchakib : 3 nov. 10 */
public class PagerVO implements IValueObject {
	
	private String numPage;
	
	/** Nombre totale d'enregistrements*/
	private String nbrLignes;
	
	/** Nombre de lignes demandé dans une page*/
	private String pageSize;

	private String nbrPages;
	
	public String getNbrLignes() {
		return nbrLignes;
	}

	public void setNbrLignes(String nbrLignes) {
		this.nbrLignes = nbrLignes;
	}

	public String getNumPage() {
		return numPage;
	}

	public void setNumPage(String numPage) {
		this.numPage = numPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getNbrPages() {
		return nbrPages;
	}

	public void setNbrPages(String nbrPages) {
		this.nbrPages = nbrPages;
	}
	
	
	

}


