package gefpmvc.web.controllerTest;





import gefpmvc.modelTest.dao.UserDaoT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller(value="userContollerTest")
public class UserController {

    @Autowired
    private UserDaoT userDao;

    @RequestMapping("/users.html")
    public String users( ModelMap models )
    {
        models.put( "users", userDao.getUsers() );
        return "users";
    }
    
    

}
