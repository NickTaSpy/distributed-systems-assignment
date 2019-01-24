package assignment.springmvc;

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
import assignment.entities.Service;

@Controller
public class ServiceController {
	@Autowired
	private ServicesDAO servicesDAO;
	
	@RequestMapping("/services")
	public String publisherPage(HttpServletRequest request, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<Service> services = servicesDAO.getServices(userDetails.getAuthorities().iterator().next().getAuthority());
		model.addAttribute("services", services);
		return "services";
	}
}
