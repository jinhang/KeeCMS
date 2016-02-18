package com.kee.cms.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kee.cms.service.ArticleService;
/**
 * 
 * @author keehang
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class FileServiceTest {

	@Autowired
	private ArticleService fileService;

	// @Test
	// public void testAddFile() {
	// assertEquals(2, fileService.addFile(2, "bb", "sef", "", "yhdwe", 0)
	// .getFolderId());
	// }

	// @Test
	// public void testDeleteFileById() {
	// assertEquals(true, fileService.deleteFileById(3));
	// }

	// @Test
	// public void testUpdateFileById() {
	// assertEquals(
	// 2,
	// fileService.updateFileById(1, 2, "ibk", "sdgr", "ibksdg",
	// "hjjuy", 2).getFolderId());
	// }

	// @Test
	// public void testGetFilePageByFoderId() {
	// assertEquals(2, fileService.getFilePageByFoderId(1, 1).getPageCount());
	// }
	//
	// @Test
	// public void testGetFileById() {
	// assertEquals(2, fileService.getFileById(2).getFileId());
	// }

}
