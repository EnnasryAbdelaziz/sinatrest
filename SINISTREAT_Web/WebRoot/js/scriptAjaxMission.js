function callBackTraitement(pageToShow){
	
	$("#"+pageToShow).show();
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);
}

function getDetailQuittance(numQuittance,afficher){
	$.ajax({
	   type: "POST",
	   contentType : "application/x-www-form-urlencoded; charset=ISO-8859-1",
	   url: "./consultationQuittanceBisAction.do?actions=initConsultQuit&numQuittance="+numQuittance,
	   complete: function(data){
	   			getDetailQuittanceCallBack(afficher,data);
   	}
 });
}
function getDetailQuittanceCallBack(afficher,data){
	$("#detailQuittance").empty().append(data.responseText);
	$("#detailQuittance").show();
	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);	
	if(afficher=='1'){
		$("#retour").show();
	}
}
function getDetailMission(numMission,afficher){
	$.ajax({
	   type: "POST",
	   contentType : "application/x-www-form-urlencoded; charset=ISO-8859-1",
	   url: "./consultationMissionAction.do?actions=initConsult&idMission="+numMission,
	   complete: function(data){
	   			getDetailMissionCallBack(afficher,data);
   	}
 });
}
function getDetailMissionCallBack(afficher,data){
	$("#detailMission").empty().append(data.responseText);
	$("#detailMission").show();
	
	$("#list1").hide();
	if(afficher=='1'){
		$("#retour").show();
	}
	
	//accordion setUp
	$("#accordion").accordion();
}
function getConsultEchanges(){
	
	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./consultationEchangeAction.do?actions=initConsult&idMission="+numMission,
	   complete: function(data){
	   			getConsultEchangesCallBack(data);
   	}
 });
}
function getConsultEchangesCallBack(data){
	$("#consultEchange").empty().append(data.responseText);
	$("#listPieceJointeConsultEchange").empty();
	
}


 function getConsultReponses(){
 	
	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./consultationReponseAction.do?actions=initConsult&idMission="+numMission,
	   complete: function(data){
	   			getConsultReponsesCallBack(data);
   	}
 });
}

function getConsultReponsesCallBack(data){
	$("#consultReponse").empty().append(data.responseText);
	$("#listPieceJointeConsultReponse").empty();
	
}
 function getConsultHonoraires(){
 	
	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./consultationQuittanceAction.do?actions=initConsult&idMission="+numMission,
	   complete: function(data){
	   			getConsultHonorairesCallBack(data);
   	}
 });
}

function getConsultHonorairesCallBack(data){
	$("#consultHonoraire").empty().append(data.responseText);
	$("#listConsultReglementQuittance").empty();
	
}

function getConsultJournals(){
 	
	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./consultationJournalMissionAction.do?actions=initConsult&idMission="+numMission,
	   complete: function(data){
	   			getConsultJournalsCallBack(data);
   	}
 });
}

function getConsultJournalsCallBack(data){
	$("#consultJournal").empty().append(data.responseText);
	 
}

function getCreationReponse(){
	
	$.ajax({
	   type: "POST",
	   url: "./creationReponseAction.do?actions=initReponse",
	   complete: function(data){
			getCreationReponseCallBack(data);
   	}
 });
}
function getCreationReponseCallBack(data){
	$("#reponseMission").empty().append(data.responseText);
	$("#reponseMission").show();
	
	$("#list1").hide();
	$("#retour").show();
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);
	
}


