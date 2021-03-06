package br.com.freire.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "freire")
public class Permission {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;

	@OneToMany(mappedBy="permission")
	private List<UserHasPermissions> userHasPermissions;
	
	public Permission() {
		super();
	}

	public Permission(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + "]";
	}

	public List<UserHasPermissions> getUserHasPermissions() {
		return userHasPermissions;
	}

	public void setUserHasPermissions(List<UserHasPermissions> userHasPermissions) {
		this.userHasPermissions = userHasPermissions;
	}

	
	
}
