package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "publisher_books")
public class PublisherBooks {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "publisheruserid")
	private int publisherUserId;
	
	@ManyToOne
	@JoinColumn(name="publisheruserid", referencedColumnName="userid", insertable=false, updatable=false)
	private Publisher publisher;
	
	@OneToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name="book", referencedColumnName="id", insertable=false, updatable=false)
	private Book book;
	
	@Column(name="book")
	private int bookId;
	
	@Column(name = "booksavailable")
	private int booksAvailable;
	
	public PublisherBooks() {
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPublisherUserId() {
		return publisherUserId;
	}

	public void setPublisherUserId(int publisherUserId) {
		this.publisherUserId = publisherUserId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getBooksAvailable() {
		return booksAvailable;
	}

	public void setBooksAvailable(int booksAvailable) {
		this.booksAvailable = booksAvailable;
	}
}
