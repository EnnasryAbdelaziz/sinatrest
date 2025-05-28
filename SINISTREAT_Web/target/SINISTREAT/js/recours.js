function openPop(actions) {
	window.open(actions , '', 'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,height=280,width=820,top=200 , left=200');
}


function ajoutInfoSinistre(){

		branche = document.forms[0].codeBranche.value;
		numeroSinistre = document.forms[0].numeroSinistre.value;
	
		if(branche == "" || numeroSinistre == "") {
			alert("Veuillez saisir une branche et un numero de sinistre");
			return false;
		}
		document.forms[0].action="creationRecoursAction.do";		
		document.forms[0].actions.value="rechercheSinistre";
		document.forms[0].submit();
			
}
function showBlockProcedure()
{
	if(document.forms[0].typeProcedureInitial[0].checked)
	{
		$("#idProcAmiable").css("display","block");
		$("#idProcJudiciaire").css("display","none");
	}
	else if (document.forms[0].typeProcedureInitial[1].checked)
	{
		$("#idProcAmiable").css("display","none");
		$("#idProcJudiciaire").css("display","block");
	}
	else
	{
		$("#idProcAmiable").css("display","none");
		$("#idProcJudiciaire").css("display","none");
	}
}
function showBlockProcedureRadio()
{
	if (document.forms[0].elements["procJudiciaire.typeJuridiction"][0].checked)
	{
		$("#idInstance").css("display","block");
		$("#idCourAppel").css("display","none");
		$("#idCourSupreme").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.typeJuridiction"][1].checked)
	{
		$("#idInstance").css("display","none");
		$("#idCourAppel").css("display","block");
		$("#idCourSupreme").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.typeJuridiction"][2].checked)
	{
		$("#idInstance").css("display","none");
		$("#idCourAppel").css("display","none");
		$("#idCourSupreme").css("display","block");
	}
	else{
		$("#idInstance").css("display","none");
		$("#idCourAppel").css("display","none");
		$("#idCourSupreme").css("display","none");
	}
}
function showBlockProcedureParElements()
{
	if (document.forms[0].elements["procJudiciaire.typeJuridiction"].value=="1")
	{
		$("#idInstance").css("display","block");
		$("#idCourAppel").css("display","none");
		$("#idCourSupreme").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.typeJuridiction"].value="2")
	{
		$("#idInstance").css("display","none");
		$("#idCourAppel").css("display","block");
		$("#idCourSupreme").css("display","none");
	}
	else if (document.forms[0].elements["procJudiciaire.typeJuridiction"].value="3")
	{
		$("#idInstance").css("display","none");
		$("#idCourAppel").css("display","none");
		$("#idCourSupreme").css("display","block");
	}
	else{
		$("#idInstance").css("display","none");
		$("#idCourAppel").css("display","none");
		$("#idCourSupreme").css("display","none");
	}
}
function showBlockComplementAmiable()
{
	if(document.forms[0].elements["procAmiable.resultatReclamation"].value=="3")
	{
		$("#idProposition").css("display","block");
		$("#idMotifRejet").css("display","none");
	}
	else if(document.forms[0].elements["procAmiable.resultatReclamation"].value=="2")
	{
		$("#idProposition").css("display","none");
		$("#idMotifRejet").css("display","block");
	}
	else
	{
		$("#idProposition").css("display","none");
		$("#idMotifRejet").css("display","none");
	}
}
function ajoutPartieAdverseCreer(){
	document.creerRecoursForm.actions.value="ajoutPartieAdverseCreer";
	var serialize=$("form[name=creerRecoursForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "./creationProcJudAction.do?",
		   data: serialize,
		   success: function(data){
				ajoutPartieAdverseCreerCallBack(data);
		   }
		 });
}
function ajoutPartieAdverseCreerCallBack(data){

		    $("#listPartieAdverseCreer").empty().append(data);
		    $("#listPartieAdverseCreer").show();
		    
}

function deletePartieAdverseCreer(index){
	if(confirm("Voulez vous vraiment supprimer cette partie adverse ?")) {
		getForm("creerRecoursForm");
			$.ajax({
				   type: "POST",
				   url: "./creationProcJudAction.do?actions=deletePartieAdverseCreer&index="+index,
				   data: values,
				   success: function(data){
						ajoutPartieAdverseCreerCallBack(data);
				   }
				 });
	}
}

function ajoutPartieAdverseBnej(){
	document.recoursForm.actions.value="ajoutPartieAdverseBnej";
	var serialize=$("form[name=recoursForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "./gestionRecoursAction.do?",
		   data: serialize,
		   success: function(data){
				ajoutPartieAdverseBnejCallBack(data);
		   }
		 });
}
function ajoutPartieAdverseBnejCallBack(data){

		    $("#listPartieAdverseBnej").empty().append(data);
		    $("#listPartieAdverseBnej").show();
		    
}

function deletePartieAdverseBnej(index){
	if(confirm("Voulez vous vraiment supprimer cette partie adverse ?")) {
		getForm("recoursForm");
			$.ajax({
				   type: "POST",
				   url: "./gestionRecoursAction.do?actions=deletePartieAdverseBnej&index="+index,
				   data: values,
				   success: function(data){
						ajoutPartieAdverseBnejCallBack(data);
				   }
				 });
	}
}

function ajoutExpertise(){
	document.creerRecoursForm.actions.value="ajoutExpertise";
	var serialize=$("form[name=creerRecoursForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "./creationJugementAction.do?",
		   data: serialize,
		   success: function(data){
				ajoutExpertiseCallBack(data);
		   }
		 });
}

