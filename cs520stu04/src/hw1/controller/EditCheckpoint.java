package hw1.controller;

import hw1.config.Constants;
import hw1.dao.Dao;
import hw1.model.Checkpoint;
import hw1.model.Plan;
import hw1.model.Runway;
import hw1.model.Stage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditCheckpoint")
public class EditCheckpoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditCheckpoint() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestRow = request.getParameter("rowId");
		String requestCol = request.getParameter("colId");
		String requestCheckpoint = request.getParameter("checkpointindex");
		/*System.out.println("---> row: "+requestRow+"\t"+requestCol+"\t"+requestCheckpoint);*/
		
		 Plan plan = (Plan) getServletContext().getAttribute(Constants.PLAN);
		 
		 Runway runwayIndex = plan.getRunwayList().get(Integer.parseInt(requestCol));
		 Stage stageIndex = plan.getStageList().get(Integer.parseInt(requestRow));
		 if(plan!=null){
			 String checkpoint = null;
			 List<Checkpoint> listCheckPoint = Dao.getCheckPoint(runwayIndex.getDesc(), stageIndex.getDesc(), getServletContext());
			 if(listCheckPoint!=null){
				 checkpoint = listCheckPoint.get(Integer.parseInt(requestCheckpoint)).getDesc();
			 }
			 RequestDispatcher rd = request.getRequestDispatcher("views/EditCheckPoint.jsp");
			 request.setAttribute("stage", stageIndex.getDesc());
			 request.setAttribute("runway", runwayIndex.getDesc());
			 request.setAttribute("checkpoint", checkpoint);
/*			 request.setAttribute("row", requestRow);
			 request.setAttribute("col", requestCol);*/
			 request.setAttribute("checkpointindex", requestCheckpoint);
			 rd.forward(request, response);
		 }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String requestOperation = request.getParameter("submit");
		String requestRowId = request.getParameter("row");
		String requestColId = request.getParameter("col");
		String requestCheckpointIndex = request.getParameter("checkpointindex");
		String requestCheckpoint = request.getParameter("checkpoint");
		
		if(requestOperation.equalsIgnoreCase("delete")){
			delete(requestRowId,requestColId,requestCheckpointIndex,request.getServletContext());
		}else if(requestOperation.equalsIgnoreCase("save")){
			save(requestRowId,requestColId,requestCheckpointIndex,requestCheckpoint,request.getServletContext());
		}else{
			System.out.println(" neither delete nor edit");
		}
		
		response.sendRedirect("views/index.jsp");
	}
	

	private void save(String requestRow, String requestCol, String requestCheckpointIndex,String requestCheckpoint, ServletContext sc) {
		/*Plan plan = (Plan)getServletContext().getAttribute(Constants.PLAN);	
		String address[] = Cell.getAddress(requestID);
		
		
		Cell cellToBeFetched = new Cell(address[0]+","+address[1],null,null);
		plan.getCheckpointMap().get(cellToBeFetched).set((Integer.parseInt(address[2])),new Checkpoint(requestCheckpoint));*/
	
		List<Checkpoint> listcheckPoint = Dao.getCheckPoint(requestCol, requestRow,  sc);
		listcheckPoint.set(Integer.parseInt(requestCheckpointIndex), new Checkpoint(requestCheckpoint));
		
	}


	private void delete(String requestRow, String requestCol,String requestCheckpointIndex, ServletContext sc) {
		/*Plan plan = (Plan)getServletContext().getAttribute(Constants.PLAN);
		
		String address[] = Cell.getAddress(requestID);
		Cell cellToBeFetched = new Cell(address[0]+","+address[1],null,null);
		plan.getCheckpointMap().get(cellToBeFetched).remove(Integer.parseInt(address[2]));*/

		List<Checkpoint> listcheckPoint = Dao.getCheckPoint(requestCol, requestRow, sc);
		listcheckPoint.remove(Integer.parseInt(requestCheckpointIndex));
		//getServletContext().setAttribute(Constants.PLAN,listcheckPoint);
	}

}
