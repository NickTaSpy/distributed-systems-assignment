package assignment.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.dao.ServicesDAO;
import assignment.entities.Professor;
import assignment.entities.Publisher;
import assignment.entities.Role;
import assignment.entities.Secretariat;
import assignment.entities.Student;
import assignment.entities.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ServicesDAO servicesDAO;
	
	@RequestMapping("")
	public String adminPage(HttpServletRequest request, Model model) {
		model.addAttribute("users", servicesDAO.getUsers());
		return "admin/adminPage";
	}
	
	@RequestMapping("add")
	public String addUser(HttpServletRequest request, Model model) {
		if (servicesDAO.findUser(request.getParameter("email")) != null) {
			return "redirect:/admin";
		}
		Role role = Role.values()[Integer.parseInt(request.getParameter("role"))];
		User user;
    	switch (role) {
	    	case professor:
	    		user = new Professor();
	    		break;
	    	case secretariat:
	    		user = new Secretariat();
	    		break;
	    	case student:
	    		Student student = new Student();
	    		student.setSemester(1);
	    		user = student;
	    		break;
	    	case publisher:
	    		user = new Publisher();
	    		break;
    		default:
    			return "redirect:/admin";
		}
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(new BCryptPasswordEncoder(4).encode(request.getParameter("password")));
		user.setPhone(request.getParameter("phone"));
		user.setRole(role);
		user.setEnabled(true);
		servicesDAO.addUser(user, request.getParameter("roleColumn"));
		return "redirect:/admin";
	}
	
	@RequestMapping("update/{id}")
	public String updateUser(HttpServletRequest request, Model model, @PathVariable("id") int id) {
		User user = servicesDAO.findUser(id);
		model.addAttribute("users", servicesDAO.getUsers());
		model.addAttribute("selectedUser", user);
		switch (user.getRole()) {
			case admin:
				return "admin/adminPage";
			case professor:
				model.addAttribute("specialUser", (Professor)user);
				break;
			case publisher:
				model.addAttribute("specialUser", (Publisher)user);
				break;
			case secretariat:
				model.addAttribute("specialUser", (Secretariat)user);
				break;
			case student:
				model.addAttribute("specialUser", (Student)user);
				break;
		}
		return "admin/adminPage";
	}
	
	@RequestMapping("update/{userId}/finalUpdate/")
	public String updateUserFinal(HttpServletRequest request, Model model, @PathVariable("userId") int id) {
		
		servicesDAO.updateUser(id, request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("phone"));
		
		if (servicesDAO.findUserRole(id) == Role.publisher) {
			servicesDAO.updatePublisher(id, request.getParameter("publisherName"));
		}
		return "redirect:/admin";
	}
	
	@RequestMapping("delete/{userId}")
	public String deleteUser(HttpServletRequest request, Model model, @PathVariable("userId") int userId) {
		servicesDAO.deleteUser(userId);
		return "redirect:/admin";
	}
}
