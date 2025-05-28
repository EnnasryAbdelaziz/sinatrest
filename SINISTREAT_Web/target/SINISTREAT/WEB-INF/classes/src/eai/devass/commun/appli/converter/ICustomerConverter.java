package eai.devass.commun.appli.converter;

public interface  ICustomerConverter {

	/**
	 * @param entiteOrig
	 * @param entiteDest
	 * @return
	 * @throws Exception
	 */
	public Object getValuePropertyDest(Object object) throws Exception;
	
	
	/**
	 * @param entiteDest 
	 * @param entiteOrig
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public Object getValuePropertyOrig(Object entiteOrig) throws Exception;
}
