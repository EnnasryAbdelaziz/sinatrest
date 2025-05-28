/*function getActivitePrestataire(domElment){
	$.ajax({
		   type: "POST",
		   url: "./ajouterMission.do?action=getActivitePrestataire",
		   data: "domaineActivite="+$(domElment).val(),
		   success: function(data){
		    $("#informationPrestataire").empty().append(data);
		   }
		 });

}
function getRegion(domElment){
	$.ajax({
		   type: "POST",
		   url: "./ajouterMission.do?action=getRegion",
		   data: "pays="+$(domElment).val(),
		   success: function(data){
		    $("#informationPrestataire").empty().append(data);
		   }
		 });

}
function getVille(domElment){
	$.ajax({
		   type: "POST",
		   url: "./ajouterMission.do?action=getVille",
		   data: "region="+$(domElment).val(),
		   success: function(data){
		    $("#informationPrestataire").empty().append(data);
		   }
		 });

}
*/
function getList(domElment, action){
	$.ajax({
		   type: "POST",
		   url: "./ajouterMission.do?action="+action,
		   data: $(domElment).attr("name")+"="+$(domElment).val(),
		   success: function(data){
		    $("#ajouterMissionDiv").empty().append(data);
		   }
		 });
}

function getListInformatioMission(domElment, action){
	$.ajax({
		   type: "POST",
		   url: "./ajouterMission.do?action="+action,
		   data: $(domElment).attr("name")+"="+$(domElment).val(),
		   success: function(data){
		    $("#ajouterMissionDiv").empty().append(data);
		   }
		 });
}

jQuery().ready(function (){ 

});
 

