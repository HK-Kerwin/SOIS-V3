package com.tedu.sois.stu;

import com.tedu.sois.stu.dao.StuBaseInfoDao;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import com.tedu.sois.stu.service.StuBaseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class StuBaseInfoDaoTestCase {



    @Autowired
    private StuBaseInfoDao dao;

    @Autowired
    private StuBaseInfoService service;

    @Test
    public void doSaveStuBaseInfo(){
        StuBaseInfo data = new StuBaseInfo();
        data.setStuName("account");
        data.setClassRoom("0");
        data.setBirth(new Date());
        data.setGender("男");
        data.setStuEmail("735622418@qq.com");
        data.setPhoneNumber("18585060564");
        data.setIdCard("520123199409083052");
        System.out.println(service.saveStuBaseInfo(data,0));
    }

    @Test
    public void selectStuBaseInfoList(){
        StuBaseInfo data = new StuBaseInfo();
        data.setDelFlag("0");
        List<StuBaseInfo> list = dao.selectStuBaseInfoList(data,0,10);
        for (StuBaseInfo t:  list) {
            System.out.println(t);
        }
    }

    @Test
    public void showStuInfoList() {

        System.out.println(service.showStuInfoList(null,0,10));
    }

    @Test
    public void test(){
        System.out.println("520123199409083052".substring(14,18));
        System.out.println("18585060564".substring(7,11));
        System.out.println(new StringBuilder("520123199409083052".substring(14,18))
                .append("18585060564".substring(7,11))
                .toString());
    }
}
