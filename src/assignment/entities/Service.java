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
	private String name;
	
	@Enumerated
	@Column(name = "role")
	private Role role;
	
	@Column(name = "link")
	private String link;
	
	public Service() {
	}
	
	public Service(String name, Role role, String link) {
		this.name = name;
		this.role = role;
		this.link = link;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Service [name=" + name + ", role=" + role + ",  link=" + link + "]";
	}
}
