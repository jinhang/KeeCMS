package com.kee.cms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kee.cms.constant.CommentConstant;
import com.kee.cms.dao.CommentDao;
import com.kee.cms.entity.Comment;
import com.kee.cms.entity.vo.CommentVo;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.util.AuthUtils;

/**
 * 评论服务
 * 
 * @author keehang
 * 
 */
@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private UserService userService;

	/**
	 * 增加评论
	 * 
	 * @param fileId
	 * @param fatherId
	 * @param email
	 * @param name
	 * @param ip
	 * @param content
	 * @return Comment
	 * 
	 */
	public Comment addComment(long kindId, CommentConstant.kind kind,
			String phone, String email, String name, String ip, String content,
			String company) {
		Comment comment = new Comment();
		comment.setKindId(kindId);
		comment.setFatherId(0);
		comment.setEmail(email);
		comment.setStatus(CommentConstant.Status.hidden);
		comment.setContent(content);
		comment.setCreateTime(new Date());
		comment.setIp(ip);
		comment.setName(name);
		comment.setKind(kind);
		comment.setPhone("");
		comment.setUrl(company);
		commentDao.addComment(comment);
		return comment;

	}
	/**
	 * 修改评论状态
	 * 
	 * @param commentId
	 * @param status
	 * @return Integer
	 */
	public int updateCommentStatus(long commentId, CommentConstant.Status status) {
		Comment comment = this.getCommentById(commentId);
		comment.setStatus(status);
		return commentDao.updateCommentStatus(comment);
	}
	/**
	 * 获得评论分页
	 * 
	 * @param fileId
	 * @param pageNum
	 * @return
	 */
	public PageVo<CommentVo> getCommentPage(long kindId,
			CommentConstant.kind kind, int pageNum, int rows) {
		PageVo<CommentVo> pageVo = new PageVo<CommentVo>(pageNum);
		pageVo.setRows(rows);
		pageVo.setCount(this.getCommentCountByFatherId(kindId, kind, 0,
				CommentConstant.Status.display));
		List<CommentVo> commentList = this.getCommentListByFatherId(kindId,
				kind, 0, CommentConstant.Status.display, pageVo.getOffset(),
				pageVo.getRows());
		for (CommentVo comment : commentList) {
			List<CommentVo> childComment = this.getCommentListByFatherId(
					kindId, kind, comment.getCommentId(),
					CommentConstant.Status.display, 0, 50);
			comment.setChildComment(childComment);
		}
		pageVo.setList(commentList);
		return pageVo;
	}

	public int getCommentCountByFatherId(long kindId,
			CommentConstant.kind kind, long fatherId,
			CommentConstant.Status status) {
		return commentDao.getCommentCountByFatherId(kindId, kind, 0, status);
	}

	public List<CommentVo> getCommentListByFatherId(long kindId,
			CommentConstant.kind kind, long fatherId,
			CommentConstant.Status status, long offset, long rows) {
		List<CommentVo> commentList = commentDao.getCommentListByFatherId(
				kindId, kind, fatherId, status, offset, rows);
		for (CommentVo comment : commentList) {
			comment.setFaceUrl(AuthUtils.getFaceUrl(comment.getEmail()));
		}
		return commentList;
	}
	/**
	 * 通过id获得指定评论
	 * 
	 * @param commentId
	 * @return Comment
	 */
	public Comment getCommentById(long commentId) {
		return commentDao.getCommentById(commentId);
	}
	/**
	 * 获得所有评论
	 * 
	 * @param offset
	 * @param rows
	 * @return List<CommentVo>
	 */
	public List<CommentVo> getCommentList(long offset, long rows) {
		return commentDao.getAllList(offset, rows);
	}
	/**
	 * 获得所有评论的数量
	 * 
	 * @return Integer
	 */
	public int getCommentListCount() {
		return commentDao.getAllListCount();
	}
	/**
	 * 获得所有评论的分页
	 * 
	 * @param pageNum
	 * @return PageVo<CommentVo>
	 */
	public PageVo<CommentVo> getCommentListPage(int pageNum,
			CommentConstant.Status status) {
		PageVo<CommentVo> pageVo = new PageVo<CommentVo>(pageNum);
		List<CommentVo> list = new ArrayList<CommentVo>();
		pageVo.setRows(20);
		if (status == null) {
			list = this.getCommentList(pageVo.getOffset(), pageVo.getRows());
			pageVo.setCount(this.getCommentListCount());
		} else {
			list = this.getCommentByStatus(pageVo.getOffset(),
					pageVo.getRows(), status);
			pageVo.setCount(this.getCommentByStatusCount(status));
		}
		for (CommentVo comment : list) {
			if (comment.getContent().length() > 20) {
				comment.setContent(comment.getContent().substring(0, 20)
						+ "...");
			}
		}
		pageVo.setList(list);
		return pageVo;
	}
	/**
	 * 获得某状态下的所有评论
	 * 
	 * @param offset
	 * @param rows
	 * @param status
	 * @return List<CommentVo>
	 */
	public List<CommentVo> getCommentByStatus(long offset, long rows,
			CommentConstant.Status status) {
		return commentDao.getCommentByStatus(offset, rows, status);
	}
	/**
	 * 获得某状态下的评论的数量
	 * 
	 * @param status
	 * @return Integer
	 */
	public int getCommentByStatusCount(CommentConstant.Status status) {
		return commentDao.getCommentByStatusCount(status);
	}
	// /**
	// * 获得某状态下的评论的分页
	// *
	// * @param pageNum
	// * @param status
	// * @return PageVo<CommentVo>
	// */
	// public PageVo<CommentVo> getCommentByStatusPage(int pageNum,
	// CommentConstant.Status status) {
	// PageVo<CommentVo> pageVo = new PageVo<CommentVo>(pageNum);
	// pageVo.setUrl(SystemConstant.BASE_PATH +
	// "admin/comment/auditing/list.htm");
	// pageVo.setRows(5);
	// List<CommentVo> list = this.getCommentByStatus(pageVo.getOffset(),
	// pageVo.getRows(), status);
	// pageVo.setList(list);
	// pageVo.setCount(this.getCommentByStatusCount(status));
	// return pageVo;
	// }
}
