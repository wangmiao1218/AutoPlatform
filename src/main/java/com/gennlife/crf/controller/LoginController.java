package com.gennlife.crf.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gennlife.crf.bean.SysFuncBean;
import com.gennlife.crf.bean.SysOp;
import com.gennlife.crf.service.CrfTemplateService;
import com.gennlife.crf.service.LoginService;
import com.gennlife.crf.utils.VerifyCodeUtil;

/**
 * @Description: 用户登录控制
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:22:02
 */
@Controller
@RequestMapping("loginController")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CrfTemplateService crfTemplateService;
	

	/**
	 * @Title: getVerifyCode
	 * @Description: 获取验证码
	 * @param: HttpServletResponse response
	 * @param: HttpSession session
	 * @param: @throws IOException
	 * @throws
	 */
	@RequestMapping("getVerifyCode")
	public void getVerifyCode(HttpServletResponse response, HttpSession session)
			throws IOException {
		// 设置页面不缓存
		// HTTP 1.1
		response.setHeader("Pragma", "no-cache");// HTTP 1.1
		// HTTP 1.0
		response.setHeader("Cache-Control", "no-cache");
		// 设置缓存时间，0就是不缓存
		response.setDateHeader("Expires", 0);

		String verifyCode = VerifyCodeUtil.generateTextCode(
				VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		session.setAttribute("verifyCode", verifyCode);
		logger.debug("已将验证码放入到session中：【" + verifyCode + "】");

		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(
				verifyCode, 90, 30, 5, true, Color.WHITE, Color.BLACK, null);

		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());

	}

	
	/**
	 * @Title: login
	 * @Description: 登录(不使用shiro)
	 * @param: String username
	 * @param: String password
	 * @param: String verifyCode
	 * @param: HttpSession session
	 * @param: RedirectAttributes redirectAttributes
	 * @return: String
	 * @throws
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			String password, String verifyCode, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {

		logger.debug("登陆参数为" + username + "---" + password + "---" + verifyCode);

		String sessionVerifyCode = (String) session.getAttribute("verifyCode");
		if (!verifyCode.equals(sessionVerifyCode)) {
			logger.debug("验证码不正确");
			redirectAttributes.addFlashAttribute("errMsg", "验证码不正确");
			return "redirect:/login";
		}

		// 用户名密码
		SysOp param = new SysOp();
		param.setLoginCode(username);
		param.setLoginPasswd(password);

		// 后台查询
		SysOp sysOp = loginService.getSysOpByUnameAndPwd(param);
		if (sysOp == null) {
			logger.debug("用户名密码不正确");
			redirectAttributes.addFlashAttribute("errMsg", "用户名密码不正确");
			return "redirect:/login";
		}
		// 登录用户信息放到session中
		session.setAttribute("loginSysOp", sysOp);
		// 成功返回主页
		//测试return "redirect:/page/list.html";
		return "redirect:/index";

	}

	
	/** 
	* @Title: getMenu 
	* @Description: 通过登录的用户id，查询表，获取菜单列表
	* @param: @param map
	* @param: @return
	* @param: @throws Exception :
	* @return: List<SysFuncBean>
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "getMenu", method = RequestMethod.GET)
	public List<SysFuncBean> getMenu(HttpSession session) throws Exception{
		SysOp sysOp = (SysOp) session.getAttribute("loginSysOp");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("opId", sysOp.getOpId());
		
		//执行查询
		List<SysFuncBean> list = loginService.getSysFuncByOpId(map);
		List<SysFuncBean> newList =new ArrayList<>();;
		//拼装一棵树
		//第一次循环
		for (int i = 0; i < list.size(); i++) {
			SysFuncBean parent = list.get(i);
			//拿到一级节点
			if (list.get(i).getFuncLevel().equals(new Short("1") )) {
				//第二次循环，找其他子节点
				List<SysFuncBean> childList=new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					SysFuncBean child = list.get(j);
					if (parent.getFuncId().equals(child.getSupFuncId())) {
						childList.add(child);
					}
				}
				//将子节点列表放入到父节点中
				parent.setChildren(childList);
				newList.add(parent);
			}
		}
		//返回
		return newList;
	}
	
	
	/** 
	* @Title: logout 
	* @Description: 退出功能 
	* @param: @param session
	* @param: @return
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/login.jsp";
	}
	
	
	/** 
	* @Title: loginShiro 
	* @Description: 使用shiro登录
	* @param: @param username
	* @param: @param password
	* @param: @param verifyCode
	* @param: @param session
	* @param: @param redirectAttributes
	* @param: @return
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	@RequestMapping(value = "loginShiro", method = RequestMethod.POST)
	public String loginShiro(@RequestParam("username") String username,
			String password, String verifyCode, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("登陆参数为" + username + "---" + password + "---" + verifyCode);
		//获取验证码
		String sessionVerifyCode = (String) session.getAttribute("verifyCode");
		if (!verifyCode.equals(sessionVerifyCode)) {
			logger.debug("验证码不正确");
			redirectAttributes.addFlashAttribute("errMsg","验证码不正确");
			return "redirect:/login";
		}
		//当前用户信息
		Subject currentUser = SecurityUtils.getSubject();
		
		//是否经过认证，是否登录
		if (!currentUser.isAuthenticated()) {
			// 构造一个UsernamePasswordToken，传入用户名和密码（来自于前台）
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			//执行认证
			try {
				currentUser.login(token);
			} catch (AuthenticationException ae) {
				// 针对登录时相关的所有异常，在这处理
            	logger.info("****>  发生了异常：" + ae.getMessage());
            	//异常处理(同原来一致)
            	redirectAttributes.addFlashAttribute("errMsg","验证码不正确");
    			return "redirect:/login";
			}
		}
		
		// 怎么获取到用户的对象
		// currentUser.getPrincipal()是realm在构造SimpleAuthenticationInfo的时候,Object principal，传入的sysOp对象
		//登录用户信息放到session中
		session.setAttribute("loginSysOp", currentUser.getPrincipal());
		
		return "redirect:/index";
	}
	
	
}
