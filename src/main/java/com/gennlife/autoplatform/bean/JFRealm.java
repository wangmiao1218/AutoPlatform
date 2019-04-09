package com.gennlife.autoplatform.bean;

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

import com.gennlife.autoplatform.service.LoginService;


public class JFRealm extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;

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
		Object principal = upToken.getPrincipal();
		SysOp param = new SysOp();
		param.setLoginCode(principal.toString());
		SysOp sysOp = null;
		try {
			sysOp = loginService.getSysOpByUnameAndPwd(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ByteSource salt = ByteSource.Util.bytes(principal);
		String credentials = sysOp.getLoginPasswd();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysOp, credentials, salt, getName());
		return info;
	}
	
}
