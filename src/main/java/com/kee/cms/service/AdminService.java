/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kee.cms.constant.AdminConstant;
import com.kee.cms.constant.SystemConstant;
import com.kee.cms.dao.AdminDao;
import com.kee.cms.entity.Admin;
import com.kee.cms.entity.vo.AdminVo;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.exception.AuthException;
import com.kee.cms.util.AuthUtils;

/**
 * 管理员
 * 
 * @author Administrator
 * 
 */
@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 添加管理员
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * @return Admin
	 */
	public Admin addAdmin(String email, String name, String password,
			AdminConstant.Status status) throws AuthException {
		email = email.toLowerCase();
		Admin admin = new Admin();
		admin.setName(name);
		admin.setEmail(email);
		admin.setStatus(status);
		admin.setCreateTime(new Date());
		admin.setPassword(AuthUtils.getPassword(password, email));
		adminDao.addAdmin(admin);
		return admin;
	}

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除管理员
	 * 
	 * @param adminId
	 * @return Integer
	 */
	public int deleteAdmin(long adminId) {
		return adminDao.deleteAdmin(adminId);
	}

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改管理员资料
	 * 
	 * @param adminId
	 * @param name
	 * @param password
	 * @param status
	 * @return Admin
	 * @throws AuthException
	 */
	public Admin updateAdmin(long adminId, String name, String password,
			AdminConstant.Status status) throws AuthException {
		Admin admin = this.getAdminById(adminId);
		admin.setName(name);
		if (password.equals("")) {
			admin.setPassword(admin.getPassword());
		} else {
			admin.setPassword(AuthUtils.getPassword(password, admin.getEmail()));
		}
		admin.setStatus(status);
		adminDao.updateAdmin(admin);
		return admin;
	}

	public void updateAdminByAmdinId(long adminId, String name, String password)
			throws AuthException {
		Admin admin = this.getAdminById(adminId);
		String pwd = AuthUtils.getPassword(password, admin.getEmail());
		adminDao.updateAdminByadminId(adminId, name, pwd);
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 管理员登陆
	 * 
	 * @param email
	 * @param password
	 * @param request
	 */
	public void adminLogin(String email, String password,
			HttpServletRequest request) throws AuthException {
		AdminVo admin = adminDao.getAdminByEmail(email);
		if (admin == null) {
			throw new AuthException("邮箱或密码错误");
		}
		admin.setFaceUrl(AuthUtils.getFaceUrl(admin.getEmail()));
		String loginPassword = AuthUtils.getPassword(password, email);
		if (loginPassword.equals(admin.getPassword())) {
			HttpSession session = request.getSession();
			admin.setPassword("");
			session.setAttribute(SystemConstant.SESSION_ADMIN, admin);
		} else {
			throw new AuthException("邮箱或密码错误");
		}
	}

	/**
	 * 通过Id获得指定管理员资料
	 */
	public Admin getAdminById(long adminId) {
		return adminDao.getAdminById(adminId);
	}

	/**
	 * 获得所有管理员的分页数据
	 * 
	 * @param offset
	 * @param rows
	 * @return List<Admin>
	 */
	public List<Admin> getAllList(long offset, long rows) {
		return adminDao.getAllList(offset, rows);
	}

	/**
	 * 获得所有管理员的数量
	 * 
	 * @return Integer
	 */
	public int getAllListCount() {
		return adminDao.getAllListCount();
	}

	/**
	 * 获得所有管理员的分页
	 * 
	 * @param Integer
	 * @return PageVo<Admin>
	 */
	public PageVo<Admin> getAllListPage(int pageNum) {
		PageVo<Admin> pageVo = new PageVo<Admin>(pageNum);
		pageVo.setRows(5);
		List<Admin> list = this
				.getAllList(pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		pageVo.setCount(this.getAllListCount());
		return pageVo;
	}

	/**
	 * 通过email获得管理员资料
	 * 
	 * @param email
	 * @return Admin
	 */
	public Admin getAdminByEmail(String email) {
		return adminDao.getAdminByEmail(email);
	}
}
