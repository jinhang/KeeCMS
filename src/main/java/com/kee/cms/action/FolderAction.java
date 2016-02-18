/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */
package com.kee.cms.action;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kee.cms.entity.Folder;
import com.kee.cms.exception.FolderNotFoundException;
import com.kee.cms.exception.TemplateNotFoundException;

/**
 * @author Herbert
 * 
 */
@Controller
public class FolderAction extends BaseAction {

	/**
	 * 一级目录页
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{firstFolderEname}/index.htm", method = RequestMethod.GET)
	public String firstFolder(@PathVariable String firstFolderEname,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		return fourthFolder(firstFolderEname, null, null, null, p, modelMap);
	}

	/**
	 * 二级目录页
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{firstFolderEname}/{secondFolderEname}/index.htm", method = RequestMethod.GET)
	public String secondFolder(@PathVariable String firstFolderEname,
			@PathVariable String secondFolderEname,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		return fourthFolder(firstFolderEname, secondFolderEname, null, null, p,
				modelMap);
	}

	/**
	 * 三级目录页
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{firstFolderEname}/{secondFolderEname}/{thirdFolderEname}/index.htm", method = RequestMethod.GET)
	public String thirdFolder(@PathVariable String firstFolderEname,
			@PathVariable String secondFolderEname,
			@PathVariable String thirdFolderEname,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		return fourthFolder(firstFolderEname, secondFolderEname,
				thirdFolderEname, null, p, modelMap);
	}

	/**
	 * 四级目录页
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{firstFolderEname}/{secondFolderEname}/{thirdFolderEname}/{fourthFolderEname}/index.htm", method = RequestMethod.GET)
	public String fourthFolder(@PathVariable String firstFolderEname,
			@PathVariable String secondFolderEname,
			@PathVariable String thirdFolderEname,
			@PathVariable String fourthFolderEname,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		try {
			List<Folder> folderPathList = packageFolderByEname(
					firstFolderEname, secondFolderEname, thirdFolderEname,
					fourthFolderEname, modelMap);
			modelMap.addAttribute("p", p);
			return themeService.getFolderTemplate(folderPathList);
		} catch (FolderNotFoundException e) {
			logger.fatal(e.getMessage());
			return themeService.getTemplatePath("404");
		} catch (TemplateNotFoundException e) {
			logger.fatal(e.getMessage());
			return themeService.getTemplatePath("404");
		}
	}

}
