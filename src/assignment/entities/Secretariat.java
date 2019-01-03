package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name = "secretariat")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Secretariat extends User{
	@Enumerated
	@Column(name = "department")
	public Department department;
}
