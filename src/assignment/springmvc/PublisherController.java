package assignment.springmvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import assignment.dao.ServicesDAO;
import assignment.entities.Publisher;
import assignment.entities.PublisherBooks;

@Controller
public class PublisherController {
	@Autowired
	private ServicesDAO servicesDAO;
	
	@RequestMapping("/publisher/bookManagement")
	public String bookManagement(HttpServletRequest request, Model model) {
		
		return "publisher/bookManagement";
	}
	
	@RequestMapping("/publisher/manageBook")
	public String manageBook(HttpServletRequest request, Model model) {
		Publisher publisher = servicesDAO.getPublisher();
		model.addAttribute("books", publisher.getPublisherBooks());
		return "publisher/manageBook";
	}
	
	@RequestMapping("/publisher/updateInstructions")
	public String updateInstructions(HttpServletRequest request, Model model) {
		
		return "publisher/updateInstructions";
	}
	
	@RequestMapping("/publisher/manageBook/delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		servicesDAO.deletePublisherBook(id);
		return "redirect:/publisher/manageBook";
	}
	
	@RequestMapping("/publisher/manageBook/update/{id}/booksAvailable/")
	public String updateBook(HttpServletRequest request, Model model, @PathVariable("id") int id) {
		servicesDAO.updatePublisherBook(id, Integer.parseInt(request.getParameter("booksAvailable")));
		return "redirect:/publisher/manageBook";
	}
	
	@RequestMapping("/publisher/manageBook/add/")
	public String addBook(HttpServletRequest request, Model model) {
		servicesDAO.addPublisherBook(request.getParameter("bookName"), request.getParameter("bookAuthor"), Integer.parseInt(request.getParameter("booksAvailable")));
		return "redirect:/publisher/manageBook";
	}
}
