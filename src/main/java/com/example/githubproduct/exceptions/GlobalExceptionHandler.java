package com.example.githubproduct.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * GlobalExceptionHandler class handles all exceptions globally across the application.
 * It uses @RestControllerAdvice to intercept and manage exceptions thrown by any controller.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
     * Handles RuntimeException specifically.
     * When any RuntimeException is thrown in the application, this method captures it
     * and returns a custom error message with HTTP status 500 (Internal Server Error).
     *
     * @param ex The RuntimeException that occurred.
     * @return A ResponseEntity containing the custom error message and HTTP status.
     */
	   @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
	        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	   /**
	     * Handles all general exceptions that are not specifically RuntimeExceptions.
	     * Provides a fallback for any unhandled exception and returns a generic error message
	     * with HTTP status 500 (Internal Server Error).
	     *
	     * @param ex The Exception that occurred.
	     * @return A ResponseEntity containing the generic error message and HTTP status.
	     */
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex) {
	        return new ResponseEntity<>("Unexpected Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
