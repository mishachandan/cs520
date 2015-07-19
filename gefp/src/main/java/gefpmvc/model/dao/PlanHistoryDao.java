package gefpmvc.model.dao;



import java.util.List;

import gefpmvc.model.PlanHistory;
import gefpmvc.model.User;

public interface PlanHistoryDao {
 public  PlanHistory savePlanHistory(PlanHistory planHistory);

 public List<PlanHistory> getPlanHistory(User user);
}
