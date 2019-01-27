package assignment.springmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assignment.dao.ServicesDAO;
import assignment.entities.Course;
import assignment.entities.PublisherBooks;
import assignment.entities.Student;
import assignment.entities.StudentBooks;

@RestController
@RequestMapping("/student")
public class StudentRestController {
	@Autowired
	private ServicesDAO servicesDAO;
	
    @GetMapping("books")
    public String sayHello() {
    	Student student = servicesDAO.getStudent();
    	for (StudentBooks sb : student.getStudentBooks()) {
    		System.out.println(sb.getCourseId());
    		Course course = sb.getCourse();
    		System.out.println(course.getName());
    		System.out.println(course.getSemester());
    		PublisherBooks pb = sb.getPublisherBook();
    		if (pb != null) {
    			System.out.println(pb.getPublisher().getPublisherName());
    		}
    		
    	}
        return "Hello World!";
    }
}
