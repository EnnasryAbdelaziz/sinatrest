package eai.devass.sinistreat.appli.usecase.edition;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import eai.devass.edition.modele.Rapport;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.edition.valueobjects.FichierVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public abstract class EditerEtatParTrancheUC extends EditerEtatUC {

	private static int pagination=1000;
	private static int paginationLot=10;
	private static String pathEditionPdf="./webapps/SINISTREAT/edition/";
	private static final Logger logger = Logger.getLogger("loggerSINAT");
	@Override
	protected void executerUC(IValueObject entite, HashMap params)	throws FonctionnelleException, Exception {
		try {
			Integer x=null;
			EditionVO editionVO = (EditionVO) entite;
			List<String> listesFichiers=null;
			
			PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
			if(pagerVO==null){
				super.executerUC(entite, params);
				return;
			}
			Map<String, Object> param=this.getRapportParametresValues(editionVO);
			if(pagerVO.getNbrPages().equals("0")){
			 x=recupererNombreEnregistrement(getCodeRapport(),editionVO);
			}
			else{
				x=Integer.valueOf(pagerVO.getNbrLignes());
			}
			String nonEdition=(String)param.get("nomEdition");
			final String  codeRedacteur=(String)param.get("codeRedacteur");
			
			supprimerRapportUser(codeRedacteur);
			
			if(x<=pagination){
				       JasperPrint jasperPrint=this.genererJasperReport(editionVO,1,x);
				byte[] rapportBytes = JasperExportManager.exportReportToPdf(jasperPrint);
				this.addResultItem(rapportBytes);
			}
			else{
				if(pagerVO.getNbrPages().equals("0")){
					int nombrePage=recupererNombrePage(x);
					pagerVO.setNbrPages(String.valueOf(nombrePage));
					pagerVO.setNumPage("1");
					pagerVO.setPageSize(String.valueOf(paginationLot));
					pagerVO.setNbrLignes(String.valueOf(x));
				}
						
					}
			
			listesFichiers =genererRapport(nonEdition, codeRedacteur, Integer.valueOf(pagerVO.getNbrPages()),x,Integer.valueOf(pagerVO.getNumPage()), editionVO);
			FichierVO fichier = new FichierVO();
			fichier.setCheminFichier(listesFichiers);
			this.addResultItem(fichier);
			this.addResultItem(pagerVO);
			
		} catch (Exception e) {
			logger.error("problème technique", e);
		}
	}

	
public Integer recupererNombrePage(Integer nombreEnregistrement) throws Exception {
		
		Integer nombrePage=nombreEnregistrement/(pagination*paginationLot);
		int modulo = nombreEnregistrement%(pagination*paginationLot);
		if(modulo!=0){
			return (nombrePage+1);
		}
		else
		{
			return nombrePage;
		}
		}



public void supprimerRapportUser(final String codeRedacteur) throws Exception {
	
	File repFile =  new File(pathEditionPdf) ;
	 FilenameFilter fileNameFilter = new FilenameFilter() {
           public boolean accept(File dir, String name) {
           	return name.contains(codeRedacteur);
             
           }
        };
	final File[] files = repFile.listFiles(fileNameFilter);
	if(files!=null) {
       for(File f: files) {
               f.delete();
       }
   }

	}


public List<String> genererRapport(String nonEdition,String codeRedacteur,Integer nombrePage,Integer nombreTotal,int numPAge,EditionVO editionVO) throws Exception {
	int nombreFichier=0;
	List<String> listesFichiers = new ArrayList<String>();
	if(nombrePage==1)
	{
		 nombreFichier=nombreTotal/pagination;
		int modulo = nombreTotal%pagination;
		if(modulo!=0){
			nombreFichier=nombreFichier+1;
		}
	}
	else if(nombrePage>1){
		if(numPAge==nombrePage){
			int modulo = nombreTotal%(pagination*paginationLot);
			
			if(modulo!=0){
				
				if(modulo<pagination){
					nombreFichier=1;
				}
				else{
				 nombreFichier=modulo/pagination;
				int reste=modulo%pagination;
				if(reste!=0)
					nombreFichier= nombreFichier+1;
			}
			}
		}
		else if(numPAge<nombrePage){
			nombreFichier=paginationLot;
		}
		
	}
	
	
	for(int h=1 ; h<=nombreFichier;h++ ){
		JasperPrint jasperPrint=null;
		int pas=pagination;
		int debutRow=(numPAge-1)*(pagination*paginationLot);
		
	 jasperPrint=this.genererJasperReport(editionVO,debutRow+((h-1)*pas)+1,debutRow+(h)*pas);
	
	 
	 String reportDest = pathEditionPdf+nonEdition+"_"+codeRedacteur+"_"+(h)+".pdf";  
	 String lienRapport="edition/"+nonEdition+"_"+codeRedacteur+"_"+(h)+".pdf";
		JasperExportManager.exportReportToPdfFile(jasperPrint,reportDest);
		
		listesFichiers.add(lienRapport);
		 if((debutRow+(h*pas))>nombreTotal){
			 break;
		 }
	}
	
	return listesFichiers;
	}
		
	
	public Integer recupererNombreEnregistrement(String codeRapport,EditionVO editionVO) throws Exception {
		
		
		Map<String, Object> param=getRequeteRapportParametresValues(editionVO);
		Rapport rapport = rapportManager.getRapportByCode(codeRapport);
		// La requete
		String requete = rapport.getRequeteSql();
		if(param!=null){
		Set<Entry<String, Object>> setOfRows=param.entrySet();
		for ( Map.Entry<String, Object> entry : setOfRows) {
			requete=requete.replace("$P{"+entry.getKey()+"}", "'"+entry.getValue()+"'");
		}
		}
		requete=requete.replace("vue.rownumero", "1");
		requete=requete.replace("$P{row_min}", "1");
		requete=requete.replace("$P{row_max}", "1");
	
		String requeteCount="Select count(1) from ("+requete+")";
		System.out.println(System.currentTimeMillis()+"DEBUT"+"*******************************************************");
		Integer i=rapportManager.getcountRapport(requeteCount) ;
		System.out.println(System.currentTimeMillis()+"fin"+"**************************************************************");
		return i;
		}
	protected abstract Map<String, Object> getRequeteRapportParametresValues(IValueObject vo) ;

}
