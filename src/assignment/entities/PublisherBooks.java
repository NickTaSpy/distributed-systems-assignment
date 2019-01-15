package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publisher_books")
public class PublisherBooks {
	@Id
	@Column(name = "publisheruserid")
	public int publisherUserId;
	
	@Column(name = "books")
	public int book;
	
	@Column(name = "booksavailable")
	public boolean bookAvailable;
}
