
//-------------------------------------------------------
//	onChange de la categorie du client : Physique/Morale
//-------------------------------------------------------

function onChangeCategorieClient() {
  if(document.forms[0].elements['categorieClient'].value == '') return; 
  if(document.forms[0].elements['categorieClient'].value == '0')
    {
    // Activer les champs relatifs au client personne physique
    document.forms[0].elements['titreCivilite'].disabled = false;     
    document.forms[0].elements['nomClient'].disabled = false;     
    document.forms[0].elements['prenomClient'].disabled = false;     
    document.forms[0].elements['dateNaissanceClient'].disabled = false;     
    document.forms[0].elements['situationFamilialeClient'].disabled = false;     
    document.forms[0].elements['nombreEnfantsClient'].disabled = false;     
    document.forms[0].elements['professionClient'].disabled = false;     
    document.forms[0].elements['revenuAnnuelClient'].disabled = false;     
    document.forms[0].elements['employeurClient'].disabled = false;         
    document.forms[0].elements['telephoneDomicileClient'].disabled = false;         
    document.forms[0].elements['gsmClient'].disabled = false;         
    document.forms[0].elements['poidsClient'].disabled = false;         
    document.forms[0].elements['tailleClient'].disabled = false;                         
    
    // Desactiver les champs relatifs au client personne morale
	    document.forms[0].elements['formeJuridiqueClient'].value ='';
    document.forms[0].elements['formeJuridiqueClient'].disabled = true;
    	document.forms[0].elements['raisonSocialeClient'].value ='';
    document.forms[0].elements['raisonSocialeClient'].disabled = true;
    	document.forms[0].elements['activiteClient'].value ='';
    document.forms[0].elements['activiteClient'].disabled = true;    
    }
  else 
    {
    // Activer les champs relatifs au client personne morale
    document.forms[0].elements['formeJuridiqueClient'].disabled = false;     
    document.forms[0].elements['raisonSocialeClient'].disabled = false; 
    document.forms[0].elements['activiteClient'].disabled = false;

    // Desactiver les champs relatifs au client personne physique    
    	document.forms[0].elements['titreCivilite'].value ='';
    document.forms[0].elements['titreCivilite'].disabled = true;     
	    document.forms[0].elements['nomClient'].value ='';    
    document.forms[0].elements['nomClient'].disabled = true;     
        document.forms[0].elements['prenomClient'].value ='';
    document.forms[0].elements['prenomClient'].disabled = true;     
        document.forms[0].elements['dateNaissanceClient'].value ='';
    document.forms[0].elements['dateNaissanceClient'].disabled = true;     
        document.forms[0].elements['situationFamilialeClient'].value ='';
    document.forms[0].elements['situationFamilialeClient'].disabled = true;     
        document.forms[0].elements['nombreEnfantsClient'].value ='';
    document.forms[0].elements['nombreEnfantsClient'].disabled = true;     
        document.forms[0].elements['professionClient'].value ='';
    document.forms[0].elements['professionClient'].disabled = true;     
        document.forms[0].elements['revenuAnnuelClient'].value ='';
    document.forms[0].elements['revenuAnnuelClient'].disabled = true;     
        document.forms[0].elements['employeurClient'].value ='';
    document.forms[0].elements['employeurClient'].disabled = true;         
        document.forms[0].elements['telephoneDomicileClient'].value ='';
    document.forms[0].elements['telephoneDomicileClient'].disabled = true;         
        document.forms[0].elements['gsmClient'].value ='';
    document.forms[0].elements['gsmClient'].disabled = true;         
        document.forms[0].elements['poidsClient'].value ='';
    document.forms[0].elements['poidsClient'].disabled = true;         
        document.forms[0].elements['tailleClient'].value ='';
    document.forms[0].elements['tailleClient'].disabled = true;                             
    }
}

