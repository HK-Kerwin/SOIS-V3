package com.tedu.sois.common.pojo.teacher;

import java.io.Serializable;

/**
 * 统计教室信息对象
 * @author LYS
 */
public class ClassRoomCountVo implements Serializable {

    /**教室总数*/
    private Integer roomNumAll = 0;

    /**座位总数*/
    private Integer seatNumAll = 0;

    /**在读人数总数*/
    private Integer fullTimeStuNumAll = 0;

    /**其他人数总数*/
    private Integer otherStuNumAll = 0;

    /**座位使用率*/
    private String seatUsageRate = "0.00%";

    public Integer getRoomNumAll() {
        return roomNumAll;
    }

    public void setRoomNumAll(Integer roomNumAll) {
        this.roomNumAll = roomNumAll;
    }

    public Integer getSeatNumAll() {
        return seatNumAll;
    }

    public void setSeatNumAll(Integer seatNumAll) {
        this.seatNumAll = seatNumAll;
    }

    public Integer getFullTimeStuNumAll() {
        return fullTimeStuNumAll;
    }

    public void setFullTimeStuNumAll(Integer fullTimeStuNumAll) {
        this.fullTimeStuNumAll = fullTimeStuNumAll;
    }

    public Integer getOtherStuNumAll() {
        return otherStuNumAll;
    }

    public void setOtherStuNumAll(Integer otherStuNumAll) {
        this.otherStuNumAll = otherStuNumAll;
    }

    public String getSeatUsageRate() {
        return seatUsageRate;
    }

    public void setSeatUsageRate(String seatUsageRate) {
        this.seatUsageRate = seatUsageRate;
    }

    @Override
    public String toString() {
        return "ClassRoomCountVo{" +
                "roomNumAll=" + roomNumAll +
                ", seatNumAll=" + seatNumAll +
                ", fullTimeStuNumAll=" + fullTimeStuNumAll +
                ", otherStuNumAll=" + otherStuNumAll +
                ", seatUsageRate=" + seatUsageRate +
                '}';
    }
}
