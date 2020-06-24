package com.tedu.sois.sys.dao;

import com.tedu.sois.common.vo.Node;
import com.tedu.sois.common.pojo.sys.SysMenu;
import com.tedu.sois.sys.vo.SysUserMenuVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 菜单持久层接口
 * @author LYS
 */

public interface SysMenuDao {


    /**
     * 将内存中的菜单对象持久化(存储到数据库)
     *
     * @param entity
     * @return
     */
    int insertSysMenuInfo(SysMenu entity);

    /**
     * 基于菜单id删除菜单
     *
     * @param menuId 菜单id
     * @return 删除的行数
     */
    @Delete("delete from sys_menu where menu_id=#{menuId}")
    int deleteSysMenuInfo(Integer menuId);

    /**
     * 更新数据库中的菜单记录
     *
     * @param entity
     * @return
     */
    int updateSysMenuInfo(SysMenu entity);

    /**
     * 基于菜单id获取对应的菜单权限标识
     * @param menuIds
     * @return
     */
    List<String> findPermissions(@Param("menuIds") Integer[] menuIds);

    /**
     * 获取所有菜单的菜单id,菜单名,上级菜单id
     * 然后一个记录存储到一个Node对象中
     * FAQ?一定要用node对象封装数据,不一定,
     * 也可以使用map.
     *
     * @return
     */
    @Select("select menu_id as id,menu_name as name,parent_id as parentId,type,menu_id as value from sys_menu order by sort")
    List<Node> selectZtreeMenuNodes();


    /**
     * 基于菜单id统计子菜单的数量
     *
     * @param menuId 菜单id
     * @return 子菜单的数量
     */
    @Select("select count(*) from sys_menu where parent_id=#{menuId}")
    int getChildCount(Integer menuId);


    /**
     * 查询所有的菜单以及菜单对应的上级菜单信息
     * 要求:一行记录映射为内存中一个map对象.
     * 说明:在很多优秀的产品级应用中其实不推荐
     * 直接基于map做映射存储,因为第一可读性相对较差,
     * 第二它的值的类型不可控.但是有时为提高开发的
     * 速度,map就可以直接作为映射存储对象.尤其是
     * 一些外包项目.
     *
     * @return
     */
    List<Map<String, Object>> selectMenuList();

    /**
     * 基于菜单id获取用户权限菜单信息
     * 需要包含多级菜单
     * @param menuIds
     * @return
     */
    List<SysUserMenuVo> selectMenusByIds(@Param("menuIds") List<Integer> menuIds);

    /**
     * 查询全部
     * @param menuIds
     * @return
     */
    List<SysUserMenuVo> selectMenusListByIds(@Param("menuIds") List<Integer> menuIds);
}








