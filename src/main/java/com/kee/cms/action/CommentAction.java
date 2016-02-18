/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */
package com.kee.cms.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.kee.cms.constant.CommentConstant;
import com.kee.cms.entity.vo.JsonVo;
import com.kee.cms.exception.ValidateException;
import com.kee.cms.service.ArticleService;
import com.kee.cms.service.CommentService;
import com.kee.cms.util.HttpUtils;

/**
 * @author Herbert
 * 
 */
@Controller
@RequestMapping("/comment")
public class CommentAction {
	@Autowired
	private CommentService commentService;

	@Autowired
	private ArticleService fileService;

	/**
	 * @param name
	 * @param email
	 * @param fileId
	 * @param content
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public JsonVo<String> add(
			@RequestParam(value = "kind") CommentConstant.kind kind,
			@RequestParam(value = "kindId") long kindId,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "phone", required = false, defaultValue = "") String phone,
			@RequestParam(value = "company", required = false, defaultValue = "") String company,
			HttpServletRequest request, ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (StringUtils.isBlank(email)) {
				json.getErrors().put("email", "邮箱不能为空");
			}
			if (StringUtils.isBlank(content)) {
				json.getErrors().put("content", "内容不能为空");
			}
			json.check();
			commentService.addComment(kindId, CommentConstant.kind.folder,
					phone, email, name, HttpUtils.getIp(request),
					HtmlUtils.htmlEscape(content), company);
			return json;
		} catch (ValidateException e) {
			json.setResult(false);
			return json;
		}
	}

	@RequestMapping(value = "/page.htm", method = RequestMethod.GET)
	public String commentPage(
			@RequestParam(value = "p", defaultValue = "1") int p,
			ModelMap modelMap) {
		modelMap.put("pageVo", commentService.getCommentListPage(p, null));
		return "system/comment/list";
	}
}
