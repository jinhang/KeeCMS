package com.kee.cms.action.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kee.cms.constant.ArticleConstant;
import com.kee.cms.entity.vo.ArticleVo;
import com.kee.cms.exception.FolderNotFoundException;
/**
 * 进入后台
 * @author keehang
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminAction extends AdminBaseAction {

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String login(ModelMap modelMap) throws FolderNotFoundException {
		modelMap.put("articleCount", 0);
		modelMap.put("downloadCount", 0);
		modelMap.put("userCount", 0);
		modelMap.put("folderAll", folderService.getAllFolderList(0, null));
		List<ArticleVo> articleList = articleService.getArticleListByStatus(0,
				0, 0, 0, ArticleConstant.Status.display, 0, 10);
		modelMap.put("articleList", articleList);
		return "system/index";
	}

}
