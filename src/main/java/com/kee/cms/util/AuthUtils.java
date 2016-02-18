/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.kee.cms.constant.SystemConstant;
import com.kee.cms.exception.AuthException;

/**
 * 授权相关的工具类
 * 
 * @author Herbert
 * 
 */
public class AuthUtils {

	/**
	 * 生产密文密码
	 * 
	 * @param password
	 *            明文密码
	 * @param email
	 *            邮箱
	 * @return
	 * @throws AuthException
	 */
	public static String getPassword(String password, String email)
			throws AuthException {
		if (StringUtils.isBlank(password) || StringUtils.isBlank(email)
				|| StringUtils.isBlank(email)) {
			throw new AuthException("值不能为空");
		}
		return DigestUtils.md5Hex(password + email).toLowerCase();
	}

	/**
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		return DigestUtils.md5Hex(str).toLowerCase();
	}

	/**
	 * @param email
	 * @return
	 */
	public static String getFaceUrl(String email) {
		return SystemConstant.FACE_URL + "/" + AuthUtils.MD5(email) + ".jpg";
	}
}
