package eai.devass.gsr.appli.usecase.parametrage.ihm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;


@SuppressWarnings("all")
public abstract class InitAbstractMouvementUC extends SimpleBaseUC {

	private final static String PKG_VALUEOBJECT = "eai.devass.gsr.appli.valueobjects.parametrage.";
	protected DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected CommonGsrUtils commonGsrUtils = CommonGsrUtils.getInstance();	
	public abstract void executeUC(IValueObject valueObject, HashMap params) throws Exception;
	
	protected void execute(IValueObject entite, HashMap params) throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory
			.getService(TransverseManager.class);
		
		// Vérification Transition ETAT type Mouvement
		BeanDTOVO paramVO = (BeanDTOVO) entite;
		try {
			if(paramVO == null) {
				throw new ExceptionMetier("Le parmaVO ne peut être null");
			}
			
			String idTypeMouvement = paramVO.getTypeMouvement();
			if(!commonUtils.isNumeric(idTypeMouvement)) {
				throw new ExceptionMetier("Le type mouvement ne peut être null");
			}
			
			TypeMouvement typeMouvement = new TypeMouvement();
			typeMouvement.setId(Long.valueOf(idTypeMouvement));
			String idRentier = paramVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("L'identifiant du rentier ne peut être null");
			}
			Rentier rentier = new Rentier(Long.valueOf(idRentier));
			
			// lA LISTE DES etats possible pour le type mouvement
			List<EtatMvt> listEtatMvt = transverseManager
					.getListTransitEtatMvt(typeMouvement, rentier);
			if(commonUtils.isEmpty(listEtatMvt)) {
				//throw new ExceptionMetier("Ce type mouvement ne peut pas changer l'etat du rentier");
			}
			
			// Date echeace : 
			Date calLastEcheance = transverseManager
					.getDateDerniereEcheanceReglee(Long.valueOf(idRentier));
			
			if (calLastEcheance != null)
			{
				Date trimestre = commonGsrUtils.getDateFinCurrentTrimestre(calLastEcheance);
				paramToConverter.put("dateDerniereEcheance", (trimestre == null) ? ""
						: dateFormat.format(trimestre));
			}
		
			// OK, chargement des liste de parametrage 
			List<String> listObject = paramVO.getListObject();
			if(!commonUtils.isEmpty(listObject)) {
				Class clazz = null;
				for(String className : listObject) {
					try {
						clazz = Class.forName(className);
						paramToConverter.put("list" + getNameObjectVO(className),
								transverseManager.getSimilarObject(clazz.newInstance()));
						
					}  catch(Exception e) {
						//throw new ExceptionMetier("Impossible de charger la liste des " + className);
						logger.warn("Impossible de charger la liste des " + className, e);
					}
				}
			}
			
			
			
			// Run UC du mouvement en question
			executeUC(entite, params);
			addResultItem(paramToConverter);
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}
	
	private String getNameObjectVO(String className) {
		StringTokenizer stringTokenizer = new StringTokenizer(className, ".");
		String nameObject = null;
		while (stringTokenizer.hasMoreElements()) {
			nameObject = stringTokenizer.nextToken();
			
		}
		
		return nameObject + "VO";
	}

	public boolean isTransactionnal() {
		return false;
	}

}