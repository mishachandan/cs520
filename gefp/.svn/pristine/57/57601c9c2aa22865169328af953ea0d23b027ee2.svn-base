package gefpmvc.security;



import gefpmvc.model.User;
import gefpmvc.model.dao.DepartmentDao;
import gefpmvc.model.dao.UserDao;
import gefpmvc.util.Constants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired 
	UserDao userDao;
	
	@Autowired
	DepartmentDao departmentDao;
	
    @Override
    public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication )
        throws ServletException, IOException
    {
    	 logger.info( "onAuthenticationSuccess ::  signed in." );
      
    	 /*UserDetails userDetails  =  (UserDetails) authentication.getPrincipal();
    	 user.setUsername(userDetails.getUsername());
    	 user.setPassword(userDetails.getPassword());*/
    	 User user = (User)authentication.getPrincipal();
    	
    	 
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest( request, response );
        if( savedRequest != null )
        {
            super.onAuthenticationSuccess( request, response, authentication );
            return;
        }

             
        HttpSession session = request.getSession(true);
        
        String returnUrl = "";
        
/*        User userFetched = userDao.getUserByUsername(user.getUsername());
*/
        /*if(userFetched==null){
			returnUrl =  "/login.html?message=Invalid Username or password";
		}
		

		
		if(!userFetched.getPassword().equals(user.getPassword())){
			returnUrl =  "/login.html?message=Invalid Username or password";
		}
		*/
		session.setAttribute(Constants.AUTHENTICATEDUSER,  user);
		boolean isAdmin = user.isAdmin();
		boolean isAdvisor = user.isAdvisor();
		boolean isStudent = user.isStudent();
		
		if(isAdmin){
			returnUrl =  "/admin/welcome-file-admin.html";
			
		}else if(isAdvisor){
			
			//	returnUrl =  "/advisor/welcome.html";
				returnUrl = "/advisor/viewStudents.html";
				
		}else if(isStudent){
			
			//return "welcome-file-student";
			returnUrl =  "/student/viewPlan.html";
			
		}else{
			returnUrl =  "/login.html?message= You are not authorized for this operation.";
		}
				
		 getRedirectStrategy().sendRedirect( request, response, returnUrl );
    }

}
