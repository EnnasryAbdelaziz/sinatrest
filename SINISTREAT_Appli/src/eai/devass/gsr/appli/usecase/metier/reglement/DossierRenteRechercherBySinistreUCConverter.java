package eai.devass.gsr.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;

import com.sun.star.lang.IllegalArgumentException;

import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVOConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;

public class DossierRenteRechercherBySinistreUCConverter extends
		ValueObjectConverterAbst {

	
	private CommonGsrUtils commonGsrUtils = CommonGsrUtils.getInstance();
	private DossierRenteVOConverter dossierRenteVOConverter = new DossierRenteVOConverter();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List item) {
		RechListeVO rechListeVO = new RechListeVO();
		try
		{
			List<DossierRente> listDossierRente = (List<DossierRente>) item.get(0);
			if(commonGsrUtils.isEmpty(listDossierRente)) {
				return null;
			}
			
			List<DossierRenteVO> listDossierRenteVO = new ArrayList<DossierRenteVO>();
			Sinistre sinistre = null;
			SinistreVO sinistreVO = null;
			DossierRenteVO dossierRenteVO = null;
			VictimeVO victimeVO = null;
			VilleVO villeVO = null;
			RentierVO rentierVO = null;
			List<RentierVO> rentierVOs = null;
			List<Rentier> listRentiers = null;
			for(DossierRente curDossierRente : listDossierRente) {
				dossierRenteVO = new DossierRenteVO();
				dossierRenteVO.setId(String.valueOf(curDossierRente.getId()));
				dossierRenteVO.setNumeroRente(String.valueOf(curDossierRente.getNumeroRente()));
				sinistreVO = new SinistreVO();
				
				// Convert le dossier sinistre
				sinistre = curDossierRente.getRefSinistre();
				if(sinistre == null) {
					throw new IllegalArgumentException("Impossible de trouver le dossier sinistre de la rente : "
									+ curDossierRente.getNumeroRente());
				}
				
				sinistreVO.setNumeroGrave(sinistre.getNumeroGrave());
				sinistreVO.setNumeroSinistre(sinistre.getNumeroSinistre());
				
				// Convert victime
				if(sinistre.getRefVictime() != null) {
					victimeVO = new VictimeVO();
					victimeVO.setNom(sinistre.getRefVictime().getNom());
					victimeVO.setPrenom(sinistre.getRefVictime().getPrenom());
					victimeVO.setAdresse(sinistre.getRefVictime().getAdresse());
					villeVO = new VilleVO();
					if(sinistre.getRefVictime().getRefVille() != null) {
						villeVO.setLibelle(sinistre.getRefVictime()
								.getRefVille().getLibelle());
					}
					victimeVO.setRefVille(villeVO);
					sinistreVO.setRefVictime(victimeVO);
				}
				dossierRenteVO.setRefSinistre(sinistreVO);
				
				// Liste des rentiers
				listRentiers = curDossierRente.getRefsRentier();
				if(!commonGsrUtils.isEmpty(listRentiers)) {
					rentierVOs = new ArrayList<RentierVO>();
					for(Rentier curRentier : listRentiers) {
						rentierVO = new RentierVO();
						rentierVO.setId(String.valueOf(curRentier.getId()));
						rentierVO.setLienParente(String.valueOf(curRentier.getLienParente()));
						rentierVO.setNom(curRentier.getNom());
						rentierVO.setPrenom(curRentier.getPrenom());
						rentierVO.setMontantRenteTrimestrielle(String
								.valueOf(curRentier.getMontantRenteTrimestrielle()));
						rentierVO.setAdresse(curRentier.getAdresse());
						rentierVO.setVille(curRentier.getVille());
						rentierVOs.add(rentierVO);
					}
					dossierRenteVO.setRefsRentier(rentierVOs);
				}
				
				listDossierRenteVO.add(dossierRenteVO);
				
			}
			
			rechListeVO.setCurrentRes(listDossierRenteVO);
			rechListeVO.setNbResultats((Integer)item.get(1));
			rechListeVO.setNumPage((Integer)item.get(2));
			rechListeVO.setPageSize((Integer)item.get(3));
			
			// Nbr de pages
			long nbrPages = 0;
			if(rechListeVO.getNbResultats() > rechListeVO.getPageSize()) {
				nbrPages = ((long) (rechListeVO.getNbResultats()/rechListeVO.getPageSize())) + 1;
				rechListeVO.setCritere(String.valueOf(nbrPages));
			}
			
		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		return rechListeVO;
	}

	/**
	 * Methode qui convertit un Value Object à une liste d' entités
	 * 
	 * @param ovo
	 *            value object à convertir
	 * @returns Listes des entités resultats convertis
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	public List<DossierRente> doConvertValueObjectToItems(Object ovo)
			throws ValidationException {
		DossierRenteVO vo = (DossierRenteVO) ovo;
		DossierRente item = new DossierRente();
		dossierRenteVOConverter.voToItem(vo, item);
		List<DossierRente> res = new ArrayList<DossierRente>();
		res.add(item);
		return res;
	}

}
