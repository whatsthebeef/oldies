<?php

require_once 'control/LinksController.php';
require_once 'simpletest/autorun.php';
require_once 'testsetup/TestSetup.php';

class LinksControllerTest extends UnitTestCase
{	
	private $linksController = null;
	private $pageModel = null;
	private $connection = null;
	private $credentials = null;	
	private $testSetup = null;
	
	function setUp()
	{
		$this->linksController = new LinksController();	
		$database = 'wtb';
		$contentModelName = 'test';
		
		$this->testSetup = new TestSetup();
		$this->testSetup->modelTestSetup();
		
		
		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($database);	
		
		$contentNameIdentifier = array('name' => 'test');
		$contentIdIdentifier = array('id' => 'test');
	}
	
	function tearDown() 
	{
		$this->testSetup->tearDown();
		MySQLConnection::$handle = null;
	}
	
	public function testGetLinksMap()
	{	
		$contentNameIdentifier = array('name' => 'test');
		$contentIdIdentifier = array('id' => 'test');
		$this->assertNotNull($this->linksController->getLinksMap($this->connection, $contentNameIdentifier));		
	}
	
	public function testCreateLink()
	{
		$contentNameIdentifier = array('name' => 'createtest1');
		$contentIdIdentifier = array('id' => 'test');
		$this->assertNotNull($this->linksController->createLink($this->connection, $contentNameIdentifier));
	}
		
}

?>