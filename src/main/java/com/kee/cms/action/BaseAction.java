/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */
package com.kee.cms.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.kee.cms.entity.Folder;
import com.kee.cms.entity.vo.FolderVo;
import com.kee.cms.entity.vo.JsonVo;
import com.kee.cms.exception.FolderNotFoundException;
import com.kee.cms.exception.ValidateException;
import com.kee.cms.service.ArticleService;
import com.kee.cms.service.ConfigService;
import com.kee.cms.service.FolderService;
import com.kee.cms.service.TemplateService;

/**
 * 
 * @author Herbert
 * 
 */
public class BaseAction {

	@Autowired
	protected FolderService folderService;

	@Autowired
	protected ArticleService fileService;

	@Autowired
	protected ConfigService configService;

	@Autowired
	protected TemplateService themeService;

	protected final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 参数校验
	 * 
	 * @param json
	 *            json数据Bean
	 * @throws ValidateException
	 */
	protected <T> void validate(JsonVo<T> json) throws ValidateException {
		if (json.getErrors().size() > 0) {
			json.setResult(false);
			throw new ValidateException("有错误发生");
		} else {
			json.setResult(true);
		}
	}

	/**
	 * 包装返回给页面的各级Folder数据
	 * 
	 * @param firstFolderEname
	 * @param secondFolderEname
	 * @param thirdFolderEname
	 * @param fourthFolderEname
	 * @param modelMap
	 * @return
	 * @throws FolderNotFoundException
	 */
	protected List<Folder> packageFolderByEname(String firstFolderEname,
			String secondFolderEname, String thirdFolderEname,
			String fourthFolderEname, ModelMap modelMap)
			throws FolderNotFoundException {
		List<Folder> folderPathList = new ArrayList<Folder>();
		FolderVo firstFolder = folderService.getFolderByEnameAndFatherId(
				firstFolderEname, 0);
		modelMap.addAttribute("firstFolder", firstFolder);
		modelMap.addAttribute("folder", firstFolder);
		folderPathList.add(firstFolder);
		if (StringUtils.isNotBlank(secondFolderEname)) {
			FolderVo secondFolder = folderService.getFolderByEnameAndFatherId(
					secondFolderEname, firstFolder.getFolderId());
			modelMap.addAttribute("secondFolder", secondFolder);
			modelMap.addAttribute("folder", secondFolder);
			folderPathList.add(secondFolder);
			if (StringUtils.isNotBlank(thirdFolderEname)) {
				FolderVo thirdFolder = folderService
						.getFolderByEnameAndFatherId(thirdFolderEname,
								secondFolder.getFolderId());
				modelMap.addAttribute("thirdFolder", thirdFolder);
				modelMap.addAttribute("folder", thirdFolder);
				folderPathList.add(thirdFolder);
				if (StringUtils.isNotBlank(fourthFolderEname)) {
					FolderVo fourthFolder = folderService
							.getFolderByEnameAndFatherId(fourthFolderEname,
									secondFolder.getFolderId());
					modelMap.addAttribute("fourthFolder", fourthFolder);
					modelMap.addAttribute("folder", fourthFolder);
					folderPathList.add(fourthFolder);
				}
			}
		}
		modelMap.addAttribute("folderPathList", folderPathList);
		return folderPathList;
	}
}
