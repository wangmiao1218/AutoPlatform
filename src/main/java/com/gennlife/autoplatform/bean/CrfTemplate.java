package com.gennlife.autoplatform.bean;

/**
 * @Description: 实体类，与数据库表对应(crf_template_anzhen_all:安贞高血压疾病)
 * 功能：验证下拉框
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:48:50 
 */
public class CrfTemplate {
	private int id;

	private String baseName;
	private String chineseName;
	private String englishName;
	private String dataType;
	private String variableType;
	private String rangeData;
	private String input;
	private String xpath;
	private String output;
	private String result;

	public CrfTemplate() {
	}

	public CrfTemplate(int id, String baseName, String chineseName,
			String englishName, String dataType, String variableType,
			String rangeData, String input, String xpath, String output,
			String result) {
		this.id = id;
		this.baseName = baseName;
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.dataType = dataType;
		this.variableType = variableType;
		this.rangeData = rangeData;
		this.input = input;
		this.xpath = xpath;
		this.output = output;
		this.result = result;
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

	public String getRangeData() {
		return rangeData;
	}

	public void setRangeData(String rangeData) {
		this.rangeData = rangeData;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "CrfTemplate [id=" + id + ", baseName=" + baseName
				+ ", chineseName=" + chineseName + ", englishName="
				+ englishName + ", dataType=" + dataType + ", variableType="
				+ variableType + ", rangeData=" + rangeData + ", input="
				+ input + ", xpath=" + xpath + ", output=" + output
				+ ", result=" + result + "]";
	}

}
