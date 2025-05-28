package eai.devass.commun.appli.historisation;
/** @author kchakib */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ALazy {
	
	boolean lazy() default true;
		
	
}
