<?php
require_once 'common/KeyWrapper.php';

require_once 'database/IConnection.php';

require_once 'model/PageModel.php';

class PageModelFactory
{ 
	/**
	 * Factory function which creates models 
	 * @param $connection
	 * @param $database
	 * @param $name
	 * @return unknown_type
	 */
	public static function create(IConnection $connection, $identifier)
	{
		$key = KeyWrapper::generateKey();
		
		$query = "INSERT INTO pages (creationdate, pageid, name) VALUES (NOW(), '$key', '$identifier[name]')";		
		$connection->execute($query, null);
		
		return new PageModel($connection, $identifier);
	}
	
	public static function delete(IConnection $connection, $name)
	{
		$query = "DELETE FROM pages WHERE name='$name'";		
		
		return $connection->execute($query, null);
	}
	
}	

?>