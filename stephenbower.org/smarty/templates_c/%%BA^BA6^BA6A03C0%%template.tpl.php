<?php /* Smarty version 2.6.20, created on 2009-04-01 23:03:36
         compiled from template.tpl */ ?>
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
			<?php $_smarty_tpl_vars = $this->_tpl_vars;
$this->_smarty_include(array('smarty_include_tpl_file' => "header.tpl", 'smarty_include_vars' => array('user' => $this->_tpl_vars['user'])));
$this->_tpl_vars = $_smarty_tpl_vars;
unset($_smarty_tpl_vars);
 ?>
			<div id="leftpanel">
				<?php if (! $this->_tpl_vars['user'] == 'user'): ?>
     	 		 	<?php $_smarty_tpl_vars = $this->_tpl_vars;
$this->_smarty_include(array('smarty_include_tpl_file' => "linkstable.tpl", 'smarty_include_vars' => array('links' => $this->_tpl_vars['links'])));
$this->_tpl_vars = $_smarty_tpl_vars;
unset($_smarty_tpl_vars);
 ?>
				<?php endif; ?>
			</div>
			<?php if ($this->_tpl_vars['user'] == 'admin'): ?>
			<div id=admincentrepanel>
			<?php $_smarty_tpl_vars = $this->_tpl_vars;
$this->_smarty_include(array('smarty_include_tpl_file' => "adminview.tpl", 'smarty_include_vars' => array()));
$this->_tpl_vars = $_smarty_tpl_vars;
unset($_smarty_tpl_vars);
 ?>
			<?php else: ?>
			<div id=centrepanel>
			<?php $_smarty_tpl_vars = $this->_tpl_vars;
$this->_smarty_include(array('smarty_include_tpl_file' => "userview.tpl", 'smarty_include_vars' => array()));
$this->_tpl_vars = $_smarty_tpl_vars;
unset($_smarty_tpl_vars);
 ?>
			<?php endif; ?>	
			</div>
			<?php $_smarty_tpl_vars = $this->_tpl_vars;
$this->_smarty_include(array('smarty_include_tpl_file' => "footer.tpl", 'smarty_include_vars' => array()));
$this->_tpl_vars = $_smarty_tpl_vars;
unset($_smarty_tpl_vars);
 ?>	
		</div>	
	</body>
</div>
</html>