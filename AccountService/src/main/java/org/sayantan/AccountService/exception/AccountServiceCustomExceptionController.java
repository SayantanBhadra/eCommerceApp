package org.sayantan.AccountService.exception;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AccountServiceCustomExceptionController extends ResponseEntityExceptionHandler{
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp",LocalDate.now());
		body.put("errors", ex.getMessage());
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
	        HttpStatus status, WebRequest request){
		List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp",LocalDate.now());
		body.put("errors", errors);
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = AccountServiceRuntimeException.class)
	public ResponseEntity<Object> handleAccountServiceRuntimeException(AccountServiceRuntimeException ex){
		String message="An application error has occurred.Please contact support team support.accountservice@sayantan.org. Here is the details of the Message:"+ex.getMessage();
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp",LocalDate.now());
		body.put("errors", message);
		return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
