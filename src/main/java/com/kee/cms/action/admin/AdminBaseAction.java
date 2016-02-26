package com.kee.cms.action.admin;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kee.cms.constant.SystemConstant;
import com.kee.cms.entity.Admin;
import com.kee.cms.entity.vo.JsonVo;
import com.kee.cms.exception.ValidateException;
import com.kee.cms.service.AdminService;
import com.kee.cms.service.ArticleService;
import com.kee.cms.service.AttachmentService;
import com.kee.cms.service.CommentService;
import com.kee.cms.service.ConfigService;
import com.kee.cms.service.FolderService;
import com.kee.cms.service.UserService;
/**
 * 管理员操作父类
 * @author keehang
 * @version 1.0
 */
@Controller
public class AdminBaseAction {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected ConfigService configSevice;

	@Autowired
	protected FolderService folderService;

	@Autowired
	protected ArticleService articleService;

	@Autowired
	protected AttachmentService attachmentService;

	@Autowired
	protected UserService userService;
	@Autowired
	protected AdminService adminService;
	@Autowired
	protected CommentService commentService;

	/**
	 * 参数校验
	 * 
	 * @param json
	 *            json数据Bean
	 * @throws ValidateException
	 */
	protected <T> void validate(JsonVo<T> json) throws ValidateException {
		if (json.getErrors().size() > 0) {
			json.setResult(false);
			throw new ValidateException("有错误发生");
		} else {
			json.setResult(true);
		}
	}

	/**
	 * 从session中获得管理员的信息
	 * 
	 * @param request
	 * @return
	 */
	protected Admin getAdmin(HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute(
				SystemConstant.SESSION_ADMIN);
		return admin;
	}
}
