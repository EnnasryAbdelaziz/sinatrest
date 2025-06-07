package eai.devass.gsr.appli.usecase.metier.reglement;

import java.util.HashMap;
import java.util.StringTokenizer;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.valueobjects.parametrage.ParamVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

public class InitCreationQuittanceUC extends SimpleBaseUC {

	protected void execute(IValueObject entite, HashMap params) throws Exception {
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		try {
			
			ParamVO paramVO = (ParamVO) entite;
			String libelle = paramVO.getLibelle();
			if(CommonUtils.isEmpty(libelle)) {
				return ;
			}

			StringTokenizer listParms = new StringTokenizer(libelle, "|");
			Class clazz = null;
			String className = null;
			while(listParms.hasMoreTokens()) {
				try {
					className = listParms.nextToken();
					clazz = Class.forName(className);
					
					// POur la nature quittance, il faut ajouter un condition !!
					if(clazz.equals(NatureQtcGsr.class)) {
						paramToConverter.put("list" + getNameObjectVO(className),
							transverseManager.listNatureQtcManuelle(new Long[] {
								NatureQuittance.rachat_apres_constitution_recours_victime.getId(),
								NatureQuittance.capital_constitutif_a_la_CNRA.getId(),
								NatureQuittance.Prorata_rente.getId(),
								NatureQuittance.Complement_Capital_constitutif_CNRA.getId(),
								NatureQuittance.Complement_rente.getId(),
								NatureQuittance.Rente_periodique.getId(),
								NatureQuittance.Capital_remariage.getId(),
								NatureQuittance.Prothese.getId(),
								NatureQuittance.Droits_Taxes.getId()}));
						continue;
					}
					
					// Aussi pour le type reglement
					if(clazz.equals(TypeRgltGsr.class)) {
						paramToConverter.put("list" + getNameObjectVO(className),
							transverseManager.listTypeReglementQtcManuelle(new Long[] {
								eai.devass.gsr.appli.prm.TypeReglement.Direct.getId(),
								eai.devass.gsr.appli.prm.TypeReglement.Mondat.getId()}));
						continue;
					}
					
					paramToConverter.put("list" + getNameObjectVO(className),
							transverseManager.getSimilarObject(clazz.newInstance()));
					
				} catch(Exception e) {
					logger.fatal("Impossible de charger la liste des " + className, e);
				}
			}
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