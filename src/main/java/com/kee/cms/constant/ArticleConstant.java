/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.constant;

/**
 * 文件常量
 * 
 * @author Zhang jiale
 * 
 */
public class ArticleConstant {

	/**
	 * 是否拥护配图
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum Picture {
		no_exist, exist
	};

	/**
	 * 文件状态
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum Status {
		/**
		 * 初始化，将被系统自动清理
		 */
		init,
		/**
		 * 隐藏
		 */
		hidden, /**
		 * 垃圾
		 */
		trash,
		/**
		 * 公开的
		 */
		display,
	};

	/**
	 * @author Herbert
	 * 
	 */
	public static enum Owner {
		/**
		 * 系统创建
		 */
		system, /**
		 * 应用创建
		 */
		app
	};
}
