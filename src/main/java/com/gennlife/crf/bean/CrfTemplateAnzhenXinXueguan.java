package com.gennlife.crf.bean;

/**
 * @Description: 实体类，与数据库表对应(crf_template_anzhen_xinxueguan:安贞心血管疾病)
 *               功能：验证联动字段、枚举值下拉框
 * @author: wangmiao
 * @Date: 2017年9月19日 上午9:28:26
 */
public class CrfTemplateAnzhenXinXueguan {
	private int id;
	private String baseName;
	private String secondGroup;
	private String thirdGroup;
	private String chineseName;
	private String englishName;
	private String displayMainKey;
	private String displayMainValue;
	private String dataType;
	private String variableType;
	private String rangeData;
	private String dateFormat;
	private String idXpath;
	private String linkageResult;
	private String selectOutputValue;
	private String selectResult;
	private String inputValue;
	private String minMaxAlertOutputValue;
	private String minMaxAlertResult;
	private String minValue;
	private String maxValue;
	private String minAlertValue;
	private String maxAlertValue;

	public CrfTemplateAnzhenXinXueguan() {
	}

	public CrfTemplateAnzhenXinXueguan(int id, String baseName,
			String secondGroup, String thirdGroup, String chineseName,
			String englishName, String displayMainKey, String displayMainValue,
			String dataType, String variableType, String rangeData,
			String dateFormat, String idXpath, String linkageResult,
			String selectOutputValue, String selectResult, String inputValue,
			String minMaxAlertOutputValue, String minMaxAlertResult,
			String minValue, String maxValue, String minAlertValue,
			String maxAlertValue) {
		this.id = id;
		this.baseName = baseName;
		this.secondGroup = secondGroup;
		this.thirdGroup = thirdGroup;
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.displayMainKey = displayMainKey;
		this.displayMainValue = displayMainValue;
		this.dataType = dataType;
		this.variableType = variableType;
		this.rangeData = rangeData;
		this.dateFormat = dateFormat;
		this.idXpath = idXpath;
		this.linkageResult = linkageResult;
		this.selectOutputValue = selectOutputValue;
		this.selectResult = selectResult;
		this.inputValue = inputValue;
		this.minMaxAlertOutputValue = minMaxAlertOutputValue;
		this.minMaxAlertResult = minMaxAlertResult;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.minAlertValue = minAlertValue;
		this.maxAlertValue = maxAlertValue;
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

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getMinMaxAlertOutputValue() {
		return minMaxAlertOutputValue;
	}

	public void setMinMaxAlertOutputValue(String minMaxAlertOutputValue) {
		this.minMaxAlertOutputValue = minMaxAlertOutputValue;
	}

	public String getMinMaxAlertResult() {
		return minMaxAlertResult;
	}

	public void setMinMaxAlertResult(String minMaxAlertResult) {
		this.minMaxAlertResult = minMaxAlertResult;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinAlertValue() {
		return minAlertValue;
	}

	public void setMinAlertValue(String minAlertValue) {
		this.minAlertValue = minAlertValue;
	}

	public String getMaxAlertValue() {
		return maxAlertValue;
	}

	public void setMaxAlertValue(String maxAlertValue) {
		this.maxAlertValue = maxAlertValue;
	}

	public String getSecondGroup() {
		return secondGroup;
	}

	public void setSecondGroup(String secondGroup) {
		this.secondGroup = secondGroup;
	}

	public String getThirdGroup() {
		return thirdGroup;
	}

	public void setThirdGroup(String thirdGroup) {
		this.thirdGroup = thirdGroup;
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

	public String getIdXpath() {
		return idXpath;
	}

	public void setIdXpath(String idXpath) {
		this.idXpath = idXpath;
	}

	public String getDisplayMainKey() {
		return displayMainKey;
	}

	public void setDisplayMainKey(String displayMainKey) {
		this.displayMainKey = displayMainKey;
	}

	public String getDisplayMainValue() {
		return displayMainValue;
	}

	public void setDisplayMainValue(String displayMainValue) {
		this.displayMainValue = displayMainValue;
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

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getSelectOutputValue() {
		return selectOutputValue;
	}

	public void setSelectOutputValue(String selectOutputValue) {
		this.selectOutputValue = selectOutputValue;
	}

	public String getSelectResult() {
		return selectResult;
	}

	public void setSelectResult(String selectResult) {
		this.selectResult = selectResult;
	}

	public String getLinkageResult() {
		return linkageResult;
	}

	public void setLinkageResult(String linkageResult) {
		this.linkageResult = linkageResult;
	}

	@Override
	public String toString() {
		return "CrfTemplateAnzhenXinXueguan [id=" + id + ", baseName="
				+ baseName + ", secondGroup=" + secondGroup + ", thirdGroup="
				+ thirdGroup + ", chineseName=" + chineseName
				+ ", englishName=" + englishName + ", idXpath=" + idXpath
				+ ", displayMainKey=" + displayMainKey + ", displayMainValue="
				+ displayMainValue + ", dataType=" + dataType
				+ ", variableType=" + variableType + ", rangeData=" + rangeData
				+ ", inputValue=" + inputValue + ", selectOutputValue="
				+ selectOutputValue + ", selectResult=" + selectResult
				+ ", linkageResult=" + linkageResult + "]";
	}

}
