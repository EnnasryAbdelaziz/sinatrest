package eai.devass.sinistreat.appli.manager;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.CoefficientAge;
import eai.devass.sinistreat.appli.modele.parametrage.Palier;
import eai.devass.sinistreat.appli.utils.entites.IParam;

public class RegleGestionManager {
	private Logger logger = Logger.getLogger("loggerSINAT");
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
			.getService(ParametrageManager.class);

	public Double calculSalaireAnnuel(Sinistre sin) {
		Victime vic = sin.getRefVictime();
		if (vic == null) {
			return Double.valueOf(0);
		}
		Double salaireHoraire = vic.getSalaireHoraire();
		Double salaireJournalier = vic.getSalaireJournalier();
		Double salaireMensuel = vic.getSalaireMensuel();
		Double salaireAnnuel = vic.getSalaireAnnuel();
		if (salaireAnnuel != 0) {
			return salaireAnnuel;
		} else if (salaireMensuel != 0) {
			return getSalaireAnnuelByMonth(salaireMensuel);
		} else if (salaireJournalier != 0) {
			return getSalaireAnnuelByjour(salaireJournalier);
		} else if (salaireHoraire != 0) {
			return getSalaireAnnuelByheur(salaireHoraire);
		} else {
			return Double.valueOf(0);
		}
	}

	public Double calculSalaireUtile(Sinistre sin) throws Exception {
		if (sin == null) {
			return Double.valueOf(0);
		}
		Victime vic = sin.getRefVictime();
		if (vic == null) {
			return Double.valueOf(0);
		}

		Double salaireAnnuel = vic.getSalaireAnnuel();
		Double salaireUtile = vic.getSalaireUtile();
		Double salaireAnnuelReel;

		Double palier1 = Double.valueOf(0);
		Double palier2 = Double.valueOf(0);
		Double salaireMin = Double.valueOf(0);

		try {
			Palier palier = parametrageManager.getPalier(sin.getRefEvenement()
					.getDateAccident());
			if (palier != null) {
				palier1 = palier.getPalier1();
				palier2 = palier.getPalier2();
				salaireMin = palier.getSalaireMinLeg();

			} else {
				// palier1=Double.valueOf(121600.62);
				// palier2=Double.valueOf(486402.48);
				// salaireMin=Double.valueOf(28005.12);
				palier1 = Double.valueOf(95909);
				palier2 = Double.valueOf(383637);
				salaireMin = Double.valueOf(22102.0);
			}
		}

		catch (Exception e) {
			logger.error("problème technique",e);
			// palier1=Double.valueOf(121600.62);
			// palier2=Double.valueOf(486402.48);
			// salaireMin=Double.valueOf(28005.12);
			palier1 = Double.valueOf(95909);
			palier2 = Double.valueOf(383637);
			salaireMin = Double.valueOf(22102.0);
		}

		// END
		if (salaireAnnuel < salaireMin) {
			salaireAnnuelReel = salaireMin;
		} else {
			salaireAnnuelReel = salaireAnnuel;
		}

		if (salaireAnnuelReel <= palier1) {
			salaireUtile = salaireAnnuelReel;
		} else if (palier1 < salaireAnnuelReel && salaireAnnuelReel <= palier2) {
			salaireUtile = getSalaireAnnuelUtilByfirstPalier(salaireAnnuelReel,
					palier1);
		} else if (palier2 < salaireAnnuelReel) {
			salaireUtile = getSalaireAnnuelUtilBysecondPalier(
					salaireAnnuelReel, palier1, palier2);
		} else {
			salaireUtile = salaireAnnuelReel;
		}

		return salaireUtile;
	}

	public Double getCoefAge(Sinistre sin) throws Exception {
		if (sin == null || sin.getRefVictime() == null) {
			return Double.valueOf(0);
		}

		CoefficientAge cofage = parametrageManager.getCoefParSin(sin);
		if (cofage != null) {
			return cofage.getCoefficient();
		} else {
			return Double.valueOf(20.75);
		}
	}

