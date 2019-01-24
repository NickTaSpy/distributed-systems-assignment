package assignment.springmvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import assignment.dao.ServicesDAO;
import assignment.entities.Professor;

@Controller
public class ProfessorController {
	@Autowired
	private ServicesDAO servicesDAO;
	
	@RequestMapping("/professor/bookSelection")
	public String bookSelection(HttpServletRequest request, Model model) {
		Professor professor = servicesDAO.getProfessor();
		model.addAttribute("courses", professor.getProfessorCourses());
		return "professor/bookSelection";
	}
	
	@RequestMapping("/professor/bookSelection/select/{courseName}")
	public String bookSelection(HttpServletRequest request, Model model, @PathVariable("courseName") String courseName) {
		model.addAttribute("courseName", courseName);
		model.addAttribute("publishers", servicesDAO.getAllBooks());
		return "/professor/bookSelection2";
	}
	
	@RequestMapping("/professor/bookSelection/select/{courseName}/books")
	public String bookSelection(HttpServletRequest request, Model model, @PathVariable("courseName") String courseName, @RequestParam MultiValueMap<String, String> allRequestParams) {
		List<String> books = allRequestParams.get("book");
		servicesDAO.updateProfessorBooks(courseName, Integer.parseInt(books.get(0)), Integer.parseInt(books.get(1)));
		return "redirect:/professor/bookSelection";
	}
}
