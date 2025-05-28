package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.missionnement.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinResultatOffre;
import eai.devass.sinistreat.appli.modele.parametrage.MotifOffre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.AyantDroitOffreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MotifOffreVO;

public class ValiderOffreAvantCreationUC extends BaseUC {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public long RESULTAT_ACCEPTER = 1L;
	public long OFFRE_CREER = 1L;
	public long OFFRE_VALIDE = 2L;

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		OffreVO offre = (OffreVO) this.getItem(OffreVO.class);
		ConciliationVO c = offre.getRefConciliation();
		boolean bloquer = false;
		List<String> message = new ArrayList<String>();

		/*
		 * RG 4.1.9 si la date de l'offre est inférieur a la date conciliation
		 * le SI affiche un message d'information avant l'ajout de l'offre
		 */
		if (offre.getDateOffre() != null && c.getDateEtat() != null) {
			Date dateOffre = dateFormat.parse(offre.getDateOffre());
			Date dateConciliation = dateFormat.parse(c.getDateEtat());
			if (dateOffre.before(dateConciliation)) {
				message.add("Date offre est inférieur à la date conciliation");
				bloquer = false;
			}
			/*
			 * RG 4.1.10 si la date signature PV sont inférieurs à la date
			 * conciliation ou la date offre, le SI affiche un message
			 * d'information avant l'ajout de l'offre
			 */

			if (offre.getListAyantDroit() != null
					&& !offre.getListAyantDroit().isEmpty()) {
				for (int i = 0; i < offre.getListAyantDroit().size(); i++) {
					AyantDroitOffreVO ayD = offre.getListAyantDroit().get(i);
					if (ayD.getDateSignaturePv() != null && ayD.getChoix().equals(true)) {
						Date dateSgnPV = dateFormat.parse(ayD
								.getDateSignaturePv());
						if (dateSgnPV.before(dateConciliation) || dateSgnPV.before(dateOffre)) {
							message.add("Date signature PV pour l'ayant droit "
									+ ayD.getNom()
									+ " est inférieur à la date Conciliation et/ou date Offre");
							bloquer = false;
							break;
						}
					}
				}

			}
		} else {
			throw new FonctionnelleException(
					"Erreur: la date offre ou la date conciliation est vide");
		}
		/*
		 * RG 4.1.7 la creation d'une deuxième offre n'est permise que si la
		 * première est cloturée avec un resultat négative ou l'offre est
		 * rejeter par le responsable lors de la validation
		 */

		// pour le cas d'une modification
		// dans le cas deces il faut autoriser la creation d'une deuxième offre
		if (offre.getId() == 0) {
			if (offre.getListAyantDroit() == null) {
				List<Offre> listOffre = (List<Offre>) conciliationManager
						.getOffresByIdConciliation(c);

				if (listOffre != null && listOffre.size() != 0) {
					for (int i = 0; i < listOffre.size(); i++) {
						Offre o = (Offre) listOffre.get(i);
						if (isOfrreCreer(o)) {
							message.add(OFFRE_CREER_EN_COURS);
							bloquer = true;
							break;
						}
						if (isOfrreValider(o)) {
							SinResultatOffre resultat = o.getRefResultatOffre();
							if (resultat != null
									&& resultat.getRefEtatResultat() != null
									&& resultat.getRefEtatResultat().getId()
											.equals(RESULTAT_ACCEPTER)) {
								message.add(OFFRE_VALIDE_EXISTE);
								bloquer = true;
								break;
							}
						}
					}

				}
			}
		}
		this.addResultItem(message);
		this.addResultItem(bloquer);
	}

	/**
	 * Verifier s'il y a une offre valide avec resultat accepter
	 * 
	 * @param offre
	 * @return
	 * @throws FonctionnelleException
	 */
	private boolean isOfrreValider(Offre o) throws FonctionnelleException {
		boolean trouver = false;
		MotifOffre motif = o.getRefMotifOffre();
		if (motif == null) {
			throw new FonctionnelleException("Motif Offre est obligatoire");
		}
		Long cod = motif.getId();
		if (cod.equals(OFFRE_VALIDE)) {
			trouver = true;
		}
		return trouver;

	}

	/**
	 * Verifier s'il y a une offre créer
	 * 
	 * @param offre
	 * @return
	 * @throws FonctionnelleException
	 */
	private boolean isOfrreCreer(Offre o) throws FonctionnelleException {
		boolean trouver = false;
		MotifOffre motif = o.getRefMotifOffre();
		if (motif == null) {
			throw new FonctionnelleException("Motif Offre est obligatoire");
		}
		Long cod = motif.getId();
		if (cod.equals(OFFRE_CREER)) {
			trouver = true;
		}
		return trouver;

	}
}
