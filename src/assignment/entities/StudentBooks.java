package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_books")
public class StudentBooks {
	@Id
	@Column(name = "studentuserid")
	public int studentUserId;
	
	@Column(name = "booksselected")
	public int bookSelected;
	
	@Column(name = "booksreceived")
	public boolean bookReceived;
}
