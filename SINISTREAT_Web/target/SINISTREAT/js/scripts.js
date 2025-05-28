function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

//{Début}Script pour gérer l'affichage de l'arborescence du menu de navigation. 
function developper(obj) {
var LeBloque = "TR_"+obj.id.substring(4);
var Symbol   = "IMG_"+obj.id.substring(4);

if(obj.etat == null) obj.etat = 'plus';
    if  (obj.etat == 'plus' ) {
       obj.etat = 'moins';
       obj.src = '../images/commun/puce-moins.gif';
       document.getElementById(LeBloque).className = 'OuvrirLeNoue';
       }  
    else {
       obj.etat = 'plus';
       obj.src = '../images/commun/puce-plus.gif';   
       document.getElementById(LeBloque).className = 'FermerLeNoue';
       }
}
//{Fin}Script pour gérer l'affichage de l'arborescence du menu de navigation. 

//{Début}Script pour gérer l'affichage de l'arborescence du menu de navigation. 
function ddevelopper(obj) {
//var LeBloque = "TR_"+obj.id.substring(4);
//var Symbol   = "IMG_"+obj.id.substring(4);

//if(obj.etat == null) obj.etat = 'plus';
    //if  (obj.etat == 'plus' ) {
       //obj.etat = 'moins';
      // obj.src = '../images/commun/puce-moins.gif';
      //alert(LeBloque);
       document.getElementById(obj).className = 'OuvrirLeNoue';
    //   }  
    //else {
    //   obj.etat = 'plus';
      // obj.src = '../images/commun/puce-plus.gif';   
    //   document.getElementById(LeBloque).className = 'FermerLeNoue';
     //  }
}
//{Fin}Script pour gérer l'affichage de l'arborescence du menu de navigation. 
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
//{Début}Script pour gérer l'affichage de l'arborescence du menu de navigation. 
function SSasieEnBloque(obj) 
{
var LeBloque = "TRR_"+obj.id.substring(4);
var Symbol   = "IMG_"+obj.id.substring(4);
var TabId=new Array(); 
var TabIdI=new Array(); 
var TabIdZ=new Array(); 
var TabIdZA=new Array(); 
var TabIdZB=new Array(); 
 var inc=0; 
 var ind=0; 
 var inz=0;
 var inza=0;
 var inzb=0;
 var alltags=document.all? document.all : document.getElementsByTagName("*"); 
	 for (i=0; i<alltags.length; i++)
	 { 
	   try {
		   if (alltags[i].id.substring(0,4)=="TRR_") 
		     TabId[inc++]=alltags[i]; 
		     
		        if (alltags[i].id.substring(0,4)=="TDD_") 
		     TabIdI[ind++]=alltags[i]; 
		     
		      if (alltags[i].id.substring(0,4)=="IMG_") 
		     TabIdZ[inz++]=alltags[i]; 
		       
		        if (alltags[i].id.substring(0,4)=="TGA_") 
		     TabIdZA[inza++]=alltags[i]; 
		       
		        if (alltags[i].id.substring(0,4)=="TDR_") 
		     TabIdZB[inzb++]=alltags[i]; 
		  }catch(err){}
	  } 

     document.getElementById("TDD_"+obj.id.substring(4)).className = 'boutonOnglet15Actif';
     document.getElementById("TRR_"+obj.id.substring(4)).className = 'OuvrirLeNoue';
     document.getElementById("IMG_"+obj.id.substring(4)).className = 'LienOnglet-3';
     document.getElementById("TGA_"+obj.id.substring(4)).className = 'bord-gauche-bouton-3-actif';
     document.getElementById("TDR_"+obj.id.substring(4)).className = 'bord-droit-bouton-3-actif';
      for (i=0; i<TabIdI.length; i++){ 
   if (TabIdI[i].id!="TDD_"+obj.id.substring(4))
    {document.getElementById(TabIdI[i].id).className = 'ongletLinge5';}
      }  
      
      for (i=0; i<TabId.length; i++){ 
   if (TabId[i].id!="TRR_"+obj.id.substring(4))
    {document.getElementById(TabId[i].id).className = 'FermerLeNoue';}
      }  
      
          for (i=0; i<TabIdZ.length; i++){ 
   if (TabIdZ[i].id!="IMG_"+obj.id.substring(4))
    {document.getElementById(TabIdZ[i].id).className = 'LienOnglet';}
      } 
              for (i=0; i<TabIdZA.length; i++){ 
   if (TabIdZA[i].id!="TGA_"+obj.id.substring(4))
    {document.getElementById(TabIdZA[i].id).className = 'bord-gauche-bouton-3';}
      } 
              for (i=0; i<TabIdZB.length; i++){ 
   if (TabIdZB[i].id!="TDR_"+obj.id.substring(4))
    {document.getElementById(TabIdZB[i].id).className = 'bord-droit-bouton-3';}
      } 
}
//{Fin}Script pour gérer l'affichage de l'arborescence du menu de navigation. 
//{Début}Script pour gérer l'affichage de l'arborescence du menu de navigation. 
function SasieEnBloque(obj) {
var LeBloque = "TRR_"+obj.id.substring(4);
var Symbol   = "IMG_"+obj.id.substring(4);

if(obj.etat == null) obj.etat = 'plus';
    if  (obj.etat == 'plus' ) {
       obj.etat = 'moins';
       obj.src = '../images/commun/puce-moins-rouge.gif';
       document.getElementById(LeBloque).className = 'OuvrirLeNoue';
       }  
    else {
       obj.etat = 'plus';
       obj.src = '../images/commun/puce-plus-rouge.gif';   
       document.getElementById(LeBloque).className = 'FermerLeNoue';
       }
}
//{Fin}Script pour gérer l'affichage de l'arborescence du menu de navigation. 