function getCreationComplement(){
	
	$.ajax({
	   type: "POST",
	   url: "./creationComplementAction.do?actions=initComplement",
	   complete: function(data){
	   	getCreationComplementCallBack(data);
   	}
 });
}
function getCreationComplementCallBack(data){
	$("#complementInfo").empty().append(data.responseText);
	$("#complementInfo").show();
	
	$("#list1").hide();
	$("#retour").show();	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);	
}
function getCreationRefus(){
	 
	$.ajax({
	   type: "POST",
	   url: "./refusAction.do?actions=initRefus",
	   complete: function(data){
	   		getCreationRefusCallBack(data);
   	}
 });
}
function getCreationRefusCallBack(data){
	$("#refus").empty().append(data.responseText);
	$("#refus").show();
	
	$("#list1").hide();
	$("#retour").show();	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);		
}
function getCreationReponseComplement(){
	
	$.ajax({
	   type: "POST",
	   url: "./creationReponseComplementAction.do?actions=initReponseComplement",
	   complete: function(data){
			getCreationReponseComplementCallBack(data);
			
   	}
 });
}
function getCreationReponseComplementCallBack(data){
	$("#reponseComplement").empty().append(data.responseText);
	$("#reponseComplement").show();
	
	$("#list1").hide();
	$("#retour").show();	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);		
}
function getCreationPoursuite(){
	 
	$.ajax({
	   type: "POST",
	   url: "./poursuiteAction.do?actions=initPoursuite",
	   complete: function(data){
	   		getCreationPoursuiteCallBack(data);
   	}
 });
}
function getCreationPoursuiteCallBack(data){
	$("#poursuite").empty().append(data.responseText);
	$("#poursuite").show();
	
	$("#list1").hide();
	$("#retour").show();	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);			
}
 function getModification(){
	 
 	var numMission=$("#idMission").val(); 
	$.ajax({
	   type: "POST",
	   url: "./modificationMissionAction.do?actions=initModification&idMission="+numMission,
	   complete: function(data){
	   		getModificationCallBack(data);
   	}
 });
}
function getModificationCallBack(data){
	$("#modification").empty().append(data.responseText);
	$("#modification").show();
	
	$("#list1").hide();
	$("#retour").show();	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);			
}
function getPrecision(){
	 
 	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./precisionMissionAction.do?actions=initPrecision&idMission="+numMission,
	   complete: function(data){
	   		getPrecisionCallBack(data);
   	}
 });
}

function getPrecisionCallBack(data){
	$("#precision").empty().append(data.responseText);
	$("#precision").show();
	
	$("#list1").hide();
	$("#retour").show();	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);		
}
 function getAnnulation(){
	 
 	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./annulationMissionAction.do?actions=initAnnulation&idMission="+numMission,
	   complete: function(data){
	   		getAnnulationCallBack(data);
   	}
 });
}
function getAnnulationCallBack(data){
	$("#annulation").empty().append(data.responseText);
	$("#annulation").show();
	
	$("#list1").hide();
	$("#retour").show();	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);			
}

 function getReaffectation(){
	 
 	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./reaffectationPrestataire.do?actions=initReaffectationPrestataire&idMission="+numMission,
	   complete: function(data){
	   		getReaffectationCallBack(data);
   	}
 });
}
function getReaffectationCallBack(data){
	$("#reaffecation").empty().append(data.responseText);	
	$("#reaffecation").show();
	$("#retour").show();
	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);			
}
function validerReaffectation(traitement){
	var numMission=$("#idMission").val(); 
	var serialize=$("form[name=reaffecterMissionForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./reaffectationPrestataire.do?actions="+traitement+"&idMission="+numMission,
	   data: serialize,
	   complete: function(data){
	   		validerReaffectationCallBack(data);
   	}
 });
}
function validerReaffectationCallBack(data){
	getAutorisedActions(10);
	$("#reaffecation").empty().append(data.responseText);
	
}

function getValiderMission(){
	 
 	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./gestionMission.do?actions=consulterMission&idMission="+numMission+"&rattache="+0,
	   complete: function(data){
	   		getValiderMissionCallBack(data);
   	}
 });
}
function getValiderMissionCallBack(data){
	$("#validerMission").empty().append(data.responseText);	
	$("#validerMission").show();
	$("#retour").show();
	$("#list1").hide();
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);			
}

function validerMission(traitement){
	var numMission=$("#idMission").val(); 
	
	var serialize=$("form[name=validerMissionForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./gestionMission.do?actions="+traitement,
	   data: serialize,
	   complete: function(data){
	   		validerMissionCallBack(data);
   	}
 });
}

