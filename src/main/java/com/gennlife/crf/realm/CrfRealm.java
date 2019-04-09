package com.gennlife.crf.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.gennlife.crf.bean.SysOp;
import com.gennlife.crf.service.LoginService;

public class CrfRealm extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;

	// 授权的回调方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {
		return null;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// 认证
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 获取用户名（与下面一致）
		// String username = upToken.getUsername();
		// System.out.println(username);//admin

		// getPrincipal方法获取到用户名
		Object principal = upToken.getPrincipal();
		// System.out.println(principal);//admin

		// 用户名和登录密码
		SysOp param = new SysOp();
		param.setLoginCode(principal.toString());

		// 通过后台查询.注意：需要查看sql写的是否正确
		SysOp sysOp = null;
		try {
			sysOp = loginService.getSysOpByUnameAndPwd(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 盐值就是用户名
		ByteSource salt = ByteSource.Util.bytes(principal);
		String credentials = sysOp.getLoginPasswd();

		// 第一个参数principal，放入一个用户对象.
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysOp,
				credentials, salt, getName());

		return info;
	}

}
