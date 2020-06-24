package com.tedu.sois.sys.service;

import com.tedu.sois.common.vo.CheckBox;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.sys.SysRole;
import com.tedu.sois.sys.vo.SysRoleMenuVo;

import java.util.List;

public interface SysRoleService {


	/**
	 *  保存角色以及角色和菜单的关系数据
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	void saveRoleInfo(SysRole entity, Integer[] menuIds);
	/**
	 * 基于角色id删除角色以及对应的关系数据
	 * @param roleId
	 * @return
	 */
	void removeRoleInfo(Integer roleId);
	/**
	   * 更新角色以及角色对应的关系数据
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int modifyRoleInfo(SysRole entity, Integer[] menuIds);

	/**
	 * 根据用户id查询用户对应角色
	 * @param stuId 用户编号
	 * @return 角色
	 */
	List<SysRole> findRoleByUserId(Long stuId);

	List<CheckBox> findObjects();

	/**
	 * 基于角色id查询角色以及角色对应的菜单id
	 * @param roleId
	 * @return
	 */
	SysRoleMenuVo findRoleAndMenuInfoByRoleId(Integer roleId);


	/**
	 * 基于角色名字进行分页查询的方法
	 * @param roleName
	 * @param page
	 * @param limit
	 * @return
	 */
	JsonResult findPageRoleInfoByRoleName(String roleName, Integer page, Integer limit);

	/**
	 * 查找所有角色信息
	 * @return
	 */
	List<SysRole> findRoleInfoList();

}
