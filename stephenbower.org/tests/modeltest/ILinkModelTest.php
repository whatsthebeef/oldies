<?php

require_once 'model/ILinkModel.php';
require_once 'model/LinkModelObject.php';
require_once 'model/LinkModel.php';

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';

require_once 'simpletest/autorun.php';

//Mock::generate('IConnection');

class ILinkModelTest extends UnitTestCase
{
	private $linkModel = null;
	private $connection = null;
	private $credentials = null;

	function setUp()
	{
		$database = 'wtb';
		$linkNameIdentifier = array('name' => 'test');
		$linkIdIdentifier = array('name' => 'test');

		$this->testSetup = new TestSetup();
		$this->testSetup->modelTestSetup();


		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($database);

		try {
			$this->linkModel = LinkModelObject::cast(
			new LinkModel($this->connection, $linkNameIdentifier));
		} catch (Exception $e) {
			var_dump($e->getMessage());
		}
	}

	function tearDown()
	{
		$this->linkModel = null;
		$this->testSetup->tearDown();
		MySQLConnection::$handle = null;
	}

	public function testGetURLLink()
	{
		$url = $this->linkModel->getURLLink();
		$this->assertEqual($url, 'test');
	}

	public function testGetCreationDate()
	{
		$creationDate = $this->linkModel->getCreationDate();
		$this->assertEqual($creationDate, '0000-00-00 00:00:00');
	}

	public function test__ConstructWithId()
	{
		$this->linkModel = null;
		
		$identifier = array('id' => 'test');
		$this->linkModel = LinkModelObject::cast(new LinkModel($this->connection, $identifier));
		$this->assertNotNull($this->linkModel);
	}
}

?>