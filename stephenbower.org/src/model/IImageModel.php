<?php 

interface IImageModel
{
	
	/*
	 * get type of image (format)
	 */
	public function getType();
	
	/**
	 * get size of image
	 * @return unknown_type
	 */
	public function getSize();
	
	
	/**
	 * get image associated with model		
	 * @return unknown_type
	 */
	public function getImage();
	
}

?>