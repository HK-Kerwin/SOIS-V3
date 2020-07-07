package com.tedu.sois.stu.controllerpage;

import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import com.tedu.sois.common.pojo.sys.SysUser;
import com.tedu.sois.teacher.service.CrmManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 学生页面请求
 *
 * @author LYS
 */
@Controller
@RequestMapping("stu/")
public class StuBaseInfoPageController {


    @Autowired
    private CrmManageService crmManageService;

    /**
     * 页面有查询个人信息和注册功能
     *
     * @return 学员查询页面
     */
    @RequestMapping("getStuSingleListPage")
    public String getStuSingleListPage() {
        return "stu/stu_list";
    }

    /**
     * 通过请求携带班级信息到注册界面进行用户添加
     *
     * @param model 数据携带model
     * @return 注册页面HTML
     */
    @RequestMapping("getStuAddPage")
    public String getStuAddPage(Model model) {
        List<String> list = crmManageService.showClassNumAllList();
        model.addAttribute("classNum", list);
        return "stu/stu_add";
    }


    /**
     * 进入编辑界面
     *
     * @param model
     * @return HTML
     */
    @RequestMapping("getStuEditPage")
    public String getStuEditPage(Model model) {
        List<String> list = crmManageService.showClassNumAllList();
        model.addAttribute("classNum", list);
        /*model.addAttribute("data",data);*/
        SysUser user = ShiroUtils.getUser();
        if (user != null) {
            model.addAttribute("deptId", user.getDeptId());
        }
        return "stu/stu_edit";
    }

    /**
     * 双击获取学员信息
     *
     * @param data
     * @param model
     * @return HTML
     */
    @RequestMapping("getStuSingleInfoPage")
    public String stuSingleInfoPage(@RequestBody StuBaseInfo data, Model model) {
        //如果没有头像路径则设置默认图片
        if ("".equals(data.getAvatar()) || data.getAvatar() == null) {
            data.setAvatar("../dist/layuiadmin/img/defualt.png");
        }
        model.addAttribute("data", data);
        return "stu/stuSingleInfoPage";
    }
}
