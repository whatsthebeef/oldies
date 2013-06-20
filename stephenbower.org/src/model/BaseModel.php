<?php
require_once 'database/IConnection.php';
require_once 'database/ICredentials.php';
require_once 'database/CredentialsObject.php';

abstract class BaseModel
{
	protected $connection; 
	protected $name;
	protected $creationDate;
	protected $id;
	
	/**
	 * standard constructor for model with connection to database
	 * @return unknown_type
	 */
	public function __construct(IConnection $connection) 
	{
		$this->connection = $connection;
	}
	
	/**
	 * gets connection model has to database
	 * @return unknown_type
	 */
	public function getConnection() 
	{
		return $this->connection;
	}

	/**
	 * gets model name
	 * @return unknown_type
	 */
	public function getName() 
	{
		return $this->name;
	}
	
	/**
	 * gets creation date of model
	 * @return unknown_type
	 */
	public function getCreationDate() 
	{
		return $this->creationDate;
	}
	
	public function getID() 
	{
		return $this->id;
	}
}
 
?>