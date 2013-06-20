<table id="admintable"> 
 	<tr id="admintableborder">  
   	   	<td id="admintableborder">name</td>
   	   	<td id="admintableborder">images</td>
   	   	<td id="admintableborder">creation date</td>
   	   	<td id="admintableborder"></td>
  	</tr> 
 	{section name=images loop=$images} 
 	<tr>  
  	   	<td>{$images[images].name}</td>
     	<td>{$images[images].images}</td>
     	<td>{$images[images].creationdate}</td>
     	<td>
     		<form name='deleteimage' method='post' action='index.php?action=deleteimage'>
     		<input type='hidden' name='imageid' value='{$images[images].imageid}'>	
     		<input type='submit' value='delete'>
     		</form>
     	</td>
  	</tr> 
	{/section} 
</table> 
<form method="post" enctype="multipart/form-data"' action='index.php?action=createimage'>
	<div id='value'>
		<input type="hidden" name="MAX_FILE_SIZE" value="2000000">
		<input name="userfile" type="file" id="userfile">
		<input name="upload" type="submit" class="box" id="upload" value="Upload">
	</div>
</form>
<form name='createimage' method='post' action='index.php?action=createimage'>
	<div id='name'>image name: <input type='text' name='imagename'/></div>
	<div id='value'><textarea name='image' rows='10' cols='60'>enter image label:</textarea></div>
	<div id='dropdown'>
		<select name='page'>
		{foreach from=$pages item=page}
			<option>{$page}</option>
		{/foreach}
		</select>
	</div>
	<div id='submit'><input type='submit' value='submit'></div>
</form>