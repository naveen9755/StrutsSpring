package com.exception;

/**
 * This class demonstrate UserNotFoundException(Custom Exception) and extends
 * RuntimeException.
 * 
 * @author Chandrabhan singh
 * @version 1.1
 * 
 */

public class UserNotFoundException extends RuntimeException {
	/**
	 * This is Srting parametries cunstructor to super class.
	 * 
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}