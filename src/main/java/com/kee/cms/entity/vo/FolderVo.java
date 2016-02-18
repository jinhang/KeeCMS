/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.kee.cms.entity.Folder;

public class FolderVo extends Folder {

	private List<FolderVo> folderList = new ArrayList<FolderVo>();

	private List<String> folderPathList = new ArrayList<String>();

	public List<String> getFolderPathList() {
		return folderPathList;
	}

	public void setFolderPathList(List<String> folderPathList) {
		this.folderPathList = folderPathList;
	}

	public List<FolderVo> getFolderList() {
		return folderList;
	}

	public void setFolderList(List<FolderVo> folderList) {
		this.folderList = folderList;
	}

	public long getFirstFolderId() {
		return Long.parseLong(this.getFolderId(1));
	}

	public long getSecondFolderId() {
		return Long.parseLong(this.getFolderId(2));
	}

	public long getThirdFolderId() {
		return Long.parseLong(this.getFolderId(3));
	}

	public long getFourthFolderId() {
		return Long.parseLong(this.getFolderId(4));
	}

	private String getFolderId(int level) {
		String[] ids = this.getPath().split("#");
		if (ids.length < level) {
			return "0";
		} else {
			return ids[level - 1];
		}
	}
}
