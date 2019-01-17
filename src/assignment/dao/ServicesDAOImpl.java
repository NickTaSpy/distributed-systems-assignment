package assignment.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import assignment.entities.Publisher;
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
}