package eai.devass.gsr.appli.service.pub;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;

public interface IServicesGsr {
	
	public static final String SERVICE_NAME = "servicesGsr";
	
	/**
	 * 
	 * @param mouvementVO
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IResult enregisterMouvement(MouvementVO mouvementVO, HashMap params) throws Exception;

}
