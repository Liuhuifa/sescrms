
package com.sesc.rms.util;

public class Result<T> {
	private static final int SUCCESS_CODE = 1;
	private static final int FAIL_CODE = 0;
	
	private int code;
	private String message;
	private T data;
	private long total; //数据条数

	public Result() {
	}

	private Result(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	private Result(int code, String message, T data, long total) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.total = total;
	}

	public static Result success() {
		return new Result(SUCCESS_CODE,null,null);
	}
	public static Result success(Object data) {
		return new Result(SUCCESS_CODE,"",data);
	}
	public static Result success(String message, Object data) {
		return new Result(SUCCESS_CODE,message,data);
	}
	public static Result success(String message, Object data, long total) {
		return new Result(SUCCESS_CODE,message,data,total);
	}
	public static Result fail(String message) {
		return new Result(FAIL_CODE,message,null);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
}
