<?php

    require_once('simpletest/unit_tester.php');
    require_once('simpletest/reporter.php');
  
class ModelsSuite extends TestSuite
{
 
	function __construct()
	{
   		$this->TestSuite('Model Suite');
   		$this->addTestFile('databasetest/ConnectionTest.php');
    	$this->addTestFile('modeltest/BaseModelTest.php');
    	$this->addTestFile('modeltest/IImageModelTest.php');
    	$this->addTestFile('modeltest/ILinkModelTest.php');
    	$this->addTestFile('modeltest/IPageModelTest.php');
    	$this->addTestFile('modeltest/IContentModelTest.php');
    	$this->addTestFile('factorytest/PageModelFactoryTest.php');
    	$this->addTestFile('factorytest/ModelFactoryTest.php');
	}
    
}

$test = new ModelsSuite(); 
$test->run(new HtmlReporter());
    
?>
