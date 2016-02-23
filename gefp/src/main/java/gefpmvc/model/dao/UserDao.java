package gefpmvc.model.dao;

import java.util.List;

import gefpmvc.model.User;

public interface UserDao {
	
	public User getUserByUsername(String username);

	public User getUserByCIN(String cin);

	public User saveUser(User user);

	public List<User> searchUsersByPrefix(String term, int i);

	public List<User> searchUsers(String term);
	
	public User getUserById(Long userId);
}
