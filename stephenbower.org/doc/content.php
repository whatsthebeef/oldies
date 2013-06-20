<?php
require_once "libs/SmartySetup.php";
require_once "control/PageControl.php";
require_once "control/LinkControl.php";
require_once "control/ContentControl.php";
require_once "control/ImageControl.php";

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';

$templater = new SmartySetup();
$pageControl = new PageControl();
$linkControl = new LinkControl();
$contentControl = new ContentControl();
$imageControl = new ImageControl();

$database = 'wtb';
$credentials = CredentialsObject::cast(new MySQLCredentials());
$connection = ConnectionObject::cast(new MySQLConnection($credentials));
$connection->getDatabaseConnection($database);

if($_GET['content'] == 'imagesadmin') 
{ 
	$templater->assign('images', $imageControl->getCompleteImageMap($connection));
	$templater->assign('pages', $pageControl->getPageStrings("user", $connection));
	$templater->display('imageadmin.tpl'); 
} 
else if($_GET['content'] == 'contentadmin') 
{ 
	$templater->assign('content', $contentControl->getCompleteContentMap($connection));
	$templater->assign('pages', $pageControl->getPageStrings("user", $connection));
	$templater->display('contentadmin.tpl');
} 
else if($_GET['content'] == 'linksadmin') 
{
	$templater->assign('links', $linkControl->getCompleteLinksMap($connection));
	$templater->assign('pages', $pageControl->getPageStrings("user", $connection));
	$templater->display('linkadmin.tpl'); 
}
else if($_GET['content'] == 'pagesadmin') 
{
	$templater->assign('pages', $pageControl->getCompletePageMap($connection));
	$templater->display('pageadmin.tpl');  
}
else if($_GET['content'] == 'home') 
{ 
	echo 'Content for contact page'; 
} 
else if($_GET['content'] == 'gallery') 
{ 
	echo 'Content for gallery'; 
} 
else if($_GET['content'] == 'about') 
{ 
	echo 'Content for about page'; 
}
else if($_GET['content'] == 'contact') 
{
	$templater->display('pageadmin.tpl');  
}


?>