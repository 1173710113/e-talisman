package com.example.demo.msghandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(annotations = Controller.class)
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public static Response<BaseException> baseExceptionHandler(BaseException e) {
		e.printStackTrace();
		return ResponseGenerator.definedErrorResponse(e);
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public static Response<Exception> runtimeExceptionHandler(Exception e){
		e.printStackTrace();
		return ResponseGenerator.exceptionResponse(e);
	}
	
}
