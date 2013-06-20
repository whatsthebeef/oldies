<?php

require_once 'control/IPageControl.php';
/**
 * Interface implemented by a Page for users
 * @author wtb
 *
 */
interface IPageControl
{

	/**
	 * gets links associated with page
	 
	public function getILinkModels();


	 * gets content associated with page
	 * @return unknown_type
	 
	public function getIContentLinks();
	
	
	 * 
	 * @return unknown_type
	 */
	public function createPage($request, $connection);
	
	/*
	 * 
	 */
	public function getPages($view, $connection);
	
	
	
}

?>