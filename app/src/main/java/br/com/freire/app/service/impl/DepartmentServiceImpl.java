package br.com.freire.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.freire.app.model.Department;
import br.com.freire.app.repository.DepartmentRepository;
import br.com.freire.app.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department save(Department department) {
		return this.getDepartmentRepository().save(department);
	}

	@Override
	public List<Department> list() {
		return this.getDepartmentRepository().findAll();
	}

	@Override
	public Department update(Department department) {
		return this.getDepartmentRepository().save(department);
	}

	@Override
	public void remove(Department department) {
		this.getDepartmentRepository().delete(department);
	}

	@Override
	public Department findById(Long id) throws Exception {
		return this.getDepartmentRepository().findById(id);
	}

	public DepartmentRepository getDepartmentRepository() {
		return departmentRepository;
	}

	public void setDepartmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	
	
	
}
