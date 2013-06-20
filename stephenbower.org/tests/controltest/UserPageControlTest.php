<?php

require_once 'control/UserPageControl.php';

require_once 'model/IPageModel.php';

require_once 'simpletest/autorun.php';

class UserPageControlTest extends UnitTestCase
{	
	private $userPageControl = null;
	private $pageModel = null;
	
	function setUp()
	{
		$this->pageModel = new MockIPageModel();
		$this->userPageControl = new UserPageControl($this->pageModel);		
	}
	
	function tearDown() 
	{
		$this->userPageControl = null;	
	}
	
	public function testGetIPageModel()
	{
		$this->assertNotNull($this->userPageControl->getIPageModel());		
	}
	
	public function testGetILinkModels()
	{
		$this->assertNotNull($this->userPageControl->getILinkModels());	
	}
	
	public function testGetIContentModels()
	{
		$this->assertNotNull($this->userPageControl->getContentModels());	
	}
	
}


?>