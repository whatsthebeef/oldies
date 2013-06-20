<?php

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';
require_once 'database/CredentialsObject.php';

class TestSetup
{
	private $pageModel = null;
	private $connection = null;
	private $credentials = null;
	private $database = null;
	
	public function __construct()
	{
		$this->database = 'wtb';
		
		$this->credentials = CredentialsObject::cast(new MySQLCredentials());
		$this->connection = ConnectionObject::cast(new MySQLConnection($this->credentials));
		$this->connection->getDatabaseConnection($this->database);
		
	}
	
	public function getConnection()
	{
		return $this->connection;
	}
	
	public function modelTestSetup()
	{
		$result = mysql_query("DELETE FROM pagelinksrelation");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM pagecontentrelation");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM pageimagesrelation");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM images");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM pages");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM links");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM content");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("INSERT INTO pages (creationdate, pageid, name, url) VALUES
			('0000-00-00 00:00:00', 'test', 'test', 'test')");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("INSERT INTO content (creationdate, contentid, name, content) VALUES
			('0000-00-00 00:00:00', 'test', 'test', 'test')");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("INSERT INTO links (creationdate, linkid, name, url) VALUES
			('0000-00-00 00:00:00', 'test', 'test', 'test')");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("INSERT INTO images (creationdate, imageid, name, type, size, label) VALUES
			('0000-00-00 00:00:00', 'test', 'test', 'test', 1, 'test')");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("INSERT INTO pagelinksrelation (creationdate, pageid, relationid, linkid) 
			VALUES ('0000-00-00 00:00:00', 'test', 'test', 'test')");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("INSERT INTO pagecontentrelation (creationdate, pageid, relationid, contentid) 
			VALUES ('0000-00-00 00:00:00', 'test', 'test', 'test')");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("INSERT INTO pageimagesrelation (creationdate, pageid, relationid, imageid) 
			VALUES ('0000-00-00 00:00:00', 'test', 'test', 'test')");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
	}
	
	public function tearDown()
	{
		$result = mysql_query("DELETE FROM pagelinksrelation");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM pagecontentrelation");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM pageimagesrelation");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM viewpagerelation");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM images");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM images");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM pages");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}	
		$result = mysql_query("DELETE FROM links");
		if(!$result)
		{
			echo 'test setup ' . mysql_error();
		}
		$result = mysql_query("DELETE FROM content");
		if(!$result)
		{
			echo 'test setup  ' . mysql_error();
		}
		
		MySQLConnection::$handle = null;
	} 
}
?>