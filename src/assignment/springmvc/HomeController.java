package assignment.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.dao.HomeDAO;
import assignment.entities.Role;
import assignment.entities.User;

@Controller
public class HomeController {
	@Autowired
	private HomeDAO homeDAO;
	
	@RequestMapping("/")
	public String showMyPage() {
		return "login";
	}
}