//------------------------------------------------------------------------
//	En cas de modification d'un client n'activer que les infos modifiables
//------------------------------------------------------------------------
function disableUnchangedClientItems() {
    document.forms[0].elements['categorieClient'].disabled = true;
    document.forms[0].elements['typePieceIdentiteClient'].disabled = true;    
    document.forms[0].elements['numeroPieceIdentiteClient'].disabled = true;    
    document.forms[0].elements['nomClient'].disabled = true;    
    document.forms[0].elements['prenomClient'].disabled = true;    
    document.forms[0].elements['dateNaissanceClient'].disabled = true;                   
    
    // Prise en compte de la categorie du client  
    
    if(document.forms[0].elements['categorieClient'].value == '0'){
    // Desactiver les champs relatifs au client personne morale
	    document.forms[0].elements['formeJuridiqueClient'].value ='';
    document.forms[0].elements['formeJuridiqueClient'].disabled = true;
    	document.forms[0].elements['raisonSocialeClient'].value ='';
    document.forms[0].elements['raisonSocialeClient'].disabled = true;
    	document.forms[0].elements['activiteClient'].value ='';
    document.forms[0].elements['activiteClient'].disabled = true;    
    } 
    else {
    // Desactiver les champs relatifs au client personne physique    
    	document.forms[0].elements['titreCivilite'].value ='';
    document.forms[0].elements['titreCivilite'].disabled = true;     
	    document.forms[0].elements['nomClient'].value ='';    
    document.forms[0].elements['nomClient'].disabled = true;     
        document.forms[0].elements['prenomClient'].value ='';
    document.forms[0].elements['prenomClient'].disabled = true;     
        document.forms[0].elements['dateNaissanceClient'].value ='';
    document.forms[0].elements['dateNaissanceClient'].disabled = true;     
        document.forms[0].elements['situationFamilialeClient'].value ='';
    document.forms[0].elements['situationFamilialeClient'].disabled = true;     
        document.forms[0].elements['nombreEnfantsClient'].value ='';
    document.forms[0].elements['nombreEnfantsClient'].disabled = true;     
        document.forms[0].elements['professionClient'].value ='';
    document.forms[0].elements['professionClient'].disabled = true;     
        document.forms[0].elements['revenuAnnuelClient'].value ='';
    document.forms[0].elements['revenuAnnuelClient'].disabled = true;     
        document.forms[0].elements['employeurClient'].value ='';
    document.forms[0].elements['employeurClient'].disabled = true;         
        document.forms[0].elements['telephoneDomicileClient'].value ='';
    document.forms[0].elements['telephoneDomicileClient'].disabled = true;         
        document.forms[0].elements['gsmClient'].value ='';
    document.forms[0].elements['gsmClient'].disabled = true;         
        document.forms[0].elements['poidsClient'].value ='';
    document.forms[0].elements['poidsClient'].disabled = true;         
        document.forms[0].elements['tailleClient'].value ='';
    document.forms[0].elements['tailleClient'].disabled = true;                             
    }
}
//------------------------------------------------------------------------
//	En cas de creation d'un client n'activer que les infos correspondants 
//   a la categorie
//------------------------------------------------------------------------
function disableUnwantedClientItems() {
  if(document.forms[0].elements['categorieClient'].value == '') return; 
  if(document.forms[0].elements['categorieClient'].value == '0')
    {
    // Activer les champs relatifs au client personne physique
    document.forms[0].elements['titreCivilite'].disabled = false;     
    document.forms[0].elements['nomClient'].disabled = false;     
    document.forms[0].elements['prenomClient'].disabled = false;     
    document.forms[0].elements['dateNaissanceClient'].disabled = false;     
    document.forms[0].elements['situationFamilialeClient'].disabled = false;     
    document.forms[0].elements['nombreEnfantsClient'].disabled = false;     
    document.forms[0].elements['professionClient'].disabled = false;     
    document.forms[0].elements['revenuAnnuelClient'].disabled = false;     
    document.forms[0].elements['employeurClient'].disabled = false;         
    document.forms[0].elements['telephoneDomicileClient'].disabled = false;         
    document.forms[0].elements['gsmClient'].disabled = false;         
    document.forms[0].elements['poidsClient'].disabled = false;         
    document.forms[0].elements['tailleClient'].disabled = false;                         
    
    // Desactiver les champs relatifs au client personne morale
    document.forms[0].elements['formeJuridiqueClient'].disabled = true;
    document.forms[0].elements['raisonSocialeClient'].disabled = true;
    document.forms[0].elements['activiteClient'].disabled = true;    
    }
  else 
    {
    // Activer les champs relatifs au client personne morale
    document.forms[0].elements['formeJuridiqueClient'].disabled = false;     
    document.forms[0].elements['raisonSocialeClient'].disabled = false; 
    document.forms[0].elements['activiteClient'].disabled = false;

    // Desactiver les champs relatifs au client personne physique    
    document.forms[0].elements['titreCivilite'].disabled = true;     
    document.forms[0].elements['nomClient'].disabled = true;     
    document.forms[0].elements['prenomClient'].disabled = true;     
    document.forms[0].elements['dateNaissanceClient'].disabled = true;     
    document.forms[0].elements['situationFamilialeClient'].disabled = true;     
    document.forms[0].elements['nombreEnfantsClient'].disabled = true;     
    document.forms[0].elements['professionClient'].disabled = true;     
    document.forms[0].elements['revenuAnnuelClient'].disabled = true;     
    document.forms[0].elements['employeurClient'].disabled = true;         
    document.forms[0].elements['telephoneDomicileClient'].disabled = true;         
    document.forms[0].elements['gsmClient'].disabled = true;         
    document.forms[0].elements['poidsClient'].disabled = true;         
    document.forms[0].elements['tailleClient'].disabled = true;                             
    }
}