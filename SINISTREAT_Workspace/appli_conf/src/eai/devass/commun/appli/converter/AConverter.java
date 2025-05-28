package eai.devass.commun.appli.converter;
/** @author kchakib */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AConverter {
	
	String propertyDist() default "";
	String propertyOrig() default "";
	String entiteDist() default "";
	String entiteMapping() default "";
	String customerConverter() default "";
	String pattern() default "";	
	
}
