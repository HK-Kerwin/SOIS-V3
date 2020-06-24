package com.tedu.sois.stu.service;

import com.tedu.sois.common.vo.PageObject;
import com.tedu.sois.common.pojo.stu.ScoreTable;

import java.util.List;

public interface ScoreTableService {

    /**
     * 添加成绩信息
     * @param score
     */
    void saveScoreInfo(ScoreTable score);

    void modifiedScore(List<ScoreTable> list);

    /**
     * 根据相应条件查询成绩
     * @param direction
     * @param stuName
     * @param stuClass
     * @param classRoom
     * @param pageCurrent
     * @return
     */
    PageObject<ScoreTable> findPageScore(String[] direction,
                                         String stuName,
                                         String stuClass,
                                         String classRoom,
                                         Integer pageCurrent);

    /**
     * 查询方向
     * @return
     */
    List<String> findStuDirection();



}
