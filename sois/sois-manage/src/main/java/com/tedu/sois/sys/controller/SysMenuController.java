package com.tedu.sois.sys.controller;

import com.tedu.sois.common.pojo.sys.SysMenu;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.vo.Node;
import com.tedu.sois.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("menu/")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("doSaveSysMenuInfo")
    public JsonResult doSaveSysMenuInfo(SysMenu entity) {
        sysMenuService.saveSysMenuInfo(entity);
        Integer type = entity.getType();
        if(type == 2 || type.equals(2))
            new JsonResult("按钮保存成功!");
        return new JsonResult("菜单保存成功!");
    }

    /**
     * 根据菜单id删除菜单信息
     * @param menuId
     * @return
     */
    @RequestMapping("doDeleteSysMenuInfoById")
    public JsonResult doDeleteSysMenuInfoById(Integer menuId) {
        sysMenuService.deleteSysMenuInfoById(menuId);
        return new JsonResult("删除成功");
    }


    @PostMapping("doModifySysMenuInfo")
    public JsonResult doModifySysMenuInfo(SysMenu entity) {
        sysMenuService.modifySysMenuInfo(entity);
        Integer type = entity.getType();
        if(type == 2 || type.equals(2))
            new JsonResult("按钮修改成功!");
        return new JsonResult("菜单修改成功");
    }

    /**
     * 查询所有菜单信息
     * @return
     */
    @RequestMapping("doFindMenuList")
    public JsonResult doFindMenuList() {
        return new JsonResult(sysMenuService.findMenuList());
    }

    /**
     * 查询节点树
     * @return
     */
    @GetMapping("doFindZtreeMenuNodes")
    public List<Node> doFindZtreeMenuNodes() {
        return sysMenuService.findZtreeMenuNodes();
    }

}






