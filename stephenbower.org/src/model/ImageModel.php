<?php

require_once 'model/BaseModel.php';
require_once 'model/IImageModel.php';

/**
 * Model created for image in project which stores databse infomration 
 * @author wtb
 *
 */
class ImageModel extends BaseModel implements IImageModel
{
	private $image = null;
	private $type = null; 
	private $label = null;
	private $size = null;
	
	public function __construct(IConnection $connection, $identifier) 
	{
		$queryWithName = "SELECT * FROM images WHERE name='$identifier[name]'";
		$queryWithId = "SELECT * FROM images WHERE imageid='$identifier[id]'";
		$numArgs = sizeof($identifier);

		switch($numArgs)
		{
			case 1:
				if(isset($identifier['name'])) 
				{	 
					parent::__construct($connection);
					$imageDataAssocArray = parent::getConnection()->execute($queryWithName, 'mysql_fetch_assoc');
					$this->image = $imageDataAssocArray['image'];
					$this->type = $imageDataAssocArray['type'];
					$this->name = $imageDataAssocArray['name'];
					$this->creationDate = $imageDataAssocArray['creationdate'];
					$this->size = $imageDataAssocArray['size'];
					$this->label = $imageDataAssocArray['label'];
					break;
				}
			case 2:
				if(isset($identifier['id'])) 
				{	 
					parent::__construct($connection);
					$imageDataAssocArray = parent::getConnection()->execute($queryWithId, 'mysql_fetch_assoc');
					$this->image = $imageDataAssocArray['image'];
					$this->type = $imageDataAssocArray['type'];
					$this->name = $imageDataAssocArray['name'];
					$this->creationDate = $imageDataAssocArray['creationdate'];
					$this->size = $imageDataAssocArray['size'];
					$this->label = $imageDataAssocArray['label'];
					break;
				}
			default:
				throw new Exception("image model doesn't exist");
		}
				
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IImageModel#getImage()
	 */
	public function getImage()
	{
		return $this->image;
	}	

	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IImageModel#getSize()
	 */
	public function getSize()
	{
		return $this->size;
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IImageModel#getType()
	 */
	public function getType()
	{
		return $this->type;
	}
	
	/**
	 * (non-PHPdoc)
	 * @see src/model/IImageModel#getLabel()
	 */
	public function getLabel()
	{
		return $this->label;
	}
}

?>