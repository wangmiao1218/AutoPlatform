package com.gennlife.crf.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gennlife.crf.bean.SysOp;
import com.gennlife.crf.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description: 用户模块
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:22:02
 */
@Controller
@RequestMapping("userController")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/** 
	* @Title: getSysOpList 
	* @Description: 获取用户列表
	* @param: @param sysOp
	* @param: @param page
	* @param: @param limit
	* @param: @return
	* @param: @throws Exception :
	* @return: PageInfo<SysOp>
	* @throws 
	*/
	@ResponseBody
	@RequestMapping("getSysOpList")
	public PageInfo<SysOp> getSysOpList(SysOp sysOp,
			@RequestParam(defaultValue="0",required=false)Integer page,
			@RequestParam(defaultValue="10",required=false)Integer limit) throws Exception {
		// 查询列表
		Map<String, Object> map = new HashedMap<>();

		//空指针异常(加@RequestParam)
		PageHelper.startPage(page, limit);
		logger.debug("开始查询用户列表");
		List<SysOp> list = userService.getSysOpList(map);
		
		// 封装为PageHelper实体
		PageInfo<SysOp> pageInfo = new PageInfo<>(list);

		return pageInfo;
	}
	
	
	/** 
	* @Title: toAddPage
	* @Description: 到添加页面
	* @param: @param type
	* @param: @param opId
	* @param: @param map
	* @param: @return
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	@RequestMapping(value="toAddPage",method=RequestMethod.GET)
	public String toAdd(String type, Integer opId, Map<String, Object> map) throws Exception{
		if("modify".equals(type) && opId != null){
			SysOp sysOp = userService.getSysOpById(opId);
			map.put("sysOp", sysOp);
		}
		map.put("type", type);
		
		//返回userAdd.jsp页面
		return "user/userAdd";
	}
	
	
	
}
