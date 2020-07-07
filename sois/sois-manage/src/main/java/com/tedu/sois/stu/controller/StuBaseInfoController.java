package com.tedu.sois.stu.controller;

import com.tedu.sois.common.config.StatusCodeConfig;
import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import com.tedu.sois.stu.service.StuBaseInfoService;
import com.tedu.sois.common.pojo.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 学生信息基础操作控制层
 * @author LYS
 */
@RestController
@RequestMapping("stu/")
public class StuBaseInfoController implements StatusCodeConfig {

    @Autowired
    private StuBaseInfoService stuBaseInfoService;
    

    /**
     * 注册学员信息
     *
     * @param entity 学生信息实体对象
     * @return 注册成功结果信息
     */
    @PostMapping("doSaveStuBaseInfo")
    public JsonResult doSaveStuBaseInfo(StuBaseInfo entity) {
        int row = stuBaseInfoService.saveStuBaseInfo(entity,SAVE);
        if (row == 2)
            return new JsonResult("保存成功!并且创建了用户,账号为邮箱,密码为身份证后四位拼接手机号后四位,请及时登录修改");
        return new JsonResult("保存成功!请创建用户进行绑定");
    }


    @PostMapping("doModifyStuBaseInfo")
    public JsonResult doModifyStuBaseInfo(StuBaseInfo entity) {
        StuBaseInfo data = stuBaseInfoService.findStuInfoById(entity.getStuId());
        data.setDelFlag("1");//必须在控制层设置好状态值,不然其他地方调用修改就会废弃当前信息
        stuBaseInfoService.modifyStuBaseInfo(data);

        entity.setStuId(null);
        entity.setModifiedTime(new Date());
        entity.setModifiedUser(data.getStuName());
        //判断如果是学生修改信息,则设置stuName为null,防止学员修改真实名字
        SysUser sessionUser = ShiroUtils.getUser();
        if(sessionUser == null || (sessionUser != null && sessionUser.getDeptId() == STU_DEPT_CODE))
            entity.setStuName(data.getStuName());
        //entity.setDelFlag("0"); //业务层已经设置了
        int row = stuBaseInfoService.saveStuBaseInfo(entity,MODIFY);
        if (row == 2)
            return new JsonResult("学员信息,账户信息,更新成功");
        return new JsonResult("修改学员信息成功!");
    }

    


    /**
     * 身份证后四位和手机号后四位查询
     *
     * @param afterFour
     * @return
     */
    @GetMapping("doFindInfoToAfterFour")
    public JsonResult doFindInfoToAfterFour(@RequestParam("afterFour") String afterFour) {
        List<StuBaseInfo> list = stuBaseInfoService.findByAfterFour(afterFour);
        String msg = "查询无结果";
        if (list != null){
            int size = list.size();
            msg = "查询出" + size + "条数据";
        }
        return new JsonResult(msg,list);
    }



}
