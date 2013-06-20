<table id="admintable"> 
 	<tr id="admintableborder">  
   	   	<td id="admintableborder">name</td>
   	   	<td id="admintableborder">url</td>
   	   	<td id="admintableborder">creation date</td>
   	   	<td id="admintableborder"></td>
  	</tr> 
 	{section name=links loop=$links} 
 	<tr>  
  	   	<td><a href={$links[links].url}>{$links[links].name}</a></td>
     	<td>{$links[links].url}</td>
     	<td>{$links[links].creationdate}</td>
     	<td>
     		<form name='deletelink' method='post' action='index.php?action=deletelink'>
     		<input type='hidden' name='linkid' value='{$links[links].linkid}'>	
     		<input type='submit' value='delete'>
     		</form>
     	</td>
  	</tr> 
	{/section} 
</table> 
<form name='createlink' method='post' action='index.php?action=createlink'>
	<div id='name'>link name: <input type='text' name='linkname'/></div>
	<div id='value'>link url: <input type='text' name='url'/></div>
	<div id='dropdown'>
		<select name='page'>
		{foreach from=$pages item=page}
			<option>{$page}</option>
		{/foreach}
		</select>
	</div>
	<div id='submit'><input type='submit' value='submit'></div>
</form>