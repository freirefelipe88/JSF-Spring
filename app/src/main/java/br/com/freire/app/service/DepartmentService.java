package br.com.freire.app.service;

import java.util.List;

import br.com.freire.app.model.Department;

public interface DepartmentService {

	public Department save(Department department) throws Exception;
	
	public List<Department> list() throws Exception;
	
	public Department update(Department department) throws Exception;
	
	public void remove(Department department) throws Exception;

	public Department findById(Long id) throws Exception;
}
