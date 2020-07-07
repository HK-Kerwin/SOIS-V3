package com.tedu.sois.teacher.controllerpage;

import com.tedu.sois.common.exception.ServiceException;
import com.tedu.sois.common.pojo.topic.TopicCategory;
import com.tedu.sois.stu.service.StuBaseInfoService;
import com.tedu.sois.teacher.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("teacher/")
public class TeacherPageController {

    @Autowired
    private StuBaseInfoService stuBaseInfoService;

    /**
     * 学员信息管理界面
     * @param indexSearch
     * @param model
     * @return
     */
    @RequestMapping("doGetStuInfoPageForTeacher")
    public String doGetStuInfoPageForTeacher(String indexSearch,Model model) {
        List<String> list = stuBaseInfoService.showStuClassNumList();
        model.addAttribute("stuClass",list);
        if(indexSearch != null && !"".equals(indexSearch)){

            String[] result = indexSearch.split(":");
            if (result.length != 2)
                throw new ServiceException("输入内容有误,请检查");

            Map<String,String> map = new HashMap<>();

            switch (result[0]){
                case "stuName":
                    map.put("stuName",result[1]);
                    break;
                case "phoneNumber":
                    map.put("phoneNumber",result[1]);
                    break;
                case "idCard":
                    map.put("idCard",result[1]);
                    break;
            }

            model.addAttribute("searchResult",map);
        }
        return "teacher/stuInfo_list";
    }

    /**
     * 教室管理界面
     * @return
     */
    @RequestMapping("doGetClassRoomInfoPageForTeacher")
    public String doGetClassRoomInfoPageForTeacher(){
        return "teacher/classroom_list";
    }

    /**
     * 资产管理界面
     * @return
     */
    @RequestMapping("doGetAssetsInfoPageForMaintenance")
    public String doGetAssetsInfoPageForMaintenance(){
        return "teacher/assets_info";
    }


    @Autowired
    private TopicService topicService;

    /**
     * 题库展示界面
     * @return
     */
    @RequestMapping("doGetTopicPage")
    public String doGetTopicPage(Model model){
        List<TopicCategory> topicCategory = topicService.findTopicTypeList();
        model.addAttribute("topicCategory",topicCategory);
        return "teacher/topic_list";
    }

}
