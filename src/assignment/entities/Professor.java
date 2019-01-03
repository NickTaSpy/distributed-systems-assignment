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
@Table(name = "professor")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Professor extends User {
	@Enumerated
	@Column(name = "department")
	public Department department;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="professor_books",
	joinColumns=@JoinColumn(name="professoruserid"),
	inverseJoinColumns=@JoinColumn(name="courses"))
	public List<String> courses;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="professor_books",
	joinColumns=@JoinColumn(name="professoruserid"),
	inverseJoinColumns=@JoinColumn(name="books"))
	public List<Book> books;
}
