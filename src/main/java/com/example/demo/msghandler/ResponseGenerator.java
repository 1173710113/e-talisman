package com.example.demo.msghandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class ResponseGenerator {
	private static final Logger logger = LoggerFactory.getLogger(ResponseGenerator.class);

	public static Response<BaseException> definedErrorResponse(BaseException e){
		Response<BaseException> response = new Response<BaseException>();
		response.setFlag(false);
		response.setCode(e.getCode());
		response.setMsg(e.getMessage());
		response.setData(e);
		response.setTime(System.currentTimeMillis());
		logger.error(JSON.toJSONString(response));
		return response;
	}
	
	public static<T> Response<T> successResponse(T data){
		Response<T> response = new Response<T>();
		response.setFlag(true);
		response.setCode(200);
		response.setMsg("请求成功");
		response.setData(data);
		response.setTime(System.currentTimeMillis());
		logger.info(JSON.toJSONString(response));
		return response;
	}
	
	public static Response<ExceptionEnum>  enumErroResponse(ExceptionEnum exceptionEnum) {
		Response<ExceptionEnum> response = new Response<ExceptionEnum>();
		response.setCode(exceptionEnum.getCode());
		response.setData(exceptionEnum);
		response.setFlag(false);
		response.setMsg(exceptionEnum.getMsg());
		response.setTime(System.currentTimeMillis());
		logger.error(JSON.toJSONString(response));
		return response;
	}
	
	public static Response<Exception> exceptionResponse(Exception e){
		Response<Exception> response = new Response<Exception>();
		response.setFlag(false);
		response.setCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode());
		response.setData(e);
		response.setMsg(ExceptionEnum.INTERNAL_SERVER_ERROR.getMsg());
		response.setTime(System.currentTimeMillis());
		logger.error(JSON.toJSONString(response));
		return response;
	}
}
