package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "publisher_books")
public class PublisherBooks {
	
	@Column(name = "publisheruserid")
	private int publisherUserId;
	
	@OneToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name="book", referencedColumnName="id")
	private Book book;
	
	@Id
	@PrimaryKeyJoinColumn
	@Column(name="book")
	private int bookId;
	
	@Column(name = "booksavailable")
	private int booksAvailable;
	
	public PublisherBooks() {
		
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
