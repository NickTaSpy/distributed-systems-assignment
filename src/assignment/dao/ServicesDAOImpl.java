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
}