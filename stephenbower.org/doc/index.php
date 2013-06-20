	<?php

/**
 * Project: www.stephenbower.org php smarty mysql
 * Author: johnbower [DOT] uk [AT] gamil [DOT] com>
 * Date: 25th February 2009
 * File: index.php
 * Version: 1.0
 */

// define our application directory
// define_once('STEPEHENBOWER_DIR', '/Users/wtb/Sites/www.stephenbower.org/');
// define smarty lib directory
// define_once('SMARTY_DIR', '/usr/local/PEAR/Smarty/');
// include the setup script
// include(STEPEHENBOWER_DIR . 'libs/guestbook_setup.php');
require_once "ApplicationController.php";

// create guestbook object
$applicationController = new ApplicationController();

// set the current action
$_action = isset($_REQUEST['action']) ? $_REQUEST['action'] : 'view';

switch($_action) {
	case 'user':
    	$applicationController->displayMainPage();
    	break;
    case 'admin':
    	$applicationController->displayAdminPage();
    	break;
    case 'createpage':
    	$applicationController->createPage($_REQUEST);
    	$applicationController->displayAdminPage();
    	break;
    case 'createlink':
    	$applicationController->createLink($_REQUEST);
    	$applicationController->displayAdminPage();
    	break;
   	case 'deletelink':
    	$applicationController->deleteLink($_REQUEST);
    	$applicationController->displayAdminPage();
    	break;
   	case 'deletepage':
    	$applicationController->deletePage($_REQUEST);
    	$applicationController->displayAdminPage();
    	break; 
    case 'createcontent':
    	$applicationController->createContent($_REQUEST);
    	$applicationController->displayAdminPage();
    	break;
    case 'deletecontent':
    	$applicationController->deleteContent($_REQUEST);
    	$applicationController->displayAdminPage();
    	break;
    case 'createimage':
    	$applicationController->createImage($_FILE);
    	$applicationController->displayAdminPage();
    	break;
    default:
        $applicationController->displayMainPage();        
        break;   
}

?>