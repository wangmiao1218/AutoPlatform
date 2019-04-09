package com.gennlife.crf.bean;

/**
 * @Description: 通用的返回结果
 * @author: wangmiao
 * @Date: 2017年9月1日 下午3:19:40 
 */
public class ResultBean {

	public static final String RESULT_FAILED = "failed";
	public static final String RESULT_SUCCESS = "success";

	private String result;
	private String msg;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
