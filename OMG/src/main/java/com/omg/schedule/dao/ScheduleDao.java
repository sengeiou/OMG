package com.omg.schedule.dao;

import java.util.List;

import com.omg.schedule.domain.ScheduleVO;

public interface ScheduleDao {

    public int doInsert(ScheduleVO schedule);
    
    public int doDelete(ScheduleVO schedule);
    
    public int doUpdate(ScheduleVO schedule);
    
    public ScheduleVO doSelectOne(ScheduleVO schedule);
    
    public List<ScheduleVO> doSelectList(ScheduleVO schedule);

}
