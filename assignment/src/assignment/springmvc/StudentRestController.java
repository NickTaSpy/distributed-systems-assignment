package assignment.springmvc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assignment.dao.ServicesDAO;
import assignment.entities.Book;
import assignment.entities.Course;
import assignment.entities.ProfessorBooks;
import assignment.entities.PublisherBooks;
import assignment.entities.Student;
import assignment.entities.StudentBooks;
import assignment.restdata.StudentRequest;

@RestController
@RequestMapping("/student")
public class StudentRestController {
	@Autowired
	private ServicesDAO servicesDAO;
	
    @GetMapping("books")
    public List<StudentRequest> getStudentRequests() {
    	Student student = servicesDAO.getStudent();
    	List<StudentRequest> studentReqs = new ArrayList<StudentRequest>();
    	
    	for (StudentBooks sb : student.getStudentBooks()) {
    		Course course = sb.getCourse();
    		PublisherBooks pb = sb.getPublisherBook();
    		if (pb != null) {
    			studentReqs.add(new StudentRequest(course.getName(), pb.getBook().getName(), 
    					pb.getBook().getAuthor(), pb.getPublisher().getPublisherName(), 
    					course.getSemester(), sb.isBookReceived()));
    		}
    		
    	}
    	
        return studentReqs;
    }
    
    @GetMapping("semesterCourses")
    public List<String> getSemesterCourses() {
    	List<String> courseNames = new ArrayList<String>();
    	for (Course course : servicesDAO.getSemesterCourses(servicesDAO.getStudent().getSemester())) {
    		courseNames.add(course.getName());
    	}
    	return courseNames;
    }
    
    @GetMapping(value = "semesterBooks/{courseName}", produces = "application/json")
    public List<Book> getSemesterBooks(@PathVariable("courseName") String courseName) {
    	List<Book> books = new ArrayList<Book>();
    	List<ProfessorBooks> profBooks = servicesDAO.getProfessorBooks(courseName);
    	for (ProfessorBooks profBook : profBooks) {
    		books.add(profBook.getBookDetails());
    	}
        return books;
    }
}
