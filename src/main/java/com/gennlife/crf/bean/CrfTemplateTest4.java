package com.gennlife.crf.bean;

/**
 * @Description: 烟台环境：表的实体类，与数据库表对应（数据库crf_yantai_test4）
 * 功能：自动录入单病种数据
 * @author: wangmiao
 * @Date: 2017年7月12日 下午1:45:53
 */
public class CrfTemplateTest4 {
	private int id;

	private String baseName;
	private String chineseName;
	private String englishName;
	private String idXpath;
	private String dataType;
	private String variableType;
	private String dateFormat;
	private String rangeData;

	public String getRangeData() {
		return rangeData;
	}

	public void setRangeData(String rangeData) {
		this.rangeData = rangeData;
	}

	public CrfTemplateTest4() {
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public String getIdXpath() {
		return idXpath;
	}

	public void setIdXpath(String idXpath) {
		this.idXpath = idXpath;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
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

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	@Override
	public String toString() {
		return "CrfPatientInfo [id=" + id + ", baseName=" + baseName
				+ ", chineseName=" + chineseName + ", englishName="
				+ englishName + ", idXpath=" + idXpath + ", dataType="
				+ dataType + ", variableType=" + variableType + ", dateFormat="
				+ dateFormat + ", rangeData=" + rangeData + "]";
	}


}
