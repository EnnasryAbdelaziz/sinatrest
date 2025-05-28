create or replace view SIN_COMPLEMENT_QUITTANCE_VIEW as
select
    sinistre.id as id,
    reglement.numeroquittance as numero_quittance,
    substr(sinistre.numerosinistre,4,1) as type_sinistre,
    null as code_validite,
    sinistre.numerograve as numero_grave,
    sinistre.typecontrat as nature_contrat,
    null as code_activite,
    null as code_sous_activite,
    sinistre.codeclient as code_client,
    reglement.typebeneficiaire as type_beneficiaire,
    null as assure,
    null as code_operateur_redacteur,
    substr(sinistre.numerosinistre,5,4) as code_decentralisation,
    evenement.dateaccident as date_accident,
    sinistre.dateffet as date_effet_police,
    null as code_emetteur_qtc,
    null as code_beneficiaire,
    null as ref_sinistre,
    reglement.nombeneficiaire as nom_beneficiaire,
    victime.nom as nom_victime,
    evenement.dateaccident as date_sinistre,
    substr(to_char(sinistre.dateffet, 'dd/MM/yyyy'),7,4) as annee_effet_police,
    reglement.codeauxiliaire as code_auxiliaire,
    null as adresse_beneficiaire,
    null as ville_beneficiaire
from sin_sinistre sinistre, sin_reglement reglement, sin_evenement evenement,
        sin_victime victime
where sinistre.id = reglement.idsinistre
and sinistre.idevenement = evenement.id
and sinistre.idvictime = victime.id;