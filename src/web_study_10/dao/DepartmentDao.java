package web_study_10.dao;

import java.util.List;

import web_study_10.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();

	int getNextNo();

	int insertDepartment(Department dept);

	Department selectDeptByNo(Department dept);

	int deleteDept(Department dept);

	int updateDept(Department dept);

}