	public Double getCoefAgeProthese(Sinistre sin) throws Exception {
		Date date = null;
		if (sin == null || sin.getRefVictime() == null) {
			return Double.valueOf(0);
		}

		// CoefficientAge cofage= parametrageManager.getCoefParSin(sin);
		if (sin.getRefEvenement().getDateAccident() == null) {
			return null;
		}
		if (sin.getDateCalculReserve() != null) {
			date = sin.getDateCalculReserve();
		} else {
			date = new Date();
		}
		CoefficientAge cofage = parametrageManager.getCoefParType(sin
				.getRefVictime().getAge(date), "2");

		if (cofage != null) {
			return cofage.getCoefficient();
		} else {
			return Double.valueOf(20.75);
		}

	}

	public Double getCoefAgeAY(AyantDroit ay, Sinistre sin) throws Exception {
		if (sin == null) {
			return Double.valueOf(0);
		}
		if (ay == null) {
			return Double.valueOf(0);
		}

		CoefficientAge cofage = parametrageManager.getCoefParAY(sin, ay);
		if (cofage != null) {
			return cofage.getCoefficient();
		} else {
			return Double.valueOf(19.691);
		}
	}

	// salaire
	public Double getSalaireAnnuelByjour(Double salairejournalier) {
		if (salairejournalier <= 0) {
			return Double.valueOf(0);
		} else {
			return salairejournalier * 300;
		}
	}

	public Double getSalaireAnnuelByheur(Double salairehoraire) {
		if (salairehoraire <= 0) {
			return Double.valueOf(0);
		} else {
			return salairehoraire * 8 * 300;
		}
	}

	public Double getSalaireAnnuelByMonth(Double salairemensuel) {
		if (salairemensuel <= 0) {
			return Double.valueOf(0);
		} else {
			return salairemensuel * 12;
		}
	}

	public Double getSalaireJournalierByhour(Double salairehoraire) {
		if (salairehoraire <= 0) {
			return Double.valueOf(0);
		} else {
			return salairehoraire * 8;
		}
	}

	public Double getSalaireJournalierByYear(Double salaireannuel) {
		if (salaireannuel <= 0) {
			return Double.valueOf(0);
		} else {
			return salaireannuel / 300;
		}
	}

	public Double getSalaireJournalierByMonth(Double salairemensuel) {
		if (salairemensuel <= 0) {
			return Double.valueOf(0);
		} else {
			return salairemensuel / 24;
		}
	}

	public Double getSalaireHoraireByDay(Double salairejournalier) {
		if (salairejournalier <= 0) {
			return Double.valueOf(0);
		} else {
			return salairejournalier / 8;
		}
	}

	public Double getSalaireAnnuelUtilByfirstPalier(Double salaireAnnuelReel,
			Double premierPalier) {
		Double salaireUtile = Double.valueOf(0);
		if (salaireAnnuelReel <= 0) {
			return salaireUtile;
		} else {
			salaireUtile = (salaireAnnuelReel - premierPalier) / 3
					+ premierPalier;
		}

		return (salaireUtile > 0) ? salaireUtile : Double.valueOf(0);
	}

	public Double getSalaireAnnuelUtilBysecondPalier(Double salaireAnnuelReel,
			Double premierPalier, Double secondPalier) {
		Double salaireUtile = Double.valueOf(0);
		if (salaireAnnuelReel <= 0) {
			return salaireUtile;
		} else {
			salaireUtile = (salaireAnnuelReel - secondPalier) / 8 + 2
					* premierPalier;
		}

		return (salaireUtile > 0) ? salaireUtile : Double.valueOf(0);
	}

	// Arrérages de rente

	public Calendar getDateDepartArrerage(Calendar dateGuerison) {
		dateGuerison.add(Calendar.DAY_OF_MONTH, 1);
		return dateGuerison;
	}

