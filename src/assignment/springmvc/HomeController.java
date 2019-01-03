package assignment.springmvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.dao.HomeDAO;
import assignment.entities.User;

@Controller
public class HomeController {
	@Autowired
	private HomeDAO homeDAO;
	
	@RequestMapping("/")
	public String showMyPage() {
		return "login";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest request, Model model) {
		//get users from dao
		List<User> users = homeDAO.getUsers();
		
		// add the users to the model
		model.addAttribute("users", users);
		
		return "login";
	}
}
