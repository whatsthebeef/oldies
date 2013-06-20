<?php
/**
 * Classes which implement the IConnection interface should contain class with static 
 * variable with existing or new connection to the database. 
 */
interface IConnection 
{
	
	/**
	 * getConnection returns a static class variable which holds database handle, the 
	 * handle will be created if it doesn't exist
	 * @return self::$handle
	 */
	public function getConnection(); 	
	
	/**
	 * Handle direct to a specific database passed as argument
	 * @param $database - databse to connect to 
	 */
	public function getDatabaseConnection($database);
	
	/**
	 * Call back function in use to query and process database interaction
	 * executes database function with query
	 * @param $query
	 * @param $callback 
	 * @return result of callback function passed in
	 */
	public function execute($query, $callback);
	
	/**
	 * Standard SQL select statement passing table required to access and conditions
	 * @param $table
	 * @param $conditions
	 * @return unknown_type
	 */
	public function select($table, $conditions);
	
	/**
	 * Standard SQL insert statement passing table to insert into and key
	 * @param $table
	 * @param $key
	 * @return unknown_type
	 */
	public function insert($table, $key);
	
	/**
	 * Standard SQL delete statement passing tables and conditions
	 * @param $table
	 * @param $conditions
	 * @return unknown_type
	 */
	//public function delete($table, $conditions);
	
	/**
	 * Standard database query using the IConnection object 
	 * @param $query
	 * @return unknown_type
	 */
	public function query($query);
	
	/**
	 * gets the primary key of the table passed
	 * @param $tableName
	 * @return primary key of table passed
	 */
	public function getPrimaryKey($tableName);
	
	/**
	 * standard query which can be rolled back if any of it fails
	 * @return unknown_type
	 */
	public function transactionalQuery($queryArray);
	
}
?>