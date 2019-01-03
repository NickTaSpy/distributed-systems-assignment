package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@Column(name = "id")
	public int id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "author")
	public String author;
}
