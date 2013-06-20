<?php
/*
 * Credentials class contains the data required to connect to the database 
 *  
 */
require_once 'database/ICredentials.php';

 class MySQLCredentials implements ICredentials
 {
 
 	private $host     = 'ubuntu';
 	private $database = 'wtb';
 	private $username = 'wtb';
 	private $password = 'audr3y1104';
 	
 	static public function cast(ICredentials $object) 
 	{  
		return $object;
 	}  
 	
 	public function __construct() 
 	{
 		
 	}
 	
 	public function getHost()
 	{
 		return $this->host;
 	}
 	
 	public function getDatabase()
 	{
 		return $this->database;
 	}
 	
 	public function getUsername()
 	{
 		return $this->username;
 	}
 	
 	public function getPassword(){
 		return $this->password;
 	}
 		
 }
  
?>