package com.tedu.sois.sys.controllerpage;

import com.tedu.sois.common.vo.Node;
import com.tedu.sois.common.pojo.sys.SysDept;
import com.tedu.sois.common.pojo.sys.SysMenu;
import com.tedu.sois.sys.service.SysDeptService;
import com.tedu.sois.sys.service.SysMenuService;
import com.tedu.sois.sys.service.SysRoleService;
import com.tedu.sois.sys.vo.SysRoleMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 返回对应编辑页面
 * @author LYS
 */
@Controller
@RequestMapping("")
public class SysEditPageController {

    /**
     * 菜单业务层接口
     */
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 角色业务层接口
     */
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 部门业务层接口
     */
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 菜单编辑 界面
     * @param model
     * @return
     */
    @PostMapping("menu/editPage")
    public String doGetMenuEditPage(SysMenu sysMenu, Model model) {
        model.addAttribute("menu",sysMenu);
        //这里后期可以查出parentId为0的结果,把对应的id值放入model中
        model.addAttribute("parentId",8);
        return "sys/menu_edit" ;
    }

    /**
     * 角色编辑页面
     * @param roleId
     * @param model
     * @return
     */
    @PostMapping("role/editPage")
    public String doGetRoleEditPage(Integer roleId, Model model) {
        SysRoleMenuVo sysRoleMenuVo = new SysRoleMenuVo();
        if (roleId != null)
            sysRoleMenuVo = sysRoleService.findRoleAndMenuInfoByRoleId(roleId);
        model.addAttribute("role",sysRoleMenuVo);
        List<Node> nodes = sysMenuService.findZtreeMenuNodes();
        model.addAttribute("nodes",nodes);
        return "sys/role_edit" ;
    }




    @PostMapping("dept/editPage")
    public String doGetDeptEditPage(Integer deptId, Model model) {
        SysDept dept = new SysDept();
        if (deptId != null)
            dept = sysDeptService.findSysDeptInfoByDeptId(deptId);
        model.addAttribute("dept",dept);
        List<Node> nodes = sysDeptService.findZTreeDeptNodes();
        model.addAttribute("nodes",nodes);
        return "sys/dept_edit" ;
    }

}
