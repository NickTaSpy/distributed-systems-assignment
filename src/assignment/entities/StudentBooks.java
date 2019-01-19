package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_books")
public class StudentBooks {
	@Id
	@Column(name = "studentuserid")
	private int studentUserId;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private Student student;
	
	@Column(name = "bookselected")
	private int bookSelected;
	
	@Column(name = "bookreceived")
	private boolean bookReceived;
	
	public StudentBooks() {
		
	}

	public int getStudentUserId() {
		return studentUserId;
	}

	public void setStudentUserId(int studentUserId) {
		this.studentUserId = studentUserId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getBookSelected() {
		return bookSelected;
	}

	public void setBookSelected(int bookSelected) {
		this.bookSelected = bookSelected;
	}

	public boolean isBookReceived() {
		return bookReceived;
	}

	public void setBookReceived(boolean bookReceived) {
		this.bookReceived = bookReceived;
	}
}
