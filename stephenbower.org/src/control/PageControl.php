<?php

require_once 'control/IPageControl.php';
require_once 'factory/ModelFactory.php';
require_once 'control/BasePageControl.php';
require_once 'database/MySQLCredentials.php';

/**
 * class implementing IUserPageControl interface instanciated for each user web page 
 * present
 * @author wtb
 *
 */


class PageControl implements IPageControl
{
	private $linkModels = null;
	private $contentModels = null;

	
	public function __construct()
	{	
	
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/control/IUserPageControl#getLinks()
	 */
	public function getLinks()
	{
				
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/control/IUserPageControl#getContent()
	 */
	public function getContent()
	{
		
	}
	
	public function createPage($request, $connection) 
	{
		$identifier['name'] = $request['pagename'];
		$url = $request['url'];
		return ModelFactory::createPage($connection, $identifier, $url);
	}

	public function deletePage($request, $connection)
	{
		$identifier['id'] = $request['pageid'];
		return ModelFactory::deletePage($connection, $identifier);
	}

	public function getPages($view, $connection) 
	{	
		$iterator = 0;
		$models = array();
		
		$query = "SELECT * FROM viewpagerelation WHERE viewid='$view'";
		
		$result = $connection->query($query);	
	  	if($result)
	  	{
	  		while($row = mysql_fetch_assoc($result))
			{
				try	{
					$models[$iterator++] = new PageModel($connection, array('id' => $row[pageid]));
				} catch (Exception $e) {
					throw new Exception("unable to create page model with pageid " + $row[pageid] 
					+ " for page model " + $this->name);					
				}		
			}		
		}
		return $models;
	}
	
	function getCompletePageMap($connection) {
		$pageModels = array();
		$pageMap = array();
		$iterator = 0;

		$query = "SELECT * FROM pages";
		$result = $connection->query($query);
		if($result)
		{
			while($row = mysql_fetch_assoc($result))
			{
				try {
					$pageModels[$iterator++] = new PageModel($connection,
					array('id' => $row['pageid']));
				} catch (Exception $e) {
					throw new Exception("unable to create page model with linkid "
					+ $row['pageid']);
				}
			}
		}
		
		$iterator = 0;
		foreach($pageModels as $pageModel) 
		{
			$pageMap[$iterator++] = array("name" => $pageModel->getName(),
				"url" => $pageModel->getURL(), 'creationdate' => 
					$pageModel->getCreationDate(), 'pageid' => $pageModel->getID());
		}
		return $pageMap;

	}
	
	
	public function getPageStrings($view, $connection) 
	{	
		$iterator = 0;
		$models = array();
		
		$query = "SELECT * FROM viewpagerelation WHERE viewid='$view'";
		
		$result = $connection->query($query);	
	  	if($result)
	  	{
	  		while($row = mysql_fetch_assoc($result))
			{
				try	
				{
					$page = new PageModel($connection, array('id' => $row[pageid]));
					$models[$iterator++] = $page->getName();
				} 
				catch (Exception $e) 
				{
					throw new Exception("unable to create page model with pageid $row[pageid]", 2);					
				}		
			}		
		}
		return $models;
	}
}

?>