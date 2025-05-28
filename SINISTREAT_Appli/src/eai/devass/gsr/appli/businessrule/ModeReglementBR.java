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
			throw new ExceptionMetier("La quittance de r�glement est obligatoire");
		}
		
		ModeReglement modeReglement = quittanceGsr.getRefModeReglement(); 
		TypeRgltGsr typeRgltGsr = quittanceGsr.getRefTypeReglement();
		
		// Mode r�glement
		if(modeReglement == null || modeReglement.getId() == 0) {
			throw new ExceptionMetier("Le mode r�glement est obligatoire");
		}
		
		// Prereglement
		Prerglt prerglt = quittanceGsr.getRefPrerglt();
		
		// Dans le cas cheque
		if(eai.devass.gsr.appli.prm.ModeReglement.Cheque.getId() == modeReglement.getId()) {
			// Type r�glement
			if(typeRgltGsr == null || typeRgltGsr.getId() == 0) {
				throw new ExceptionMetier("Le type r�glement est obligatoire");
			}
			
			if(prerglt == null) {
				throw new ExceptionMetier("Les information des pr�reglement sont obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getPourLeCompte())) {
				throw new ExceptionMetier("Pour le compte de est obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getAdresse())) {
				throw new ExceptionMetier("L'adresse du b�n�ficiare est obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getCodeVille())) {
				throw new ExceptionMetier("La ville du b�n�ficiare est obligatoire");
			}
			
			// Type cheque
			if(prerglt.getRefTypeCheque() == null || prerglt.getRefTypeCheque().getId() == 0) {
				throw new ExceptionMetier("Le type du ch�que est obligatoire");
			}
			
			// Si le type reglement = intermediare !!
			if(typeRgltGsr.getId() == TypeReglement.Intermediare.getId()) {
				if(CommonUtils.isEmpty(prerglt.getRefBordereau())) {
					throw new ExceptionMetier("La r�ference du bordereau est obligatoire");
				}
				
//				if(!prerglt.getPourLeCompte().equals(prerglt.getLblVirement())) {
//					throw new ExceptionMetier("Le ch�que doit �tre au nom de l'int�rmediare");
//				}
			}
		}
		
		// Virement
		if(eai.devass.gsr.appli.prm.ModeReglement.Virement.getId() == modeReglement.getId()) {
			// Type r�glement
			if(typeRgltGsr == null || typeRgltGsr.getId() == 0) {
				throw new ExceptionMetier("Le type r�glement est obligatoire");
			}
			
			if(prerglt == null) {
				throw new ExceptionMetier("Les information des pr�reglement sont obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getLblVirement())) {
				throw new ExceptionMetier("Le libell� du vitrement est obligatoire");
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
				throw new ExceptionMetier("Les information des pr�reglement sont obligatoire");
			}
			
			if(CommonUtils.isEmpty(prerglt.getNumCIN())) {
				throw new ExceptionMetier("Le N� CIN est obligatoire");
			}
			
		}
		
		
		
	}
	
	

}
