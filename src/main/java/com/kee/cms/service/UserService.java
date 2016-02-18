/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kee.cms.constant.UserConstant;
import com.kee.cms.dao.UserDao;
import com.kee.cms.entity.User;
import com.kee.cms.entity.vo.PageVo;

/**
 * 用户服务
 * 
 * @author Zhangjiale
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加用户
	 * 
	 * @param name
	 * @return User
	 */
	public User addUser(String name) {
		User user = new User();
		user.setOpenId(0);
		user.setType(UserConstant.Type.kee);
		user.setName(name);
		user.setCreateTime(new Date());
		userDao.addUser(user);
		return user;
	}

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除用户
	 * 
	 * @param userId
	 */
	public int deleteUserById(long userId) {
		return userDao.deleteUserById(userId);
	}

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 更新用户数据
	 * 
	 * @param userId
	 * @return user
	 */
	public User updateUser(long userId, long openId, UserConstant.Type type,
			String name) {
		User user = this.getUserById(userId);
		user.setType(type);
		user.setOpenId(openId);
		user.setName(name);
		userDao.updateUser(user);
		return user;
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 根据Id得到用户信息
	 * 
	 * @param userId
	 * @return User
	 */
	public User getUserById(long userId) {
		return userDao.getUserById(userId);
	}

	/**
	 * 得到用户数据分页
	 * 
	 * @param pageNum
	 * @return pageVo
	 */
	public PageVo<User> getUserPage(int pageNum) {
		PageVo<User> pageVo = new PageVo<User>(pageNum);
		pageVo.setRows(20);
		List<User> list = this
				.getUserList(pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		pageVo.setCount(this.getUserListCount());
		return pageVo;
	}

	/**
	 * 得到所有用户数据
	 * 
	 * @param offset
	 *            ,rows
	 * @return List<User>
	 */
	public List<User> getUserList(long offset, long rows) {
		return userDao.getUserList(offset, rows);
	}

	/**
	 * 所有用户数据的数量
	 * 
	 * @return Integer
	 */
	public int getUserListCount() {
		return userDao.getUserListCount();
	}
}
