package smu.shuttle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import smu.shuttle.model.Bus;
import smu.shuttle.util.DBUtil;

public class BusDao {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	ArrayList<Bus> busList;
	
	// 버스 추가
	public int insertBus(Bus b) {
		con = DBUtil.getConnection();
		String sql = "insert into bus values (?,?,?,?,?)";
		pst = null;
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, b.getId());
			pst.setString(2, b.getDep());
			pst.setString(3, b.getDest());
			pst.setInt(4, b.getHour());
			pst.setInt(5, b.getMin());
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(pst);
		}
		return 0;
	}	
}
