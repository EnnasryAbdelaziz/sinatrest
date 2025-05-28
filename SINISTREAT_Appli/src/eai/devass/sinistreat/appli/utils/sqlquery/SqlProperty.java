package eai.devass.sinistreat.appli.utils.sqlquery;

/** @author chak's */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SqlProperty {
	
	String champName() default "null";
	String defaultValue() default "null";
	int length() default 0;
	boolean ignore() default false ;
	Type type() default Type.DEFAULT ;
	
	public static enum Type {DEFAULT, STRING, INT, LONG, DATE ,DOUBLE, ENUM, REF ,LIST, BOOLEAN,MAIL, DATE_SQLSERVER};
	
}
