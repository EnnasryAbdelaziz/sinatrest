package eai.devass.sinistreat.appli.services.pub;

import ma.co.omnidata.framework.services.businessInterface.IResult;

public interface ISynchroniseSinistreAT  {
	
	/**
	 * Pour sunchroniser les dossiers sinistres dans la base DBC
	 * @return
	 * @throws Exception
	 */
	public String SERVICE_NAME = "synchroniseSinistreAT";
	
	public IResult synchroniseSinistre() throws Exception;
}
