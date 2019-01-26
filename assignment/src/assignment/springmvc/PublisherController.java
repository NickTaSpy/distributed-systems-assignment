package assignment.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.dao.ServicesDAO;
import assignment.entities.Publisher;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
	@Autowired
	private ServicesDAO servicesDAO;
	
	@RequestMapping("manageBook")
	public String manageBook(HttpServletRequest request, Model model) {
		Publisher publisher = servicesDAO.getPublisher();
		model.addAttribute("books", publisher.getPublisherBooks());
		model.addAttribute("currentDirections", publisher.getDirections());
		return "publisher/manageBook";
	}
	
	@RequestMapping("manageBook/delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		servicesDAO.deletePublisherBook(id);
		return "redirect:/publisher/manageBook";
	}
	
	@RequestMapping("manageBook/update/{id}/booksAvailable/")
	public String updateBook(HttpServletRequest request, Model model, @PathVariable("id") int id) {
		servicesDAO.updatePublisherBook(id, Integer.parseInt(request.getParameter("booksAvailable")));
		return "redirect:/publisher/manageBook";
	}
	
	@RequestMapping("manageBook/add/")
	public String addBook(HttpServletRequest request, Model model) {
		servicesDAO.addPublisherBook(request.getParameter("bookName"), request.getParameter("bookAuthor"), Integer.parseInt(request.getParameter("booksAvailable")));
		return "redirect:/publisher/manageBook";
	}
	
	@RequestMapping("manageBook/updateDirections/")
	public String updateDirections(HttpServletRequest request, Model model) {
		servicesDAO.updatePublisherDirections(servicesDAO.getPublisher().getId(), request.getParameter("directions"));
		return "redirect:/publisher/manageBook";
	}
	
	@RequestMapping("deliveryConfirmation")
	public String deliveryConfirmation(HttpServletRequest request, Model model) {
		Publisher publisher = servicesDAO.getPublisher();
		model.addAttribute("books", publisher.getPublisherBooks());
		return "publisher/deliveryConfirmation";
	}
	
	@RequestMapping("deliveryConfirmation/confirm/")
	public String deliveryConfirm(HttpServletRequest request, Model model) {
		servicesDAO.confirmDelivery(Integer.parseInt(request.getParameter("bookId")), request.getParameter("email"));
		return "redirect:/publisher/deliveryConfirmation";
	}
}
