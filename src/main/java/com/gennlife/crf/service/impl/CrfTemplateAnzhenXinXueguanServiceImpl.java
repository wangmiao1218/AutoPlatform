package com.gennlife.crf.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.crf.bean.CrfTemplateAnzhenXinXueguan;
import com.gennlife.crf.mapper.CrfTemplateAnzhenXinXueguanMapper;
import com.gennlife.crf.service.CrfTemplateAnzhenXinXueguanService;
import com.gennlife.crf.utils.ListAndStringUtils;
import com.gennlife.crf.utils.SeleniumUtils;

/**
 * @Description: 获取CrfTemplateAnzhenXinXueguan的impl
 * @author: wangmiao
 * @Date: 2017年9月19日 上午9:36:22 
 */
@Service
public class CrfTemplateAnzhenXinXueguanServiceImpl implements CrfTemplateAnzhenXinXueguanService {

	@Autowired
	private CrfTemplateAnzhenXinXueguanMapper crfTemplateAnzhenXinXueguanMapper;


	/**
	 * 通用打开所有联动字段
	 */
	@Override
	public void openAllLinkageFieldGeneralServiceMethod() throws Exception {
		
		
		
	}
	
	
	/**
	 * 使用递归：只写了四层逻辑判断(组联动)
	 * 注意：没有判断 组联动的情况，只要有组联动，整组都没有判断，需要人工判断。组联动目前结果：“存在组联动情况，请人工判断”
	 */
	@Override
	//通用service验证联动字段(组联动)
	public void verifyLinkageFieldGeneralServiceMethodWithGroupLinkage(String baseName) throws Exception{
		// 例：获取所有就诊－住院与诊断list
		List<CrfTemplateAnzhenXinXueguan> listAll = crfTemplateAnzhenXinXueguanMapper
				.getCrfTemplateAnzhenXinXueguanListByBaseName(baseName);
		if (listAll.size()>0 ) {
			//判断是否有组联动
			for (int i = 0; i < listAll.size(); i++) {
				//存在组联动时，整组都写：“整组存在组联动情况，请人工判断”
				listAll.get(i).setLinkageResult("整组存在组联动情况，请人工判断");
				//每次循环完，更新数据库
				crfTemplateAnzhenXinXueguanMapper.updateCrfTemplateAnzhenXinXueguan(listAll.get(i));
			}
		}
		Thread.sleep(1000);
	}
	
	
	/**
	 * 使用递归：只写了四层逻辑判断
	 * 注意：没有判断 组联动的情况，组联动目前结果都是no，需要人工判断
	 */
	@Override
	//通用service验证联动字段
	public void verifyLinkageFieldGeneralServiceMethod(PhantomJSDriver driver,String baseName) throws Exception{
		// 例：获取所有就诊－住院与诊断list
		List<CrfTemplateAnzhenXinXueguan> listAll = crfTemplateAnzhenXinXueguanMapper
				.getCrfTemplateAnzhenXinXueguanListByBaseName(baseName);
		if (listAll.size()>0) {
			// 循环list(因为没判断联动的值是否有值，全部都循环，所以返回个数为0或者1时就是不是联动的字段)
			for (int i = 0; i < listAll.size(); i++) {
				//新建list，便于接受递归返回的结果，每次循环则变化
				List<CrfTemplateAnzhenXinXueguan> listcrf=new ArrayList<CrfTemplateAnzhenXinXueguan>();
				
				List<CrfTemplateAnzhenXinXueguan> returnlist = ListAndStringUtils.
						searchCrfListReturnAllLinkageFieldsList(listAll, listAll.get(i),listcrf);
				//判断返回的returnlist个数，即联动的层级结构
				//个数==0或==1,一层逻辑暂时不做判断
				if (returnlist.size()==0 || returnlist.size()==1) {
					listAll.get(i).setLinkageResult("不是联动字段");
				}
				//两层逻辑	
				else if (returnlist.size()==2){
					//先判断元素没有联动时，是否存在，存在直接no，不存在再继续判断（list.get(i)==returnlist.get(0)）
					if (SeleniumUtils.isElementPresent(driver,listAll.get(i).getIdXpath())) {
						listAll.get(i).setLinkageResult("no");
					}else {//不存在 
						Boolean b = SeleniumUtils.isSelectByValuePresent(driver, returnlist.get(1).getIdXpath(), 
								ListAndStringUtils.displayMainValueToSelectByValue(listAll.get(i).getDisplayMainValue()));
						//先判断联动的下拉框是否能选择对应的值，能则继续判断，不能则no
						if (b) {
							new Select(driver.findElementById(returnlist.get(1).getIdXpath())).
							selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(listAll.get(i).getDisplayMainValue()));
							//检查是否存在字段
							//存在,则结果直接为pass
							if (SeleniumUtils.isElementPresent(driver,listAll.get(i).getIdXpath())) {
								listAll.get(i).setLinkageResult("pass");
							}else {//不存在,则结果直接no
								listAll.get(i).setLinkageResult("no");
							}
							//判断之后跟选项归位，以防影响后面元素判断
							new Select(driver.findElementById(returnlist.get(1).getIdXpath())).selectByIndex(0);
						}else {
							listAll.get(i).setLinkageResult("no");
						}
					}
				}	
				
				//三层逻辑		
				else if (returnlist.size()==3){
					//先判断元素没有联动时，是否存在，存在直接no，不存在再继续判断（list.get(i)==returnlist.get(0)）
					if (SeleniumUtils.isElementPresent(driver,listAll.get(i).getIdXpath())) {
						listAll.get(i).setLinkageResult("no");
					}else {//不存在 
						
						//即：开启returnlist.get(2)，再开启returnlist.get(1)（关闭则逆序）
						//1.先判断returnlist.get(2)是否能选择
						//页面中设置联动字段为对应选项值,不能则直接no
						Boolean bb = SeleniumUtils.isSelectByValuePresent(driver, returnlist.get(2).getIdXpath(),
								ListAndStringUtils.displayMainValueToSelectByValue(returnlist.get(1).getDisplayMainValue()));
						if (bb) {
							//2.开启最根级节点returnlist.get(2)
							new Select(driver.findElementById(returnlist.get(2).getIdXpath())).
							selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(returnlist.get(1).getDisplayMainValue()));
							//3.检查次级节点returnlist.get(1)是否存在
							if (SeleniumUtils.isElementPresent(driver,returnlist.get(1).getIdXpath())) {
								//4.存在，则判断returnlist.get(1)能否设置联动字段
								//页面中设置联动字段为对应选项值
								Boolean bbb = SeleniumUtils.isSelectByValuePresent(driver, returnlist.get(1).getIdXpath(), 
										ListAndStringUtils.displayMainValueToSelectByValue(listAll.get(i).getDisplayMainValue()));
								if (bbb) {
									new Select(driver.findElementById(returnlist.get(1).getIdXpath())).
									selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(listAll.get(i).getDisplayMainValue()));
									//5.检查是否存在字段
									//存在,则结果直接为pass
									if (SeleniumUtils.isElementPresent(driver,listAll.get(i).getIdXpath())) {
										listAll.get(i).setLinkageResult("pass");
									}else {//不存在,则结果直接no
										listAll.get(i).setLinkageResult("no");
									}
									//6.判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭returnlist.get(1)，再关闭returnlist.get(2))
									new Select(driver.findElementById(returnlist.get(1).getIdXpath())).selectByIndex(0);
								}else {
									listAll.get(i).setLinkageResult("no");
								}
							}else {//不存在,则结果直接no
								listAll.get(i).setLinkageResult("no");
							}
							//判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭returnlist.get(1)，再关闭returnlist.get(2))
							new Select(driver.findElementById(returnlist.get(2).getIdXpath())).selectByIndex(0);
						}else {
							listAll.get(i).setLinkageResult("no");
						}
					}	
				}
				
