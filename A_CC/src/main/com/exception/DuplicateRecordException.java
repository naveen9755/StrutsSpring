package com.exception;

/**
 * This class demonstrate DuplicateRecordException(Custom Exception) and extends
 * RuntimeException.
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */

public class DuplicateRecordException extends RuntimeException {
	/**
	 * This is Srting parametries cunstructor to super class.
	 * 
	 */
	public DuplicateRecordException(String message) {
		super(message);
	}
}