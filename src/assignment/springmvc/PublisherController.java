package assignment.springmvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.dao.ServicesDAO;
import assignment.entities.Publisher;
import assignment.entities.Role;
import assignment.entities.Service;

@Controller
public class PublisherController {
	@Autowired
	private ServicesDAO servicesDAO;
	
	@RequestMapping("/publisher/bookManagement")
	public String bookManagement(HttpServletRequest request, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Publisher publisher = (Publisher)servicesDAO.findUser(userDetails.getUsername());
		return "publisher/bookManagement";
	}
	
	@RequestMapping("/publisher/manageBook")
	public String manageBook(HttpServletRequest request, Model model) {
		
		return "publisher/manageBook";
	}
	
	@RequestMapping("/publisher/updateInstructions")
	public String updateInstructions(HttpServletRequest request, Model model) {
		
		return "publisher/updateInstructions";
	}
}