function validerMissionCallBack(data){
	getAutorisedActions(10);
	$("#validerMission").empty().append(data.responseText);
	
}


function getCreateMission(){
	 
 	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./gestionMission.do?actions=consulterMission&idMission="+numMission+"&rattache="+1,
	   complete: function(data){
	   		getCreateMissionCallBack(data);
   	}
 });
}

function getCreateMissionCallBack(data){
	$("#createMissionRattache").empty().append(data.responseText);	
	$("#createMissionRattache").show();
	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);			
}
function getCreateMissionRattache(){
		var numMission=$("#idMission").val();		
		document.forms[0].action="gestionMission.do?actions=consulterMission&idMission="+numMission+"&rattache="+1;		
		document.forms[0].submit();	
}


function addMissionRattache(traitement){
	var numMission=$("#idMission").val(); 
 	document.getElementById('actions').value=traitement;
	var serialize=$("form[name=validerMissionForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./gestionMission.do?",
	   data: serialize,
	   complete: function(data){
	   		addMissionRattacheCallBack(data);
   	}
 });
}

function addMissionRattacheCallBack(data){
	$("#createMissionRattache").empty().append(data.responseText);
	
}

function validerReponse(){
 	
 	var numMission=$("#idMission").val(); 
 	document.creationReponseForm.actions.value="validerTraitement";
	var serialize=$("form[name=creationReponseForm]").serialize();
	$.ajax({
	   type: "POST",
	   url: "./creationReponseAction.do",
	   data:"idMission="+numMission +"&"+ serialize,
	   complete: function(data){
	   		validerReponseCallBack(data);
   	}
 });
}
function validerReponseCallBack(data){
	getAutorisedActions(10);
	$("#reponseMission").empty().append(data.responseText);
	
	
}
 function validerComplement(){
  	
	var numMission=$("#idMission").val(); 
 	document.creationComplementForm.actions.value="validerTraitement";
	var serialize=$("form[name=creationComplementForm]").serialize();  	
	$.ajax({
	   type: "POST",
	   url: "./creationComplementAction.do?",
	   data:"idMission="+numMission +"&"+ serialize,
	   complete: function(data){
	   		validerComplementCallBack(data);
   	}
 });
}
function validerComplementCallBack(data){
	getAutorisedActions(10);
	$("#complementInfo").empty().append(data.responseText);
	
}
function validerRefus(){
	var numMission=$("#idMission").val(); 
 	document.refusForm.actions.value="validerTraitement";
	var serialize=$("form[name=refusForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./refusAction.do?",
	   data:"idMission="+numMission +"&"+ serialize,
	   complete: function(data){
	   		validerRefusCallBack(data);
   	}
 });
}
function validerRefusCallBack(data){
	getAutorisedActions(10);
	$("#refus").empty().append(data.responseText);
	
}
 function validerPoursuite(){
	var numMission=$("#idMission").val(); 
 	document.poursuiteForm.actions.value="validerTraitement";
	var serialize=$("form[name=poursuiteForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./poursuiteAction.do?",
	   data:"idMission="+numMission +"&"+ serialize,
	   complete: function(data){
	   		validerPoursuiteCallBack(data);
   	}
 });
}
function validerPoursuiteCallBack(data){
	getAutorisedActions(10);
	$("#poursuite").empty().append(data.responseText);
	
}
 function validerModification(){
	var numMission=$("#idMission").val(); 
 	document.modificationForm.actions.value="validerTraitement";
	var serialize=$("form[name=modificationForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./modificationMissionAction.do?",
	   data:"idMission="+numMission +"&"+ serialize,
	   complete: function(data){
	   		validerModificationCallBack(data);
   	}
 });
}
function validerModificationCallBack(data){
	$("#modification").empty().append(data.responseText);
	
}

 function validerPrecision(){
	var numMission=$("#idMission").val(); 
 	document.precisionForm.actions.value="validerTraitement";
	var serialize=$("form[name=precisionForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./precisionMissionAction.do?",
	   data:"idMission="+numMission +"&"+ serialize,
	   complete: function(data){
	   		validerPrecisionCallBack(data);
   	}
 });
}
function validerPrecisionCallBack(data){
	$("#precision").empty().append(data.responseText);
	
}

 function validerAnnulation(traitement){
	var numMission=$("#idMission").val(); 
 	document.annulationForm.actions.value=traitement;
	var serialize=$("form[name=annulationForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./annulationMissionAction.do?",
	   data:"idMission="+numMission +"&"+ serialize,
	   complete: function(data){
	   		validerAnnulationCallBack(data);
   	}
 });
}
function validerAnnulationCallBack(data){
	getAutorisedActions(10);
	$("#annulation").empty().append(data.responseText);
	
}

