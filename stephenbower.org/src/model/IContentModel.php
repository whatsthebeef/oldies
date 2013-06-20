<?php 

require_once 'model/IModel.php';

interface IContentModel extends IModel
{
	
	/*
	 * get URL of page content
	 */
	public function getContent();
	
	
}

?>