package com.tedu.sois.sys.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装用户权限菜单信息
 *  根据用户ID获取用户对应的菜单信息
 *  根据用户ID获取用户对应的角色ID
 *  根据用户ID获取用户对应的菜单ID
 * @author LYS
 */
public class SysUserMenuVo implements Serializable {

    /**菜单id*/
    private Integer menuId;

    /**菜单名称*/
    private String menuName;

    /**菜单url*/
    private String url;

    /**菜单图标*/
    private String icon;

    /**上级节点id*/
    private Integer parentId;

    /**下级菜单集合*/
    private List<SysUserMenuVo> children = new ArrayList<>();

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

    public List<SysUserMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysUserMenuVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SysUserMenuVo{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