function annulerQuittance(){
	var numQuittance=$("#idMission").val(); 
	document.consultationQuittanceBisForm.actions.value="annulerQuittance";
	var serialize=$("form[name=consultationQuittanceBisForm]").serialize();
	$.ajax({
	   type: "POST",
	   url: "./consultationQuittanceBisAction.do?",
	   data:"numQuittance="+numQuittance +"&"+ serialize,
	   complete: function(data){
	   		annulerQuittanceCallBack(data);
   	}
 });
}
function annulerQuittanceCallBack(data){
	getAutorisedActions(10);
	$("#detailQuittance").empty().append(data.responseText);
	
}

function validerReponseComplement(){

 	
 	var numMission=$("#idMission").val(); 
 	document.creationReponseComplementForm.actions.value="validerTraitement";
	var serialize=$("form[name=creationReponseComplementForm]").serialize();
	$.ajax({
	   type: "POST",
	   url: "./creationReponseComplementAction.do",
	   data:"idMission="+numMission +"&"+ serialize,
	   complete: function(data){
	   		validerReponseComplementCallBack(data);
   	}
 });
}
function validerReponseComplementCallBack(data){
	getAutorisedActions(10);
	$("#reponseComplement").empty().append(data.responseText);
	
	
}

function fermer(){
	$(PAGES_DIV).hide();
}
function selectListEchanges(typeEchange){

 	
 	var numMission=$("#idMission").val(); 
	$.ajax({
	   type: "POST",
	   url: "./consultationEchangeAction.do?",
	   data:"actions=selectListEchanges&type="+$(typeEchange).val(),
	   complete: function(data){
	   		selectListEchangeCallBack(data);
   	}
 });
}
function selectListEchangeCallBack(data){
	$("#consultEchange").empty().append(data.responseText);
	$("#listPieceJointeConsultEchange").empty();
	
}
function selectPieceEchange(id){

 	
 	var numMission=$("#idMission").val(); 
	$.ajax({
	   type: "POST",
	   url: "./consultationEchangeAction.do?actions=selectPieceEchange&idEchange="+id,
	   complete: function(data){
	   		selectPieceEchangeCallBack(data);
   	}
 });
}
function selectPieceEchangeCallBack(data){
	$("#listPieceJointeConsultEchange").empty().append(data.responseText);
	
}

function selectReglement(id,num){

 	
 	var numMission=$("#idMission").val();
 	if(num==0)
 	{ 
			$.ajax({
			   type: "POST",
			   url: "./consultationQuittanceAction.do?actions=selectReglement&idMissionQuittance="+id,
			   complete: function(data){
			   		selectReglementCallBack(num,data);
		   	}
		 });
	}
	if(num==1)
 	{ 
			$.ajax({
			   type: "POST",
			   url: "./consultationQuittanceBisAction.do?actions=selectReglement&idMissionQuittance="+id,
			   complete: function(data){
			   		selectReglementCallBack(num,data);
		   	}
		 });
	}
}
function selectReglementCallBack(num,data){
	if(num==0)
	{
		$("#listConsultReglementQuittance").empty().append(data.responseText);
	}
	if(num==1)
	{
		$("#listConsultReglementQuittanceBis").empty().append(data.responseText);
	}
	
}
function selectPieceReponse(id){

 	
 	var numMission=$("#idMission").val(); 
	$.ajax({
	   type: "POST",
	   url: "./consultationReponseAction.do?actions=selectPieceReponse&idReponse="+id,
	   complete: function(data){
	   		selectPieceReponseCallBack(data);
   	}
 });
}
function selectPieceReponseCallBack(data){
	$("#listPieceJointeConsultReponse").empty().append(data.responseText);
	
}



