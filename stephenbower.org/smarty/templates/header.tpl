<div id="header">
		{if $user eq 'user'}
		<a href="index.php?action=admin"></a>
		<div id="user_title">
		</div>     	 		 	
		{elseif $user eq 'admin'}
		<a href="index.php?action=user">user</a>
		<div id="admin_title">
		</div>
		{else}
		<a href="index.php?action=admin">admin</a>
		<div id="user_title">
		</div>     	 		 	
		{/if}     	 		 	
</div>
