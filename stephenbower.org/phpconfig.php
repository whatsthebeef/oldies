<?php 

if(SERVER_NAME == 'stephenbower.webhop.org') 
{
#	define('STEPHENBOWER_DIR', '/home/wtb/sites/www.stephenbower.org/');
#	define('SMARTY_DIR', '/usr/share/php/smarty/');
	define('STEPHENBOWER_DIR', '/home/samba/webroot/www.stephenbower.org/');
	define('SMARTY_DIR', '/usr/share/php/smarty/');	
}
elseif(SERVER_NAME == 'localhost')
{
	define('STEPHENBOWER_DIR', '/Users/wtb/Sites/www.stephenbower.org/');
	define('SMARTY_DIR', '/usr/local/PEAR/Smarty/');
}
else
{
	define('STEPHENBOWER_DIR', '/Users/wtb/Sites/www.stephenbower.org/');
	define('SMARTY_DIR', '/usr/local/PEAR/Smarty/');
}


?>