//{Début}Script pour gérer l'affichage de l'arborescence du menu de navigation. 
function RSasieEnBloque(obj) 
{
var LeBloque = "TRR_"+obj.id.substring(4);
var Symbol   = "IMG_"+obj.id.substring(4);
var TabId=new Array(); 
var TabIdZ=new Array(); 
var TabIdI=new Array(); 
 var inc=0; 
 var inz=0;
 var ind=0;
 var alltags=document.all? document.all : document.getElementsByTagName("*"); 
 for (i=0; i<alltags.length; i++)
 { 
   if (alltags[i].id.substring(0,4)=="TRR_") 
     TabId[inc++]=alltags[i]; 
     
      if (alltags[i].id.substring(0,4)=="IMG_") 
     TabIdZ[inz++]=alltags[i]; 
 if (alltags[i].id.substring(0,4)=="TDD_") 
     TabIdI[ind++]=alltags[i]; 
     
      } 
       document.getElementById("TDD_"+obj.id.substring(4)).className = 'active';
         document.getElementById("TRR_"+obj.id.substring(4)).className = 'OuvrirLeNoue';
         
      for (i=0; i<TabId.length; i++){ 
   if (TabId[i].id!="TRR_"+obj.id.substring(4))
    {document.getElementById(TabId[i].id).className = 'FermerLeNoue';}
      }  
          for (i=0; i<TabIdI.length; i++){ 
   if (TabIdI[i].id!="TDD_"+obj.id.substring(4))
    {document.getElementById(TabIdI[i].id).className = '';}
      }  
        
}

//Rabii Add On 7-10-2005
function confirmDeleteIssue(msg,successPage,errorPage){
    var rep;
    rep = confirm(msg);
    if (rep)
        window.navigate(successPage);
}

function confirmDeleteIdentitePR(){

        alert("Veuillez supprimer tout d'abord tous les avis de recherche, demandes de recherche et documents attachés à cette identité");
}


function cocher(n) {
    for(i=0; i<n; i++){
        document.frm1.ch(i).checked=true;
    }
}


