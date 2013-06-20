<?php 

require_once 'model/IModel.php';

interface ILinkModel extends IModel
{
	
	/*
	 * get URL of link model
	 */
	public function getURLLink();
	
	
}

?>