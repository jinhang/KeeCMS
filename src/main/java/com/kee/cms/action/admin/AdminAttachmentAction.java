/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kee.cms.constant.AttachmentConstant;
import com.kee.cms.constant.FolderConstant;
import com.kee.cms.entity.Attachment;
import com.kee.cms.entity.Folder;
import com.kee.cms.entity.vo.AttachmentVo;
import com.kee.cms.entity.vo.JsonVo;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.exception.ArticleNotFoundException;
import com.kee.cms.exception.FolderNotFoundException;
import com.kee.cms.exception.UploadException;
import com.kee.cms.util.UploadUtils;

@Controller
@RequestMapping("/admin/attachment")
public class AdminAttachmentAction extends AdminBaseAction {

	/**
	 * @author 进入某种文件的列表分页的首页
	 * @throws FolderNotFoundException
	 * 
	 */
	@RequestMapping(value = "/page.htm", method = RequestMethod.GET)
	public String filePage(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "folderId", defaultValue = "1") long folderId,
			HttpServletRequest request, ModelMap modelMap)
			throws FolderNotFoundException {
		Folder folder = folderService.getFolderById(folderId);
		PageVo<AttachmentVo> attachmentPage = attachmentService
				.getAttachmentPageByKindId(folderId,
						AttachmentConstant.Kind.folder, 12, pageNum);
		modelMap.put("folderAll", folderService.getAllFolderList(0,
				FolderConstant.Status.display));
		modelMap.put("JSESSIONID", request.getSession().getId());
		modelMap.put("folder", folder);
		modelMap.put("attachmentPage", attachmentPage);
		return "system/attachment/page";
	}

	/**
	 * 增加file
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list.json", method = RequestMethod.GET)
	public String attachment(@RequestParam("kindId") long kindId) {
		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentListByKindId(kindId,
						AttachmentConstant.Kind.article,
						AttachmentConstant.Status.display);
		JSONObject json = new JSONObject();
		json.put("attachmentList", attachmentList);
		return json.toString();
	}

	/**
	 * 附件上传
	 */
	@ResponseBody
	@RequestMapping(value = "/upload.json", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "kindId") long kindId,
			@RequestParam(value = "kind") AttachmentConstant.Kind kind,
			@RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) {
		try {
			attachmentService.addUploadFile(file, file.getOriginalFilename(),
					kindId, kind, AttachmentConstant.Status.display);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		return json.toString();
	}

	/**
	 * @author 彻底删除文件
	 * @throws ArticleNotFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public JsonVo<String> deleteFile(
			@RequestParam(value = "attachmentId") long attachmentId) {
		JsonVo<String> json = new JsonVo<String>();
		Attachment attachment = attachmentService
				.getAttachmentById(attachmentId);
		attachmentService.deleteAttachment(attachmentId, attachment.getPath());
		json.setResult(true);
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/update_status.json", method = RequestMethod.POST)
	public JsonVo<String> updateStatus(
			@RequestParam(value = "attachmentId") long attachmentId,
			@RequestParam(value = "status") AttachmentConstant.Status status) {
		JsonVo<String> json = new JsonVo<String>();
		attachmentService.updateStatusByAttachmentId(attachmentId, status);
		json.setResult(true);
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/update_link.json", method = RequestMethod.POST)
	public JsonVo<String> updateLink(
			@RequestParam(value = "attachmentId") long attachmentId,
			@RequestParam(value = "link") String link) {
		JsonVo<String> json = new JsonVo<String>();
		attachmentService.updateLinkByAttachmentId(attachmentId, link);
		json.setResult(true);
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/ueditor/manager.htm", method = RequestMethod.POST)
	public String photoManager(@RequestParam(value = "kindId") long kindId,
			@RequestParam(value = "kind") AttachmentConstant.Kind kind,
			HttpServletRequest request) {
		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentListByKindId(kindId, kind,
						AttachmentConstant.Status.hidden);
		List<String> picturePathList = new ArrayList<String>();
		for (AttachmentVo attachment : attachmentList) {
			if (attachment.getType().equals(AttachmentConstant.Type.photo)
					&& attachment.getStatus().equals(
							AttachmentConstant.Status.hidden)) {
				picturePathList.add(attachment.getPath().replace(
						java.io.File.separator, "/"));
			}
		}
		return StringUtils.join(picturePathList.toArray(), "ue_separate_ue");
	}

	@ResponseBody
	@RequestMapping(value = "/ueditor/upload.htm", method = RequestMethod.POST)
	public String photoUpload(@RequestParam("kindId") long kindId,
			@RequestParam(value = "kind") AttachmentConstant.Kind kind,
			@RequestParam("Filename") String Filename,
			@RequestParam("fileName") String fileName,
			@RequestParam("fileNameFormat") String fileNameFormat,
			@RequestParam("pictitle") String pictitle,
			@RequestParam("Upload") String Upload,
			@RequestParam("upfile") MultipartFile upfile,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		if (!UploadUtils.isFileType(fileName, UploadUtils.PHOTO_TYPE)) {
			json.put("state", "不允许的文件格式");
			return json.toString();
		}
		try {
			Attachment attachment = attachmentService.addUploadFile(upfile,
					fileName, kindId, kind, AttachmentConstant.Status.hidden);
			json.put("original", fileName);
			json.put("url", attachment.getPath());
			json.put("title", pictitle);
			json.put("state", "SUCCESS");
			return json.toString();
		} catch (IllegalStateException e) {
			json.put("state", e.getMessage());
		} catch (IOException e) {
			json.put("state", e.getMessage());
		} catch (UploadException e) {
			json.put("state", e.getMessage());
		}
		return json.toString();
	}
}