function decocher(n) {
    for(i=0; i<n; i++){
        document.frm1.ch(i).checked=false;
    }
}







































/* Author : Rabii@wanadoopro.ma */
/* Load a combo 'Select object' from a file */
function  loadComboFromFile(filename,oSelect){
   var pathToFiles;
   var fso, f, r;
   var ForReading = 1;
   var value, text
   var TabLine, loc;
   
   /* This is a hardcoded value, must change this !!!*/
   pathToFiles = "D://Marouane.Louah//Projet//DGSN//Dev//Maquettes//pre//common//res";
   
   fullpath = pathToFiles + "//" + filename
   //disp("fullpath", fullpath);
   fso = new ActiveXObject("Scripting.FileSystemObject");
   f = fso.OpenTextFile(fullpath, ForReading);
    
   while (!f.AtEndOfStream)
   {
        line =  f.ReadLine();
        TabLine= line.split(",");
        value = TabLine[0];
        text  = TabLine[1];
        addOptionToSelect(text,value,oSelect);
    }
}

/*calculer la date d'expiration*/
function  calculateDateExpiration(){
    dateExpiration.value="2650";
}

/* Add an option to select element passed as a param*/ 
function addOptionToSelect(text, value, oSelect){
    var oOption = document.createElement("OPTION");
    oOption.text=text;
    oOption.value=value;
    oSelect.add(oOption);
}
/* just for displaying info*/ 
function disp(msg,x)
{
    alert(msg + " -> " + x);
}

function addUser() {
    var form = document.editFrm;
    var fl = form.resources.length -1;
    var au = form.assigned.length -1;
    var users = "x";

    //build array of assiged users
    for (au; au > -1; au--) {
        users = users + "," + form.assigned.options[au].value + ","
    }

    //Pull selected resources and add them to list
    for (fl; fl > -1; fl--) {
        if (form.resources.options[fl].selected && users.indexOf( "," + form.resources.options[fl].value + "," ) == -1) {
            t = form.assigned.length
            opt = new Option( form.resources.options[fl].text, form.resources.options[fl].value );
            form.assigned.options[t] = opt
            form.resources.options[fl] = null;
        }
    }
}
function addUser2() {
    var form = document.editFrm3;
    var fl = form.resources.length -1;
    var au = form.assigned.length -1;
    var users = "x";

    //build array of assiged users
    for (au; au > -1; au--) {
        users = users + "," + form.assigned.options[au].value + ","
    }

    //Pull selected resources and add them to list
    for (fl; fl > -1; fl--) {
        if (form.resources.options[fl].selected && users.indexOf( "," + form.resources.options[fl].value + "," ) == -1) {
            t = form.assigned.length
            opt = new Option( form.resources.options[fl].text, form.resources.options[fl].value );
            form.assigned.options[t] = opt
            form.resources.options[fl] = null;
        }
    }
}
function removeUser() {
    var form = document.editFrm;
    fl = form.assigned.length -1;
    
    for (fl; fl > -1; fl--) {
        if (form.assigned.options[fl].selected) {
            t = form.resources.length;
            //alert(form.assigned.options[fl].text);
            opt = new Option(form.assigned.options[fl].text, form.assigned.options[fl].value);
            form.resources.options[t] = opt;
            form.assigned.options[fl] = null;
        }
    }
}
function removeUser2() {
    var form = document.editFrm3;
    fl = form.assigned.length -1;
    
    for (fl; fl > -1; fl--) {
        if (form.assigned.options[fl].selected) {
            t = form.resources.length;
            //alert(form.assigned.options[fl].text);
            opt = new Option(form.assigned.options[fl].text, form.assigned.options[fl].value);
            form.resources.options[t] = opt;
            form.assigned.options[fl] = null;
        }
    }
}

