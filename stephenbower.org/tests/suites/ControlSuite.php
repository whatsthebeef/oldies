<?php

require_once('simpletest/unit_tester.php');
require_once('simpletest/reporter.php');
  
class ControlSuite extends TestSuite
{
 
	function __construct()
	{
   		$this->TestSuite('Control Suite');
   		$this->addTestFile('controltest/LinksControllerTest.php');
	}
    
}

$test = new ControlSuite(); 
$test->run(new HtmlReporter());
    
?>
