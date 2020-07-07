package com.tedu.sois.sys;

import com.tedu.sois.sys.dao.SysMenuDao;
import com.tedu.sois.sys.dao.SysRoleMenuDao;
import com.tedu.sois.sys.dao.SysUserRoleDao;
import com.tedu.sois.sys.service.SysMenuService;
import com.tedu.sois.sys.service.SysRoleService;
import com.tedu.sois.sys.vo.SysRoleMenuVo;
import com.tedu.sois.sys.vo.SysUserMenuVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class SysMenuTestCase {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 角色业务层接口
     */
    @Autowired
    private SysRoleService sysRoleService;


    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;


    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Test
    public void find(){
        List<Map<String, Object>> menuList = sysMenuService.findMenuList();
        for (Map map : menuList) {
            System.out.println(map);
        }
    }
    @Test
    public void findById(){
        Integer roleIds = 47;
        SysRoleMenuVo data = sysRoleService.findRoleAndMenuInfoByRoleId(roleIds);
        System.err.println(data);
    }

    @Test
    public void findTree(){
        //获取角色id
        List<Integer> roleIds = sysUserRoleDao.selectRoleIdsByUserId(2L);
        //获取菜单id
        List<Integer> menuIds = sysRoleMenuDao.selectMenuIdsByRoleIds(roleIds);
        //获取菜单
        //List<SysUserMenuVo> sysUserMenuVos = sysMenuDao.selectMenusByIds(menuIds);//mybatis二级
        List<SysUserMenuVo> sysUserMenuVos = sysMenuDao.selectMenusListByIds(menuIds);//获取全部使用递归
        System.err.println(sysUserMenuVos.size());
        for (SysUserMenuVo d: sysUserMenuVos) {
            System.err.println( d);
        }
    }





}
