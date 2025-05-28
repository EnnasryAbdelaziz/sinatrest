package eai.devass.commun.appli.rg;
/** @author kchakib */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ASkipRG {
	
	String property() default "";
	String value() default "";
	String bean() default "";
	String isEmty() default "";
		
	
}
