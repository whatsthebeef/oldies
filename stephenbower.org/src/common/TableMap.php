<?php

class TableMap
{
	static function getTableName($model)
	{
		
		if($model == 'links')
		{
			$tables = array(
							'masterTable' => 'pages',
							'masterTableKey' => 'pageid',
						    'key' => 'linkid',
							'table' => 'links',
							'relationsTable' => 'pagelinksrelation');
		}
		if($model == 'content')
		{
			$tables = array(
							'masterTable' => 'pages',
							'masterTableKey' => 'pageid',
						    'key' => 'contentid',
							'table' => 'content',
							'relationsTable' => 'pagecontentrelation');
		}
		if($model == 'pages')
		{
			$tables = array(
							'masterTable' => 'pages',
							'masterTableKey' => 'pageid',
						    'key' => 'pageid',
							'table' => 'pages',
							'relationsTable' => 'pagecontentrelation');
		}
		if($model == 'images')
		{
			$tables = array(
							'masterTable' => 'pages',
							'masterTableKey' => 'pageid',
						    'key' => 'imageid',
							'table' => 'images',
							'relationsTable' => 'pageimagerelation');
		}
		return $tables;
		
	}	
}

?>