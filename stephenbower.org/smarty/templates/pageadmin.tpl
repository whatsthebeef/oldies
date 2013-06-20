<table id="admintable"> 
 	<tr id="admintableborder">  
   	   	<td id="admintableborder">name</td>
   	   	<td id="admintableborder">url</td>
   	   	<td id="admintableborder">creation date</td>
   	   	<td id="admintableborder"></td>
  	</tr> 
 	{section name=pages loop=$pages} 
 	<tr>  
  	   	<td><a href={$pages[pages].url}>{$pages[pages].name}</a></td>
     	<td>{$pages[pages].url}</td>
     	<td>{$pages[pages].creationdate}</td>
     	<td>
     		<form name='deletepage' method='post' action='index.php?action=deletepage'>
     		<input type='hidden' name='pageid' value='{$pages[pages].pageid}'>	
     		<input type='submit' value='delete'>
     		</form>
     	</td>
  	</tr> 
	{/section} 
</table>
<form name='createpage' method='post' action='index.php?action=createpage'>
	<div id="name">
		page name: <input type='text' name='pagename'/>
	</div>
	<div id="value">
		url: <input type='text' name='url'/>
	</div>
	<div id="submit">
		<input type='submit' value='submit'>
	</div>
</form>