	public Double getRenteTrimestrielle(Double renteAnnuelle) {
		return renteAnnuelle / 4;
	}

	public Double getRenteMensuelle(Double renteTrimestrielle) {
		return renteTrimestrielle / 3;
	}

	public Double getRenteJournalieree(Double renteAnnuelle) {
		return renteAnnuelle / 360;
	}

	// réserves
	public Double getReserveOrdinaire(Sinistre sinistre) throws Exception {
		Double reserveOrd = Double.valueOf(0);
		if (sinistre != null) {
			Victime victime = sinistre.getRefVictime();
			if (victime == null) {
				return reserveOrd;
			}
			Boolean deces = victime.getDeces();
			if (deces == false) {
				String codeZone = "1";
				Double ipp = sinistre.getIpp();
				if (ipp.compareTo(Double.valueOf(0)) == 0) {
					reserveOrd = Double.valueOf(2300);
				} else {
					if (sinistre.getRefEvenement() != null
							&& sinistre.getRefEvenement().getRefZone() != null
							&& !StringUtils.isEmpty(sinistre.getRefEvenement()
									.getRefZone().getCode())) {
						codeZone = sinistre.getRefEvenement().getRefZone()
								.getCode();
					}
					reserveOrd = parametrageManager.getReserveOrdByZoneSQL(
							codeZone, ipp);
				}
			} else if (deces == true) {
				reserveOrd = Double.valueOf(10000);
			} else {
				reserveOrd = Double.valueOf(0);
			}
		}
		return reserveOrd;
	}

	public Double getReserveOrdinaireByITT(Sinistre sinistre) {
		Victime victime = sinistre.getRefVictime();
		if (victime == null) {
			return Double.valueOf(0);
		}

		Long itt = sinistre.getItt();
		Double salairejour = victime.getSalaireJournalier();

		return itt * salairejour;
	}

