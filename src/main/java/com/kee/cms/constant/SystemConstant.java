package com.kee.cms.constant;
import com.kee.cms.util.PropertyUtils;
/**
 * 系统常量
 * @author keehang
 *
 */
public class SystemConstant {

	/**
	 * 应用部署路径的KEY
	 */
	public static String kee_CMS_ROOT = PropertyUtils.getRoot();

	/**
	 * 上传文件夹
	 */
	public static String UPLOAD_FOLDER = "upload/photo";

	/**
	 * 备份文件夹
	 */
	public static String BACKUP_FOLDER = "/WEB-INF/backup";

	/**
	 * Session中的管理员Key
	 */
	public static final String SESSION_ADMIN = "SESSION_ADMIN";

	/**
	 * 头像URL 180x180
	 */
	public static final String FACE_URL = "http://faceurl.kee.com/face";

	/**
	 * 
	 */
	public static final String LANGUAGE = "language";

}
