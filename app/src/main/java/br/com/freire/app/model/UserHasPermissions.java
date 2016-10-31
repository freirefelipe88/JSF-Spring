package br.com.freire.app.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_has_permissions", schema = "freire")
public class UserHasPermissions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5571632377506235474L;

	@EmbeddedId
	private UserHasPermissionPK pk;

	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "id", insertable = false, updatable = false)
	private Permission permission;
	
	public UserHasPermissions() {
		super();
	}

	public UserHasPermissions(UserHasPermissionPK pk) {
		super();
		this.pk = pk;
	}

	public UserHasPermissionPK getPk() {
		return pk;
	}

	public void setPk(UserHasPermissionPK pk) {
		this.pk = pk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		UserHasPermissions other = (UserHasPermissions) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserHasPermissions [pk=" + pk + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	
}
