package gefpmvc.modelTest.dao.jpa;



import gefpmvc.modelTest.UserT;
import gefpmvc.modelTest.dao.UserDaoT;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoTImpl implements UserDaoT {

    @PersistenceContext
    private EntityManager entityManager;

    @Override    
    public UserT getUserT( Integer id )
    {
        return entityManager.find( UserT.class, id );
    }

    @Override
    public List<UserT> getUsers()
    {
        return entityManager.createQuery( "from UserT order by id ", UserT.class )
            .getResultList();
    }

}