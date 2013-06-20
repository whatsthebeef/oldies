<?php

require_once 'common/KeyWrapper.php';

require_once 'database/IConnection.php';
require_once 'database/MySQLConnection.php';
require_once 'database/MySQLCredentials.php';
require_once 'database/CredentialsObject.php';
require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';

class DatabaseSchema
{
	private $createDatabaseQuery = null; 
	private $createPagesTableQuery = null;
	private $createLinksTableQuery = null;
	private $createContentTableQuery = null;
	private $createImagesTableQuery = null;
	private $createPageLinksRelationQuery = null;
	private $createPageContentRelationQuery = null;
	private $createPageRelationsQuery = null;
	private $createTableTableQuery = null;		
	
	public function __construct(IConnection $connection, $databaseName)
	{
		
		$this->createDatabaseQuery = "CREATE DATABASE $databaseName";
		
		$this->createPagesTableQuery = "CREATE TABLE pages (
  								   pageid VARCHAR(32) NOT NULL, 
  								   name VARCHAR(32) NOT NULL,
  								   url VARCHAR(256) default NULL,
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (pageid)
								   ) ENGINE=INNODB";
		
		$this->createLinksTableQuery = "CREATE TABLE links (
  								   linkid VARCHAR(32) NOT NULL, 
  								   name VARCHAR(32) NOT NULL,
  								   url VARCHAR(256) default NULL,
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (linkid)
								   ) ENGINE=INNODB";
		
		$this->createContentTableQuery = "CREATE TABLE content (
  								   contentid VARCHAR(32) NOT NULL, 
  								   name VARCHAR(32) NOT NULL,
  								   content VARCHAR(4096) default NULL,
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (contentid)
								   ) ENGINE=INNODB";
		
		$this->createImagesTableQuery = "CREATE TABLE images (
  								   imageid VARCHAR(32) NOT NULL, 
  								   name VARCHAR(32) NOT NULL,
  								   type VARCHAR(32) NOT NULL,
  								   size INT NOT NULL,
  								   content MEDIUMBLOB NOT NULL NULL,
  								   label VARCHAR(4096) default NULL,
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (imageid)
								   ) ENGINE=INNODB";	
		
		$this->createViewTableQuery = "CREATE TABLE views (
  								   viewid VARCHAR(32) NOT NULL, 
  								   name VARCHAR(32) NOT NULL,
  								   description VARCHAR(256),
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (viewid)
								   ) ENGINE=INNODB";	
		
		
		$this->createPageLinksRelationQuery = 
								   "CREATE TABLE pagelinksrelation (
  								   relationid VARCHAR(32) NOT NULL, 
  								   pageid VARCHAR(32) NOT NULL,
  								   linkid VARCHAR(32) NOT NULL,  								
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (relationid),
								   INDEX (pageid),
								   FOREIGN KEY (pageid)
								   		REFERENCES pages(pageid),
								   INDEX (linkid),
								   FOREIGN KEY (linkid)
								   		REFERENCES links(linkid)
								   ) ENGINE=INNODB";
		
		$this->createPageContentRelationQuery = 
								   "CREATE TABLE pagecontentrelation (
  								   relationid VARCHAR(32) NOT NULL, 
  								   pageid VARCHAR(32) NOT NULL,
  								   contentid VARCHAR(32) NOT NULL,  								
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (relationid),
								   INDEX (pageid),
								   FOREIGN KEY (pageid)
								   		REFERENCES pages(pageid),
								   INDEX (contentid),
								   FOREIGN KEY (contentid)
								   		REFERENCES content(contentid)
								   ) ENGINE=INNODB";
		
		$this->createPageContentRelationQuery = 
								   "CREATE TABLE pageimagesrelation (
  								   relationid VARCHAR(32) NOT NULL, 
  								   imageid VARCHAR(32) NOT NULL,
  								   pageid VARCHAR(32) NOT NULL,  								
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (relationid),
								   INDEX (pageid),
								   FOREIGN KEY (pageid)
								   		REFERENCES pages(pageid),
								   INDEX (imageid),
								   FOREIGN KEY (imageid)
								   		REFERENCES images(imageid)
								   ) ENGINE=INNODB";

		$this->createTablesTableQuery = "CREATE TABLE tables (
  								   tableid VARCHAR(32) NOT NULL, 
  								   name VARCHAR(32) NOT NULL,
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (tableid)
								   ) ENGINE=INNODB";
		
		$this->createPageRelationsQuery = 
								   "CREATE TABLE pagerelation (
  								   relationid VARCHAR(32) NOT NULL, 
  								   tableid VARCHAR(32) NOT NULL,  								
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (relationid),
								   INDEX (tableid),
								   FOREIGN KEY (tableid)
								   		REFERENCES tables(tableid)
								   ) ENGINE=INNODB";
		
		$this->createViewPageRealtionsTableQuery = 
								  "CREATE TABLE viewpagerelation (
  								   relationid VARCHAR(32) NOT NULL,
  								   pageid VARCHAR(32) NOT NULL,
  								   viewid VARCHAR(32) NOT NULL, 
  								   creationdate DATETIME NOT NULL,
								   PRIMARY KEY (relationid)
								   ) ENGINE=INNODB";
		
		$this->populateUserViewTable = "INSERT INTO views (viewid, name, creationdate) 
									VALUES ('user', 'user', NOW())"; 
		$this->populateAdminViewTable = "INSERT INTO views (viewid, name, creationdate) 
									VALUES ('admin', 'admin', NOW())"; 
					
				
	}
	
	
	public function setUpDatabase()
	{
		$result = mysql_query($this->createDatabaseQuery);
		if (!$result) 
		{
			echo ". unable to create database " . mysql_error();
		}
	
	}
	
	public function setUpTables()
	{	
		$result = mysql_query($this->createPagesTableQuery);
		if (!$result) 
		{
			echo ". unable to create pages table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('pages');
		}
		$result = mysql_query($this->createLinksTableQuery);
		if (!$result) 
		{
			echo ". unable to create links table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('links');
		}
		$result = mysql_query($this->createContentTableQuery);
		if (!$result) 
		{
			echo ". unable to create content table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('content');
		}
		$result = mysql_query($this->createImagesTableQuery);
		if (!$result) 
		{
		 	echo ". unable to create images table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('images');
		}
		$result = mysql_query($this->createPageLinksRelationQuery);
		if (!$result) 
		{
		 	echo ". unable to create pagelinksrel table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('pagelinksrelation');
		}
		$result = mysql_query($this->createPageContentRelationQuery);
		if (!$result) 
		{
		 	echo ". unable to create pagecontentrel table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('pagecontentrelation');
		}
		$result = mysql_query($this->createPageRelationsQuery);
		if (!$result) 
		{
		 	echo ". unable to create pagerels table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('pagerelation');
		}
		$result = mysql_query($this->createViewTableQuery);
		if (!$result) 
		{
		 	echo ". unable to create view table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('views');
		}
		$result = mysql_query($this->createViewPageRealtionsTableQuery);
		if (!$result) 
		{
		 	echo ". unable to create view page relations table " . mysql_error();
		}
		else
		{
			$this->addToTablesTable('viewpagerelation');
		}
		
		$result = mysql_query($this->populateUserViewTable);
		if (!$result) 
		{
		 	echo ". unable to populateUserViewTable tables table " . mysql_error();
		}
		
		
		$result = mysql_query($this->populateAdminViewTable);
		if (!$result) 
		{
		 	echo ". unable to populateAdminViewTable tables table " . mysql_error();
		}
	
	}
	
	private function addToTablesTable($tableName)
	{
		$key = KeyWrapper::generateKey();
		
		$result = mysql_query("INSERT INTO tables (tableid, name, creationdate)
								VALUES('$key', '$tableName'	, NOW())");
		if(!$result)
		{
			echo '. cant insert into tables table ' . mysql_error();
		}
	}
	
}

?>