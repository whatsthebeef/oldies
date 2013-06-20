<table id="admintable"> 
 	<tr id="admintableborder">  
   	   	<td id="admintableborder">name</td>
   	   	<td id="admintableborder">content</td>
   	   	<td id="admintableborder">creation date</td>
   	   	<td id="admintableborder"></td>
  	</tr> 
 	{section name=content loop=$content} 
 	<tr>  
  	   	<td>{$content[content].name}</td>
     	<td>{$content[content].content}</td>
     	<td>{$content[content].creationdate}</td>
     	<td>
     		<form name='deletecontent' method='post' action='index.php?action=deletecontent'>
     		<input type='hidden' name='contentid' value='{$content[content].contentid}'>	
     		<input type='submit' value='delete'>
     		</form>
     	</td>
  	</tr> 
	{/section} 
</table> 
<form name='createcontent' method='post' action='index.php?action=createcontent'>
	<div id='name'>content name: <input type='text' name='contentname'/></div>
	<div id='value'><textarea name='content' rows='10' cols='60'>enter content:</textarea></div>
	<div id='dropdown'>
		<select name='page'>
		{foreach from=$pages item=page}
			<option>{$page}</option>
		{/foreach}
		</select>
	</div>
	<div id='submit'><input type='submit' value='submit'></div>
</form>