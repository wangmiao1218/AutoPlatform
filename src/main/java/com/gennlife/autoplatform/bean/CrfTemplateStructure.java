package com.gennlife.autoplatform.bean;

/**
 * @Description: 实体类，与数据库表对应(crf_template_structure)crf模板结构，即左侧列
 * @author: wangmiao
 * @Date: 2017年9月25日 下午4:01:54 
 */
public class CrfTemplateStructure {
	private int id;
	private String baseName;
	private String hospitalDepartment;
	private String idXpath;

	public CrfTemplateStructure() {
	}

	public CrfTemplateStructure(int id, String baseName,
			String hospitalDepartment, String idXpath) {
		this.id = id;
		this.baseName = baseName;
		this.hospitalDepartment = hospitalDepartment;
		this.idXpath = idXpath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getHospitalDepartment() {
		return hospitalDepartment;
	}

	public void setHospitalDepartment(String hospitalDepartment) {
		this.hospitalDepartment = hospitalDepartment;
	}

	public String getIdXpath() {
		return idXpath;
	}

	public void setIdXpath(String idXpath) {
		this.idXpath = idXpath;
	}

	@Override
	public String toString() {
		return "CrfTemplateStructure [id=" + id + ", baseName=" + baseName
				+ ", hospitalDepartment=" + hospitalDepartment + ", idXpath="
				+ idXpath + "]";
	}

}