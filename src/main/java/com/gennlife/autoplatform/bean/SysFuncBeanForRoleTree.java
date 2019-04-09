package com.gennlife.autoplatform.bean;

/**
 * @Description: 用来展示菜单的简单逻辑类
 * @author: wangmiao
 * @Date: 2018年3月30日 上午9:52:41
 */
public class SysFuncBeanForRoleTree {

	private Long funcId;

	private Long supFuncId = new Long("0");

	private String name;

	private boolean checked;

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Long getSupFuncId() {
		return supFuncId;
	}

	public void setSupFuncId(Long supFuncId) {
		this.supFuncId = supFuncId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
