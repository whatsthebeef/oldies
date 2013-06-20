<?php

require_once 'model/BaseModel.php';
require_once 'model/IContentModel.php';

/**
 * Model created for content in project which stores databse infomration 
 * @author wtb
 *
 */
class ContentModel extends BaseModel implements IContentModel
{
	private $content = null;
	
	public function __construct(IConnection $connection, $identifier) 
	{
		$numArgs = sizeof($identifier);
		
		switch($numArgs)
		{
			case 1:
				if(isset($identifier['name'])) 
				{	 
					parent::__construct($connection);
					$queryWithName = "SELECT * FROM content WHERE name='$identifier[name]'";
					$contentDataAssocArray = parent::getConnection()->execute($queryWithName, 'mysql_fetch_assoc');
					$this->content = $contentDataAssocArray['content'];
					$this->name = $contentDataAssocArray['name'];
					$this->creationDate = $contentDataAssocArray['creationdate'];
					$this->id = $contentDataAssocArray['contentid'];
					break;
				}
			case 2:
				if(isset($identifier['id'])) 
				{	
					parent::__construct($connection);
					$queryWithId = "SELECT * FROM content WHERE contentid='$identifier[id]'";
					$contentDataAssocArray = parent::getConnection()->execute($queryWithId, 'mysql_fetch_assoc'); 
					$this->content = $contentDataAssocArray['content'];
					$this->name = $contentDataAssocArray['name'];
					$this->creationDate = $contentDataAssocArray['creationdate'];
					$this->id = $contentDataAssocArray['contentid'];
					break;
				}
			default:
				throw new Exception("unable to create content model");
		}
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IContentModel#getContent()
	 */
	public function getContent()
	{
		return $this->content;
	}	
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IContentModel#getName()
	 */
	public function getName()
	{
		return $this->name;
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IContentModel#getCreationDate()
	 */
	public function getCreationDate()
	{
		return $this->creationDate;
	}
}

?>