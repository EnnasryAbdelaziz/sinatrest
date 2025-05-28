var values = {};

function getForm(formName){
$.each($('[name='+formName+']').serializeArray(), function(i, field) {
    values[field.name] = field.value;
});
}

function setAction(arg){
            document.reglementForm.action="reglementAction.do";            
            document.reglementForm.actions.value=arg;
            document.reglementForm.submit();  
}

function getPrestation(){
 	var numMission=$("#idMission").val(); 
	$.ajax({
		   type: "POST",
		   url: "./reglementAction.do?actions=getPrestation&idMission="+numMission,
		   success: function(data){
				getPrestationCallBack(data);
		   }
		 });

}
function getPrestationCallBack(data){
		    $("#reglement").empty().append(data);
		    $("#reglement").show();
		    $("#accordionReg").accordion();
		    var numMission=$("#idMission").val();
		    $("#retour").show();
			$("td.legend span.ui-jqgrid-title b").html(numMission);

}

function getTarifPrestation(){
getForm("reglementForm");
var numMission=$("#idMission").val();
	$.ajax({
		   type: "POST",
		   url: "./reglementAction.do?actions=getTarifPrestation&idMission="+numMission,
		   data: values,
		   success: function(data){
				getPrestationCallBack(data);
		   }
		 });

}

function ajoutPrestation(){
//alert($("select[name=prestation]").value);
if ($('[name=prestation]').value==''){
	alert('la prestation doit être renseignée');
}else if ($('[name="montantCalcule"]').value==''){
	alert('le montant calculé doit être renseignée');
}else if ($('[name="montantAccorde"]').value==''){
	alert('le montant accordé doit être renseigné');
}else{
	getForm("reglementForm");
		$.ajax({
			   type: "POST",
			   url: "./reglementAction.do?actions=ajoutPrestation",
			   data: values,
			   success: function(data){
					getPrestationCallBack(data);
			   }
			 });
}
}

function deletePrestation(index){
if(confirm("Voulez vous vraiment supprimer cette prestation ?")) {
	getForm("reglementForm");
		$.ajax({
			   type: "POST",
			   url: "./reglementAction.do?actions=deletePrestation&index="+index,
			   data: values,
			   success: function(data){
					getPrestationCallBack(data);
			   }
			 });
}
}

function validerReglement(){
getForm("reglementForm");
var numMission=$("#idMission").val();
	$.ajax({
		   type: "POST",
		   url: "./reglementAction.do?actions=validerReglement&idMission="+numMission,
		   data: values,
		   success: function(data){
				getPrestationCallBack(data);
		   }
		 });

}

function addPieceJointeReglement() {

	$.ajaxFileUpload({ 
       url: "./reglementAction.do?actions=addPieceJointe",
       secureuri:false,
       fileElementId:'formFileReglement',
       dataType:'json',
       complete: function (data){
			$("#listPieceJointeReglement").empty().append(data.responseText);
		}
    });  
	
}


function deletePieceJointeReglement(index) {
if(confirm("Voulez vous vraiment supprimer la piece jointe ?")) {
		$.ajax({ 
		       url: "./reglementAction.do?actions=deletePieceJointe&index="+index,
		       secureuri:false,
		       dataType:'json',
		       complete: function (data){
					$("#listPieceJointeReglement").empty().append(data.responseText);
				}
				
	    }); 
	}
}