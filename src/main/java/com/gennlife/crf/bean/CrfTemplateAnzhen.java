package com.gennlife.crf.bean;

/**
 * @Description: 安贞环境：表的实体类，与数据库表对应（数据库crf_template_anzhen：安贞高血压疾病）
 * 功能：验证导出功能，指定输入数据
 * @author: wangmiao
 * @Date: 2017年7月27日 下午6:34:35
 */
public class CrfTemplateAnzhen {
	private int id;
	private String baseName;
	private String chineseName;
	private String englishName;
	private String idXpath;
	private String dataType;
	private String variableType;
	private String dateFormat;
	private String rangeData;
	private String inputValue;
	private String inputValue02;
	private String inputValue03;

	public String getInputValue02() {
		return inputValue02;
	}

	public void setInputValue02(String inputValue02) {
		this.inputValue02 = inputValue02;
	}

	public String getInputValue03() {
		return inputValue03;
	}

	public void setInputValue03(String inputValue03) {
		this.inputValue03 = inputValue03;
	}

	public String getRangeData() {
		return rangeData;
	}

	public void setRangeData(String rangeData) {
		this.rangeData = rangeData;
	}

	public CrfTemplateAnzhen() {
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

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	@Override
	public String toString() {
		return "CrfTemplateAnzhen [id=" + id + ", baseName=" + baseName
				+ ", chineseName=" + chineseName + ", englishName="
				+ englishName + ", idXpath=" + idXpath + ", dataType="
				+ dataType + ", variableType=" + variableType + ", dateFormat="
				+ dateFormat + ", rangeData=" + rangeData + ", inputValue="
				+ inputValue + ", inputValue02=" + inputValue02
				+ ", inputValue03=" + inputValue03 + "]";
	}

}
