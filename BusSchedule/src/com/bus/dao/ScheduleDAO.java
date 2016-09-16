package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bus.bean.ScheduleBean;
import com.bus.util.DBUtil;
import com.bus.util.InvalidInputException;

public class ScheduleDAO {
	public String createSchedule(ScheduleBean scheduleBean) throws SQLException, ClassNotFoundException{
		Connection con = DBUtil.getDBConnection();
		int r = 0;
		if(con != null){
			PreparedStatement ps = con.prepareStatement("INSERT into SCHEDULE_TBL values(?,?,?,?,?)");
			ps.setString(1, scheduleBean.getScheduleId());
			ps.setString(2, scheduleBean.getSource());
			ps.setString(3, scheduleBean.getDestination());
			ps.setString(4, scheduleBean.getStartTime());
			ps.setString(5, scheduleBean.getArrivalTime());
			r = ps.executeUpdate();
		}
		if(r!=0)
			return "SUCCESS";
		else
			return "FAIL";
	}
	
	public String generateID(String source, String destination) throws ClassNotFoundException, SQLException, InvalidInputException{
		long schid = 0;
		String res,one,two,three;
		Connection con = DBUtil.getDBConnection();
			
		if(source.length()< 2 && destination.length()< 2){
			throw new InvalidInputException();	
		}
		if(con!=null){	
			PreparedStatement ps = con.prepareStatement("SELECT SCHEDULE_SEQ.NEXTVAL from dual");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				schid = rs.getLong(1);
			}
		}
		
		one = source.substring(0,2).toUpperCase();
		two = destination.substring(0,2).toUpperCase();
		three = String.valueOf(schid);
		res = one+two+three;
		return res;
	}
	
	public ArrayList<ScheduleBean> viewSchedule(String source,String destination) throws ClassNotFoundException, SQLException, InvalidInputException{
		ArrayList<ScheduleBean> a = new ArrayList<ScheduleBean>();
		ScheduleBean sb = new ScheduleBean();
		Connection con = DBUtil.getDBConnection();
		if(source.length()< 2 && destination.length()< 2){
			throw new InvalidInputException();
		}
		if(con!=null){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from SCHEDULE_TBL where SOURCE='"+source+"'and DESTINATION='"+destination+"'");
			while(rs.next()){
				sb.setScheduleId(rs.getString(1));
				sb.setSource(rs.getString(2));
				sb.setDestination(rs.getString(3));
				sb.setStartTime(rs.getString(4));
				sb.setArrivalTime(rs.getString(5));
				a.add(sb);
			}
			return a;
		}
		
		return a;
	}

}
