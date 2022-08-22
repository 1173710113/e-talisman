package com.example.demo.msghandler;

public enum ExceptionEnum {

	SUCESS(200,"请求成功"),
	INTERNAL_SERVER_ERROR(500, "服务器跑路了"),
	FILE_SAVE_FAILED(7002,"文件存储失败"),
	TOKEN_CREATE_FAILED(7003,"创建token失败"),
	INFO_SEARCH_FAILED(7004,"信息查找失败"),
	PROJECT_CREATE_FAILED(7005, "创建项目失败"),
	CONTRACT_FUNC_FAILED(7006, "合约交互失败"),
	AUGUMENT_ERROR(7007,"参数错误"),
	UNKNOWN_HOST(7008,"未知服务器"),
	USER_CREATE_ERROR(7009,"用户创建失败"),
	ENCODE_ERROR(7010,"加密失敗"),
	DECODE_ERROR(7011,"解密失敗"),
	UNKOWN_USER(7012,"未知用户");
	
	private Integer code;
	
	private String msg;
	
	ExceptionEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
	
}
