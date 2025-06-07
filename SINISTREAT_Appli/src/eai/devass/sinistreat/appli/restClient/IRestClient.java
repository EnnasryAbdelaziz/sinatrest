package eai.devass.sinistreat.appli.restClient;

import java.util.List;

import com.eai.org.accesseur.valueObject.ConsultationVO;
import com.eai.org.accesseur.valueObject.PointVenteVO;

import eai.devass.missionnement.valueobjects.parametrage.PrestataireVO;
import eai.devass.sinistreat.appli.modele.parametrage.Prestataire;

public interface IRestClient {

    boolean ouvrirDossier(DossierKappa dossierKappa);

    boolean cloturerDossier(ClotureDossierKappa clotureDossierKappa);

    boolean envoyerDocument(final DocumentKappa documentKappa);

    String recupererStatutProcedure(StatutProcedureDossierKappa statutProcedureDossierKappa);

    boolean majStatutProcedure(ProcedureKappa procedureKappa);

    boolean reglementTransactionnel(ReglementTransactionnelKappa reglementTransactionnelKappa);

    boolean serviceVictime(VictimeKappa victimeKappa);

    boolean servicePositionnement(PositionnementKappa positionnementKappa);

    boolean serviceHonoraire(HonoraireKappa honoraireKappa);

    String getValeurParametrage(final String nomParametrage);
    
    List rechercherPestataire(PrestataireVO prestataireVO);
    
    PointVenteVO ConsulterOrgService(ConsultationVO consultationVO ) ;

}
