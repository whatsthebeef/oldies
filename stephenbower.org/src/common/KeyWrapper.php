<?php

/**
 * object which generates a unique key
 * @author wtb
 *
 */
class KeyWrapper
{
	
	public static function generateKey()
	{
		$key = date("ymdHis").substr((string)microtime(), 2, 4);
		return $key;
	} 
}


?>