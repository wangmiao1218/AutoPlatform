package com.gennlife.crf.bean;

import java.util.List;

/**
 * @Description: 查询左侧列菜单表节点、子节点（封装，便于查询子节点）
 * @author: wangmiao
 * @Date: 2018年3月24日 上午10:42:25
 */
public class SysFuncBean {
	private List<SysFuncBean> children;

	private Long funcId;

	private Short funcType;

	private Long supFuncId;

	private Short funcLevel;

	private String funcCode;

	private String funcName;

	private Integer funcOrder;

	private String funcUrl;

	private String funcImg;

	public List<SysFuncBean> getChildren() {
		return children;
	}

	public void setChildren(List<SysFuncBean> children) {
		this.children = children;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Short getFuncType() {
		return funcType;
	}

	public void setFuncType(Short funcType) {
		this.funcType = funcType;
	}

	public Long getSupFuncId() {
		return supFuncId;
	}

	public void setSupFuncId(Long supFuncId) {
		this.supFuncId = supFuncId;
	}

	public Short getFuncLevel() {
		return funcLevel;
	}

	public void setFuncLevel(Short funcLevel) {
		this.funcLevel = funcLevel;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public Integer getFuncOrder() {
		return funcOrder;
	}

	public void setFuncOrder(Integer funcOrder) {
		this.funcOrder = funcOrder;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public String getFuncImg() {
		return funcImg;
	}

	public void setFuncImg(String funcImg) {
		this.funcImg = funcImg;
	}

}
