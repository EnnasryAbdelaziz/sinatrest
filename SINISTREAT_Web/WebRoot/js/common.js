//------------------------------------------------------------------------------------
// Desactiver tous les Elements d'un formulaire
//------------------------------------------------------------------------------------

function disableAllItems() {
    var listForms =  document.forms;
    for (i=0 ;i < listForms.length ; i++)
    {
		var listItems = document.forms[i].elements;
		for (j=0; j<listItems.length;j++) {
		 if (document.forms[i].elements[j].type != 'hidden')
			 document.forms[i].elements[j].disabled = true;
			}
	}
}


function execAction(action){
		document.forms[0].action=action;		
//		document.forms[0].actions.value=arg;
		document.forms[0].submit();	
}
//------------------------------------------------------------------------------------
// Activer tous les Elements d'un formulaire
//------------------------------------------------------------------------------------

function activateAllItems() {
	var listItems = document.forms[0].elements;
	for (i=0; i<listItems.length;i++) {
		document.forms[0].elements[i].disabled = false;
	}
}

function setJob(arg) {

	document.forms[0].job.value = arg;
	activateAllItems();
}



//------------------------------------------------------------------------------------
// initialiser tous les Elements text d'un formulaire
//------------------------------------------------------------------------------------

function initTextItems() {
	var listItems = document.forms[0].elements;
	for (i=0; i<listItems.length;i++) {
	if (document.forms[0].elements[i].type == 'text' || document.forms[0].elements[i].type == 'hidden')
			 document.forms[0].elements[i].value = '';
					
	}
}

//------------------------------------------------------------------------------------
// initialiser tous les Elements text d'un formulaire
//------------------------------------------------------------------------------------

function initSelectItems() {
	var listItems = document.forms[0].elements;
	for (i=0; i<listItems.length;i++) {
	if (document.forms[0].elements[i].type == 'select' || document.forms[0].elements[i].name != 'type')
			 document.forms[0].elements[i].selectedIndex = 0;
					
	}
}
function hideInfoMessage(value) {
	var msgInfo = document.getElementById('msgInfos');
	if(value==true)
		msgInfo.style.display="none";
	else
		msgInfo.style.display="block";
}
function setJob(arg) {

	document.forms[0].job.value = arg;
	activateAllItems();
}

//------------------------------------------------------------------------------------
//   Lancer un formulaire en une PopUp
//------------------------------------------------------------------------------------

function loadFormInPOPUP(Url,width,height)
			{
			DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
			window.open(Url,"","location=no,scrollbars=yes,menubars=no,toolbars=no,resizable=no,left=0,top=0,width="+ width+",height="+ height +"");
			return false;
			var prm=new Array();
   			prm[0]=window.location;			
			//window.showModalDialog(Url,prm,'dialogHeight:'+height+'px; dialogWidth:'+width+'px; center: Yes; help: No; resizable: No; status: No; scroll:1');
			//return false;
}
function loadFormInModalPOPUP(Url,width,height)
			{
			//window.open(Url,"","location=no,scrollbars=yes,menubars=no,toolbars=no,resizable=no,left=0,top=0,width="+ width+",height="+ height +"");
			//return false;
			var prm=new Array();
   			prm[0]=window.location;	   			
   			showMessage();
			window.showModalDialog(Url,prm,'dialogHeight:'+height+'px; dialogWidth:'+width+'px; center: Yes; help: No; resizable: No; status: No; scroll:1');
			hideMessage();
			return false;
}
function loadFormInModal(Url,width,height)
			{
			//window.open(Url,"","location=no,scrollbars=yes,menubars=no,toolbars=no,resizable=no,left=0,top=0,width="+ width+",height="+ height +"");
			//return false;
			var prm=new Array();
   			prm[0]=window.location;	   			
   			showMessage();
			var value = window.showModalDialog(Url,prm,'dialogHeight:'+height+'px; dialogWidth:'+width+'px; center: Yes; help: No; resizable: No; status: No; scroll:1');
			hideMessage();
			return value;
}

function loadMessage(message){
	  var loadingMessage;
  	  if (message) loadingMessage = message;
	  disabledZone = document.createElement('div');
      disabledZone.setAttribute('id', 'disabledZone');
      disabledZone.style.position = "absolute";
      disabledZone.style.zIndex = "1000";
      disabledZone.style.left = "0px";
      disabledZone.style.top = "0px";
      disabledZone.style.width = "100%";
      disabledZone.style.height = "100%";
      document.body.appendChild(disabledZone);
      var messageZone = document.createElement('div');
      messageZone.setAttribute('id', 'messageZone');
      messageZone.style.position = "absolute";
      messageZone.style.top = "0px";
      messageZone.style.right = "0px";
      messageZone.style.background = "red";
      messageZone.style.color = "white";
      messageZone.style.fontFamily = "Arial,Helvetica,sans-serif";
      messageZone.style.padding = "4px";
      disabledZone.appendChild(messageZone);
      var text = document.createTextNode(loadingMessage);
      messageZone.appendChild(text);
}

function showMessage(){
  	  var disabledZone = document.getElementById('disabledZone');
      if (!disabledZone) 
	  		loadMessage('Chargement des données; veuillez patientez SVP ...');
	  document.getElementById('disabledZone').style.visibility = 'visible';
}
function hideMessage(){
	  document.getElementById('disabledZone').style.visibility = 'hidden';
}

function showElement(element){
	  document.getElementById(element).style.display="block";
}
function hideElement(element){
	  document.getElementById(element).style.display="none";
}
//------------------------------------------------------------------------------------
//   Recuperer la valeur de retour d'une PopUp vers un formulaire 
//------------------------------------------------------------------------------------			
			
			
function setOpenerElementValueById(id,value)
			{
			var index ="";
			value = value.replace(' ','');
			value = value.replace('\n','');
			value = value.replace('\r','');
			value = value.replace('\t','');
			value = trim(value);
			//index =getIndexforSelectedValue(id,value);
			//window.opener.document.getElementById(id).selectedIndex=index;		
			window.opener.document.getElementsByName(id)[0].value=value;
			appelAjaxGetMandataire(window.opener.document);					
			//alert('1'+value);	

			}	
function returnValueToWindowOpener (value)
			{
				value = value.replace(' ','');
				value = value.replace('\n','');
				value = value.replace('\r','');
				value = value.replace('\t','');				
			 	window.returnValue	=value;

			}

//------------------------------------------------------------------------------------
//   recuperer l'index d'une liste (Select) 
//------------------------------------------------------------------------------------			

function getIndexforSelectedValue(id,value)
{
			var length = window.opener.document.getElementById(id).options.length;
			var i=0;
			var index="";			
			var	val="";
			for (i;i<length;i++)
			{
			val= window.opener.document.getElementById(id).options[i].value;
				if (val == value)
				{			
					index=i;
				}
			}	
			return index;		
}
//------------------------------------------------------------------------------------
//   Fermer un formulaire 
//------------------------------------------------------------------------------------			

function closeCurrentWindow()
			{			
			window.close();
			}
			

function trim (myString) 
{ 
	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'') ;
}
function trim2 (myString) 
{ 
	return myString.replace(/^\s*|\s*$/,'');
}
//------------------------------------------------------------------------------------
// Charger les images
//------------------------------------------------------------------------------------

function MM_preloadImages() { 
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}


//------------------------------------------------
//	Valider une date 
//------------------------------------------------

function validerDate(index){
    	//alert('validerDate :'+ index);
	var a=document.forms[0].elements[index].value; // Zone de texte 
	var ismois=false;
	var isannee=false;

	// Se debarasser des caracteres indesirables !! du balai ;)	
	a = a.replace(/[^0-9]/g,"");
	a = a.substr(0,8);

	//Si jour est saisi
	if ((a.length > 2) && (a.substring(2,3)!="/")	){
		a = a.substring(0,2)+"/"+a.substring(2,a.length);
	}
	//Si mois saisi
	if ((a.length > 5) && (a.substring(5,6)!="/")	){
		a = a.substring(0,5)+"/"+a.substring(5,a.length);
	}
	

	var jourc, moisc, anneec;
	var jour, mois, annee;
	var validFev = false;
	jour = a.substring(0,2)*1;	
	jourc = a.substring(0,2);	

	if (a.length>2){
		mois = a.substring(3,5)*1;
		moisc = a.substring(3,5);
		ismois = true;
	}
	if (a.length>5){
		annee = a.substring(6,10)*1;
		anneec = a.substring(6,10);
		isannee=true;
		if (anneec.length > 3){
			validFev= true;
		}
	}

	//Validation du jour
	if (jour>31){
		jourc="31";
	}
	if (jourc == "00"){
		jourc="01";
	}
	//Validation du mois
	if (mois>12){
		moisc="12";
	}
	if (moisc == "00"){
		moisc="01";
	}
	if (anneec == "0000"){
		anneec="0001";
	}	
	//Validation de l'ann?e

	if ( (jour < 10) && (ismois)  ) jour = "0" + jour;
	if ( (mois < 10) && (isannee) ) mois = "0" + mois;

	if (ismois) {
		//Verifier le jour par rapport au mois
		if ( (mois==4) || (mois==6) || (mois==9) || (mois==11) ) {
			if (jour == 31 ) {
				jourc="30";
			}
		}
	
	}
	if (validFev == true){
		//FEVRIER 
		if (mois == 2){
			if ( (annee/4) != parseInt(annee/4)){
				if (jour>28) jourc="28";
			} else {
				if (jour>29) jourc="29";
			}
		}
	}
	a = jourc ;
	if (ismois){
		a = a + "/" + moisc;		
	}	
	if (isannee){
		a = a + "/" + anneec;
	}	
	document.forms[0].elements[index].value = a;
}


//--------------------------------------
//	Valider un champ de type mois
//
//--------------------------------------
function validerMois(index){
	var a=document.forms[0].elements[index].value; // Zone de texte 
	a = a.replace(/[^0-9]/gi,"");
	if (((a.length > 1) && a==0)){
		a="01";
	}
	if (a>12){
		a="12";
	}
	document.forms[0].elements[index].value = a;
}

function endValiderMois(index){
	var a=document.forms[0].elements[index].value; // Zone de texte 
	if (((a.length == 1) && a==0)){
		a="01";
	}
	document.forms[0].elements[index].value = a;
}


//--------------------------------------
//	Valider un champ de type entier
//
//--------------------------------------
function validerEntier(index){
	var a=document.forms[0].elements[index].value; // Zone de texte 
	a = a.replace(/[^0-9]/gi,"");
	document.forms[0].elements[index].value = a;
}


//--------------------------------------
// Valider un champ de type d?cimal
//
//	ent : nbre de digits dans la partie enti?re
//	dec : nbre de digits dans la partie d?cimale
//--------------------------------------
function validerDecimal(index,ent,dec){
	var a=document.forms[0].elements[index].value; // Zone de texte 

	// Se debarasser des caracteres indesirables !! du balai ;)	
	a = a.replace(/[^0-9.]/g,"");

	//Avoir un seul point
	var ind =  a.indexOf(".");
		// si no point et limite atteinte, ajouter le point ;)
	if ( (ind == -1) && (a.length == ent)) {
		a = a + ".";
	}
	while (ind != -1){
		ind = a.indexOf(".",ind+1);
		a = a.substring(0,ind) + a.substring(ind+1, a.length);
	}
	//Avoir ent chiffres au d?but, et dec chiffres ? la fin
	ind = a.indexOf(".");
	var entpart, decpart;
	if (ind != -1){
		entpart = a.substring(0,ind);
		entpart = entpart.substr(0,ent);
		decpart = a.substring(ind+1, a.length);
		decpart = decpart.substr(0,dec);
		a = entpart + "." + decpart;
	}
	
	document.forms[0].elements[index].value = a;
}


//--------------------------------------
//	Valider un champ de type nom ou prenom
//
//--------------------------------------
function validerUpperCase(index){
	var a=document.forms[0].elements[index].value; // Zone de texte 
	a = a.replace(/[^ A-Z]/gi,"");
	a = a.toUpperCase();
	document.forms[0].elements[index].value = a;}


//----------------------------------
//    Fonctions pour le sablier
//  
//----------------------------------
function desactiveMenu() {
         alert ('Traitement en cours...!');
         return false;
   }
      
function onSubmitEventAction(){
	var k = 0;
	var doc;
	var listTables;
    //
   
    document.getElementById('Sablier').style.display ='inline';
   	var listTables = document.getElementsByTagName('TABLE');
  	for (k=0; k<listTables.length;k++) {	
		listTables[k].style.display = 'none';
  	}	
}

function activerMenu(){
   var k = 0;
   var doc;
   var listTables;
   //
    var listTables = document.getElementsByTagName('TABLE');
	for (k=0; k<listTables.length;k++) {	
		listTables[k].style.display = 'inline';
	}
    document.getElementById('Sablier').style.display ='none';  
}

//---------------------------------------------
//       CIN semi-validation
//---------------------------------------------

function validerCIN(index){
	var a=document.forms[0].elements[index].value; // Zone de texte 
	a = a.replace(/[^0-9A-Z]/gi,"");
	a = a.toUpperCase();
	document.forms[0].elements[index].value = a;
}

//--------------------------------------
//	Valider un champ de type entier
//
//--------------------------------------
function validerLettreProduit(index){
	var a=document.forms[0].elements[index].value; // Zone de texte 
	a = a.replace(/[^A-Z]/gi,"");
	a = a.toUpperCase();
	document.forms[0].elements[index].value = a;
}


