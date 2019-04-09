package com.gennlife.autoplatform.bean;


/**
 * @Description: 实体类，与数据库表对应(rw) 功能：保存rws稳定性计算结果
 * @author: wangmiao
 * @Date: 2018年8月20日 下午1:46:44 
 */
public class RwsBean {
	private String id;
	private String result;
	private String time;

	public RwsBean() {
		super();
	}

	public RwsBean(String id, String result, String time) {
		super();
		this.id = id;
		this.result = result;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "RwsBean [id=" + id + ", result=" + result + ", time=" + time
				+ "]";
	}

}
