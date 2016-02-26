
package com.kee.cms.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kee.cms.constant.AttachmentConstant;
import com.kee.cms.entity.vo.AttachmentVo;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.service.AttachmentService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * folder标签
 * 
 * @author keehang
 * 
 */
@Service
public class AttachmentPageTag implements TemplateDirectiveModel {

	@Autowired
	private AttachmentService attachmentService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		// 获取页面的参数
		Integer kindId = Integer.parseInt(params.get("kindId").toString());
		AttachmentConstant.Kind kind = AttachmentConstant.Kind.valueOf(params
				.get("kind").toString());
		Integer pageNum = Integer.parseInt(params.get("p").toString());
		Integer rows = Integer.parseInt(params.get("rows").toString());
		// 获得目录列表
		PageVo<AttachmentVo> pageVo = attachmentService
				.getAttachmentPageByKindId(kindId, kind, rows, pageNum);
		env.setVariable("tag_attachment_page", DEFAULT_WRAPPER.wrap(pageVo));
		body.render(env.getOut());
	}

}
