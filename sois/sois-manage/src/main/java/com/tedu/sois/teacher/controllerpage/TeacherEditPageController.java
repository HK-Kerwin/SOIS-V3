package com.tedu.sois.teacher.controllerpage;

import com.tedu.sois.common.pojo.teacher.ClassRoom;
import com.tedu.sois.teacher.service.CrmManageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class TeacherEditPageController {

    @Autowired
    private CrmManageService crmManageService;

    /**
     *
     * 教室编辑页面
     * @param classRoom
     * @param model
     * @return
     */
    @PostMapping("classRoom/doGetClassRoomEditPageForTeacher")
    public String doGetClassRoomEditPageForTeacher(ClassRoom classRoom, Model model){
        List<String> list = crmManageService.showClassNumAllList();
        model.addAttribute("classNum", list);
        if (classRoom == null){
            classRoom = new ClassRoom();
        }
        model.addAttribute("classRoom",classRoom);
        return "teacher/classroom_edit";
    }

    /**
     * 班级管理弹窗界面
     * @param model
     * @return
     */
    @RequiresPermissions("teacher:class:info")
    @RequestMapping("classInfo/doGetClassTablePageForTeacher")
    public String doGetClassRoomEditPageForTeacher(Model model){
        List<String> list = crmManageService.showClassNumAllList();
        model.addAttribute("classNum", list);
        return "teacher/class_info";
    }

}
