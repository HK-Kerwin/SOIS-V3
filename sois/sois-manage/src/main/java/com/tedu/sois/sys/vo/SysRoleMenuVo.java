package com.tedu.sois.sys.vo;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *     通过此对象封装基于角色id查询到的角色以
 *     及对应的菜单id
 */
public class SysRoleMenuVo implements Serializable{
	private static final long serialVersionUID = 3609240922718345518L;
	/**角色id*/
	private Integer roleId;

	/**角色名称*/
	private String roleName;

    /**角色备注*/
	private String note;

	/**角色权限字符串*/
	private String roleKey;

	/**显示顺序*/
	private Integer roleSort;

	/**数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）*/
	private String dataScope;

	/**角色状态（0正常 1停用）*/
	private String status;

	/**角色对应的菜单id*/
	private List<Integer> menuIds;

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public Integer getRoleSort() {
		return roleSort;
	}

	public void setRoleSort(Integer roleSort) {
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

	public List<Integer> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SysRoleMenuVo that = (SysRoleMenuVo) o;
		return Objects.equals(roleId, that.roleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId);
	}

	@Override
	public String toString() {
		return "SysRoleMenuVo{" +
				"roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", note='" + note + '\'' +
				", roleKey='" + roleKey + '\'' +
				", roleSort=" + roleSort +
				", dataScope='" + dataScope + '\'' +
				", status='" + status + '\'' +
				", menuIds=" + menuIds +
				'}';
	}
}