	public Double getReserveGrave(Sinistre sinistre) throws Exception {

		Victime victime = sinistre.getRefVictime();
		if (victime == null) {
			return Double.valueOf(0);
		}
		Double salaireUtile = victime.getSalaireUtile();
		Double ippReduit = getTauxIppReduit(sinistre);
		Double coefAge = getCoefAge(sinistre);
		Double ippReduit100 = new BigDecimal((ippReduit / 100)).setScale(4,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();
		Double rente = salaireUtile * ippReduit100;
		rente = new BigDecimal(rente).setScale(2, BigDecimal.ROUND_HALF_EVEN)
				.doubleValue();
		Double reserve = rente * coefAge;
		reserve = new BigDecimal(reserve).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();
		//
		sinistre.setIppReduit(ippReduit);
		victime.setRente(rente);
		victime.setCoef(coefAge);
		sinistre.setReserveGrave(reserve);

		return reserve;
	}

	public Double getReserveGraveAY(AyantDroit ayant, Sinistre sin)
			throws Exception {
		Victime victime = sin.getRefVictime();
		if (victime == null) {
			return Double.valueOf(0);
		}
		if (ayant == null) {
			return Double.valueOf(0);
		}
		Double salaireUtile = victime.getSalaireUtile();
		Double ippReduit = ayant.getTauxIPP();
		Double coefAge = getCoefAgeAY(ayant, sin);
		ippReduit = new BigDecimal((ippReduit / 100)).setScale(4,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();
		Double rente = salaireUtile * ippReduit;
		rente = new BigDecimal(rente).setScale(2, BigDecimal.ROUND_HALF_EVEN)
				.doubleValue();
		Double reserve = rente * coefAge;
		reserve = new BigDecimal(reserve).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();

		ayant.setRente(rente);
		ayant.setCoef(coefAge);

		return reserve;
	}

	public Double getReserveProthese(Sinistre sinistre) throws Exception {
		if (sinistre != null && sinistre.getRefVictime() != null
				&& sinistre.getIpp() >= 10) {

			Double prixProthese = sinistre.getPrixProthese();
			Double coefAge = getCoefAgeProthese(sinistre);
			Double reserve = new BigDecimal((prixProthese * coefAge) / 2 * 1.5)
					.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
			return reserve;
		}
		return Double.valueOf(0);
	}

	public Double getCapitalRachat(Double salaireAnnuelReel,
			Double salaireAnnuelUtil, Double ipp, Double coefAge) {
		if (salaireAnnuelReel != null) {
			return salaireAnnuelReel * ipp * coefAge;
		} else {
			return salaireAnnuelUtil * ipp * coefAge;
		}
	}

	// Taux d'IPP et réduction

	public Double getTauxIppReduit(Sinistre sin) throws Exception {
		Double ippRed = Double.valueOf(0);
		if (sin == null) {
			return ippRed;
		}

		// utiliser la zone pour taux ipp min cas sinistre grave
		Double ipp = sin.getIpp();
		if (sin.getIppEstime() && sin.getIpp() != null
				&& sin.getIpp().compareTo(new Double(0)) != 0) {
			if (ipp > 0) {
				if (Boolean.valueOf(IParam.useZone)) {
					Double ippMinZone = parametrageManager
							.getIPPMinByZoneSQL(sin);
					if (ipp <= ippMinZone) {
						ipp = ippMinZone;
					}
				}
			}
		}

		String d1 = "18/11/2002";
		String d11 = "19/11/2002";
		String d2 = "18/06/2003";
		String d22 = "19/06/2003";
		Date date1 = dateFormat.parse(d1);
		Date date11 = dateFormat.parse(d11);
		Date date2 = dateFormat.parse(d2);
		Date date22 = dateFormat.parse(d22);
		try {
			if (sin.getRefEvenement().getDateAccident().before(date11)) {
				if (ipp <= 50) {
					ippRed = ipp / 2;
				} else {
					ippRed = 25 + (ipp - 50) * (1.5);
				}
			} else if (sin.getRefEvenement().getDateAccident().after(date1)
					&& sin.getRefEvenement().getDateAccident().before(date22)) {
				ippRed = ipp;

			} else if (sin.getRefEvenement().getDateAccident().after(date2)) {
				if (ipp <= 30) {
					ippRed = ipp / 2;
				}
				if (ipp > 30 && ipp <= 50) {
					ippRed = 15 + (ipp - 30) * (1.5);
				}
				if (ipp > 50 && ipp <= 100) {
					ippRed = 45 + (ipp - 50);
				}

			}
		} catch (Exception e) {
			logger.error("problème technique",e);
			return Double.valueOf(5);
		}
		return ippRed;

	}

	// Balthazard
	public Double getTauxIppReduitByBalthazard(Double capaciteTotal,
			Double ippActuel, Double ippAnterieur, Double infirmite)
			throws Exception {
		if (ippAnterieur != null) {
			return capaciteTotal - (ippAnterieur * ippActuel) / 100;
		} else {
			return capaciteTotal - (infirmite * ippActuel) / 100;
		}

	}

	public List calculTauxRente(List<AyantDroit> listAyantDroit, Sinistre sin)
			throws Exception {
		Date date = null;
		int nbrAsc = 0;
		int nbrCjn = 0;
		int nbrDsc = 0;
		Double totTauxAsc = Double.valueOf(0);
		Double totTauxCjn = Double.valueOf(0);
		Double totTauxDsc = Double.valueOf(0);
		Double coefTotTaux = Double.valueOf(1);

		if (sin.getDateCalculReserve() != null) {
			date = sin.getDateCalculReserve();
		} else {
			date = new Date();
		}
		for (int i = 0; i < listAyantDroit.size(); i++) {
			AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
			String code = "1";
			try {
				code = ay.getRefDegreParente().getCode();
			} catch (Exception e) {
				code = "1";
			}
			if (code.equals("1")) {
				nbrAsc++;
				if (nbrAsc >= 3) {
					totTauxAsc = Double.valueOf(30);
				} else {
					totTauxAsc += Double.valueOf(10);
				}
			} else if (code.equals("10")) {
				nbrCjn++;
				if (totTauxCjn != 50) {
					if (ay.getAge(date) < 60) {
						totTauxCjn = Double.valueOf(30);
					} else {
						totTauxCjn = Double.valueOf(50);
					}
				}
			} else if (code.equals("20")) {
				nbrDsc++;
				if (ay.getOrphelinPur()) {
					totTauxDsc += Double.valueOf(20);
				} else if (nbrDsc < 3) {
					totTauxDsc += Double.valueOf(15);
				} else {
					totTauxDsc += Double.valueOf(10);
				}
			}
		}
		Double totTaux = totTauxAsc + totTauxCjn + totTauxDsc;
		if (totTaux > 85) {
			coefTotTaux = 85 / totTaux;
		}
		for (int i = 0; i < listAyantDroit.size(); i++) {
			AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
			String code = "1";
			try {
				code = ay.getRefDegreParente().getCode();
			} catch (Exception e) {
				code = "1";
			}
			if (code.equals("1")) {
				Double tauxRente = (totTauxAsc / nbrAsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				ay.setTauxIPP(tauxRente);
			} else if (code.equals("10")) {
				Double tauxRente = (totTauxCjn / nbrCjn) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				ay.setTauxIPP(tauxRente);
			} else if (code.equals("20")) {
				Double tauxRente = (totTauxDsc / nbrDsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				ay.setTauxIPP(tauxRente);
			}
		}

		return listAyantDroit;
	}
	
	//Evol réglementaire 29/06/2015
	public List calculTauxRenteR(List<AyantDroit> listAyantDroit, Sinistre sin)
			throws Exception {
		//Date date = null;
		int nbrAsc = 0;
		int nbrCjn = 0;
		int nbrDsc = 0;
		int nbrDivo = 0;

		Double totTauxAsc = Double.valueOf(0);
		Double totTauxCjn = Double.valueOf(0);
		Double totTauxDsc = Double.valueOf(0);
		Double totTauxDivo = Double.valueOf(0);

		Double coefTotTaux = Double.valueOf(1);

//		if (sin.getDateCalculReserve() != null) {
//			date = sin.getDateCalculReserve();
//		} else {
//			date = new Date();
//		}
		for (int i = 0; i < listAyantDroit.size(); i++) {
			AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
			String code = "1";
			try {
				code = ay.getRefDegreParente().getCode();
			} catch (Exception e) {
				code = "1";
			}
			if (code.equals("1") || code.equals("70")) {
				nbrAsc++;
				if (nbrAsc >= 2) {
					totTauxAsc = Double.valueOf(30);
				} else {
					totTauxAsc += Double.valueOf(15);
				}
			}
			
			else if (code.equals("10")) {
				nbrCjn++;
				totTauxCjn = Double.valueOf(50);
			} else if (code.equals("60")) {
				nbrDivo++;
				totTauxDivo = Double.valueOf(20);

			} else if (code.equals("20")) {
				nbrDsc++;
				if (ay.getOrphelinPur()) {
					totTauxDsc += Double.valueOf(30);
				} else if (nbrDsc < 2) {
					totTauxDsc = Double.valueOf(20);
				} else {
					totTauxDsc += Double.valueOf(10);
				}
			}
		}
		Double totTaux = totTauxAsc + totTauxCjn + totTauxDsc + totTauxDivo;
		if (totTaux > 85) {
			coefTotTaux = 85 / totTaux;
		}
		for (int i = 0; i < listAyantDroit.size(); i++) {
			Double tauxRente=0D;
			AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
			String code = "1";
			try {
				code = ay.getRefDegreParente().getCode();
			} catch (Exception e) {
				code = "1";
			}
			if (code.equals("1") || code.equals("70")) {
				 tauxRente = (totTauxAsc / nbrAsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} else if (code.equals("10")) {
				 tauxRente = (totTauxCjn / nbrCjn) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} else if (code.equals("20")) {
				 tauxRente = (totTauxDsc / nbrDsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} else if (code.equals("60")) {
				 tauxRente = (totTauxDivo / nbrDivo) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} 
			
			ay.setTauxIPP(tauxRente);
		}

		return listAyantDroit;
	}

	//Fin evol réglementaire 29/06/2015

	public List selectAscendant(List<AyantDroit> listAyantDroit)
			throws Exception {
		List<AyantDroit> listAscendant = new ArrayList<AyantDroit>();

		for (int i = 0; i < listAyantDroit.size(); i++) {
			AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
			if (ay.getRefDegreParente().getCode() == "1") {
				listAscendant.add(ay);
			}

		}
		return listAscendant;

	}

	public void calculTauxAscendant(List<AyantDroit> listAyantDroit)
			throws Exception {
		List<AyantDroit> listAscendant = selectAscendant(listAyantDroit);
		Double tauxAsc = Double.valueOf(10);
		int nbrAsc = listAscendant.size();
		if (nbrAsc > 3) {
			tauxAsc = 30 / Double.valueOf(nbrAsc);
		}
		for (int i = 0; i < nbrAsc; i++) {
			AyantDroit ay = (AyantDroit) listAscendant.get(i);
			ay.setTauxIPP(tauxAsc);
		}
	}

	public List selectConjoint(List<AyantDroit> listAyantDroit)
			throws Exception {
		List<AyantDroit> listConjoint = new ArrayList<AyantDroit>();

		for (int i = 0; i < listAyantDroit.size(); i++) {
			AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
			if (ay.getRefDegreParente().getCode() == "2") {
				listConjoint.add(ay);
			}

		}
		return listConjoint;

	}

	public void calculTauxConjoint(List<AyantDroit> listAyantDroit)
			throws Exception {
		List<AyantDroit> listConjoint = selectConjoint(listAyantDroit);

		Double tauxCjn = Double.valueOf(30);
		int nbrCjn = listConjoint.size();
		if (nbrCjn != 1) {
			tauxCjn = 30 / Double.valueOf(nbrCjn);
		}
		for (int i = 0; i < nbrCjn; i++) {
			AyantDroit ay = (AyantDroit) listConjoint.get(i);
			ay.setTauxIPP(tauxCjn);
		}

	}

	public List selectDecendant(List<AyantDroit> listAyantDroit)
			throws Exception {
		List<AyantDroit> listDecendant = new ArrayList<AyantDroit>();

		for (int i = 0; i < listAyantDroit.size(); i++) {
			AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
			if (ay.getRefDegreParente().getCode() == "3") {
				listDecendant.add(ay);
			}

		}
		return listDecendant;

	}

	public void calculTauxDescendant(List<AyantDroit> listAyantDroit)
			throws Exception {
		List<AyantDroit> listDescendant = selectDecendant(listAyantDroit);
		if (listDescendant == null || listDescendant.isEmpty()) {
			return;
		}
		int nbrDsc = listDescendant.size();
		if (nbrDsc <= 2) {
			for (int i = 0; i < nbrDsc; i++) {
				AyantDroit ay = (AyantDroit) listDescendant.get(i);
				ay.setTauxIPP(Double.valueOf(15));
				if (ay.getOrphelinPur()) {
					ay.setTauxIPP(Double.valueOf(20));
				}
			}
		} else {
			for (int i = 0; i < nbrDsc; i++) {
				AyantDroit ay = (AyantDroit) listDescendant.get(i);
				if (i == 0 || i == 1) {
					ay.setTauxIPP(Double.valueOf(15));
				} else {
					ay.setTauxIPP(Double.valueOf(10));
				}

				if (ay.getOrphelinPur()) {
					ay.setTauxIPP(Double.valueOf(20));
				}
			}
		}
	}

	public Double calculSalaireUtileOld(Sinistre sin) {
		Victime vic = sin.getRefVictime();
		if (vic == null) {
			return Double.valueOf(0);
		}
		Double salaireAnnuel = vic.getSalaireAnnuel();
		Double salaireUtile = vic.getSalaireUtile();
		Double palier1 = Double.valueOf(3000);
		Double palier2 = Double.valueOf(10000);
		if (salaireAnnuel <= palier1) {
			salaireUtile = salaireAnnuel;
		} else if (palier1 < salaireAnnuel && salaireAnnuel <= palier2) {
			salaireUtile = getSalaireAnnuelUtilByfirstPalier(salaireAnnuel,
					palier1);
		} else if (palier2 < salaireAnnuel) {
			salaireUtile = getSalaireAnnuelUtilBysecondPalier(salaireAnnuel,
					palier1, palier2);
		} else {
			salaireUtile = salaireAnnuel;
		}
		return salaireUtile;
	}

	public Double getCoefAgeOld(Sinistre sin) {
		Victime vic = sin.getRefVictime();
		Date date = null;
		if (vic == null) {
			return Double.valueOf(0);
		}
		if (sin.getRefEvenement().getDateAccident() == null) {
			return null;
		}
		if (sin.getDateCalculReserve() != null) {
			date = sin.getDateCalculReserve();
		} else {
			date = new Date();
		}
		int age = vic.getAge(date);
		if (age != 0) {
			return Double.valueOf(age);
		} else {
			return Double.valueOf(1);
		}
	}

	public Double getCoefAgeAYOld(AyantDroit ay, Sinistre sin) {
		Date date = null;
		if (sin.getDateCalculReserve() != null) {
			date = sin.getDateCalculReserve();
		} else {
			date = new Date();
		}
		if (ay == null) {
			return Double.valueOf(0);
		}
		int age = ay.getAge(date);
		if (age != 0) {
			return Double.valueOf(age);
		} else {
			return Double.valueOf(1);
		}
	}

	public void calculTauxRenteOld(List<AyantDroit> listAyantDroit)
			throws Exception {
		List<AyantDroit> listAscendant;
		List<AyantDroit> listConjoint;
		List<AyantDroit> listDecendant;

		listAscendant = selectAscendant(listAyantDroit);
		listConjoint = selectConjoint(listAyantDroit);
		listDecendant = selectDecendant(listAyantDroit);

		Double tauxAsc = Double.valueOf(10);
		int nbrAsc = listAscendant.size();
		if (nbrAsc > 3) {
			tauxAsc = 30 / Double.valueOf(nbrAsc);
		}
		for (int i = 0; i < nbrAsc; i++) {
			AyantDroit ay = (AyantDroit) listAscendant.get(i);
			ay.setTauxIPP(tauxAsc);
		}
		Double tauxCjn = Double.valueOf(30);
		int nbrCjn = listConjoint.size();
		if (nbrCjn != 1) {
			tauxCjn = 30 / Double.valueOf(nbrCjn);
		}
		for (int i = 0; i < nbrCjn; i++) {
			AyantDroit ay = (AyantDroit) listConjoint.get(i);
			ay.setTauxIPP(tauxCjn);
		}
		int nbrDsc = listDecendant.size();
		if (nbrDsc <= 2) {
			for (int i = 0; i < nbrDsc; i++) {
				AyantDroit ay = (AyantDroit) listDecendant.get(i);
				ay.setTauxIPP(Double.valueOf(15));
				if (ay.getOrphelinPur()) {
					ay.setTauxIPP(Double.valueOf(20));
				}
			}
		} else {
			for (int i = 0; i < nbrDsc; i++) {
				AyantDroit ay = (AyantDroit) listDecendant.get(i);
				if (i == 0 || i == 1) {
					ay.setTauxIPP(Double.valueOf(15));
				} else {
					ay.setTauxIPP(Double.valueOf(10));
				}

				if (ay.getOrphelinPur()) {
					ay.setTauxIPP(Double.valueOf(20));
				}
			}
		}

	}
}
