var refPays;
var refVille;
var refRegion;
var refQuartier;
var refActivitePrestataire;
var refDomaineActivite;

var form;

function changeListeActivitePrestataire(formName){
		if(formName=='reaffecterMissionForm'){
			form=formName;
		}else{
			form='';
		}
		 refActivitePrestataire=document.forms[formName].refActivitePrestataire;
		 refActivitePrestataire.value = null;
		 refDomaineActivite=document.forms[formName].refDomaineActivite;
		 var codeDomaine = refDomaineActivite.value;
		 
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 AjaxAction.getListeActivitePrestataireByDomaineActivite(codeDomaine,formName,populateListActivitePrestataire);
	}
			
function populateListActivitePrestataire(data){
		 DWRUtil.removeAllOptions(form+"refActivitePrestataire");		 
		 DWRUtil.addOptions(form+"refActivitePrestataire",data,"id","libelle");
		 form="";
	}
	
function changeListeNatureMission(formName){			
		 document.forms[formName].refNatureMission.value = null;
		 var codeDomaine = document.forms[formName].refDomaineActivite.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 AjaxAction.getListeNatureMissionByDomaineActivite(codeDomaine,formName,populateListNatureMission);
	}	
	
function populateListNatureMission(data){
		 DWRUtil.removeAllOptions("refNatureMission");
		 DWRUtil.addOptions("refNatureMission", {"":"**************"});
		 DWRUtil.addOptions("refNatureMission",data,"id","libelle");
	}
	
function ajouterReference(formName){		
		 var idReference = document.forms[formName].refReference.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 AjaxAction.ajouterReference(idReference,formName,populateDisplayTagListe);
	}			
function supprimerReference(id,formName){				
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 AjaxAction.supprimerReference(id,formName,populateDisplayTagListe);
	}	
function populateDisplayTagListe(data){
		 DWRUtil.setValue("displayTable", data, { escapeHtml:false });
	}			

function ajouterPrestation(formName){		
		 var id = document.forms[formName].refPrestation.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 GestionConventionPrestationAction.ajouterPrestation(id,formName,populateDisplayTagListe);
	}
	
function changeListePrestation(formName){
		 document.forms[formName].refPrestation.value = null;
		 var codeDomaine = document.forms[formName].refDomaineActivite.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 GestionConventionPrestationAction.getListePrestationByDomaineActivite(codeDomaine,formName,populateListPrestation);
	}	
function populateListPrestation(data){
		 DWRUtil.removeAllOptions("refPrestation");
		 DWRUtil.addOptions("refPrestation", {"":"**************"});
		 DWRUtil.addOptions("refPrestation",data,"id","libelle");
	}		
	
function supprimerPrestation(id,formName){				
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 GestionConventionPrestationAction.supprimerPrestation(id,formName,populateDisplayTagListe);
	}		


	
function changeListePrestationByNatureMission(formName){
		 document.forms[formName].refPrestation.value = null;
		 var id = document.forms[formName].refNatureMission.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 GestionTarifPrestationAction.getListePrestationByNatureMission(id,formName,populateListPrestation);
	}	
		
	
function changeListePrestataire(formName){
		 if(formName=='reaffecterMissionForm'){
			form=formName;
		}else{
			form='';
		}
		 document.forms[formName].refPrestataire.value = null;
		 var codeDomaine = document.forms[formName].refActivitePrestataire.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 GestionConventionPrestataireAction.getListePrestataireByActivitePrestataire(codeDomaine,formName,populateListPrestataire);
	}	
	
function populateListPrestataire(data){		 
		 DWRUtil.removeAllOptions(form+"refPrestataire");
		 DWRUtil.addOptions(form+"refPrestataire", {"":"**************"});
		 DWRUtil.addOptions(form+"refPrestataire",data,"code","nomRaisonSocial");
		 if(document.getElementById(form+"refPrestataire").options.length==1)
		 	messagePrestataire('1',form);
		 else
		 	messagePrestataire('0',form);
		 	
		 form="";
		 	
	}
	
function messagePrestataire(afficher,form){
	if(afficher=='1')
		document.getElementById(form+'msgPrestataire').style.display="block";
	else
		document.getElementById(form+'msgPrestataire').style.display="none";
}
function ajouterPrestataire(formName){		
		 var id = document.forms[0].refPrestataire.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 GestionConventionPrestataireAction.ajouterPrestataire(id,formName,populateDisplayTagListe);
	}	
