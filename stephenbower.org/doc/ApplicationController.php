<?php

/**
 * Project: www.stephenbower.org php smarty mysql
 * Author: johnbower [DOT] uk [AT] gamil [DOT] com>
 * Date: 25th February 2009
 * File: index.php
 * Version: 1.0
 */

// define our application directory
// define('STEPEHENBOWER_DIR', '/Users/wtb/Sites/www.stephenbower.org/');
// define smarty lib directory
// define('SMARTY_DIR', '/usr/local/PEAR/Smarty/');
// include the setup script
// include(STEPEHENBOWER_DIR . 'libs/guestbook_setup.php');
require_once "libs/SmartySetup.php";

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';

require_once "control/LinkControl.php";
require_once "control/PageControl.php";
require_once "control/ContentControl.php";
require_once "control/ImageControl.php";

class ApplicationController
{
	private $templater = null;
	private $linksController = null;
	
	private $credentials = null;
	private $connection = null;
	private $database = null;
	private $pageControl = null; 
	private $linkControl = null;
	private $contentControl = null;
	
	function __construct()
	{
		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($this->credentials->getDatabase());
		
		$this->templater = new SmartySetup();
		$this->pageControl = new PageControl();
		$this->linkControl = new LinkControl();
		$this->contentControl = new ContentControl();
		$this->imageControl = new ImageControl();
		
	#	$this->templater->debugging = TRUE;
		
	}	
	
	function displayMainPage()
	{
		$this->templater->assign('links', $this->linkControl->getCompleteLinksMap
			($this->connection));
		$this->templater->display('template.tpl');
	}
	
	function displayAdminPage()
	{
		$this->templater->assign('user', 'admin');
        $this->templater->display('template.tpl');
	}
	
	function createPage($request)
	{
		return $this->pageControl->createPage($request, $this->connection);
	}
	
	function deletePage($request)
	{
		return $this->pageControl->deletePage($request, $this->connection);
	}
	
	function createLink($request)
	{
		return $this->linkControl->createLink($request, $this->connection);
	}
	
	function deleteLink($request)
	{
		return $this->linkControl->deleteLink($request, $this->connection);
	}
	
	function createContent($request)
	{
		return $this->contentControl->createContent($request, $this->connection);
	}
	
	function deleteContent($request)
	{
		return $this->contentControl->deleteContent($request, $this->connection);
	}
	
	function createImage($file)
	{
		return $this->imageControl->createImage($file, $this->connection);
	}
	
	function deleteImage($request)
	{
		return $this->imageControl->deleteImage($request, $this->connection);
	}
	
}


?>