package com.kee.cms.entity;

import java.util.Date;

import com.kee.cms.constant.AdminConstant;
/**
 * 管理员实体类
 * @author keehang
 *
 */
public class Admin {

	/**
	 * 管理员Id
	 */
	private long adminId;

	/**
	 * 邮箱地址
	 */
	private String email;

	/**
	 * 管理员名称
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 状态
	 */
	private AdminConstant.Status status;

	/**
	 * 时间
	 */
	private Date createTime;

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AdminConstant.Status getStatus() {
		return status;
	}

	public void setStatus(AdminConstant.Status status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
