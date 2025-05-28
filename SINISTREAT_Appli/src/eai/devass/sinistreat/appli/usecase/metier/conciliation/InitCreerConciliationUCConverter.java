package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.AyantDroitOffre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Pieces;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.AyantDroitOffreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.PiecesVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DegreParenteVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PieceVO;

//@SuppressWarnings("all")
public class InitCreerConciliationUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		ConciliationVO conciliationVO = new ConciliationVO();
		OffreVO offreVO = new OffreVO();
		List<PiecesVO> listPiecesVO = new ArrayList<PiecesVO>();
		Map map = new HashMap();
		// Converter Conciliation
		try {
			Conciliation conciliation = (Conciliation) listeItems.get(0);
			for (Pieces p : conciliation.getListPieces()) {
				PiecesVO pVO = new PiecesVO();
				try {
					pVO.setRecu(String.valueOf(p.getRecu()));
					PieceVO pi = new PieceVO();
					pi.setId(String.valueOf(p.getRefPiece().getId()));
					pi.setLibelle(p.getRefPiece().getLibelle());
					pVO.setRefPiece(pi);
				} catch (Exception e) {
					Logger.getLogger("loggerSinat").fatal(
							"Erreur lors de la conversion !", e);
				}
				listPiecesVO.add(pVO);
			}
			conciliationVO.setListPieces(listPiecesVO);
			map.put(conciliationVO.getClass().getSimpleName(), conciliationVO);

			// Converter listAyantDroitOffre

			try {
				List<AyantDroitOffre> listAyd = (List<AyantDroitOffre>) listeItems
						.get(1);
				List<AyantDroitOffreVO> listAydVo = new ArrayList<AyantDroitOffreVO>();
				if (listAyd != null && listAyd.size()!=0 ) {
					String nomClasse = listAyd.get(0).getClass()
							.getSimpleName();

					for (AyantDroitOffre ayd : listAyd) {
						AyantDroitOffreVO aydVo = new AyantDroitOffreVO();
						aydVo.setChoix(String.valueOf(ayd.isChoix()));
						aydVo.setNom(ayd.getNom());
						aydVo.setPrenom(ayd.getPrenom());
						aydVo.setLienParente((DegreParenteVO) converterTools.convertToObjectVOWithoutList(ayd.getLienParente()));
						if (ayd.getDateSignaturePv()!= null){
							aydVo.setDateSignaturePv(dateFormat.format(ayd.getDateSignaturePv()));						
						}
						listAydVo.add(aydVo);

					}
					map.put("list" + nomClasse, listAydVo);
				}
			} catch (Exception e1) {
				Logger.getLogger("loggerSinat").fatal(
						"Erreur lors de la conversion !", e1);
			}
			// Converter Offre

			try {
				Offre off = (Offre) listeItems.get(2);
				offreVO.setIpp(String.valueOf(off.getIpp()));
			} catch (Exception e1) {
				Logger.getLogger("loggerSinat").fatal(
						"Erreur lors de la conversion !", e1);

			}
			// Converter listItems
			for (int i = 2; i < listeItems.size(); i++) {
				Object item = listeItems.get(i);
				if (item instanceof List) {
					List itemList = (List) item;
					if (!itemList.isEmpty()) {
						String nomClasse = itemList.get(0).getClass()
								.getSimpleName();
						map.put("list" + nomClasse,
								converterTools.convertToListObjectVO(itemList));
					}

				} else {
					String nomClasse = item.getClass().getSimpleName();
					map.put(nomClasse,
							converterTools.convertToObjectVOWithoutList(item));
				}
			}
		} catch (Exception e) {
			logger.error("problème technique", e);
		}

		return map;

	}

	public List<Conciliation> doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List<Conciliation> listOut = new ArrayList<Conciliation>();
		ConciliationVO conVO = (ConciliationVO) vo;
		SinistreVO sin = conVO.getRefSinistre();
		Date dateAcc = null;
		Date dateCalc = null;
		/*
		 * RG 4.1.1 si la date survenance sinistre est inferieur au 22/01/2015
		 * le SI bloque la creation de la conciliation
		 */
		try {
			dateAcc = dateFormat.parse(sin.getRefEvenement().getDateAccident());
			dateCalc = new SimpleDateFormat("dd/MM/yyyy").parse("22/01/2015");
			if (dateAcc.before(dateCalc)) {
				throw new FonctionnelleException(
						EXP_DATE_CREATION_SINISTRE_INFERIEUR_22012015);
			}
			Conciliation conciliation = (Conciliation) converterTools
					.convertToObjectBO(conVO);
			listOut.add(conciliation);

		} catch (ParseException e1) {
			logger.error(
					"problème lors de la convertion de la date d'accident", e1);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
