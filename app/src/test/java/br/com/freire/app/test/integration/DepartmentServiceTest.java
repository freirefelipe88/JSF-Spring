package br.com.freire.app.test.integration;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.freire.app.configuration.AppConfig;
import br.com.freire.app.configuration.MyAppInitializer;
import br.com.freire.app.configuration.PersistenceConfig;
import br.com.freire.app.model.Department;
import br.com.freire.app.service.DepartmentService;

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
		Department department = this.getDepartmentService().findById(1L);
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
}
