package com.hisi.common.util;

/**
 * 自定义响应结构
 * 
 * @author Ys 2017年10月19日 下午1:45:24
 */
public class YfslResult {
	/**
	 * 响应业务状态
	 */
	private Integer status;

	/**
	 * 响应消息
	 */
	private String msg;

	/**
	 * 响应中的数据
	 */
	private Object data;

	public static YfslResult build(Integer status, String msg, Object data) {
		return new YfslResult(status, msg, data);
	}

	public static YfslResult ok(Object data) {
		return new YfslResult(data);
	}

	public static YfslResult ok(String msg, Object data) {
		return new YfslResult(200, msg, data);
	}

	public static YfslResult ok() {
		return new YfslResult(null);
	}

	public YfslResult() {

	}

	public static YfslResult fail(String msg) {
		return new YfslResult(400, msg, null);
	}

	public static YfslResult build(Integer status, String msg) {
		return new YfslResult(status, msg, null);
	}

	public YfslResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public YfslResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
