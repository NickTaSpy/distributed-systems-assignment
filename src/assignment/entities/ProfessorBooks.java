package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "professor_books")
public class ProfessorBooks {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "professoruserid")
	private int professorUserId;
	
	@Column(name = "course")
	private String course;
	
	@Column(name = "book")
	private int book;
	
	public ProfessorBooks() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProfessorUserId() {
		return professorUserId;
	}

	public void setProfessorUserId(int professorUserId) {
		this.professorUserId = professorUserId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getBook() {
		return book;
	}

	public void setBook(int book) {
		this.book = book;
	}
	
	
}
