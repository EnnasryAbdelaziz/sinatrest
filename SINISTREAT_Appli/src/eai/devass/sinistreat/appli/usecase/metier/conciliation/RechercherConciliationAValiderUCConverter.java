package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinMotifOffre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.MotifOffre;
import eai.devass.sinistreat.appli.modele.parametrage.OrigineConciliation;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.SinMotifOffreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EvenementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MotifOffreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.OrigineConciliationVO;

public class RechercherConciliationAValiderUCConverter extends ValueObjectConverterAbst {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	public Object doConvertItemsToValueObject(List listeItems) {

		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<ConciliationVO> listConciliationVO = new ArrayList<ConciliationVO>();
		try {
			List<Conciliation> listConciliation = (List<Conciliation>) listeItems.get(0);
			for (Conciliation conciliation : listConciliation) {
				if (conciliation != null && conciliation instanceof Conciliation) {

					ConciliationVO conciVO = new ConciliationVO();

					//Offre
					List<Offre> listOff = conciliation.getListOffre();
					Offre offre = listOff.get(0);
					//Situation offre
					List<SinMotifOffre> listSinMotifOff = listOff.get(0).getListSinMotifOffre();
					SinMotifOffre sinMotifOffre = listSinMotifOff.get(0);
					//Motif offre
					MotifOffre motifOffre =  listOff.get(0).getRefMotifOffre();
					//Sinistre
					Sinistre sinistre = conciliation.getRefSinistre();
					//Victime
					Victime victime = sinistre.getRefVictime();
					//Evenement
					Evenement evenement = sinistre.getRefEvenement();
					
					OrigineConciliation origine = conciliation.getRefOrigineConciliation();
					
					// convertion manuelle : pour ne convertir que les elements
					// necessaire pour l'IHM

					//Motif offre
					((MotifOffre) motifOffre).setPropertiesToConvert(new String[] { "id","libelle" });
					MotifOffreVO motifOffres = (MotifOffreVO) ConverterTools.getInstance().convertToObjectVOWithoutList(motifOffre);
					
					//Situation offre
					((SinMotifOffre) sinMotifOffre).setPropertiesToConvert(new String[] { "id","userCreateur","refMotifOffre" });
					List<SinMotifOffreVO> listSinMotifOffres = (List<SinMotifOffreVO>) ConverterTools
							.getInstance().convertToListObjectVOWithoutList(listSinMotifOff);
					//Offre
					((Offre) offre).setPropertiesToConvert(new String[] { "id","dateOffre", "reserveOffre","montantRente","listAyantDroit" });
					List<OffreVO> listOffreVO = (List<OffreVO>) ConverterTools.getInstance().convertToListObjectVO(listOff);
					listOffreVO.get(0).setRefMotifOffre(motifOffres);
					listOffreVO.get(0).setListSinMotifOffre(listSinMotifOffres);
					//Sinistre
					((Sinistre)sinistre).setPropertiesToConvert(new String[] {"numeroSinistre"});
					SinistreVO sinistreVO = (SinistreVO) ConverterTools.getInstance().convertToObjectVOWithoutList(sinistre);
					//Victime
					((Victime)victime).setPropertiesToConvert(new String[] {"nom"});
					VictimeVO victimeVO = (VictimeVO) ConverterTools.getInstance().convertToObjectVOWithoutList(victime);
					sinistreVO.setRefVictime(victimeVO);
					//Evenement
					((Evenement)evenement).setPropertiesToConvert(new String[] {"dateAccident"});
					EvenementVO evenementVO = (EvenementVO) ConverterTools.getInstance().convertToObjectVOWithoutList(evenement);
					sinistreVO.setRefEvenement(evenementVO);
					//Origine conciliation
					OrigineConciliationVO origineVO = (OrigineConciliationVO) ConverterTools.getInstance().convertToObjectVOWithoutList(origine);
					conciVO.setRefOrigineConciliation(origineVO); 
					conciVO.setListOffre(listOffreVO);
					conciVO.setId(conciliation.getId());
					conciVO.setRefSinistre(sinistreVO);
					conciVO.setUserCreateur(conciliation.getUserCreateur());
					
					listConciliationVO.add(conciVO);
					if (listConciliationVO != null && !listConciliationVO.isEmpty()) {
						map.put("listConciliationVO",listConciliationVO);
					}

				}
			}
			return map;

		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return null;
	}

	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		ConciliationVO conVO = (ConciliationVO) vo;

		try {
			Conciliation conciliation = (Conciliation) converterTools
					.convertToObjectBO(conVO);
			listOut.add(conciliation);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