function supprimerPrestataire(id,formName){				
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 GestionConventionPrestataireAction.supprimerPrestataire(id,formName,populateDisplayTagListe);
	}
function changePays(formName){
		 if(formName=='reaffecterMissionForm'){
			form=formName;
		}else{
			form='';
		}
		 refRegion=document.forms[formName].refRegion;
		 refRegion.value = null;		 
		 refVille=document.forms[formName].refVille;
		 refVille.value = null;
		 var id = document.forms[formName].refPays.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 
		 AjaxAction.getListeRegion(id,formName,populateListRegion);
		 AjaxAction.getListeVille(id,"",formName,populateListVille);
	}
function changeVilleByPays(formName){
		 if(formName=='reaffecterMissionForm'){
			form=formName;
		}else{
			form='';
		}		 
		 refVille=document.forms[formName].refVille;
		 refVille.value = null;
		 var id = document.forms[formName].refPays.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 		
		 AjaxAction.getListeVille(id,"",formName,populateListVille);
	}
function changeRegion(formName){
		 if(formName=='reaffecterMissionForm'){
			form=formName;
		}else{
			form='';
		}
		 refVille=document.forms[formName].refVille;
		 refVille.value = null;
		 var id = document.forms[formName].refRegion.value;		
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 
		 AjaxAction.getListeVille("",id,formName,populateListVille);
	}
function changeVille(formName){
		 if(formName=='reaffecterMissionForm'){
			form=formName;
		 }else{
			form='';
		 }
		 //document.getElementById('refQuartier').value = null;
		 var codeVille = document.forms[formName].refVille.value;
		 var codePays = document.forms[formName].refPays.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 
		 AjaxAction.getListeQuartier(codePays,codeVille,formName,populateListQuartier);
	}
	
function populateListRegion(data){		 
		 DWRUtil.removeAllOptions(form+"refRegion");
		 DWRUtil.addOptions(form+"refRegion", {"":"**************"});
		 DWRUtil.addOptions(form+"refRegion",data,"code","libelle");
		 form="";
	}
function populateListVille(data){		 
		 DWRUtil.removeAllOptions(form+"refVille");
		 DWRUtil.addOptions(form+"refVille", {"":"**************"});
		 DWRUtil.addOptions(form+"refVille",data,"code","libelle");
		 form="";
	}
function populateListQuartier(data){		 
		 DWRUtil.removeAllOptions(form+"refQuartier");
		 DWRUtil.addOptions(form+"refQuartier", {"":"**************"});
		 DWRUtil.addOptions(form+"refQuartier",data,"code","libelle");
		 DWRUtil.setValue(form+"refQuartier", "");
		 form="";
	}
function changeListePrestataireAReaffecter(formName){
		 if(formName=='reaffecterMissionForm'){
			form=formName;
		 }else{
			form='';
		 }
		 document.forms[formName].refPrestataire.value = null;
		 var codeDomaine = document.forms[formName].refDomaineActivite.value;
		 var codeActivite = document.forms[formName].refActivitePrestataire.value;
		 var codeVille = document.forms[formName].refVille.value;
		 var codePays = document.forms[formName].refPays.value;
         var codeRegion = document.forms[formName].refRegion.value;
         var codeQuartier = document.forms[formName].refQuartier.value;
         var interne;
         if(formName=='validerMissionForm'){
         	interne = document.forms[formName].interne.value;
         }else{
         	interne = document.forms[formName].interneReaffectation.value;
         }
         var codeEntite ='';
         if(interne=="1"){
         	if(document.forms[formName].chkconvention.checked==true)
         		codeEntite =document.forms[formName].entiteCreatrice.value;
         }
         
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 if(interne=="1"){		 	
		 	AjaxAction.getListePrestataireByCritere(codeDomaine,codeActivite,codeVille,codePays,codeRegion,codeQuartier,codeEntite,formName,populateListPrestataire);
		 }
		 else
		 	AjaxAction.getPrestataireByCritere(codeDomaine,codeActivite,codeVille,codePays,codeRegion,codeQuartier,formName,populatePrestataire);
		 	
		 
	}
