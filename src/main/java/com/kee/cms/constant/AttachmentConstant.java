package com.kee.cms.constant;

/**
 * 附件
 * @author keehang
 * @version 1.0
 */
public class AttachmentConstant {

	/**
	 * 类型
	 * photo相册
	 * file文件
	 * 
	 * @author keehang
	 * 
	 */
	public static enum Type {
		/**
		 * 相册
		 */
		photo, /**
		 * 文件
		 */
		file
	}

	/**
	 * 种类
	 * 
	 * @author keehang
	 * 
	 */
	public static enum Kind {
		folder, article
	}

	public static enum Status {
		/**
		 * 隐藏是嵌入文章的
		 */
		hidden, /**
		 * 没有嵌入文章的
		 */
		display
	}
}
