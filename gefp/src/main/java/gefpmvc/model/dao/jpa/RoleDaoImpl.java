package gefpmvc.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import gefpmvc.model.Role;
import gefpmvc.model.User;
import gefpmvc.model.dao.RoleDao;

@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Role getRole(String roleName) {
		List<Role> roleSet= entityManager.createQuery( "from Role where  roleName = :roleName  ", Role.class )
				.setParameter("roleName", roleName).getResultList();

		return roleSet!=null && roleSet.size()>0? roleSet.iterator().next() : null;
	}

}
