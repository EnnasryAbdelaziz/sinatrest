package eai.devass.sinistreat.appli.usecase.edition;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import eai.devass.edition.Utils.PrintPdf;
import eai.devass.edition.modele.Template;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public abstract class EditerJavaBeanJrxmlUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)	throws FonctionnelleException, Exception {
		try {
			EditionVO editionVO = (EditionVO) entite;
			
			//Recuperation du fichier jrxml
			String codeTemplate = getCodeTemplate(); //Méthode abstraite à implémenter par chaque use case
			ByteArrayInputStream inStream = getJrxmlFile(codeTemplate);
			
			//Compilation fichier jrxml
			JasperReport jasperReport = JasperCompileManager.compileReport(inStream);
			
			//Execution du jasper
			//correction sonar Dead store to local variable.
			//Map<String, Object> param = getRapportParametresValues(editionVO); //Méthode abstraite à implémenter par chaque use case
			JasperPrint jasperPrint  = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(pupulateBeans(editionVO)));
			
			byte[] rapportBytes = JasperExportManager.exportReportToPdf(jasperPrint);
			if(imprimer(editionVO)){
				PrintPdf.print(rapportBytes);
			}else{
				this.addResultItem(rapportBytes);
			}
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
	}

	abstract protected String getCodeTemplate();
	
	abstract protected Map<String, Object> getRapportParametresValues(IValueObject vo);
	
	abstract protected boolean imprimer(IValueObject vo);
	
	abstract protected List pupulateBeans(IValueObject vo);
	
	private ByteArrayInputStream getJrxmlFile(String codeTemplate) throws Exception {
		Template template = rapportManager.getTemplateByCode(codeTemplate);
		Blob fichierTemplate = template.getFichierTemplate();
		byte[] bytes = fichierTemplate.getBytes(1, (int)fichierTemplate.length());
		ByteArrayInputStream is = new ByteArrayInputStream(new String(bytes, "UTF-8").getBytes("UTF-8"));
		return is;
        
	}
}
