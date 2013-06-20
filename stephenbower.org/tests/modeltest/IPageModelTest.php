<?php

require_once 'testsetup/TestSetup.php';

require_once 'model/IPageModel.php';
require_once 'model/PageModelObject.php';
require_once 'model/PageModel.php';

require_once 'database/MySQLConnection.php';
require_once 'database/ConnectionObject.php';
require_once 'database/ICredentials.php';
require_once 'database/MySQLCredentials.php';

require_once 'simpletest/autorun.php';

//Mock::generate('IConnection');

class IPageModelTest extends UnitTestCase
{
	private $pageModel = null;
	private $connection = null;
	private $credentials = null;
	private $testSetup = null;

	function setUp()
	{
		$this->testSetup = new TestSetup();
		$this->testSetup->modelTestSetup();
		
		$this->connection = $this->testSetup->getConnection(); 

		$pageNameIdentifier = array('name' => 'test');
		
		$this->pageModel = PageModelObject::cast(
		new PageModel($this->connection, $pageNameIdentifier));
		
	}

	function tearDown()
	{
		$this->testSetup->tearDown();
	}

	public function testGetURL()
	{
		$url = $this->pageModel->getURL();
		$this->assertEqual($url, 'test');
	}

	public function testGetCreationDate()
	{
		$creationDate = $this->pageModel->getCreationDate();
		$this->assertEqual($creationDate, '0000-00-00 00:00:00');
	}

	public function testGetName()
	{
		$name = $this->pageModel->getName();
		$this->assertEqual($name, 'test');
	}

	public function testGetLinks()
	{
		$linkArray = $this->pageModel->getLinks();
		$this->assertIsA($linkArray[0], 'LinkModel');
	}
	
	public function testGetContent()
	{
		$contentArray = $this->pageModel->getContent();
		$this->assertIsA($contentArray[0], 'ContentModel');
		
		echo "page model tests complete" . "\n";
	}

}

?>