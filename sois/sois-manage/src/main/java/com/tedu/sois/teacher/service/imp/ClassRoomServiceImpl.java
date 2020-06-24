package com.tedu.sois.teacher.service.imp;

import com.tedu.sois.common.exception.ServiceException;
import com.tedu.sois.common.pojo.teacher.ClassRoom;
import com.tedu.sois.common.util.ShiroUtils;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.teacher.dao.ClassRoomDao;
import com.tedu.sois.common.pojo.teacher.ClassRoomCountVo;
import com.tedu.sois.teacher.service.ClassRoomService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Transactional(isolation = Isolation.READ_COMMITTED, //隔离级别
        rollbackFor = Throwable.class,//什么异常回滚
        timeout = 30,//单位:秒
        propagation = Propagation.REQUIRED)//传播特性
@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomDao classRoomDao;

    @RequiresPermissions("teacher:classRoom:add")
    @Override
    public void saveClassRoomInfo(ClassRoom entity) {
        entity.setCreatedUser(ShiroUtils.getUsername());
        entity.setCreatedTime(new Date());
        int row = classRoomDao.insertClassRoomInfo(entity);
        if (row == 0)
            throw new ServiceException("保存教室信息失败");
    }

    @RequiresPermissions("teacher:classRoom:delete")
    @Override
    public void removeClassRoomInfoByIds(Integer[] classRoomIds) {
        if (classRoomIds == null)
            throw new ServiceException("必须选中后删除");
        if (classRoomIds.length == 0)
            throw new ServiceException("必须选中后删除");
        int row = classRoomDao.deleteClassRoomInfoByIds(classRoomIds);
        if (row == 0)
            throw new ServiceException("删除失败");
    }

    @RequiresPermissions("teacher:classRoom:update")
    @Override
    public void modifyClassRoomInfo(ClassRoom entity) {
        if (entity == null)
            throw new ServiceException("请填写信息修改");
        entity.setModifiedUser(ShiroUtils.getUsername());
        entity.setModifiedTime(new Date());
        int row = classRoomDao.updateClassRoomInfo(entity);
        if (row == 0)
            throw new ServiceException("更新失败");
    }

    @RequiresPermissions("teacher:classRoom:view")
    @Transactional(readOnly = true)
    @Override
    public JsonResult findPageClassRoomInfoList(Integer page, Integer limit) {
        if (page < 1)
            throw new ServiceException("页码值不正确");
        int count = classRoomDao.getClassRoomCount();
        if (count == 0)
            throw new ServiceException("查询无结果");
        int startIndex = (page -1) * limit;
        List<ClassRoom> records = classRoomDao.selectPageClassRoomInfoList(startIndex,limit);
        return new JsonResult(page,limit,count,records);
    }

    @Override
    public ClassRoomCountVo getClassRoomStatistical() {
        ClassRoomCountVo data = classRoomDao.selectClassRoomStatistical();
        Integer fullTimeStuNumAll = data.getFullTimeStuNumAll();
        Integer otherStuNumAll = data.getOtherStuNumAll();
        int stuNum = fullTimeStuNumAll + otherStuNumAll;
        Integer seatNumAll = data.getSeatNumAll();
        if (seatNumAll != null && seatNumAll.intValue() != 0){
            DecimalFormat df = new DecimalFormat("##.##%");
            data.setSeatUsageRate(df.format(stuNum*1.0/seatNumAll));
        }
        return data;
    }
}
