package com.tedu.sois.stu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.sois.common.pojo.stu.ScoreTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreTableDao extends BaseMapper<ScoreTable> {

    /**
     * 插入成绩信息
     * @param score
     * @return
     */
    int insertScore(ScoreTable score);

    /**
     * 分页查询成绩信息
     * @param stuName
     * @param stuClass
     * @param classRoomNum
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<ScoreTable> selectScorePage(@Param("direction") String[] direction,
                                     @Param("stuName") String stuName,
                                     @Param("stuClass") String stuClass,
                                     @Param("classRoomNum") String classRoomNum,
                                     @Param("startIndex") int startIndex,
                                     @Param("pageSize") int pageSize);

    /**
     * 根据条件查询是否有数据
     * @param stuName
     * @param stuClass
     * @param classRoomNum
     * @return
     */
    int getRowCount(@Param("direction") String[] direction,
                    @Param("stuName") String stuName,
                    @Param("stuClass") String stuClass,
                    @Param("classRoomNum") String classRoomNum);

    /**
     * 查询方向
     * @return
     */
    List<String> selectStuDirection();
}
