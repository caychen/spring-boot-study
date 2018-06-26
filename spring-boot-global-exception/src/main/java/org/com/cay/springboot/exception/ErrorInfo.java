package org.com.cay.springboot.exception;

/**
 * Created by Cay on 2017/9/27.
 */
public class ErrorInfo<T> {

	public static final Integer OK = 200;
	public static final Integer ERROR = 400;

	private Integer code;
	private String message;
	private String url;
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
