package eai.devass.sinistreat.appli.utils.validation;

/** @author kchakib */

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;

import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Tools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.validation.Validate.Context;
import eai.devass.sinistreat.appli.utils.validation.Validate.LenghtPattern;
import eai.devass.sinistreat.appli.utils.validation.Validate.Type;

public class ValidateTools implements IMessageException {

	private static ValidateTools instance;

	/** Types variables */
	public static final String DOUBLE = "double";
	public static final String INT = "int";
	public static final String LONG = "long";
	public static final String MAIL = "mail";
	public static final String ENUM = "enum";
	public static final String BOOLEAN = "boolean";

	public synchronized static ValidateTools getInstance() {
		if (instance == null) {
			instance = new ValidateTools();
		}

		return instance;
	}

	public void validateVO(Object object, Context context)
			throws ValidationUnitaireException, Exception {

		Field[] fields = ConverterTools.getInstance().getAllFields(
				object.getClass());
		String nameObjectSansVO = object.getClass().getSimpleName()
				.replaceAll("VO", "");

		for (Field field : fields) {

			Object value = null;
			String name = field.getName();

			try {
				value = BeanUtilsBean.getInstance().getPropertyUtils()
						.getProperty(object, name);

			} catch (NoSuchMethodException e) {
				continue;
			} catch (Exception e) {
				throw new ValidationUnitaireException(EXP_TECHNIQUE_PROBLEME,e,
						new String[] { name });
			}

			for (Annotation annotation : field.getDeclaredAnnotations()) {
				if (!(annotation instanceof Validate)) {
					continue;
				}

				Validate validate = (Validate) annotation;
				String libelle = validate.libelle();
				int lenght = validate.lenght();
				Type type = validate.type();
				LenghtPattern lenghtPattern = validate.lenghtPattern();
				Context[] contextObjet = validate.context();
				boolean autoGenarate = validate.autoGenerate();
				boolean isObligatoire = isObligatoire(context, contextObjet,
						validate.obligatoire(), autoGenarate);

				if ("null".equals(libelle)) {
					libelle = getLibelle(name, object);
				}

				// Si le champ est obligatoire
				if (isObligatoire && isNullObject(value, type)) {
					throw new ValidationUnitaireException(EXP_CHAMP_REQUIRED,
							new Object[] { libelle, nameObjectSansVO });
				}

				// Dans le cas d'un identifiant auto generate, il faut que la
				// valeur == null dans le cas de l'ajout
				if (autoGenarate && value != null && !value.equals("")
						&& Context.CREATE.equals(context)) {
					throw new ValidationUnitaireException(
							EXP_CHAMP_AUTO_GENERATE, new Object[] { libelle,
									nameObjectSansVO });
				}

				/* Dans le cas d'une reference d'objet */
				if (Type.REF.equals(type) && value != null && isObligatoire) {
					validateVO(value, Context.REF);
				} else if (Type.LIST.equals(type) && value != null
						&& isObligatoire) {
					List listRefObject = (List) value;
					if (isObligatoire && listRefObject.isEmpty()) {
						throw new ValidationUnitaireException(
								EXP_LIST_OBJECT_REQUIRED,
								new Object[] { libelle });
					}

					for (Object objectInList : listRefObject) {
						validateVO(objectInList, Context.LIST);
					}
				}

				String valueSt = String.valueOf(value);
				if ("null".equals(valueSt)) {
					valueSt = null;
				}

				/** Validate length */
				if (valueSt != null && lenght != -1 && !"null".equals(valueSt)
						&& !value.equals("")) {
					if (LenghtPattern.EXACTE.equals(lenghtPattern)
							&& valueSt.length() != lenght) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_LENGTH_EQAL_NOTVALIDE, new Object[] {
										libelle, nameObjectSansVO, lenght });
					} else if (LenghtPattern.MAX.equals(lenghtPattern)
							&& valueSt.length() > lenght) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_LENGTH_INF_NOTVALIDE, new Object[] {
										libelle, nameObjectSansVO, lenght });
					} else if (LenghtPattern.MIN.equals(lenghtPattern)
							&& valueSt.length() < lenght) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_LENGTH_SUP_NOTVALIDE, new Object[] {
										libelle, nameObjectSansVO, lenght });
					}
				}

				/** Validate type */
				if (Type.INT.equals(type) && valueSt != null
						&& !value.equals("")) {
					try {
						Integer.valueOf(valueSt);
						break;
					} catch (Exception e) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_TYPE_NOTVALIDE,e, new Object[] {
										libelle, nameObjectSansVO, INT });
					}
				}

				else if (Type.LONG.equals(type) && valueSt != null
						&& !value.equals("")) {
					try {
						Long.valueOf(valueSt);
						break;
					} catch (Exception e) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_TYPE_NOTVALIDE,e, new Object[] {
										libelle, nameObjectSansVO, LONG });
					}
				}

				else if (Type.BOOLEAN.equals(type) && valueSt != null
						&& !value.equals("")) {
					try {
						Boolean.valueOf(valueSt);
						break;
					} catch (Exception e) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_TYPE_NOTVALIDE,e, new Object[] {
										libelle, nameObjectSansVO, BOOLEAN });
					}
				}

				else if (Type.DOUBLE.equals(type) && valueSt != null
						&& !"null".equals(valueSt) && !value.equals("")) {
					try {
						Double.valueOf(valueSt);
						break;
					} catch (Exception e) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_TYPE_NOTVALIDE,e, new Object[] {
										libelle, nameObjectSansVO, DOUBLE });
					}
				}

				else if (Type.DATE.equals(type) && valueSt != null
						&& !value.equals("")) {
					String pattern = validate.pattern();
					DateFormat dateFormat = new SimpleDateFormat(pattern);

					try {
						dateFormat.parse(valueSt);
						break;
					} catch (Exception e) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_TYPE_DATE_NOTVALIDE,e, new Object[] {
										libelle, nameObjectSansVO, pattern });
					}
				}

				else if (Type.ENUM.equals(type) && valueSt != null
						&& !field.getType().isEnum()) {
					throw new ValidationUnitaireException(
							EXP_CHAMP_TYPE_NOTVALIDE, new Object[] { libelle,
									nameObjectSansVO, ENUM });
				}

				else if (Type.MAIL.equals(type) && valueSt != null
						&& !value.equals("")) {
					try {
						// A implementerrrr
						break;
					} catch (Exception e) {
						throw new ValidationUnitaireException(
								EXP_CHAMP_TYPE_NOTVALIDE,e, new Object[] {
										libelle, nameObjectSansVO, MAIL });
					}
				}

			}
		}

	}

	private boolean isObligatoire(Context context, Context[] contextsObjet,
			boolean isObligatoire, boolean autoGenerate) {

		if (autoGenerate
				&& (Context.UPDATE.equals(context) || Context.DELETE
						.equals(context))) {
			return true;
		}

		if (!isObligatoire) {
			return false;
		} else if (isInListContext(contextsObjet, context) && isObligatoire) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isInListContext(Context[] contextsObjet, Context context) {

		for (Context curContext : contextsObjet) {
			if (curContext.equals(Context.ALL) || context.equals(curContext)) {
				return true;
			}
		}

		return false;
	}

	public String getLibelle(String name, Object object) {

		String nameObject = (object != null) ? object.getClass()
				.getSimpleName().replaceAll("VO", "") : name;
		String[] stringSplit = null;
		StringBuilder stringOut = new StringBuilder("");

		// Dans les cas des libelles
		if ("libelle".equals(name)) {
			stringSplit = StringUtils.splitByCharacterTypeCamelCase(nameObject);
		} else {
			// Dans le cas des reference d'objet
			if (name.indexOf("ref") >= 0) {
				stringSplit = StringUtils.splitByCharacterTypeCamelCase(name
						.replaceAll("ref", ""));
			} else {
				stringSplit = StringUtils.splitByCharacterTypeCamelCase(name);
			}
		}

		for (String curString : stringSplit) {
			stringOut.append(curString).append(" ");
		}

		return stringOut.toString().toLowerCase();

	}

	private boolean isNullObject(Object value, Type type) {

		if (Type.REF.equals(type)) {
			if (Tools.isNullObjectFields(value)) {
				return true;
			} else {
				return false;
			}

		} else {
			if ((value == null || "null".equals(value) || String.valueOf(value)
					.length() == 0)) {
				return true;
			} else {
				return false;
			}
		}

	}
}
