/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.entity;

import java.util.Date;

import com.kee.cms.constant.FolderConstant;

/**
 * 目录实体
 * 
 * @author zsy
 * 
 */
public class Folder {
	/**
	 * 目录Id
	 */
	private long folderId;

	/**
	 * 父亲Id
	 */
	private long fatherId;

	/**
	 * 英文名称
	 */
	private String ename;

	/**
	 * 目录名称
	 */
	private String name;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 路径
	 */
	private String path;
	/**
	 * 层级
	 */
	private int level;

	/**
	 * 排序
	 */
	private int sort;

	/**
	 * 文件数
	 */
	private int count;

	/**
	 * 类型
	 */
	private FolderConstant.Type type;

	public FolderConstant.Type getType() {
		return type;
	}

	public void setType(FolderConstant.Type type) {
		this.type = type;
	}

	/**
	 * 
	 */
	private FolderConstant.Owner owner;

	public FolderConstant.Owner getOwner() {
		return owner;
	}

	public void setOwner(FolderConstant.Owner owner) {
		this.owner = owner;
	}

	/**
	 * 等级
	 */
	private FolderConstant.Rank rank;

	/**
	 * 状态
	 */
	private FolderConstant.Status status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public long getFatherId() {
		return fatherId;
	}

	public void setFatherId(long fatherId) {
		this.fatherId = fatherId;
	}

	public String getName() {
		return name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public FolderConstant.Rank getRank() {
		return rank;
	}

	public void setRank(FolderConstant.Rank rank) {
		this.rank = rank;
	}

	public void setStatus(FolderConstant.Status status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public FolderConstant.Status getStatus() {
		return status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
