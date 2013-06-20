<?php

require_once 'doc/ApplicationController.php';

class ApplicationContollerTest
{
	private $applicationController = null;
	
	function testApplicationController()
	{
		$this->applicationController = new ApplicationController();
		$this->applicationController->displayMainPage();
	}
}

$applicationControllerTest = new ApplicationContollerTest();
$applicationControllerTest->testApplicationController();

?>