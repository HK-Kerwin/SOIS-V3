package com.tedu.sois.sys.controller;

import com.tedu.sois.common.pojo.sys.SysRole;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制层
 * @author LYS
 */
@RestController
@RequestMapping("role/")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 新增角色信息,并且绑定角色对应菜单权限
     * @param entity 角色信息
     * @param menuIds 菜单权限值
     * @return
     */
    @PostMapping("doSaveRoleInfo")
    public JsonResult doSaveRoleInfo(SysRole entity, Integer[] menuIds) {
        sysRoleService.saveRoleInfo(entity, menuIds);
        return new JsonResult("保存成功");
    }

    /**
     * 基于角色id删除角色以及对应的关系数据
     * @param roleId
     * @return
     */
    @RequestMapping("doRemoveRoleInfo")
    public JsonResult doRemoveRoleInfo(Integer roleId) {
        sysRoleService.removeRoleInfo(roleId);
        return new JsonResult("删除成功");
    }

    /**
     * 更新角色以及角色对应的关系数据
     * @param entity
     * @param menuIds
     * @return
     */
    @PostMapping("doModifyRoleInfo")
    public JsonResult doModifyRoleInfo(SysRole entity, Integer[] menuIds) {
        sysRoleService.modifyRoleInfo(entity, menuIds);
        return new JsonResult("修改成功");
    }


    @GetMapping("doFindRoles")
    public JsonResult doFindRoles() {
        return new JsonResult(sysRoleService.findObjects());
    }

    /**
     * 根据角色ID查询角色以及角色对应的菜单id
     * @param roleId
     * @return
     */
    @GetMapping("getRoleAndMenuInfoByRoleId")
    public JsonResult getRoleAndMenuInfoByRoleId(Integer roleId) {
        return new JsonResult(sysRoleService.findRoleAndMenuInfoByRoleId(roleId));
    }

    /**
     * 根据角色名字查询,如果没有,则返回全部
     * @param roleName 角色名
     * @param page 页码
     * @param limit 数量
     * @return
     */
    @RequestMapping("doFindPageRoleInfoByRoleName")
    public JsonResult doFindPageRoleInfoByRoleName(String roleName, Integer page, Integer limit) {
        return sysRoleService.findPageRoleInfoByRoleName(roleName,page,limit);
    }
}
