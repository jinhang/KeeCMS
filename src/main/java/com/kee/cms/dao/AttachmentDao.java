package com.kee.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kee.cms.constant.AttachmentConstant;
import com.kee.cms.constant.AttachmentConstant.Status;
import com.kee.cms.entity.Attachment;
import com.kee.cms.entity.vo.AttachmentVo;
@Repository
public interface AttachmentDao {

	

	/**
	 * @param att
	 * @return
	 */
	public int addAttachment(Attachment att);


	/**
	 * @param attachmentId
	 * @return
	 */
	public int deleteAttachment(@Param("attachmentId") long attachmentId);



	/**
	 * @param attachmentId
	 * @param status
	 */
	public void updateStatusByAttachmentId(
			@Param("attachmentId") long attachmentId,
			@Param("status") Status status);

	public int updateLinkByAttachmentId(
			@Param("attachmentId") long attachmentId, @Param("link") String link);

	

	/**
	 * @param attachmentId
	 * @return
	 */
	public Attachment getAttachmentById(@Param("attachmentId") long attachmentId);

	/**
	 * @param folderId
	 * @return
	 */
	public int getAttachmentCountByKindId(@Param("kindId") long kindId,
			@Param("kind") AttachmentConstant.Kind kind,
			@Param("status") AttachmentConstant.Status status);

	/**
	 * @param folderId
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<AttachmentVo> getAttachmentListByKindId(
			@Param("kindId") long kindId,
			@Param("kind") AttachmentConstant.Kind kind,
			@Param("status") AttachmentConstant.Status status,
			@Param("offset") int offset, @Param("rows") int rows);

}
