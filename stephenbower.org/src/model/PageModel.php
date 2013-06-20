<?php

require_once 'model/BaseModel.php';
require_once 'model/IPageModel.php';
require_once 'common/TableMap.php';
require_once 'model/ContentModel.php';
require_once 'model/LinkModel.php';

/**
 * Model created for page in project which stores databse infomration
 * @author wtb
 *
 */
class PageModel extends BaseModel implements IPageModel
{
	private $url = null;
	private $linkArray = null;
	private $contentArray = null;
	
	public function __construct(IConnection $connection, $identifier)
	{		

		$queryWithName = "SELECT * FROM pages WHERE name='$identifier[name]'";
		$queryWithID = "SELECT * FROM pages WHERE pageid='$identifier[id]'";
		$numArgs = sizeof($identifier);
		
		switch($numArgs)
		{
			case 1:
				if(isset($identifier['name'])) 
				{	 
					parent::__construct($connection);
					$pageDataAssocArray = parent::getConnection()->execute($queryWithName, 'mysql_fetch_assoc');
					$this->url = $pageDataAssocArray['url'];
					$this->name = $pageDataAssocArray['name'];
					$this->creationDate = $pageDataAssocArray['creationdate'];
					$this->id = $pageDataAssocArray['pageid'];
					if($this->id == null) {
						throw new Exception("page model cannot be created");
					}
					break;
				}
			case 2:
				if(isset($identifier['id'])) 
				{	 
					parent::__construct($connection);	
					$pageDataAssocArray = parent::getConnection()->execute($queryWithID, 'mysql_fetch_assoc');
					$this->url = $pageDataAssocArray['url'];
					$this->name = $pageDataAssocArray['name'];
					$this->creationDate = $pageDataAssocArray['creationdate'];
					$this->id = $pageDataAssocArray['pageid'];
					if($this->id == null) {
						throw new Exception("page model cannot be created");
					}
					break;
				}
			default:
				throw new Exception("page model cannot be created");
		}
		
				$this->linkArray = $this->findRelations('links', $connection);
				$this->contentArray = $this->findRelations('content', $connection);
	}

	/**
	 * (non-PHPdoc)
	 * @see src/model/IPageModel#getURL()
	 */
	public function getURL()
	{
		return $this->url;
	}

	/**
	 * (non-PHPdoc)
	 * @see src/model/IPageModel#getID()
	 */
	public function getID()
	{
		return $this->id;
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IPageModel#getLinks()
	 */
	public function getLinks()
	{
		return $this->linkArray;
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IPageModel#getContent()
	 */
	public function getContent()
	{
		return $this->contentArray;
	}

	/**
	 * private function called by constructor to query database for link page relations
	 * @return unknown_type
	 */
	private function findRelations($relation, $connection)
	{		
		$iterator = 0;
		$properties = TableMap::getTableName($relation);
		$model = array();
		
		$query = "SELECT * FROM $properties[relationsTable] WHERE $properties[masterTableKey] = '$this->id'";
		$result = parent::getConnection()->query($query);
	  	if($result)
	  	{
	  		while($row = mysql_fetch_assoc($result))
			{
				if($relation == 'links')
				{
					try {
						$model[$iterator++] = new LinkModel($connection, array('id' => $row[$properties['key']]));
					} catch (Exception $e) {
						throw new Exception("unable to create link model with linkid " + $row[$properties['key']] 
						+ " for page model " + $this->name);
					}
				}
				elseif($relation == 'content')
				{
					try {
						$model[$iterator++] = new ContentModel($connection, array('id' => $row[$properties['key']]));
					} catch (Exception $e) {
						throw new Exception("unable to create content model with contentid " + $row[$properties['key']] 
						+ " for page model " + $this->name);
					}			
				}
			}
	  	}
		return $model;
	}

}

?>