package com.Student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Student.Dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> responseNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
   
	/*@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}*/

}
