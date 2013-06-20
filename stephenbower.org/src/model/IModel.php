<?php

/**
 * Interface implemented generally by model objects which access databse connection instance
 */
 
interface IModel 
{
	
	/**
	 * gets Connection model has to database 
	 * @return unknown_type
	 */
	public function getConnection();
	
	/**
	 * set name of model objects
	 * @return unknown_type
	 */
	public function getName();
	
	/**
	 * gets creation date of object related to ID in associated table
	 * @return unknown_type
	 */
	public function getCreationDate();
	
	public function getID();
	
	
} 

?>