function disableItemsForModification(){
   if(document.forms[0].elements['dateVersementPremierePrimeGlobale'] != null) {
   		document.forms[0].elements['dateVersementPremierePrimeGlobale'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesGlobale[0].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesGlobale[0].nomCompagnie'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesGlobale[0].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesGlobale[0].pourcentage'].disabled = true;
   }

   if(document.forms[0].elements['coassurancesGlobale[1].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesGlobale[1].nomCompagnie'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesGlobale[1].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesGlobale[1].pourcentage'].disabled = true;
   }

   if(document.forms[0].elements['coassurancesGlobale[2].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesGlobale[2].nomCompagnie'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesGlobale[2].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesGlobale[2].pourcentage'].disabled = true;
   }
   
   if(document.forms[0].elements['coassurancesGlobale[3].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesGlobale[3].nomCompagnie'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesGlobale[3].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesGlobale[3].pourcentage'].disabled = true;
   }
   
   if(document.forms[0].elements['typePieceIdentiteClientSouscripteur'] != null) {
   		document.forms[0].elements['typePieceIdentiteClientSouscripteur'].disabled = true;
   }
   if(document.forms[0].elements['numeroPieceIdentiteClientSouscripteur'] != null) {
   		document.forms[0].elements['numeroPieceIdentiteClientSouscripteur'].disabled = true;
   }
   if(document.forms[0].elements['typePieceIdentiteClientAdherent'] != null) {
   		document.forms[0].elements['typePieceIdentiteClientAdherent'].disabled = true;
   }
   if(document.forms[0].elements['numeroPieceIdentiteClientAdherent'] != null) {
   		document.forms[0].elements['numeroPieceIdentiteClientAdherent'].disabled = true;
   }
   
   // Epargne
   if(document.forms[0].elements['versementInitial'] != null) {
   		document.forms[0].elements['versementInitial'].disabled = true;
   }  
   
   if(document.forms[0].elements['etatPartieEpargne'] != null && 
           (document.forms[0].elements['etatPartieEpargne'].value == '3' || document.forms[0].elements['etatPartieEpargne'].value == '2')
           ) {
   		// cas du rachat total de la partie epargne
   
		if(document.forms[0].elements['garantieEpargneActive'] != null) {
   			document.forms[0].elements['garantieEpargneActive'].disabled = true;
		}
		
		if(document.forms[0].elements['nantissement'] != null) {
   			document.forms[0].elements['nantissement'].disabled = true;
		}
		
		if(document.forms[0].elements['deductibilite'] != null) {
   			document.forms[0].elements['deductibilite'].disabled = true;
		}
				
      	if(document.forms[0].elements['versementInitial'] != null) {
   			document.forms[0].elements['versementInitial'].disabled = true;
   		}		
   		
		if(document.forms[0].elements['cotisation'] != null) {
   			document.forms[0].elements['cotisation'].disabled = true;
		}
		
		if(document.forms[0].elements['typePeriodiciteCotisationChoisi'] != null) {
   			document.forms[0].elements['typePeriodiciteCotisationChoisi'].disabled = true;
		}
		
		if(document.forms[0].elements['dureeGarantieEpargne'] != null) {
   			document.forms[0].elements['dureeGarantieEpargne'].disabled = true;
		}
		
		if(document.forms[0].elements['dateTermeEpargne'] != null) {
   			document.forms[0].elements['dateTermeEpargne'].disabled = true;
		}				   
   }
   
     
   
   // Prevoyance
   if(document.forms[0].elements['coassurancesPrevoyance[0].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesPrevoyance[0].nomCompagnie'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesPrevoyance[0].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesPrevoyance[0].pourcentage'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesPrevoyance[1].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesPrevoyance[1].nomCompagnie'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesPrevoyance[1].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesPrevoyance[1].pourcentage'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesPrevoyance[2].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesPrevoyance[2].nomCompagnie'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesPrevoyance[2].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesPrevoyance[2].pourcentage'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesPrevoyance[3].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesPrevoyance[3].nomCompagnie'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesPrevoyance[3].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesPrevoyance[3].pourcentage'].disabled = true;
   }         
   
   // Dommages
   if(document.forms[0].elements['coassurancesDommages[0].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesDommages[0].nomCompagnie'].disabled = true;
   }    
   if(document.forms[0].elements['coassurancesDommages[0].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesDommages[0].pourcentage'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesDommages[1].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesDommages[1].nomCompagnie'].disabled = true;
   }    
   if(document.forms[0].elements['coassurancesDommages[1].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesDommages[1].pourcentage'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesDommages[2].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesDommages[2].nomCompagnie'].disabled = true;
   }    
   if(document.forms[0].elements['coassurancesDommages[2].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesDommages[2].pourcentage'].disabled = true;
   }
   if(document.forms[0].elements['coassurancesDommages[3].nomCompagnie'] != null) {
   		document.forms[0].elements['coassurancesDommages[3].nomCompagnie'].disabled = true;
   }    
   if(document.forms[0].elements['coassurancesDommages[3].pourcentage'] != null) {
   		document.forms[0].elements['coassurancesDommages[3].pourcentage'].disabled = true;
   }
}


function onChangeOptionBaremePrevoyance() {
	
	if(document.forms[0].elements['primePrevoyance']!= null) {
		document.forms[0].elements['primePrevoyance'].value = '';
	}
	
	if(document.forms[0].elements['surprimePrevoyance']!= null) {
		document.forms[0].elements['surprimePrevoyance'].value = '';
	}
	
	if(document.forms[0].elements['primeTotalePrevoyance']!= null) {
		document.forms[0].elements['primeTotalePrevoyance'].value = '';
	}	
	
	if(document.forms[0].elements['capitalMinAssurePrevoyance']!= null) {
		document.forms[0].elements['capitalMinAssurePrevoyance'].value = '';
	}
	
	if(document.forms[0].elements['capitalMaxAssurePrevoyance']!= null) {
		document.forms[0].elements['capitalMaxAssurePrevoyance'].value = '';
	}
	
	appelAjaxPrimePrevoyance();
}

function onChangeOptionBaremePrevoyancePers() {
	
	if(document.forms[0].elements['primePrevoyancePers']!= null) {
		document.forms[0].elements['primePrevoyancePers'].value = '';
	}
	
	if(document.forms[0].elements['surprimePrevoyancePers']!= null) {
		document.forms[0].elements['surprimePrevoyancePers'].value = '';
	}
	
	if(document.forms[0].elements['primeTotalePrevoyancePers']!= null) {
		document.forms[0].elements['primeTotalePrevoyancePers'].value = '';
	}	
	
	if(document.forms[0].elements['capitalMinAssurePrevoyancePers']!= null) {
		document.forms[0].elements['capitalMinAssurePrevoyancePers'].value = '';
	}
	
	if(document.forms[0].elements['capitalMaxAssurePrevoyancePers']!= null) {
		document.forms[0].elements['capitalMaxAssurePrevoyancePers'].value = '';
	}
	
	appelAjaxPrimePrevoyancePers();
}


function onChangeSecteur() {
 	if(document.forms[0].elements['secteurActivite']!= null && document.forms[0].elements['secteurActivite'].value != '') {
    	document.forms[0].job.value = 'POPULATE_ACTIVITIES';
    	activateAllItems();
	    onSubmitEventAction();
		document.forms[0].submit();
 	}
}

function onChangeActivite() {

    if(document.forms[0].elements['activiteDommages'].value != '') {
       	if(document.forms[0].elements['activiteComplement']!= null) {
	       	document.forms[0].elements['activiteComplement'].value = '';
			document.forms[0].elements['activiteComplement'].disabled = true;
		}
    } else {    
       if(document.forms[0].elements['activiteComplement']!= null) {
	       	document.forms[0].elements['activiteComplement'].disabled = false;
		}
    }  
	
	if(document.forms[0].elements['primeDommages']!= null) {
		document.forms[0].elements['primeDommages'].value = '';
	}
	
	if(document.forms[0].elements['surprimeDommages']!= null) {
		document.forms[0].elements['surprimeDommages'].value = '';
	}
	
	if(document.forms[0].elements['primeTotaleDommages']!= null) {
		document.forms[0].elements['primeTotaleDommages'].value = '';
	}	
	
	if(document.forms[0].elements['capitalMobilier']!= null) {
		document.forms[0].elements['capitalMobilier'].value = '';
	}
	
	if(document.forms[0].elements['capitalImmobilier']!= null) {
		document.forms[0].elements['capitalImmobilier'].value = '';
	}
}


function onChangeOptionBaremeDommages() {
	
	if(document.forms[0].elements['primeDommages']!= null) {
		document.forms[0].elements['primeDommages'].value = '';
	}
	
	if(document.forms[0].elements['surprimeDommages']!= null) {
		document.forms[0].elements['surprimeDommages'].value = '';
	}
	
	if(document.forms[0].elements['primeTotaleDommages']!= null) {
		document.forms[0].elements['primeTotaleDommages'].value = '';
	}	
	
	if(document.forms[0].elements['capitalMobilier']!= null) {
		document.forms[0].elements['capitalMobilier'].value = '';
	}
	
	if(document.forms[0].elements['capitalImmobilier']!= null) {
		document.forms[0].elements['capitalImmobilier'].value = '';
	}
	
	appelAjaxPrimeDommagesSansActivite();
}


function onChangeCapitalMobilier() {
 	if(document.forms[0].elements['capitalMobilier']!= null && document.forms[0].elements['capitalMobilier'].value != '') {
		document.forms[0].elements['capitalImmobilier'].value = 3 * document.forms[0].elements['capitalMobilier'].value;
		document.forms[0].elements['capitalImmobilier'].value = document.forms[0].elements['capitalImmobilier'].value + '.00';
		appelAjaxPrimeDommagesAvecActivite();
 	}else{
	 	document.forms[0].elements['capitalImmobilier'].value = '';
 	}
}


function supprimerEnfant(arg) {

      nomEnfant = 'enfants['+ arg + '].nom';
      prenomEnfant = 'enfants['+ arg + '].prenom';
      dateNaissanceEnfant = 'enfants['+ arg + '].dateNaissance'; 
      numeroEnfant = 'enfants['+ arg + '].numero';  
      enInstanceEnfant = 'enfants['+ arg + '].enInstance';                  
      
      document.forms[0].elements[nomEnfant].value = '';
	  document.forms[0].elements[prenomEnfant].value = '';
	  document.forms[0].elements[dateNaissanceEnfant].value = '';
	  document.forms[0].elements[numeroEnfant].value = '';
	  document.forms[0].elements[enInstanceEnfant].value = '';		  	  
}

function disableEnfants(){
	for (i=0; i<11;i++) {
	  nomEnfant = 'enfants['+ i + '].nom';
      prenomEnfant = 'enfants['+ i + '].prenom';
      dateNaissanceEnfant = 'enfants['+ i + '].dateNaissance';            
      
		if(document.forms[0].elements[nomEnfant] != null) {      
      		document.forms[0].elements[nomEnfant].disabled = true;
      	}
		if(document.forms[0].elements[prenomEnfant] != null) {      
      		document.forms[0].elements[prenomEnfant].disabled = true;
      	}
		if(document.forms[0].elements[dateNaissanceEnfant] != null) {      
      		document.forms[0].elements[dateNaissanceEnfant].disabled = true;
      	}      	      		
	}
}


function limiteTextArea(zone,size)
{
if ((zone != null) && (zone.value.length>=size))
    {zone.value=zone.value.substring(0,size);}
}

// appel appelAjaxActivites
function appelAjaxActivites() {    
    url = 'ajaxActivitesAction.do?idSecteur=' + document.forms[0].elements['secteurActivite'].value; 
	ajaxCallRemotePageActivites(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageActivites(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeActivites;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeActivites;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeActivites() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;      
       document.getElementById("listeActivites").innerHTML = xml;
       

        if(document.forms[0].elements['activiteComplement']!= null) {
	       	document.forms[0].elements['activiteComplement'].disabled = false;
		}
    
		if(document.forms[0].elements['primeDommages']!= null) {
			document.forms[0].elements['primeDommages'].value = '';
		}
		
		if(document.forms[0].elements['surprimeDommages']!= null) {
			document.forms[0].elements['surprimeDommages'].value = '';
		}
		
		if(document.forms[0].elements['primeTotaleDommages']!= null) {
			document.forms[0].elements['primeTotaleDommages'].value = '';
		}	
		
		if(document.forms[0].elements['capitalMobilier']!= null) {
			document.forms[0].elements['capitalMobilier'].value = '';
		}
		
		if(document.forms[0].elements['capitalImmobilier']!= null) {
			document.forms[0].elements['capitalImmobilier'].value = '';
		}
       
       appelAjaxCapitaux();       
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

/// Fin activites

// appel appelAjaxCapitaux
function appelAjaxCapitaux() {    
    url = 'ajaxCapitauxAction.do?idSecteur=' + document.forms[0].elements['secteurActivite'].value; 
	ajaxCallRemotePageCapitaux(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageCapitaux(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeCapitaux;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeCapitaux;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeCapitaux() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;   
       document.getElementById("listeCapitauxMobilier").innerHTML = xml;       
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// Fin Capitaux

// appel dans la page pour chercher l'assure
function appelAjaxAdherent() {   
    if(document.forms[0].elements['typePieceIdentiteClientAdherent'].value != '' &&  document.forms[0].elements['numeroPieceIdentiteClientAdherent'].value != '') {
    	url = 'ajaxAdherentAction.do?tCIN=' + document.forms[0].elements['typePieceIdentiteClientAdherent'].value + '&nCIN=' + document.forms[0].elements['numeroPieceIdentiteClientAdherent'].value; 
		ajaxCallRemoteAdherentPage(url);
	}else{
		alert('Veuillez fournir le type et le numero de la CIN');
	}
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemoteAdherentPage(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeAdherent;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeAdherent;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeAdherent() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       var rxml = req.responseXML;  
       var client = rxml.documentElement;
        
       var idAdherent = (client.getElementsByTagName("idAdherent"))[0];
       
        if(idAdherent.text == 0){
         alert('Client inexistant dans la base !!');
         
         document.forms[0].elements['idAdherent'].value = '';

         document.forms[0].elements['lastNumVersionAdherent'].value = '';
         
         document.forms[0].elements['rappatrieAdherent'].value = '';
         
	     document.forms[0].elements['numeroClientAdherent'].value = '';  
                 
         document.forms[0].elements['dateCreationClientAdherent'].value = '';   
         
         document.forms[0].elements['dateAncienneteClientAdherent'].value = '';  
         
         document.forms[0].elements['categorieClientAdherent'].value = '';   
         
         document.forms[0].elements['typeClientAdherent'].value = '';   
         
         document.forms[0].elements['gammeClientAdherent'].value = '';  
         
         document.forms[0].elements['segementClientAdherent'].value = '';   
         
         document.forms[0].elements['adresse1ClientAdherent'].value = '';  
         
         document.forms[0].elements['adresse2ClientAdherent'].value = '';   
         
         document.forms[0].elements['adresse3ClientAdherent'].value = '';   
         
         document.forms[0].elements['paysClientAdherent'].value = ''; 
         
         document.forms[0].elements['villeClientAdherent'].value = '';   
         
         document.forms[0].elements['codePostalClientAdherent'].value = '';  
         
         document.forms[0].elements['emailClientAdherent'].value = '';   
         
         document.forms[0].elements['telephoneBureauClientAdherent'].value = '';   
         
         document.forms[0].elements['faxClientAdherent'].value = '';   
         
         document.forms[0].elements['titreCiviliteAdherent'].value = '';   
       
         document.forms[0].elements['nomClientAdherent'].value = '';  
         
         document.forms[0].elements['prenomClientAdherent'].value = '';   
         
         document.forms[0].elements['dateNaissanceClientAdherent'].value = '';   
         
         document.forms[0].elements['situationFamilialeClientAdherent'].value = '';
         
         document.forms[0].elements['nombreEnfantsClientAdherent'].value = '';   
                
         document.forms[0].elements['professionClientAdherent'].value = '';   
         
         document.forms[0].elements['revenuAnnuelClientAdherent'].value = '';   
         
         document.forms[0].elements['employeurClientAdherent'].value = '';   
         
         document.forms[0].elements['telephoneDomicileClientAdherent'].value = '';   
         
         document.forms[0].elements['gsmClientAdherent'].value = ''; 
         
         document.forms[0].elements['poidsClientAdherent'].value = '';   
         
         document.forms[0].elements['tailleClientAdherent'].value = '';                                  
         return;         
        }
        document.forms[0].elements['idAdherent'].value = idAdherent.text;

       var lastNumVersionAdherent = (client.getElementsByTagName("lastNumVersionAdherent"))[0];
	     document.forms[0].elements['lastNumVersionAdherent'].value = lastNumVersionAdherent.text;   
	     
       var rappatrieAdherent = (client.getElementsByTagName("rappatrieAdherent"))[0];
	     document.forms[0].elements['rappatrieAdherent'].value = rappatrieAdherent.text;	     
	     
       var numeroClientAdherent = (client.getElementsByTagName("numeroClientAdherent"))[0];
	     document.forms[0].elements['numeroClientAdherent'].value = numeroClientAdherent.text;   
                 
       var dateCreationClientAdherent = (client.getElementsByTagName("dateCreationClientAdherent"))[0];
         document.forms[0].elements['dateCreationClientAdherent'].value = dateCreationClientAdherent.text;   
         
       var dateAncienneteClientAdherent = (client.getElementsByTagName("dateAncienneteClientAdherent"))[0];
         document.forms[0].elements['dateAncienneteClientAdherent'].value = dateAncienneteClientAdherent.text;   
         
       var categorieClientAdherent = (client.getElementsByTagName("categorieClientAdherent"))[0];
         document.forms[0].elements['categorieClientAdherent'].value = categorieClientAdherent.text;   
         
       var typeClientAdherent = (client.getElementsByTagName("typeClientAdherent"))[0];
         document.forms[0].elements['typeClientAdherent'].value = typeClientAdherent.text;   
         
       var gammeClientAdherent = (client.getElementsByTagName("gammeClientAdherent"))[0];
         document.forms[0].elements['gammeClientAdherent'].value = gammeClientAdherent.text;   
         
       var segementClientAdherent = (client.getElementsByTagName("segementClientAdherent"))[0];
         document.forms[0].elements['segementClientAdherent'].value = segementClientAdherent.text;   
         
       var adresse1ClientAdherent = (client.getElementsByTagName("adresse1ClientAdherent"))[0];
         document.forms[0].elements['adresse1ClientAdherent'].value = adresse1ClientAdherent.text;   
         
       var adresse2ClientAdherent = (client.getElementsByTagName("adresse2ClientAdherent"))[0];    
         document.forms[0].elements['adresse2ClientAdherent'].value = adresse2ClientAdherent.text;   
         
       var adresse3ClientAdherent = (client.getElementsByTagName("adresse3ClientAdherent"))[0];
         document.forms[0].elements['adresse3ClientAdherent'].value = adresse3ClientAdherent.text;   
         
       var paysClientAdherent = (client.getElementsByTagName("paysClientAdherent"))[0];
         document.forms[0].elements['paysClientAdherent'].value = paysClientAdherent.text;   
         
       var villeClientAdherent = (client.getElementsByTagName("villeClientAdherent"))[0];
         document.forms[0].elements['villeClientAdherent'].value = villeClientAdherent.text;   
         
       var codePostalClientAdherent = (client.getElementsByTagName("codePostalClientAdherent"))[0];
         document.forms[0].elements['codePostalClientAdherent'].value = codePostalClientAdherent.text;   
         
       var emailClientAdherent = (client.getElementsByTagName("emailClientAdherent"))[0];
         document.forms[0].elements['emailClientAdherent'].value = emailClientAdherent.text;   
         
       var telephoneBureauClientAdherent = (client.getElementsByTagName("telephoneBureauClientAdherent"))[0];
         document.forms[0].elements['telephoneBureauClientAdherent'].value = telephoneBureauClientAdherent.text;   
         
       var faxClientAdherent = (client.getElementsByTagName("faxClientAdherent"))[0];
         document.forms[0].elements['faxClientAdherent'].value = faxClientAdherent.text;   
         
       var titreCiviliteAdherent = (client.getElementsByTagName("titreCiviliteAdherent"))[0];
         document.forms[0].elements['titreCiviliteAdherent'].value = titreCiviliteAdherent.text;   
       
       var nomClientAdherent = (client.getElementsByTagName("nomClientAdherent"))[0];       		
         document.forms[0].elements['nomClientAdherent'].value = nomClientAdherent.text;   
         
       var prenomClientAdherent = (client.getElementsByTagName("prenomClientAdherent"))[0];
         document.forms[0].elements['prenomClientAdherent'].value = prenomClientAdherent.text;   
         
       var dateNaissanceClientAdherent = (client.getElementsByTagName("dateNaissanceClientAdherent"))[0];
         document.forms[0].elements['dateNaissanceClientAdherent'].value = dateNaissanceClientAdherent.text;   
         
       var situationFamilialeClientAdherent = (client.getElementsByTagName("situationFamilialeClientAdherent"))[0];
         document.forms[0].elements['situationFamilialeClientAdherent'].value = situationFamilialeClientAdherent.text;   
         
       var nombreEnfantsClientAdherent = (client.getElementsByTagName("nombreEnfantsClientAdherent"))[0];
         document.forms[0].elements['nombreEnfantsClientAdherent'].value = nombreEnfantsClientAdherent.text;   
                
       var professionClientAdherent = (client.getElementsByTagName("professionClientAdherent"))[0];
         document.forms[0].elements['professionClientAdherent'].value = professionClientAdherent.text;   
         
       var revenuAnnuelClientAdherent = (client.getElementsByTagName("revenuAnnuelClientAdherent"))[0];
         document.forms[0].elements['revenuAnnuelClientAdherent'].value = revenuAnnuelClientAdherent.text;   
         
       var employeurClientAdherent = (client.getElementsByTagName("employeurClientAdherent"))[0];
         document.forms[0].elements['employeurClientAdherent'].value = employeurClientAdherent.text;   
         
       var telephoneDomicileClientAdherent = (client.getElementsByTagName("telephoneDomicileClientAdherent"))[0];
         document.forms[0].elements['telephoneDomicileClientAdherent'].value = telephoneDomicileClientAdherent.text;   
         
       var gsmClientAdherent = (client.getElementsByTagName("gsmClientAdherent"))[0];
         document.forms[0].elements['gsmClientAdherent'].value = gsmClientAdherent.text;   
         
       var poidsClientAdherent = (client.getElementsByTagName("poidsClientAdherent"))[0];
         document.forms[0].elements['poidsClientAdherent'].value = poidsClientAdherent.text;   
         
       var tailleClientAdherent = (client.getElementsByTagName("tailleClientAdherent"))[0];
         document.forms[0].elements['tailleClientAdherent'].value = tailleClientAdherent.text; 
         
	   if(document.forms[0].elements['idAdherent']!= null && document.forms[0].elements['idAdherent'].value !=''){
	      document.forms[0].elements['typePieceIdentiteClientAdherent'].disabled = true;
	      document.forms[0].elements['numeroPieceIdentiteClientAdherent'].disabled = true;	      
	      if(document.forms[0].elements['nomClientAdherent']!=null && document.forms[0].elements['nomClientAdherent'].value!='') {
	      	
	      	  document.forms[0].elements['nomClientAdherent'].disabled = true;
	      }
	      if(document.forms[0].elements['prenomClientAdherent']!=null && document.forms[0].elements['prenomClientAdherent'].value!=''){
		      document.forms[0].elements['prenomClientAdherent'].disabled = true;
	      }
	      if(document.forms[0].elements['dateNaissanceClientAdherent']!=null && document.forms[0].elements['dateNaissanceClientAdherent'].value!=''){   
	          document.forms[0].elements['dateNaissanceClientAdherent'].disabled = true;
	      }         
	  }
           
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// Fin adherent


// conjoint
// appel dans la page pour chercher le conjoint
function appelAjaxConjoint() {   
    if(document.forms[0].elements['typePieceIdentiteClientConjoint'].value != '' &&  document.forms[0].elements['numeroPieceIdentiteClientConjoint'].value != '') {
    	url = 'ajaxConjointAction.do?tCIN=' + document.forms[0].elements['typePieceIdentiteClientConjoint'].value + '&nCIN=' + document.forms[0].elements['numeroPieceIdentiteClientConjoint'].value; 
		ajaxCallRemoteConjointPage(url);
	}else{
		alert('Veuillez fournir le type et le numero de la CIN');
	}
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemoteConjointPage(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeConjoint;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeConjoint;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeConjoint() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       var rxml = req.responseXML;  
       var client = rxml.documentElement;
        
       var idConjoint = (client.getElementsByTagName("idConjoint"))[0];
       
        if(idConjoint.text == 0){
         alert('Client inexistant dans la base !!');
           
         document.forms[0].elements['idConjoint'].value = '';

		 document.forms[0].elements['lastNumVersionConjoint'].value = '';

		 document.forms[0].elements['rappatrieConjoint'].value = '';

	     document.forms[0].elements['numeroClientConjoint'].value = '';   
                 
         document.forms[0].elements['dateCreationClientConjoint'].value = '';   
         
         document.forms[0].elements['dateAncienneteClientConjoint'].value = '';   
         
         document.forms[0].elements['categorieClientConjoint'].value = '';   
         
         document.forms[0].elements['typeClientConjoint'].value = '';  
         
         document.forms[0].elements['gammeClientConjoint'].value = '';   
         
         document.forms[0].elements['segementClientConjoint'].value = '';   
         
         document.forms[0].elements['adresse1ClientConjoint'].value = '';   
         
         document.forms[0].elements['adresse2ClientConjoint'].value = '';  
         
         document.forms[0].elements['adresse3ClientConjoint'].value = '';  
         
         document.forms[0].elements['paysClientConjoint'].value = '';  
         
         document.forms[0].elements['villeClientConjoint'].value = '';  
         
         document.forms[0].elements['codePostalClientConjoint'].value = '';   
         
         document.forms[0].elements['emailClientConjoint'].value = '';   
         
         document.forms[0].elements['telephoneBureauClientConjoint'].value = '';   
         
         document.forms[0].elements['faxClientConjoint'].value = ''; 
         
         document.forms[0].elements['titreCiviliteConjoint'].value = '';   
       
         document.forms[0].elements['nomClientConjoint'].value = '';   
         
         document.forms[0].elements['prenomClientConjoint'].value = '';  
         
         document.forms[0].elements['dateNaissanceClientConjoint'].value = '';   
         
         document.forms[0].elements['situationFamilialeClientConjoint'].value = '';;   
         
         document.forms[0].elements['nombreEnfantsClientConjoint'].value = '';;   
                
         document.forms[0].elements['professionClientConjoint'].value = '';;   
         
         document.forms[0].elements['revenuAnnuelClientConjoint'].value = '';;   
         
         document.forms[0].elements['employeurClientConjoint'].value = '';;   
         
         document.forms[0].elements['telephoneDomicileClientConjoint'].value = '';;   
         
         document.forms[0].elements['gsmClientConjoint'].value = '';;   
         
         document.forms[0].elements['poidsClientConjoint'].value = '';;   
         
         document.forms[0].elements['tailleClientConjoint'].value = '';;             
           
         return;
        }
        
       document.forms[0].elements['idConjoint'].value = idConjoint.text;
        
       var lastNumVersionConjoint = (client.getElementsByTagName("lastNumVersionConjoint"))[0];
	     document.forms[0].elements['lastNumVersionConjoint'].value = lastNumVersionConjoint.text; 
	   
       var rappatrieConjoint = (client.getElementsByTagName("rappatrieConjoint"))[0];
	     document.forms[0].elements['rappatrieConjoint'].value = rappatrieConjoint.text;
	     
       var numeroClientConjoint = (client.getElementsByTagName("numeroClientConjoint"))[0];
	     document.forms[0].elements['numeroClientConjoint'].value = numeroClientConjoint.text;   
                 
       var dateCreationClientConjoint = (client.getElementsByTagName("dateCreationClientConjoint"))[0];
         document.forms[0].elements['dateCreationClientConjoint'].value = dateCreationClientConjoint.text;   
         
       var dateAncienneteClientConjoint = (client.getElementsByTagName("dateAncienneteClientConjoint"))[0];
         document.forms[0].elements['dateAncienneteClientConjoint'].value = dateAncienneteClientConjoint.text;   
         
       var categorieClientConjoint = (client.getElementsByTagName("categorieClientConjoint"))[0];
         document.forms[0].elements['categorieClientConjoint'].value = categorieClientConjoint.text;   
         
       var typeClientConjoint = (client.getElementsByTagName("typeClientConjoint"))[0];
         document.forms[0].elements['typeClientConjoint'].value = typeClientConjoint.text;   
         
       var gammeClientConjoint = (client.getElementsByTagName("gammeClientConjoint"))[0];
         document.forms[0].elements['gammeClientConjoint'].value = gammeClientConjoint.text;   
         
       var segementClientConjoint = (client.getElementsByTagName("segementClientConjoint"))[0];
         document.forms[0].elements['segementClientConjoint'].value = segementClientConjoint.text;   
         
       var adresse1ClientConjoint = (client.getElementsByTagName("adresse1ClientConjoint"))[0];
         document.forms[0].elements['adresse1ClientConjoint'].value = adresse1ClientConjoint.text;   
         
       var adresse2ClientConjoint = (client.getElementsByTagName("adresse2ClientConjoint"))[0];    
         document.forms[0].elements['adresse2ClientConjoint'].value = adresse2ClientConjoint.text;   
         
       var adresse3ClientConjoint = (client.getElementsByTagName("adresse3ClientConjoint"))[0];
         document.forms[0].elements['adresse3ClientConjoint'].value = adresse3ClientConjoint.text;   
         
       var paysClientConjoint = (client.getElementsByTagName("paysClientConjoint"))[0];
         document.forms[0].elements['paysClientConjoint'].value = paysClientConjoint.text;   
         
       var villeClientConjoint = (client.getElementsByTagName("villeClientConjoint"))[0];
         document.forms[0].elements['villeClientConjoint'].value = villeClientConjoint.text;   
         
       var codePostalClientConjoint = (client.getElementsByTagName("codePostalClientConjoint"))[0];
         document.forms[0].elements['codePostalClientConjoint'].value = codePostalClientConjoint.text;   
         
       var emailClientConjoint = (client.getElementsByTagName("emailClientConjoint"))[0];
         document.forms[0].elements['emailClientConjoint'].value = emailClientConjoint.text;   
         
       var telephoneBureauClientConjoint = (client.getElementsByTagName("telephoneBureauClientConjoint"))[0];
         document.forms[0].elements['telephoneBureauClientConjoint'].value = telephoneBureauClientConjoint.text;   
         
       var faxClientConjoint = (client.getElementsByTagName("faxClientConjoint"))[0];
         document.forms[0].elements['faxClientConjoint'].value = faxClientConjoint.text;   
         
       var titreCiviliteConjoint = (client.getElementsByTagName("titreCiviliteConjoint"))[0];
         document.forms[0].elements['titreCiviliteConjoint'].value = titreCiviliteConjoint.text;   
       
       var nomClientConjoint = (client.getElementsByTagName("nomClientConjoint"))[0];
         document.forms[0].elements['nomClientConjoint'].value = nomClientConjoint.text;   
         
       var prenomClientConjoint = (client.getElementsByTagName("prenomClientConjoint"))[0];
         document.forms[0].elements['prenomClientConjoint'].value = prenomClientConjoint.text;   
         
       var dateNaissanceClientConjoint = (client.getElementsByTagName("dateNaissanceClientConjoint"))[0];
         document.forms[0].elements['dateNaissanceClientConjoint'].value = dateNaissanceClientConjoint.text;   
         
       var situationFamilialeClientConjoint = (client.getElementsByTagName("situationFamilialeClientConjoint"))[0];
         document.forms[0].elements['situationFamilialeClientConjoint'].value = situationFamilialeClientConjoint.text;   
         
       var nombreEnfantsClientConjoint = (client.getElementsByTagName("nombreEnfantsClientConjoint"))[0];
         document.forms[0].elements['nombreEnfantsClientConjoint'].value = nombreEnfantsClientConjoint.text;   
                
       var professionClientConjoint = (client.getElementsByTagName("professionClientConjoint"))[0];
         document.forms[0].elements['professionClientConjoint'].value = professionClientConjoint.text;   
         
       var revenuAnnuelClientConjoint = (client.getElementsByTagName("revenuAnnuelClientConjoint"))[0];
         document.forms[0].elements['revenuAnnuelClientConjoint'].value = revenuAnnuelClientConjoint.text;   
         
       var employeurClientConjoint = (client.getElementsByTagName("employeurClientConjoint"))[0];
         document.forms[0].elements['employeurClientConjoint'].value = employeurClientConjoint.text;   
         
       var telephoneDomicileClientConjoint = (client.getElementsByTagName("telephoneDomicileClientConjoint"))[0];
         document.forms[0].elements['telephoneDomicileClientConjoint'].value = telephoneDomicileClientConjoint.text;   
         
       var gsmClientConjoint = (client.getElementsByTagName("gsmClientConjoint"))[0];
         document.forms[0].elements['gsmClientConjoint'].value = gsmClientConjoint.text;   
         
       var poidsClientConjoint = (client.getElementsByTagName("poidsClientConjoint"))[0];
         document.forms[0].elements['poidsClientConjoint'].value = poidsClientConjoint.text;   
         
       var tailleClientConjoint = (client.getElementsByTagName("tailleClientConjoint"))[0];
         document.forms[0].elements['tailleClientConjoint'].value = tailleClientConjoint.text;   
         
       		 if(document.forms[0].elements['idConjoint']!= null && document.forms[0].elements['idConjoint'].value !=''){
			      document.forms[0].elements['typePieceIdentiteClientConjoint'].disabled = true;
			      document.forms[0].elements['numeroPieceIdentiteClientConjoint'].disabled = true;
			      
			      if(document.forms[0].elements['nomClientConjoint']!=null) {
			      	  document.forms[0].elements['nomClientConjoint'].disabled = true;
			      }
			      if(document.forms[0].elements['prenomClientConjoint']!=null){
				      document.forms[0].elements['prenomClientConjoint'].disabled = true;
			      }
			      if(document.forms[0].elements['dateNaissanceClientConjoint']!=null){   
			          document.forms[0].elements['dateNaissanceClientConjoint'].disabled = true;
			      }   
			  }
			  
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// Fin conjoint


//*********************
// personne assuré
// appel dans la page pour chercher une personne
function appelAjaxPersonne() {   
    if(document.forms[0].elements['typePieceIdentitePersonne'].value != '' &&  document.forms[0].elements['numeroPieceIdentitePers'].value != '') {
    	url = 'ajaxPersonneAction.do?tCIN=' + document.forms[0].elements['typePieceIdentitePersonne'].value + '&nCIN=' + document.forms[0].elements['numeroPieceIdentitePers'].value; 
		ajaxCallRemotePersonnePage(url);
	}else{
		alert('Veuillez fournir le type et le numero de la CIN');
	}
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePersonnePage(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangePersonne;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangePersonne;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangePersonne() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       var rxml = req.responseXML;  
       var client = rxml.documentElement;
        
       var idPersonne = (client.getElementsByTagName("idPersonne"))[0];
       
        if(idPersonne.text == 0){
         alert('Client inexistant dans la base !!');
           
         document.forms[0].elements['idPersonne'].value = '';

	     document.forms[0].elements['numeroPersonne'].value = '';   
	     
         document.forms[0].elements['categoriePers'].value = '';   
         
         document.forms[0].elements['typePers'].value = '';  
         
         document.forms[0].elements['gammePers'].value = '';   
         
         document.forms[0].elements['segementPers'].value = '';   
                  
         document.forms[0].elements['adresse1Personne'].value = '';   
         
         document.forms[0].elements['adresse2Personne'].value = '';  
         
         document.forms[0].elements['adresse3Personne'].value = '';  
  
         document.forms[0].elements['titreCivilitePers'].value = ''; 
                  
         document.forms[0].elements['nomPers'].value = '';   
         
         document.forms[0].elements['prenomPers'].value = '';  
         
         document.forms[0].elements['dateNaissancePersonne'].value = '';  
         
         document.forms[0].elements['paysPers'].value = '';  
         
         document.forms[0].elements['villePers'].value = '';  
         
         document.forms[0].elements['codePostalPers'].value = '';   
         
         document.forms[0].elements['situationFamilialePers'].value = '';;            
         return;
        }
        
       document.forms[0].elements['idPersonne'].value = idPersonne.text;
        
       var numeroPersonne = (client.getElementsByTagName("numeroPersonne"))[0];
	     document.forms[0].elements['numeroPersonne'].value = numeroPersonne.text;   
 
        var categoriePers = (client.getElementsByTagName("categoriePers"))[0];
         document.forms[0].elements['categoriePers'].value = categoriePers.text;   
         
       var typePers = (client.getElementsByTagName("typePers"))[0];
         document.forms[0].elements['typePers'].value = typePers.text;   
         
       var gammePers = (client.getElementsByTagName("gammePers"))[0];
         document.forms[0].elements['gammePers'].value = gammePers.text;   
         
       var segementPers = (client.getElementsByTagName("segementPers"))[0];
         document.forms[0].elements['segementPers'].value = segementPers.text;   
                     
       var adresse1Personne = (client.getElementsByTagName("adresse1Personne"))[0];
         document.forms[0].elements['adresse1Personne'].value = adresse1Personne.text;   
         
       var adresse2Personne = (client.getElementsByTagName("adresse2Personne"))[0];    
         document.forms[0].elements['adresse2Personne'].value = adresse2Personne.text;   
         
       var adresse3Personne = (client.getElementsByTagName("adresse3Personne"))[0];
         document.forms[0].elements['adresse3Personne'].value = adresse3Personne.text;   
    
       var paysPers = (client.getElementsByTagName("paysPers"))[0];
         document.forms[0].elements['paysPers'].value = paysPers.text;   
         
       var villePers = (client.getElementsByTagName("villePers"))[0];
         document.forms[0].elements['villePers'].value = villePers.text;   
         
       var codePostalPers = (client.getElementsByTagName("codePostalPers"))[0];
         document.forms[0].elements['codePostalPers'].value = codePostalPers.text;   
 
        var titreCivilitePers = (client.getElementsByTagName("titreCivilitePers"))[0];
         document.forms[0].elements['titreCivilitePers'].value = titreCivilitePers.text;   
 
       var nomPers = (client.getElementsByTagName("nomPers"))[0];
         document.forms[0].elements['nomPers'].value = nomPers.text;   
         
       var prenomPers = (client.getElementsByTagName("prenomPers"))[0];
         document.forms[0].elements['prenomPers'].value = prenomPers.text;   

	   var dateNaissancePersonne = (client.getElementsByTagName("dateNaissancePersonne"))[0];
         document.forms[0].elements['dateNaissancePersonne'].value = dateNaissancePersonne.text;   

       var situationFamilialePers = (client.getElementsByTagName("situationFamilialePers"))[0];
         document.forms[0].elements['situationFamilialePers'].value = situationFamilialePers.text;   
 			  
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// Fin Personne assuré










//*************************





// appel appelAjaxPrimePrevoyance
function appelAjaxPrimePrevoyance() {  

	if(document.forms[0].elements['optionBaremePrevoyance'] == null || document.forms[0].elements['optionBaremePrevoyance'].value == ''){
		return;
	}
    
    dateNaissance = '';
    if(document.forms[0].elements['dateNaissanceClientAdherent'] != null) {
		dateNaissance = document.forms[0].elements['dateNaissanceClientAdherent'].value;
	}else{
    	dateNaissance = document.forms[0].elements['dateNaissanceClientSouscripteur'].value;
	}
    
    avecConjoint = 'false';
    if(document.forms[0].elements['avecConjoint'] != null && document.forms[0].elements['avecConjoint'].checked == true) {
      avecConjoint = 'true';
    }
     
    url = 'ajaxPrimePrevoyanceAction.do?idOption=' + document.forms[0].elements['optionBaremePrevoyance'].value + '&dateNaissance=' + dateNaissance + '&dateSouscription=' + document.forms[0].elements['dateSouscription'].value + '&categorieCompte=' + document.forms[0].elements['compte2'].value + '&avecConjoint=' + avecConjoint; 
	ajaxCallRemotePagePrimePrevoyance(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePagePrimePrevoyance(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangePrimePrevoyance;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangePrimePrevoyance;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 
 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangePrimePrevoyance() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;   
       document.forms[0].elements['primePrevoyance'].value = xml;
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// Fin Prime Prevoyance




//**************************************************


// appel appelAjaxPrimePrevoyancePers
function appelAjaxPrimePrevoyancePers() {  

	if(document.forms[0].elements['optionBaremePrevoyancePers'] == null || document.forms[0].elements['optionBaremePrevoyancePers'].value == ''){
		return;
	}
    
    dateNaissance = '';
    if(document.forms[0].elements['dateNaissanceClientAdherent'] != null) {
		dateNaissance = document.forms[0].elements['dateNaissanceClientAdherent'].value;
	}else{
    	dateNaissance = document.forms[0].elements['dateNaissanceClientSouscripteur'].value;
	}
    
    avecConjoint = 'false';
    if(document.forms[0].elements['avecConjoint'] != null && document.forms[0].elements['avecConjoint'].checked == true) {
      avecConjoint = 'true';
    }
     
    url = 'ajaxPrimePrevoyancePersAction.do?idOption=' + document.forms[0].elements['optionBaremePrevoyancePers'].value + '&dateNaissance=' + dateNaissance + '&dateSouscription=' + document.forms[0].elements['dateSouscription'].value + '&categorieCompte=' + document.forms[0].elements['compte2'].value + '&avecConjoint=' + avecConjoint; 
	ajaxCallRemotePagePrimePrevoyancePers(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePagePrimePrevoyancePers(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangePrimePrevoyancePers;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangePrimePrevoyancePers;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 
 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangePrimePrevoyancePers() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;   
       document.forms[0].elements['primePrevoyancePers'].value = xml;
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// Fin Prime Prevoyance







//**************************************************

// appel appelAjaxPrimeDommagesAvecActivite
function appelAjaxPrimeDommagesAvecActivite() {  

	if(document.forms[0].elements['activiteDommages'] == null || document.forms[0].elements['activiteDommages'].value == ''){
		return;
	}
    
    capitalMob = document.forms[0].elements['capitalMobilier'].value;
    capitalImmob = document.forms[0].elements['capitalImmobilier'].value;   
     
    url = 'ajaxPrimeDommagesAvecActiviteAction.do?idActivite=' + document.forms[0].elements['activiteDommages'].value + '&capitalMob=' + capitalMob + '&capitalImmob=' + capitalImmob; 
	ajaxCallRemotePagePrimeDommagesAvecActivite(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePagePrimeDommagesAvecActivite(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangePrimeDommagesAvecActivite;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangePrimeDommagesAvecActivite;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangePrimeDommagesAvecActivite() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;   
       document.forms[0].elements['primeDommages'].value = xml;
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// Fin Prime Dommages avec activite



// appel appelAjaxPrimeDommagesSansActivite
function appelAjaxPrimeDommagesSansActivite() {  

	if(document.forms[0].elements['optionBaremeDommages'] == null || document.forms[0].elements['optionBaremeDommages'].value == ''){
		return;
	}
    
    categorieCompte = document.forms[0].elements['compte3'].value;  
     
    url = 'ajaxPrimeDommagesSansActiviteAction.do?idOption=' + document.forms[0].elements['optionBaremeDommages'].value + '&categorieCompte=' + categorieCompte; 
	ajaxCallRemotePagePrimeDommagesSansActivite(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePagePrimeDommagesSansActivite(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangePrimeDommagesSansActivite;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangePrimeDommagesSansActivite;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangePrimeDommagesSansActivite() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;   
       document.forms[0].elements['primeDommages'].value = xml;
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// Fin Prime Dommages sans activite



function onChangeTypeRemplacement() {
   if(document.forms[0].elements['typeRemplacement'].value != '1') {
      document.forms[0].elements['contrat1'].value ='';
      document.forms[0].elements['contrat1'].disabled = true;
      document.forms[0].elements['contrat2'].value ='';
      document.forms[0].elements['contrat2'].disabled = true;
      document.forms[0].elements['contrat3'].value ='';
      document.forms[0].elements['contrat3'].disabled = true;
   }
	else {
      document.forms[0].elements['contrat1'].disabled = false;
      document.forms[0].elements['contrat2'].disabled = false;
      document.forms[0].elements['contrat3'].disabled = false;
   }      
}



function initSouscripteur()
{
  if(document.forms[0].elements['idSouscripteur']!= null && document.forms[0].elements['idSouscripteur'].value !='' && document.forms[0].elements['idSouscripteur'].value !='0'){
      document.forms[0].elements['typePieceIdentiteClientSouscripteur'].disabled = true;
      document.forms[0].elements['numeroPieceIdentiteClientSouscripteur'].disabled = true;
      
      if(document.forms[0].elements['categorieClientSouscripteur']!=null) {
      	  document.forms[0].elements['categorieClientSouscripteur'].disabled = true;
      }
      if(document.forms[0].elements['nomClientSouscripteur']!=null) {
      	  document.forms[0].elements['nomClientSouscripteur'].disabled = true;
      }
      if(document.forms[0].elements['prenomClientSouscripteur']!=null){
	      document.forms[0].elements['prenomClientSouscripteur'].disabled = true;
      }
      if(document.forms[0].elements['dateNaissanceClientSouscripteur']!=null){   
          document.forms[0].elements['dateNaissanceClientSouscripteur'].disabled = true;
      }       
  }
}

function initAdherent()
{
  if(document.forms[0].elements['idAdherent']!= null && document.forms[0].elements['idAdherent'].value !='' && document.forms[0].elements['idAdherent'].value !='0'){
      document.forms[0].elements['typePieceIdentiteClientAdherent'].disabled = true;
      document.forms[0].elements['numeroPieceIdentiteClientAdherent'].disabled = true;
            
  /*    if(document.forms[0].elements['categorieClientAdherent']!=null) {
      	  document.forms[0].elements['categorieClientAdherent'].disabled = true;
      }      */
      if(document.forms[0].elements['nomClientAdherent']!=null && document.forms[0].elements['nomClientAdherent'].value!='') {
      	  document.forms[0].elements['nomClientAdherent'].disabled = true;
      }
      if(document.forms[0].elements['prenomClientAdherent']!=null && document.forms[0].elements['prenomClientAdherent'].value!=''){
	      document.forms[0].elements['prenomClientAdherent'].disabled = true;
      }
      if(document.forms[0].elements['dateNaissanceClientAdherent']!=null && document.forms[0].elements['dateNaissanceClientAdherent'].value!=''){   
          document.forms[0].elements['dateNaissanceClientAdherent'].disabled = true;
      }      
      if(document.forms[0].elements['rechercherAdherent']!=null){
      	document.forms[0].elements['rechercherAdherent'].disabled = true;    
      }   
  }
}

function leftpad(theItem, thePad, minSize) {
	newItem = new String(document.forms[0].elements[theItem].value);
	if(newItem.length == 0) { 
		return; 
	}
	while (newItem.length < minSize) {
		newItem = thePad + newItem;
	}
	document.forms[0].elements[theItem].value = newItem;
}

function onChangeCapiatlOrDuree() {
	
	if(document.forms[0].elements['tauxPrimePrevoyance']!= null) {
		document.forms[0].elements['tauxPrimePrevoyance'].value = '';
	}		
	
	if(document.forms[0].elements['primePrevoyance']!= null) {
		document.forms[0].elements['primePrevoyance'].value = '';
	}
	
	if(document.forms[0].elements['surprimePrevoyance']!= null) {
		document.forms[0].elements['surprimePrevoyance'].value = '';
	}
	
	if(document.forms[0].elements['primeTotalePrevoyance']!= null) {
		document.forms[0].elements['primeTotalePrevoyance'].value = '';
	}
}


// appel appelAjaxAttestations
function appelAjaxAttestations() { 
	deleteAllRowspieceJustificative();   
    url = 'ajaxAttestationsAction.do?idTypeSinistre=' + document.forms[0].elements['idTypeSinistre'].value; 
	ajaxCallRemotePageAttestations(url);
}

function appelAjaxAttestationsByTypePrestation() { 
	deleteAllRowspieceJustificative();   
    url = 'ajaxAttestationsByTypePrestationAction.do?typeSinistre=' + document.forms[0].elements['typePrestation'].value; 
	ajaxCallRemotePageAttestations(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageAttestations(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeAttestations;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeAttestations;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeAttestations() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;      
       document.getElementById("listeAttestations").innerHTML = xml;
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}



// PRMOTIONS

// appel appelAjaxEvenementPrm
function appelAjaxEvenementPrm() {
    doEmptyPaliers(); 
    if(document.forms[0].elements['evenement'].value == '') {    
       document.forms[0].elements['idReduction'].value = '';    
       document.forms[0].elements['idLigneReduction'].value = '';
       return;
    }

    url = 'ajaxEvenementPrmAction.do?idEvenement=' + document.forms[0].elements['evenement'].value
           + '&idPromotion=' + document.forms[0].elements['idPromotion'].value
           + '&produit=' + document.forms[0].elements['produit'].value; 
	ajaxCallRemotePageEvenementPrm(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageEvenementPrm(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeEvenementPrm;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeEvenementPrm;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeEvenementPrm() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       var rxml = req.responseXML;  
       var reduction = rxml.documentElement;        
       var idPromotion = (reduction.getElementsByTagName("idPromotion"))[0];
                document.forms[0].elements['idPromotion'].value = idPromotion.text;

       var codePromotion = (reduction.getElementsByTagName("codePromotion"))[0];
                document.forms[0].elements['code'].value = codePromotion.text;
                
       var idReduction = (reduction.getElementsByTagName("idReduction"))[0];
       			document.forms[0].elements['idReduction'].value = idReduction.text;
       			
       var listeMontantsAReduire = (reduction.getElementsByTagName("listeMontantsAReduire"))[0];
              document.getElementById("listeMontantsAReduire").innerHTML = listeMontantsAReduire.xml;
       
      } else {
        alert("Un probleme est survenu: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}



// creation de la ligne de reduction 
function onChangeMontantAReduire() {
    doEmptyPaliers(); 
    if(document.forms[0].elements['montantAReduire'].value == '') {
       document.forms[0].elements['idLigneReduction'].value = '';
       return;
    }
    url = 'ajaxMontantPrmAction.do?idReduction=' + document.forms[0].elements['idReduction'].value 
            + '&montantAReduire=' + document.forms[0].elements['montantAReduire'].value; 
                 
	ajaxCallRemotePageMontantPrm(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageMontantPrm(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeMontantPrm;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeMontantPrm;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeMontantPrm() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);       

       var rxml = req.responseXML;  
       var ligneReduction = rxml.documentElement; 
              
       afficherLigneReduction(ligneReduction);
                
                
      } else {
        alert("Un probleme est survenu: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}




// appel appelAjaxEnregistrerLigneReduction
function appelAjaxEnregistrerLigneReduction() { 
    if(document.forms[0].elements['idLigneReduction'].value == ''){
        alert('Veuillez choisir un evenement et un type de montant');
    	return;
    }      
 
    url = 'ajaxEnregistrerLRAction.do?idLigneReduction=' + document.forms[0].elements['idLigneReduction'].value

           + '&idReduction=' + document.forms[0].elements['idReduction'].value
           + '&montantAReduire=' + document.forms[0].elements['montantAReduire'].value           
           + '&nombreMois=' + document.forms[0].elements['nombreMois'].value
           + '&dureeCotisationsContinues=' + document.forms[0].elements['dureeCotisationsContinues'].value           
           + '&nombreSuspensionsPaiementMax=' + document.forms[0].elements['nombreSuspensionsPaiementMax'].value           
           + '&dureeTotaleSuspensionsPaiementMax=' + document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value           
           
           + '&idPalier1=' + document.forms[0].elements['idPalier1'].value            
           + '&tR1=' + document.forms[0].elements['tR1'].value                      
           + '&vMin1=' + document.forms[0].elements['vMin1'].value     
           + '&vMax1=' + document.forms[0].elements['vMax1'].value                           
           + '&valR1=' + document.forms[0].elements['valR1'].value                

           + '&idPalier2=' + document.forms[0].elements['idPalier2'].value 
           + '&tR2=' + document.forms[0].elements['tR2'].value                      
           + '&vMin2=' + document.forms[0].elements['vMin2'].value     
           + '&vMax2=' + document.forms[0].elements['vMax2'].value                           
           + '&valR2=' + document.forms[0].elements['valR2'].value                
           
           + '&idPalier3=' + document.forms[0].elements['idPalier3'].value            
           + '&tR3=' + document.forms[0].elements['tR3'].value                      
           + '&vMin3=' + document.forms[0].elements['vMin3'].value     
           + '&vMax3=' + document.forms[0].elements['vMax3'].value                           
           + '&valR3=' + document.forms[0].elements['valR3'].value                
           
           + '&idPalier4=' + document.forms[0].elements['idPalier4'].value            
           + '&tR4=' + document.forms[0].elements['tR4'].value                      
           + '&vMin4=' + document.forms[0].elements['vMin4'].value     
           + '&vMax4=' + document.forms[0].elements['vMax4'].value                           
           + '&valR4=' + document.forms[0].elements['valR4'].value                
           
           + '&idPalier5=' + document.forms[0].elements['idPalier5'].value            
           + '&tR5=' + document.forms[0].elements['tR5'].value                      
           + '&vMin5=' + document.forms[0].elements['vMin5'].value     
           + '&vMax5=' + document.forms[0].elements['vMax5'].value                           
           + '&valR5=' + document.forms[0].elements['valR5'].value                
           
           + '&idPalier6=' + document.forms[0].elements['idPalier6'].value            
           + '&tR6=' + document.forms[0].elements['tR6'].value                      
           + '&vMin6=' + document.forms[0].elements['vMin6'].value     
           + '&vMax6=' + document.forms[0].elements['vMax6'].value                           
           + '&valR6=' + document.forms[0].elements['valR6'].value                
           
           + '&idPalier7=' + document.forms[0].elements['idPalier7'].value            
           + '&tR7=' + document.forms[0].elements['tR7'].value                      
           + '&vMin7=' + document.forms[0].elements['vMin7'].value     
           + '&vMax7=' + document.forms[0].elements['vMax7'].value                           
           + '&valR7=' + document.forms[0].elements['valR7'].value; 
	ajaxCallRemotePageEnregistrerLRPrm(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageEnregistrerLRPrm(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeEnregistrerLRPrm;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeEnregistrerLRPrm;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "appelAjaxEnregistrerLigneReduction"
function processStateChangeEnregistrerLRPrm() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
                                
       doEmptyPaliers();
       var rxml = req.responseXML;  
       var ligneReduction = rxml.documentElement; 
              
       afficherLigneReduction(ligneReduction);
        
      } else {
        alert("Un probleme est survenu : " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}


// Vider les les paliers de reduction

function doEmptyPaliers(){
	document.forms[0].elements['nombreMois'].value = '';
    document.forms[0].elements['dureeCotisationsContinues'].value = '';          
    document.forms[0].elements['nombreSuspensionsPaiementMax'].value = '';          
    document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value = '';          
           
    document.forms[0].elements['idPalier1'].value = '';           
    document.forms[0].elements['tR1'].value = '';                     
    document.forms[0].elements['vMin1'].value = '';    
    document.forms[0].elements['vMax1'].value = '';                          
    document.forms[0].elements['valR1'].value = '';              

    document.forms[0].elements['idPalier2'].value = '';
    document.forms[0].elements['tR2'].value = '';                     
    document.forms[0].elements['vMin2'].value = '';    
    document.forms[0].elements['vMax2'].value = '';                          
    document.forms[0].elements['valR2'].value = '';               
           
    document.forms[0].elements['idPalier3'].value = '';           
    document.forms[0].elements['tR3'].value = '';                     
    document.forms[0].elements['vMin3'].value = '';    
    document.forms[0].elements['vMax3'].value = '';                           
    document.forms[0].elements['valR3'].value = '';               
           
    document.forms[0].elements['idPalier4'].value = '';           
    document.forms[0].elements['tR4'].value = '';                     
    document.forms[0].elements['vMin4'].value = '';    
    document.forms[0].elements['vMax4'].value = '';                          
    document.forms[0].elements['valR4'].value = '';              
           
    document.forms[0].elements['idPalier5'].value = '';           
    document.forms[0].elements['tR5'].value = '';                     
    document.forms[0].elements['vMin5'].value = '';    
    document.forms[0].elements['vMax5'].value = '';                         
    document.forms[0].elements['valR5'].value = '';               
           
    document.forms[0].elements['idPalier6'].value = '';           
    document.forms[0].elements['tR6'].value = '';                     
    document.forms[0].elements['vMin6'].value = '';    
    document.forms[0].elements['vMax6'].value = '';                          
    document.forms[0].elements['valR6'].value = '';               
           
    document.forms[0].elements['idPalier7'].value = '';           
    document.forms[0].elements['tR7'].value = '';                     
    document.forms[0].elements['vMin7'].value = '';    
    document.forms[0].elements['vMax7'].value = '';                          
    document.forms[0].elements['valR7'].value = ''; 
}


function afficherLigneReduction(ligneReduction){
       var idLigneReduction = (ligneReduction.getElementsByTagName("idLigneReduction"))[0];
                document.forms[0].elements['idLigneReduction'].value = idLigneReduction.text; 

       var nombreMois = (ligneReduction.getElementsByTagName("nombreMois"))[0];
       if(nombreMois != null) {
       	document.forms[0].elements['nombreMois'].value = nombreMois.text; 
       }
       
       var dureeCotisationsContinues = (ligneReduction.getElementsByTagName("dureeCotisationsContinues"))[0];
       if(dureeCotisationsContinues != null) {
       	document.forms[0].elements['dureeCotisationsContinues'].value = dureeCotisationsContinues.text; 
       }
       var nombreSuspensionsPaiementMax = (ligneReduction.getElementsByTagName("nombreSuspensionsPaiementMax"))[0];
       if(nombreSuspensionsPaiementMax != null) {
       	document.forms[0].elements['nombreSuspensionsPaiementMax'].value = nombreSuspensionsPaiementMax.text; 
       }
       var dureeTotaleSuspensionsPaiementMax = (ligneReduction.getElementsByTagName("dureeTotaleSuspensionsPaiementMax"))[0];
       if(dureeTotaleSuspensionsPaiementMax != null) {
       	document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value = dureeTotaleSuspensionsPaiementMax.text; 
       }       

       // palier1
       var idPalier1 = (ligneReduction.getElementsByTagName("idPalier1"))[0];
       if(idPalier1 != null) {
       	document.forms[0].elements['idPalier1'].value = idPalier1.text; 
       	
       	var tR1 = (ligneReduction.getElementsByTagName("tR1"))[0];
                document.forms[0].elements['tR1'].value = tR1.text;        	
       	
       	var vMin1 = (ligneReduction.getElementsByTagName("vMin1"))[0];
                document.forms[0].elements['vMin1'].value = vMin1.text;
                
       	var vMax1 = (ligneReduction.getElementsByTagName("vMax1"))[0];
                document.forms[0].elements['vMax1'].value = vMax1.text;
                
       	var valR1 = (ligneReduction.getElementsByTagName("valR1"))[0];
                document.forms[0].elements['valR1'].value = valR1.text;                                       	
       }        
        
       // palier2
       var idPalier2 = (ligneReduction.getElementsByTagName("idPalier2"))[0];
       if(idPalier2 != null) {
       	document.forms[0].elements['idPalier2'].value = idPalier2.text; 
       	
       	var tR2 = (ligneReduction.getElementsByTagName("tR2"))[0];
                document.forms[0].elements['tR2'].value = tR2.text;        	
       	
       	var vMin2 = (ligneReduction.getElementsByTagName("vMin2"))[0];
                document.forms[0].elements['vMin2'].value = vMin2.text;
                
       	var vMax2 = (ligneReduction.getElementsByTagName("vMax2"))[0];
                document.forms[0].elements['vMax2'].value = vMax2.text;
                
       	var valR2 = (ligneReduction.getElementsByTagName("valR2"))[0];
                document.forms[0].elements['valR2'].value = valR2.text;                                       	
       }
       
       // palier3
       var idPalier3 = (ligneReduction.getElementsByTagName("idPalier3"))[0];
       if(idPalier3 != null) {
       	document.forms[0].elements['idPalier3'].value = idPalier3.text; 
       	
       	var tR3 = (ligneReduction.getElementsByTagName("tR3"))[0];
                document.forms[0].elements['tR3'].value = tR3.text;        	
       	
       	var vMin3 = (ligneReduction.getElementsByTagName("vMin3"))[0];
                document.forms[0].elements['vMin3'].value = vMin3.text;
                
       	var vMax3 = (ligneReduction.getElementsByTagName("vMax3"))[0];
                document.forms[0].elements['vMax3'].value = vMax3.text;
                
       	var valR3 = (ligneReduction.getElementsByTagName("valR3"))[0];
                document.forms[0].elements['valR3'].value = valR3.text;                                       	
       }
       
       // palier4
       var idPalier4 = (ligneReduction.getElementsByTagName("idPalier4"))[0];
       if(idPalier4 != null) {
       	document.forms[0].elements['idPalier4'].value = idPalier4.text; 
       	
       	var tR4 = (ligneReduction.getElementsByTagName("tR4"))[0];
                document.forms[0].elements['tR4'].value = tR4.text;        	
       	
       	var vMin4 = (ligneReduction.getElementsByTagName("vMin4"))[0];
                document.forms[0].elements['vMin4'].value = vMin4.text;
                
       	var vMax4 = (ligneReduction.getElementsByTagName("vMax4"))[0];
                document.forms[0].elements['vMax4'].value = vMax4.text;
                
       	var valR4 = (ligneReduction.getElementsByTagName("valR4"))[0];
                document.forms[0].elements['valR4'].value = valR4.text;                                       	
       }       
              
       // palier5
       var idPalier5 = (ligneReduction.getElementsByTagName("idPalier5"))[0];
       if(idPalier5 != null) {
       	document.forms[0].elements['idPalier5'].value = idPalier5.text; 
       	
       	var tR5 = (ligneReduction.getElementsByTagName("tR5"))[0];
                document.forms[0].elements['tR5'].value = tR5.text;        	
       	
       	var vMin5 = (ligneReduction.getElementsByTagName("vMin5"))[0];
                document.forms[0].elements['vMin5'].value = vMin5.text;
                
       	var vMax5 = (ligneReduction.getElementsByTagName("vMax5"))[0];
                document.forms[0].elements['vMax5'].value = vMax5.text;
                
       	var valR5 = (ligneReduction.getElementsByTagName("valR5"))[0];
                document.forms[0].elements['valR5'].value = valR5.text;                                       	
       }

       // palier6
       var idPalier6 = (ligneReduction.getElementsByTagName("idPalier6"))[0];
       if(idPalier6 != null) {
       	document.forms[0].elements['idPalier6'].value = idPalier6.text; 
       	
       	var tR6 = (ligneReduction.getElementsByTagName("tR6"))[0];
                document.forms[0].elements['tR6'].value = tR6.text;        	
       	
       	var vMin6 = (ligneReduction.getElementsByTagName("vMin6"))[0];
                document.forms[0].elements['vMin6'].value = vMin6.text;
                
       	var vMax6 = (ligneReduction.getElementsByTagName("vMax6"))[0];
                document.forms[0].elements['vMax6'].value = vMax6.text;
                
       	var valR6 = (ligneReduction.getElementsByTagName("valR6"))[0];
                document.forms[0].elements['valR6'].value = valR6.text;                                       	
       }

       // palier7
       var idPalier7 = (ligneReduction.getElementsByTagName("idPalier7"))[0];
       if(idPalier7 != null) {
       	document.forms[0].elements['idPalier7'].value = idPalier7.text; 
       	
       	var tR7 = (ligneReduction.getElementsByTagName("tR7"))[0];
                document.forms[0].elements['tR7'].value = tR7.text;        	
       	
       	var vMin7 = (ligneReduction.getElementsByTagName("vMin7"))[0];
                document.forms[0].elements['vMin7'].value = vMin7.text;
                
       	var vMax7 = (ligneReduction.getElementsByTagName("vMax7"))[0];
                document.forms[0].elements['vMax7'].value = vMax7.text;
                
       	var valR7 = (ligneReduction.getElementsByTagName("valR7"))[0];
                document.forms[0].elements['valR7'].value = valR7.text;                                       	
       }
} 




////////////////////////////////////////////////////////



// CONVENTIONS

// appel appelAjaxEvenementCNV
function appelAjaxEvenementCnv() { 
    doEmptyPaliers(); 
    if(document.forms[0].elements['evenement'].value == '') {    
       document.forms[0].elements['idReductionConvention'].value = '';    
       document.forms[0].elements['idLigneReductionConvention'].value = '';
       return;
    }

    url = 'ajaxEvenementCnvAction.do?idEvenement=' + document.forms[0].elements['evenement'].value
           + '&idConvention=' + document.forms[0].elements['idConvention'].value
           + '&idProduit=' + document.forms[0].elements['produit'].value; 
	ajaxCallRemotePageEvenementCnv(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageEvenementCnv(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeEvenementCnv;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeEvenementCnv;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeEvenementCnv() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       var rxml = req.responseXML;  
       var reductionConvention = rxml.documentElement;        
       var idConvention = (reductionConvention.getElementsByTagName("idConvention"))[0];
                document.forms[0].elements['idConvention'].value = idConvention.text;

       var codeConvention = (reductionConvention.getElementsByTagName("codeConvention"))[0];
                document.forms[0].elements['code'].value = codeConvention.text;
                
       var idReductionConvention = (reductionConvention.getElementsByTagName("idReductionConvention"))[0];
       			document.forms[0].elements['idReductionConvention'].value = idReductionConvention.text;
       			
       var listeMontantsAReduire = (reductionConvention.getElementsByTagName("listeMontantsAReduire"))[0];
              document.getElementById("listeMontantsAReduire").innerHTML = listeMontantsAReduire.xml;
       
      } else {
        alert("Un probleme est survenu: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}



// creation de la ligne de reduction 
function onChangeMontantAReduireCnv() {
    doEmptyPaliers(); 
    if(document.forms[0].elements['montantAReduire'].value == '') {
       document.forms[0].elements['idLigneReductionConvention'].value = '';
       return;
    }
    url = 'ajaxMontantCnvAction.do?idReductionConvention=' + document.forms[0].elements['idReductionConvention'].value 
            + '&montantAReduire=' + document.forms[0].elements['montantAReduire'].value 
            + '&idProduit=' + document.forms[0].elements['produit'].value; 
                 
	ajaxCallRemotePageMontantCnv(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageMontantCnv(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeMontantCnv;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeMontantCnv;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeMontantCnv() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);       

       var rxml = req.responseXML;  
       var ligneReductionConvention = rxml.documentElement; 
              
       afficherLigneReductionConvention(ligneReductionConvention);
                
                
      } else {
        alert("Un probleme est survenu: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}




// appel appelAjaxEnregistrerLigneReductionConvention
function appelAjaxEnregistrerLigneReductionConvention() { 
    if(document.forms[0].elements['idLigneReductionConvention'].value == ''){
        alert('Veuillez choisir un evenement et un type de montant');
        if(document.forms[0].elements['produit'].value == ''){
        alert('Veuillez choisir produit');
        return;
        }
    	return;
    }      
 
    url = 'ajaxEnregistrerLRCAction.do?idLigneReductionConvention=' + document.forms[0].elements['idLigneReductionConvention'].value
 		+ '&idProduit=' + document.forms[0].elements['produit'].value
           + '&idReductionConvention=' + document.forms[0].elements['idReductionConvention'].value
           + '&montantAReduire=' + document.forms[0].elements['montantAReduire'].value           
           + '&nombreMois=' + document.forms[0].elements['nombreMois'].value
           + '&dureeCotisationsContinues=' + document.forms[0].elements['dureeCotisationsContinues'].value           
           + '&nombreSuspensionsPaiementMax=' + document.forms[0].elements['nombreSuspensionsPaiementMax'].value           
           + '&dureeTotaleSuspensionsPaiementMax=' + document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value           
           
           + '&idPalier1=' + document.forms[0].elements['idPalier1'].value            
           + '&tR1=' + document.forms[0].elements['tR1'].value                      
           + '&vMin1=' + document.forms[0].elements['vMin1'].value     
           + '&vMax1=' + document.forms[0].elements['vMax1'].value                           
           + '&valR1=' + document.forms[0].elements['valR1'].value                

           + '&idPalier2=' + document.forms[0].elements['idPalier2'].value 
           + '&tR2=' + document.forms[0].elements['tR2'].value                      
           + '&vMin2=' + document.forms[0].elements['vMin2'].value     
           + '&vMax2=' + document.forms[0].elements['vMax2'].value                           
           + '&valR2=' + document.forms[0].elements['valR2'].value                
           
           + '&idPalier3=' + document.forms[0].elements['idPalier3'].value            
           + '&tR3=' + document.forms[0].elements['tR3'].value                      
           + '&vMin3=' + document.forms[0].elements['vMin3'].value     
           + '&vMax3=' + document.forms[0].elements['vMax3'].value                           
           + '&valR3=' + document.forms[0].elements['valR3'].value                
           
           + '&idPalier4=' + document.forms[0].elements['idPalier4'].value            
           + '&tR4=' + document.forms[0].elements['tR4'].value                      
           + '&vMin4=' + document.forms[0].elements['vMin4'].value     
           + '&vMax4=' + document.forms[0].elements['vMax4'].value                           
           + '&valR4=' + document.forms[0].elements['valR4'].value                
           
           + '&idPalier5=' + document.forms[0].elements['idPalier5'].value            
           + '&tR5=' + document.forms[0].elements['tR5'].value                      
           + '&vMin5=' + document.forms[0].elements['vMin5'].value     
           + '&vMax5=' + document.forms[0].elements['vMax5'].value                           
           + '&valR5=' + document.forms[0].elements['valR5'].value                
           
           + '&idPalier6=' + document.forms[0].elements['idPalier6'].value            
           + '&tR6=' + document.forms[0].elements['tR6'].value                      
           + '&vMin6=' + document.forms[0].elements['vMin6'].value     
           + '&vMax6=' + document.forms[0].elements['vMax6'].value                           
           + '&valR6=' + document.forms[0].elements['valR6'].value                
           
           + '&idPalier7=' + document.forms[0].elements['idPalier7'].value            
           + '&tR7=' + document.forms[0].elements['tR7'].value                      
           + '&vMin7=' + document.forms[0].elements['vMin7'].value     
           + '&vMax7=' + document.forms[0].elements['vMax7'].value                           
           + '&valR7=' + document.forms[0].elements['valR7'].value; 
	ajaxCallRemotePageEnregistrerLRCCnv(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageEnregistrerLRCCnv(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeEnregistrerLRCCnv;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeEnregistrerLRCCnv;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "appelAjaxEnregistrerLigneReduction"
function processStateChangeEnregistrerLRCCnv() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
                                
       doEmptyPaliers();
       var rxml = req.responseXML;  
       var ligneReductionConvention = rxml.documentElement; 
              
       afficherLigneReductionConvention(ligneReductionConvention);
        
      } else {
        alert("Un probleme est survenu : " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}


// Vider les les paliers de reduction

function doEmptyPaliers(){
	document.forms[0].elements['nombreMois'].value = '';
    document.forms[0].elements['dureeCotisationsContinues'].value = '';          
    document.forms[0].elements['nombreSuspensionsPaiementMax'].value = '';          
    document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value = '';          
           
    document.forms[0].elements['idPalier1'].value = '';           
    document.forms[0].elements['tR1'].value = '';                     
    document.forms[0].elements['vMin1'].value = '';    
    document.forms[0].elements['vMax1'].value = '';                          
    document.forms[0].elements['valR1'].value = '';              

    document.forms[0].elements['idPalier2'].value = '';
    document.forms[0].elements['tR2'].value = '';                     
    document.forms[0].elements['vMin2'].value = '';    
    document.forms[0].elements['vMax2'].value = '';                          
    document.forms[0].elements['valR2'].value = '';               
           
    document.forms[0].elements['idPalier3'].value = '';           
    document.forms[0].elements['tR3'].value = '';                     
    document.forms[0].elements['vMin3'].value = '';    
    document.forms[0].elements['vMax3'].value = '';                           
    document.forms[0].elements['valR3'].value = '';               
           
    document.forms[0].elements['idPalier4'].value = '';           
    document.forms[0].elements['tR4'].value = '';                     
    document.forms[0].elements['vMin4'].value = '';    
    document.forms[0].elements['vMax4'].value = '';                          
    document.forms[0].elements['valR4'].value = '';              
           
    document.forms[0].elements['idPalier5'].value = '';           
    document.forms[0].elements['tR5'].value = '';                     
    document.forms[0].elements['vMin5'].value = '';    
    document.forms[0].elements['vMax5'].value = '';                         
    document.forms[0].elements['valR5'].value = '';               
           
    document.forms[0].elements['idPalier6'].value = '';           
    document.forms[0].elements['tR6'].value = '';                     
    document.forms[0].elements['vMin6'].value = '';    
    document.forms[0].elements['vMax6'].value = '';                          
    document.forms[0].elements['valR6'].value = '';               
           
    document.forms[0].elements['idPalier7'].value = '';           
    document.forms[0].elements['tR7'].value = '';                     
    document.forms[0].elements['vMin7'].value = '';    
    document.forms[0].elements['vMax7'].value = '';                          
    document.forms[0].elements['valR7'].value = ''; 
}


function afficherLigneReductionConvention(ligneReductionConvention){
       var idLigneReductionConvention = (ligneReductionConvention.getElementsByTagName("idLigneReductionConvention"))[0];
                document.forms[0].elements['idLigneReductionConvention'].value = idLigneReductionConvention.text; 

       var nombreMois = (ligneReductionConvention.getElementsByTagName("nombreMois"))[0];
       if(nombreMois != null) {
       	document.forms[0].elements['nombreMois'].value = nombreMois.text; 
       }
       
       var dureeCotisationsContinues = (ligneReductionConvention.getElementsByTagName("dureeCotisationsContinues"))[0];
       if(dureeCotisationsContinues != null) {
       	document.forms[0].elements['dureeCotisationsContinues'].value = dureeCotisationsContinues.text; 
       }
       var nombreSuspensionsPaiementMax = (ligneReductionConvention.getElementsByTagName("nombreSuspensionsPaiementMax"))[0];
       if(nombreSuspensionsPaiementMax != null) {
       	document.forms[0].elements['nombreSuspensionsPaiementMax'].value = nombreSuspensionsPaiementMax.text; 
       }
       var dureeTotaleSuspensionsPaiementMax = (ligneReductionConvention.getElementsByTagName("dureeTotaleSuspensionsPaiementMax"))[0];
       if(dureeTotaleSuspensionsPaiementMax != null) {
       	document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value = dureeTotaleSuspensionsPaiementMax.text; 
       }       

       // palier1
       var idPalier1 = (ligneReductionConvention.getElementsByTagName("idPalier1"))[0];
       if(idPalier1 != null) {
       	document.forms[0].elements['idPalier1'].value = idPalier1.text; 
       	
       	var tR1 = (ligneReductionConvention.getElementsByTagName("tR1"))[0];
                document.forms[0].elements['tR1'].value = tR1.text;        	
       	
       	var vMin1 = (ligneReductionConvention.getElementsByTagName("vMin1"))[0];
                document.forms[0].elements['vMin1'].value = vMin1.text;
                
       	var vMax1 = (ligneReductionConvention.getElementsByTagName("vMax1"))[0];
                document.forms[0].elements['vMax1'].value = vMax1.text;
                
       	var valR1 = (ligneReductionConvention.getElementsByTagName("valR1"))[0];
                document.forms[0].elements['valR1'].value = valR1.text;                                       	
       }        
        
       // palier2
       var idPalier2 = (ligneReductionConvention.getElementsByTagName("idPalier2"))[0];
       if(idPalier2 != null) {
       	document.forms[0].elements['idPalier2'].value = idPalier2.text; 
       	
       	var tR2 = (ligneReductionConvention.getElementsByTagName("tR2"))[0];
                document.forms[0].elements['tR2'].value = tR2.text;        	
       	
       	var vMin2 = (ligneReductionConvention.getElementsByTagName("vMin2"))[0];
                document.forms[0].elements['vMin2'].value = vMin2.text;
                
       	var vMax2 = (ligneReductionConvention.getElementsByTagName("vMax2"))[0];
                document.forms[0].elements['vMax2'].value = vMax2.text;
                
       	var valR2 = (ligneReductionConvention.getElementsByTagName("valR2"))[0];
                document.forms[0].elements['valR2'].value = valR2.text;                                       	
       }
       
       // palier3
       var idPalier3 = (ligneReductionConvention.getElementsByTagName("idPalier3"))[0];
       if(idPalier3 != null) {
       	document.forms[0].elements['idPalier3'].value = idPalier3.text; 
       	
       	var tR3 = (ligneReductionConvention.getElementsByTagName("tR3"))[0];
                document.forms[0].elements['tR3'].value = tR3.text;        	
       	
       	var vMin3 = (ligneReductionConvention.getElementsByTagName("vMin3"))[0];
                document.forms[0].elements['vMin3'].value = vMin3.text;
                
       	var vMax3 = (ligneReductionConvention.getElementsByTagName("vMax3"))[0];
                document.forms[0].elements['vMax3'].value = vMax3.text;
                
       	var valR3 = (ligneReductionConvention.getElementsByTagName("valR3"))[0];
                document.forms[0].elements['valR3'].value = valR3.text;                                       	
       }
       
       // palier4
       var idPalier4 = (ligneReductionConvention.getElementsByTagName("idPalier4"))[0];
       if(idPalier4 != null) {
       	document.forms[0].elements['idPalier4'].value = idPalier4.text; 
       	
       	var tR4 = (ligneReductionConvention.getElementsByTagName("tR4"))[0];
                document.forms[0].elements['tR4'].value = tR4.text;        	
       	
       	var vMin4 = (ligneReductionConvention.getElementsByTagName("vMin4"))[0];
                document.forms[0].elements['vMin4'].value = vMin4.text;
                
       	var vMax4 = (ligneReductionConvention.getElementsByTagName("vMax4"))[0];
                document.forms[0].elements['vMax4'].value = vMax4.text;
                
       	var valR4 = (ligneReductionConvention.getElementsByTagName("valR4"))[0];
                document.forms[0].elements['valR4'].value = valR4.text;                                       	
       }       
              
       // palier5
       var idPalier5 = (ligneReductionConvention.getElementsByTagName("idPalier5"))[0];
       if(idPalier5 != null) {
       	document.forms[0].elements['idPalier5'].value = idPalier5.text; 
       	
       	var tR5 = (ligneReductionConvention.getElementsByTagName("tR5"))[0];
                document.forms[0].elements['tR5'].value = tR5.text;        	
       	
       	var vMin5 = (ligneReductionConvention.getElementsByTagName("vMin5"))[0];
                document.forms[0].elements['vMin5'].value = vMin5.text;
                
       	var vMax5 = (ligneReductionConvention.getElementsByTagName("vMax5"))[0];
                document.forms[0].elements['vMax5'].value = vMax5.text;
                
       	var valR5 = (ligneReductionConvention.getElementsByTagName("valR5"))[0];
                document.forms[0].elements['valR5'].value = valR5.text;                                       	
       }

       // palier6
       var idPalier6 = (ligneReductionConvention.getElementsByTagName("idPalier6"))[0];
       if(idPalier6 != null) {
       	document.forms[0].elements['idPalier6'].value = idPalier6.text; 
       	
       	var tR6 = (ligneReductionConvention.getElementsByTagName("tR6"))[0];
                document.forms[0].elements['tR6'].value = tR6.text;        	
       	
       	var vMin6 = (ligneReductionConvention.getElementsByTagName("vMin6"))[0];
                document.forms[0].elements['vMin6'].value = vMin6.text;
                
       	var vMax6 = (ligneReductionConvention.getElementsByTagName("vMax6"))[0];
                document.forms[0].elements['vMax6'].value = vMax6.text;
                
       	var valR6 = (ligneReductionConvention.getElementsByTagName("valR6"))[0];
                document.forms[0].elements['valR6'].value = valR6.text;                                       	
       }

       // palier7
       var idPalier7 = (ligneReductionConvention.getElementsByTagName("idPalier7"))[0];
       if(idPalier7 != null) {
       	document.forms[0].elements['idPalier7'].value = idPalier7.text; 
       	
       	var tR7 = (ligneReductionConvention.getElementsByTagName("tR7"))[0];
                document.forms[0].elements['tR7'].value = tR7.text;        	
       	
       	var vMin7 = (ligneReductionConvention.getElementsByTagName("vMin7"))[0];
                document.forms[0].elements['vMin7'].value = vMin7.text;
                
       	var vMax7 = (ligneReductionConvention.getElementsByTagName("vMax7"))[0];
                document.forms[0].elements['vMax7'].value = vMax7.text;
                
       	var valR7 = (ligneReductionConvention.getElementsByTagName("valR7"))[0];
                document.forms[0].elements['valR7'].value = valR7.text;                                       	
       }
} 


 
 

 
 ////////////////////////////////////////////////////////



// CONDITIONS PARTICULIERES

// appel appelAjaxEvenementCondPart
function appelAjaxEvenementCondPart() {    
    doEmptyPaliers(); 
    if(document.forms[0].elements['evenement'].value == '') {    
       document.forms[0].elements['idReductionConditionParticuliere'].value = '';    
       document.forms[0].elements['idLigneReductionConditionParticuliere'].value = '';
       return;
    }

    url = 'ajaxEvenementCondPartAction.do?idEvenement=' + document.forms[0].elements['evenement'].value
           + '&idConditionParticuliere=' + document.forms[0].elements['idConditionParticuliere'].value; 
	ajaxCallRemotePageEvenementCondPart(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageEvenementCondPart(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeEvenementCondPart;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeEvenementCondPart;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeEvenementCondPart() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       var rxml = req.responseXML;  
       var reductionConditionParticuliere = rxml.documentElement;        
       var idConditionParticuliere = (reductionConditionParticuliere.getElementsByTagName("idConditionParticuliere"))[0];
                document.forms[0].elements['idConditionParticuliere'].value = idConditionParticuliere.text;

       var codeConditionParticuliere = (reductionConditionParticuliere.getElementsByTagName("codeConditionParticuliere"))[0];
                document.forms[0].elements['code'].value = codeConditionParticuliere.text;
                
       var idReductionConditionParticuliere = (reductionConditionParticuliere.getElementsByTagName("idReductionConditionParticuliere"))[0];
       			document.forms[0].elements['idReductionConditionParticuliere'].value = idReductionConditionParticuliere.text;
       			
       var listeMontantsAReduire = (reductionConditionParticuliere.getElementsByTagName("listeMontantsAReduire"))[0];
              document.getElementById("listeMontantsAReduire").innerHTML = listeMontantsAReduire.xml;
       
      } else {
        alert("Un probleme est survenu: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}



// creation de la ligne de reduction 
function onChangeMontantAReduireCondPart() {
    doEmptyPaliers(); 
    if(document.forms[0].elements['montantAReduire'].value == '') {
       document.forms[0].elements['idLigneReductionConditionParticuliere'].value = '';
       return;
    }
    url = 'ajaxMontantCondPartAction.do?idReductionConditionParticuliere=' + document.forms[0].elements['idReductionConditionParticuliere'].value 
            + '&montantAReduire=' + document.forms[0].elements['montantAReduire'].value; 
                 
	ajaxCallRemotePageMontantCondPart(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageMontantCondPart(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeMontantCondPart;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeMontantCondPart;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeMontantCondPart() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);       

       var rxml = req.responseXML;  
       var ligneReductionConditionParticuliere = rxml.documentElement; 
              
       afficherLigneReductionConditionParticuliere(ligneReductionConditionParticuliere);
                
                
      } else {
        alert("Un probleme est survenu: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}




// appel appelAjaxEnregistrerLigneReductionConditionParticuliere
function appelAjaxEnregistrerLigneReductionConditionParticuliere() { 
    if(document.forms[0].elements['idLigneReductionConditionParticuliere'].value == ''){
        alert('Veuillez choisir un evenement et un type de montant');
    	return;
    }      
 
    url = 'ajaxEnregistrerLRCPAction.do?idLigneReductionConditionParticuliere=' + document.forms[0].elements['idLigneReductionConditionParticuliere'].value

           + '&idReductionConditionParticuliere=' + document.forms[0].elements['idReductionConditionParticuliere'].value
           + '&montantAReduire=' + document.forms[0].elements['montantAReduire'].value           
           + '&nombreMois=' + document.forms[0].elements['nombreMois'].value
           + '&dureeCotisationsContinues=' + document.forms[0].elements['dureeCotisationsContinues'].value           
           + '&nombreSuspensionsPaiementMax=' + document.forms[0].elements['nombreSuspensionsPaiementMax'].value           
           + '&dureeTotaleSuspensionsPaiementMax=' + document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value           
           
           + '&idPalier1=' + document.forms[0].elements['idPalier1'].value            
           + '&tR1=' + document.forms[0].elements['tR1'].value                      
           + '&vMin1=' + document.forms[0].elements['vMin1'].value     
           + '&vMax1=' + document.forms[0].elements['vMax1'].value                           
           + '&valR1=' + document.forms[0].elements['valR1'].value                

           + '&idPalier2=' + document.forms[0].elements['idPalier2'].value 
           + '&tR2=' + document.forms[0].elements['tR2'].value                      
           + '&vMin2=' + document.forms[0].elements['vMin2'].value     
           + '&vMax2=' + document.forms[0].elements['vMax2'].value                           
           + '&valR2=' + document.forms[0].elements['valR2'].value                
           
           + '&idPalier3=' + document.forms[0].elements['idPalier3'].value            
           + '&tR3=' + document.forms[0].elements['tR3'].value                      
           + '&vMin3=' + document.forms[0].elements['vMin3'].value     
           + '&vMax3=' + document.forms[0].elements['vMax3'].value                           
           + '&valR3=' + document.forms[0].elements['valR3'].value                
           
           + '&idPalier4=' + document.forms[0].elements['idPalier4'].value            
           + '&tR4=' + document.forms[0].elements['tR4'].value                      
           + '&vMin4=' + document.forms[0].elements['vMin4'].value     
           + '&vMax4=' + document.forms[0].elements['vMax4'].value                           
           + '&valR4=' + document.forms[0].elements['valR4'].value                
           
           + '&idPalier5=' + document.forms[0].elements['idPalier5'].value            
           + '&tR5=' + document.forms[0].elements['tR5'].value                      
           + '&vMin5=' + document.forms[0].elements['vMin5'].value     
           + '&vMax5=' + document.forms[0].elements['vMax5'].value                           
           + '&valR5=' + document.forms[0].elements['valR5'].value                
           
           + '&idPalier6=' + document.forms[0].elements['idPalier6'].value            
           + '&tR6=' + document.forms[0].elements['tR6'].value                      
           + '&vMin6=' + document.forms[0].elements['vMin6'].value     
           + '&vMax6=' + document.forms[0].elements['vMax6'].value                           
           + '&valR6=' + document.forms[0].elements['valR6'].value                
           
           + '&idPalier7=' + document.forms[0].elements['idPalier7'].value            
           + '&tR7=' + document.forms[0].elements['tR7'].value                      
           + '&vMin7=' + document.forms[0].elements['vMin7'].value     
           + '&vMax7=' + document.forms[0].elements['vMax7'].value                           
           + '&valR7=' + document.forms[0].elements['valR7'].value; 
	ajaxCallRemotePageEnregistrerLRCondPart(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePageEnregistrerLRCondPart(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeEnregistrerLRCondPart;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeEnregistrerLRCondPart;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "appelAjaxEnregistrerLigneReductionCP"
function processStateChangeEnregistrerLRCondPart() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
                                
       doEmptyPaliers();
       var rxml = req.responseXML;  
       var ligneReductionConditionParticuliere = rxml.documentElement; 
              
       afficherLigneReductionConditionParticuliere(ligneReductionConditionParticuliere);
        
      } else {
        alert("Un probleme est survenu : " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}


// Vider les les paliers de reduction

function doEmptyPaliers(){
	document.forms[0].elements['nombreMois'].value = '';
    document.forms[0].elements['dureeCotisationsContinues'].value = '';          
    document.forms[0].elements['nombreSuspensionsPaiementMax'].value = '';          
    document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value = '';          
           
    document.forms[0].elements['idPalier1'].value = '';           
    document.forms[0].elements['tR1'].value = '';                     
    document.forms[0].elements['vMin1'].value = '';    
    document.forms[0].elements['vMax1'].value = '';                          
    document.forms[0].elements['valR1'].value = '';              

    document.forms[0].elements['idPalier2'].value = '';
    document.forms[0].elements['tR2'].value = '';                     
    document.forms[0].elements['vMin2'].value = '';    
    document.forms[0].elements['vMax2'].value = '';                          
    document.forms[0].elements['valR2'].value = '';               
           
    document.forms[0].elements['idPalier3'].value = '';           
    document.forms[0].elements['tR3'].value = '';                     
    document.forms[0].elements['vMin3'].value = '';    
    document.forms[0].elements['vMax3'].value = '';                           
    document.forms[0].elements['valR3'].value = '';               
           
    document.forms[0].elements['idPalier4'].value = '';           
    document.forms[0].elements['tR4'].value = '';                     
    document.forms[0].elements['vMin4'].value = '';    
    document.forms[0].elements['vMax4'].value = '';                          
    document.forms[0].elements['valR4'].value = '';              
           
    document.forms[0].elements['idPalier5'].value = '';           
    document.forms[0].elements['tR5'].value = '';                     
    document.forms[0].elements['vMin5'].value = '';    
    document.forms[0].elements['vMax5'].value = '';                         
    document.forms[0].elements['valR5'].value = '';               
           
    document.forms[0].elements['idPalier6'].value = '';           
    document.forms[0].elements['tR6'].value = '';                     
    document.forms[0].elements['vMin6'].value = '';    
    document.forms[0].elements['vMax6'].value = '';                          
    document.forms[0].elements['valR6'].value = '';               
           
    document.forms[0].elements['idPalier7'].value = '';           
    document.forms[0].elements['tR7'].value = '';                     
    document.forms[0].elements['vMin7'].value = '';    
    document.forms[0].elements['vMax7'].value = '';                          
    document.forms[0].elements['valR7'].value = ''; 
}


function afficherLigneReductionConditionParticuliere(ligneReductionConditionParticuliere){
       var idLigneReductionConditionParticuliere = (ligneReductionConditionParticuliere.getElementsByTagName("idLigneReductionConditionParticuliere"))[0];
                document.forms[0].elements['idLigneReductionConditionParticuliere'].value = idLigneReductionConditionParticuliere.text; 

       var nombreMois = (ligneReductionConditionParticuliere.getElementsByTagName("nombreMois"))[0];
       if(nombreMois != null) {
       	document.forms[0].elements['nombreMois'].value = nombreMois.text; 
       }
       
       var dureeCotisationsContinues = (ligneReductionConditionParticuliere.getElementsByTagName("dureeCotisationsContinues"))[0];
       if(dureeCotisationsContinues != null) {
       	document.forms[0].elements['dureeCotisationsContinues'].value = dureeCotisationsContinues.text; 
       }
       var nombreSuspensionsPaiementMax = (ligneReductionConditionParticuliere.getElementsByTagName("nombreSuspensionsPaiementMax"))[0];
       if(nombreSuspensionsPaiementMax != null) {
       	document.forms[0].elements['nombreSuspensionsPaiementMax'].value = nombreSuspensionsPaiementMax.text; 
       }
       var dureeTotaleSuspensionsPaiementMax = (ligneReductionConditionParticuliere.getElementsByTagName("dureeTotaleSuspensionsPaiementMax"))[0];
       if(dureeTotaleSuspensionsPaiementMax != null) {
       	document.forms[0].elements['dureeTotaleSuspensionsPaiementMax'].value = dureeTotaleSuspensionsPaiementMax.text; 
       }       

       // palier1
       var idPalier1 = (ligneReductionConditionParticuliere.getElementsByTagName("idPalier1"))[0];
       if(idPalier1 != null) {
       	document.forms[0].elements['idPalier1'].value = idPalier1.text; 
       	
       	var tR1 = (ligneReductionConditionParticuliere.getElementsByTagName("tR1"))[0];
                document.forms[0].elements['tR1'].value = tR1.text;        	
       	
       	var vMin1 = (ligneReductionConditionParticuliere.getElementsByTagName("vMin1"))[0];
                document.forms[0].elements['vMin1'].value = vMin1.text;
                
       	var vMax1 = (ligneReductionConditionParticuliere.getElementsByTagName("vMax1"))[0];
                document.forms[0].elements['vMax1'].value = vMax1.text;
                
       	var valR1 = (ligneReductionConditionParticuliere.getElementsByTagName("valR1"))[0];
                document.forms[0].elements['valR1'].value = valR1.text;                                       	
       }        
        
       // palier2
       var idPalier2 = (ligneReductionConditionParticuliere.getElementsByTagName("idPalier2"))[0];
       if(idPalier2 != null) {
       	document.forms[0].elements['idPalier2'].value = idPalier2.text; 
       	
       	var tR2 = (ligneReductionConditionParticuliere.getElementsByTagName("tR2"))[0];
                document.forms[0].elements['tR2'].value = tR2.text;        	
       	
       	var vMin2 = (ligneReductionConditionParticuliere.getElementsByTagName("vMin2"))[0];
                document.forms[0].elements['vMin2'].value = vMin2.text;
                
       	var vMax2 = (ligneReductionConditionParticuliere.getElementsByTagName("vMax2"))[0];
                document.forms[0].elements['vMax2'].value = vMax2.text;
                
       	var valR2 = (ligneReductionConditionParticuliere.getElementsByTagName("valR2"))[0];
                document.forms[0].elements['valR2'].value = valR2.text;                                       	
       }
       
       // palier3
       var idPalier3 = (ligneReductionConditionParticuliere.getElementsByTagName("idPalier3"))[0];
       if(idPalier3 != null) {
       	document.forms[0].elements['idPalier3'].value = idPalier3.text; 
       	
       	var tR3 = (ligneReductionConditionParticuliere.getElementsByTagName("tR3"))[0];
                document.forms[0].elements['tR3'].value = tR3.text;        	
       	
       	var vMin3 = (ligneReductionConditionParticuliere.getElementsByTagName("vMin3"))[0];
                document.forms[0].elements['vMin3'].value = vMin3.text;
                
       	var vMax3 = (ligneReductionConditionParticuliere.getElementsByTagName("vMax3"))[0];
                document.forms[0].elements['vMax3'].value = vMax3.text;
                
       	var valR3 = (ligneReductionConditionParticuliere.getElementsByTagName("valR3"))[0];
                document.forms[0].elements['valR3'].value = valR3.text;                                       	
       }
       
       // palier4
       var idPalier4 = (ligneReductionConditionParticuliere.getElementsByTagName("idPalier4"))[0];
       if(idPalier4 != null) {
       	document.forms[0].elements['idPalier4'].value = idPalier4.text; 
       	
       	var tR4 = (ligneReductionConditionParticuliere.getElementsByTagName("tR4"))[0];
                document.forms[0].elements['tR4'].value = tR4.text;        	
       	
       	var vMin4 = (ligneReductionConditionParticuliere.getElementsByTagName("vMin4"))[0];
                document.forms[0].elements['vMin4'].value = vMin4.text;
                
       	var vMax4 = (ligneReductionConditionParticuliere.getElementsByTagName("vMax4"))[0];
                document.forms[0].elements['vMax4'].value = vMax4.text;
                
       	var valR4 = (ligneReductionConditionParticuliere.getElementsByTagName("valR4"))[0];
                document.forms[0].elements['valR4'].value = valR4.text;                                       	
       }       
              
       // palier5
       var idPalier5 = (ligneReductionConditionParticuliere.getElementsByTagName("idPalier5"))[0];
       if(idPalier5 != null) {
       	document.forms[0].elements['idPalier5'].value = idPalier5.text; 
       	
       	var tR5 = (ligneReductionConditionParticuliere.getElementsByTagName("tR5"))[0];
                document.forms[0].elements['tR5'].value = tR5.text;        	
       	
       	var vMin5 = (ligneReductionConditionParticuliere.getElementsByTagName("vMin5"))[0];
                document.forms[0].elements['vMin5'].value = vMin5.text;
                
       	var vMax5 = (ligneReductionConditionParticuliere.getElementsByTagName("vMax5"))[0];
                document.forms[0].elements['vMax5'].value = vMax5.text;
                
       	var valR5 = (ligneReductionConditionParticuliere.getElementsByTagName("valR5"))[0];
                document.forms[0].elements['valR5'].value = valR5.text;                                       	
       }

       // palier6
       var idPalier6 = (ligneReductionConditionParticuliere.getElementsByTagName("idPalier6"))[0];
       if(idPalier6 != null) {
       	document.forms[0].elements['idPalier6'].value = idPalier6.text; 
       	
       	var tR6 = (ligneReductionConditionParticuliere.getElementsByTagName("tR6"))[0];
                document.forms[0].elements['tR6'].value = tR6.text;        	
       	
       	var vMin6 = (ligneReductionConditionParticuliere.getElementsByTagName("vMin6"))[0];
                document.forms[0].elements['vMin6'].value = vMin6.text;
                
       	var vMax6 = (ligneReductionConditionParticuliere.getElementsByTagName("vMax6"))[0];
                document.forms[0].elements['vMax6'].value = vMax6.text;
                
       	var valR6 = (ligneReductionConditionParticuliere.getElementsByTagName("valR6"))[0];
                document.forms[0].elements['valR6'].value = valR6.text;                                       	
       }

       // palier7
       var idPalier7 = (ligneReductionConditionParticuliere.getElementsByTagName("idPalier7"))[0];
       if(idPalier7 != null) {
       	document.forms[0].elements['idPalier7'].value = idPalier7.text; 
       	
       	var tR7 = (ligneReductionConditionParticuliere.getElementsByTagName("tR7"))[0];
                document.forms[0].elements['tR7'].value = tR7.text;        	
       	
       	var vMin7 = (ligneReductionConditionParticuliere.getElementsByTagName("vMin7"))[0];
                document.forms[0].elements['vMin7'].value = vMin7.text;
                
       	var vMax7 = (ligneReductionConditionParticuliere.getElementsByTagName("vMax7"))[0];
                document.forms[0].elements['vMax7'].value = vMax7.text;
                
       	var valR7 = (ligneReductionConditionParticuliere.getElementsByTagName("valR7"))[0];
                document.forms[0].elements['valR7'].value = valR7.text;                                       	
       }
} 




// CONTRATS GROUPES

// appel appelAjaxEvenementPrm
function appelAjaxTypesSalariesContratsGroupe() {
    if(
      (document.forms[0].elements['contrat1'].value != '' &&  document.forms[0].elements['contrat1'].value != '')
    &&(document.forms[0].elements['contrat2'].value != '' &&  document.forms[0].elements['contrat2'].value != '')
    &&(document.forms[0].elements['contrat3'].value != '' &&  document.forms[0].elements['contrat3'].value != '')
    ){
    	url = 'ajaxTypeAdherentContratGroupeAction.do?numeroContrat=' + document.forms[0].elements['contrat1'].value +document.forms[0].elements['contrat2'].value+document.forms[0].elements['contrat3'].value; 
					ajaxCallRemoteTypesSalariesPage(url);
	}else{
		alert('Veuillez fournir le numéro du contrat groupe');
	}
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemoteTypesSalariesPage(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeTypesSalariesCG;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeTypesSalariesCG;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }

 // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeTypesSalariesCG() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       var rxml = req.responseXML;  
       var typeSalarie = rxml.documentElement;        
       var listTypesSalaries = (typeSalarie.getElementsByTagName("listTypesSalaries"))[0];
              document.getElementById("listTypesSalaries").innerHTML = listTypesSalaries.xml;
       
      } else {
        alert("Le contrat groupe saisi est inexistant !");
        // alert("status: " + req.status);
      }
    }
}


// demandeur de la reclamation

// appel dans la page pour chercher demandeur


// appel appelAjaxPays
function appelAjaxPaysForSouscripteur() { 
    url = 'ajaxVillesForSouscripteurAction.do?idPays=' + document.forms[0].elements['paysClientSouscripteur'].value; 
	ajaxCallRemotePagePaysForSouscripteur(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePagePaysForSouscripteur(url) {
    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeVillesForSouscripteur;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeVillesForSouscripteur;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }
 
   // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeVillesForSouscripteur() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;      
       document.getElementById("listeVillesSouscripteur").innerHTML = xml;
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// appel appelAjaxAdherent
function appelAjaxPaysForAdherent() { 
    url = 'ajaxVillesForAdherentAction.do?idPays=' + document.forms[0].elements['paysClientAdherent'].value; 
	ajaxCallRemotePagePaysForAdherent(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePagePaysForAdherent(url) {
    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeVillesForAdherent;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeVillesForAdherent;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }
 
   // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeVillesForAdherent() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;      
       document.getElementById("listeVillesAdherent").innerHTML = xml;
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

// appel appelAjaxConjoint
function appelAjaxPaysForConjoint() { 
    url = 'ajaxVillesForConjointAction.do?idPays=' + document.forms[0].elements['paysClientConjoint'].value; 
	ajaxCallRemotePagePaysForConjoint(url);
}

// Methode d'appel a une page en XmlHttpRequest
function ajaxCallRemotePagePaysForConjoint(url) {
    if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChangeVillesForConjoint;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { // IE
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChangeVillesForConjoint;
       	req.open("GET", url, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; // Navigateur non compatible	
	}
 }
 
   // Methode private qui traite le retour de l'appel de "ajaxCallRemotePage"
function processStateChangeVillesForConjoint() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
       // Pour le debug
       // alert("ok:"+req.responseText);
       xml = req.responseText;      
       document.getElementById("listeVillesConjoint").innerHTML = xml;
      } else {
        // Pour le debug
        // alert("Problem: " + req.statusText);
        // alert("status: " + req.status);
      }
    }
}

function ttCocher() {
  for (var i = 0; i < document.forms[0].elements.length; i=i+1) {
    if(document.forms[0].elements[i].type == 'checkbox'){
      document.forms[0].elements[i].checked = true;
    }
  }
}
function ttDeCocher() {
  for (var i = 0; i < document.forms[0].elements.length; i=i+1) {
    if(document.forms[0].elements[i].type == 'checkbox'){
      document.forms[0].elements[i].checked = false;
    }
  }
}

function CheckAllCheckboxs() {
  var indice = 0;
  for (var i = 0; i < document.forms[0].elements.length; i=i+1) {
    if(document.forms[0].elements[i].type == 'checkbox'){
      if (document.forms[0].elements[i].checked) indice = indice + 1;
    }
  }
  if (indice == 0) alert('Cochez au moins une ligne');
  return;
}

