/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */
package com.kee.cms.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kee.cms.constant.AttachmentConstant;
import com.kee.cms.constant.FolderConstant;
import com.kee.cms.entity.Folder;
import com.kee.cms.entity.vo.AttachmentVo;
import com.kee.cms.entity.vo.FolderVo;
import com.kee.cms.entity.vo.JsonVo;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.exception.FolderNotFoundException;
import com.kee.cms.util.RegexUtils;

/**
 * @author 目录action
 * 
 */
@RequestMapping("/admin/folder")
@Controller
public class AdminFolderAction extends AdminBaseAction {

	@Autowired
	private AdminConfigAction adminConfigAction;

	/**
	 * @author 进入添加目录页面
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String login(ModelMap modelMap) throws Exception {
		modelMap.put("folderAll", folderService.getAllFolderList(0, null));
		modelMap.put("folderName", "");
		modelMap.put("folderEname", "");
		return "system/folder/add";
	}

	/**
	 * @author 添加新的目录
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public JsonVo<String> add(
			@RequestParam(value = "fatherId", defaultValue = "0") long fatherId,
			@RequestParam(value = "folderName") String folderName,
			@RequestParam(value = "folderEname") String folderEname,
			@RequestParam(value = "status") FolderConstant.Status status,
			ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();
		// FIXME 检查目录的ename不能用循环遍历检查
		List<FolderVo> list = folderService.getAllFolderList(0, null);
		try {
			if (StringUtils.isBlank(folderName)) {
				json.getErrors().put("folderName", "目录名称不能为空");
			}
			if (StringUtils.isBlank(folderEname)) {
				json.getErrors().put("folderEname", "英文名称不能为空");
			} else if (!RegexUtils.isAlphaUnderline(folderEname)) {
				json.getErrors().put("folderEname", "只能是英文字母，数字和下划线");
			} else {
				for (Folder folder : list) {
					if (folderEname.equals(folder.getEname())) {
						json.getErrors().put("folderEname", "英文名称不能重复");
					}
				}
			}
			// 检测校验结果
			validate(json);
			folderService.addFolder(fatherId, folderName, status,
					folderEname.toLowerCase(), FolderConstant.Rank.everyone,
					FolderConstant.Type.folder);
			json.setResult(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * @author 进入目录列表
	 * @throws FolderNotFoundException
	 * 
	 */
	@RequestMapping(value = "/page.htm", method = RequestMethod.GET)
	public String page(
			@RequestParam(value = "folderId", defaultValue = "0") long folderId,
			ModelMap modelMap) throws FolderNotFoundException {
		List<FolderVo> list = folderService.getFolderListByFatherId(folderId,
				null);
		List<Folder> pathList = folderService
				.getFolderPathListByFolderId(folderId);
		Folder folder = new Folder();
		if (folderId == 0) {
			folder.setFolderId(0);
			folder.setName("Home");
		} else {
			folder = folderService.getFolderById(folderId);
		}
		modelMap.put("folder", folder);
		modelMap.put("list", list);
		modelMap.put("pathList", pathList);
		modelMap.put("folderAll", folderService.getAllFolderList(0, null));
		return "system/folder/page";
	}

