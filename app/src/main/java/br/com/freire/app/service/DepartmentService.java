package br.com.freire.app.service;

import java.util.List;

import br.com.freire.app.model.Department;

public interface DepartmentService {

	public Department save(Department department);
	
	public List<Department> list();
	
	public Department update(Department department);
	
	public void remove(Department department);

	public Department findById(Long id);
}
