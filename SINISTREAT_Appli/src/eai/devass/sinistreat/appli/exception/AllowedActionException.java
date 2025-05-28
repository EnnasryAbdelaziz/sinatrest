package eai.devass.sinistreat.appli.exception;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;

public class AllowedActionException extends ValidationException {

    public AllowedActionException(String arg0, Object[] values) {
        super(arg0, values);
    }

    public AllowedActionException(String arg0, Throwable arg1, Object[] values) {
        super(arg0, arg1, values);
    }

    public AllowedActionException() {
        super();
    }

    public AllowedActionException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public AllowedActionException(String arg0) {
        super(arg0);
    }

}
