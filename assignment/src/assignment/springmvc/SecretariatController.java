package assignment.springmvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.dao.ServicesDAO;
import assignment.entities.User;

@Controller
@RequestMapping("/secretariat")
public class SecretariatController {
	@Autowired
	ServicesDAO servicesDAO;
	
	@RequestMapping("")
	public String bookSelection(HttpServletRequest request, Model model) {
		List<User> users = servicesDAO.findStudentsWithNoSelectedBooks();
		model.addAttribute("students", users);
		String emails = "";
		for (User user : users) {
			emails += user.getEmail() + ", ";
		}
		model.addAttribute("emails", emails);
		return "secretariat/secretariat";
	}
}
