
package com.kee.cms.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kee.cms.constant.ConfigConstant;
import com.kee.cms.constant.SystemConstant;
import com.kee.cms.entity.Folder;
import com.kee.cms.exception.TemplateNotFoundException;

/**
 * 模板工具类
 * 
 * @author keehang
 * 
 */
@Service
public class TemplateService {

	private static String FOLDER_TEMPLATE_PREFIX = "folder";
	private static String FILE_TEMPLATE_PREFIX = "article";

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ConfigService configService;

	/**
	 * 得到首页（默认页）模板
	 * 
	 * @return
	 * @throws TemplateNotFoundException
	 */
	public String getDefaultTemplate() throws TemplateNotFoundException {
		List<String> themeOrderList = new ArrayList<String>();
		themeOrderList.add("index");
		themeOrderList.add(FOLDER_TEMPLATE_PREFIX);
		themeOrderList.add(FILE_TEMPLATE_PREFIX);
		for (String theme : themeOrderList) {
			if (this.isExist(theme)) {
				return this.getTemplatePath(theme);
			}
		}
		throw new TemplateNotFoundException("模板文件：index 不存在！！");
	}

	/**
	 * 得到文件夹模板
	 * 
	 * @param folderPathList
	 * @return
	 * @throws TemplateNotFoundException
	 */
	public String getFolderTemplate(List<Folder> folderPathList)
			throws TemplateNotFoundException {
		List<String> themeOrderList = new ArrayList<String>();
		themeOrderList.add(FOLDER_TEMPLATE_PREFIX);
		String themeString = FOLDER_TEMPLATE_PREFIX;
		for (Folder folder : folderPathList) {
			themeString = themeString + "-" + folder.getEname();
			themeOrderList.add(themeString);
		}
		// 模板顺序反转
		Collections.reverse(themeOrderList);
		for (String theme : themeOrderList) {
			if (this.isExist(theme)) {
				return this.getTemplatePath(theme);
			}
		}
		throw new TemplateNotFoundException("模板文件："
				+ this.getTemplatePath(FOLDER_TEMPLATE_PREFIX) + " 不存在！！");
	}

	/**
	 * 得到文件模板
	 * 
	 * @param folderPathList
	 * @param articleId
	 * @return
	 * @throws TemplateNotFoundException
	 */
	public String getArticleTemplate(List<Folder> folderPathList, long articleId)
			throws TemplateNotFoundException {
		List<String> themeOrderList = new ArrayList<String>();
		themeOrderList.add(FILE_TEMPLATE_PREFIX);
		String themeString = FILE_TEMPLATE_PREFIX;
		for (Folder folder : folderPathList) {
			themeString = themeString + "-" + folder.getEname();
			themeOrderList.add(themeString);
		}
		themeOrderList.add(themeString + "-" + articleId);
		// 模板顺序反转
		Collections.reverse(themeOrderList);
		for (String theme : themeOrderList) {
			if (this.isExist(theme)) {
				return this.getTemplatePath(theme);
			}
		}
		throw new TemplateNotFoundException("模板文件："
				+ this.getTemplatePath(FILE_TEMPLATE_PREFIX) + " 不存在！！");
	}

	/**
	 * 得到当前请求需要渲染的模板相对路径
	 * 
	 * @param theme
	 * @return
	 */
	public String getTemplatePath(String template) {
		return "/themes/"
				+ configService.getConfigByKey(ConfigConstant.SYS_THEME) + "/"
				+ template;
	}

	/**
	 * 模板物理地址是否存在
	 * 
	 * @param theme
	 * @return
	 */
	public Boolean isExist(String theme) {
		String themePath = SystemConstant.kee_CMS_ROOT + "/themes/"
				+ configService.getConfigByKey(ConfigConstant.SYS_THEME) + "/"
				+ theme + ".ftl";
		File file = new File(themePath);
		if (file.exists()) {
			return true;
		} else {
			logger.info("模板不存在：" + themePath);
			return false;
		}
	}

}
