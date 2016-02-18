package com.kee.cms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kee.cms.entity.Config;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.service.ConfigService;
/**
 * 
 * @author keehang
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ConfigServiceTest {

	@Autowired
	private ConfigService configService;

	@Test
	public void testAddConfig() {
		assertEquals("h", configService.addConfig("h", "ew").getKey());
	}

	// @Test
	// public void testGetConfigPage() {
	// PageVo<Config> pageVo = configService.getConfigPage(1);
	// assertNotNull(pageVo);
	// assertEquals(6, pageVo.getList().size());
	// }

	@Test
	public void testDeleteConfigByKey() {
		assertEquals(1, configService.deleteConfigByKey("f"));
	}

	@Test
	public void testUpdagteConfigByKey() {
		assertEquals("a", configService.updagteConfigByKey("a", "ad").getKey());
	}

}
