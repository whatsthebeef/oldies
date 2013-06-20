<?php
//require_once 'common/ModelEnum.php';

require_once 'simpletest/autorun.php';

require_once 'testsetup/TestSetup.php';

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';
require_once 'database/CredentialsObject.php';

require_once 'factory/ModelFactory.php';

require_once 'common/TableMap.php';

class ModelFactoryTest extends UnitTestCase
{
	private $credentials = null;
	private $connection = null;
	private $modelFactory = null;
	private $database = null;
	private $testSetup = null;
	
	
	function setUp()
	{
		$this->testSetup = new TestSetup();
		$this->testSetup->tearDown();
		
		$this->database = 'wtb';
		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($this->database);
				
	}
	
	function tearDown()
	{
		$this->testSetup->tearDown();
		MySQLConnection::$handle = null;
	}
	
	public function testCreateLink()
	{
		$identifer = array('name' => 'test');
		
		$this->assertEqual(ModelFactory::createLink($this->connection, 
			$identifer)->getName(), $identifer['name']);
	}
	
	public function testCreateContent()
	{
		$identifer = array('name' => 'test');
		
		$this->assertEqual(ModelFactory::createContent($this->connection,
			$identifer)->getName(), $identifer['name']);
	}
	
	public function testCreatePage()
	{
		$identifer = array('name' => 'test');
		
		$this->assertEqual(ModelFactory::createPage($this->connection, 
			$identifer)->getName(), $identifer['name']);
	}
	
	public function testCreateImage()
	{
		$identifer = array('name' => 'test');
		
		$this->assertEqual(ModelFactory::createImage($this->connection,
			$identifer)->getName(), $identifer['name']);
	}
	
	public function testDeleteLink()
	{
		$identifer = array('name' => 'test');
		
		ModelFactory::deleteLink($this->connection, $identifer);
		$result = mysql_query("SELECT * FROM pages WHERE name=$name");
		$this->assertFalse($result);
	}
	
	public function testDeleteImage()
	{
		$identifer = array('name' => 'test');
		
		ModelFactory::deleteImage($this->connection, $identifer);
		$result = mysql_query("SELECT * FROM pages WHERE name=$name");
		$this->assertFalse($result);
	}
	
	public function testDeleteContent()
	{
		$identifer = array('name' => 'test');
		
		ModelFactory::deleteContent($this->connection, $identifer);
		$result = mysql_query("SELECT * FROM pages WHERE name=$name");
		$this->assertFalse($result);
	}
	
	public function testDeletePage()
	{
		$identifer = array('name' => 'test');
		
		ModelFactory::deletePage($this->connection, $identifer);
		$result = mysql_query("SELECT * FROM pages WHERE name=$name");
		$this->assertFalse($result);
	}
}

?>