package assignment.entities;

import java.util.List;
import java.util.Set;

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
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="studentuserid")
	private Set<StudentBooks> studentBooks;

	public Student() {
		
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<StudentBooks> getStudentBooks() {
		return studentBooks;
	}

	public void setStudentBooks(Set<StudentBooks> studentBooks) {
		this.studentBooks = studentBooks;
	}
	
	
}