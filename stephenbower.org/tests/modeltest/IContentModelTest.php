<?php

require_once 'model/IContentModel.php';
require_once 'model/ContentModelObject.php';
require_once 'model/ContentModel.php';

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';

require_once 'simpletest/autorun.php';

//Mock::generate('IConnection');

class IContentModelTest extends UnitTestCase
{
	private $contentModel = null;
	private $connection = null;
	private $credentials = null;	
	private $testSetup = null;
	
	function setUp()
	{
		$database = 'wtb';
		$contentModelName = 'test';
		
		$this->testSetup = new TestSetup();
		$this->testSetup->modelTestSetup();
		
		
		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($database);	
		
		$contentNameIdentifier = array('name' => 'test');
		$contentIdIdentifier = array('id' => 'test');
		
		$this->contentModel = ContentModelObject::cast(
			new ContentModel($this->connection, $contentNameIdentifier));
	}
	
	function tearDown() 
	{
		$this->testSetup->tearDown();
		MySQLConnection::$handle = null;
	}
	
	public function testGetContent()
	{
		$content = $this->contentModel->getContent();
		$this->assertEqual($content, 'test');
	}
	
	public function testGetCreationDate()
	{
		$creationDate = $this->contentModel->getCreationDate();
		$this->assertEqual($creationDate, '0000-00-00 00:00:00');
		
		echo "content model tests complete" . "\n";
	}
	
}

?>