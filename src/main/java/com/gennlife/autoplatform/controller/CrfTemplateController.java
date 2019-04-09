package com.gennlife.autoplatform.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gennlife.autoplatform.bean.CrfTemplate;
import com.gennlife.autoplatform.service.CrfTemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description: 获取CrfTemplate列表
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:22:02
 */
@Controller
@RequestMapping("crfTemplateController")
public class CrfTemplateController {

	//private static final Logger logger = LoggerFactory.getLogger(CrfTemplateController.class);

	@Autowired
	private CrfTemplateService crfTemplateService;

	/** 
	* @Title: getCrfTemplateList 
	* @Description: 获取CrfTemplateList，使用PageHelper分页
	* @param: SysOp sysOp
	* @param: Integer page
	* @param: Integer limit
	* @param: @throws Exception :
	* @return: PageInfo<CrfTemplate>
	* @throws 
	*/
	@ResponseBody
	//报错：@RequestMapping(value = "getCrfTemplateList", method = RequestMethod.GET)
	@RequestMapping("getCrfTemplateList")
	//public PageInfo<CrfTemplate> getCrfTemplateList(CrfTemplate crfTemplate, Integer page,Integer limit) throws Exception {
	public PageInfo<CrfTemplate> getCrfTemplateList(CrfTemplate crfTemplate,
			@RequestParam(defaultValue="0",required=false)Integer page,
			@RequestParam(defaultValue="10",required=false)Integer limit) throws Exception {
		// 查询列表
		Map<String, Object> map = new HashedMap<>();

		//空指针异常(加@RequestParam)
		PageHelper.startPage(page, limit);
		
		List<CrfTemplate> list = crfTemplateService.getCrfTemplateList(map);

		// 封装为PageHelper实体
		PageInfo<CrfTemplate> pageInfo = new PageInfo<>(list);

		return pageInfo;
	}

}
