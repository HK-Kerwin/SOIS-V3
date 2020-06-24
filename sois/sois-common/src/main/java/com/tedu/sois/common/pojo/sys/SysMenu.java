package com.tedu.sois.common.pojo.sys;

import com.tedu.sois.common.pojo.BaseEntity;

import java.util.Date;
import java.util.Objects;

public class SysMenu extends BaseEntity {

	private Integer menuId;

	/**菜单名称*/
	private String menuName;

	/**菜单url: log/doFindPageObjects*/
	private String url;

	/**菜单类型( 1：菜单   2：按钮)*/
	private Integer type = 1;

	/**排序(序号)*/
	private Integer sort;

	/**备注*/
	private String note;

	/**菜单图标*/
	private String icon;

	/**上级菜单id*/
	private Integer parentId;

	/**菜单对应的权限标识(sys:log:delete)*/
	private String permission;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SysMenu sysMenu = (SysMenu) o;
		return Objects.equals(menuId, sysMenu.menuId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(menuId);
	}

	@Override
	public String toString() {
		return "SysMenu{" +
				"menuId=" + menuId +
				", menuName='" + menuName + '\'' +
				", url='" + url + '\'' +
				", type=" + type +
				", sort=" + sort +
				", note='" + note + '\'' +
				", icon='" + icon + '\'' +
				", parentId=" + parentId +
				", permission='" + permission + '\'' +
				", createdTime=" + createdTime +
				", modifiedTime=" + modifiedTime +
				", createdUser='" + createdUser + '\'' +
				", modifiedUser='" + modifiedUser + '\'' +
				", delFlag='" + delFlag + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
