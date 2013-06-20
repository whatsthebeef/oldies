<?php

require_once 'model/BaseModel.php';
require_once 'model/ILinkModel.php';

/**
 * Model created for link in project which stores databse infomration 
 * @author wtb
 *
 */
class LinkModel extends BaseModel implements ILinkModel
{
	private $urlLink = null;
	
	public function __construct(IConnection $connection, $identifier) 
	{	
		$numArgs = sizeof($identifier);
		
		switch($numArgs)
		{
			case 1:
				if(isset($identifier['name'])) 
				{	 
					parent::__construct($connection);
					$queryWithName = "SELECT * FROM links WHERE name='$identifier[name]'";
					$linkDataAssocArray = parent::getConnection()->execute($queryWithName, 'mysql_fetch_assoc');
					$this->urlLink = $linkDataAssocArray['url'];
					$this->name = $linkDataAssocArray['name'];
					$this->creationDate = $linkDataAssocArray['creationdate'];
					$this->id = $linkDataAssocArray['linkid'];
					break;
				}
			case 2:
				if(isset($identifier['id']))
				{
					parent::__construct($connection);
					$queryWithId = "SELECT * FROM links WHERE linkid='$identifier[id]'";
					$linkDataAssocArray = parent::getConnection()->execute($queryWithId, 'mysql_fetch_assoc');
					$this->urlLink = $linkDataAssocArray['url'];
					$this->name = $linkDataAssocArray['name'];
					$this->creationDate = $linkDataAssocArray['creationdate'];
					$this->id = $linkDataAssocArray['linkid'];
					break;
				}
			default:
				throw new Exception("link model doesn't exist");
		}
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/ILinkModel#getURL()
	 */
	public function getURLLink()
	{
		return $this->urlLink;
	}	
	
	
}

?>