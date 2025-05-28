jQuery().ready(function (){ 
		rechercheListMission();
});

function openPop(actions) {
	window.open(actions , '', 'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,height=280,width=820,top=200 , left=200');
}

function rechercheListMission(){

		jQuery("#list1").GridUnload();
		var title;
		var param
		if(location.search==""){
			param = "missionATraiter";
		}else{
			param = location.search.split(":")[1];
		}
		
		
		var actions;
		if(param=="missionATraiter"){
			actions="initMissionAtraiter";
			title= "Missions à traiter";
		}
		if(param=="missionSoumise"){
			actions="initMissionSoumises";
			title= "Missions soumises";
		}
		if(param=="missionCloture"){
			actions="initMissionCoturer";
			title= "Missions clôturées";
		}
		url = 'mission.do?actions=getMission&useCase='+actions;		

	// jQGrid setup
	jQuery("#list1").jqGrid({
	url:url,	
	datatype: "xml",
	loadComplete : function (data) {
					if(jQuery("#list1").getUserDataItem('hidden')=='true')
					{
						jQuery("#list1").hideCol('responsable');
					}
				}, 				
 	colModel:[ 
            {name:'numMission',index:'numMission', width:100, align:'center',label:'N° Mission', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}, 
            {name:'codePrestataire',index:'codePrestataire', width:120, align:"center",label:'Code prestataire', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
			{name:'prestataire',index:'prestataire', width:160, align:'center',label:'Prestataire', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},           
            {name:'libelleActivitePrestataire',index:'libelleActivitePrestataire', width:120, align:'center',label:'Activite prestataire', search:true,stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
			{name:'natureMission',index:'natureMission', width:120, align:"center",label:'Nature Mission', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}, 
			{name:'etat',index:'etat', width:200, align:'center',label:'Etat', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
			{name:'dateEtat',index:'dateEtat', width:75,align:'center',label:'Date Etat', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}				
	],
	xmlReader: { repeatitems:false,userdata:"userdata" },
	rowTotal: 12, 
	loadonce:true, 
	rowNum:10, autowidth: true, 
	rowList:[10,20,30], 
	height: "100%", 
	pager: '#pager1',
	sortname: 'numMission',
	viewrecords: true,
	sortorder: "desc",
	caption:title }).navGrid('#pager1', {edit:false,add:false,del:false},
	{}, // default settings for edit
   	{}, // default settings for add
   	{}, // delete
   	{closeOnEscape: true, multipleSearch: true, closeAfterSearch: true }, // search options
   	{}
	);
}

// jQGrid setup pour recherche
function rechercherMission()
{
	$(PAGES_DIV).hide();
	$(PAGES_DIV_BIS).hide();
	jQuery("#listResultRecherche").GridUnload();
 	document.rechercheConsultationForm.actions.value="getMission";
	var serialize=$("form[name=rechercheConsultationForm]").serialize();  
	var title= "Résultat de la recherche";
	var url = "rechercheConsultationAction.do?"+serialize;
	
	jQuery("#listResultRecherche").jqGrid({
	url:url,
	datatype: "xml",
	colModel:[ 
	            {name:'id',index:'id', width:100, align:"center",label:'N° Mission',search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}, 
	            {name:'codePrestataire',index:'codePrestataire', width:120, align:"center",label:'Code prestataire', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
				{name:'libellePrestataire',index:'libellePrestataire', width:160, align:"center",label:'Prestataire', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},            
	            {name:'libelleActivitePrestataire',index:'libelleActivitePrestataire', width:120, align:"center",label:'Activite prestataire', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
				{name:'libelleNatureMission',index:'libelleNatureMission', width:120, align:"center",label:'Nature Mission', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}, 
				{name:'libelleEtat',index:'libelleEtat', width:200, align:"center",label:'Etat', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
				{name:'dateEtat',index:'dateEtat', width:75,align:"center",label:'Date Etat', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
				{name:'numDossier',index:'numDossier', width:120, align:"center",label:'N° Dossier', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}
	],
	xmlReader: { repeatitems:false,userdata:"userdata" },	
	rowTotal: 12, 
	loadonce:true, 
	rowNum:10, autowidth: true, 
	rowList:[10,20,30], 
	height: "100%", 
	pager: '#pagerRes',
	sortname: 'id',
	viewrecords: true,
	sortorder: "desc",
	caption:title }).navGrid('#pagerRes', {edit:false,add:false,del:false});
}

function rechercherQuittance()
{
	$(PAGES_DIV).hide();
	$(PAGES_DIV_BIS).hide();
	jQuery("#listResultRechercheQuit").GridUnload();
 	document.rechercheQuittanceForm.actions.value="getQuittance";
	var serialize=$("form[name=rechercheQuittanceForm]").serialize();  
	var title= "Résultat de la recherche";
	var url = "rechercheQuittanceAction.do?"+serialize;
	
	jQuery("#listResultRechercheQuit").jqGrid({
	url:url,
	datatype: "xml",
	colModel:[ 
	            {name:'numQuittance',index:'numQuittance', width:100, align:"center",label:'N° Quittance', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}, 
	            {name:'idMission',index:'idMission', width:100, align:"center",label:'N° Mission',search:true},
	            {name:'montantQuittance',index:'montantQuittance', width:100, align:"center",label:'Montant Quittance', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}, 	            	            
	            {name:'libelleTypeQuittance',index:'libelleTypeQuittance', width:100, align:"center",label:'Type Quittance', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},	            
	            {name:'codePrestataire',index:'codePrestataire', width:120, align:"center",label:'Code prestataire', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
				{name:'libellePrestataire',index:'libellePrestataire', width:160, align:"center",label:'Prestataire', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},            
	            {name:'libelleActivitePrestataire',index:'libelleActivitePrestataire', width:120, align:"center",label:'Activite prestataire', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}},
				{name:'dateModification',index:'dateModification', width:75,align:"center",label:'Date Création', search:true, stype:'text',searchoptions: { sopt: ['cn', 'nc']}}
	],
	xmlReader: { repeatitems:false,userdata:"userdata" },	
	rowTotal: 12, 
	loadonce:true, 
	rowNum:10, autowidth: true, 
	rowList:[10,20,30], 
	height: "100%", 
	pager: '#pagerResQuit',
	sortname: 'id',
	viewrecords: true,
	sortorder: "desc",
	caption:title }).navGrid('#pagerResQuit', {edit:false,add:false,del:false});
}

var PAGES_DIV = "#refus,#complementInfo, #reponseMission,#poursuite,#reponseComplement,#reglement,";
	PAGES_DIV = PAGES_DIV +"#modification,#precision,#annulation,#retour,#reaffecation,#validerMission,#validerReponseMission";
var PAGES_DIV_BIS=	"#detailMission,#actionZone,#detailQuittance";

var ACTIONS_SELECT;
var selectAction;
var ID_MISSION;
var NUMQUIT;
function getPage(pageToShow){

	
	$(PAGES_DIV).hide();
	if($(pageToShow).val().length==0){
		return 0;
	}
	numMission=ID_MISSION;
	$("#idMission").val(numMission);
	if($(pageToShow).val()=="310"){
		getCreateMissionRattache();
		return 0;
	}
	
	//showDetailMission(numMission);

	if($(pageToShow).val()=="331"){
		getCreationReponse();		
	}
	else if($(pageToShow).val()=="311"){
		getCreationComplement();
	}	
	else if($(pageToShow).val()=="317"){
		getCreationRefus();
	}
	else if($(pageToShow).val()=="312"){
		getCreationReponseComplement();
	}
	else if($(pageToShow).val()=="332"){
		getCreationPoursuite();
	}	
	else if($(pageToShow).val()=="701"){
		getPrestation();
	}		
	else if($(pageToShow).val()=="314"){
		getModification();
	}	
	else if($(pageToShow).val()=="339"){
		getPrecision();
	}	
	else if($(pageToShow).val()=="315"){
		getAnnulation();
	}	
	else if($(pageToShow).val()=="324"){
		getReaffectation();
	}else if($(pageToShow).val()=="313"){
		getValiderMission();
	}else if($(pageToShow).val()=="330"){
		getValiderReponseMission();
	}
	else{
		return 0;
	}	
	
	
}

function showDetailMission(numMission,afficher){
	$(PAGES_DIV).hide();
	$(PAGES_DIV_BIS).hide();
	$("#idMission").val(numMission);
	ID_MISSION=numMission;
	//setTimeout("showDetailMissionCallBack(" + $(domElmt).val() + ")",2000);
	getDetailMission($("#idMission").val(),afficher);	
	if(afficher=='1'){
		getAutorisedActions(2000);
	}
}
function showDetailQuittance(numQuittance,afficher){
	$(PAGES_DIV).hide();
	$(PAGES_DIV_BIS).hide();
	$("#idMission").val(numQuittance);
	NUMQUIT=numQuittance;
	getDetailQuittance($("#idMission").val(),afficher);	
	if(afficher=='1'){
		getAutorisedActions(2000);
	}
}


function getActions(missionID, domElmt){
	ACTIONS_SELECT = domElmt;	
	remplirLaListeActions(missionID)	
	
}
function getAutorisedActions(time){
	remplirLaListeActions(ID_MISSION)
	setTimeout("$('#actionZone').show()",time);	
	
}
function remplirLaListeActions(missionID){
	 DWRUtil.useLoadingMessage('Chargement des données; veuillez patientez SVP ...');		  
	 AjaxAction.getListeActions(missionID,populateListActions);
}
function populateListActions(data){				
		 DWRUtil.removeAllOptions("refActions");
		 DWRUtil.addOptions("refActions", {"":"**************"});
		 DWRUtil.addOptions("refActions",data,"code","titre");		
}
function retour(){
	$(PAGES_DIV).hide();
	$(PAGES_DIV_BIS).hide();
	rechercheListMission();
	//$('#list1').trigger('reloadGrid');
	$("#list1").show();
}
function retourRecherche(){
	$(PAGES_DIV).hide();
	$(PAGES_DIV_BIS).hide();
	//$('#listResultRecherche').trigger('reloadGrid');
	$("#listResultRecherche").show();
}
function retourRechercheQuit(){
	$(PAGES_DIV).hide();
	$(PAGES_DIV_BIS).hide();
	$("#listResultRechercheQuit").show();
}
function block(){
        $.blockUI({ 
            message: "Veuillez patienter ...", 
            fadeIn: 700, 
            fadeOut: 700, 
            timeout: 60000, 
            centerY: true, 
            css: { 
                width: '350px', 
                //top: '10px', 
                //left: '', 
                //right: '10px', 
                border: 'none', 
                padding: '5px', 
                backgroundColor: '#000', 
                '-webkit-border-radius': '10px', 
                '-moz-border-radius': '10px', 
                opacity: 0.5, 
                color: '#fff' 
            } ,
            showOverlay: true,
		    overlayCSS : {
				backgroundColor : '#fff',
				opacity : 0.1
		    }
        }); 
}
 $(document).ajaxStart(function() {block();}).ajaxStop($.unblockUI);
//$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
function setDateInput(){
	// register the criter the recherche  
	/* $('#closingIcon').click(function() { 
	        $('.rechercheMissionBody').slideToggle("fast");
	 });
	*/
		//DateInput
		// the french localization
		
		$.tools.dateinput.localize("fr",  {
		   months:        'janvier,f&eacute;vrier,mars,avril,mai,juin,juillet,ao&ucirc;t,' +
		                   	'septembre,octobre,novembre,d&eacute;cembre',
		   shortMonths:   'jan,f&eacute;v,mar,avr,mai,jun,jul,ao&ucirc;,sep,oct,nov,d&eacute;c',
		   days:          'dimanche,lundi,mardi,mercredi,jeudi,vendredi,samedi',
		   shortDays:     'dim,lun,mar,mer,jeu,ven,sam'
		});
		
		$(":date").dateinput({ 
			lang: 'fr', 
			format: 'dddd, dd mmmm yyyy',
			offset: [30, 0],
			yearRange :	  [-5, 5]
		});
}
function setLibelleNumDossier(formname){
		if(document.forms[formname].codeTypeDossier.value=="2")
		{
			$("#libelleNumDossier").text("N\260 Sinistre");
		}
		else if(document.forms[formname].codeTypeDossier.value=="3")
		{
			$("#libelleNumDossier").text("N\260 Souscription");		
		}	
		else if(document.forms[formname].codeTypeDossier.value=="1")
		{
			$("#libelleNumDossier").text("N\260 Contrat");		
		}
		else if(document.forms[formname].codeTypeDossier.value=="" || document.forms[formname].codeTypeDossier.value==null)
		{
			$("#libelleNumDossier").text("N\260 Dossier");		
		}				
}
function setLibelleNumDossierValider(formname,label){
		if(document.forms[formname].codeTypeDossier.value=="2")
		{
			$("#"+label).text("N\260 Sinistre");
		}
		else if(document.forms[formname].codeTypeDossier.value=="3")
		{
			$("#"+label).text("N\260 Souscription");		
		}	
		else if(document.forms[formname].codeTypeDossier.value=="1")
		{
			$("#"+label).text("N\260 Contrat");		
		}
		else if(document.forms[formname].codeTypeDossier.value=="" || document.forms[formname].codeTypeDossier.value==null)
		{
			$("#"+label).text("N\260 Dossier");		
		}				
}

function showBlock()
{
	if($("input[name=checkedYesNo]:checked").val()!= undefined)
	{
		$("#annulBlock").css("display","block");
	}
	else
	{
		$("#annulBlock").css("display","none");
	}
}
function setLibelleTypeUser(){
		if(document.forms[0].typeUser.value=="I")
		{
			$("#libelleTypeUser").text("N\260 Code utilisateur");
			$("#idLogin").css("display","block");
		}
		else if(document.forms[0].typeUser.value=="E")
		{
			$("#libelleTypeUser").text("N\260 Code prestataire / Login");
			$("#idLogin").css("display","none");		
		}
		else 
		{
			$("#libelleTypeUser").text("N\260 Code utilisateur");
			$("#idLogin").css("display","block");		
		}		
}