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

import org.hibernate.Session;

import eai.devass.edition.Utils.PrintPdf;
import eai.devass.edition.modele.Template;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public abstract class EditerJrxmlDocxUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params) throws FonctionnelleException, Exception {

		Connection connection = null;
		try {
			EditionVO editionVO = (EditionVO) entite;
			Map<String, Object> param = getRapportParametresValues(editionVO);
			param.put("logoPath", editionVO.getLogoPath());
			connection = getConnection();
			param.put(JRParameter.REPORT_LOCALE, Locale.GERMAN);

			// String codeTemplate = getCodeTemplate(editionVO);
			// ByteArrayInputStream inStream = getJrxmlFile(codeTemplate);
			//
			// JasperReport jasperReport = JasperCompileManager.compileReport(inStream);
			// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param,
			// connection);
			//
			// Debut evo :Merge two jrxml
			List<String> codesTemplate = getCodesTemplate(editionVO);
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			for (String code : codesTemplate) {
				ByteArrayInputStream inStream = getJrxmlFile(code);
				JasperReport jasperReport = JasperCompileManager.compileReport(inStream);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, connection);
				jasperPrints.add(jasperPrint);
			}
			// FIn evo
			if ("1".equals(editionVO.getIsWord())) {
				JRDocxExporter exporter = new JRDocxExporter();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
				exporter.exportReport();
				byte[] bytes = out.toByteArray();
				if (imprimer(editionVO)) {
					PrintPdf.print(bytes);
				} else {
					this.addResultItem(bytes);
				}
			} else if ("1".equals(editionVO.getIsExcel())) {
				JRXlsxExporter Xlsxexporter = new JRXlsxExporter();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Xlsxexporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				Xlsxexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT_LIST, jasperPrints);
				Xlsxexporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
				Xlsxexporter.exportReport();
				byte[] bytes = out.toByteArray();
				if (imprimer(editionVO)) {
					PrintPdf.print(bytes);
				} else {
					this.addResultItem(bytes);
				}
			} else {
				JRPdfExporter exporter = new JRPdfExporter();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
				exporter.exportReport();
				byte[] bytes = out.toByteArray();
				if (imprimer(editionVO)) {
					PrintPdf.print(bytes);
				} else {
					this.addResultItem(bytes);
				}
			}

		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		} catch (Exception e) {
			logger.error(EXP_STAND_MESS, e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnée", e2);
					throw new FonctionnelleException(e2.getMessage(), e2);
				} finally {
					connection = null;
				}
			}
		}
	}

	abstract protected String getCodeTemplate(EditionVO editionVO);

	abstract protected List<String> getCodesTemplate(EditionVO editionVO) throws FonctionnelleException;

	abstract protected Map<String, Object> getRapportParametresValues(IValueObject vo) throws FonctionnelleException;

	abstract protected boolean imprimer(IValueObject vo);

	private Connection getConnection() throws PersistenceException {
		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		Session session = (Session) dao.getSession();
		return session.connection();
	}

	private ByteArrayInputStream getJrxmlFile(String codeTemplate) throws Exception {
		Template template = rapportManager.getTemplateByCode(codeTemplate);
		Blob fichierTemplate = template.getFichierTemplate();
		byte[] bytes = fichierTemplate.getBytes(1, (int) fichierTemplate.length());
		logger.debug(new String(bytes, "UTF-8"));
		ByteArrayInputStream is = new ByteArrayInputStream(new String(bytes, "UTF-8").getBytes("UTF-8"));
		return is;
	}
}
