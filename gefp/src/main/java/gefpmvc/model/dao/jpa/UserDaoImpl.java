package gefpmvc.model.dao.jpa;

import gefpmvc.model.User;
import gefpmvc.model.dao.UserDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public User getUserByUsername(String username) {
		List<User> usersSet= entityManager.createQuery( "from User where  username = :username  ", User.class )
								.setParameter("username", username).getResultList();
		
		return usersSet!=null && usersSet.size()>0? usersSet.iterator().next() : null;
	}

	@Override
	public User getUserByCIN(String cin) {
		List<User> usersSet= entityManager.createQuery( "from User where  cin = :cin  ", User.class )
				.setParameter("cin", cin).getResultList();

		return usersSet!=null && usersSet.size()>0? usersSet.iterator().next() : null;
	}

	/**
	 * A user can only edit their own account
	 */
	
	@Override
	@Transactional
	@PreAuthorize("principal.username = #user.username")
	public User saveUser(User user){
		return entityManager.merge(user);
	}

	 @Override
	    public List<User> searchUsersByPrefix( String term, int maxResults )
	    {
	        term = term.toLowerCase();
	        String query = "from User where cin like :term || '%' "
	            + "or lower(username) like :term || '%' "
	            + "or lower(fname) like :term || '%' "
	            + "or lower(lname) like :term || '%' "
	            + "or lower(fname || ' ' || lname) like :term || '%' "
	            + "or lower(emailid) like :term || '%' "
	            + "order by fname asc";

	        return entityManager.createQuery( query, User.class )
	            .setParameter( "term", term )
	            .setMaxResults( maxResults )
	            .getResultList();
	    }


	 
	 @Override
	    public List<User> searchUsers( String term )
	    {
	        term = term.toLowerCase();
	        String query = "from User where cin = :term or lower(username) = :term "
	            + "or lower(fname) = :term or lower(lname) = :term "
	            + "or lower(fname || ' ' || lname) = :term "
	            + "or lower(emailid) = :term order by fname asc";

	        return entityManager.createQuery( query, User.class )
	            .setParameter( "term", term )
	            .getResultList();
	    }

	@Override
	public User getUserById(Long userId) {
		return entityManager.find(User.class, userId);
	}
	
}
