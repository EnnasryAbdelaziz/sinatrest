package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.parametrage.Nationalite;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.ModePayementVO;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;

@SuppressWarnings("all")
public class EditerRentierUCConverter implements IValueObjectConverter {

	//private QuittanceGsrVOConverter quittanceVOConverter = new QuittanceGsrVOConverter();
	private CommonGsrUtils commonGsrUtils = CommonGsrUtils.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	//private RentierManager rentierManager = (RentierManager) ServicesFactory.getService(RentierManager.class);
	
	private TransverseManager transverseManager = (TransverseManager) ServicesFactory
			.getService(TransverseManager.class);

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		
		return null;
	}

	
	public Object convertItemsToValueObject(List item) {
		
		if(item == null || item.isEmpty()) {
			return null;
		}
		
		Rentier rentier = (Rentier) item.get(0);
		try {
			RentierVO rentierVO = new RentierVO();
			rentierVO.setAdresse(rentier.getAdresse());			
//			List<Nationalite> nationalite = transverseManager
//					.getSimilarObject(rentier.getRefNationalite());
			
			Nationalite nationalite = rentier.getRefNationalite();
			if(nationalite != null) {
				rentierVO.setNationalite(nationalite.getLibelle());
			}
			List<Ville> ville = transverseManager.getSimilarObject(new Ville(rentier.getVille()));
			if(!commonGsrUtils.isEmpty(ville)) {
				rentierVO.setVille(ville.get(0). getLibelle());
			}
			
			if(commonGsrUtils.isTrue(rentier.getApprobationQuittance())) {
				rentierVO.setApprobationQuittance("Oui");
				
			} else {
				rentierVO.setApprobationQuittance("Non");
			}
			
			if(rentier.getDateDepartRente() != null) {
				rentierVO.setDateDepartRente(dateFormat.format(rentier
						.getDateDepartRente().getTime()));
			}
			if(rentier.getDateNaissance() != null) {
				rentierVO.setDateNaissance(dateFormat.format(rentier
						.getDateNaissance().getTime()));
			}
						
			rentierVO.setEmail(rentier.getEmail());			
			if(rentier.getRefEtatRentier() != null) {
				rentierVO.setEtatRente(rentier.getRefEtatRentier().getLibelle());
			}
			if(rentier.getLienParente() != null) {
				rentierVO.setLienParente(String.valueOf(rentier.getLienParente()));
			}
			if(rentier.getRefModePayement() != null 
					&& rentier.getRefModePayement().getIdModePayement() != 0) {
				ModePayementVO modePayementVO = new ModePayementVO();
				modePayementVO.setId(String.valueOf(rentier.getRefModePayement().getIdModePayement()));
				rentierVO.setRefModePayement(modePayementVO);
			}
			
			rentierVO.setNom(rentier.getNom());
			rentierVO.setPrenom(rentier.getPrenom());
			rentierVO.setNumeroCIN(rentier.getNumeroCIN());
			
			if(rentier.getCapitalConstitutif() != null) {
				rentierVO.setCapitalConstitutif(String.valueOf(rentier.getCapitalConstitutif()));
			}
			
			if(rentier.getMontantRenteTrimestrielle() != null) {
				rentierVO.setMontantRenteTrimestrielle(String.valueOf(rentier
						.getMontantRenteTrimestrielle()));
			}
			
			rentierVO.setNumeroTelephone(rentier.getNumeroTelephone());	
			rentierVO.setNumeroGSM(rentier.getNumeroGSM());	
			if(commonGsrUtils.isTrue(rentier.getRentierARisque())) {
				rentierVO.setRentierARisque("Oui");
				
			} else {
				rentierVO.setRentierARisque("Nom");
			}
			
			
			return rentierVO;
			
		} catch(Exception e) {
			throw new IllegalArgumentException();
		}
		
	}

	public IValidator getValidator() {
		
		return null;
	}
	
	
}
