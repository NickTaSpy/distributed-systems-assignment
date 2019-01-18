package assignment.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name="userid", referencedColumnName="id")
public class Professor extends User {
	
	@Enumerated
	@Column(name = "department")
	private Department department;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="professoruserid")
	private List<ProfessorBooks> professorBooks;
	
	public Professor() {
		
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<ProfessorBooks> getProfessorBooks() {
		return professorBooks;
	}

	public void setProfessorBooks(List<ProfessorBooks> professorBooks) {
		this.professorBooks = professorBooks;
	}
	
	
}
