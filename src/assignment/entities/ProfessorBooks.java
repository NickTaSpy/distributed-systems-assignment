package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "professor_books")
public class ProfessorBooks {
	@Id
	@Column(name = "professoruserid")
	private int publisherUserId;
	
	@Column(name = "course")
	private String course;
	
	@Column(name = "book")
	private int book;
	
	public ProfessorBooks() {
		
	}

	public int getPublisherUserId() {
		return publisherUserId;
	}

	public void setPublisherUserId(int publisherUserId) {
		this.publisherUserId = publisherUserId;
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
