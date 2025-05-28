package eai.devass.gsr.appli.usecase.parametrage;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import eai.devass.commun.appli.uc.SimpleBaseUC;


@SuppressWarnings("all")
public class CrudServletUC extends SimpleBaseUC {
	
		
	
	protected void execute(IValueObject entite, HashMap params) throws Exception {
		
		String reqSql = (String) params.get("sqlReq");
		boolean isSelect = (Boolean) params.get("isSelect");
		
		// 2 cas : select ou modifiier
		if(isSelect) {
			try {
				params.put("listResult", getSession().createSQLQuery(reqSql)
						.setMaxResults(50).list());
				
				// Recuperer les columns de la table
				String tableName = getTableName(reqSql);
				if(tableName != null) {
					DatabaseMetaData myMT = getSession().connection().getMetaData();
					ResultSet columns = myMT.getColumns(null, "SINISTREAT", 
							tableName, null);
					
					
					// Une seule table
					String[] columnNames = null;
					List<String> listColumn = new ArrayList<String>();
					int i = 1;
					while(columns.next()) {
						listColumn.add(String.valueOf(columns.getString("COLUMN_NAME")));
						
					}
					params.put("columnNames", listColumn);
				}
			
			} catch(Exception e) {
				addMessageItem(new ErrorMessageItem(e.getMessage()));
				
			} finally {
				getSession().connection().close();
			}
						
		} else {
			// IL faut que la req a une clause where, c'est tres important
			if(reqSql.indexOf("where") < 0) {
				addMessageItem(new ErrorMessageItem(
						"Merci de vérifier votre requête !!!, la clause where est absente"));
				return;
			}
			getSession().createSQLQuery(reqSql).executeUpdate();
			
			// JOurnaliser les operations CRUD
			getSession().createSQLQuery(getInsertOperation(reqSql)).executeUpdate();
		}
			
	}

	
	private String getInsertOperation(String reqSql) {
		
		StringBuilder sql = new StringBuilder("insert into HISTO_CRUD_OPER values (sysdate,'");
		sql.append(reqSql.replaceAll("'", "")).append("')");		
		return sql.toString();
	}
	
	private String getTableName(String reqSql) {
		StringTokenizer tokenizer = new StringTokenizer(reqSql, " ");
		String tableName = null;
		while(tokenizer.hasMoreTokens()) {
			tableName = tokenizer.nextToken();
			if("FROM".equalsIgnoreCase(tableName)) {
				return tokenizer.nextToken();
			}
		}
		
		return null;
	}
	
}


