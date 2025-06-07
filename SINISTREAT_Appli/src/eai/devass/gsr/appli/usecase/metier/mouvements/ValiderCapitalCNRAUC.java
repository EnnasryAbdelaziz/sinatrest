package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.utile.IMessageMvts;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtConsignCNRAVO;

public class ValiderCapitalCNRAUC extends SimpleBaseUC {
	
	private static final String FALSE = "false";
	
	@Override
	protected void execute(IValueObject valueObject, HashMap params)
			throws Exception {
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		MvtConsignCNRAVO mouvement = (MvtConsignCNRAVO) valueObject;
		if(mouvement == null) {
			throw new ExceptionMetier("Le mouvement ne peut être null !!!");
		}
		
		List<RentierVO> listRent = mouvement.getListRentierVO();
		if(commonUtils.isEmpty(listRent)) {
            throw new ExceptionMetier(
                    "La liste des renties ne peut être null !!!");
		}
		
		String avp = mouvement.getAvp();
		if(CommonUtils.isEmpty(avp)) {
			throw new ExceptionMetier("L' avp ne peut être null !!!");
		}
		
		StringBuffer message = new StringBuffer();
		Double capitalGSR = 0D;
		Double totalCapitalGSR = 0D;
		Double capitalCNRA = 0D;
		Double diffCapital = 0D;
		Double totalDiffCapital = 0D;
		for(RentierVO curRentierVO : listRent) {
            capitalGSR = transverseManager.getCapitalGSRyRentier(curRentierVO
                    .getId());
			totalCapitalGSR += capitalGSR;
			capitalCNRA = commonUtils.stringToDouble(curRentierVO
					.getMntCapitalCnra());
			diffCapital = capitalCNRA - capitalGSR;
			totalDiffCapital += diffCapital;
			curRentierVO.setMntCapitalCnraCalcule(String.valueOf(capitalGSR));
			curRentierVO.setMntCapitalCnraDiff(String.valueOf(diffCapital));
			if(diffCapital <= 0) {
				continue;
			}
			
			if (FALSE.equals(avp)) {
                // Cas non avp. En cas de différence entre ces 2 capitaux (CCNRA
                // > CGSR) la
                // différence est tolérable à hauteur de 5% du capital GSR avec
                // un maximum de 1 000,00 Dhs
                if (diffCapital > (capitalGSR * 5 / 100) && diffCapital > 1000) {
					message.append(IMessageMvts.AVP_DIFF_SUP_5_PC);
				}
				
			} else {
				// Cas AVP : Si CCNRA>CGSR, alors afficher un message d’alerte.
				message.append(IMessageMvts.NON_AVP_DIFF_1)
						.append(curRentierVO.getNom()).append(" ")
						.append(IMessageMvts.NON_AVP_DIFF_2);
			}
		}
		
		mouvement.setCapitalCalcule(String.valueOf(totalCapitalGSR));
		mouvement.setDiffMntCapitalCnra(String.valueOf(totalDiffCapital));	
		paramToConverter.put("message", message.toString());
		paramToConverter.put("mouvement", mouvement);
		addResultItem(paramToConverter);
		
	}

	/**
	 * Methode pour activer le service de transaction
	 * 
	 * @returns soit true pour activer le service de transaction ou false pour
	 *          le desactiver
	 */
	public boolean isTransactionnal() {
		return true;
	}

	/**
	 * Methode pour activer le service de trace
	 * 
	 * @returns soit true pour activer le service de trace ou false pour le
	 *          desactiver
	 */
	public boolean isTracable() {
		return false;
	}

}