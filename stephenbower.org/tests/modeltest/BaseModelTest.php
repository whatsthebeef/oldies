<?php

require_once 'database/MySQLCredentials.php';
require_once 'database/ConnectionObject.php';
require_once 'database/MySQLConnection.php';

require_once 'model/BaseModel.php';

require_once 'simpletest/autorun.php';

class BaseModelMock extends BaseModel
{
	
	public function __construct(IConnection $connection) 
	{
	
		parent::__construct($connection);
	
	}
	
}
	
class BaseModelTest extends UnitTestCase
{
	private $connection = null;
	private $credentials = null;
	private $database = null;
	private $baseModelMock = null;
	
	function setup()
	{
		$database = 'wtb';
		
		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($database);
			
	}
	
	public function __construct() 
	{
			
	}
	
	public function __destruct() 
	{
     
    }

	public function testBaseModel()
	{
	
		$this->baseModelMock = new BaseModelMock($this->connection);
		
		$this->assertTrue($this->baseModelMock->getConnection());		
	}
	
} 
?>