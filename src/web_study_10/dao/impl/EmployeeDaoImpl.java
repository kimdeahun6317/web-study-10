package web_study_10.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import web_study_10.dao.EmployeeDao;
import web_study_10.ds.JndiDS;
import web_study_10.dto.Department;
import web_study_10.dto.Employee;
import web_study_10.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();

	private EmployeeDaoImpl() {
	}

	public static EmployeeDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, REGDATE, "
				+ "       EMAIL, TEL, PIC_URL, TITLE_NAME, DEPT_NAME, MANAGER_NAME " + "  FROM VW_EMPLOYEE_JOIN";
		try (Connection con = JndiDS.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<Employee>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		Employee manager = new Employee();
		manager.setEmpNo(rs.getInt("MANAGER"));
		manager.setEmpName(rs.getString("MANAGER_NAME"));

		Title title = new Title();
		title.setTitleNo(rs.getInt("TNO"));
		title.setTitleName(rs.getString("TITLE_NAME"));

		Department dept = new Department();
		dept.setDeptNo(rs.getInt("DNO"));
		dept.setDeptName(rs.getString("DEPT_NAME"));

		int empNo = rs.getInt("EMP_NO");
		String empName = rs.getString("EMP_NAME");
		int salary = rs.getInt("SALARY");
		String email = rs.getString("EMAIL");
		Date regDate = rs.getDate("REGDATE");
		String tel = rs.getString("TEL");
		String picUrl = rs.getString("PIC_URL");

		return new Employee(empNo, empName, title, manager, salary, dept, email, null, regDate, tel, picUrl);
	}

	@Override
	public int getNextNo() {
		String sql = "SELECT MAX(EMP_NO)+1 FROM EMPLOYEE";
		try (Connection con = JndiDS.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return 0;
	}

	@Override
	public int insertEmployee(Employee employee) {
		String sql = "INSERT INTO EMPLOYEE(EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, PASSWD,REGDATE,TEL,PIC_URL) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
		try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().getTitleNo());
			pstmt.setInt(4, employee.getManager().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDept().getDeptNo());
			pstmt.setString(7, employee.getEmail());
			pstmt.setString(8, employee.getPasswd());
			pstmt.setTimestamp(9, new Timestamp(employee.getRegDate().getTime()));
			pstmt.setString(10, employee.getTel());
			pstmt.setString(11, employee.getPicUrl());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

	@Override
	public Employee selectEmpByNo(Employee employee) {
		String sql = "SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, REGDATE, "
				+ "       EMAIL, TEL, PIC_URL, TITLE_NAME, DEPT_NAME, MANAGER_NAME " + "  FROM VW_EMPLOYEE_JOIN "
				+ " 	WHERE EMP_NO = ?";
		try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getEmpNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEmployee(rs);
				}
			}

		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public int updataeEmployee(Employee empolyee) {
		String sql = "UPDATE EMPLOYEE " + "	SET EMP_NAME = ? ," + "		TNO = ?, " + "		MANAGER = ?, "
				+ "		SALARY = ?, " + "		DNO = ?, " + "		REGDATE =  ?, " + "		EMAIL = ?, "
				+ "		TEL =  ?, " + "		PIC_URL = ? " + "	WHERE EMP_NO = ?";
		try (Connection con = JndiDS.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, empolyee.getEmpName());
			pstmt.setInt(2, empolyee.getTitle().getTitleNo());
			pstmt.setInt(3, empolyee.getManager().getEmpNo());
			pstmt.setInt(4, empolyee.getSalary());
			pstmt.setInt(5, empolyee.getDept().getDeptNo());
			pstmt.setTimestamp(6, new Timestamp(empolyee.getRegDate().getTime()));
			pstmt.setString(7, empolyee.getEmail());
			pstmt.setString(8, empolyee.getTel());
			pstmt.setString(9, empolyee.getPicUrl());
			pstmt.setInt(10, empolyee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

	@Override
	public int removeEmployee(Employee empolyee) {
		String sql = "DELETE FROM EMPLOYEE WHERE EMP_NO = ?";
		try(Connection con = JndiDS.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql)){
			pstmt.setInt(1, empolyee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}
}
