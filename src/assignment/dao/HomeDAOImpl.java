package assignment.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import assignment.entities.User;

@Repository
public class HomeDAOImpl implements HomeDAO {
        // inject the session factory
        @Autowired
        private SessionFactory sessionFactory;
        
        @Override
        @Transactional
        public List<User> getUsers() {
                // get current hibernate session
                Session currentSession = sessionFactory.getCurrentSession();
                
                // create a query
                Query<User> query = currentSession.createQuery("from user", User.class);
                
                // execute the query and get the results list
                List<User> users = query.getResultList();
                                
                //return the results
                return users;
        }

}