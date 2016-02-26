package com.kee.cms.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kee.cms.constant.ArticleConstant;
import com.kee.cms.constant.AttachmentConstant;
import com.kee.cms.constant.FolderConstant;
import com.kee.cms.entity.Article;
import com.kee.cms.entity.Folder;
import com.kee.cms.entity.vo.ArticleVo;
import com.kee.cms.entity.vo.AttachmentVo;
import com.kee.cms.entity.vo.JsonVo;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.exception.ArticleNotFoundException;
import com.kee.cms.exception.FolderNotFoundException;
import com.kee.cms.util.HttpUtils;
/**
 * 文件操作
 * @author keehang
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleAction extends AdminBaseAction {

	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String add(
			@RequestParam(value = "folderId", defaultValue = "1") long folderId,
			HttpServletRequest request, HttpServletResponse response)
			throws FolderNotFoundException {
		Article article = articleService.addArticle(folderId);
		try {
			response.sendRedirect(HttpUtils.getBasePath(request)
					+ "/admin/article/update.htm?articleId="
					+ article.getArticleId());
		} catch (IOException e) {
			e.printStackTrace();
			return "system/500";
		}
		return null;
	}

	@RequestMapping(value = "toadd.htm")
	public String toAdd(
			@RequestParam(value = "folderId", defaultValue = "1") long folderId,
			HttpServletResponse response) {
		return "system/article/add";

	}

	/**
	 * @author 进入某种文章的列表分页的首页
	 * @throws FolderNotFoundException
	 * 
	 */
	@RequestMapping(value = "/page.htm", method = RequestMethod.GET)
	public String filePage(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "status", defaultValue = "display") ArticleConstant.Status status,
			@RequestParam(value = "folderId", defaultValue = "0") long folderId,
			HttpServletRequest request, ModelMap modelMap)
			throws FolderNotFoundException {
		List<Folder> pathList = new ArrayList<Folder>();
		if (folderId != 0) {
			pathList = folderService.getFolderPathListByFolderId(folderId);
		}

		modelMap.put("pathList", pathList);
		modelMap.put("folderId", folderId);
		PageVo<ArticleVo> pageVo = articleService
				.getArticlePageByTypeAndStatusPage(folderId, status, pageNum);
		int displayCount = articleService.getArticleCountByStatus(0, 0, 0, 0,
				ArticleConstant.Status.display);
		int hiddenCount = articleService.getArticleCountByStatus(0, 0, 0, 0,
				ArticleConstant.Status.hidden);
		int trashCount = articleService.getArticleCountByStatus(0, 0, 0, 0,
				ArticleConstant.Status.trash);
		int initCount = articleService.getArticleCountByStatus(0, 0, 0, 0,
				ArticleConstant.Status.init);
		modelMap.put("displayCount", displayCount);
		modelMap.put("hiddenCount", hiddenCount);
		modelMap.put("trashCount", trashCount);
		modelMap.put("initCount", initCount);
		modelMap.put("status", status);
		modelMap.put("pageVo", pageVo);

		return "system/article/list";
	}

	/**
	 * @author 进入修改文章页面
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String update(
			@RequestParam(value = "articleId", defaultValue = "1") long articleId,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArticleVo article = articleService.getArticleByArticleId(articleId);
		modelMap.put("article", article);
		modelMap.put("folderAll", folderService.getAllFolderList(0,
				FolderConstant.Status.display));
		modelMap.put("JSESSIONID", request.getSession().getId());
		return "system/article/update";
	}

	/**
	 * @author 修改文章资料
	 * @param fileId
	 * @param folderId
	 * @param name
	 * @param titile
	 * @param content
	 * @param description
	 * @param status
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<Article> update(@RequestParam("articleId") long articleId,
			@RequestParam("folderId") long folderId,
			@RequestParam("name") String name,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("description") String description,
			@RequestParam("status") ArticleConstant.Status status,
			HttpServletRequest request, ModelMap modelMap) {
		JsonVo<Article> json = new JsonVo<Article>();
		Article file = articleService.updateFileByFileId(articleId, folderId, this.getAdmin(request).getAdminId(), name, content, title, description, status);
		json.setT(file);
		json.setResult(true);
		return json;
	}

	/**
	 * @author 彻底删除文件
	 * @throws ArticleNotFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public JsonVo<String> deleteFile(@RequestParam(value = "fileId") long fileId)
			throws ArticleNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		Article file = articleService.getArticleByArticleId(fileId);
		if (file.getOwner().equals(ArticleConstant.Owner.system)) {
			json.setResult(false);
			json.setMsg("这是系统文件无法被删除");
			return json;
		}
		// 删除文件系统
		articleService.deleteFileByArticleId(fileId);
		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentPageByKindId(fileId,
						AttachmentConstant.Kind.article, 1000, 1).getList();
		for (AttachmentVo attachment : attachmentList) {
			attachmentService.deleteAttachment(attachment.getAttachmentId(),
					attachment.getPath());
		}
		json.setResult(true);
		return json;
	}

	/**
	 * 放进回收站，还原
	 * 
	 * @throws ArticleNotFoundException
	 */
	@ResponseBody
	@RequestMapping(value = "/status/update.json", method = RequestMethod.POST)
	public JsonVo<String> updateModify(
			@RequestParam(value = "fileId") long fileId,
			@RequestParam(value = "status") ArticleConstant.Status status)
			throws ArticleNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		Article file = articleService.getArticleByArticleId(fileId);
		if (file.getOwner().equals(ArticleConstant.Owner.system)) {
			json.setResult(false);
			json.setMsg("这是系统文件不能进行操作");
		} else {
			articleService.updateStatusByFileId(fileId, status);
			json.setResult(true);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/status/delete.json", method = RequestMethod.POST)
	public JsonVo<String> deleteList() throws ArticleNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		int count = articleService.getArticleCountByStatus(0, 0, 0, 0,
				ArticleConstant.Status.trash);
		if (count == 0) {
			json.setResult(true);
		} else {
			int i = articleService
					.deleteArticleListByStatus(ArticleConstant.Status.trash);
			if (i == 1) {
				json.setResult(true);
			} else {
				json.setResult(false);
			}
		}
		return json;
	}

}
