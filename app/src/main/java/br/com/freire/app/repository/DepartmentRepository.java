package br.com.freire.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.freire.app.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	@Query("select department from Department department join department.userList user where user.id = :id")
	public Department findById(@Param("id") Long id) throws Exception;

}
