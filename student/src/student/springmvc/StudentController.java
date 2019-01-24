package student.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
public class StudentController {

	@RequestMapping("/student")
	public String studentPage(HttpServletRequest request, Model model) {
		final String uri = "http://localhost:8080/assignment/student/hello";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	     
	    model.addAttribute("message", result);
		return "student";
	}
}
