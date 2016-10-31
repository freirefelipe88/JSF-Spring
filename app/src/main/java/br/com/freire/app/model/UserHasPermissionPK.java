package br.com.freire.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UserHasPermissionPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6002910586045216827L;

	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="PERMISSION_ID")
	private Long permissionId;

	
	public UserHasPermissionPK() {
		super();
	}

	public UserHasPermissionPK(Long userId, Long permissionId) {
		super();
		this.userId = userId;
		this.permissionId = permissionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserHasPermissionPK other = (UserHasPermissionPK) obj;
		if (permissionId == null) {
			if (other.permissionId != null)
				return false;
		} else if (!permissionId.equals(other.permissionId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserHasPermissionPK [userId=" + userId + ", permissionId=" + permissionId + "]";
	}
	
	
}
