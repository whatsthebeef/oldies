<?php

require_once 'model/IPageModel.php';
/**
 * Base class for all web page controls web page control object is present for each page
 * @author wtb
 *
 */
abstract class BasePageControl
{
	private $pageModel = null;
	 
	public function __construct(IPageModel $pageModel)
	{
		$this->pageModel = $pageModel;
	}
	
	/**
	 * gets the IPageModel associated with web page control
	 * @return member variabe pageModel
	 */
	public function getIPageModel()
	{
		return $this->pageModel;
	}
	
}
?>