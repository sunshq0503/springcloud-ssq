package com.ssq.commons.enums;

public enum ResultCode  {
	/* 成功状态码 */
	SUCCESS(0, "成功"),

	/* 系统500错误*/
	SYSTEM_ERROR(10000, "系统异常，请联系开发人员"),
	UNAUTHORIZED(10401, "签名验证失败"),

	/* 参数错误：10001-19999 */
	PARAM_IS_INVALID(10001, "参数无效"),

	/* 用户错误：20001-29999*/
	USER_HAS_EXISTED(20001, "用户名已存在"),
	USER_NOT_FIND(20002, "用户名不存在");
	private Integer code;

	private String message;

	ResultCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

}
