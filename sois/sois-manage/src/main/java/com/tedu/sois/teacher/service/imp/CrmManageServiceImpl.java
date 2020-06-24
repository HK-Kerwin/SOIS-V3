package com.tedu.sois.teacher.service.imp;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.tedu.sois.common.exception.ServiceException;
import com.tedu.sois.common.listener.StuCrmManageTemporaryListener;
import com.tedu.sois.common.pojo.teacher.StuCrmManageTemporary;
import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.teacher.dao.StuCrmManageDao;
import com.tedu.sois.common.pojo.teacher.ClassInfo;
import com.tedu.sois.common.pojo.teacher.StuCrmManage;
import com.tedu.sois.teacher.service.CrmManageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = false,
        isolation = Isolation.READ_COMMITTED, //隔离级别
        rollbackFor = Throwable.class,//什么异常回滚
        timeout = 30,//单位:秒
        propagation = Propagation.REQUIRED)//传播特性
public class CrmManageServiceImpl implements CrmManageService {

    @Value("${sois.profile}")
    private String uploadFolder;

    @Autowired
    private StuCrmManageDao stuCrmManageDao;

    @RequiresPermissions("crm:import:info")
    @Override
    public void addCrmExcel(MultipartFile file) {
        if (file == null || file.isEmpty())
            throw new ServiceException("请选择文件");

        File upload = new File(uploadFolder+"crm/excel/");
        if (!upload.exists()){
            upload.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        System.err.println("fileName = " + fileName);

        String suffix = "";
        int beginIndex = fileName.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = fileName.substring(beginIndex);
        }

        if (fileName.indexOf("(") != -1){
            fileName = fileName.split("\\(")[0];
        }
        if (fileName.indexOf("_") != -1){
            fileName = fileName.split("_")[0];
        }
        File uploadPath = new File(uploadFolder+"crm/excel/", fileName+suffix);
        System.err.println("uploadPath = " + uploadPath);
        try {
            file.transferTo(uploadPath);
        } catch (IOException e) {
            throw new ServiceException("传输出现异常,请联系管理员修复");
        }

        StuCrmManageTemporaryListener listener = new StuCrmManageTemporaryListener();
        try {
            EasyExcel.read(uploadPath, StuCrmManageTemporary.class, listener).sheet().doRead();
        } catch (ExcelAnalysisException e) {
            throw new ServiceException("文件出现异常,请查看文件是否存在加密或损坏");
        }

        List<StuCrmManage> list = listener.getList();
        System.err.println(list);

        stuCrmManageDao.insertStuCrmManage(list);

        Set<ClassInfo> seriesClassAll = listener.getSeriesClassAll();

        stuCrmManageDao.insertStuClassSet(seriesClassAll);
    }

    @Override
    public void SaveClassInfo(ClassInfo classInfo) {
        if (classInfo == null)
            throw new ServiceException("保存数据不允许全为空");
        if (classInfo.getClassName() == null || "".equals(classInfo.getClassName()))
            throw new ServiceException("班级名字不能为空");
        if (classInfo.getClassDirection() == null || "".equals(classInfo.getClassDirection()))
            throw new ServiceException("课程方向不能为空");
        int count = stuCrmManageDao.selectClassInfoByClassName(classInfo.getClassName());
        if (count > 0)
            throw new ServiceException("班级信息已经存在");
        classInfo.setCreatedUser(ShiroUtils.getUsername());
        classInfo.setCreatedTime(new Date());
        classInfo.setClassName(classInfo.getClassName().toUpperCase());
        classInfo.setClassDirection(classInfo.getClassDirection().toUpperCase());
        int row = stuCrmManageDao.insertClassInfo(classInfo);
        if (row == 0)
            throw new ServiceException("添加失败");
    }

    @Override
    public void removeClassInfoByClassName(String className) {
        if (className == null || "".equals(className))
            throw new ServiceException("班级名字不能为空");
        int count = stuCrmManageDao.selectClassInfoByClassName(className);
        if (count == 0)
            throw new ServiceException("班级信息不存在");
        int row = stuCrmManageDao.deleteClassInfoByClassName(className);
        if (row == 0)
            throw new ServiceException("删除失败");
    }

    @Override
    public void removeRepeatClassInfo() {
        int row = stuCrmManageDao.deleteRepeatClassInfo();
        if (row == 0)
            throw new ServiceException("没有重复数据删除");
    }


    @Transactional(readOnly = true)
    @Override
    public List<String> showClassNumAllList() {
        return stuCrmManageDao.selectClassNameAllList();
    }

    @Transactional(readOnly = true)
    @Override
    public String findClassDirectionByClassName(String className) {
        return stuCrmManageDao.selectClassDirectionByClassName(className);
    }
}
