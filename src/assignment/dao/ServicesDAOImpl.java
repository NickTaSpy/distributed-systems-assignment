package assignment.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import assignment.entities.Book;
import assignment.entities.Department;
import assignment.entities.Professor;
import assignment.entities.ProfessorBooks;
import assignment.entities.Publisher;
import assignment.entities.PublisherBooks;
import assignment.entities.Role;
import assignment.entities.Service;
import assignment.entities.User;

@Repository
public class ServicesDAOImpl implements ServicesDAO {
    // inject the session factory
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public List<User> getUsers() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createQuery("from User", User.class);
        List<User> users = query.getResultList();
        return users;
    }
    
    @Override
    @Transactional
    public User findUser(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User U where U.email='" + email + "' and U.password='" + password + "'", User.class);
        List<User> results = query.getResultList();
        
        if (results.size() == 0) {
        	return null;
        }else {
        	return results.get(0);
        }
    }
    
    @Override
    @Transactional
    public User findUser(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User U where U.email='" + email + "'", User.class);
        return query.getSingleResult();
    }
    
    @Override
    @Transactional
    public User findUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User U where U.id='" + id + "'", User.class);
        return query.getSingleResult();
    }
    
    @Override
    @Transactional
    public Role findUserRole(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User U where U.id='" + id + "'", User.class);
        return query.getSingleResult().getRole();
    }
    
    @Override
    @Transactional
    public List<Service> getServices(String role) {
    	Session session = sessionFactory.getCurrentSession();
    	Query<Service> query = session.createQuery("from Service S where S.role='" + role + "'", Service.class);
    	return query.getResultList();
    }
    
    @Override
    @Transactional
    public Publisher getPublisher() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return (Publisher)findUser(userDetails.getUsername());
    }
    
    @Override
    @Transactional
    public Professor getProfessor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return (Professor)findUser(userDetails.getUsername());
    }
    
    @Override
    @Transactional
    public void deletePublisherBook(int id) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createQuery("delete from PublisherBooks PB where PB.bookId='" + id + "'").executeUpdate();
    	session.createQuery("delete from Book B where B.id='" + id + "'").executeUpdate();
    }
    
    @Override
    @Transactional
    public void updatePublisherBook(int id, int booksAvailable) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createQuery("update PublisherBooks PB set booksAvailable='" + booksAvailable + "' where PB.bookId='" + id + "'").executeUpdate();
    }
    
    @Override
    @Transactional
    public void addPublisherBook(String name, String author, int booksAvailable) {
    	Session session = sessionFactory.getCurrentSession();
    	
    	Book book = new Book();
    	book.setName(name);
    	book.setAuthor(author);
    	session.save(book);
    	
    	PublisherBooks publisherBook = new PublisherBooks();
    	publisherBook.setBookId(book.getId());
    	publisherBook.setBooksAvailable(booksAvailable);
    	publisherBook.setPublisherUserId(getPublisher().getId());
    	session.save(publisherBook);
    }
    
    @Override
    @Transactional
    public void updatePublisherDirections(int id, String directions) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createQuery("update Publisher P set P.directions='" + directions + "' where P.id='" + id + "'").executeUpdate();
    }
    
    @Override
    @Transactional
    public void confirmDelivery(int bookId, String studentEmail) {
    	Session session = sessionFactory.getCurrentSession();
    	User user = findUser(studentEmail);
    	session.createQuery("update StudentBooks SB set SB.bookReceived='1' where SB.bookSelected='" + bookId + "' and SB.studentUserId='" + user.getId() + "'").executeUpdate();
    	session.createQuery("update PublisherBooks PB set PB.booksAvailable=PB.booksAvailable - 1 where PB.bookId='" + bookId + "'").executeUpdate();
    }
    
    @Override
    @Transactional
    public List<Publisher> getAllBooks() {
    	Session session = sessionFactory.getCurrentSession();
    	return session.createQuery("from Publisher", Publisher.class).getResultList();
    }
    
    @Override
    @Transactional
    public void updateProfessorBooks(String courseName, int bookId1, int bookId2) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createQuery("delete from ProfessorBooks PB where PB.courseName='" + courseName + "'").executeUpdate();
    	ProfessorBooks professorBooks = new ProfessorBooks();
    	professorBooks.setBook(bookId1);
    	professorBooks.setCourse(courseName);
    	professorBooks.setProfessorUserId(getProfessor().getId());
    	session.save(professorBooks);
    	professorBooks = new ProfessorBooks();
    	professorBooks.setBook(bookId2);
    	professorBooks.setCourse(courseName);
    	professorBooks.setProfessorUserId(getProfessor().getId());
    	session.save(professorBooks);
    }
    
    @Override
    @Transactional
    public void deleteUser(int userId) {
    	Session session = sessionFactory.getCurrentSession();
    	Role role = findUserRole(userId);
    	switch (role) {
	    	case admin:
	    		return;
	    	case professor:
	    		session.createQuery("delete from Professor P where P.id='" + userId +"'").executeUpdate();
	    		session.createQuery("delete from ProfessorBooks PB where PB.professorUserId='" + userId +"'").executeUpdate();
	    		break;
	    	case publisher:
	    		session.createQuery("delete from Publisher P where P.id='" + userId +"'").executeUpdate();
	    		session.createQuery("delete from PublisherBooks PB where PB.publisherUserId='" + userId +"'").executeUpdate();
	    		break;
	    	case secretariat:
	    		session.createQuery("delete from Secretariat S where S.id='" + userId +"'").executeUpdate();
	    		break;
	    	case student:
	    		session.createQuery("delete from Student S where S.id='" + userId +"'").executeUpdate();
	    		session.createQuery("delete from StudentBooks SB where SB.studentUserId='" + userId +"'").executeUpdate();
	    		break;
    	}
    	session.createQuery("delete from User U where U.id='" + userId +"'").executeUpdate();
    }
    
    @Override
    @Transactional
    public void updateUser(int id, String firstName, String lastName, String email, String phone) {
    	Session session = sessionFactory.getCurrentSession();
    	User user = findUser(id);
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    	user.setEmail(email);
    	user.setPhone(phone);
    	session.update(user);
    }
    
    @Override
    @Transactional
    public void updateProfessor(int id, Department department) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createQuery("update Professor P set P.department='" + department + "' where P.id='" + id + "'").executeUpdate();
    }
    
    @Override
    @Transactional
    public void updatePublisher(int id, String publisherName) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createQuery("update Publisher P set P.publisherName='" + publisherName + "' where P.id='" + id + "'").executeUpdate();
    }
    
    @Override
    @Transactional
    public void updateStudent(int id, Department department) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createQuery("update Student S set S.department='" + department + "' where S.id='" + id + "'").executeUpdate();
    }
    
    @Override
    @Transactional
    public void updateSecretariat(int id, Department department) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createQuery("update Secretariat S set S.department='" + department + "' where S.id='" + id + "'").executeUpdate();
    }
}