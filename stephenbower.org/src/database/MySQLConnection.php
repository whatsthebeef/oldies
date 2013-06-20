<?php

require_once 'database/IConnection.php';
require_once 'database/ICredentials.php';


/**
 * implements IConnection interface
 * @author wtb
 *
 */
class MySQLConnection implements IConnection 
{	
	/*
	 * static variable acting as a singleton handle so there is only on db connection
	 */
	public static $handle = false;
	
	private $credentials;
	private $database;
	
	/**
	 * constructor always called (interface architecture
	 * @return unknown_type
	 */
	public function __construct(ICredentials $credentials)
	{
		$this->credentials = $credentials;
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/database/IConnection#getConnection()
	 */
	public function getConnection()
	{
		if(!self::$handle) 
		{
			self::$handle = mysql_connect($this->credentials->getHost(), 
				$this->credentials->getUsername(), $this->credentials->getPassword());
			if(!self::$handle) 
			{
				die("Could not connect to the database: " . mysql_error());
			}
		}	
		return self::$handle;
	}
    
	/**
	 * (non-PHPdoc)
	 * @see src/database/IConnection#getDatabaseConnection()
	 */
    public function getDatabaseConnection($database) 
    {
    	if(!self::$handle) 
		{
			self::$handle = mysql_connect($this->credentials->getHost(), 
				$this->credentials->getUsername(), $this->credentials->getPassword());
			if(!self::$handle) 
			{
				die("Could not connect to the database: " . mysql_error());
			}
		}
		$this->database = $database;
			
    	return mysql_select_db($database, self::$handle);	
    }
	
    /**
     * (non-PHPdoc)
     * @see src/database/IConnection#execute()
     */
    public function execute($query, $callback)
    {
   
    	$result = mysql_query($query, self::$handle);
   		if($result && ($callback == null))
   		{
   			return $result;	
   		}
    	else if ($result == false) 
   		{
   			throw new Exception("Could not successfully run query ($query) from DB: " . mysql_error());
		}
		else
		{
    		return $callback($result);
		}	
    }
    
    /**
     * (non-PHPdoc)
     * @see src/database/IConnection#select()
     */
    public function select($tables, $conditions)
    {
    	$query = "SELECT * FROM $tables WHERE $conditions";
    	$result = mysql_query($query, self::$handle);
    	return mysql_fetch_assoc($result);
    }
    
    /**
     * (non-PHPdoc)
     * @see src/database/IConnection#insert()
     */
	public function insert($tables, $values)
    {
    	$query = "INSERT INTO $tables SET $values";
    	return mysql_query($query, self::$handle);
    }
    
    /**
     * (non-PHPdoc)
     * @see src/database/IConnection#query()
     */
    public function query($query)
    {
    	if ($query != null)
    	{
    		return mysql_query($query, self::$handle);
    	}
    	else 
    	{
    		throw new Exception("query emtpy");
    	}
	   
    }
    
    /**
     * (non-PHPdoc)
     * @see src/database/IConnection#getPrimaryKey()
     */
    public function getPrimaryKey($tableName)
    {
		$primaryKeyQuery = "SELECT k.column_name
							FROM information_schema.table_constraints t
							JOIN information_schema.key_column_usage k
							USING(constraint_name,table_schema,table_name)
							WHERE t.constraint_type='PRIMARY KEY'
							AND t.table_schema='$this->database'
							AND t.table_name='$tableName'";

		$result = mysql_query($primaryKeyQuery);
		$row = mysql_fetch_array($result);
	
		return $row[0];;
    }
    
    public function transactionalQuery($queryArray)
    {
    	mysql_query('begin', self::$handle);
    	foreach($queryArray as $query) 
    	{
    		$result = mysql_query($query, self::$handle);	
    	}	
    	if (mysql_error()) 
    	{
    		echo mysql_error();
    		$result = mysql_query('rollback', self::$handle);
    		throw new Exception(e);	
    	}
    	else 
    	{
    		mysql_query('commit', self::$handle);
    	}
    }
    
}
?>