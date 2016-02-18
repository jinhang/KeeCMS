/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kee.cms.constant.ArticleConstant;
import com.kee.cms.entity.Article;
import com.kee.cms.entity.vo.ArticleVo;

/**
 * 文件服务
 * 
 * @author Harbored
 * 
 */
@Repository
public interface ArticleDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加文件
	 * 
	 * @return Integer
	 */
	public int addArticle(Article article);

	public int deleteArticleListByStatus(
			@Param("status") ArticleConstant.Status status);

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除文件
	 * 
	 * @return boolean
	 */
	public boolean deleteArticle(@Param("articleId") long articleId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改文件
	 * 
	 * @param article
	 * @return Integer
	 */
	public int updateArticle(Article article);

	/**
	 * 放进回收站或者还原
	 * 
	 * @param Article
	 * @return Integer
	 */
	public int updateStatusByArticleId(@Param("articleId") long articleId,
			@Param("status") ArticleConstant.Status status);

	/**
	 * 更新浏览人数
	 * 
	 * @param articleId
	 * @param viewCount
	 * @return int
	 */
	public int updateViewCount(@Param("articleId") long articleId,
			@Param("viewCount") int viewCount);

	/**
	 * 更新评论数
	 * 
	 * @param articleId
	 * @param commentCount
	 * @return int
	 */

	public int updateCommentCount(@Param("articleId") long articleId,
			@Param("commentCount") int commentCount);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 得到文件
	 * 
	 * @param articleId
	 * @return File
	 */
	public ArticleVo getArticleById(@Param("articleId") long articleId);

	/**
	 * 得到目录的文件的列表
	 * 
	 * @param foderId
	 * @return List<FileVo>
	 */
	public List<ArticleVo> getArticleListByFoderIdPath(
			@Param("firstFolderId") long firstFolderId,
			@Param("secondFolderId") long secondFolderId,
			@Param("thirdFolderId") long thirdFolderId,
			@Param("fourthFolderId") long fourthFolderId,
			@Param("status") ArticleConstant.Status status,
			@Param("offset") long offset, @Param("rows") long rows);

	/**
	 * 得到目录的所有文件的数量
	 * 
	 * @param foderId
	 * @return Integer
	 */
	public int getArticleCountByFoderIdPath(
			@Param("firstFolderId") long firstFolderId,
			@Param("secondFolderId") long secondFolderId,
			@Param("thirdFolderId") long thirdFolderId,
			@Param("fourthFolderId") long fourthFolderId,
			@Param("status") ArticleConstant.Status status);

	/**
	 * 得到某种显示的文件的列表
	 * 
	 * @param foderId
	 * @return List<FileVo>
	 */
	public List<ArticleVo> getArticleListByStatus(
			@Param("firstFolderId") long firstFolderId,
			@Param("secondFolderId") long secondFolderId,
			@Param("thirdFolderId") long thirdFolderId,
			@Param("fourthFolderId") long fourthFolderId,
			@Param("status") ArticleConstant.Status status,
			@Param("offset") long offset, @Param("rows") long rows);

	public int getArticleCountByStatus(
			@Param("firstFolderId") long firstFolderId,
			@Param("secondFolderId") long secondFolderId,
			@Param("thirdFolderId") long thirdFolderId,
			@Param("fourthFolderId") long fourthFolderId,
			@Param("status") ArticleConstant.Status status);

	/**
	 * @param firstFolderId
	 * @param secondFolderId
	 * @param thirdFolderId
	 * @param fourthFolderId
	 * @return
	 */
	public int getArticleCountByFoderId(
			@Param("firstFolderId") long firstFolderId,
			@Param("secondFolderId") long secondFolderId,
			@Param("thirdFolderId") long thirdFolderId,
			@Param("fourthFolderId") long fourthFolderId);

}
