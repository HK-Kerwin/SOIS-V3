package com.tedu.sois.sys.service;

import com.tedu.sois.common.vo.Node;
import com.tedu.sois.common.pojo.sys.SysMenu;
import com.tedu.sois.sys.vo.SysUserMenuVo;

import java.util.List;
import java.util.Map;

public interface SysMenuService {


    /**
     * 根据菜单id删除菜单信息
     * @param menuId
     * @return
     */
    int deleteSysMenuInfoById(Integer menuId);

    void saveSysMenuInfo(SysMenu entity);

    int modifySysMenuInfo(SysMenu entity);

    /**
     * 查询节点树
     * @return
     */
    List<Node> findZtreeMenuNodes();

    /**
     * 查询菜单及子菜单信息
     *
     * @return
     */
    List<Map<String, Object>> findMenuList();

    /**
     * 获取用户对应的菜单信息
     * @param userId
     * @return
     */
    List<SysUserMenuVo> findMenusByUserId(Long userId);
}