/* This fucntion permits to alert usser about deleting something important 
  - If the user is sure about the issue thant will be deleted, he will be forwarded to the confirmation page
  - If not, no action will be executed
*/
//function confirmDeleteIssue(msgDelete, msgConfirmation,msgError,simulErr){
//  var rep;
//  rep = confirm(msgDelete);
//  if (rep)
//      popup(msgConfirmation);
//  //window.close();   
//}

function confirmDeleteIssue(msg,successPage,errorPage){
    var rep;
    rep = confirm(msg);
    if (rep)
        window.navigate(successPage);
}

function confirmDeleteIdentitePR(){

        alert("Veuillez supprimer tout d'abord tous les avis de recherche, demandes de recherche et documents attachés à cette identité");
}

function popup(msg,url){
    //window.open(url,"Page de confirmation","status = 1,height = 300, width = 300, resizable = 0" );
    newwindow2=window.open('','name','height=400,width=400,resizable=1');
    var tmp = newwindow2.document;
    tmp.write('<html><head><title>Page de confirmation</title>');
    tmp.write('<link rel="stylesheet" href="">');
    tmp.write('</head><body>'+ msg);
    //tmp.write('<p><a href="javascript:alert(self.location.href)">view location</a>.</p>');
    tmp.write('<p><a href="javascript:self.close()">Fermer</a></p>');
    tmp.write('<p><a href="../../pre_index.htm">Retour au menu</a> </p>');
    tmp.write('</body></html>');
    tmp.close();
}

/* TODO HAE : à corriger la mise en forme*/
function addElement() {

            var form = document.editFrm2;

            var fl = form.resources.length -1;

            var au = form.assigned.length -1;

            var users = "x";

 

            //build array of assiged users

            for (au; au > -1; au--) {

                        users = users + "," + form.assigned.options[au].value + ","

            }

 

            //Pull selected resources and add them to list

            for (fl; fl > -1; fl--) {

                        if (form.resources.options[fl].selected && users.indexOf( "," + form.resources.options[fl].value + "," ) == -1) {

                                    t = form.assigned.length

                                    opt = new Option( form.resources.options[fl].text, form.resources.options[fl].value );

                                    form.assigned.options[t] = opt

                                    form.resources.options[fl] = null;

                        }

            }

}


function removeElement() {

            var form = document.editFrm2;

            fl = form.assigned.length -1;

            

            for (fl; fl > -1; fl--) {

                        if (form.assigned.options[fl].selected) {

                                    t = form.resources.length;

                                    //alert(form.assigned.options[fl].text);

                                    opt = new Option(form.assigned.options[fl].text, form.assigned.options[fl].value);

                                    form.resources.options[t] = opt;

                                    form.assigned.options[fl] = null;

                        }

            }

}

function forwardPage(versPage1, versPage2, id){

    if(document.getElementById(id).checked){
        window.navigate(versPage1);
    }
    else {
        window.navigate(versPage2);
    }       
}

function checkAll(checkbox,all) {
    if(all.checked==false){
       for (var i=0; i<checkbox.length;i++) {
                checkbox[i].checked=false ;
       }
     }
     if(all.checked==true){
       for (var i=0; i<checkbox.length;i++) {
                checkbox[i].checked=true ;
       }
     }
}

/******* RMA Table **/

function ShowDiv(obj) 
{
	 document.getElementById(obj).className = 'OuvrirLeNoue';
}



