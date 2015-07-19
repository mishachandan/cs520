package gefpmvc.web.controllerTest;



import gefpmvc.model.dao.DepartmentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value="DepartmentControllerTest")
public class DepartmentController {

	@Autowired
	DepartmentDao deptDao;
	
    @RequestMapping("/addDept.html")
    public String index()
    {
    	deptDao.addDepartment("Misha Chandan");
        return "showDepartments";
    }
    

}