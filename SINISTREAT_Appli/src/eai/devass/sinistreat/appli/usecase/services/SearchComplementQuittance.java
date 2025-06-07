package eai.devass.sinistreat.appli.usecase.services;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.reglement.EtatReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.ComplementQuittanceView;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.ComplementQuittanceVO;


public class SearchComplementQuittance extends FacadeServiceUseCase {

	
	private final static  String TYPE_REGLEENT_AT = "AT";
	private DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	
	protected void doExecuter(IValueObject vo, HashMap map) throws Exception {

		SinistreManager complQtcManager = (SinistreManager) ServicesFactory
				.getService(SinistreManager.class);
		ComplementQuittanceVO complementQuittanceVO = (ComplementQuittanceVO) vo;

		ComplementQuittanceView complementQtc = complQtcManager.getComplementQuittance(complementQuittanceVO
						.getNumeroQuittance());
		complementQuittanceVO = convertObjectToVO(complementQtc);
		this.addResultItem(complementQuittanceVO);
		
		// Dans le cas ou complementQuittanceVO == null !!
		if(complementQuittanceVO == null) {
			return;
		}
		
		//CK11122014 : mettre à jour l'etat de la quittace		
		try {
			String typeReglement = complementQtc.getSource();
			
			// Seulement ds le ca reglement AT (est non pas GSR prothese)
			if(TYPE_REGLEENT_AT.equals(typeReglement) && map != null) {
				Long idReglement = Long.valueOf(complementQtc.getIdReglement());
				EtatReglement etatReglement = new EtatReglement(new EtatRgl("10"));
				
				String dateSort = (String) map.get("dateSort");
				String numCheque = (String) map.get("numCheque");
				if(dateSort != null) {
					etatReglement.setDateEtat(dateFormat.parse(dateSort));
				} else {
					etatReglement.setDateEtat(new Date());
				}
				Reglement reglement = new Reglement();
				reglement.setId(idReglement);
				etatReglement.setRefReglement(reglement);
				etatReglement.setMotifEtat("Synchronisation QTC");
				etatReglement.setUtilisateurCreateur("BATCH SYNCHRO");
				complQtcManager.majReglement(etatReglement, numCheque);
			}
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Probléme lors de la mise à jour du reglement : " 
					+ complementQuittanceVO.getNumeroQuittance());
		}
		
	}

	private ComplementQuittanceVO convertObjectToVO(ComplementQuittanceView complementQtc) {
		ComplementQuittanceVO complementQuittanceVO = new ComplementQuittanceVO();
		if(complementQtc == null){
			return null;
		}
		complementQuittanceVO.setNumeroQuittance(complementQtc.getNumeroQuittance());
		complementQuittanceVO.setTypeSinistre(complementQtc.getTypeSinistre());
		complementQuittanceVO.setCodeValidite(complementQtc.getCodeValidite());
		complementQuittanceVO.setNumeroGrave(complementQtc.getNumeroGrave());
		complementQuittanceVO.setNatureContrat(complementQtc.getNatureContrat());
		complementQuittanceVO.setCodeClassificationPolice(complementQtc.getCodeClassificationPolice());
		complementQuittanceVO.setCodeClient(complementQtc.getCodeClient());
		complementQuittanceVO.setTypeBeneficiaire(complementQtc.getTypeBeneficiaire());
		complementQuittanceVO.setAssure(complementQtc.getAssure());
		complementQuittanceVO.setCodeOperateurRedacteur(complementQtc.getCodeOperateurRedacteur());
		complementQuittanceVO.setCodeDecentralisation(complementQtc.getCodeDecentralisation());
		complementQuittanceVO.setDateAccident(complementQtc.getDateAccident());
		complementQuittanceVO.setDateEffetPolice(complementQtc.getDateEffetPolice());
		complementQuittanceVO.setCodeEmetteurQtc(complementQtc.getCodeEmetteurQtc());
		complementQuittanceVO.setCodeBeneficiaire(complementQtc.getCodeBeneficiaire());
		complementQuittanceVO.setRefSinistre(complementQtc.getRefSinistre());
		complementQuittanceVO.setNomBeneficiaire(complementQtc.getNomBeneficiaire());
		complementQuittanceVO.setNomVictime(complementQtc.getNomVictime());
		complementQuittanceVO.setDateSinistre(complementQtc.getDateSinistre());
		complementQuittanceVO.setAnneeEffetPolice(complementQtc.getAnneeEffetPolice());
		complementQuittanceVO.setCodeAuxiliaire(complementQtc.getCodeAuxiliaire());
		complementQuittanceVO.setAdresseBeneficiaire(complementQtc.getAdresseBeneficiaire());
		complementQuittanceVO.setVilleBeneficiaire(complementQtc.getVilleBeneficiaire());
		return complementQuittanceVO;
	}
}

