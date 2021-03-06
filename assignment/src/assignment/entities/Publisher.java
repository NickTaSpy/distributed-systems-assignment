package assignment.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "publisher")
@PrimaryKeyJoinColumn(name="userid", referencedColumnName="id")
public class Publisher extends User{
	
	@Column(name = "directions")
	private String directions;
	
	@Column(name = "publisherName")
	private String publisherName;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="publisheruserid")
	private Set<PublisherBooks> publisherBooks;

	public Publisher() {
		
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Set<PublisherBooks> getPublisherBooks() {
		return publisherBooks;
	}

	public void setPublisherBooks(Set<PublisherBooks> publisherBooks) {
		this.publisherBooks = publisherBooks;
	}
	
	@Override
	public String toString() {
		return "Publisher [directions=" + directions + ", publisherName=" + publisherName + ", publisherBooks=" + publisherBooks + "]"; 
	}
}
