package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;

@SuppressWarnings("all")
public class InitMvtConsignationCNRAUCConverter extends
		InitMouvementUCConverter {

	DossierRenteManager dossierRenteManager = (DossierRenteManager) ServicesFactory
			.getService(DossierRenteManager.class);
	SinistreManager sinistreManager = (SinistreManager) ServicesFactory
			.getService(SinistreManager.class);
	
	private Sinistre sinistreBD = null;
	private Long idSinistre = null;
	private Logger logger = Logger.getLogger("loggerSINAT");

	public Object doConvertItemsToValueObject(List listeItems) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if (CommonUtils.getInstance().isEmpty(listeItems)) {
			return null;
		}

		Map map = (Map) listeItems.get(0);
		List<Rentier> listRentier = (List<Rentier>) map.get("listRentier");
		if (CommonUtils.getInstance().isEmpty(listRentier)) {
			return map;
		}

		try {
			idSinistre = dossierRenteManager.doGetIdSinistre(listRentier.get(0)
					.getRefDossierRente().getId());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique",e);
		} catch (ProcessEntiteException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique",e);
		} catch (EntiteException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique",e);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique",e);
		}
		try {
			sinistreBD = sinistreManager.getSinistreById(idSinistre);
		} catch (FonctionnelleException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique",e);
		}

		List<RentierVO> listRentierVO = new ArrayList<RentierVO>();
		RentierVO rentierVO = null;

		for (Rentier curRentier : listRentier) {
			EtatRentierVO etatRentierVO = new EtatRentierVO();
			rentierVO = new RentierVO();
			rentierVO.setId(String.valueOf(curRentier.getId()));
			rentierVO.setNom(curRentier.getNom());
			rentierVO.setPrenom(curRentier.getPrenom());
			rentierVO
					.setLienParente(String.valueOf(curRentier.getLienParente()));
			rentierVO.setMontantRenteTrimestrielle(String.valueOf(curRentier
					.getMontantRenteTrimestrielle()));
			// etat rentier
			etatRentierVO.setId(curRentier.getRefEtatRentier().getId());
			etatRentierVO.setLibelle(curRentier.getRefEtatRentier()
					.getLibelle());
			rentierVO.setRefEtatRentier(etatRentierVO);
			if (curRentier.getDateNaissance() != null) {
				rentierVO.setDateNaissance(dateFormat.format(curRentier
						.getDateNaissance().getTime()));
			}
			if (sinistreBD.getRefVictime().getDeces() != null
					&& rentierVO.getRefEtatRentier().getId() == 4
					|| rentierVO.getRefEtatRentier().getId() == 6) {
				if (sinistreBD.getRefVictime().getDeces() == false
						&& Long.valueOf(0).equals(curRentier.getLienParente())) {
					listRentierVO.add(rentierVO);
				} else if (!Long.valueOf(0).equals(curRentier.getLienParente())) {
					listRentierVO.add(rentierVO);
				}
			}

		}

		map.put("listRentierVO", listRentierVO);
		map.remove("listRentier");
		return map;

	}

}
