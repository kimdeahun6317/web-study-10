package web_study_10.dao;

import java.util.List;

import web_study_10.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	
	int getNextNo();
	
	int insertEmployee(Employee employee);
	
	Employee selectEmpByNo(Employee employee);
	
	int updataeEmployee(Employee empolyee);
	
	int removeEmployee(Employee empolyee);
}
