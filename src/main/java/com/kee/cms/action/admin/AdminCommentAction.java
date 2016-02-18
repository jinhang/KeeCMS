/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.action.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kee.cms.constant.CommentConstant;
import com.kee.cms.entity.Comment;
import com.kee.cms.entity.vo.JsonVo;

/**
 * 评论action
 * 
 * @author Zhangjiale
 * 
 */
@Controller
@RequestMapping("/admin/comment")
public class AdminCommentAction extends AdminBaseAction {

	/**
	 * @author 进入所有评论列表页面
	 * 
	 */
	@RequestMapping(value = "/page.htm", method = RequestMethod.GET)
	public String allComment(
			ModelMap modelMap,
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "status", required = false, defaultValue = "hidden") CommentConstant.Status status) {
		modelMap.put("pageVo",
				commentService.getCommentListPage(pageNum, status));
		int displayCount = commentService
				.getCommentByStatusCount(CommentConstant.Status.display);
		int hiddenCount = commentService
				.getCommentByStatusCount(CommentConstant.Status.hidden);
		int trashCount = commentService
				.getCommentByStatusCount(CommentConstant.Status.trash);
		int allCount = trashCount + hiddenCount + displayCount;
		modelMap.put("statusType", status);
		modelMap.put("displayCount", displayCount);
		modelMap.put("hiddenCount", hiddenCount);
		modelMap.put("trashCount", trashCount);
		modelMap.put("allCount", allCount);
		return "system/comment/all";
	}

	/**
	 * @author 进入指定的comment页面
	 * 
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String comment(@RequestParam(value = "commentId") long commentId,
			ModelMap modelMap) {
		modelMap.put("comment", commentService.getCommentById(commentId));
		return "system/comment/detail";
	}

	/**
	 * @author 垃圾评论
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/update/status.json", method = RequestMethod.POST)
	public JsonVo<Comment> cancelAuditing(
			@RequestParam(value = "commentId") long commentId,
			CommentConstant.Status status) {
		JsonVo<Comment> json = new JsonVo<Comment>();
		json.setResult(true);
		commentService.updateCommentStatus(commentId, status);
		return json;
	}
}
