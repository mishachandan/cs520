package gefpmvc.model.dao;

import gefpmvc.model.Department;
import gefpmvc.model.Plan;

import java.util.List;

public interface DepartmentDao {
	
	public Department getDeptByDepartmentName(String deptnametname);
	
	public Department getDeptByDeptNo(Long deptNo);
	
	public void addDepartment(String name);
	
	public List<Department> getAllDepartment();
	
	public Department saveDepartment(Department department);
}
