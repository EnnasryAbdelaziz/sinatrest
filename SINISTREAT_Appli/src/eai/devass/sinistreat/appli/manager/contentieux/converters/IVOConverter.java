package eai.devass.sinistreat.appli.manager.contentieux.converters;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public interface IVOConverter {
	public IValueObject sinistreToRecoursEntrant(IValueObject vo);
	public IValueObject recoursEntrantToSinistre(IValueObject vo);
	public List sinistreListToRecoursEntrantList(List sinistreList);
	public List recoursEntrantListToSinistreList(List recoursEntrantList);
}
