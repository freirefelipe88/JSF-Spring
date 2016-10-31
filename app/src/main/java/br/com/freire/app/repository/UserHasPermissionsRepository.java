package br.com.freire.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.freire.app.model.UserHasPermissions;

@Repository
public interface UserHasPermissionsRepository extends JpaRepository<UserHasPermissions, Long>{

	@Transactional
	public void deleteUserHasPermissionsByPermissionId(Long permissionId);

	@Transactional
	public void deleteUserHasPermissionsByUserId(Long userId);
	
}
