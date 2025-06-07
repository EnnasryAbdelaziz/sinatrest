package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeCertificat;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeCertificatVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeCertificatVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeCertificatRechercherUCConverter extends RechListeVOConverter {


	TypeCertificatVOConverter typeCertificatVOConverter = new TypeCertificatVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeCertificatVO vo = new TypeCertificatVO();
		typeCertificatVOConverter.itemToVo(vo, (TypeCertificat)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeCertificat> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeCertificatVO vo = (TypeCertificatVO) ovo;
		TypeCertificat item = new TypeCertificat();
		typeCertificatVOConverter.voToItem(vo,item);
		List<TypeCertificat> res = new ArrayList<TypeCertificat>();
		res.add(item);
		return res;
	}

}
