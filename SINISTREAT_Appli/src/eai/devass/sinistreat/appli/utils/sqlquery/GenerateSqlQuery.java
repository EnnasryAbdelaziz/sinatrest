package eai.devass.sinistreat.appli.utils.sqlquery;

/** @author chak's */

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.utils.sqlquery.SqlProperty.Type;


@SuppressWarnings("rawtypes")
public class GenerateSqlQuery  {

	private static GenerateSqlQuery instance;

	/** Types variables */
	public static final String DOUBLE = "double";
	public static final String INT = "int";
	public static final String LONG = "long";
	public static final String MAIL = "mail";
	public static final String ENUM = "enum";
	public static final String BOOLEAN = "boolean";
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public synchronized static GenerateSqlQuery getInstance() {
		if (instance == null) {
			instance = new GenerateSqlQuery();
		}

		return instance;
	}

	public String generateInsertQuery(Object object) throws Exception {

		// REcuperer le nom de la table
		Class clazz = object.getClass();
		TableName tableName = (TableName) clazz.getAnnotation(TableName.class);
		if(tableName == null) {
			throw new Exception("Le nom de la table est obligatoire, veuillez définir @TableName");
		}
		
		String sqlTableName = tableName.tableName();
		if("null".equals(sqlTableName)) {
			throw new Exception("La proprieté tableName est obligatoire !!!");
		}
		
		// Debut contruction de la requete sql insert
		//logger.info("Debut contruction de la requete sql insert");
		StringBuilder sqlInsert = new StringBuilder("insert into ")
			.append(sqlTableName).append(" (");
		StringBuilder sqlInsertValues = new StringBuilder("values (");
		
		Field[] fields = CommonUtils.getInstance().getAllFields(clazz, new Field[1]);
		Type type = null;
		String nameChamp = null;
		String dafautValue = null;
		int length = 0;
		for (Field curField : fields) {
			
			Object value = null;
			dafautValue = null;			
			if(curField == null) {
				continue;
			}
			
			if("serialVersionUID".equals(curField.getName())) {
				continue;
			}
			
			nameChamp = curField.getName().toUpperCase();
			SqlProperty sqlProperty = curField.getAnnotation(SqlProperty.class);
			
			// Nom du champ
			if(sqlProperty != null && !"null".equals(sqlProperty.champName())) {
				nameChamp = sqlProperty.champName().toUpperCase();
			}
			
			// Valeur par defaut
			if(sqlProperty != null && !"null".equals(sqlProperty.defaultValue())) {
				dafautValue = sqlProperty.defaultValue();
			}
			
			// Ignore le champ courant
			if(sqlProperty != null && sqlProperty.ignore()) {
				continue;
			}

			try {
				if(dafautValue == null) {
					value = BeanUtilsBean.getInstance().getPropertyUtils()
							.getProperty(object, curField.getName());
				} else {
					value = dafautValue;
				}
			
			} catch (Exception e) {
				//throw new Exception("Impossible de récuperer la valeur du champ : " + curField.getName());
			}
			
			// Rien a inserer !!!
			if(value == null) {
				continue;
			}
			
			// Verfier la longeur su champ, ca pose bcp de probleme !!
			if(sqlProperty != null && sqlProperty.length() > 0) {
				length = sqlProperty.length();
				if(String.valueOf(value).length() > length) {
					throw new Exception("La taille du champ : " + nameChamp + " = " + String.valueOf(value).length()
							+ " est supérieur à : " + length);
				}
			}
			
			// Ajouter la valeur du champs
			type = getType(curField, sqlProperty);
			if(type == null) {
				throw new Exception("Type non définit pour le champ [STRING, INT ou DOUBLE] : " + nameChamp);
			}
			
			// Ajouter le nom du champs dans la clause insert
			sqlInsert.append(nameChamp).append(",");		
			if(Type.STRING.equals(type)) {
				sqlInsertValues.append("'");
				sqlInsertValues.append(((String) value).replaceAll("'", "''"));
								
			} else if(Type.DATE.equals(type)) {
				sqlInsertValues.append("to_date('").append(dateFormat.format((Date) value))
					.append("','dd/MM/yyyy')");
				
			}  else if(Type.DATE_SQLSERVER.equals(type)) {
				sqlInsertValues.append(String.valueOf(value).replaceAll(",","."));
			
		    } else {
				sqlInsertValues.append(String.valueOf(value).replaceAll(",","."));
			}
			
			if(Type.STRING.equals(type)) {
				sqlInsertValues.append("'");
			}
			sqlInsertValues.append(",");
		}
		
		// Supprimer les derneir , ds les deux clause
		sqlInsert.replace(sqlInsert.length() - 1, sqlInsert.length(), ")");
		sqlInsertValues.replace(sqlInsertValues.length() - 1, sqlInsertValues.length(), ")");
		sqlInsert.append(" ").append(sqlInsertValues);//.append(";");
		return sqlInsert.toString();

	}
	
	private Type getType(Field field, SqlProperty sqlProperty) {
		if(sqlProperty != null) {
			if(!Type.DEFAULT.equals(sqlProperty.type())) {
				return sqlProperty.type();
			}
		}
		
		Class classField = field.getType();
		if(classField.equals(String.class)) {
			return Type.STRING;
			
		} else if(classField.equals(Integer.class)) {
			return Type.INT;
			
		}  else if(classField.equals(Double.class)) {
			return Type.DOUBLE;
			
		}   else if(classField.equals(Long.class)) {
			return Type.LONG;
			
		} else if(classField.equals(Date.class)) {
			return Type.DATE;
		}  
				
		return null;
	}
	
//	private void checkLength(Object value, int lenght) throws Exception {
//		
//		//Class classField = field.getType();
//		int curLengt = String.valueOf(value).length();
//		if(curLengt > lenght) {
//			
//		}
//	}
	
	

	
}