				//四层逻辑		
				else if (returnlist.size()==4){
					//先判断元素没有联动时，是否存在，存在直接no，不存在再继续判断（list.get(i)==returnlist.get(0)）
					if (SeleniumUtils.isElementPresent(driver,listAll.get(i).getIdXpath())) {
						listAll.get(i).setLinkageResult("no");
					}else {//不存在 
						
						//即：开启returnlist.get(3)，returnlist.get(2)，再开启returnlist.get(1)（关闭则逆序）
						//1.先判断returnlist.get(3)是否能选择
						//页面中设置联动字段为对应选项值,不能则直接no
						Boolean b = SeleniumUtils.isSelectByValuePresent(driver, returnlist.get(3).getIdXpath(),
								ListAndStringUtils.displayMainValueToSelectByValue(returnlist.get(2).getDisplayMainValue()));
						if (b) {
							//2.开启最根级节点returnlist.get(3)
							new Select(driver.findElementById(returnlist.get(3).getIdXpath())).
							selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(returnlist.get(2).getDisplayMainValue()));
							//3.检查次级节点returnlist.get(2)是否存在
							if (SeleniumUtils.isElementPresent(driver,returnlist.get(2).getIdXpath())) {
								//4.存在，则判断returnlist.get(2)能否设置联动字段
								//页面中设置联动字段为对应选项值
								Boolean bb = SeleniumUtils.isSelectByValuePresent(driver, returnlist.get(2).getIdXpath(), 
										ListAndStringUtils.displayMainValueToSelectByValue(returnlist.get(1).getDisplayMainValue()));
								if (bb) {
									new Select(driver.findElementById(returnlist.get(2).getIdXpath())).
									selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(returnlist.get(1).getDisplayMainValue()));
									//5.检查次次级节点returnlist.get(1)是否存在
									if (SeleniumUtils.isElementPresent(driver,returnlist.get(1).getIdXpath())) {
										//6.存在，则判断returnlist.get(1)能否设置联动字段
										Boolean bbb = SeleniumUtils.isSelectByValuePresent(driver, returnlist.get(1).getIdXpath(), 
												ListAndStringUtils.displayMainValueToSelectByValue(listAll.get(i).getDisplayMainValue()));
										if (bbb) {
											new Select(driver.findElementById(returnlist.get(1).getIdXpath())).
											selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(listAll.get(i).getDisplayMainValue()));
											//7.检查元素list.get(i)是否存在
											if (SeleniumUtils.isElementPresent(driver,listAll.get(i).getIdXpath())) {
												listAll.get(i).setLinkageResult("pass");
											}else {//不存在,则结果直接no
												listAll.get(i).setLinkageResult("no");
											}		
											//判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭returnlist.get(1)，再关闭returnlist.get(2)，returnlist.get(3))
											new Select(driver.findElementById(returnlist.get(1).getIdXpath())).selectByIndex(0);
										}else {
											listAll.get(i).setLinkageResult("no");
										}
									}else{
										listAll.get(i).setLinkageResult("no");
									}
									//判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭returnlist.get(1)，再关闭returnlist.get(2)，returnlist.get(3))
									new Select(driver.findElementById(returnlist.get(2).getIdXpath())).selectByIndex(0);
								}else {
									listAll.get(i).setLinkageResult("no");
								}
							}else {//不存在,则结果直接no
								listAll.get(i).setLinkageResult("no");
							}
							//判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭returnlist.get(1)，再关闭returnlist.get(2)，returnlist.get(3))
							new Select(driver.findElementById(returnlist.get(3).getIdXpath())).selectByIndex(0);
						}else {
							listAll.get(i).setLinkageResult("no");
						}
					}
				}
				//每次循环完，更新数据库
				crfTemplateAnzhenXinXueguanMapper.updateCrfTemplateAnzhenXinXueguan(listAll.get(i));
			}//for
		}//if
		
		Thread.sleep(1000);
	}
	
	
	/**
	 * 注意：巨大坑(直接判断逻辑，没有递归，建议使用上面递归，逻辑清晰)
	 */
	/*
	@Override
	//通用service验证联动字段
	public void verifyLinkageFieldGeneralServiceMethod_bak(PhantomJSDriver driver,String baseName) throws Exception{
		// 获取所有就诊－住院与诊断list
		List<CrfTemplateAnzhenXinXueguan> list = crfTemplateAnzhenXinXueguanMapper
				.getCrfTemplateAnzhenXinXueguanListByBaseName(baseName);
		// 循环list
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getChineseName());
			//判断DisplayMainKey是否为空，不为空则继续判断
			//注意坑：可能取出值为空""
			if (list.get(i).getDisplayMainKey()!=null && !"".equals(list.get(i).getDisplayMainKey()) && !" ".equals(list.get(i).getDisplayMainKey())) {
				//不为空，去页面查看是否存在
				//存在,则结果直接为no
				if (SeleniumUtils.isElementPresent(driver,list.get(i).getIdXpath())) {
					list.get(i).setLinkageResult("no");
				}else {//不存在 
					//==========================
					//注意：多级联动的大坑
					//获取对应的联动字段名称
					String linkageEnglishName1 = ListAndStringUtils.displayMainKeyToEnglishName(list.get(i).getDisplayMainKey());
					//在list中查英文名称为linkageEnglishName的CrfTemplateAnzhenXinXueguan
					CrfTemplateAnzhenXinXueguan linkageCrf1 = ListAndStringUtils.searchCrfListReturnOneCrf(list, linkageEnglishName1);
					
					//！！判断linkageCrf1是否存在联动路径
					//如果为空，则为两层联动，执行下面两层的逻辑
					if (linkageCrf1.getDisplayMainKey()==null || "".equals(linkageCrf1.getDisplayMainKey()) || " ".equals(linkageCrf1.getDisplayMainKey())) {
						//===================二层逻辑============================
						//页面中设置联动字段为对应选项值
						Boolean b = SeleniumUtils.isSelectByValuePresent(driver, linkageCrf1.getIdXpath(), 
								ListAndStringUtils.displayMainValueToSelectByValue(list.get(i).getDisplayMainValue()));
						if (b) {
							new Select(driver.findElementById(linkageCrf1.getIdXpath())).
							selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(list.get(i).getDisplayMainValue()));
							//检查是否存在字段
							//存在,则结果直接为pass
							if (SeleniumUtils.isElementPresent(driver,list.get(i).getIdXpath())) {
								list.get(i).setLinkageResult("pass");
							}else {//不存在,则结果直接no
								list.get(i).setLinkageResult("no");
							}
							//判断之后跟选项归位，以防影响后面元素判断
							new Select(driver.findElementById(linkageCrf1.getIdXpath())).selectByIndex(0);
						}else {
							list.get(i).setLinkageResult("no");
						}
						//===================二层逻辑结束============================
						
					}else if (linkageCrf1.getDisplayMainKey()!=null && !"".equals(linkageCrf1.getDisplayMainKey()) && !" ".equals(linkageCrf1.getDisplayMainKey())) {
						//如果不为空，则为多层联动，执行下面三层的逻辑
						//获取对应的联动字段名称
						String linkageEnglishName2 = ListAndStringUtils.displayMainKeyToEnglishName(linkageCrf1.getDisplayMainKey());
						//在list中查英文名称为linkageEnglishName2的CrfTemplateAnzhenXinXueguan
						CrfTemplateAnzhenXinXueguan linkageCrf2 = ListAndStringUtils.searchCrfListReturnOneCrf(list, linkageEnglishName2);
						//如果为空，则为三层联动，执行下面三层的逻辑
						if (linkageCrf2.getDisplayMainKey()==null || "".equals(linkageCrf2.getDisplayMainKey()) || " ".equals(linkageCrf2.getDisplayMainKey())) {
							//===================三层逻辑============================
							//即：开启linkageCrf2，再开启linkageCrf1（关闭则逆序）
							//1.先判断linkageCrf2是否能选择
							//页面中设置联动字段为对应选项值,不能则直接no
							Boolean bb = SeleniumUtils.isSelectByValuePresent(driver, linkageCrf2.getIdXpath(),
									ListAndStringUtils.displayMainValueToSelectByValue(linkageCrf1.getDisplayMainValue()));
							if (bb) {
								//2.开启linkageCrf2
								new Select(driver.findElementById(linkageCrf2.getIdXpath())).
								selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(linkageCrf1.getDisplayMainValue()));
								//3.检查linkageCrf1是否存在
								if (SeleniumUtils.isElementPresent(driver,linkageCrf1.getIdXpath())) {
									//4.存在，则判断linkageCrf1能否设置联动字段
									//页面中设置联动字段为对应选项值
									Boolean bbb = SeleniumUtils.isSelectByValuePresent(driver, linkageCrf1.getIdXpath(), 
											ListAndStringUtils.displayMainValueToSelectByValue(list.get(i).getDisplayMainValue()));
									if (bbb) {
										new Select(driver.findElementById(linkageCrf1.getIdXpath())).
										selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(list.get(i).getDisplayMainValue()));
										//5.检查是否存在字段
										//存在,则结果直接为pass
										if (SeleniumUtils.isElementPresent(driver,list.get(i).getIdXpath())) {
											list.get(i).setLinkageResult("pass");
										}else {//不存在,则结果直接no
											list.get(i).setLinkageResult("no");
										}
										//6.判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭linkageCrf1，再关闭linkageCrf2)
										new Select(driver.findElementById(linkageCrf1.getIdXpath())).selectByIndex(0);
									}else {
										list.get(i).setLinkageResult("no");
									}
								}else {//不存在,则结果直接no
									list.get(i).setLinkageResult("no");
								}
								
								//判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭linkageCrf1，再关闭linkageCrf2)
								new Select(driver.findElementById(linkageCrf2.getIdXpath())).selectByIndex(0);
								
							}else {
								list.get(i).setLinkageResult("no");
							}
							
						}else {//若不为空，则直接写"大于三层，请人工测试"
							list.get(i).setLinkageResult("大于三层，请人工测试");
						}
						//===================三层逻辑结束============================
					}
				}
				//更新数据库
				crfTemplateAnzhenXinXueguanMapper.updateCrfTemplateAnzhenXinXueguan(list.get(i));
			}
		}
		Thread.sleep(500);
	}
	*/
	
	
	@Override
	public List<CrfTemplateAnzhenXinXueguan> getCrfTemplateAnzhenXinXueguanList(
			Map<String, Object> map) throws Exception {
		return crfTemplateAnzhenXinXueguanMapper.getCrfTemplateAnzhenXinXueguanList(map);
	}

	@Override
	public List<CrfTemplateAnzhenXinXueguan> getCrfTemplateAnzhenXinXueguanListByVariableType(
			String variableType) throws Exception {
		return crfTemplateAnzhenXinXueguanMapper.getCrfTemplateAnzhenXinXueguanListByVariableType(variableType);
	}

	@Override
	public CrfTemplateAnzhenXinXueguan getCrfTemplateAnzhenXinXueguanByEnglishName(
			String englishName) throws Exception {
		return crfTemplateAnzhenXinXueguanMapper.getCrfTemplateAnzhenXinXueguanByEnglishName(englishName);
	}

	@Override
	public List<CrfTemplateAnzhenXinXueguan> getCrfTemplateAnzhenXinXueguanListByBaseName(
			String baseName) throws Exception {
		return crfTemplateAnzhenXinXueguanMapper.getCrfTemplateAnzhenXinXueguanListByBaseName(baseName);
	}

	@Override
	public int updateCrfTemplateAnzhenXinXueguan(
			CrfTemplateAnzhenXinXueguan CrfTemplateAnzhenXinXueguan)
			throws Exception {
		return crfTemplateAnzhenXinXueguanMapper.updateCrfTemplateAnzhenXinXueguan(CrfTemplateAnzhenXinXueguan);
	}

	@Override
	public int updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(
			String baseName) throws Exception {
		return crfTemplateAnzhenXinXueguanMapper.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(baseName);
	}

	@Override
	public List<CrfTemplateAnzhenXinXueguan> getVerifyLinkageFieldResultList()
			throws Exception {
		return crfTemplateAnzhenXinXueguanMapper.getVerifyLinkageFieldResultList();
	}

	@Override
	public List<CrfTemplateAnzhenXinXueguan> getVerifyLinkageFieldResultListByBaseName(
			String baseName) throws Exception {
		return crfTemplateAnzhenXinXueguanMapper.getVerifyLinkageFieldResultListByBaseName(baseName);
	}

}
