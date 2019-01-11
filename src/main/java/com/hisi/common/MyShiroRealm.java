package com.hisi.common;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisi.model.HisiPermission;
import com.hisi.model.HisiRole;
import com.hisi.model.HisiUser;
import com.hisi.service.PermissionService;
import com.hisi.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String userId = (String) token.getPrincipal();
		System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		HisiUser userInfo = userService.findUserByUserId(userId);
		if (userInfo == null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo.getUserid(), // 用户id
				userInfo.getPassword(), // 密码
				ByteSource.Util.bytes(userInfo.getUserid()),// salt=username
				getName() // realm name
		);
		return authenticationInfo;
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Object principal = principals.getPrimaryPrincipal();//用户id
		String userId = principal.toString();
		String roleName=userService.findRoleByUserId(userId);
		authorizationInfo.addRole(roleName);
		List<HisiPermission> permissions=permissionService.getPermissionsByRoleName(roleName);
		for(HisiPermission p:permissions){
			authorizationInfo.addStringPermission(p.getUrl());
		}
		return authorizationInfo;

	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("132123");
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt,
				hashIterations);
		System.out.println(result);
		/*
		 * String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象 uuid
		 * = uuid.replace("-", ""); //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
		 * System.out.println(uuid);
		 */
	}

}