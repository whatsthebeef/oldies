<table id="linkstable"> 
  	<tr>  
   	   	<td>new links</td>  
  	</tr> 
 	{section name=links loop=$links} 
  	<tr>  
     	<td>
        	<a href={$links[links].url}>{$links[links].name}</a> 
     	</td> 
  	</tr> 
 	{/section} 
</table> 
