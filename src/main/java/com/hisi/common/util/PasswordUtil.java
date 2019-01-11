package com.hisi.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtil {
	public Object generateScret(String UserId, String password) {
		String hashAlgorithmName = "MD5";
		Object credentials = password;
		Object salt = ByteSource.Util.bytes(UserId);
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt,
				hashIterations);
		//System.out.println(result);
		return result;
	}
}
