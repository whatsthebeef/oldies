<?php

require_once 'setup/DatabaseSchema.php';
require_once 'database/MySQLCredentials.php';

$credentials = CredentialsObject::cast(new MySQLCredentials());
$connection = ConnectionObject::cast(new MySQLConnection($credentials));	
		
$databaseName = "wtb";

$connection->getConnection();

$setup = new DatabaseSchema($connection, $databaseName);

$setup->setUpDatabase();

$connection->getDatabaseConnection($databaseName, $connection);

$setup->setUpTables();

?>