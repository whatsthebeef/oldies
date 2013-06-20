<?php

require_once 'model/IPageModel.php';
require_once 'model/PageModel.php';

require_once 'factory/ModelFactory.php';

class LinkControl {

	private $pageModel = null;

	function getPageLinksMap($connection, $identifier = array()) {
		$links = array();
		$linkMap = array();
		$iterator = 0;

		$this->pageModel = new PageModel($connection, $identifier);

		$links = $this->pageModel->getLinks();

		foreach($links as $linkModel) {
			$linkMap[$iterator++] = array("name" => $linkModel->getName(),
				"url" => $linkModel->getURLLink());
		}
		return $linkMap;

	}

	function getCompleteLinksMap($connection) {
		$linkModels = array();
		$linkMap = array();
		$iterator = 0;

		$query = "SELECT * FROM links";
		$result = $connection->query($query);
		if($result)
		{
			while($row = mysql_fetch_assoc($result))
			{
				try {
					$linkModels[$iterator++] = new LinkModel($connection,
					array('id' => $row['linkid']));
				} catch (Exception $e) {
					throw new Exception("unable to create link model with linkid "
					+ $row['linkid']	+ " for page model " + $this->name);
				}
			}
		}
		
		$iterator = 0;
		foreach($linkModels as $linkModel) 
		{
			$linkMap[$iterator++] = array("name" => $linkModel->getName(),
				"url" => $linkModel->getURLLink(), 'creationdate' => 
					$linkModel->getCreationDate(), 'linkid' => $linkModel->getID());
		}
		return $linkMap;

	}

	public function createLink($request, $connection)
	{
		$identifier['name'] = $request['linkname'];
		$pageIdentifier['name'] = $request['page'];
		$url = $request['url'];
		return ModelFactory::createLink($connection, $identifier,  $url,
		$pageIdentifier);
	}
	
	public function deleteLink($request, $connection)
	{
		$identifier['id'] = $request['linkid'];
		return ModelFactory::deleteLink($connection, $identifier);
	}
}

?>