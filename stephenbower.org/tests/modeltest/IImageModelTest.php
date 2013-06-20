<?php

require_once 'model/IImageModel.php';
require_once 'model/ImageModelObject.php';
require_once 'model/ImageModel.php';

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';

require_once 'simpletest/autorun.php';

//Mock::generate('IConnection');

class IImageModelTest extends UnitTestCase
{
	private $imageModel = null;
	private $connection = null;
	private $credentials = null;	
	
	function setUp()
	{
		$database = 'wtb';
		$imageModelName = 'test';
		
		$this->testSetup = new TestSetup();
		$this->testSetup->modelTestSetup();
		
		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($database);	
		
		$this->imageModel = ImageModelObject::cast(
			new ImageModel($this->connection, $imageModelName));
	}
	
	function tearDown() 
	{
		$this->testSetup->tearDown();
		MySQLConnection::$handle = null;
	}
	
	public function testGetType()
	{
		$type = $this->imageModel->getType();
		$this->assertEqual($type, 'test');
	}
	
	public function testGetCreationDate()
	{
		$creationDate = $this->imageModel->getCreationDate();
		$this->assertEqual($creationDate, '0000-00-00 00:00:00');
	}
	
	public function testGetSize()
	{
		$size = $this->imageModel->getSize();
		$this->assertEqual($size, 1);
	}
	
	public function testGetLabel()
	{
		$label = $this->imageModel->getLabel();
		$this->assertEqual($label, 'test');
	}
}

?>