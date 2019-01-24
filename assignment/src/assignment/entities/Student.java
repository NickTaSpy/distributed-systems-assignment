package assignment.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name="userid", referencedColumnName="id")
public class Student extends User {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "department")
	private Department department;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="studentuserid")
	private List<StudentBooks> studentBooks;

	public Student() {
		
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<StudentBooks> getStudentBooks() {
		return studentBooks;
	}

	public void setStudentBooks(List<StudentBooks> studentBooks) {
		this.studentBooks = studentBooks;
	}
	
	
}