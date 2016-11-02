package br.com.freire.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.freire.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select user from User user join user.userHasPermissions userHasPermissions where userHasPermissions.permission.id = :id")
	public List<User> findAllByPermissionId(@Param("id") Long permissionId) throws Exception;

	public List<User> findAllByDepartmentId(Long id) throws Exception;

}
