package com.tedu.sois.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 此Dao对象负责操作sys_role_menu中间表数据
 */

public interface SysRoleMenuDao {
    /**
     * 保存角色菜单关系数据
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    int insertSysRoleMenu(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);

    /**
     * 基于角色id删除菜单和角色的关系数据
     *
     * @param roleId
     * @return
     */
    @Delete("delete from sys_role_menu where role_id=#{roleId}")
    int deleteSysRoleMenuByRoleId(Integer roleId);

    /**
     * 基于菜单id删除菜单和角色的关系数据
     *
     * @param menuId
     * @return
     */
    @Delete("delete from sys_role_menu where menu_id=#{menuId}")
    int deleteSysRoleMenuByMenuId(Integer menuId);

    /**
     * 基于多个角色id获取多个菜单id
     *
     * @param roleIds
     * @return
     */
    List<Integer> selectMenuIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);

}






