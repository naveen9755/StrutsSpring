package com.exception;

/**
 * This class demonstrate DataBaseException(Custom Exception) and extends
 * RuntimeException.
 * 
 * @author Chandrabhan singh
 * @version 1.1
 * 
 */
public class DatabaseException extends RuntimeException {

	/**
	 * This is Srting parametries cunstructor to super class.
	 * 
	 */

	public DatabaseException(String message) {
		super(message);
	}

}
