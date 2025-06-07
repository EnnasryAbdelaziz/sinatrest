package eai.devass.gsr.appli.businessrule;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.prm.TypeReglement;
import eai.devass.gsr.appli.utile.RibTools;

public class ModeReglementBR {
	
	private QuittanceGsr quittanceGsr;

	public ModeReglementBR(QuittanceGsr quittanceGsr) {
		this.quittanceGsr = quittanceGsr;
	}
	
	public void valideModeReglement() throws ExceptionMetier {
		
		if(quittanceGsr == null) {
			throw new ExceptionMetier("La quittance de réglement est obligatoire");
		}
		
		ModeReglement modeReglement = quittanceGsr.getRefModeReglement(); 
		TypeRgltGsr typeRgltGsr = quittanceGsr.getRefTypeReglement();
		
		// Mode réglement
		if(modeReglement == null || modeReglement.getId() == 0) {
			throw new ExceptionMetier("Le mode réglement est obligatoire");
		}
		
		// Prereglement
		Prerglt prerglt = quittanceGsr.getRefPrerglt();
		
		// Dans le cas cheque
		if(eai.devass.gsr.appli.prm.ModeReglement.Cheque.getId() == modeReglement.getId()) {
			// Type réglement
			if(typeRgltGsr == null || typeRgltGsr.getId() == 0) {
				throw new ExceptionMetier("Le type réglement est obligatoire");
			}
			
			if(prerglt == null) {
				throw new ExceptionMetier("Les information des préreglement sont obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getPourLeCompte())) {
				throw new ExceptionMetier("Pour le compte de est obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getAdresse())) {
				throw new ExceptionMetier("L'adresse du bénéficiare est obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getCodeVille())) {
				throw new ExceptionMetier("La ville du bénéficiare est obligatoire");
			}
			
			// Type cheque
			if(prerglt.getRefTypeCheque() == null || prerglt.getRefTypeCheque().getId() == 0) {
				throw new ExceptionMetier("Le type du chèque est obligatoire");
			}
			
			// Si le type reglement = intermediare !!
			if(typeRgltGsr.getId() == TypeReglement.Intermediare.getId()) {
				if(CommonUtils.isEmpty(prerglt.getRefBordereau())) {
					throw new ExceptionMetier("La réference du bordereau est obligatoire");
				}
				
//				if(!prerglt.getPourLeCompte().equals(prerglt.getLblVirement())) {
//					throw new ExceptionMetier("Le chéque doit être au nom de l'intérmediare");
//				}
			}
		}
		
		// Virement
		if(eai.devass.gsr.appli.prm.ModeReglement.Virement.getId() == modeReglement.getId()) {
			// Type réglement
			if(typeRgltGsr == null || typeRgltGsr.getId() == 0) {
				throw new ExceptionMetier("Le type réglement est obligatoire");
			}
			
			if(prerglt == null) {
				throw new ExceptionMetier("Les information des préreglement sont obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getLblVirement())) {
				throw new ExceptionMetier("Le libellé du vitrement est obligatoire");
			}
			
			// Validaer le RIB
			try {
				RibTools.validerRib(prerglt.getNumRIB());
				
			} catch(Exception e) {
				throw new ExceptionMetier(e.getMessage());
			}
			
			// Type virement
			if(prerglt.getRefTypeVirement() == null || prerglt.getRefTypeVirement().getId() == 0) {
				throw new ExceptionMetier("Le type virement est obligatoire");
			}
			
		}
		
		// Virement
		if(eai.devass.gsr.appli.prm.ModeReglement.Mandat.getId() == modeReglement.getId()) {
			if(prerglt == null) {
				throw new ExceptionMetier("Les information des préreglement sont obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getNumCIN())) {
				throw new ExceptionMetier("Le N° CIN est obligatoire");
			}
			
		}
		
		
		
	}
	
	

}
