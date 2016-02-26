
package com.kee.cms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kee.cms.constant.ArticleConstant;
import com.kee.cms.constant.AttachmentConstant;
import com.kee.cms.constant.CommentConstant;
import com.kee.cms.dao.ArticleDao;
import com.kee.cms.dao.FolderDao;
import com.kee.cms.entity.Admin;
import com.kee.cms.entity.Article;
import com.kee.cms.entity.vo.ArticleVo;
import com.kee.cms.entity.vo.FolderVo;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.exception.ArticleNotFoundException;
import com.kee.cms.exception.FolderNotFoundException;

/**
 * 
 * 文章服务
 * 
 * @author keehang
 * 
 */
@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private AdminService adminService;

	@Autowired
	private FolderService folderService;

	@Autowired
	private FolderDao folderDao;

	@Autowired
	private CommentService commentService;

	@Autowired
	private AttachmentService attachmentService;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加文件
	 * 
	 * @param folderId
	 * @param adminId
	 * @param picture
	 *            {@link:FileConstant.PICTURE}
	 * @param name
	 * @param content
	 * @param type
	 * @param status
	 * @param createTime
	 * @return
	 * @throws FolderNotFoundException
	 */
	@CacheEvict(value = "article", allEntries = true)
	public Article addArticle(long folderId) throws FolderNotFoundException {
		FolderVo folder = folderService.getFolderById(folderId);
		Article article = new Article();
		Date now = new Date();
		article.setFirstFolderId(folder.getFirstFolderId());
		article.setSecondFolderId(folder.getSecondFolderId());
		article.setThirdFolderId(folder.getThirdFolderId());
		article.setFourthFolderId(folder.getFourthFolderId());
		article.setCreateTime(now);
		article.setUpdateTime(now);
		article.setExpireTime(now);
		articleDao.addArticle(article);
		return articleDao.getArticleById(article.getArticleId());
	}



	/**
	 * 删除文件
	 * 
	 * @param fileId
	 * @return boolean
	 */
	@CacheEvict(value = "article", allEntries = true)
	public boolean deleteFileByArticleId(long articleId) {
		return articleDao.deleteArticle(articleId);
	}

	public int deleteArticleListByStatus(ArticleConstant.Status status) {
		return articleDao.deleteArticleListByStatus(status);
	}

	/**
	 * 修改文件
	 * 
	 * @param fileId
	 * @param folderId
	 * @param adminId
	 * @param picture
	 * @param name
	 * @param content
	 * @param type
	 * @param status
	 * @return
	 */
	@CacheEvict(value = "article", allEntries = true)
	public Article updateFileByFileId(long fileId, long folderId,long adminId, String name, String content,
			String title, String description,ArticleConstant.Status status) {
		Article article = articleDao.getArticleById(fileId);
		FolderVo folder = folderDao.getFolderById(folderId);
		article.setFirstFolderId(folder.getFirstFolderId());
		article.setSecondFolderId(folder.getSecondFolderId());
		article.setThirdFolderId(folder.getThirdFolderId());
		article.setFourthFolderId(folder.getFourthFolderId());
		article.setAdminId(adminId);
		article.setName(name);
		article.setContent(content);
		article.setTitle(title);
		article.setDescription(description);
		article.setViewCount(0);
		article.setCommentCount(0);
		article.setStatus(status);
		article.setUpdateTime(new Date());
		articleDao.updateArticle(article);
		return article;
	}

	/**
	 * 修改文件的状态
	 * 
	 * @param fileId
	 * @param status
	 * @return boolean
	 * 
	 */
	public void updateStatusByFileId(long articleId,
			ArticleConstant.Status status) {
		articleDao.updateStatusByArticleId(articleId, status);
	}

	/**
	 * 更新浏览人数
	 * 
	 * @param fileId
	 * @param viewCount
	 * 
	 */
	public void updateViewCount(long articleId, int nowViewCount) {
		articleDao.updateViewCount(articleId, nowViewCount + 1);
	}

	/**
	 * 更新评论数
	 * 
	 * @param fileId
	 */
	public void updateCommentCount(long articleId) {
		int commentCount = commentService
				.getCommentCountByFatherId(articleId,
						CommentConstant.kind.article, 0,
						CommentConstant.Status.display);
		articleDao.updateCommentCount(articleId, commentCount);
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 得到文件
	 * 
	 * @param fileId
	 * @return File
	 * @throws ArticleNotFoundException
	 */
	@Cacheable(value = "article", key = "'getArticleByArticleId_'+#articleId")
	public ArticleVo getArticleByArticleId(long articleId)
			throws ArticleNotFoundException {

		ArticleVo articleVo = articleDao.getArticleById(articleId);
		if (articleVo == null) {
			throw new ArticleNotFoundException(articleId + " 文件，不存在");
		} else {
			Admin admin = adminService.getAdminById(articleVo.getAdminId());

			articleVo
					.setFolder(folderDao.getFolderById(articleVo.getFolderId()));
			articleVo.setAdmin(admin);
			articleVo.setAttachmentList(attachmentService
					.getAttachmentListByKindId(articleId,
							AttachmentConstant.Kind.article,
							AttachmentConstant.Status.display));
			return articleVo;
		}
	}

	/**
	 * 得到目录的显示的文件分页
	 * 
	 * @param folderId
	 * @return pageVo
	 * @throws FolderNotFoundException
	 */
	@Cacheable(value = "article", key = "'getArticlePageByFolderId_'+#folderId+'_'+#pageNum+'_'+#rows")
	public PageVo<ArticleVo> getArticlePageByFolderId(long folderId,
			int pageNum, int rows) throws FolderNotFoundException {
		PageVo<ArticleVo> pageVo = new PageVo<ArticleVo>(pageNum);
		FolderVo folder = folderService.getFolderById(folderId);
		pageVo.setRows(rows);
		pageVo.setCount(this.getArticleCountByFoderIdPath(
				folder.getFirstFolderId(), folder.getSecondFolderId(),
				folder.getThirdFolderId(), folder.getFourthFolderId(),
				ArticleConstant.Status.display));
		List<ArticleVo> articlelist = this.getArticleListByFoderIdPath(
				folder.getFirstFolderId(), folder.getSecondFolderId(),
				folder.getThirdFolderId(), folder.getFourthFolderId(),
				ArticleConstant.Status.display, pageVo.getOffset(),
				pageVo.getRows());
		for (ArticleVo article : articlelist) {
			article.setAttachmentList(attachmentService
					.getAttachmentPageByKindId(article.getArticleId(),
							AttachmentConstant.Kind.article, 1000, 1).getList());
		}
		pageVo.setList(articlelist);
		return pageVo;
	}

	public PageVo<ArticleVo> getArticlePageByFoderIdPath(long folderId,
			int pageNum) throws FolderNotFoundException {
		PageVo<ArticleVo> pageVo = new PageVo<ArticleVo>(pageNum);
		pageVo.setRows(10);
		FolderVo folder = folderService.getFolderById(folderId);
		List<ArticleVo> list = this.getArticleListByFoderIdPath(
				folder.getFirstFolderId(), folder.getSecondFolderId(),
				folder.getThirdFolderId(), folder.getFourthFolderId(), null,
				pageVo.getOffset(), pageVo.getRows());
		Collections.reverse(list);
		pageVo.setList(list);
		pageVo.setCount(this.getArticleCountByFoderIdPath(
				folder.getFirstFolderId(), folder.getSecondFolderId(),
				folder.getThirdFolderId(), folder.getFourthFolderId(), null));
		return pageVo;
	}

	/**
	 * 得到目录下的文件
	 * 
	 * @param foderId
	 * @return
	 * @throws FolderNotFoundException
	 */
	public List<ArticleVo> getArticleListByFoderIdPath(long firstFolderId,
			long secondFolderId, long thirdFolderId, long fourthFolderId,
			ArticleConstant.Status status, long offset, long rows)
			throws FolderNotFoundException {
		List<ArticleVo> articlelist = articleDao.getArticleListByFoderIdPath(
				firstFolderId, secondFolderId, thirdFolderId, fourthFolderId,
				status, offset, rows);
		for (ArticleVo article : articlelist) {
			article.setAttachmentList(attachmentService
					.getAttachmentPageByKindId(article.getArticleId(),
							AttachmentConstant.Kind.article, 1000, 1).getList());
			article.setAdmin(adminService.getAdminById(article.getAdminId()));
			article.setFolder(folderService.getFolderById(article.getFolderId()));
			article.setFolderPathList(folderService
					.getFolderPathListByFolderId(article.getFolderId()));
		}
		return articlelist;
	}

	/**
	 * 得到目录的某种文件的数量
	 * 
	 * @param folderId
	 * @param type
	 * @param status
	 * @return Integer
	 */
	public int getArticleCountByFoderIdPath(long firstFolderId,
			long secondFolderId, long thirdFolderId, long fourthFolderId,
			ArticleConstant.Status status) {
		return articleDao.getArticleCountByFoderIdPath(firstFolderId,
				secondFolderId, thirdFolderId, fourthFolderId, status);
	}

	/**
	 * @param firstFolderId
	 * @param secondFolderId
	 * @param thirdFolderId
	 * @param fourthFolderId
	 * @return
	 */
	public int getArticleCountByFoderId(long firstFolderId,
			long secondFolderId, long thirdFolderId, long fourthFolderId) {
		return articleDao.getArticleCountByFoderId(firstFolderId,
				secondFolderId, thirdFolderId, fourthFolderId);
	}

	/**
	 * 获取某种文件的分页
	 * 
	 * @param type
	 * @param status
	 * @param pageNum
	 * @return PageVo<File>
	 * @throws FolderNotFoundException
	 * 
	 */
	public PageVo<ArticleVo> getArticlePageByTypeAndStatusPage(long folderId,
			ArticleConstant.Status status, int pageNum)
			throws FolderNotFoundException {
		PageVo<ArticleVo> pageVo = new PageVo<ArticleVo>(pageNum);
		pageVo.setRows(20);
		pageVo.getArgs().put("status", status.name());
		List<ArticleVo> list = new ArrayList<ArticleVo>();
		int count = 0;
		if (folderId == 0) {
			list = this.getArticleListByStatus(0, 0, 0, 0, status,
					pageVo.getOffset(), pageVo.getRows());
			count = this.getArticleCountByStatus(0, 0, 0, 0, status);
		} else {
			FolderVo folder = folderService.getFolderById(folderId);
			list = this.getArticleListByStatus(folder.getFirstFolderId(),
					folder.getSecondFolderId(), folder.getThirdFolderId(),
					folder.getFourthFolderId(), status, pageVo.getOffset(),
					pageVo.getRows());
			count = this.getArticleCountByStatus(folder.getFirstFolderId(),
					folder.getSecondFolderId(), folder.getThirdFolderId(),
					folder.getFourthFolderId(), status);
		}
		for (ArticleVo articleVo : list) {
			articleVo
					.setFolder(folderDao.getFolderById(articleVo.getFolderId()));

			articleVo.setFolderPathList(folderService
					.getFolderPathListByFolderId(articleVo.getFolderId()));
		}
		pageVo.setList(list);
		pageVo.setCount(count);
		return pageVo;
	}

	/**
	 * 获取不同类型的文件的列表
	 * 
	 * @param type
	 * @param status
	 * @param offset
	 * @param rows
	 * @return List<File>
	 * 
	 */
	public List<ArticleVo> getArticleListByStatus(long firstFolderId,
			long secondFolderId, long thirdFolderId, long fourthFolderId,
			ArticleConstant.Status status, long offset, long rows) {
		return articleDao.getArticleListByStatus(firstFolderId, secondFolderId,
				thirdFolderId, fourthFolderId, status, offset, rows);
	}

	/**
	 * 获取不同类型的文件的数量
	 * 
	 * @param type
	 * @param status
	 * @param Integer
	 * 
	 */
	public int getArticleCountByStatus(long firstFolderId, long secondFolderId,
			long thirdFolderId, long fourthFolderId,
			ArticleConstant.Status status) {
		return articleDao.getArticleCountByStatus(firstFolderId,
				secondFolderId, thirdFolderId, fourthFolderId, status);
	}

}
