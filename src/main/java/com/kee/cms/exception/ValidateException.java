
package com.kee.cms.exception;

/**
 * 
 * 系统配置Key获得的Value为空
 * 
 * @author keehang
 * 
 */
public class ValidateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidateException(String msg) {
		super(msg);
	}
}