function deleteExpertise(index){
	if(confirm("Voulez vous vraiment supprimer cette expertise ?")) {
		getForm("creerRecoursForm");
			$.ajax({
				   type: "POST",
				   url: "./creationJugementAction.do?actions=deleteExpertise&index="+index,
				   data: values,
				   success: function(data){
						ajoutExpertiseCallBack(data);
				   }
				 });
	}
}
function ajoutExpertiseCallBack(data){

		    $("#listExpertise").empty().append(data);
		    $("#listExpertise").show();
		    
}

function ajoutTaux(){
	document.creerRecoursForm.actions.value="ajoutTaux";
	var serialize=$("form[name=creerRecoursForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "./creationJugementAction.do?",
		   data: serialize,
		   success: function(data){
				ajoutTauxCallBack(data);
		   }
		 });
}

function deleteTaux(index){
	if(confirm("Voulez vous vraiment supprimer cette taux responsabilite ?")) {
		getForm("creerRecoursForm");
			$.ajax({
				   type: "POST",
				   url: "./creationJugementAction.do?actions=deleteTaux&index="+index,
				   data: values,
				   success: function(data){
						ajoutTauxCallBack(data);
				   }
				 });
	}
}
function ajoutTauxCallBack(data){

		    $("#listTaux").empty().append(data);
		    $("#listTaux").show();
		    
}

function ajoutIndemnisation(){
	document.creerRecoursForm.actions.value="ajoutIndemnisation";
	var serialize=$("form[name=creerRecoursForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "./creationJugementAction.do?",
		   data: serialize,
		   success: function(data){
				ajoutIndemnisationCallBack(data);
		   }
		 });
}
function deleteIndemnisation(index){
	if(confirm("Voulez vous vraiment supprimer cette indemnisation ?")) {
		getForm("creerRecoursForm");
			$.ajax({
				   type: "POST",
				   url: "./creationJugementAction.do?actions=deleteIndemnisation&index="+index,
				   data: values,
				   success: function(data){
						ajoutIndemnisationCallBack(data);
				   }
				 });
	}
}
function ajoutIndemnisationCallBack(data){

		    $("#listIndemnisation").empty().append(data);
		    $("#listIndemnisation").show();
		    
}


function ajoutDepens(){
	document.creerRecoursForm.actions.value="ajoutDepens";
	var serialize=$("form[name=creerRecoursForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "./creationDossierAction.do?",
		   data: serialize,
		   success: function(data){
				ajoutDepensCallBack(data);
		   }
		 });
}
function deleteDepens(index){
	if(confirm("Voulez vous vraiment supprimer cette depens ?")) {
		getForm("creerRecoursForm");
			$.ajax({
				   type: "POST",
				   url: "./creationDossierAction.do?actions=deleteDepens&index="+index,
				   data: values,
				   success: function(data){
						ajoutDepensCallBack(data);
				   }
				 });
	}
}
function ajoutDepensCallBack(data){

		    $("#listDepens").empty().append(data);
		    $("#listDepens").show();
		    
}