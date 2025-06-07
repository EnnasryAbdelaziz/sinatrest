package eai.devass.sinistreat.appli.usecase.edition;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.Session;

import eai.devass.edition.Utils.PrintPdf;
import eai.devass.edition.modele.Template;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public abstract class EditerCombiningJrxmlUC extends BaseUC {

	@Override
    protected void executerUC(IValueObject entite, HashMap params)
            throws FonctionnelleException, Exception {
		
		Connection connection = null;
		try {
			EditionVO editionVO = (EditionVO) entite;
			Map<String, Object> param = getRapportParametresValues(editionVO);
			connection = getConnection();
			param.put(JRParameter.REPORT_LOCALE, Locale.GERMAN);

			// Merge two jrxml
			List<String> codesTemplate = getCodesTemplate(editionVO);
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			for (String code : codesTemplate) {
				ByteArrayInputStream inStream = getJrxmlFile(code);
				JasperReport jasperReport = JasperCompileManager
						.compileReport(inStream);
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, param, connection);
				jasperPrints.add(jasperPrint);
			}

			JRPdfExporter exporter = new JRPdfExporter();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
					jasperPrints);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			byte[] bytes = out.toByteArray();
			if (imprimer(editionVO)) {
				PrintPdf.print(bytes);
			} else {
				this.addResultItem(bytes);
			}
		} catch (PersistenceException e) {
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
	
	abstract protected List<String> getCodesTemplate(EditionVO editionVO) throws FonctionnelleException;
	
    abstract protected Map<String, Object> getRapportParametresValues(
            IValueObject vo) throws FonctionnelleException;
	
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
