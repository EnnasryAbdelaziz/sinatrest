package eai.devass.gsr.appli.manager.metier.transverse;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.CoefficientAge;

public class CalculRente {
	private IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	
	private static final int DEUX = 2;
	private static final int TROIS = 3;
	private static final int DIX = 10;
	private static final int QUINZE = 15;
	private static final int VINGT = 20;
	private static final int TRENTE = 30;
	private static final int CINQUANTE = 50;
	private static final int SOIXANTE = 60;
	private static final int QUATRE_VINGT_CINQ = 85;
	private DateFormat dateFormaty = new SimpleDateFormat("yyyyMMdd");
	protected static final Logger logger = Logger.getLogger("loggerGSR");

	/**
	 * Calcul du taux de rente pour la liste des rentiers.
	 * 
	 * @param listRentier
	 */
	public void calculTauxRente(List<Rentier> listRentier) throws Exception {
		
		int nbrAsc = 0;
		int nbrCjn = 0;
		int nbrDsc = 0;
		Double totTauxAsc = 0D;
		Double totTauxCjn = 0D;
		Double totTauxDsc = 0D;
		int age = 0;
		Long code = null;
		
		for (Rentier rentier : listRentier) {
			age = rentier.getAgeRentier();
			code = rentier.getLienParente();
			if(code == null) {
				throw new Exception("Impossible de récuperer le lien de parenté du rentier : " 
						+ rentier);
			}

			// Ascendants
			if (code > 0 && code <= 9) {
				nbrAsc++;
				if (nbrAsc >= TROIS) {
					totTauxAsc = Double.valueOf(TRENTE);
					
				} else {
					totTauxAsc += Double.valueOf(DIX);
				}
			} 
			// Conjoint
			else if (code >=10 && code <= 19) {
				nbrCjn++;
				if (totTauxCjn != CINQUANTE) {
					if (age < SOIXANTE) {
						totTauxCjn = Double.valueOf(TRENTE);
						
					} else {
						totTauxCjn = Double.valueOf(CINQUANTE);
					}
				}
			} 
			// Descendant
			else if (code >=20 && code <= 29) {
				nbrDsc++;
				if (rentier.getOrphelinPur().equals(true)) {
					totTauxDsc += VINGT;
					
				} else if (nbrDsc < TROIS) {
					totTauxDsc += QUINZE;
					
				} else {
					totTauxDsc += DIX;
				}
			}
			
			else {
				throw new Exception("Le lien de parenté " + code + " du rentier : " 
						+ rentier + " est non définit !!");
			}
			
		}
		
		Double totTaux = totTauxAsc + totTauxCjn + totTauxDsc;
		Double coefTotTaux = 0D;
		if (totTaux <= QUATRE_VINGT_CINQ) {
			coefTotTaux = Double.valueOf(1);
		} else {
			coefTotTaux = QUATRE_VINGT_CINQ / totTaux;
		}

		Double tauxRente = 0D;
		for (Rentier rentier : listRentier) {			
			code = rentier.getLienParente();
			
			// Ascendants
			if (code <= 9) {
				tauxRente = (totTauxAsc / nbrAsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				rentier.setIppTauxRente(tauxRente);
				
			} 
			
			// Conjoint
			else if (code >=10 && code <= 19) {
				tauxRente = (totTauxCjn / nbrCjn) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				rentier.setIppTauxRente(tauxRente);
				
			} 
			
			// Descendant
			else if (code >=20 && code <= 29) {
				tauxRente = (totTauxDsc / nbrDsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				rentier.setIppTauxRente(tauxRente);
			}
		}
	}

	/**
	 * Calcul la rente trimestrielle pour la liste de rentier
	 * 
	 * @param listRentier
	 * @throws FonctionnelleException
	 */
	public void calculRenteTrimestrielle(List<Rentier> listRentier)
			throws FonctionnelleException {
		
		if(listRentier == null || listRentier.isEmpty()) {
			return;
		}
		
		Rentier firstRentier = listRentier.get(0);
		Sinistre sinistre = firstRentier.getRefDossierRente().getRefSinistre();
		Date dateAccident = null;
		Long classe = null;
		Double ipp = 0D;
		double ippReduit = 0;
		
		if(sinistre.getRefEvenement() != null) {
			dateAccident = sinistre.getRefEvenement().getDateAccident();
		}
		
		// Liste des ayants droits
		if(sinistre.getRefVictime() == null) {
			throw new FonctionnelleException("La vitime ne peut être null pour le sinistre : " 
					+ sinistre.getNumeroSinistre());
		}
		
		// EVO : 05112014 : calculer à partie du SU du rentier
		Double su = 0D;
		Date dateCompare = null;
		Date dateCompare1 = null;
		
		for (Rentier r : listRentier) {
			
			try {
				dateCompare = new SimpleDateFormat("dd/MM/yyyy").parse("19/11/2002");
				dateCompare1 = new SimpleDateFormat("dd/MM/yyyy").parse("18/06/2003");
				classe = r.getLienParente();
				ipp = r.getIppTauxRente();
				
				// Début Calcul IPP réduit
				if (classe == 0) {
					if (dateAccident == null
							|| dateCompare.compareTo(dateAccident) >= 0) {
						if (ipp > 50) {
							ippReduit = ((ipp * 1.5) - 50);
						} else {
							ippReduit = ipp / 2;
						}
					} else {
						if (dateCompare.compareTo(dateAccident) <= 0
								&& dateCompare1.compareTo(dateAccident) >= 0) {
							ippReduit = ipp;
						} else {
							if (dateCompare.compareTo(dateAccident) < 0) {
								if (ipp < 30) {
									ippReduit = ipp / 2;
								} else {
									if (ipp >= 30 && ipp <= 50) {
										ippReduit = ((ipp * 1.5) - 30);
									} else {
										if (ipp > 50) {
											ippReduit = ipp - 5;
										}
									}
								}
							}
						}
					}
				} else {
					ippReduit = ipp;
				}
				// Fin Calcul IPP réduit
				// Début Calcul Rente trimestrielle
				su = (r.getSalaireUtile() == null) ? 0D : r.getSalaireUtile();
				Double rt = (su * (ippReduit / 100)) / 4;
				rt = new BigDecimal(rt).setScale(2, BigDecimal.ROUND_HALF_EVEN)
						.doubleValue();
				r.setMontantRenteTrimestrielle(rt);
				// Fin Calcul Rente trimestrielle
				
			} catch (ParseException e) {
				logger.error(IMessageException.EXP_STAND_MESS, e);
				throw new FonctionnelleException(
						IMessageException.EXP_STAND_MESS);
			}
		}
	}

	/**
	 * Calcul de la reserve grave pour la liste des rentiers
	 * 
	 * @param listRentier
	 * @throws FonctionnelleException
	 */
	public void calculReserveGraveRentier(List<Rentier> listRentier)
			throws FonctionnelleException {
		for (int k = 0; k < listRentier.size(); k++) {
			Rentier rentier = listRentier.get(k);
			Double coefAge = getCoefAgeRentier(rentier);
			Double rente = rentier.getMontantRenteTrimestrielle();
			int v = 4;
			Double reserve = rente * v * coefAge;
			reserve = new BigDecimal(reserve).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			rentier.setCapitalConstitutif(reserve);
		}
	}

	/**
	 * Calcul du coefficient d'age
	 * 
	 * @param rentier
	 * @return Double coefficient d'age
	 * @throws FonctionnelleException
	 * @throws Exception
	 */
	private Double getCoefAgeRentier(Rentier rentier)
			throws FonctionnelleException {
		if (rentier == null) {
			return Double.valueOf(0);
		}
		CoefficientAge cofage = getCoefParRentier(rentier);
		if (cofage != null) {
			return cofage.getCoefficient();
		} else {
			return Double.valueOf(19.691);
		}
	}

	/**
	 * Calcul du coefficient d'age
	 * 
	 * @param rentier
	 * @return CoefficientAge
	 * @throws FonctionnelleException
	 */
	private CoefficientAge getCoefParRentier(Rentier rentier)
			throws FonctionnelleException {

		CoefficientAge cofAge = null;
		if (rentier == null || rentier.getRefDossierRente() == null
				|| rentier.getRefDossierRente().getRefSinistre() == null) {
			return null;
		}
		if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement() == null) {
			return null;
		}
		if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement()
				.getDateAccident() == null) {
			return null;
		}
		long codeDegre = 1;
		try {
			codeDegre = rentier.getLienParente();
		} catch (Exception e) {
		    logger.error("Problème technique", e);
			codeDegre = 1;
		}
		String code = "2";
		try {
			// La date repère du calcul de la reserve grave est 02/12/2010 
			if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement()
					.getDateAccident().before(dateFormaty.parse("20101202"))) {

				if (codeDegre == 0) {
					code = "2";
				} else if (codeDegre >= 1 && codeDegre < 14) {
					code = "2";
				} else if (codeDegre >= 20) {
					code = "4";
				}
			} else if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement()
					.getDateAccident().compareTo(dateFormaty.parse("20101202"))>=0 
					&& rentier.getRefDossierRente().getRefSinistre().getRefEvenement()
					.getDateAccident().compareTo(dateFormaty.parse("20150122"))<0){
				if (codeDegre == 0) {
					code = "2";
				} else if (codeDegre >= 1 && codeDegre <10) {
					code = "2";
				} else if (codeDegre >= 10 && codeDegre < 14) {
					code = "5";
				} else if (codeDegre >= 20) {
					code = "4";// ou 6
				}
			}else if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement()
					.getDateAccident().compareTo(dateFormaty.parse("20150122"))>=0){
				if (codeDegre >= 0 && codeDegre < 10) {
					code = "7";
				} else if ((codeDegre >= 10 && codeDegre < 14) || (codeDegre >= 60 && codeDegre < 70)) {
					code = "8";
				} else if (codeDegre >= 70) {
					code = "9";
				} else if (codeDegre >= 20 && codeDegre < 60) {
					if (rentier.getRefSituationRentier().getId()==3) {
						code = "11";
					}else{
						code = "10";
					}
				} 
				
			}
			cofAge = getCoefParType(getAge(rentier), code);
			return cofAge;	
			
		} catch (ParseException e) {
			logger.error(IMessageException.EXP_STAND_MESS, e);
			throw new FonctionnelleException(IMessageException.EXP_STAND_MESS);
		}
	}

	public int getAge(Rentier rentier) {
		int age = 0;
		if (rentier.getDateNaissance() != null
				&& rentier.getDateConstitution() != null) {
//			Date dateNaissance = (rentier.getDateNaissance()).getTime();
//			Calendar naissance = Calendar.getInstance();
//			naissance.setTime(dateNaissance);
//
//			Date dateConstitution = (rentier.getDateConstitution()).getTime();
//			Calendar constitution = Calendar.getInstance();
//			constitution.setTime(dateConstitution);
//
//			// Calendar today = Calendar.getInstance();
//
//			age = constitution.get(Calendar.YEAR)
//					- naissance.get(Calendar.YEAR);
//			constitution.add(Calendar.YEAR, -age);
//			if (naissance.after(constitution)) {
//				age = age - 1;
//			}
//			
			//Evol 01/10/2020: Arrondir l'age
			int timeDebut = (int) (((rentier.getDateNaissance()).getTime()).getTime()/ (24 * 60 * 60 * 1000));
			int timeFin = (int) (((rentier.getDateConstitution()).getTime()).getTime()/ (24 * 60 * 60 * 1000));
			age = (int) Math.round((timeFin - timeDebut)/365d);
		}

		return (age >= 0) ? age : 0;
	}

	private CoefficientAge getCoefParType(Integer age, String type)
			throws FonctionnelleException {

		Session sessionH;
		try {
			sessionH = (Session) dao.getSession();
			if (age == null || type == null) {
				throw new FonctionnelleException(
						IMessageException.EXP_OBJECT_ENTREE);
			}
			String sql = " from CoefficientAge where type='" + type
					+ "' and  age=" + age + "";
			Query q = sessionH.createQuery(sql);
			if (!q.list().isEmpty()) {
				return (CoefficientAge) q.list().get(0);
			} else {
				logger.error(IMessageException.EXP_COEF_AGE_NON_TROUVE);
				throw new FonctionnelleException(
						IMessageException.EXP_STAND_MESS);
			}

		} catch (PersistenceException e) {
			logger.error(IMessageException.EXP_STAND_MESS, e);
			throw new FonctionnelleException(IMessageException.EXP_STAND_MESS);
		}
	}
	
	/**
	 * Evo Réglementaire 29/06/2015
	 * @throws Exception 
	 */
	public void calculTauxRenteR(List<Rentier> listRentier) throws Exception {
		
		int nbrAsc = 0;
		int nbrCjn = 0;
		int nbrDsc = 0;
		int nbrDscOr = 0;
		int nbrDivo = 0;
		
		Double totTauxAsc = Double.valueOf(0);
		Double totTauxCjn = Double.valueOf(0);
		Double totTauxDsc = Double.valueOf(0);
		Double totTauxDivo = Double.valueOf(0);
		Long code = null;

		for (Rentier rentier : listRentier) {
			code = rentier.getLienParente();
			if(code == null) {
				throw new Exception("Impossible de récuperer le lien de parenté du rentier : " 
						+ rentier);
			}

			//Ascendant et Kafel
			if ((code >= 1 && code < 10) || (code >= 70)) {
				nbrAsc++;
				if (nbrAsc >= DEUX) {
					totTauxAsc = Double.valueOf(TRENTE);
				} else {
					totTauxAsc += Double.valueOf(QUINZE);
				}
			} else if (code >=10 && code <14) {
			    // Conjoint
				nbrCjn++;
				totTauxCjn = Double.valueOf(CINQUANTE);
				
			} else if (code >=60 && code <70) {
			    // Divorcé
				nbrDivo++;
				totTauxDivo = Double.valueOf(VINGT);
				
			} else if (code >= 40 && code <60) {
			    //Descendant OrphelinPur
				nbrDscOr++;
				if (rentier.getOrphelinPur()) {
					totTauxDsc += Double.valueOf(TRENTE);
				} 
			} else if (code >= 20 && code <40) {
			    // Descendant
				nbrDsc++;
				if (nbrDsc < DEUX) {
					totTauxDsc = Double.valueOf(VINGT);
				} else {
					totTauxDsc += Double.valueOf(DIX);
				}
			}
			else {
				throw new Exception("Le lien de parenté " + code + " du rentier : " 
						+ rentier + " est non définit !!");
			}
		}	
		
		Double totTaux = totTauxAsc + totTauxCjn + totTauxDsc + totTauxDivo;
		Double coefTotTaux = Double.valueOf(1);
		if (totTaux > QUATRE_VINGT_CINQ) {
			coefTotTaux = QUATRE_VINGT_CINQ / totTaux;
		}
		
		Double tauxRente = 0D;
		for (Rentier rentier : listRentier) {			
			code = rentier.getLienParente();
			
			if ((code >= 1 && code < 10) || (code >= 70)) {
				tauxRente = (totTauxAsc / nbrAsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} else if (code >=10 && code <14) {
				tauxRente = (totTauxCjn / nbrCjn) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} else if (code >=60 && code <70) {
				tauxRente = (totTauxDivo / nbrDivo) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			}else if (code >= 20 && code <60) {
				tauxRente = (totTauxDsc /(nbrDsc+nbrDscOr)) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			}
			rentier.setIppTauxRente(tauxRente);
		}
	}
	
	

}
