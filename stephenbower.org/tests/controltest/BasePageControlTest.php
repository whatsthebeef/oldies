<?php

require_once 'control/BasePageControl.php';

require_once 'model/IPageModel.php';

require_once 'simpletest/autorun.php';

Mock::generate('IPageModel');

class MockPageControl extends BasePageControl
{
	public function __construct(IPageModel $pageModel) 
	{
		parent::__construct($pageModel);
	}
}

class BasePageControlTest extends UnitTestCase
{	
	private $mockWebPageControl = null;
	private $pageModel = null;
	
	function setUp()
	{
		$this->pageModel = new MockIPageModel();
		$this->mockPageControl = new MockPageControl($this->pageModel);		
	}
	
	function tearDown() 
	{
		$this->mockPageControl = null;	
	}
	
	public function testGetIPageModel()
	{
		$this->assertNotNull($this->mockPageControl->getIPageModel());		
	}

	
}

?>