package com.tedu.sois.common.pojo.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tedu.sois.common.pojo.BaseEntity;

import java.util.Objects;

/**
  *  角色模块po对象(与sys_role表中的字段有一一映射关系)
 */
@TableName("sys_role")
public class SysRole extends BaseEntity {
	/**编号*/
	private Integer roleId;

	/**角色名称*/
	private String roleName;

	/**角色权限字符串*/
	private String roleKey;

	/**显示顺序*/
	private String roleSort;

	/**数据范围 1:全部 2:自定义 3:本部门数据权限 4:本部门及以下数据权限*/
	private String dataScope;

	/**角色状态（0正常 1停用）*/
	private String status;

	/**备注*/
	private String note;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getRoleSort() {
		return roleSort;
	}

	public void setRoleSort(String roleSort) {
		this.roleSort = roleSort;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SysRole sysRole = (SysRole) o;
		return Objects.equals(roleId, sysRole.roleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId);
	}

	@Override
	public String toString() {
		return "SysRole{" +
				"roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", roleKey='" + roleKey + '\'' +
				", roleSort='" + roleSort + '\'' +
				", dataScope='" + dataScope + '\'' +
				", status='" + status + '\'' +
				", note='" + note + '\'' +
				", createdTime=" + createdTime +
				", modifiedTime=" + modifiedTime +
				", createdUser='" + createdUser + '\'' +
				", modifiedUser='" + modifiedUser + '\'' +
				", delFlag='" + delFlag + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
