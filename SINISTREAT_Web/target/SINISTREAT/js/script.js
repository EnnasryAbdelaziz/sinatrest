
function showhideBloc(nomBloc){
        quoiId = document.getElementById(nomBloc);
        var old = typeof(quoiId.saveDisplay)!='undefined'?
        quoiId.saveDisplay : quoiId.style.display;
        quoiId.style.display = quoiId.style.display==old? 'none' : old;
        quoiId.saveDisplay = old;

}

function showhideBloc(nomBloc,dispatch){
	if(dispatch != null){
		setHidden(dispatch);
		document.forms[0].submit();
	}else{
        quoiId = document.getElementById(nomBloc);
        var old = typeof(quoiId.saveDisplay)!='undefined'?
        quoiId.saveDisplay : quoiId.style.display;
        quoiId.style.display = quoiId.style.display==old? 'none' : old;
        quoiId.saveDisplay = old;
        }
}


function switchMenu(nomBloc){
		document.getElementById('tableauSuivi').style.display='none';
		document.getElementById('fonctionnalitesFE').style.display='none';
		document.getElementById('gestionParc').style.display='none';
		document.getElementById('gestionFE').style.display='none';
		document.getElementById('fonctionnalitesFS').style.display='none';
		document.getElementById('gestionAdhesion').style.display='none';
		document.getElementById('gestionFS').style.display='none';
		document.getElementById('gestionTerme').style.display='none';
		document.getElementById('gestionHabilitation').style.display='none';				
		document.getElementById('gestionGED').style.display='none';
		document.getElementById('documentation').style.display='none';
				document.getElementById('interfaceDeMasse').style.display='none';
        document.getElementById(nomBloc).style.display ='block';
}

function initMenu(){
		 AjaxAction.getMenuVisibility(setMenuVisibility);
		 AjaxAction.getSelectedSubMenu(updateColor);
//FIXME KEL, recalcule de la visibilité du menu selon l'état au niveau du serveur
}

function setMenuVisibility(data){
	
	
	for(var i = 0 ; i < data.length ; i++){
		//alert(data[i].id);
		var idElement = data[i].id ;
		if(idElement != null && idElement != '' && data[i].visibility != null && data[i].visibility != ''){
		
			//alert(idElement +'       visibility == '+data[i].visibility );
			if(document.getElementById(idElement) != null){
				document.getElementById(idElement).style.display = data[i].visibility ;
			}
		}
	}
}
/**
*
*/
function updateColor(data){
	var idElement = 'bgColorTr.'+data ;
	
	if(document.getElementById(idElement) != null ){
		document.getElementById(idElement).bgColor = "#DDDAAA" ;
	}
}


function setHidden(value){
	alert(value);
	document.creationReponseForm.actions.value=value;
}

function onChangeSelect(value){
	var libelle = value+'Libelle';
	document.getElementById(libelle).value = document.getElementById(value).options[document.getElementById(value).selectedIndex].text;
}

function afficheDIV(idpr)
{	
	var pr = document.getElementById(idpr);
	pr.style.display = "";	
}
 
function cacheDIV(idpr)
{
	var pr = document.getElementById(idpr);
	pr.style.display = "none";	
}

function afficheCacheDIV(idpr, idch){
	var pr = document.getElementById(idpr);
	var pr2 = document.getElementById(idch);

	if (pr2.value == "off"){
		pr2.value = "on";
		pr.style.display = "";
	}
	else if	(pr2.value == "on"){
		pr2.value = "off";
		pr.style.display = "none";
	}
}

function today(){

	navvers = navigator.appVersion.substring(0,1);
	if (navvers > 3)
		navok = true;
	else
		navok = false;
	
	today = new Date;
	jour = today.getDay();
	numero = today.getDate();
	if (numero<10)
		numero = "0"+numero;
	mois = today.getMonth();
	if (navok)
		annee = today.getFullYear();
	else
		annee = today.getYear();
	TabJour = new Array("Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi");
	TabMois = new Array("Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre");
	messageDate = TabJour[jour] + " " + numero + " " + TabMois[mois] + " " + annee;
	return messageDate;
}


/**
*
*/
function updateBGColor(subMenuID){
	AjaxAction.updateBGColor(subMenuID);
}


function displayNomenclature(modeleId,marqueId){
	var modeleObj = document.getElementById(modeleId) ;
	var marqueObj = document.getElementById(marqueId) ;
	
	var modele = modeleObj.options[modeleObj.selectedIndex].text ; 
	var marque = marqueObj.options[marqueObj.selectedIndex].text ;
    
    if(modeleObj.value != '' && marqueObj.value != ''){
        var url = 'nomenclatureAction.do?marque='+marque+'&modele='+modele+'&time='+(new Date()).getTime();
		window.open(url ,'nomenclature','toolbar=0, location=0, directories=0, status=0, scrollbars=1, resizable=0, copyhistory=0, menuBar=0, width=800, height=500, left=100, top=100');
	}
	else{
		alert('Vous devez sélectionner une marque et un modèle');
	}	
	
	return false;

}


function displayNomenclatureLink(){

	var modeleObj = document.getElementById('modele') ;

	var div = document.getElementById('nomenclatureHrefDivId') ;
	if(modeleObj.value != ''){
		div.innerHTML = '<a  href=\"#\" onClick="javascript:displayNomenclature(\'modele\',\'marque\');\">Nomenclature</a>' ;
	}
	else{
		div.innerHTML = '' ;
	}
}

//
// -- Imprimer une partie de la page 
// id : id de l'element HTML à immprimer
//
function imprimer (id) {
	var impression = null;
	var content=document.getElementById(id).innerHTML;
	var title=document.getElementsByTagName('title')[0].innerText;
	if (impression) {if(!impression.closed) impression.close();}
	impression = window.open ('',"impression", "height=500,width=600,menubar=yes,scrollbars=yes,resizable=yes,,left=10,top=10");  
	impression.document.open();
	impression.document.write("<html><head><title>"+title+"</title></head><body bgcolor='#ffffff'>"+content+"</body></html>");
	impression.document.close();
	impression.print();
	//impression.document.getElementById('id').style.visibility='hidden';
	impression.focus();
}


//--------------------------------------
// Valider un champ de type d?cimal pour le tarif
//
//	ent : nbre de digits dans la partie enti?re
//	dec : nbre de digits dans la partie d?cimale
//--------------------------------------
function validerTauxReduction(index){
	var a=document.forms[0].elements[index].value; // Zone de texte 

	// Se debarasser des caracteres indesirables !! du balai ;)	
	a = a.replace(/[^0-9.]/g,"");

	//Avoir un seul point
	var ind =  a.indexOf(".");
		// si no point et limite atteinte, ajouter le point ;)
	if ( (ind == -1) && (a.length == 3)) {
		a = a + ".";
	}
	while (ind != -1){
		ind = a.indexOf(".",ind+1);
		a = a.substring(0,ind) + a.substring(ind+1, a.length);
	}
	//Avoir 3  chiffres max au d?but, et 2 chiffres ? la fin
	ind = a.indexOf(".");
	var entpart, decpart;
	if (ind != -1){
		entpart = a.substring(0,ind);
		entpart = entpart.substr(0,3);
		decpart = a.substring(ind+1, a.length);
		decpart = decpart.substr(0,2);
		a = entpart + "." + decpart;
	}

	if(entpart > 100){
		alert('le taux ne doit pas dépasser 100');
		a = "";
	}
	else if((entpart == 100) && (decpart > 0)){
			alert('le taux ne doit pas dépasser 100');
			a = "";
		}
		
	document.forms[0].elements[index].value = a;
}
