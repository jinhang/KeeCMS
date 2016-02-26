package com.kee.cms.constant;

/**
 * 文件常量
 * @author keehang
 * @version 1.0
 */
public class ArticleConstant {

	/**
	 * 是否拥护配图
	 * 
	 * @author keehang
	 * 
	 */
	public static enum Picture {
		no_exist, exist
	};

	/**
	 * 文件状态
	 * 
	 * @author keehang
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
	 * @author keehang
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
