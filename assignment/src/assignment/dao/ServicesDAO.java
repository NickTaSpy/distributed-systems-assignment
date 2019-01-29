package assignment.dao;

import java.util.List;

import assignment.entities.Book;
import assignment.entities.Course;
import assignment.entities.Professor;
import assignment.entities.ProfessorBooks;
import assignment.entities.Publisher;
import assignment.entities.PublisherBooks;
import assignment.entities.Role;
import assignment.entities.Service;
import assignment.entities.Student;
import assignment.entities.User;

public interface ServicesDAO {
	public List<User> getUsers();
	public User findUser(String email, String password);
	public User findUser(String email);
	public List<Service> getServices(String role);
	public Publisher getPublisher();
	public Professor getProfessor();
	public void deletePublisherBook(int id);
	public void updatePublisherBook(int id, int booksAvailable);
	public void addPublisherBook(String name, String author, int booksAvailable);
	public void updatePublisherDirections(int id, String directions);
	public void confirmDelivery(int bookId, String studentEmail);
	public List<Publisher> getAllBooks();
	public void updateProfessorBooks(String courseName, int bookId1, int bookId2);
	public void deleteUser(int userId);
	public Role findUserRole(int id);
	public User findUser(int id);
	public void updateUser(int id, String firstName, String lastName, String email, String phone);
	public void updatePublisher(int id, String publisherName);
	public void addUser(User user, String roleParam);
	public Student getStudent();
	public List<Course> getSemesterCourses(int semester);
	public List<ProfessorBooks> getProfessorBooks(String course);
	public void setStudentBooks(String courseName, String bookName);
	public List<Course> findCourse(String course);
	public List<Book> findBook(String book);
	public PublisherBooks getPublisherBook(int bookId);
	public List<User> findStudentsWithNoSelectedBooks();
}
