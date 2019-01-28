package assignment.restdata;

public class StudentRequest {
	
	private String courseName;
	private String title;
	private String author;
	private String publisher;
	private int semester;
	private boolean received;
	
	public StudentRequest() {

	}
	
	public StudentRequest(String courseName, String title, String author, String publisher, int semester, boolean received) {
		this.courseName = courseName;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.semester = semester;
		this.received = received;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public boolean isReceived() {
		return received;
	}
	public void setReceived(boolean received) {
		this.received = received;
	}
	
	
}
