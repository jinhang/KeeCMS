package com.kee.cms.entity;

import java.util.Date;

import com.kee.cms.constant.AttachmentConstant;
/**
 * 附件实体
 * @author keehang
 *
 */
public class Attachment {

	private long attachmentId;
	private long kindId;
	private String name;
	private String path;
	private String description;
	private long size;
	private AttachmentConstant.Type type;
	private Date createTime;
	private String link;
	private AttachmentConstant.Kind kind;
	private AttachmentConstant.Status status;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public long getKindId() {
		return kindId;
	}

	public void setKindId(long kindId) {
		this.kindId = kindId;
	}

	public AttachmentConstant.Kind getKind() {
		return kind;
	}

	public void setKind(AttachmentConstant.Kind kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public AttachmentConstant.Type getType() {
		return type;
	}

	public void setType(AttachmentConstant.Type type) {
		this.type = type;
	}

	public AttachmentConstant.Status getStatus() {
		return status;
	}

	public void setStatus(AttachmentConstant.Status status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
