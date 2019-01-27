package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_books")
public class StudentBooks {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "studentuserid")
	private int studentUserId;
	
	@ManyToOne
	@JoinColumn(name="studentuserid", referencedColumnName="userid", insertable=false, updatable=false)
	private Student student;
	
	@Column(name = "bookselected", nullable=true)
	private Integer bookSelected;
	
	@Column(name = "bookreceived")
	private boolean bookReceived;
	
	@Column(name = "courseid")
	private int courseId;
	
	@ManyToOne
	@JoinColumn(name="courseid", referencedColumnName="id", insertable=false, updatable=false)
	private Course course;
	
	@OneToOne
	@JoinColumn(name="bookselected", referencedColumnName="book", insertable=false, updatable=false)
	private PublisherBooks publisherBook;
	
	public StudentBooks() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PublisherBooks getPublisherBook() {
		return publisherBook;
	}

	public void setPublisherBook(PublisherBooks publisherBook) {
		this.publisherBook = publisherBook;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