function deletePieceJointeReponse(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe !!!")) {
		$.ajax({ 
		       url: "./creationReponseAction.do?actions=deletePieceJointe&index_pieceJointe="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data, status)
				{
					$("#listPieceJointe").empty().append(data.responseText);
				}
				
	    }); 
	}
}

function deletePieceJointeMission(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe !!!")) {
		$.ajax({ 
		       url: "./gestionMission.do?actions=deletePieceJointe&index_pieceJointe="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data, status)
				{
					$("#listPieceJointe").empty().append(data.responseText);
				}
				
	    }); 
	}
}



function addPieceJointeReponse() {
	$.ajaxFileUpload({ 
       url: "./creationReponseAction.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFile',
       dataType:'json',
       complete: function (data, status)
		{
			$("#listPieceJointe").empty().append(data.responseText);
		}
    });  
	
}

function addPieceJointeMission() {
	$.ajaxFileUpload({ 
       url: "./gestionMission.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFile',
       dataType:'json',
       complete: function (data, status)
		{
			$("#listPieceJointe").empty().append(data.responseText);
		}
    });  
	
}



function enregistrerReponse() {
	
	$.ajaxFileUpload({ 
       url: "./creationReponseAction.do?actions=enregistrerReponse",
       secureuri:false,
       dataType:'json',
       success: function (data, status)
		{
			alert(data.responseText);
		},
		error: function (data, status, e)
		{
			alert("Probleme lors de la création : " + data.responseText);
		}
    });  
	
}

function deletePieceJointeComplement(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe !!!")) {
		$.ajax({ 
		       url: "./creationComplementAction.do?actions=deletePieceJointe&index_pieceJointe="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data, status)
				{
					$("#listPieceJointeComplement").empty().append(data.responseText);
				}
				
	    }); 
	}
}

function addPieceJointeComplement() {
	$.ajaxFileUpload({ 
       url: "./creationComplementAction.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFileComp',
       dataType:'json',
       complete: function (data, status)
		{
			$("#listPieceJointeComplement").empty().append(data.responseText);
		}
    });  
	
}

function deletePieceJointePoursuite(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe !!!")) {
		$.ajax({ 
		       url: "./poursuiteAction.do?actions=deletePieceJointe&index_pieceJointe="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data, status)
				{
					$("#listPieceJointePoursuite").empty().append(data.responseText);
				}
				
	    }); 
	}
}

function addPieceJointePoursuite() {
	$.ajaxFileUpload({ 
       url: "./poursuiteAction.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFilePours',
       dataType:'json',
       complete: function (data, status)
		{
			$("#listPieceJointePoursuite").empty().append(data.responseText);
		}
    });  
	
}

function deletePieceJointeReponseComplement(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe !!!")) {
		$.ajax({ 
		       url: "./creationReponseComplementAction.do?actions=deletePieceJointe&index_pieceJointe="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data, status)
				{
					$("#listPieceJointeReponseComplement").empty().append(data.responseText);
				}
				
	    }); 
	}
}

function addPieceJointeReponseComplement() {
	$.ajaxFileUpload({ 
       url: "./creationReponseComplementAction.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFileRepComp',
       dataType:'json',
       complete: function (data, status)
		{
			$("#listPieceJointeReponseComplement").empty().append(data.responseText);
		}
    });  
	
}

