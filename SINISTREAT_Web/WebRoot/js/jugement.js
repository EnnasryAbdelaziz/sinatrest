function rechercherProcJudiciaire(){

		branche = document.forms[0].numeroProcedureJudiciaire.value;
		numeroSinistre = document.forms[0].typeJuridiction.value;
	
		if(branche == "" || numeroSinistre == "") {
			alert("Veuillez saisir un numero de procédure judiciaire et son type ");
			return false;
		}
		document.forms[0].action="gestionJugementAction.do";		
		document.forms[0].actions.value="rechercherProcJudiciaire";
		document.forms[0].submit();
			
}
function showBlockJugement()
{
	
	if(document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="1" || 
		document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="4" )
	{
		$("#idJugementADD").css("display","block");
		$("#idJugementInterlocutoire").css("display","none");
		$("#idJugementFond").css("display","none");
		$("#idJugementSupreme").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="2")
	{
		$("#idJugementADD").css("display","none");
		$("#idJugementInterlocutoire").css("display","block");
		$("#idJugementFond").css("display","none");
		$("#idJugementSupreme").css("display","none");
	}
	else if(document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="3" || 
		document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="5" )
	{
		$("#idJugementADD").css("display","none");
		$("#idJugementInterlocutoire").css("display","none");
		$("#idJugementFond").css("display","block");
		$("#idJugementSupreme").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="6")
	{
		$("#idJugementADD").css("display","none");
		$("#idJugementInterlocutoire").css("display","none");
		$("#idJugementFond").css("display","none");
		$("#idJugementSupreme").css("display","block");
	}	
	else 
	{
		//DWRUtil.setValue("typeCompteRendu", document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value);
		hideBlockJugement();

	}		
}
function showBlockJugementBis()
{
	//DWRUtil.setValue("typeCompteRendu", document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value);
}
function showListParTypeJugement()
{
	
	 AjaxAction.resetJugement('creerRecoursForm');
	if(document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="1" || 
		document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="4" )
	{
		$("#expertiseBlock").css("display","block");
		$("#indemnisationBlock").css("display","none");
		$("#tauxBlock").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="2")
	{
		$("#expertiseBlock").css("display","none");
		$("#indemnisationBlock").css("display","none");
		$("#tauxBlock").css("display","block");
	}
	else if(document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="3" || 
		document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="5" )
	{
		$("#expertiseBlock").css("display","none");
		$("#indemnisationBlock").css("display","block");
		$("#tauxBlock").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="6")
	{
		$("#expertiseBlock").css("display","none");
		$("#indemnisationBlock").css("display","none");
		$("#tauxBlock").css("display","none");
	}	
	else 
	{
		$("#expertiseBlock").css("display","none");
		$("#indemnisationBlock").css("display","none");
		$("#tauxBlock").css("display","none");

	}			
}
function showListParTypeJugementBis()
{
	
if(document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="1" || 
		document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="4" )
	{
		$("#expertiseBlock").css("display","block");
		$("#indemnisationBlock").css("display","none");
		$("#tauxBlock").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="2")
	{
		$("#expertiseBlock").css("display","none");
		$("#indemnisationBlock").css("display","none");
		$("#tauxBlock").css("display","block");
	}
	else if(document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="3" || 
		document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="5" )
	{
		$("#expertiseBlock").css("display","none");
		$("#indemnisationBlock").css("display","block");
		$("#tauxBlock").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.jugement.typeJugement"].value=="6")
	{
		$("#expertiseBlock").css("display","none");
		$("#indemnisationBlock").css("display","none");
		$("#tauxBlock").css("display","none");
	}	
	else 
	{
		$("#expertiseBlock").css("display","none");
		$("#indemnisationBlock").css("display","none");
		$("#tauxBlock").css("display","none");

	}			
}
function hideBlockJugement(){
		$("#idJugementADD").css("display","none");
		$("#idJugementInterlocutoire").css("display","none");
		$("#idJugementFond").css("display","none");
		$("#idJugementSupreme").css("display","none");
}
function changeTypeJugement(formName){
		 var codeTypeJuridiction = document.getElementById('typeJuridiction').value;
		 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');
		 AjaxAction.getListTypeJugementParJuridiction(codeTypeJuridiction,formName,populateListTypeJugement);
}
function populateListTypeJugement(data){		 
		 DWRUtil.removeAllOptions("typeJugement");
		 DWRUtil.addOptions("typeJugement", {"":"**************"});
		 DWRUtil.addOptions("typeJugement",data,"code","libelle");
		 DWRUtil.setValue("typeJugement", "");
/*		 DWRUtil.removeAllOptions("typeCompteRendu");
		 DWRUtil.addOptions("typeCompteRendu", {"":"**************"});
		 DWRUtil.addOptions("typeCompteRendu",data,"code","libelle");
		 DWRUtil.setValue("typeCompteRendu", "");*/		 
		 hideBlockJugement();
}
function setIdPartieAdverse(id){
		 AjaxAction.setIdPartieAdverse(id,populateIdPartie)	;	 
}
function populateIdPartie(data){
		 DWRUtil.setValue("expertise.idPartieAdverse",data);
		 DWRUtil.setValue("indemnisation.idPartieAdverse",data);
		 DWRUtil.setValue("tauxResponsabilite.idPartieAdverse",data);
}

function reset(formName){
		 AjaxAction.reset(formName);
}
function ajoutIndemnisation(){
	document.recoursForm.actions.value="ajoutIndemnisation";
	var serialize=$("form[name=recoursForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "./gestionRecoursAction.do?",
		   data: serialize,
		   success: function(data){
				ajoutIndemnisationCallBack(data);
		   }
		 });
}
function ajoutIndemnisationCallBack(data){

		    $("#listIndemnisation").empty().append(data);
		    $("#listIndemnisation").show();
		    
}
var values = {};

function getForm(formName){
$.each($('[name='+formName+']').serializeArray(), function(i, field) {
    values[field.name] = field.value;
});
}

function deleteIndemnisation(index){
if(confirm("Voulez vous vraiment supprimer cette indemnisation ?")) {
	getForm("recoursForm");
		$.ajax({
			   type: "POST",
			   url: "./gestionRecoursAction.do?actions=deleteIndemnisation&index="+index,
			   data: values,
			   success: function(data){
					ajoutIndemnisationCallBack(data);
			   }
			 });
}
}


function ajoutDepens(){
	document.recoursForm.actions.value="ajoutDepens";
	var serialize=$("form[name=recoursForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "./gestionRecoursAction.do?",
		   data: serialize,
		   success: function(data){
				ajoutDepensCallBack(data);
		   }
		 });
}
function ajoutDepensCallBack(data){

		    $("#listeDepens").empty().append(data);
		    $("#listeDepens").show();
		    
}

function deleteDepens(index){
if(confirm("Voulez vous vraiment supprimer cette Depens ?")) {
	getForm("recoursForm");
		$.ajax({
			   type: "POST",
			   url: "./gestionRecoursAction.do?actions=deleteDepens&index="+index,
			   data: values,
			   success: function(data){
					ajoutDepensCallBack(data);
			   }
			 });
}
}