package eai.devass.commun.appli.util;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class CommonUtils extends StringUtils {

	protected final static Logger logger = Logger.getLogger("loggerSINAT");
	private static CommonUtils instance;
	private static final String PROPERTIES_FILE = "config.config";

	public static CommonUtils getInstance() {
		if (instance == null) {
			return new CommonUtils();
		} else {
			return instance;
		}
	}

	public Object searchObject(List listObject, Object value, String property)
			throws Exception {

		Object object = null;
		if (isEmpty(listObject)) {
			return null;
		}

		for (Object objectInList : listObject) {
			Object valueProperty = BeanUtilsBean.getInstance()
					.getPropertyUtils().getProperty(objectInList, property);
			if (value.equals(valueProperty)) {
				object = objectInList;
				break;
			}
		}

		return object;
	}

	/** Convert object to Map */
	public Map convertObjectToMap(Object object, Map params) {

		if (object == null) {
			return params;
		}
		Object[] fields = getAllFields(object.getClass(), new Field[] {});
		for (Object fieldObj : fields) {
			try {
				Field field = (Field) fieldObj;
				params.put(field.getName(), BeanUtilsBean.getInstance()
						.getPropertyUtils()
						.getProperty(object, field.getName()));

			} catch (Exception e) {
				continue;
			}
			;
		}

		return params;

	}

	/** Convert Map to Objetc */
	public Object convertMapToObject(Object object, Map params) {

		if (params == null) {
			return params;
		}

		Set<Entry<String, Object>> s = params.entrySet();
		for (Map.Entry<String, Object> entry : s) {
			try {
				BeanUtilsBean.getInstance().setProperty(object, entry.getKey(),
						entry.getValue());
			} catch (Exception e) {
				continue;
			}
		}

		return object;
	}

	public boolean isNumber(Class clazz) {
		if (clazz.equals(BigDecimal.class) || clazz.equals(Long.class)
				|| clazz.equals(Float.class) || clazz.equals(Integer.class)) {
			return true;
		} else {
			return false;
		}
	}

	public Field getField(Class clazz, String nameField) {

		Field[] fieldClass = clazz.getDeclaredFields();
		for (Field curField : fieldClass) {
			if (curField.getName().equals(nameField)) {
				return curField;
			}
		}

		if (!clazz.getSuperclass().getSimpleName().equals("Object")) {
			return getField(clazz.getSuperclass(), nameField);
		}

		return null;

	}

	public Field[] getAllFields(Class clazz, Field[] fields) {

		fields = (Field[]) ArrayUtils.addAll(clazz.getDeclaredFields(), fields);
		if (clazz.getSuperclass().getSimpleName().equals("Object")) {
			return fields;

		} else {
			return getAllFields(clazz.getSuperclass(), fields);
		}

	}

	public boolean isCollection(Class type) {
		// le type est l'interface Collection
		if (type.toString().equalsIgnoreCase(Collection.class.toString())) {
			return true;
		} else if (type.isInterface()) {
			if (type.toString().equalsIgnoreCase(List.class.toString())) {
				return true;
			}
			if (type.toString().equalsIgnoreCase(Set.class.toString())) {
				return true;
			}
		}
		// le type est une classe qui implémente Collection, List, Set ou
		// SortedSet
		else {
			Class[] interfaces = type.getInterfaces();
			int intLength = interfaces.length;
			for (int i = 0; i < intLength; i++) {
				if (interfaces[i].toString().equalsIgnoreCase(
						Collection.class.toString())) {
					return true;
				}
				if (interfaces[i].toString().equalsIgnoreCase(
						List.class.toString())) {
					return true;
				}
				if (interfaces[i].toString().equalsIgnoreCase(
						Set.class.toString())) {
					return true;
				}
				if (interfaces[i].toString().equalsIgnoreCase(
						SortedSet.class.toString())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isEmpty(List list) {

		if (list == null || list.isEmpty() || list.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isReference(Class clazz) {
		if (clazz.isPrimitive()) {
			return false;
		}
		Package pakage = clazz.getPackage();
		if (pakage == null || pakage.getName().startsWith("java")
				|| pakage.getName().startsWith("org")) {
			return false;
		}
		return true;
	}


	public boolean isTrue(Boolean bool) {
		if (bool == null) {
			return false;
		}

		return bool;
	}

	public synchronized static Object deserialise(InputStream stream) {

		Object objectOut = null;
		ObjectInputStream ois = null;
		try {

			logger.info("Début déserialisation du fichier : "
					+ stream.getClass());
			ois = new ObjectInputStream(stream);
			objectOut = ois.readObject();
			logger.info("Déserialisation ... [OK]");

		} catch (Exception e) {
			logger.fatal("Impossible de déserialiser l'objet en entrée ", e);

		} finally {
			if (ois != null) {
				try {
					ois.close();

				} catch (Exception e) {
					logger.fatal("Imossible de fermer le flux !!", e);
				}
			}
		}

		return objectOut;

	}

	public Object getValueFromObject(Object object, String property)
			throws Exception {

		if (property.indexOf(".") > 0) {

			StringTokenizer stTokenizer = new StringTokenizer(property, ".");
			String nameEntite = stTokenizer.nextToken();
			String nameProperty = property.substring(nameEntite.length() + 1,
					property.length());

			// Recuperer la valeur du obj, dans le ou il est null, il faut
			// l'instancier
			Object valueObj = BeanUtilsBean.getInstance().getPropertyUtils()
					.getProperty(object, nameEntite);

			if (valueObj == null) {
				return null;
			}

			return getValueFromObject(valueObj, nameProperty);

		} else {
			return BeanUtilsBean.getInstance().getPropertyUtils()
					.getProperty(object, property);
		}
	}

	public String getBoundelResource(String key) {

		try {
			ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_FILE);
			return bundle.getString(key);

		} catch (Exception e) {
			logger.fatal("Erreur lors de la récuperation du ResourceBundle : "
					+ key, e);
			return null;
		}

	}

	public String formatToDouble(String st) {

		try {
			st = st.replaceAll(",", ".").replaceAll(" ", "");
			return URLEncoder.encode(st, "ISO-8859-1").replaceAll("%A0", "");

		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException();
		}

	}

	public Double stringToDouble(String st) {

		try {
			st = formatToDouble(st);
			if (!isDouble(st)) {
				return null;
			}

			BigDecimal bd = new BigDecimal(Double.valueOf(st));

			// setScale is immutable
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			return bd.doubleValue();

		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	public boolean isDouble(String value) {
		try {
			value = formatToDouble(value);
			Double.valueOf(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public byte[] toByteArray(Blob fromBlob) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			return toByteArrayImpl(fromBlob, baos);

		} catch (Exception e) {
			throw new IllegalArgumentException(e);

		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException ex) {
					throw new IllegalArgumentException(
							"Impossible de fermer le flux du BLOB !!!", ex);
				}
			}
		}
	}

	private byte[] toByteArrayImpl(Blob fromBlob, ByteArrayOutputStream baos)
			throws Exception {

		byte[] buf = new byte[4000];
		InputStream is = fromBlob.getBinaryStream();
		try {
			for (;;) {
				int dataSize = is.read(buf);
				if (dataSize == -1) {
					break;
				}
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		return baos.toByteArray();
	}

	static final String ZEROES = "000000000000";
	static final String BLANKS = "            ";

	public static String format(double val, int n, int w) {
		// rounding
		double incr = 0.5;
		for (int j = n; j > 0; j--) {
			incr /= 10;
		}
		val += incr;

		String s = Double.toString(val);
		int n1 = s.indexOf('.');
		int n2 = s.length() - n1 - 1;

		if (n > n2) {
			s = s + ZEROES.substring(0, n - n2);
		} else if (n2 > n) {
			s = s.substring(0, n1 + n + 1);
		}

		if (w > 0 & w > s.length()) {
			s = BLANKS.substring(0, w - s.length()) + s;
		} else if (w < 0 & (-w) > s.length()) {
			w = -w;
			s = s + BLANKS.substring(0, w - s.length());
		}
		return s;
	}

	public static String formatterMontant(double val) {
		Locale locale = new Locale("fr", "FR");
		NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
		String s = fmt.format(val);
		s = s.replace('€', ' ').trim();
		return s;
	}
	
	public String limiterchiffreVirgule(double val) {
		DecimalFormat f = new DecimalFormat();
		String result;
		f.setMaximumFractionDigits(2);
		result = f.format(val);
		return result;
	}
	

}
