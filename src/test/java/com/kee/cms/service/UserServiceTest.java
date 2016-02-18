package com.kee.cms.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kee.cms.service.UserService;
/**
 * 
 * @author keehang
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UserServiceTest {

	@Autowired
	private UserService userService;

	// @Test
	// public void testAddUser() {
	// assertEquals(1, userService.addUser(6, 32, 113, "sdg").getOpenId());
	// ;
	// }

	@Test
	public void testGetUserById() {
		assertEquals(2, userService.getUserById(2).getUserId());
	}

	@Test
	public void testGetUserPage() {
		assertEquals(4, userService.getUserPage(1).getList().size());
	}

	@Test
	public void testDeleteUserById() {
		assertEquals(1, userService.deleteUserById(2));
	}

}
