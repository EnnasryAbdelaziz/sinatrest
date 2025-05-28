package eai.devass.sinistreat.web.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;

@SuppressWarnings("all")
public class TestAppliServlet extends HttpServlet {
	
	private final static String actionParam = "[B@1372a1a";
	private final static String separator = System.getProperty( "line.separator" );
	private Logger logger= Logger.getLogger("loggerSINAT");
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
		
			// Le comparer avec celui de l'entree
			String action = request.getParameter("actionPW");
			if(CommonGsrUtils.isEmpty(action) || !action.equals(actionParam)) {
				printError("Code action incorrecte !! vous n'êtes pas autorisé " +
						"à exécuter  cette action", response.getWriter());
				return;
			}
			
			// LEs Requête  sql sont non permises
			String sqlReq = request.getParameter("sqlReq");
			if(CommonGsrUtils.isEmpty(sqlReq)) {
				printError("Requête  sql incorrecte !!", response.getWriter());
				return;
			}
			boolean isSelect = isSelectClause(sqlReq); 
		
			// Call UC
			OMNIFacade facade = new OMNIFacade();
			HashMap params = new HashMap();
			params.put("sqlReq", sqlReq.trim());
			params.put("isSelect", isSelect);
			OMNIAction omniAction = new OMNIAction();
			omniAction.setActionId("20200008");
			IResult lRes = facade.invokeService(null, omniAction, new QuittanceGsrVO(), params);
			
			if(lRes != null && lRes.hasErrorMessages()) {
				printError(CommonGsrUtils.getInstance().getMessageFromResult(lRes), 
						response.getWriter());
				return;
			}
			
			// OK, selon le type de la req
			if(isSelect) {
				response.getWriter().print(displayTable((List<Object[]>) params.get(
						"listResult"), (List<String>) params.get("columnNames")));
				
			} else {
				printError("Mise à jour effectuée avec sucées : </br>" + sqlReq, response.getWriter());
			}
			
		} catch(Exception e) {
			logger.error("problème technique",e);
		}
		
		
	}
	
	
	private void printError(String msgError, PrintWriter writer) {
		writer.println(msgError);
		writer.println("</br><a href='../application/metier/testAppli.jsp'> RETOUR </a>");
	}
	
	
	private boolean isSelectClause(String sqlReq) {
		return (sqlReq.toUpperCase().indexOf("SELECT") >= 0);
	}
	
	
	private String displayTable(List<Object[]> listResult, List<String> columnsName) {
		
		//
		StringBuilder style = new StringBuilder("<style type='text/css'>table.hovertable {font-family: verdana,arial,sans-serif;")
			.append("font-size:8px;color:#333333;border-width: 1px;border-color: #999999;border-collapse: collapse;}")
			.append("table.hovertable th {background-color:#c3dde0;border-width: 1px;padding: 8px;border-style: solid;border-color: #a9c6c9;}")
			.append("table.hovertable tr {background-color:#d4e3e5;}")
			.append("table.hovertable td {border-width: 1px;padding: 8px;border-style: solid;border-color: #a9c6c9;}")
			.append("</style></br>");
		
		// contenu page
		StringBuilder contenuPAge = new StringBuilder("<a href='../application/metier/testAppli.jsp'> RETOUR </a>")
			.append("Résultats pour les 50 enregistrements : </br>")
		    .append("<div style=\"width: 1200px; height: 800px; overflow-x: scroll;; overflow-y: scroll;\">")
			.append("<table class=\"hovertable\" />");
		
		
		// Columns Names
		if(columnsName != null && !columnsName.isEmpty()) {
			contenuPAge.append("<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\">");
			for(String columnName : columnsName) {
				contenuPAge.append("<td>").append(columnName).append("</td>");			
			}
		}
		
		for(Object[] curObjects : listResult) {
			contenuPAge.append("<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\">");
			for(Object object : curObjects) {
				contenuPAge.append("<td>").append(object).append("</td>");
			}
			contenuPAge.append("<tr>");
		}
		contenuPAge.append("</table>");
		return style.append(contenuPAge).append("</div>").toString();
		
	}
	

}
