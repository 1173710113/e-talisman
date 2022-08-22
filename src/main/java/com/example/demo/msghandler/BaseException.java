package com.example.demo.msghandler;

public class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;
	
	
	
	public BaseException(ExceptionEnum enumExceptipn) {
		super(enumExceptipn.getMsg());
		this.code = enumExceptipn.getCode();
		
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	

	
	
}
