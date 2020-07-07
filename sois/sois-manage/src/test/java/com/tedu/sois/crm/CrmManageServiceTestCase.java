package com.tedu.sois.crm;

import com.alibaba.excel.EasyExcel;
import com.tedu.sois.common.exception.ServiceException;
import com.tedu.sois.common.listener.StuCrmManageTemporaryListener;
import com.tedu.sois.teacher.dao.StuCrmManageDao;
import com.tedu.sois.common.pojo.teacher.ClassInfo;
import com.tedu.sois.common.pojo.teacher.StuCrmManage;
import com.tedu.sois.common.pojo.teacher.StuCrmManageTemporary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class CrmManageServiceTestCase {
    @Autowired
    private StuCrmManageDao dao;

    @Test
    public void uploadExcel(){
        StuCrmManageTemporaryListener listener = new StuCrmManageTemporaryListener();
        EasyExcel.read("E:\\ChromeDownload\\学员管理_20200425060432.xls", StuCrmManageTemporary.class, listener).sheet().doRead();


        List<StuCrmManage> list = listener.getList();
        System.out.println(list);
        int row1 = dao.insertStuCrmManage(list);
        if (row1 == 0)
            throw new ServiceException("基本信息无更新内容");

        Set<ClassInfo> seriesClassAll = listener.getSeriesClassAll();
        int row2 = dao.insertStuClassSet(seriesClassAll);
        if (row2 == 0)
            throw new ServiceException("班级号信息无更新内容");
    }
}
