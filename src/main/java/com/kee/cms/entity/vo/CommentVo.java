/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.kee.cms.entity.Comment;

public class CommentVo extends Comment {

	/**
	 * 子评论
	 */
	private List<CommentVo> childComment = new ArrayList<CommentVo>();

	/**
     * 
     */
	private String faceUrl;

	public String getFaceUrl() {
		return faceUrl;
	}

	public void setFaceUrl(String faceUrl) {
		this.faceUrl = faceUrl;
	}

	public List<CommentVo> getChildComment() {
		return childComment;
	}

	public void setChildComment(List<CommentVo> childComment) {
		this.childComment = childComment;
	}

}
