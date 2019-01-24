package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name = "secretariat")
@Polymorphism(type = PolymorphismType.EXPLICIT)
@PrimaryKeyJoinColumn(name="userid", referencedColumnName="id")
public class Secretariat extends User{
	@Enumerated(EnumType.STRING)
	@Column(name = "department")
	private Department department;

	public Secretariat() {
		
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
