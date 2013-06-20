<?php

require_once 'IConnection.php';

interface IPageModelFactory
{
	
	/**
	 * Function creates a Page entry in the database and thus a IPageModel
	 * @param $connection
	 * @param $name
	 * @return unknown_type
	 */
	public function create(IConnection $connection, $name);

}	

?>