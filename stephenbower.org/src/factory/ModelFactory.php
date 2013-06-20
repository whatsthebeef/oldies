<?php

require_once 'common/KeyWrapper.php';
//require_once 'common/ModelEnum.php';

require_once 'database/IConnection.php';

require_once 'model/PageModel.php';
require_once 'model/ContentModel.php';
require_once 'model/ImageModel.php';
require_once 'model/LinkModel.php';

/**
 * A model factory which acts as an api for database creating and deleting different model types models  
 * @author wtb
 *
 */
class ModelFactory
{ 

	public static function createLink(IConnection $connection, $identifier, $url, $pageIdentifier) 
	{
		$handle = $connection->getConnection();
		$query = array();
		$linkKey = KeyWrapper::generateKey();
		$relationKey = KeyWrapper::generateKey();
		mysql_query('BEGIN', $handle);
		mysql_query("INSERT INTO links (creationdate, linkid, name, url) 
				  VALUES (NOW(), '$linkKey', '$identifier[name]', '$url')", 
				  $handle);
		$row = mysql_fetch_assoc(mysql_query("SELECT * FROM links WHERE 
				name='$identifier[name]'",  $handle));		
		$pageID = new PageModel($connection, $pageIdentifier);
		$pageIDString =  $pageID->getID();
		mysql_query("INSERT INTO pagelinksrelation (creationdate, relationid, pageid, 
					linkid) VALUES (NOW(), '$relationKey', '$pageIDString', '$linkKey')", 
					$handle);
		if(mysql_error()) 
    	{
    		echo mysql_error();
    		$result = mysql_query('ROLLBACK', $handle);
    		throw new Exception(e);	
    	}
    	else 
    	{
    		mysql_query('COMMIT', $handle);
    	}
		return new LinkModel($connection, $identifier);
	}
	
	public static function createPage(IConnection $connection, $identifier, $url) 
	{
		$query = array();
		$pageKey = KeyWrapper::generateKey();
		$relationKey = KeyWrapper::generateKey();
		$query[0] = "INSERT INTO pages (creationdate, pageid, name, url) 
				  VALUES (NOW(), '$pageKey', '$identifier[name]', '$url')";
		$query[1] = "INSERT INTO viewpagerelation (creationdate, relationid, pageid, viewid) 
				  VALUES (NOW(), '$relationKey', '$pageKey', 'user')";
		$connection->transactionalQuery($query);
		return new PageModel($connection, $identifier);
	}
/*	
	public static function createImage(IConnection $connection, $identifier, $pageIdentifier, $type, 
		$size, $content, $label) 
	{
		$handle = $connection->getConnection();
		$query = array();
		$imageKey = KeyWrapper::generateKey();
		$relationKey = KeyWrapper::generateKey();
		mysql_query('BEGIN', $handle);
		mysql_query("INSERT INTO images (creationdate, contentid, name, type, size, content, label) 
				  VALUES (NOW(), '$imageKey', '$identifier[name]', $type, $size, '$content', 
				  '$label')", $handle);
		$row = mysql_fetch_assoc(mysql_query("SELECT * FROM images WHERE 
				name='$identifier[name]'",  $handle));		
		$pageID = new PageModel($connection, $pageIdentifier);
		$pageIDString =  $pageID->getID();
		mysql_query("INSERT INTO pagecontentrelation (creationdate, relationid, pageid, 
					imageid) VALUES (NOW(), '$relationKey', '$pageIDString', '$imageKey')", 
					$handle);
		if(mysql_error()) 
    	{
    		$result = mysql_query('ROLLBACK', $handle);
    		throw new Exception(mysql_error() . "unable to be create new image", 7);	
    	}
    	else 
    	{
    		mysql_query('COMMIT', $handle);
    	}
		return new ImageModel($connection, $identifier);
	}
*/	
	
	public static function createImage(IConnection $connection, $identifier, $pageIdentifier, $type, 
		$size, $content, $label) 
	{
		$handle = $connection->getConnection();
		$query = array();
		$imageKey = KeyWrapper::generateKey();
		$relationKey = KeyWrapper::generateKey();
	//	mysql_query('BEGIN', $handle);
		mysql_query("INSERT INTO images (creationdate, contentid, name, type, size, content, label) 
				  VALUES (NOW(), '$imageKey', '$identifier[name]', $type, $size, '$content', 
				  '$label')", $handle);
		mysql_query($query);
	//	$row = mysql_fetch_assoc(mysql_query("SELECT * FROM images WHERE 
	//			name='$identifier[name]'",  $handle));		
	//	$pageID = new PageModel($connection, $pageIdentifier);
	//	$pageIDString =  $pageID->getID();
	//	mysql_query("INSERT INTO pagecontentrelation (creationdate, relationid, pageid, 
	//				imageid) VALUES (NOW(), '$relationKey', '$pageIDString', '$imageKey')", 
	//				$handle);
		if(mysql_error()) 
    	{
    //		$result = mysql_query('ROLLBACK', $handle);
    		throw new Exception(mysql_error() . "unable to be create new image", 7);	
    	}
    //	else 
  	//  {
    //		mysql_query('COMMIT', $handle);
    //	}
		return new ImageModel($connection, $identifier);
	}
	
	public static function createContent(IConnection $connection, $identifier, $content, $pageIdentifier) 
	{
		$handle = $connection->getConnection();
		$query = array();
		$contentKey = KeyWrapper::generateKey();
		$relationKey = KeyWrapper::generateKey();
		mysql_query('BEGIN', $handle);
		mysql_query("INSERT INTO content (creationdate, contentid, name, content) 
				  VALUES (NOW(), '$contentKey', '$identifier[name]', '$content')", 
				  $handle);
		$row = mysql_fetch_assoc(mysql_query("SELECT * FROM content WHERE 
				name='$identifier[name]'",  $handle));		
		$pageID = new PageModel($connection, $pageIdentifier);
		$pageIDString =  $pageID->getID();
		mysql_query("INSERT INTO pagecontentrelation (creationdate, relationid, pageid, 
					contentid) VALUES (NOW(), '$relationKey', '$pageIDString', '$contentKey')", 
					$handle);
		if(mysql_error()) 
    	{
    		echo mysql_error();
    		$result = mysql_query('ROLLBACK', $handle);
    		throw new Exception("unable to be create new content", 3);	
    	}
    	else 
    	{
    		mysql_query('COMMIT', $handle);
    	}
		return new ContentModel($connection, $identifier);
	}

	public static function deleteLink(IConnection $connection, $identifier)
	{
		if(isset($identifier['name'])) 
		{ 
			$queryRelations = "DELETE FROM pagelinksrelation WHERE linkid='$identifier[name]'";
			$connection->execute($queryRelations, null);
			$query = "DELETE FROM links WHERE name='$identifier[name]'";
			$connection->execute($query, null);
			if(mysql_error()) {
				throw new Exception(mysql_error(), 1);
			}		
		}
		else if(isset($identifier['id'])) 
		{ 
			$queryRelations = "DELETE FROM pagelinksrelation WHERE linkid='$identifier[id]'";
			$connection->execute($queryRelations, null);
			$query = "DELETE FROM links WHERE linkid='$identifier[id]'";
			$connection->execute($query, null);		
			if(mysql_error()) {
				throw new Exception(mysql_error(), 1);
			}
		}
		else
		{
			throw new Exception("no identifier present to delete link model");
		}
	}
	
	public static function deletePage(IConnection $connection, $identifier)
	{
		$query = array(); 
		if(isset($identifier['id'])) 
		{ 
			$query[0] = "DELETE FROM viewpagerelation WHERE pageid='$identifier[id]'";
			$query[0] = "DELETE FROM pagelinksrelation WHERE pageid='$identifier[id]'";
			$query[1] = "DELETE FROM pagecontentrelation WHERE pageid='$identifier[id]'";
			$query[2] = "DELETE FROM pageimagesrelation WHERE pageid='$identifier[id]'";
			$query[3] = "DELETE FROM pages WHERE pageid='$identifier[id]'";
			$connection->transactionalQuery($query);
			if(mysql_error()) 
			{
				throw new Exception(mysql_error(), 1);
			}
		}
		else if(isset($identifier['name'])) 
		{ 
			$query[0] = "DELETE FROM viewpagerelation WHERE name='$identifier[name]'";
			$query[0] = "DELETE FROM pagelinksrelation WHERE name='$identifier[name]'";
			$query[1] = "DELETE FROM pagecontentrelation WHERE name='$identifier[name]'";
			$query[2] = "DELETE FROM pageimagesrelation WHERE name='$identifier[name]'";
			$query[3] = "DELETE FROM pages WHERE name='$identifier[name]'";
			$connection->transactionalQuery($query);
			if(mysql_error()) 
			{
				throw new Exception(mysql_error(), 1);
			}
		}
		else
		{
			throw new Exception("no identifier present to delete page model", 2);
		}
	}
	
	public static function deleteContent(IConnection $connection, $identifier)
	{
		if(isset($identifier['name'])) 
		{ 
			$query[0] = "DELETE FROM pagecontentrelation WHERE name='$identifier[name]'";
			$query[1] = "DELETE FROM content WHERE name='$identifier[name]'";
			$connection->transactionalQuery($query);
			if(mysql_error()) {
				throw new Exception(mysql_error(), 1);
			}		
		}
		else if(isset($identifier['id'])) 
		{ 
			$query[0] = "DELETE FROM pagecontentrelation WHERE contentid='$identifier[id]'";
			$query[1] = "DELETE FROM content WHERE contentid='$identifier[id]'";
			$connection->transactionalQuery($query);
			if(mysql_error()) {
				throw new Exception(mysql_error(), 1);
			}
		}
		else
		{
			throw new Exception("no identifier present to delete link model", 1);
		}
	}
	
		
	public static function deleteImage(IConnection $connection, $identifier)
	{
		if(isset($identifier['name'])) 
		{ 
			$query[0] = "DELETE FROM pageimagesrelation WHERE name='$identifier[name]'";
			$query[1] = "DELETE FROM images WHERE name='$identifier[name]'";
			$connection->transactionalQuery($query);
			if(mysql_error()) {
				throw new Exception(mysql_error(), 5);
			}		
		}
		else if(isset($identifier['id'])) 
		{ 
			$query[0] = "DELETE FROM pageimagesrelation WHERE imageid='$identifier[id]'";
			$query[1] = "DELETE FROM images WHERE imageid='$identifier[id]'";
			$connection->transactionalQuery($query);
			if(mysql_error()) {
				throw new Exception(mysql_error(), 5);
			}
		}
		else
		{
			throw new Exception("no identifier present to delete image model", 1);
		}
	}
	
}	

?>