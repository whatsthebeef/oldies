<?php 

require_once 'model/IModel.php';

interface IPageModel extends IModel
{
	
	/*
	 * get ID of page model
	 */
	//public function getID();
	
	/*
	 * get URL of page model
	 */
	public function getURL();
	
	/**
	 * get links asscoaited with page model
	 * @return linkArray
	 */
	public function getLinks();
	
	/**
	 * get content associated with page model
	 * @return contentArray
	 */
	public function getContent();
	
}

?>