package com.kee.cms.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kee.cms.exception.FolderNotFoundException;
import com.kee.cms.service.FolderService;
/**
 * 
 * @author keehang
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class FolderServiceTest {

	@Autowired
	private FolderService folderService;

	@Test
	public void testGetFolderById() {
		try {
			assertEquals(2, folderService.getFolderById(2).getFolderId());
		} catch (FolderNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	// public void testAddFolder() {
	// assertEquals(1, folderService.addFolder(2, "werg", 32, 1, 2, "segs",2,3)
	// .getFolderId());
	// }

	@Test
	public void testDeleteFolderById() {
		assertEquals(true, folderService.deleteFolderById(5));
	}

	// @Test
	// public void testUpdateFolderById() {
	// assertEquals(
	// 1,
	// folderService.updateFolderById(1, 1, "uyt", 3, 43, 12,
	// "dtghfg",2,1).getFatherId());
	// }

	// @Test
	// public void testGetFolderListByFatherId() {
	// assertEquals(3, folderService.getFolderListByFatherId(2).size());
	// }

	// @Test
	// public void testGetFolderVoListByFatherId() {
	// assertEquals(3, folderService.getFolderVoListByFatherId(1).size());
	// }

}
