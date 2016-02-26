
package com.kee.cms.tag;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kee.cms.entity.vo.ArticleVo;
import com.kee.cms.exception.ArticleNotFoundException;
import com.kee.cms.service.ArticleService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author keehang 
 * file标签
 */
@Service
public class ArticleTag implements TemplateDirectiveModel {

	@Autowired
	private ArticleService articleService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		Integer articleId = Integer
				.parseInt(params.get("articleId").toString());
		// 获取指定的文件
		try {
			ArticleVo article = articleService.getArticleByArticleId(articleId);
			env.setVariable("tag_article", BEANS_WRAPPER.wrap(article));
		} catch (ArticleNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
}
