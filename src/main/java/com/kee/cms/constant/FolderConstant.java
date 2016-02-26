package com.kee.cms.constant;

/**
 * 目录属性常量
 * @author keehang
 */
public class FolderConstant {

	public static enum Status {
		hidden, display
	};

	/**
	 * @author keehang
	 * 
	 */
	public static enum Rank {
		/**
		 * 任何人
		 */
		everyone, /**
		 * 登录用户
		 */
		login, /**
		 * vip用户
		 */
		vip, /**
		 * 管理员
		 */
		admin
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

	public static enum Type {
		/**
		 * 相册
		 */
		photo,
		/**
		 * 列表
		 */
		article, /**
		 * 目录
		 */
		folder
	};
}
