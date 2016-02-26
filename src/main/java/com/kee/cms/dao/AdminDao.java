package com.kee.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kee.cms.entity.Admin;
import com.kee.cms.entity.vo.AdminVo;

/**
 * 管理员
 * 
 * @author keehang
 */

@Repository
public interface AdminDao {
	/**
	 * 添加管理员
	 * 
	 * @param Admin
	 * @return Integer
	 * 
	 */
	public int addAdmin(Admin admin);
	/**
	 * 删除管理员
	 * 
	 * @param adminId
	 * @return Integer
	 * 
	 */
	public int deleteAdmin(@Param("adminId") long adminId);


	/**
	 * 修改管理员的信息
	 * 
	 * @param userId
	 * @param name
	 * @param password
	 */
	public void updateAdminByadminId(@Param("adminId") long adminId,
			@Param("name") String name, @Param("password") String password);

	/**
	 * 修改管理员资料
	 * 
	 * @param Adin
	 * @return Integer
	 * 
	 */
	public int updateAdmin(Admin admin);

	/**
	 * 获取所有管理员列表
	 * 
	 * @param offset
	 * @param rows
	 * @return List<Admin>
	 * 
	 */
	public List<Admin> getAllList(@Param("offset") long offset,
			@Param("rows") long rows);

	/**
	 * 获取所有管理员的数量
	 * 
	 * @return Integer
	 * 
	 */
	public int getAllListCount();

	/**
	 * 通过Id获得指定管理员资料
	 * 
	 * @param adminId
	 * @return Admin
	 */
	public Admin getAdminById(@Param("adminId") long adminId);

	/**
	 * 通过email获得指定的管理员
	 * 
	 * @param email
	 * @return Admin
	 * 
	 */
	public AdminVo getAdminByEmail(@Param("email") String email);

}
