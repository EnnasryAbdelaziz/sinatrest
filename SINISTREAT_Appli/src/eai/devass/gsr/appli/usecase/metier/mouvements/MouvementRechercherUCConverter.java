package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;

import org.apache.commons.beanutils.BeanUtilsBean;

import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtDecesRentier;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVOConverter;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtDecesRentierVO;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class MouvementRechercherUCConverter extends RechListeVOConverter {


	MouvementVOConverter mouvementVOConverter = new MouvementVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		MouvementVO vo = (MouvementVO) mouvementVOConverter
				.itemToVo((Mouvement) item);
		
		if(item instanceof MvtDecesRentier && ((MvtDecesRentier) item).getEmissionQuittanceDeces()!=null) {
		if(!((MvtDecesRentier) item).getEmissionQuittanceDeces()){
			((MvtDecesRentierVO) vo).setModifiable("true");
			}
		}
		
		MouvementVO mouvementVO = new MouvementVO();
		try {
			BeanUtilsBean.getInstance().copyProperties(mouvementVO, vo);
			
			if(((Mouvement)item).getRefSituationMouvement()!=null &&
					((Mouvement)item).getRefSituationMouvement().size()>0 )
			{
			mouvementVO.setOperateur(((Mouvement)item).getRefSituationMouvement().get(0).getOperateur());
			}
			
		} catch(Exception e) {
			throw new RuntimeException("Impossible de convertir le mouvement");
		}
		return mouvementVO;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<Mouvement> convertValueObjectToItems(Object ovo) throws ValidationException {
		MouvementVO vo = (MouvementVO) ovo;
		Mouvement item = (Mouvement) mouvementVOConverter.voToItem(vo);
		List<Mouvement> res = new ArrayList<Mouvement>();
		res.add(item);
		return res;
	}

}
