<?php
/*
 * Credentials class contains the data required to connect to the database 
 *  
 */
 interface ICredentials
 {
 	
 	public function getHost();
 
 	public function getDatabase();

 	public function getUsername();
 	
 	public function getPassword();
 		
 }
  
?>