package com.kee.cms.constant;

/**
 * 管理员表的常量
 * @author keehang
 * @version 1.0
 */
public class AdminConstant {

	public static enum Status {
		/**
		 * 初始化，需要修改密码
		 */
		init, /**
		 * 正常，可以使用
		 */
		normal,
		/**
		 * 冻结，无法使用
		 */
		freeze
	};
}
