package hw1.controller;

import hw1.config.Constants;
import hw1.dao.Dao;
import hw1.model.Cell;
import hw1.model.Checkpoint;
import hw1.model.Plan;
import hw1.model.Runway;
import hw1.model.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddCheckPoint")
public class AddCheckPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCheckPoint() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("views/AddCheckPoint.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stageIndex = request.getParameter("stage");
		String runwayIndex = request.getParameter("runway");
		String checkPoint = request.getParameter("checkpoint");
		
		
		Plan plan = (Plan) getServletContext().getAttribute(Constants.PLAN);
		
		Runway runway = plan.getRunwayList().get(Integer.parseInt(runwayIndex));
		Stage stage = plan.getStageList().get(Integer.parseInt(stageIndex));
		List<Checkpoint> listCheckpoint= Dao.getCheckPoint(runway.getDesc(),  stage.getDesc(), getServletContext());
	
		
	
			Checkpoint newCheckPoint = new Checkpoint(checkPoint);
			if(listCheckpoint==null){
				listCheckpoint = new ArrayList<Checkpoint>();
			}
			listCheckpoint.add(newCheckPoint);
			
		
		response.sendRedirect("views/index.jsp");
	}

}
