package gefpmvc.web.controller;


import gefpmvc.model.User;
import gefpmvc.model.dao.UserDao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutoCompleteController {

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "/autocomplete/user.html")
    public String users( @RequestParam String term, HttpServletResponse response )
        throws JSONException, IOException
    {
    	   /*if(term.indexOf("*")>0)
           	term = term.replaceAll("\\*", "%");*/
    	   
        JSONArray jsonArray = new JSONArray();
        List<User> users = userDao.searchUsersByPrefix( term, 10 );
        for( User user : users )
        {
        	if(user.isStudent()){
                Map<String, String> json = new HashMap<String, String>();
                if(user.getUserId()!=null)
                	json.put( "userid", user.getUserId().toString());
                if(user.getCin()!=null)
                	json.put( "cin", user.getCin());
                if(user.getFname()!=null)
                	json.put( "value", user.getFname() );
    
                json.put( "label", user.getCin()!=null?user.getCin():"" + " " + user.getFname()!=null?user.getFname():null +" " + user.getLname()!=null?user.getLname():"" +" " + user.getEmailid()!=null ?user.getEmailid():"");
                jsonArray.put( json );
        		
        	}
        }

        response.setContentType( "application/json" );
        jsonArray.write( response.getWriter() );
        return null;
    }

   
}
