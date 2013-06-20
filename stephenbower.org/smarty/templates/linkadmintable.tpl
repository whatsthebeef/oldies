<table id="linkadmintable"> 
  	<tr>  
   	   	<td>name</td>
   	   	<td>url</td>
   	   	<td>creation date</td>
  	</tr> 
 	{section name=links loop=$links} 
  	<tr>  
     	<td>
        	<a href={$links[links].url}>{$links[links].name}</a> 
     	</td>
     	<td>
        	{$links[links].url} 
     	</td>
     	<td>
        	{$links[links].creationdate} 
     	</td> 
  	</tr> 
 	{/section} 
</table> 
