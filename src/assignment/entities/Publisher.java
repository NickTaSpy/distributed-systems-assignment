package assignment.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name = "publisher")
@Polymorphism(type = PolymorphismType.EXPLICIT)
@PrimaryKeyJoinColumn(name="userid", referencedColumnName="id")
public class Publisher extends User{
	
	@Column(name = "directions")
	public String directions;
	
	@Column(name = "publisherName")
	public String publisherName;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="publisher_books",
	joinColumns=@JoinColumn(name="publisheruserid"))
	public List<PublisherBooks> publisherBooks;
}
