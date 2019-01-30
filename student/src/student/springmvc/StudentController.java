package student.springmvc;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import student.entities.Book;
import student.security.AuthRequest;


@Controller
public class StudentController {
	@RequestMapping(value={"/login", "/login/?error"})
	public String loginPage(HttpServletRequest request, Model model){
		
		return "login";
	}
	
	@PostMapping("/loginForm")
	public String loginProcessingPage(HttpServletRequest request, Model model){
		byte[] authKey = Base64.encodeBase64((request.getParameter("username") + ":" + request.getParameter("password")).getBytes());
		session().setAttribute("Authorization", "Basic " + new String(authKey));
		return "redirect:/student";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model){
		session().invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/student")
	public String studentPage(HttpServletRequest request, Model model) {
		ResponseEntity<Object[]> studentRequests = AuthRequest.getResponse((String)session().getAttribute("Authorization"), "http://localhost:8080/assignment/student/books", HttpMethod.GET, null, Object[].class);
		if (HasError()) {
	    	return "redirect:/login/?error";
	    }
		
		ResponseEntity<String[]> courses = AuthRequest.getResponse((String)session().getAttribute("Authorization"), "http://localhost:8080/assignment/student/semesterCourses", HttpMethod.GET, null, String[].class);
		if (HasError()) {
			return "redirect:/login/?error";
		}
		
		List<Book> books = new ArrayList<Book>();
		for (String course : courses.getBody()) {
			ResponseEntity<Book[]> courseBooks = AuthRequest.getResponse((String)session().getAttribute("Authorization"), "http://localhost:8080/assignment/student/semesterBooks/{courseName}", HttpMethod.GET, null, Book[].class, course);
			books.addAll(Arrays.asList(courseBooks.getBody()));
		}
		model.addAttribute("books", books);
		
	    model.addAttribute("studentReqs", studentRequests.getBody());
	    model.addAttribute("courses", courses.getBody());
		return "student";
	}
	
	@GetMapping("/student/submitBooks")
	public String submitBooks(HttpServletRequest request, Model model) {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			ResponseEntity<String> reply = AuthRequest.getResponse((String)session().getAttribute("Authorization"), "http://localhost:8080/assignment/student/selectBook/{courseName}/{bookName}", HttpMethod.PUT, null, String.class, name, request.getParameter(name));
			if (HasError()) {
		    	return "redirect:/login/?error";
		    }
		}
		return "redirect:/student";
	}
	
	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true);
	}
	
	public boolean HasError() {
		return AuthRequest.hasError();
	}
}