function deletePieceJointeModif(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe !!!")) {
		$.ajax({ 
		       url: "./modificationMissionAction.do?actions=deletePieceJointe&index_pieceJointe="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data, status)
				{
					$("#listPieceJointeModif").empty().append(data.responseText);
				}
				
	    }); 
	}
}

function addPieceJointeModif() {
	$.ajaxFileUpload({ 
       url: "./modificationMissionAction.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFileModif',
       dataType:'json',
       complete: function (data, status)
		{
			$("#listPieceJointeModif").empty().append(data.responseText);
		}
    });  
	
}

function deletePieceJointePrecision(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe !!!")) {
		$.ajax({ 
		       url: "./precisionMissionAction.do?actions=deletePieceJointe&index_pieceJointe="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data, status)
				{
					$("#listPieceJointePrecision").empty().append(data.responseText);
				}
				
	    }); 
	}
}

function addPieceJointePrecision() {
	$.ajaxFileUpload({ 
       url: "./precisionMissionAction.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFilePrecis',
       dataType:'json',
       complete: function (data, status)
		{
			$("#listPieceJointePrecision").empty().append(data.responseText);
		}
    });  
	
}

function getReferencesParNature(domElment){
	$.ajax({
		   type: "POST",
		   url: "./gestionMission.do?actions=getNatureMissionReference",
		   data: "refNatureMission="+$(domElment).val(),
		   success: function(data){	  
		    $("#references").empty().append(data);
		   }
		 });
}
function getReferencesParNatureMod(domElment){
	$.ajax({
		   type: "POST",
		   url: "./modificationMissionAction.do?actions=getNatureMissionReference",
		   data: "idNatureMission="+$(domElment).val(),
		   success: function(data){	  
		    $("#referencesMod").empty().append(data);
		   }
		 });
}

function getReferencesParNatureRattache(domElment){
	$.ajax({
		   type: "POST",
		   url: "./gestionMission.do?actions=getNatureMissionReference",
		   data: "refNatureMission="+$(domElment).val(),
		   success: function(data){	  
		    $("#referencesRattache").empty().append(data);
		   }
		 });
}
//######################### Valider Response  Mission ###############################//

function getValiderReponseMission(){
	 
 	var numMission=$("#idMission").val();
	$.ajax({
	   type: "POST",
	   url: "./validerReponseMission.do?actions=initValiderReponseMission&idMission="+numMission,
	   complete: function(data){
	   		getValiderReponseMissionCallBack(data);
   	}
 });
}
function getValiderReponseMissionCallBack(data){
	$("#validerReponseMission").empty().append(data.responseText);	
	$("#validerReponseMission").show();
	
	var numMission=$("#idMission").val();
	$("td.legend span.ui-jqgrid-title b").html(numMission);			
}
function reponseMissionAction(traitement){
	var numMission=$("#idMission").val(); 
	var serialize=$("form[name=validerReponseMissionForm]").serialize();  
	$.ajax({
	   type: "POST",
	   url: "./validerReponseMission.do?actions="+traitement+"&idMission="+numMission,
	   data: serialize,
	   complete: function(data){
	   		reponseMissionActionCallBack(data);
   	}
 });
}
function reponseMissionActionCallBack(data){
	getAutorisedActions(10);
	$("#validerReponseMission").empty().append(data.responseText);
	
}
function addPieceJointeValiderReponseMission() {
	$.ajaxFileUpload({ 
       url: "./validerReponseMission.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFileValiderRep',
       dataType:'json',
       complete: function (data, status)
		{
			$("#listPieceJointeValiderReponseMission").empty().append(data.responseText);
		}
    });  
	
}

function deletePieceJointeValiderReponseMission(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe!")) {
		$.ajax({ 
		       url: "./validerReponseMission.do?actions=deletePieceJointe&index_pieceJointe="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data, status)
				{
					$("#listPieceJointeValiderReponseMission").empty().append(data.responseText);
				}
				
	    }); 
	}
}
//######################### End Valider Response  Mission ###############################//
