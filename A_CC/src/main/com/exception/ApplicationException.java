package com.exception;

/**
 * This class demonstrate DataBaseException(Custom Exception) and extends
 * RuntimeException.
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */
public class ApplicationException extends RuntimeException {

	/**
	 * This is Srting parametries cunstructor to super class.
	 * 
	 */
	public ApplicationException(String message) {
		super(message);
	}

}
