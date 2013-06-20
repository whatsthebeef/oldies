<?php

require_once 'simpletest/autorun.php';

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';
require_once 'database/CredentialsObject.php';

require_once 'factory/PageModelFactory.php';

class PageModelFactoryTest extends UnitTestCase
{
	private $credentials = null;
	private $connection = null;
	private $pageModelFactory = null;
	private $database = null;
	
	function setUp()
	{
		$this->database = 'wtb';
		
		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($this->database);	
	}
	
	function tearDown()
	{
		MySQLConnection::$handle = null;
	}
	
	public function testCreate()
	{
		
		$identifier = array('name' => 'test');
		
		$this->assertEqual(PageModelFactory::create($this->connection, $identifier)->getName(), $identifier['name']);
	}
	
	public function testDelete()
	{
		$name = 'test';
		
		PageModelFactory::delete($this->connection, $name);
		$result = mysql_query("SELECT * FROM pages WHERE name=$name");
		$this->assertFalse($result);
	}
}

?>