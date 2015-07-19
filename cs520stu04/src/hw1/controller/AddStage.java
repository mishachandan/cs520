package hw1.controller;

import hw1.config.Constants;
import hw1.model.Cell;
import hw1.model.Checkpoint;
import hw1.model.Plan;
import hw1.model.Runway;
import hw1.model.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddStage")
public class AddStage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddStage() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("views/AddStage.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stage = request.getParameter("stage");
		
		if(stage!=null && !stage.equals("")){
			Plan plan = (Plan)getServletContext().getAttribute(Constants.PLAN);
			Stage newStage = new Stage(stage);
			plan.getStageList().add(newStage);
			
			for (Iterator iterator = plan.getRunwayList().iterator(); iterator.hasNext();) {
				Runway runway = (Runway) iterator.next();
				Cell newCell = new Cell(runway, newStage);
				List<Checkpoint> listCheckpoint = new ArrayList<Checkpoint>();
				plan.getCheckpointMap().put(newCell, listCheckpoint);
			}
			response.sendRedirect("views/index.jsp");
		}else{
			System.out.println(" Stage request paramter is null");
		}
		
		
	}

}
 