	/**
	 * @author 进入修改目录资料页面
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String oneFolder(@RequestParam("folderId") long folderId,
			@RequestParam(value = "p", defaultValue = "1") int p,
			ModelMap modelMap, HttpServletRequest request) throws Exception {
		Folder folder = folderService.getFolderById(folderId);
		if (folder.getContent() == null) {
			folder.setContent("");
		}
		if (folder.getFatherId() == 0) {
			modelMap.put("fatherFolderName", "未分类");
		} else {
			Folder fatherFolder = folderService.getFolderById(folder
					.getFatherId());
			modelMap.put("fatherFolderName", fatherFolder.getName());
		}
		PageVo<AttachmentVo> pageVo = attachmentService
				.getAttachmentPageByKindId(folderId,
						AttachmentConstant.Kind.folder, 12, p);
		pageVo.getArgs().put("folderId", folderId + "");
		modelMap.put("folder", folder);
		modelMap.put("folderAll", folderService.getAllFolderList(0, null));
		modelMap.put("JSESSIONID", request.getSession().getId());
		modelMap.put("attachmentPage", pageVo);
		return "system/folder/update";
	}
	
	/**
	 * @author 进入修改目录资料页面
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/photo.htm", method = RequestMethod.GET)
	public String photo(@RequestParam("folderId") long folderId,
			@RequestParam(value = "p", defaultValue = "1") int p,
			ModelMap modelMap, HttpServletRequest request) throws Exception {
		Folder folder = folderService.getFolderById(folderId);
		if (folder.getContent() == null) {
			folder.setContent("");
		}
		if (folder.getFatherId() == 0) {
			modelMap.put("fatherFolderName", "未分类");
		} else {
			Folder fatherFolder = folderService.getFolderById(folder
					.getFatherId());
			modelMap.put("fatherFolderName", fatherFolder.getName());
		}
		PageVo<AttachmentVo> pageVo = attachmentService
				.getAttachmentPageByKindId(folderId,
						AttachmentConstant.Kind.folder, 12, p);
		pageVo.getArgs().put("folderId", folderId + "");
		modelMap.put("folder", folder);
		modelMap.put("folderAll", folderService.getAllFolderList(0, null));
		modelMap.put("JSESSIONID", request.getSession().getId());
		modelMap.put("attachmentPage", pageVo);
		return "system/folder/photo";
	}

	/**
	 * @author 修改目录资料
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<String> updateFolder(
			@RequestParam(value = "folderId") long folderId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "ename") String ename,
			@RequestParam(value = "content", required = false) String content) {

		JsonVo<String> json = new JsonVo<String>();
		// FIXME 检查目录的ename不能用循环遍历检查
		List<FolderVo> list = folderService.getAllFolderList(0, null);
		try {
			if (name.equals("")) {
				json.getErrors().put("name", "目录名称不能为空");
			}
			if (ename.equals("")) {
				json.getErrors().put("ename", "英文名称不能为空");
			} else {
				for (Folder folder : list) {
					if (folderId != folder.getFolderId()) {
						if (ename.equals(folder.getEname())) {
							json.getErrors().put("folderEname", "英文名称不能重复");
						}
					}
				}
			}

			// 检测校验结果
			validate(json);
			String newEname = ename.toLowerCase();
			folderService.updateFolderById(folderId, newEname, name, content);

			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * @author 目录排序
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/sort.json", method = RequestMethod.POST)
	public JsonVo<String> delete(
			@RequestParam(value = "sortJson") String sortJson) {
		JsonVo<String> json = new JsonVo<String>();
		JSONArray array = JSONArray.fromObject(sortJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject folder = array.getJSONObject(i);
			String folderId = folder.get("folderId").toString();
			String sort = folder.get("sort").toString();
			folderService.updateSort(Long.parseLong(folderId),
					Integer.parseInt(sort));
		}
		json.setResult(true);
		return json;
	}

	/**
	 * @author 删除目录
	 * @throws FolderNotFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public JsonVo<String> delete(@RequestParam(value = "folderId") long folderId)
			throws FolderNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		List<FolderVo> folderList = folderService.getFolderListByFatherId(
				folderId, null);
		FolderVo folder = folderService.getFolderById(folderId);
		if (folder.getOwner().equals(FolderConstant.Owner.system)) {
			json.setResult(false);
			json.setMsg("此目录是系统目录，无法删除！");
		} else {
			if (folderList.size() == 0) {
				int count = articleService.getArticleCountByFoderId(
						folder.getFirstFolderId(), folder.getSecondFolderId(),
						folder.getThirdFolderId(), folder.getFourthFolderId());
				if (count != 0) {
					json.setResult(false);
					json.setMsg("此目录下还有文件,不能被删除。");
				} else {
					json.setResult(true);
					folderService.deleteFolderById(folderId);
				}
			} else {
				json.setResult(false);
				json.setMsg("此目录下有子目录，不能删除。");
			}
		}
		return json;
	}

	/**
	 * @author 更新目录类型
	 * @throws FolderNotFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/type.json", method = RequestMethod.POST)
	public JsonVo<String> type(@RequestParam(value = "folderId") long folderId,
			@RequestParam(value = "type") FolderConstant.Type type)
			throws FolderNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		folderService.updateType(folderId, type);
		return json;
	}

	/**
	 * @author 更新目录状态
	 * @throws FolderNotFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/status.json", method = RequestMethod.POST)
	public JsonVo<String> status(
			@RequestParam(value = "folderId") long folderId,
			@RequestParam(value = "status") FolderConstant.Status status)
			throws FolderNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		folderService.updateStatus(folderId, status);
		return json;
	}

}
