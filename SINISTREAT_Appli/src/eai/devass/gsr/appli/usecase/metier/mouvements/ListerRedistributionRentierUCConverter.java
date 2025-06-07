package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.commun.appli.modele.HistoryItem;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;

@SuppressWarnings("all")
public class ListerRedistributionRentierUCConverter implements IValueObjectConverter {

	private CommonGsrUtils commonGsrUtils = CommonGsrUtils.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		
		return null;
	}

	
	public Object convertItemsToValueObject(List item) {
		
		if(commonGsrUtils.isEmpty(item)) {
			return null;
		}
		
		List<Rentier> listRentier = (List<Rentier>) item.get(0);
		if(commonGsrUtils.isEmpty(listRentier)) {
			return null;
		}
		
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
			.getService(TransverseManager.class);
			
		try {
			RentierVO rentierVO = null;
			List<RentierVO> listRentierVO = new ArrayList<RentierVO>();
			HistoryItem historyItem = null;
			Rentier rentierBlob = null;
			for(Rentier curRentier : listRentier) {
				rentierVO = new RentierVO();
				rentierVO.setNom(curRentier.getNomComplet());
				
				if(curRentier.getLienParente() != null)  {
					rentierVO.setLienParente(String.valueOf(curRentier
							.getLienParente()));
				}
				rentierVO.setMontantRenteTrimestrielle(String
						.valueOf(curRentier.getMontantRenteTrimestrielle()));
				rentierVO.setIppTauxRente(String.valueOf(curRentier.getIppTauxRente()));
				
				if(curRentier.getDateNaissance() != null) {
					rentierVO.setDateNaissance(dateFormat.format(curRentier
							.getDateNaissance().getTime()));
				}
				
				if(curRentier.getRefEtatRentier() != null) {
					rentierVO.setEtatRente(curRentier.getRefEtatRentier()
							.getLibelle());
				}
				
				// Recuperer les anciens valeurs
				historyItem = transverseManager.getLastItemHistory(
						curRentier.getId(), Rentier.class);
				if(historyItem == null) {
					listRentierVO.add(rentierVO);
					continue;
				}
				
				// Deserialiser le blob
				rentierBlob = (Rentier) CommonUtils.deserialise(
						new ByteArrayInputStream(historyItem.getSerialisation()));
				rentierVO.setAncienMontantRenteTrimestrielle(String
						.valueOf(rentierBlob.getMontantRenteTrimestrielle()));
				rentierVO.setAncienTauxIpp(String.valueOf(rentierBlob.getIppTauxRente()));
				rentierVO.setDateModification(dateFormat.format(historyItem.getDateVersion().getTime()));
				listRentierVO.add(rentierVO);
			}
			
			return listRentierVO;
			
		} catch(Exception e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	public IValidator getValidator() {
		
		return null;
	}
	
	
}
