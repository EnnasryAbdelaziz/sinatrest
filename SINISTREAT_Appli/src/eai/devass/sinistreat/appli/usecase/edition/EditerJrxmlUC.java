package eai.devass.sinistreat.appli.usecase.edition;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.hibernate.Session;

import eai.devass.edition.Utils.PrintPdf;
import eai.devass.edition.modele.Template;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;

public abstract class EditerJrxmlUC extends BaseUC {

	protected final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	@Override
    protected void executerUC(IValueObject entite, HashMap params)
            throws FonctionnelleException, Exception {
		
		Connection connection = null;
		try {
			EditionVO editionVO = (EditionVO) entite;
			
			//Recuperation du fichier jrxml
            String codeTemplate = getCodeTemplate(); // Méthode abstraite à
                                                     // implémenter par chaque
                                                     // use case
			ByteArrayInputStream inStream = getJrxmlFile(codeTemplate);
			
			//Compilation fichier jrxml
            JasperReport jasperReport = JasperCompileManager
                    .compileReport(inStream);
			
			//Execution du jasper
            Map<String, Object> param = getRapportParametresValues(editionVO); // Méthode
                                                                               // abstraite
                                                                               // à
                                                                               // implémenter
                                                                               // par
                                                                               // chaque
                                                                               // use
                                                                               // case
			connection = getConnection();
			param.put(JRParameter.REPORT_LOCALE, Locale.GERMAN); 
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, param, connection);
            byte[] rapportBytes = JasperExportManager
                    .exportReportToPdf(jasperPrint);
			if(imprimer(editionVO)){
				PrintPdf.print(rapportBytes);
			}else{
				this.addResultItem(rapportBytes);
			}
			
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS,e);
		} catch (Exception e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS,e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnée", e2);
					throw new FonctionnelleException(e2.getMessage(),e2);
				} finally {
					connection = null;
				}
			}
		}
	}

	abstract protected String getCodeTemplate();
	
    abstract protected Map<String, Object> getRapportParametresValues(
            IValueObject vo);
	
	abstract protected boolean imprimer(IValueObject vo);
	
	private Connection getConnection() throws PersistenceException{
        IPersistenceService dao = (IPersistenceService) ServicesFactory
                .getService(IPersistenceService.class);
		Session session = (Session) dao.getSession();
		return session.connection();
	}
	
    private ByteArrayInputStream getJrxmlFile(String codeTemplate)
            throws Exception {
		Template template = rapportManager.getTemplateByCode(codeTemplate);
		Blob fichierTemplate = template.getFichierTemplate();
        byte[] bytes = fichierTemplate.getBytes(1,
                (int) fichierTemplate.length());
        logger.debug(new String(bytes, "UTF-8"));
        ByteArrayInputStream is = new ByteArrayInputStream(new String(bytes,
                "UTF-8").getBytes("UTF-8"));
		return is;
        
	}
}
