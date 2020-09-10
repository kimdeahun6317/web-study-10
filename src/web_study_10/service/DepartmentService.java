package web_study_10.service;

import java.util.List;

import web_study_10.dao.DepartmentDao;
import web_study_10.dao.impl.DepartmentDaoImpl;
import web_study_10.dto.Department;

public class DepartmentService {
	private DepartmentDao departmentDao = DepartmentDaoImpl.getInstance();
	
	public List<Department> showDepartment(){
		return departmentDao.selectDepartmentByAll();
	}
	
	public int getNextNo() {
		return departmentDao.getNextNo();
	}
	
	public int addDept(Department dept) {
		return departmentDao.insertDepartment(dept);
	}
	
	public Department getDept(Department dept) {
		return departmentDao.selectDeptByNo(dept);
	}
	
	public int removeDept(Department dept) {
		return departmentDao.deleteDept(dept);
	}

	public int updateDept(Department dept) {
		return departmentDao.updateDept(dept);
	}
}
