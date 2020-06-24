package com.tedu.sois.controller;

import com.tedu.sois.common.pojo.teacher.ClassRoomCountVo;
import com.tedu.sois.teacher.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返回主页控制层
 * @author LYS
 */
@Controller
@RequestMapping("home/")
public class HomePageController {

    @Autowired
    private ClassRoomService classRoomService;

    /**
     * 返回主要页面
     * @return
     */
    @RequestMapping("console")
    public String doGetConsolePage() {
        return "home/console";
    }

    /**
     * 返回数据展示页面
     * @return
     */
    @RequestMapping("showDataPage")
    public String doGetHomePage(Model model) {
        ClassRoomCountVo crs = classRoomService.getClassRoomStatistical();
        model.addAttribute("crs",crs);
        return "home/show_data_page";
    }
}
