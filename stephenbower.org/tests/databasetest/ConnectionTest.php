<?php

require_once 'testsetup/TestSetup.php';

require_once 'database/IConnection.php';
require_once 'database/MySQLConnection.php';
require_once 'database/MySQLCredentials.php';
require_once 'database/CredentialsObject.php';
require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';

require_once 'simpletest/autorun.php';

class ConnectionTest extends UnitTestCase 
{
	private $credentials = null;
	private $database = null;
	private $connection = null;
	private $testSetup = null;
	
	public function __construct() 
	{
		
	}
	
	public function __destruct() 
	{
     
    }
	
	public function setUp() 
	{
		$this->testSetup = new TestSetup();
		$this->testSetup->modelTestSetup();
		
		$this->connection = $this->testSetup->getConnection(); 
		
	}
	
	public function tearDown()
	{
		$this->testSetup->tearDown();
	}
	
	public function testHandle()
	{ 
		$this->assertNotEqual($this->connection->getConnection(), false);
	}
		
	public function testDatabaseHandle()
	{
		
		$database = 'wtb';
		$this->assertTrue($this->connection->getDatabaseConnection($database));
	}
	
	public function testExecute()
	{
		$query = 'SELECT * FROM pages';
		$this->assertFalse(!($this->connection->execute($query, 'mysql_fetch_assoc')));	
	}
	
	public function testSelect()
	{
		$condition = "name='test'";
		$tables = "pages";
		
		$this->assertFalse(!($this->connection->select($tables, $condition)));
	}
	
	public function testInsert()
	{
		$values = "name='test'";
		$tables = "pages";
		
		$this->assertTrue($this->connection->insert($tables, $values));
	}
	/**
	public function testDelete()
	{
		$condition = "name='test'";
		$tables = "pages";
		
		$this->assertFalse($this->connection->select($tables, $condition));
	}
		**/
	
	public function testQuery()
	{
		$query = 'SELECT * FROM pages';
		$this->assertFalse(!$this->connection->query($query));
	}
	
	public function testGetPrimaryKey()
	{
		$tableName = 'pages';
		
		$id = $this->connection->getPrimaryKey($tableName, $this->database);
		$this->assertEqual($id, 'pageid');
	}
	
	public function testTransactionalQuery()
	{
		$query = array();
		$query[0] = "INSERT INTO pages (creationdate, pageid, name, url) 
				  VALUES (NOW(), 'test', 'test', 'test')";
		$query[1] = "INSERT INTO viewpagerelation (creationdate, relationid, pageid, viewid) 
				  VALUES (NOW(), 'test', 'test', 'test')";	
		$this->connection->transactionalQuery($query);
		$this->assertTrue(mysql_query("SELECT * FROM pages"));
		$this->assertTrue(mysql_query("SELECT * FROM viewpagerelation"));
	}
	
} 

?>