package assignment.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.dao.ServicesDAO;
import assignment.entities.Department;
import assignment.entities.Professor;
import assignment.entities.Publisher;
import assignment.entities.Secretariat;
import assignment.entities.Student;
import assignment.entities.User;

@Controller
public class AdminController {
	@Autowired
	private ServicesDAO servicesDAO;
	
	@RequestMapping("/admin")
	public String adminPage(HttpServletRequest request, Model model) {
		model.addAttribute("users", servicesDAO.getUsers());
		return "admin/adminPage";
	}
	
	@RequestMapping("/admin/update/{id}")
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
	
	@RequestMapping("/admin/update/{userId}/finalUpdate/")
	public String updateUserFinal(HttpServletRequest request, Model model, @PathVariable("userId") int id) {
		
		servicesDAO.updateUser(id, request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("phone"));
		
		switch (servicesDAO.findUserRole(id)) {
		case admin:
			return "redirect:/admin";
		case professor:
			servicesDAO.updateProfessor(id, Department.valueOf(request.getParameter("department")));
			break;
		case publisher:
			servicesDAO.updatePublisher(id, request.getParameter("publisherName"));
			break;
		case secretariat:
			servicesDAO.updateSecretariat(id, Department.valueOf(request.getParameter("department")));
			break;
		case student:
			servicesDAO.updateStudent(id, Department.valueOf(request.getParameter("department")));
			break;
		}
		return "redirect:/admin";
	}
	
	@RequestMapping("/admin/delete/{userId}")
	public String deleteUser(HttpServletRequest request, Model model, @PathVariable("userId") int userId) {
		servicesDAO.deleteUser(userId);
		return "redirect:/admin";
	}
}
