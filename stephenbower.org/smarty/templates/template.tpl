<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
   		<title></title>
   		<meta name="generator" content="HTML 4" />
   		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
   		<meta name="description" content="" />
  	 	<meta name="keywords" content="" />
   		<meta name="author" content="" />
		<link rel="stylesheet" href="css/style.css" type="text/css" />
		<script language="JavaScript"
       		  type="text/javascript" src="js/ahahLib.js"></script> 
		<script language="JavaScript"
       		  type="text/javascript" src=js/ahahTabs.js></script>
	</head>
	<body>
		<div id="container">
			{include file="header.tpl" user=$user}
			<div id="leftpanel">
				{if !$user eq 'user'}
     	 		 	{include file="linkstable.tpl" links=$links}
				{/if}
			</div>
			{if $user eq 'admin'}
			<div id=admincentrepanel>
			{include file="adminview.tpl"}
			{else}
			<div id=centrepanel>
			{include file="userview.tpl"}
			{/if}	
			</div>
			{include file="footer.tpl"}	
		</div>	
	</body>
</div>
</html>
