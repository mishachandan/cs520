package gefpmvc.model.dao.jpa;

import gefpmvc.model.Department;
import gefpmvc.model.dao.DepartmentDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
//@Transactional(propagation = Propagation.MANDATORY)
public class DepartmentDaoImpl implements DepartmentDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Department getDeptByDepartmentName(String department) {
		
		Department departmentObj =  entityManager.createQuery( " from Department where  deptName = :departmentname  ", Department.class )
			.setParameter("departmentname", department).getSingleResult();
		
		return departmentObj;
	}

	@Override
	public Department getDeptByDeptNo(Long deptNo) {
		Department departmentObj =  entityManager.createQuery( " from Department where  deptNo = :departmentNumber  ", Department.class )
				.setParameter("departmentNumber", deptNo).getSingleResult();
		return departmentObj;
	}

	@Transactional
	@Override
	public void addDepartment(String name) {
		Department department = new Department(name, "office", "phone", null, null);
		entityManager.merge(department);
		
	}

	@Override
	public List<Department> getAllDepartment() {
		return  entityManager.createQuery( " from Department  order by deptNo", Department.class ).getResultList();
	}

	@Transactional
	@Override
	public Department saveDepartment(Department department) {
		return entityManager.merge(department);
	}

	

}
