package assignment.dao;

import java.util.List;

import assignment.entities.Publisher;
import assignment.entities.Role;
import assignment.entities.Service;
import assignment.entities.User;

public interface ServicesDAO {
	public List<User> getUsers();
	public User findUser(String email, String password);
	public User findUser(String email);
	public List<Service> getServices(String role);
}
