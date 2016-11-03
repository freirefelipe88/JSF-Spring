package br.com.freire.app.test.integration;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.freire.app.configuration.AppConfig;
import br.com.freire.app.configuration.MyAppInitializer;
import br.com.freire.app.configuration.PersistenceConfig;
import br.com.freire.app.model.Department;
import br.com.freire.app.service.DepartmentService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyAppInitializer.class, AppConfig.class, PersistenceConfig.class})
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;
	
	@Test
	@Transactional
	public void list() throws Exception{
		List<Department> departmentList = this.getDepartmentService().list();
		Assert.assertNotNull(departmentList);
	}
	
	@Test
	@Transactional
	public void findById() throws Exception{
		Department department = new Department();
		department.setName("Felipe teste");
		department.setDescription("teste");
		
		Department savedDepartment = this.getDepartmentService().save(department);
		
		Department result = this.getDepartmentService().findById(savedDepartment.getId());
	}
	
	@Test
	@Transactional
	public void insert() throws Exception{
		Department department = new Department();
		department.setName("Felipe teste");
		department.setDescription("teste");
		
		Department result = this.getDepartmentService().save(department);
		
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getId());
	}
	
	@Test
	@Transactional
	public void edit() throws Exception{
		Department department = new Department();
		department.setName("Felipe teste");
		department.setDescription("teste");
		
		Department result = this.getDepartmentService().save(department);
		
		result.setName("Editado");
		long expectedId = result.getId();
		
		result = this.getDepartmentService().update(result);
		
		Assert.assertNotNull(result);
		Assert.assertEquals("Expected Id is not the same", expectedId, result.getId().longValue());
	}
	
	@Test
	@Transactional
	public void delete() throws Exception{
		Department department = new Department();
		department.setName("Felipe teste");
		department.setDescription("teste");
		
		Department result = this.getDepartmentService().save(department);
		
		this.getDepartmentService().remove(result);
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
}
