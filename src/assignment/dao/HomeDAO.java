package assignment.dao;

import java.util.List;

import assignment.entities.Role;
import assignment.entities.User;

public interface HomeDAO {
	public List<User> getUsers();
	public User findUser(String email, String password);
}
