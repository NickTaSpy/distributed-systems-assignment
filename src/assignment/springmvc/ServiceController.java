package assignment.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.dao.HomeDAO;

@Controller
public class ServiceController {
	@Autowired
	private HomeDAO homeDAO;
	
	@RequestMapping("/services")
	public String publisherPage(HttpServletRequest request, Model model) {
		
		return "services";
	}
}
