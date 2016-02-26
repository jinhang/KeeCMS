package com.kee.cms.constant;

/**
 * 评论表的常量
 * @author keehang
 * @version 1.0
 */
public class CommentConstant {
	/**
	 * @author keehang
	 * 
	 */
	public static enum Status {
		/**
		 * 隐藏
		 */
		hidden,
		/**
		 * 显示
		 */
		display,
		/**
		 * 垃圾
		 */
		trash
	};

	public static enum kind {
		/**
		 * 隐藏
		 */
		article,
		/**
		 * 显示
		 */
		folder
	};
}
