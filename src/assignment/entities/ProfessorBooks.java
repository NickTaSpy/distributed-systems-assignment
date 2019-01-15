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
	public int publisherUserId;
	
	@Column(name = "courses")
	public String course;
	
	@Column(name = "books")
	public int book;
}
