package smu.shuttle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import smu.shuttle.model.Class;
import smu.shuttle.util.DBUtil;

public class ClassDao {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	ArrayList<Class> classList;

	// 클래스 추가
	public int insertClass(Class c) {
		con = DBUtil.getConnection();
		String sql = "insert into class values (?,?,?,?,?)";
		pst = null;

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getId());
			pst.setString(2, c.getPass());
			pst.setString(3, c.getName());
			pst.setString(4, c.getDept());
			pst.setString(5, c.getArea());

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

	// 클래스 전체조회
	public ArrayList<Class> getAllClass() {
		classList = new ArrayList<>();
		con = DBUtil.getConnection();
		String sql = "select * from class";
		pst = null;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				classList.add(
						new Class(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return classList;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	// 해당 클래스 조회
	public Class selectUserForId(String id) {
		con = DBUtil.getConnection();
		String sql = "select * from class where id=?";
		pst = null;
		rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			rs.next();

			return new Class(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(pst);
			DBUtil.close(rs);
		}
		return null;
	}
	// 클래스 로그인
	public boolean classLogin(String id, String pass) {
		con = DBUtil.getConnection();
		pst = null;
		rs = null;
		String sql = "select * from class where id=? and pass=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pst);
			DBUtil.close(con);
		}
		return false;
	}
	// 정보수정
	public int updateClass(Class c) {
		con = DBUtil.getConnection();
		String sql = "update `seo`.`class` set `pass`=?,`name`=?,`dept`=?,`area`=? `where` `shuttle`.`id`=? limit 1";
		pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getPass());
			pst.setString(2, c.getName());
			pst.setString(3, c.getDept());
			pst.setString(4, c.getArea());
			pst.setString(5, c.getId());

			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(pst);
		}
		return 0;
	}
	// 정보삭제
	public int deleteClass(String id) {
		con = DBUtil.getConnection();
		String sql = "delete from class where id=?";
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
