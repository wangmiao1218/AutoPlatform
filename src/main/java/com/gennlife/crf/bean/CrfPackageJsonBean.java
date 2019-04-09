package com.gennlife.crf.bean;

import java.util.List;
import java.util.Map;

/**
 * @Description: 实体类，select_condition 功能：验证下拉框
 * @author: wangmiao
 * @Date:
 */
public class CrfPackageJsonBean {
	private String operator;

	private List<Map> detail;


	public CrfPackageJsonBean() {
	}


	public CrfPackageJsonBean(String operator, List<Map> detail) {
		super();
		this.operator = operator;
		this.detail = detail;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public List<Map> getDetail() {
		return detail;
	}


	public void setDetail(List<Map> detail) {
		this.detail = detail;
	}


	@Override
	public String toString() {
		return "CrfPackageJsonBean [operator=" + operator + ", detail="
				+ detail + "]";
	}


}
