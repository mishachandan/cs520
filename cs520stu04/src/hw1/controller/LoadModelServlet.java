package hw1.controller;

import hw1.config.Config;
import hw1.config.Constants;
import hw1.dao.Dao;
import hw1.model.Plan;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;



@WebServlet(description = "Loads servlet on server startup", urlPatterns = { "/LoadModelServlet" },loadOnStartup =1)
public class LoadModelServlet extends HttpServlet {
	private static  final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath("conf.properties");
		try {
			Config.readPath(fullPath);
			
			Dao  dao = new Dao();
			Plan gefpPlan = dao.getGefpPlan();
			if(gefpPlan.getCheckpointMap().size()>0){
				getServletContext().setAttribute(Constants.PLAN, gefpPlan);
			}
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

}
