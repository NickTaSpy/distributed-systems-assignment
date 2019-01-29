package assignment.restdata;

public class StudentRequest {
	
	private String courseName;
	private String title;
	private String author;
	private String publisher;
	private int semester;
	private boolean received;
	private String directions;
	
	public StudentRequest() {

	}
	
	public StudentRequest(String courseName, String title, String author, String publisher, int semester, boolean received, String directions) {
		this.courseName = courseName;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.semester = semester;
		this.received = received;
		this.directions = directions;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
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
