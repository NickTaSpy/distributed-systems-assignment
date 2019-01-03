package assignment.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;


@Entity
@Table(name = "student")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Student extends User {
	
	@Enumerated
	@Column(name = "department")
	public Department department;
	
	@Column(name = "booksRequested")
	public boolean booksRequested;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="student_books",
	joinColumns=@JoinColumn(name="studentuserid"),
	inverseJoinColumns=@JoinColumn(name="booksSelected"))
	public List<Book> booksSelected;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="student_books",
	joinColumns=@JoinColumn(name="studentuserid"),
	inverseJoinColumns=@JoinColumn(name="booksReceived"))
	public List<Boolean> booksReceived;
	
	
}
