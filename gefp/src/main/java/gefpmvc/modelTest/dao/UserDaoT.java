package gefpmvc.modelTest.dao;


import gefpmvc.modelTest.UserT;

import java.util.List;


public interface UserDaoT {

    UserT getUserT( Integer id );

    List<UserT> getUsers();

}