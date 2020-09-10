package web_study_10.service;

import java.util.List;

import web_study_10.dao.EmployeeDao;
import web_study_10.dao.impl.EmployeeDaoImpl;
import web_study_10.dto.Employee;

public class EmployeeService {
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();

	public List<Employee> selectEmployeeByAll() {
		return dao.selectEmployeeByAll();
	}

	public int getNextNo() {
		return dao.getNextNo();
	}

	public int addEmployee(Employee employee) {
		return dao.insertEmployee(employee);
	}
	
	public Employee getEmp(Employee employee) {
		return dao.selectEmpByNo(employee);
	}
	
	public int updateEmployee(Employee employee) {
		return dao.updataeEmployee(employee);
	}
	
	public int deleteEmployee(Employee employee) {
		return dao.removeEmployee(employee);
	}
}
