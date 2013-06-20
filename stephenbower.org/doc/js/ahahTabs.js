function makeadminactive(tab) 
{ 
	document.getElementById("imagesadmin").className = ""; 	
	document.getElementById("contentadmin").className = ""; 	
	document.getElementById("linksadmin").className = "";
	document.getElementById("pagesadmin").className = "";
	document.getElementById(tab).className = "active";
	callAHAH('content.php?content='+ tab, 'content',
     '', '	'); 
} 

function makeuseractive(tab) 
{ 
	document.getElementById("home").className = ""; 	
	document.getElementById("gallery").className = ""; 	
	document.getElementById("about").className = "";
	document.getElementById("contact").className = "";
	document.getElementById(tab).className = "active";
	callAHAH('content.php?content='+ tab, 'content',
     '', '	'); 
} 