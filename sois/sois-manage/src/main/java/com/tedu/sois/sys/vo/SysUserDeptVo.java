package com.tedu.sois.sys.vo;

import com.tedu.sois.common.pojo.BaseEntity;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import com.tedu.sois.common.pojo.sys.SysDept;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SysUserDeptVo extends BaseEntity{

	/**用户编号*/
	private Integer userId;

	/**用户名字*/
	private String userName;

	/**登录账号*/
	private  String login_name;

	/**手机号码*/
	private String phoneNumber;

	/**性别*/
	private String sex;

	/**用户头像地址*/
	private String avatar;

	/**用户密码*/
	private String password;//md5

	/**用户密码盐值*/
	private String salt;

	/**用户邮箱*/
	private String email;

	/**帐号状态（0正常 1停用）*/
	private Integer status = 0;

	/**登录ip地址*/
	private String loginIp;

	/**最后登录时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginDate;

	/** 学生对象*/
	private StuBaseInfo stuBaseInfo;

	/**部门PO对象*/
	private SysDept sysDept; //private Integer deptId;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public StuBaseInfo getStuBaseInfo() {
		return stuBaseInfo;
	}

	public void setStuBaseInfo(StuBaseInfo stuBaseInfo) {
		this.stuBaseInfo = stuBaseInfo;
	}

	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	@Override
	public String toString() {
		return "SysUserDeptVo{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", login_name='" + login_name + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", sex='" + sex + '\'' +
				", avatar='" + avatar + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", email='" + email + '\'' +
				", status=" + status +
				", loginIp='" + loginIp + '\'' +
				", loginDate=" + loginDate +
				", stuBaseInfo=" + stuBaseInfo +
				", sysDept=" + sysDept +
				", createdTime=" + createdTime +
				", modifiedTime=" + modifiedTime +
				", createdUser='" + createdUser + '\'' +
				", modifiedUser='" + modifiedUser + '\'' +
				", delFlag='" + delFlag + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
