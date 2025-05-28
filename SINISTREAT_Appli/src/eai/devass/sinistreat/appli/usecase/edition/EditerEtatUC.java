package eai.devass.sinistreat.appli.usecase.edition;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.Session;

import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.Utils.beans.EnteteBean;
import eai.devass.edition.Utils.beans.FieldBean;
import eai.devass.edition.Utils.beans.GroupByBean;
import eai.devass.edition.Utils.beans.LigneTitreBean;
import eai.devass.edition.Utils.beans.RecapColumnBean;
import eai.devass.edition.Utils.beans.ReportParameterBean;
import eai.devass.edition.Utils.beans.SummaryBean;
import eai.devass.edition.Utils.beans.VariableBean;
import eai.devass.edition.modele.Champ;
import eai.devass.edition.modele.Entete;
import eai.devass.edition.modele.GroupBy;
import eai.devass.edition.modele.LigneTitre;
import eai.devass.edition.modele.Parametre;
import eai.devass.edition.modele.Rapport;
import eai.devass.edition.modele.RecapColonne;
import eai.devass.edition.modele.RecapGenerale;
import eai.devass.edition.modele.Variable;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public abstract class EditerEtatUC extends BaseUC {
	JasperReport jasperReport =null;

	@Override
    protected void executerUC(IValueObject entite, HashMap params)
            throws FonctionnelleException, Exception {
		
		Connection connection = null;
		try {
			EditionVO editionVO = (EditionVO) entite;
			
			//Génération du fichier jrxml par velocity
            String codeRapport = getCodeRapport(); // Méthode abstraite à
                                                   // implémenter par chaque use
                                                   // case
			ByteArrayInputStream inStream = buildFichierJRXML(codeRapport);
			
			//Compilation fichier jrxml généré
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
			param.put("logoPath", editionVO.getLogoPath());
			connection = getConnection();
			
			param.put(JRParameter.REPORT_LOCALE, Locale.GERMAN); 
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, param, connection);
			
            byte[] rapportBytes = JasperExportManager
                    .exportReportToPdf(jasperPrint);
			this.addResultItem(rapportBytes);
			
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnée", e2);
					throw new FonctionnelleException(e2.getMessage());
				} finally {
					connection = null;
				}
			}
		}
	}
	
    protected JasperPrint genererJasperReport(EditionVO editionVO,
            int rownum_min, int rownum_max) throws FonctionnelleException,
            Exception {
		JasperPrint jasperPrint=null;
		Connection connection = null;
		try {
			
			//Génération du fichier jrxml par velocity
			
			if(this.jasperReport==null){
                String codeRapport = getCodeRapport(); // Méthode abstraite à
                                                       // implémenter par chaque
                                                       // use case
			ByteArrayInputStream inStream = buildFichierJRXML(codeRapport);
			
			//Compilation fichier jrxml généré
                JasperReport jasperReport = JasperCompileManager
                        .compileReport(inStream);
			 this.jasperReport=jasperReport;
			}
			
			//Execution du jasper
            Map<String, Object> param = getRapportParametresValues(editionVO); // Méthode
                                                                               // abstraite
                                                                               // à
                                                                               // implémenter
                                                                               // par
                                                                               // chaque
                                                                               // use
                                                                               // case
			param.put("logoPath", editionVO.getLogoPath());
			param.put("row_min", String.valueOf(rownum_min));
			param.put("row_max", String.valueOf(rownum_max));
			connection = getConnection();
            logger.info(System.currentTimeMillis()
                    + "DEBUT"
                    + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            jasperPrint = JasperFillManager.fillReport(jasperReport, param,
                    connection);
            logger.info(System.currentTimeMillis()
                    + "FIN"
                    + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnée", e2);
					throw new FonctionnelleException(e2.getMessage());
				} finally {
					connection = null;
				}
			}
		}
		return jasperPrint;
	}

	abstract protected String getCodeRapport();
	
    abstract protected Map<String, Object> getRapportParametresValues(
            IValueObject vo);
	
	private Connection getConnection() throws PersistenceException{
        IPersistenceService dao = (IPersistenceService) ServicesFactory
                .getService(IPersistenceService.class);
		Session session = (Session) dao.getSession();
		return session.connection();
	}
	
    private ByteArrayInputStream buildFichierJRXML(String codeRapport)
            throws Exception {
		Rapport rapport = rapportManager.getRapportByCode(codeRapport);

		// creer un context velocity
		VelocityContext context = new VelocityContext();

		context.put("margin", IConstantes.MARGIN);

		// Les parametres du rapport
		List<Parametre> lstParametres = rapport.getRefsParametres();
		if (lstParametres != null) {
            ReportParameterBean[] parametres = new ReportParameterBean[lstParametres
                    .size()];
			for (int i = 0; i < lstParametres.size(); i++) {
				Parametre parametre = lstParametres.get(i);
                parametres[i] = new ReportParameterBean(parametre.getName(),
                        parametre.getType(), parametre.getLibelle(),
                        parametre.getPattern(), parametre.getTextAlignment());
			}
			context.put("reportParameters", parametres);
		}
		
		// L'entete
		EnteteBean enteteBean = buildEntete(rapport.getRefEntete());
		context.put("entete", enteteBean);

		// La requete
		context.put("query", rapport.getRequeteSql());

		// Group by
        if (rapport.getRefsGroupBys() != null
                && rapport.getRefsGroupBys().size() > 0) {
			GroupByBean[] groupBys = buildGroupBy(rapport.getRefsGroupBys());
			context.put("groupBys", groupBys);
		}

		// le tableau
		List<Champ> lstChamps = rapport.getRefsChamps();
		Integer tableWidth = 0;
		if (lstChamps != null) {
			FieldBean[] fields = new FieldBean[lstChamps.size()];
			for (int i = 0; i < lstChamps.size(); i++) {
				Champ champ = lstChamps.get(i);
				
				fields[i] = new FieldBean(champ.getName(), champ.getType(),
						champ.getLibelle(), champ.getPattern(), 
							champ.getTextAlignment(), champ.getWidth());
				tableWidth += champ.getWidth();
			}
			context.put("fields", fields);
		}
		context.put("widthTable", tableWidth);

		Integer pageWidth = 0;
		if (tableWidth < IConstantes.MIN_PAGE_WIDTH) {
			pageWidth = IConstantes.MIN_PAGE_WIDTH;
		} else {
			pageWidth = tableWidth + 2 * IConstantes.MARGIN;
			
		}
		context.put("pageWidth", pageWidth);
		context.put("pageHeight", IConstantes.PAGE_HEIGHT);
		context.put("columnWidth", pageWidth - 2 * IConstantes.MARGIN);
		if(pageWidth >  IConstantes.PAGE_HEIGHT){
			context.put("orientation", "Landscape");
		}else {
			context.put("orientation", "Portrait");
		}
		// Recap generale
        SummaryBean recapGenerale = buildRecapGenerale(rapport
                .getRefRecapGenerale());
		context.put("summary", recapGenerale);

		Blob fichierTemplate = rapport.getRefTemplate().getFichierTemplate();

		VelocityEngine ve = new VelocityEngine();
		ve.init();
		StringWriter writer = new StringWriter();
        ve.evaluate(
                context,
                writer,
                "  ",
                new String(fichierTemplate.getBytes(1,
                        (int) fichierTemplate.length())));
		logger.debug(writer.toString());
        ByteArrayInputStream is = new ByteArrayInputStream(writer.toString()
                .getBytes("UTF-8"));
		return is;
        
	}

	private SummaryBean buildRecapGenerale(RecapGenerale refRecapGenerale) {
		SummaryBean summary = new SummaryBean();
		Integer summaryWidth = 6;
		if(refRecapGenerale != null){
			//recap colonnes
            List<RecapColonne> refsRecapColonnes = refRecapGenerale
                    .getRefsRecapColonnes();
			if(refsRecapColonnes != null){
                RecapColumnBean[] recapColonnes = new RecapColumnBean[refsRecapColonnes
                        .size()];
				for (int i = 0; i < refsRecapColonnes.size(); i++) {
					RecapColonne recapColonne = refsRecapColonnes.get(i);
					Champ champ = recapColonne.getRefChamp();
                    FieldBean fieldBean = new FieldBean(champ.getName(),
                            champ.getType(), champ.getLibelle(),
                            champ.getPattern(), champ.getTextAlignment(),
                            champ.getWidth());
                    recapColonnes[i] = new RecapColumnBean(
                            recapColonne.getName(), fieldBean,
                            recapColonne.getCalculation());
				}
				summary.setRecapsColumns(recapColonnes);
				summaryWidth += 20; 
			}
			
			//les autres recaps
			List<Variable> refVariables = refRecapGenerale.getRefsVariables();
			if(refVariables != null){
				summaryWidth = 30; 
				VariableBean[] variables = new VariableBean[refVariables.size()];
				for (int i = 0; i < refVariables.size(); i++) {
					Variable variable = refVariables.get(i);
                    variables[i] = new VariableBean(variable.getName(),
                            variable.getType(), variable.getLibelle(),
                            variable.getPattern(), variable.getTextAlignment(),
                            variable.getCalculation(),
                            variable.getVariableExpression());
				}
				summary.setVariables(variables);
				summaryWidth += 20 * variables.length; 
			}
		}
		summary.setWidth(summaryWidth);
		return summary;
	}

	private GroupByBean[] buildGroupBy(List<GroupBy> refsGroupBys) {
		if(refsGroupBys == null || refsGroupBys.size() < 1){
			return null;
		}
		GroupByBean[] groupBysBeans = new GroupByBean[refsGroupBys.size()];
		for(int g = 0; g < refsGroupBys.size(); g++){
			GroupBy groupBy = refsGroupBys.get(g);
			
			GroupByBean groupByBean = new GroupByBean();
			
			groupByBean.setName(groupBy.getName());
			groupByBean.setGroupExpression(groupBy.getGroupByExpression());
			
			// Group by Header
			Integer groupHeaderHeight = 10;
            if (groupBy.getAvecColonnesInHeader() != null
                    && groupBy.getAvecColonnesInHeader().booleanValue()) {
				groupHeaderHeight += 20;
				groupByBean.setAvecColonnesInHeader(Boolean.TRUE);
			}
			List<Champ> lstChamps = groupBy.getRefsChamps();
			if (lstChamps != null) {
				FieldBean[] fields = new FieldBean[lstChamps.size()];
				for (int i = 0; i < lstChamps.size(); i++) {
					Champ champ = lstChamps.get(i);
					fields[i] = new FieldBean(champ.getName(), champ.getType(),
                            champ.getLibelle(), champ.getPattern(),
                            champ.getTextAlignment(),
                            IConstantes.MIN_PAGE_WIDTH);
				}
				groupByBean.setFields(fields);
				groupHeaderHeight += 20 * fields.length;
			}
			groupByBean.setGroupHeaderHeight(groupHeaderHeight);

			// Group By Footer
			Integer groupFooterHeight = 20;
            List<Variable> lstFooterVariables = groupBy
                    .getRefsFooterVariables();
			if (lstFooterVariables != null) {
				VariableBean[] footerVariables = new VariableBean[lstFooterVariables
						.size()];
				for (int i = 0; i < lstFooterVariables.size(); i++) {
					Variable footerVariable = lstFooterVariables.get(i);
                    footerVariables[i] = new VariableBean(
                            footerVariable.getName(), footerVariable.getType(),
                            footerVariable.getLibelle(),
                            footerVariable.getPattern(),
                            footerVariable.getTextAlignment(),
                            footerVariable.getCalculation(),
                            footerVariable.getVariableExpression());
				}
				groupByBean.setFooterVariables(footerVariables);
				groupFooterHeight += 20 * footerVariables.length;
			}
			groupByBean.setGroupFooterHeight(groupFooterHeight);
			
			groupBysBeans[g] = groupByBean;
		}
		return groupBysBeans;
	}

	private static EnteteBean buildEntete(Entete entete) {
		EnteteBean enteteBean = new EnteteBean();

		// Titre
		Integer titreHeight = 10; 
		List<LigneTitre> lstTitreLignes = entete.getRefsLignesTitre();
		if (lstTitreLignes != null) {
            LigneTitreBean[] titreLignes = new LigneTitreBean[lstTitreLignes
                    .size()];
			for (int i = 0; i < lstTitreLignes.size(); i++) {
                titreLignes[i] = new LigneTitreBean(lstTitreLignes.get(i)
                        .getLibelle(), lstTitreLignes.get(i).getIsDynamqiue());
			}
			enteteBean.setTitreLignes(titreLignes);
			titreHeight += 20 * titreLignes.length;
		}

		// Sous titre
		if (entete.getSousTitre() != null) {
			enteteBean.setSousTitre(entete.getSousTitre());
			titreHeight += 20;
		}

		Integer height = titreHeight > 160 ? titreHeight : 160;
		// Les parametres de l'entete
		List<Parametre> lstParametres = entete.getRefsParametres();
		if (lstParametres != null) {
            ReportParameterBean[] parametres = new ReportParameterBean[lstParametres
                    .size()];
			for (int i = 0; i < lstParametres.size(); i++) {
				Parametre parametre = lstParametres.get(i);
				parametres[i] = new ReportParameterBean(parametre.getName(),
                        parametre.getType(), parametre.getLibelle(),
                        parametre.getPattern(), parametre.getTextAlignment());
			}
			enteteBean.setParameters(parametres);
			height += 20 * parametres.length;
		}

		enteteBean.setHeight(height);
		return enteteBean;
	}
}
