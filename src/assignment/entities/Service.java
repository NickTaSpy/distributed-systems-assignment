package assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service {
	
	@Id
	@Column(name = "name")
	public String name;
	
	@Enumerated
	@Column(name = "role")
	public Role role;
}
