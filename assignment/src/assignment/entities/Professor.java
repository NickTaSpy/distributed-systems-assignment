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
@Table(name = "professor")
@PrimaryKeyJoinColumn(name="userid", referencedColumnName="id")
public class Professor extends User {
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="professoruserid")
	private List<ProfessorBooks> professorBooks;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
	private Set<Course> professorCourses;
	
	public Professor() {
		
	}

	public List<ProfessorBooks> getProfessorBooks() {
		return professorBooks;
	}

	public void setProfessorBooks(List<ProfessorBooks> professorBooks) {
		this.professorBooks = professorBooks;
	}

	public Set<Course> getProfessorCourses() {
		return professorCourses;
	}

	public void setProfessorCourses(Set<Course> professorCourses) {
		this.professorCourses = professorCourses;
	}
}
