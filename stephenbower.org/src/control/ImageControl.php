<?php

require_once 'model/IPageModel.php';
require_once 'model/PageModel.php';
require_once 'model/IContentModel.php';
require_once 'model/ContentModel.php';

require_once 'factory/ModelFactory.php';

class ImageControl {

	private $pageModel = null;

	function getPageImageMap($connection, $identifier = array()) {
		$images = array();
		$imageMap = array();
		$iterator = 0;

		$this->pageModel = new PageModel($connection, $identifier);

		$images = $this->pageModel->getImage();

		foreach($images as $imageModel) {
			$imageMap[$iterator++] = array('name' => $imageModel->getName(),
				'label' => $imageModel->getLabel(), 'creationdate' => $imageModel->getCreationDate(),
					'imageid' => $imageModel->getID());
		}
		return $imagesMap;

	}

	function getCompleteImageMap($connection) {
		$imageModels = array();
		$imageMap = array();
		$iterator = 0;

		$query = "SELECT * FROM images";
		$result = $connection->query($query);
		if($result)
		{
			while($row = mysql_fetch_assoc($result))
			{
				try {
					$imageModels[$iterator++] = new ImageModel($connection,
					array('id' => $row['imageid']));
				} catch (Exception $e) {
					throw new Exception("unable to create images model with imageid ".$row['imagesid'], 4);
				}
			}
		}
		
		$iterator = 0;
		foreach($imageModels as $imageModel) 
		{
			$imageMap[$iterator++] = array("name" => $imageModel->getName(),
				"label" => $imageModel->getLabel(), 'creationdate' => 
					$imagesModel->getCreationDate(), 'imageid' => $imageModel->getID());
		}
		return $imageMap;

	}

	/*
	public function createImage($request, $connection)
	{
		$identifier['name'] = $request['imagename'];
		$pageIdentifier['name'] = $request['page'];
		$type = $request['type'];
		$size = $request['size'];
		$content = $request['content'];
		$label = $request['label'];
		return ModelFactory::createContent($connection, $identifier,
			$pageIdentifier, $type, $size, $content, $label);
	}
	*/
	public function createImage($file, $connection)
	{
		$identifier['name'] = $file['userfile']['name'];
		$tmpName  = $file['userfile']['tmp_name'];
		$size = $file['userfile']['size'];
		$type = $file['userfile']['type'];
		
		if(!($fp = fopen($tmpName, 'r'))) throw new Exception("image not opened", 8);
		$content = fread($fp, filesize($tmpName));
		$content = addslashes($content);
		fclose($fp);
		
		if(!get_magic_quotes_gpc())
		{
    		$fileName = addslashes($fileName);
		}
		
		return ModelFactory::createContent($connection, $identifier,
			$pageIdentifier, $type, $size, $content, $label);
	}
	
	public function deleteImage($request, $connection)
	{
		$identifier['id'] = $request['imageid'];
		return ModelFactory::deleteContent($connection, $identifier);
	}
}

?>