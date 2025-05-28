package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinResultatOffre;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ValiderConciliationAvanCreaUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Conciliation conciliation = (Conciliation) this
				.getItem(Conciliation.class);
		Sinistre s = conciliation.getRefSinistre();
		List listRecours = null;

		Recours recours =  new Recours();
		recours.setRefSinistre(s);
		listRecours = (List)recoursManager.rechercheProcedureEncours(recours);
		boolean bloquer = false;
		List<String> message = new ArrayList<String>();
		/* RG.4.1.2: si une conciliation existe déjà le SI alerte avant creation */
		/*
		 * && RG.4.1.3: la creation d'une deuxième conciliation n'est permise
		 * que si la première est à l'état "TERMINER ou SANS SUITE"
		 */
		
		// controle au moment de la creation 
        List<Offre> listOffre  = new ArrayList<Offre>();
        if (conciliation.getId() == 0) {
               List<Conciliation> listConciliation = (List<Conciliation>) conciliationManager
                             .getConciliationOrdByIdSinistre(s);

               if (listConciliation != null && listConciliation.size() != 0) {
            	   Conciliation consiliation = listConciliation.get(0);
            	   listOffre =  conciliationManager.getOffresByIdConciliationOrd(consiliation);
                   //listOffre =   listConciliation.get(0).getListOffre();
//                      if(listOffre.size() == 0) {
//                             message.add(CONCILIATION_EXISTE_AUCUNE_OFFRE);      
//                      } else if (listOffre.size()==1) {
//                             Offre offre = listOffre.get(0);
//                             if(offre.getRefMotifOffre().getId()==1) {
//                                   message.add(CONCILIATION_EXISTE_UNE_OFFRE_ETABLIE);
//                             } else if(offre.getRefMotifOffre().getId() ==3) {
//                                   message.add(CONCILIATION_EXISTE_UNE_OFFRE_REFUSEE);
//                             } else if(offre.getRefMotifOffre().getId() ==2) {
//                            	 message.add(CONCILIATION_EXISTE_UNE_OFFRE_ACCEPTEE);
//                             }
//                                 
            	   if(listOffre.size() == 0) {
                       message.add(CONCILIATION_EXISTE_AUCUNE_OFFRE);      
                } else if (listOffre.size()==1) {
                       Offre offre = listOffre.get(0);
                       if(offre.getRefMotifOffre().getId()==1) {
                             message.add(CONCILIATION_EXISTE_UNE_OFFRE_ETABLIE);
                       } else if(offre.getRefMotifOffre().getId() ==3) {
                             message.add(CONCILIATION_EXISTE_UNE_OFFRE_REFUSEE);
                       } else if(offre.getRefMotifOffre().getId() ==2) {
                      	 SinResultatOffre refResultatOffre = offre.getRefResultatOffre();
                      	 if(refResultatOffre != null) {
                      		 if(refResultatOffre.getRefEtatResultat().getId() != null) {
                      			 if(refResultatOffre.getRefEtatResultat().getId().equals("1")) {
                      				 message.add(CONCILIATION_EXISTE_UNE_OFFRE_ACCEPTEE);
                      			 }
                      			 if(refResultatOffre.getRefEtatResultat().getId().equals("2")) {
                      				 message.add(CONCILIATION_EXISTE_UNE_OFFRE_REFUSEE);
                      			 }
                      		 }
                      	 }else {
                      	 message.add(CONCILIATION_EXISTE_UNE_OFFRE_ETABLIE);
                      	 }
                       }
                      } else if(listOffre.size()>1) {
                             Offre lastOffre = listOffre.get(0);
                                   if(lastOffre.getRefMotifOffre().getId()==2) {
                                	   SinResultatOffre refResultatOffre = lastOffre.getRefResultatOffre();
                                	   if(refResultatOffre != null) {
                                    		 if(refResultatOffre.getRefEtatResultat().getId() != null) {
                                    			 if(refResultatOffre.getRefEtatResultat().getId().equals("1")) {
                                                     message.add("Une Conciliation existe déjà au niveau du dossier sinistre/"+Long.toString(listOffre.size())+"ème offre acceptée");
                                    			 }
                                    			 if(refResultatOffre.getRefEtatResultat().getId().equals("2")) {
                                                     message.add("Une Conciliation existe déjà au niveau du dossier sinistre/"+Long.toString(listOffre.size())+"ème offre refusée");
                                    			 }
                                    		 }
                                    	 }else {
                                      	   message.add("Une Conciliation existe déjà au niveau du dossier sinistre/"+Long.toString(listOffre.size())+"ème offre établie");
                                    	 }           	   
                                   } else if(lastOffre.getRefMotifOffre().getId()==3) {
                                          message.add("Une Conciliation existe déjà au niveau du dossier sinistre/"+Long.toString(listOffre.size())+"ème offre refusée");
                                   } else if(lastOffre.getRefMotifOffre().getId()==1) {
                                	   message.add("Une Conciliation existe déjà au niveau du dossier sinistre/"+Long.toString(listOffre.size())+"ème offre établie");
                                   }
                      }
               }
        }
        
        // ABE 10/01/2023 Affichage du message Procédure en cours dans le dossier
        if(listRecours.size() != 0) {
			message.add(EXP_RECOURS_EN_COURS);
        }
		
		// controle au moment de la creation 
		/*if (conciliation.getId() == 0) {
			List<Conciliation> listConciliation = (List<Conciliation>) conciliationManager
					.getConciliationByIdSinistre(s);

			if (listConciliation != null && listConciliation.size() != 0) {
				message.add(CONCILIATION_EXISTE);
				bloquer = false;
				for (int i = 0; i < listConciliation.size(); i++) {
					Conciliation c = (Conciliation) listConciliation.get(i);
					if (conciliationManager.isConciliationEncours(c)) {
						message.clear();
						message.add(CONCILIATION_ENCOURS_EXISTE);
						bloquer = true;
						break;
					}
				}

			}
		}*/
		this.addResultItem(message);
		this.addResultItem(bloquer);
	}

	public boolean isTransactionnal() {
		return true;
	}
}
