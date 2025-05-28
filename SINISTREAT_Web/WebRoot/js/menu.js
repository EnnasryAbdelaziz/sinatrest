function SwitchMenu(obj){

	var id;
	var indice;
	var ar;
	var ar1;
	var ar2;
	var ar3;
	var ar4;
	var ar5;
							
	var arList;
	indice = obj.indexOf('.')+1;

	if (indice == 0){
   		ar = document.getElementById(obj);
   		if (ar.style.display != "block"){
   			
   			ar1 = document.getElementById('parametrage');
 			ar2 = document.getElementById('mission');
			
			
			if (ar1!=null) ar1.style.display = "none";
			if (ar2!=null) ar2.style.display = "none";			
			
												
			if (ar!=null) ar.style.display = "block";
   			}
   			
   		else{   		 
   			
   			ar.style.display = "none";
   			
	   			arList = ar.getElementsByTagName("span");
	   			
	   			for (var i=0; i<arList.length; i++){
	   			
				arList[i].style.display = "none";
			    }
			    ar1 = document.getElementById('parametrage');
 				ar2 = document.getElementById('mission');
				
				 			   			 						
				
				if (ar1!=null) ar1.style.display = "none";
				if (ar2!=null) ar2.style.display = "none";
						 			   			    			  														
   		}
			
	}else{
	var table = document.getElementById("menuTab");
	arList = table.getElementsByTagName("span");
	for (var i=0; i<arList.length; i++){
		arList[i].style.display = "none";
	}
	var list = obj.split('.');
	id='';
	for (var i=0; i<list.length; i++){
		if (i==0)
   			id = list[i];
   		else
   			id = id +'.'+ list[i];   		
	   	ar = document.getElementById(id);
	   	ar.style.display = "block";	   			   		   		
	}
	
	ar1 = document.getElementById('parametrage');	
	}	
}
function test(){
}

function numbersonly(myfield, e, dec){
	var key;
	var keychar;
	if (window.event)
 		key = window.event.keyCode;
	else if (e)
 		key = e.which;
	else
 		return true;
 		
	keychar = String.fromCharCode(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
 		return true;
	// numbers
	else if ((("-0123456789.").indexOf(keychar) > -1))
 		return true;
	// decimal point jump
	else if (dec){
 		myfield.form.elements[dec].focus();
 		return false;
 	}
	else
 		return false;
}