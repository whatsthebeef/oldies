<?php

require_once 'model/IPageModel.php';
require_once 'model/PageModel.php';

require_once 'factory/ModelFactory.php';

class ContentControl {

	private $pageModel = null;

	function getPageContentMap($connection, $identifier = array()) {
		$contents = array();
		$contentMap = array();
		$iterator = 0;

		$this->pageModel = new PageModel($connection, $identifier);

		$content = $this->pageModel->getContent();

		foreach($content as $contentModel) {
			$contentMap[$iterator++] = array("name" => $contentModel->getName(),
				"url" => $contentModel->getURLLink());
		}
		return $contentMap;

	}

	function getCompleteContentMap($connection) {
		$contentModels = array();
		$contentMap = array();
		$iterator = 0;

		$query = "SELECT * FROM content";
		$result = $connection->query($query);
		if($result)
		{
			while($row = mysql_fetch_assoc($result))
			{
				try {
					$contentModels[$iterator++] = new ContentModel($connection,
					array('id' => $row['contentid']));
				} catch (Exception $e) {
					throw new Exception("unable to create content model with contentid "
					+ $row['contentid']	+ " for page model " + $this->name);
				}
			}
		}
		
		$iterator = 0;
		foreach($contentModels as $contentModel) 
		{
			$contentMap[$iterator++] = array("name" => $contentModel->getName(),
				"content" => $contentModel->getContent(), 'creationdate' => 
					$contentModel->getCreationDate(), 'contentid' => $contentModel->getID());
		}
		return $contentMap;

	}

	public function createContent($request, $connection)
	{
		$identifier['name'] = $request['contentname'];
		$pageIdentifier['name'] = $request['page'];
		$url = $request['content'];
		return ModelFactory::createContent($connection, $identifier,  $url,
		$pageIdentifier);
	}
	
	public function deleteContent($request, $connection)
	{
		$identifier['id'] = $request['contentid'];
		return ModelFactory::deleteContent($connection, $identifier);
	}
}

?>