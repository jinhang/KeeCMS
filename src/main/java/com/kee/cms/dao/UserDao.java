/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kee.cms.entity.User;

/**
 * 用户服务
 * 
 * @author Zhang jiale
 * 
 */

@Repository
public interface UserDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加用户
	 * 
	 * @param User
	 * @return Integer
	 */
	public int addUser(User user);

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除用户
	 * 
	 * @param User
	 * @return Integer
	 */
	public int deleteUserById(@Param("userId") long userId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 更新用户数据
	 * 
	 * @param userId
	 * @return Integer
	 */
	public int updateUser(User user);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 根据Id得到用户信息
	 * 
	 * @param userId
	 * @return User
	 */
	public User getUserById(@Param("userId") long userId);

	/**
	 * 得到用户数据的列表
	 * 
	 * @param long,long
	 * @return List<User>
	 */
	public List<User> getUserList(@Param("offset") long offset,
			@Param("rows") long rows);

	/**
	 * 得到用户数量
	 * 
	 * @param userId
	 * @return Integer
	 */
	public int getUserListCount();

}
