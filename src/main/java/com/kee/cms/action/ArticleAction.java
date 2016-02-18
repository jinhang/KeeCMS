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

import com.kee.cms.entity.Article;
import com.kee.cms.entity.Folder;
import com.kee.cms.exception.ArticleNotFoundException;
import com.kee.cms.exception.FolderNotFoundException;
import com.kee.cms.exception.TemplateNotFoundException;

/**
 * @author Herbert
 * 
 */
@Controller
public class ArticleAction extends BaseAction {

	/**
	 * 一级目录页
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{firstFolderEname}/{articleId}.htm", method = RequestMethod.GET)
	public String firstFolder(@PathVariable String firstFolderEname,
			@PathVariable long articleId,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		return fourthFolder(firstFolderEname, null, null, null, articleId, p,
				modelMap);
	}

	/**
	 * 二级目录页
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{firstFolderEname}/{secondFolderEname}/{articleId}.htm", method = RequestMethod.GET)
	public String secondFolder(@PathVariable String firstFolderEname,
			@PathVariable String secondFolderEname, @PathVariable long articleId,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		return fourthFolder(firstFolderEname, secondFolderEname, null, null,
				articleId, p, modelMap);
	}

	/**
	 * 三级目录页
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{firstFolderEname}/{secondFolderEname}/{thirdFolderEname}/{articleId}.htm", method = RequestMethod.GET)
	public String thirdFolder(@PathVariable String firstFolderEname,
			@PathVariable String secondFolderEname,
			@PathVariable String thirdFolderEname, @PathVariable long articleId,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		return fourthFolder(firstFolderEname, secondFolderEname,
				thirdFolderEname, null, articleId, p, modelMap);
	}

	/**
	 * 四级目录页
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{firstFolderEname}/{secondFolderEname}/{thirdFolderEname}/{fourthFolderEname}/{articleId}.htm", method = RequestMethod.GET)
	public String fourthFolder(@PathVariable String firstFolderEname,
			@PathVariable String secondFolderEname,
			@PathVariable String thirdFolderEname,
			@PathVariable String fourthFolderEname, @PathVariable long articleId,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		try {
			List<Folder> folderPathList = packageFolderByEname(
					firstFolderEname, secondFolderEname, thirdFolderEname,
					fourthFolderEname, modelMap);
			Article article = fileService.getArticleByArticleId(articleId);
			modelMap.addAttribute("p", p);
			modelMap.addAttribute("article", article);
			return themeService.getArticleTemplate(folderPathList, articleId);
		} catch (FolderNotFoundException e) {
			logger.fatal(e.getMessage());
			return themeService.getTemplatePath("404");
		} catch (TemplateNotFoundException e) {
			logger.fatal(e.getMessage());
			return themeService.getTemplatePath("404");
		} catch (ArticleNotFoundException e) {
			logger.fatal(e.getMessage());
			return themeService.getTemplatePath("404");
		}
	}
}
