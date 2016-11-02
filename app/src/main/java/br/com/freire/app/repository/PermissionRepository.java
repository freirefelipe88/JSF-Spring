package br.com.freire.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.freire.app.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	@Query("select permission from Permission permission join permission.userHasPermissions userHasPermissions where userHasPermissions.user.id = :id")
	List<Permission> findAllByUserId(@Param("id") Long id) throws Exception;

}
