package com.tedu.sois.stu.service.impl;

import com.tedu.sois.common.exception.ServiceException;
import com.tedu.sois.common.vo.PageObject;
import com.tedu.sois.stu.dao.ScoreTableDao;
import com.tedu.sois.stu.dao.StuBaseInfoDao;
import com.tedu.sois.common.pojo.stu.ScoreTable;
import com.tedu.sois.common.pojo.stu.StuBaseInfo;
import com.tedu.sois.stu.service.ScoreTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreTableServiceImpl implements ScoreTableService {

    @Autowired
    private StuBaseInfoDao stuBaseInfoDao;

    @Autowired
    private ScoreTableDao scoreTableDao;


    @Override
    public void saveScoreInfo(ScoreTable score) {

        if (score.getStuId() == null || "".equals(score.getStuId()))
            throw new ServiceException("ID不能为空");
        if (score.getStuName() == null || "".equals(score.getStuName()))
            throw new ServiceException("姓名不能为空");

        StuBaseInfo data = stuBaseInfoDao.selectById(score.getStuId());
        if (data == null)
            throw new ServiceException("没有匹配的ID,请到'增查信息'界面查询,或联系管理员");

        List<StuBaseInfo> stuBaseInfo = stuBaseInfoDao.selectStuBaseInfoByStuName(score.getStuName());
        if (stuBaseInfo.size() == 0)
            throw new ServiceException("姓名不匹配,请到'增查信息'界面查询,或联系管理员");

        if (!data.getStuName().equals(score.getStuName()))
            throw new ServiceException("姓名和ID不匹配,请到'增查信息'界面查询,或联系管理员");

        int row = scoreTableDao.insertScore(score);
        if (row == 0)
            throw new ServiceException("不能重复添加数据,如需修改,请联系管理员");

    }

    @Transactional
    @Override
    public void modifiedScore(List<ScoreTable> list) {
        for (ScoreTable score : list) {
            int row = scoreTableDao.updateById(score);
            if (row == 0)
                throw new ServiceException("修改失败");
        }
    }

    @Override
    public PageObject<ScoreTable> findPageScore(String[] direction, String stuName, String stuClass, String classRoomNum, Integer pageCurrent) {

        //1.参数校验
        if (pageCurrent == null || pageCurrent < 1)
            throw new IllegalArgumentException("页码值不正确");

        if (direction.length==0)
            direction = null;

        //2.统计总记录数并校验
        int rowCount =scoreTableDao.getRowCount(direction,stuName,stuClass,classRoomNum);
        if (rowCount == 0)
            throw new ServiceException("没有成绩信息,请先录入");
        //3.查询当前页记录
        int pageSize = 10;
        int startIndex = (pageCurrent - 1) * pageSize;
        List<ScoreTable>  records = scoreTableDao.selectScorePage(direction,stuName,stuClass, classRoomNum,startIndex, pageSize);
        //4.封装查询结果
        return new PageObject<>(pageCurrent, pageSize, rowCount, records);
    }

    @Override
    public List<String> findStuDirection() {
        return scoreTableDao.selectStuDirection();
    }
}
