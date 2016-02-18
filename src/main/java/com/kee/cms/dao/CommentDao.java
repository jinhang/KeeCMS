/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kee.cms.constant.CommentConstant;
import com.kee.cms.entity.Comment;
import com.kee.cms.entity.vo.CommentVo;

/**
 * 文章评论
 */
@Repository
public interface CommentDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 添加评论
	 * 
	 * @param comment
	 * @return Integer
	 */
	public int addComment(Comment comment);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 评论审核
	 * 
	 * @param comment
	 * @return Integer
	 */
	public int updateCommentStatus(Comment comment);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 获得所有评论
	 * 
	 * @param offset
	 * @param rows
	 * @return List<CommentVo>
	 */
	public List<CommentVo> getAllList(@Param("offset") long offset,
			@Param("rows") long rows);

	/**
	 * 获得所有评论的数量
	 * 
	 * @return Integer
	 */
	public int getAllListCount();

	/**
	 * 获得文件下的评论（分页）
	 * 
	 * @param kindId
	 * @param fatherId
	 * @param status
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<CommentVo> getCommentListByFatherId(
			@Param("kindId") long kindId,
			@Param("kind") CommentConstant.kind kind,
			@Param("fatherId") long fatherId,
			@Param("status") CommentConstant.Status status,
			@Param("offset") long offset, @Param("rows") long rows);

	/**
	 * 获得文件下的评论条数（分页）
	 * 
	 * @param articleId
	 * @param fatherId
	 * @param status
	 * @return
	 */
	public int getCommentCountByFatherId(@Param("kindId") long kindId,
			@Param("kind") CommentConstant.kind kind,
			@Param("fatherId") long fatherId,
			@Param("status") CommentConstant.Status status);

	/**
	 * 通过指定Id获得评论
	 * 
	 * @param commentId
	 * @return Comment
	 */
	public Comment getCommentById(@Param("commentId") long commentId);

	/**
	 * 获得某状态下的评论列表
	 * 
	 * @param offset
	 * @param rows
	 * @param status
	 * @return Integer
	 */
	public List<CommentVo> getCommentByStatus(@Param("offset") long offset,
			@Param("rows") long rows,
			@Param("status") CommentConstant.Status status);

	/**
	 * 获得某状态下的评论数量
	 * 
	 * @param status
	 * @return Integer
	 */
	public int getCommentByStatusCount(
			@Param("status") CommentConstant.Status status);

}
