<?php /* Smarty version 2.6.20, created on 2009-04-01 23:03:36
         compiled from header.tpl */ ?>
<div id="header">
		<?php if ($this->_tpl_vars['user'] == 'user'): ?>
		<a href="index.php?action=admin"></a>
		<div id="user_title">
		</div>     	 		 	
		<?php elseif ($this->_tpl_vars['user'] == 'admin'): ?>
		<a href="index.php?action=user">user</a>
		<div id="admin_title">
		</div>
		<?php else: ?>
		<a href="index.php?action=admin">admin</a>
		<div id="user_title">
		</div>     	 		 	
		<?php endif; ?>     	 		 	
</div>