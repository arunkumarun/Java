package com.bus.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bus.bean.ScheduleBean;
import com.bus.dao.ScheduleDAO;
import com.bus.util.InvalidInputException;

public class Administrator {
	public String addSchedule(ScheduleBean scheduleBean) throws SQLException, ClassNotFoundException, InvalidInputException{
		ScheduleDAO sdao = new ScheduleDAO();
		String res = new String();

		if(scheduleBean == null){
			return "INVALID INPUT";
		}
		if(scheduleBean.getSource().isEmpty() || scheduleBean.getDestination().isEmpty()){
			return "INVALID INPUT";
		}
		if(scheduleBean.getSource().length()< 2 || scheduleBean.getDestination().length()< 2){
			return "INVALID INPUT";
		}
		if(scheduleBean.getSource().equals(scheduleBean.getDestination())){
			return "Source and Destination Same";
		}
		String schid = sdao.generateID(scheduleBean.getSource(), scheduleBean.getDestination());
		scheduleBean.setScheduleId(schid);
		res = sdao.createSchedule(scheduleBean);
		return res;
	}
	
	public ArrayList<ScheduleBean> viewSchedule(String source,String destination) throws ClassNotFoundException, SQLException, InvalidInputException{
		
		if(source.length()< 2 || destination.length()< 2 || source.isEmpty() || destination.isEmpty()){
			throw new InvalidInputException();
		}
		
		ScheduleDAO sdao = new ScheduleDAO();
		return sdao.viewSchedule(source, destination);
	}
	
}
