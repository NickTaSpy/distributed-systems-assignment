package student.springmvc;


import java.nio.charset.Charset;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("/student")
	public String studentPage(HttpServletRequest request, Model model) {
		ResponseEntity<String> response = AuthRequest.getResponse((String)session().getAttribute("Authorization"), "http://localhost:8080/assignment/student/hello", HttpMethod.GET, null, String.class);
		if (HasError(response.getStatusCode())) {
	    	return "redirect:/login/?error";
	    }
		
	    model.addAttribute("message", response.getBody());
		return "student";
	}
	
	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true);
	}
	
	public boolean HasError(HttpStatus status) {
		return status == HttpStatus.UNAUTHORIZED;
	}
}
