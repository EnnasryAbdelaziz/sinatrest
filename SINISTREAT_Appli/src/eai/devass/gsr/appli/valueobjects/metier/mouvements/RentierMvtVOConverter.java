/**
 * 
 */
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation;
import eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt;
import eai.devass.gsr.appli.modele.parametrage.MvtEtatRentier;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVOConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.TuteurVO;
import eai.devass.gsr.appli.valueobjects.parametrage.MvtEtatRentierVO;
import eai.devass.gsr.appli.valueobjects.parametrage.MvtEtatRentierVOConverter;

/**
 * @author elfaismo
 *
 */
public class RentierMvtVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
	throws ValidationException {
		if(ovo instanceof RentierMvtVO){
		RentierMvtVO vo = (RentierMvtVO) ovo;
		this.doValider(vo);
		RentierMvt item = new RentierMvt();
		voToItem(vo, item);
		List<RentierMvt> itemList = new ArrayList<RentierMvt>();
		itemList.add(item);
		return itemList;
		}else{
			return null;
		}
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		RentierMvtVO vo = new RentierMvtVO();
		if (itemList.get(0) instanceof RentierMvt) {
			itemToVo(vo, (RentierMvt) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<RentierMvtVO> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<RentierMvtVO> list = new ArrayList<RentierMvtVO>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof RentierMvt) {
				RentierMvtVO vo = new RentierMvtVO();
				itemToVo(vo, (RentierMvt) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(RentierMvtVO vo, RentierMvt item, List itemList) {
		
		vo.setId(item.getId());

		vo.setAncienIPP(TypeConverter.getInstance().doubleToString(
				item.getAncienIPP()));

		vo.setNouveauIPP(TypeConverter.getInstance().doubleToString(
				item.getNouveauIPP()));

		vo.setMntAncienneRente(TypeConverter.getInstance().doubleToString(
				item.getMntAncienneRente()));

		vo.setMntNouvelleRente(TypeConverter.getInstance().doubleToString(
				item.getMntNouvelleRente()));

		vo.setMntTropMoinsPercu(TypeConverter.getInstance().doubleToString(
				item.getMntTropMoinsPercu()));

		vo.setAncienIPP(TypeConverter.getInstance().doubleToString(
				item.getAncienIPP()));
		
		vo.setMontantPercu(TypeConverter.getInstance().doubleToString(
				item.getMontantPercu()));
		
		vo.setMntQuittancesRegles(TypeConverter.getInstance().doubleToString(
				item.getMntQuittancesRegles()));
		
        vo.setAncienneReserveMathematique(TypeConverter.getInstance()
                .doubleToString(item.getAncienneReserveMathematique()));
		
		vo.setAncienEtatDossierRente(TypeConverter.getInstance().longToString(
				item.getAncienEtatDossierRente()));
		
		if (item.getRefAncienEtatRentier() != null) {
			MvtEtatRentierVO mvtEtatRentierVO = new MvtEtatRentierVO();
			mvtEtatRentierVO.setId(item.getRefAncienEtatRentier().getId());
			vo.setRefAncienEtatRentier(mvtEtatRentierVO);
		}

		if (item.getRefMouvement() != null) {
			MvtAnnulationVO mouvementVO = new MvtAnnulationVO();
			mouvementVO.setId(item.getRefMouvement().getId());
			vo.setRefMouvement(mouvementVO);
		}

		if (item.getRefRentier() != null) {
			RentierVO rentierVO = new RentierVO();
			vo.setRefRentier(rentierVO);
			
			Rentier rentier = item.getRefRentier();
	
            rentierVO.setId(TypeConverter.getInstance().longToString(
                    rentier.getId()));
				rentierVO.setLienParente(TypeConverter.getInstance().longToString(
						rentier.getLienParente()));
            rentierVO.setCapitalConstitutif(TypeConverter.getInstance()
                    .doubleToString(rentier.getCapitalConstitutif()));
            rentierVO.setIppTauxRente(TypeConverter.getInstance()
                    .doubleToString(rentier.getIppTauxRente()));
            rentierVO.setReserveMathematique(TypeConverter.getInstance()
                    .doubleToString(rentier.getReserveMathematique()));
            rentierVO.setSalaireUtile(TypeConverter.getInstance()
                    .doubleToString(rentier.getSalaireUtile()));
				rentierVO.setMontantRenteTrimestrielle(TypeConverter.getInstance()
						.doubleToString(rentier.getMontantRenteTrimestrielle()));
				rentierVO.setTuteur(TypeConverter.getInstance().booleanToString(
						rentier.getTuteur()));
				rentierVO.setCivilite(rentier.getCivilite());
				rentierVO.setNom(rentier.getNom());
				rentierVO.setPrenom(rentier.getPrenom());
				rentierVO.setSexe(rentier.getSexe());
				if (rentier.getRefTuteur() != null) {
				
					TuteurVO tuteurVo = new TuteurVO();
                tuteurVo.setId(TypeConverter.getInstance().longToString(
                        rentier.getRefTuteur().getId()));
					tuteurVo.setNom(rentier.getRefTuteur().getNom());
					tuteurVo.setPrenom(rentier.getRefTuteur().getPrenom());
					rentierVO.setRefTuteur(tuteurVo);
				}
	
		}

	}

	public void voToItem(RentierMvtVO vo, RentierMvt item) {

		if (vo != null) {
			item.setId(vo.getId());
			if (vo.getNouveauIPP() != null) {
                item.setNouveauIPP(TypeConverter.getInstance().stringToDouble(
                        vo.getNouveauIPP()));
			}
			if (vo.getAncienIPP() != null) {
                item.setAncienIPP(TypeConverter.getInstance().stringToDouble(
                        vo.getAncienIPP()));
			}
			if (vo.getMntAncienneRente() != null) {
				item.setMntAncienneRente(TypeConverter.getInstance()
						.stringToDouble(vo.getMntAncienneRente()));
			}
			if (vo.getMntNouvelleRente() != null) {
				item.setMntNouvelleRente(TypeConverter.getInstance()
						.stringToDouble(vo.getMntNouvelleRente()));
			}
			if (vo.getMntTropMoinsPercu() != null) {
				item.setMntTropMoinsPercu(TypeConverter.getInstance()
						.stringToDouble(vo.getMntTropMoinsPercu()));
			}
			if (vo.getMontantPercu() != null) {
				item.setMontantPercu(TypeConverter.getInstance()
						.stringToDouble(vo.getMontantPercu()));
			}
			if (vo.getMntQuittancesRegles() != null) {
				item.setMntQuittancesRegles(TypeConverter.getInstance()
						.stringToDouble(vo.getMntQuittancesRegles()));
			}
			
			if (vo.getRefMouvement() != null) {
				MvtAnnulation mouvement = new MvtAnnulation();
				mouvement.setId(vo.getRefMouvement().getId());
				item.setRefMouvement(mouvement);
			}
			if (vo.getRefRentier() != null) {
				Rentier rentier = new Rentier();
                new RentierVOConverter().voToItem(vo.getRefRentier(), rentier);
				item.setRefRentier(rentier);
			}
			
			if (vo.getRefAncienEtatRentier() != null) {
				MvtEtatRentier mvtEtatRentier = new MvtEtatRentier();
				new MvtEtatRentierVOConverter().voToItem(
						vo.getRefAncienEtatRentier(), mvtEtatRentier);
				item.setRefAncienEtatRentier(mvtEtatRentier);
			}
			
			if (vo.getAncienneReserveMathematique() != null) {
				item.setAncienneReserveMathematique(TypeConverter.getInstance()
						.stringToDouble(vo.getAncienneReserveMathematique()));
			}
			
			if (vo.getAncienEtatDossierRente() != null) {
				item.setAncienEtatDossierRente(TypeConverter.getInstance()
						.stringToLong(vo.getAncienEtatDossierRente()));
			}

		}

	}

	protected void doValider(RentierMvtVO vo) throws ValidationException {

	}

public void convertMvtAnnule(MouvementVO vo, Mouvement item){
		
		if (item != null) {
		if(item.getDatEtat()!=null) {
                vo.setDatEtat(TypeConverter.getInstance().calendarToString(
                        item.getDatEtat()));
		}
		if(item.getOperateur()!=null) {
			vo.setOperateur(item.getOperateur());
		}
		if(item.getEcheanceEffective()!=null) {
                vo.setEcheanceEffective(TypeConverter.getInstance()
                        .calendarToString(item.getEcheanceEffective()));
		}
		vo.setId(item.getId());
		if(item.getRefTypeMouvement()!=null) {
                vo.setRefTypeMouvement(TypeConverter.getInstance()
                        .longToString(item.getRefTypeMouvement().getId()));
		}
		if(item.getRefTypeMouvement()!=null) {
                vo.setRefTypeMouvementLabel(item.getRefTypeMouvement()
                        .getLibelle());
		}
		
		}
	}

}