function addRowToTableBeneficiaires()
{
  var tbl = document.getElementById('tblBeneficiaires');
  var lastRow = tbl.rows.length;
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
  // left cell
  //var cellLeft = row.insertCell(0);
  //var textNode = document.createTextNode(iteration);
  //cellLeft.appendChild(textNode);
  
  //  cell 1
  var cell1 = row.insertCell(0);
  var el1 = document.createElement('input');
  el1.setAttribute('type', 'text');
  el1.setAttribute('name', 'txtCol1' + iteration);
  el1.setAttribute('id', 'txtCol1' + iteration);
  //el1.onkeypress = keyPressTest;
  cell1.appendChild(el1);
  
  // cell 2
  var cell2 = row.insertCell(1);
  var el2 = document.createElement('input');
  el2.setAttribute('type', 'text');
  el2.setAttribute('name', 'txtCol2' + iteration);
  el2.setAttribute('id', 'txtCol2' + iteration);
  //el2.onkeypress = keyPressTest;
  cell2.appendChild(el2);
  
  // cell 3
  var cell3 = row.insertCell(2);
  var el3 = document.createElement('input');
  el3.setAttribute('type', 'text');
  el3.setAttribute('name', 'txtCol3' + iteration);
  el3.setAttribute('id', 'txtCol3' + iteration);
  //el3.onkeypress = keyPressTest;
  cell3.appendChild(el3);  
}
function keyPressTest(e, obj)
{
  var validateChkb = document.getElementById('chkValidateOnKeyPress');
  if (validateChkb.checked) {
    var displayObj = document.getElementById('spanOutput');
    var key;
    if(window.event) {
      key = window.event.keyCode; 
    }
    else if(e.which) {
      key = e.which;
    }
    var objId;
    if (obj != null) {
      objId = obj.id;
    } else {
      objId = this.id;
    }
    displayObj.innerHTML = objId + ' : ' + String.fromCharCode(key);
  }
}
function removeRowFromTableBeneficiaires()
{
  var tbl = document.getElementById('tblBeneficiaires');
  var lastRow = tbl.rows.length;
  if (lastRow > 2) tbl.deleteRow(lastRow - 1);
}
function openInNewWindow(frm)
{
  // open a blank window
  var aWindow = window.open('', 'TableAddRowNewWindow',
   'scrollbars=yes,menubar=yes,resizable=yes,toolbar=no,width=400,height=400');
   
  // set the target to the blank window
  frm.target = 'TableAddRowNewWindow';
  
  // submit
  frm.submit();
}
function validateRow(frm)
{
  var chkb = document.getElementById('chkValidate');
  if (chkb.checked) {
    var tbl = document.getElementById('tblBeneficiaires');
    var lastRow = tbl.rows.length - 1;
    var i;
    for (i=1; i<=lastRow; i++) {
      var aRow = document.getElementById('txtRow' + i);
      if (aRow.value.length <= 0) {
        alert('Row ' + i + ' is empty');
        return;
      }
    }
  }
  openInNewWindow(frm);
}


function addRowToTableEnfants()
{
  var tbl = document.getElementById('tblEnfants');
  var lastRow = tbl.rows.length;
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
  // left cell
  //var cellLeft = row.insertCell(0);
  //var textNode = document.createTextNode(iteration);
  //cellLeft.appendChild(textNode);
  
  //  cell 1
  var cell1 = row.insertCell(0);
  var el1 = document.createElement('input');
  el1.setAttribute('type', 'text');
  el1.setAttribute('name', 'eCol1' + iteration);
  el1.setAttribute('id', 'eCol1' + iteration);
  //el1.onkeypress = keyPressTest;
  cell1.appendChild(el1);
  
  // cell 2
  var cell2 = row.insertCell(1);
  var el2 = document.createElement('input');
  el2.setAttribute('type', 'text');
  el2.setAttribute('name', 'eCol2' + iteration);
  el2.setAttribute('id', 'eCol2' + iteration);
  //el2.onkeypress = keyPressTest;
  cell2.appendChild(el2);
  
  // cell 3
  var cell3 = row.insertCell(2);
  var el3 = document.createElement('input');
  el3.setAttribute('type', 'text');
  el3.setAttribute('name', 'eCol3' + iteration);
  el3.setAttribute('id', 'eCol3' + iteration);
  //el3.onkeypress = keyPressTest;
  cell3.appendChild(el3);  
}

function removeRowFromTableEnfants()
{
  var tbl = document.getElementById('tblEnfants');
  var lastRow = tbl.rows.length;
  if (lastRow > 2) tbl.deleteRow(lastRow - 1);
}




/******* End RMA Table **/

