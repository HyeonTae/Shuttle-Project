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

	// 버스시간표 수정
	public int updateBus(Bus b) {
		con = DBUtil.getConnection();
		String sql = "update `shuttle`.`Bus` set `dep`=?,`dest`=?,`hour`=?,`min`=? where `Bus`.`id`=? limit 1";
		pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, b.getDep());
			pst.setString(2, b.getDest());
			pst.setInt(3, b.getHour());
			pst.setInt(4, b.getMin());
			pst.setString(5, b.getId());

			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(pst);
		}
		return 0;
	}

	// 버스시간표 조회
	public ArrayList<Bus> getAllBus() {
		busList = new ArrayList<>();
		con = DBUtil.getConnection();
		String sql = "select * from bus order by id";
		pst = null;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				busList.add(new Bus(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
			return busList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// 버스시간표 조회 (id)
	public Bus getSearchBusForId(String id) {
		con = DBUtil.getConnection();
		String sql = "select * from bus where id=?";
		pst = null;
		rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			rs.next();
			
			return new Bus(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(pst);
			DBUtil.close(rs);
		}
		return null;
	}

	// 버스시간표 조회 (학교->목적지)
	public ArrayList<Bus> getSearchBusToDest(String dest) {
		busList = new ArrayList<>();
		con = DBUtil.getConnection();
		String sql = "select dep, dest, hour, min from bus where dest=? order by hour, min";
		pst = null;
		rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, dest);
			rs = pst.executeQuery();
			while (rs.next()) {
				busList.add(new Bus(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
			return busList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(pst);
			DBUtil.close(rs);
		}
		return null;
	}

	// 버스시간표 조회 (목적지->학교)
	public ArrayList<Bus> getSearchBusToSMU(String dep, String dest) {
		busList = new ArrayList<>();
		con = DBUtil.getConnection();
		String sql = "select dep, dest, hour, min from bus where dep=? and dest=? order by hour, min";
		pst = null;
		rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, dep);
			pst.setString(2, dest);
			rs = pst.executeQuery();
			while (rs.next()) {
				busList.add(new Bus(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
			return busList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(pst);
			DBUtil.close(rs);
		}
		return null;
	}

	// 버스정보삭제
	public int deleteClass(String id) {
		con = DBUtil.getConnection();
		String sql = "delete from bus where id=?";
		pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id);

			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(pst);
		}
		return 0;
	}
}
