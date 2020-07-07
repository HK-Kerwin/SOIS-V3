package com.tedu.sois.sys.dao;

import com.tedu.sois.common.vo.CheckBox;
import com.tedu.sois.common.pojo.sys.SysRole;
import com.tedu.sois.sys.vo.SysRoleMenuVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SysRoleDao {
    /**
     * 查询所有角色
     *
     * @return
     */
    @Select("select role_id,role_name from sys_role")
    List<CheckBox> findObjects();

    /**
     * 基于角色id查询角色以及角色对象的菜单id
     *
     * @param roleId
     * @return
     */
    SysRoleMenuVo selectRoleAndMenuInfoById(Integer roleId);

    /**
     * 更新角色自身信息
     *
     * @param entity
     * @return
     */
    int updateRoleInfo(SysRole entity);

    /**
     * 保存角色自身信息
     *
     * @param entity
     * @return
     */
    int insertRoleInfo(SysRole entity);

    /**
     * 基于角色删除角色自身信息
     *
     * @param roleId
     * @return
     */
    @Delete("delete from sys_role where role_id=#{roleId}")
    int deleteSysRoleInfo(Integer roleId);

    /**
     * 根据角色名字模糊查询统计角色名记录总数
     *
     * @param roleName 角色名
     * @return 统计数量
     */
    int getRoleInfoLikeRowCount(@Param("roleName") String roleName);

	/**
	 * 根据角色名字精确查询统计角色名记录总数
	 * @param roleName
	 * @return
	 */
	@Select("select count(role_id) from sys_role where role_name=#{roleName}")
    int getRoleInfoRowCount(@Param("roleName") String roleName);

    /**
     * 查询当前页角色记录
     *
     * @param roleName   角色名
     * @param startIndex 当前页起始位置
     * @param pageSize   当前页面大小
     * @return 当前页角色记录
     */
    List<SysRole> selectRoleInfoPage(
            @Param("roleName") String roleName,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);

    /**
     * 查找所有角色信息
     * @return
     */
    @Select("select * from sys_role")
    List<SysRole> selectRoleInfoList();

    /**
     * 根据多个角色ID查询角色信息
     * @param roleIds
     * @return
     */
    List<SysRole> selectRoleInfoById(@Param("roleIds") List<Integer> roleIds);
}





