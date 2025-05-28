package eai.devass.sinistreat.appli.utils.validation;

/** @author kchakib */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
	
	boolean obligatoire() default false;

	int lenght() default -1;

	String libelle() default "null";

	String pattern() default "dd/MM/yyyy";

	LenghtPattern lenghtPattern() default LenghtPattern.MAX ;

	Type type() default Type.STRING ;

	Context[] context() default {Context.ALL};

	boolean autoGenerate() default false;
	
    public static enum Type {
        STRING, INT, LONG, DATE, DOUBLE, ENUM, REF, LIST, BOOLEAN, MAIL
    };

    public static enum LenghtPattern {
        MAX, MIN, EXACTE
    };

    public static enum Context {
        CREATE, UPDATE, DELETE, REF, LIST, ALL
    };
}