function populatePrestataire(data){		 
		 document.getElementById('refPrestataire').value=data.code;
		 document.getElementById('prestataire').value=data.nomRaisonSocial;
		  if(data.code=='' || data.code== 'null')
		 	messagePrestataire('1',form);
		 else
		 	messagePrestataire('0',form);

		 form="";
	}	

function changeListePrestataireAReaffecterRattache(formName){
		 if(formName=='reaffecterMissionForm'){
			form=formName;
		 }else{
			form='';
		 }
		 document.forms[formName].refPrestataire.value = null;
		 var codeDomaine = document.forms[formName].refDomaineActivite.value;
		 var codeActivite = document.forms[formName].refActivitePrestataire.value;
		 var codeVille = document.forms[formName].refVille.value;
		 var codePays = document.forms[formName].refPays.value;
         var codeRegion = document.forms[formName].refRegion.value;
         var codeQuartier = document.forms[formName].refQuartier.value;
         var interne = document.forms[formName].interne.value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 if(interne=="1")
		 	AjaxAction.getListePrestataireByCritere(codeDomaine,codeActivite,codeVille,codePays,codeRegion,codeQuartier,formName,populateListPrestataire);
		 else
		 	AjaxAction.getPrestataireByCritere(codeDomaine,codeActivite,codeVille,codePays,codeRegion,codeQuartier,formName,populatePrestataireRattache);
	}
function populatePrestataireRattache(data){		 
		 document.getElementsByName('refPrestataire')[1].value=data.code;
		 document.getElementsByName('prestataire')[1].value=data.nomRaisonSocial;
		 form="";
	}	
	
function getDocumentIdentite(domElment){
	$.ajax({
		   type: "POST",
		   url: "./gestionPrestataire.do?actions=getDocumentIdentite",
		   data: "type="+$(domElment).val(),
		   success: function(data){
		    $("#documentIdentite").empty().append(data);
		   }
		 });
}

function chercherMandataire(Url,width,height){
		var codeActivite = document.forms[0].refActivitePrestataire;
		var codeDomaine = document.forms[0].refDomaineActivite;
		Url =Url + '&codeActivite='+codeActivite+'&codeDomaine='+codeDomaine;
		
		var codeMandataire=loadFormInModal(Url,width,height);
		if (codeMandataire!=undefined)
			document.forms[0].refMandataire.value=codeMandataire; 
}

function showInfoMoyenPaiement(){
		var val =document.getElementById('refMoyenPaiement');
		if(val.value==3)
			document.getElementById('rib').style.display="block";
		else
			document.getElementById('rib').style.display="none";

}
function getLibelleConvention(element){
		var codeEntite = element.value;
		document.getElementById('libelleConvention').value='';
		DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');		 
		AjaxAction.getLibelleConventionByEntite(codeEntite,populateLibelleConvention);
}

function populateLibelleConvention(data){		 
		 document.getElementsByName('refPrestataire')[1].value=data.code;
		 document.getElementsByName('prestataire')[1].value=data.nomRaisonSocial;
	}
	
	
function changeListGarantieBranche(formName){
		 document.getElementById('refGarantieBranche').value = null;
		 var codeBranche = document.getElementById('refBranche').value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 
		 AjaxAction.getListeGarantieBranche(codeBranche,formName,populateListGarantieBranche);
	}	
function populateListGarantieBranche(data){		 
		 DWRUtil.removeAllOptions("refGarantieBranche");
		 DWRUtil.addOptions("refGarantieBranche", {"":"**************"});
		 DWRUtil.addOptions("refGarantieBranche",data,"code","libelle");
	}	
function update(criteria) {
    	 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');		
		 AjaxAction.getSortedPrestatairesByPage(criteria,populateDisplayTagListe);
}
function rechercherPrestataire(criteria) {
		 
		 var code = document.getElementById('code').value;
		 var nomRaisonSocial = document.getElementById('nomRaisonSocial').value;
		 var etatConnexion = document.getElementById('etatConnexion').value;
		 var idActivite = document.getElementById('refActivitePrestataire').value;
		 var codeVille = document.getElementById('refVille').value;
		 var codePays = document.getElementById('refPays').value;

 		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');		
		 AjaxAction.getListPrestatairesByPage('', code,  nomRaisonSocial,idActivite, etatConnexion, codePays, codeVille,populateDisplayTagListe);
	
}
	
