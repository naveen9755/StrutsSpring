package com.exception;

/**
 * This class demonstrate InsufficientFundException(Custom Exception) and
 * extends RuntimeException.
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */

public class InsufficientFundException extends RuntimeException {
	/**
	 * This is Srting parametries cunstructor to super class.
	 * 
	 */
	public InsufficientFundException(String message) {
		super(message);
	}

}
