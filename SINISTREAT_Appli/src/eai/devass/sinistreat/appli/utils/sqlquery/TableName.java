package eai.devass.sinistreat.appli.utils.sqlquery;

/** @author chak's */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {
	
	String tableName() default "null";
	
	
}
