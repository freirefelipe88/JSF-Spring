package br.com.freire.app.backingbean.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.context.RequestContext;

import br.com.freire.app.backingbean.MBeanDepartment;
import br.com.freire.app.model.Department;
import br.com.freire.app.model.User;
import br.com.freire.app.service.DepartmentService;
import br.com.freire.app.service.UserService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FacesContext.class, RequestContext.class })
public class MBeanDepartmentTest{

	@Mock
	private DepartmentService departmentService;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private MBeanDepartment mBeanDepartment = new MBeanDepartment();
	
	@Mock
	private FacesContext facesContext;
	
	@Mock
	private RequestContext requestContext;
	
	@Before
	public void setUp() throws Exception {

		PowerMockito.mockStatic(FacesContext.class);
		PowerMockito.mockStatic(RequestContext.class);
		
		Mockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		Mockito.when(RequestContext.getCurrentInstance()).thenReturn(requestContext);
		
		Mockito.when(this.getDepartmentService().list()).thenReturn(this.getMyDepartmentList());
	}
	
	@Test
	public void initShouldReturnDepartmentEmptyList() throws Exception{
		
		Mockito.when(this.getDepartmentService().list()).thenReturn(Collections.EMPTY_LIST);
		
		this.mBeanDepartment.init();
		
		Assert.assertNotNull(this.getmBeanDepartment().getDepartmentList());
		Assert.assertTrue(this.getmBeanDepartment().getDepartmentList().isEmpty());
	}
	
	@Test
	public void initShouldReturnDepartmentList(){
		
		this.mBeanDepartment.init();
		
		Assert.assertNotNull(this.getmBeanDepartment().getDepartmentList());
		Assert.assertFalse(this.getmBeanDepartment().getDepartmentList().isEmpty());
	}

	@Test
	public void initShouldThrowGenericError() throws Exception{
		Mockito.doThrow(Exception.class).when(this.getDepartmentService()).list();
		
		this.getmBeanDepartment().init();
	}
	
	@Test
	public void saveShouldSucceed() throws Exception{
		
		Mockito.when(this.getDepartmentService().save(this.getMyDepartment())).thenReturn(this.getMyDepartment());
		
		this.getmBeanDepartment().setDepartment(this.getMyDepartment());
		this.getmBeanDepartment().save();
		
		Assert.assertNotNull(this.getmBeanDepartment().getDepartment());
		Assert.assertEquals("Departments are not equals.", this.getMyDepartment().getName(), this.getmBeanDepartment().getDepartment().getName());
		
	}
	
	@Test
	public void saveShouldThrowGenericError() throws Exception{
		Mockito.doThrow(Exception.class).when(this.getDepartmentService()).save(this.getMyDepartment());
		
		this.getmBeanDepartment().setDepartment(this.getMyDepartment());
		this.getmBeanDepartment().save();

		Assert.assertNotNull(this.getmBeanDepartment().getDepartment());
		Assert.assertEquals("Departments are not equals.", this.getMyDepartment().getName(), this.getmBeanDepartment().getDepartment().getName());
	}
	
	@Test
	public void updateShouldSucceed() throws Exception{
		
		Mockito.when(this.getDepartmentService().update(this.getMyDepartment())).thenReturn(this.getMyDepartment());
		
		this.getmBeanDepartment().setDepartment(this.getMyDepartment());
		this.getmBeanDepartment().update();
		
		Assert.assertNotNull(this.getmBeanDepartment().getDepartment());
		Assert.assertEquals("Departments are not equals.", this.getMyDepartment().getName(), this.getmBeanDepartment().getDepartment().getName());
		
	}
	
	@Test
	public void updateShouldThrowGenericError() throws Exception{
		Mockito.doThrow(Exception.class).when(this.getDepartmentService()).update(this.getMyDepartment());
		
		this.getmBeanDepartment().setDepartment(this.getMyDepartment());
		this.getmBeanDepartment().update();

		Assert.assertNotNull(this.getmBeanDepartment().getDepartment());
		Assert.assertEquals("Departments are not equals.", this.getMyDepartment().getName(), this.getmBeanDepartment().getDepartment().getName());
	}
	
	@Test
	public void removeShouldSucceed() throws Exception{
		
		Mockito.doNothing().when(this.getDepartmentService()).remove(this.getMyDepartment());
		
		this.getmBeanDepartment().setDepartment(this.getMyDepartment());
		this.getmBeanDepartment().delete();
		
	}
	
	@Test
	public void removeShoulReturnThatExistsUsers(){
		
		Department department = this.getMyDepartment();
		department.setUserList(this.getMyUserList());
		
		this.getmBeanDepartment().setDepartment(department);
		this.getmBeanDepartment().delete();
		
	}
	
	@Test
	public void removeShouldThrowGenericError() throws Exception{
		Mockito.doThrow(Exception.class).when(this.getDepartmentService()).remove(this.getMyDepartment());
		
		this.getmBeanDepartment().setDepartment(this.getMyDepartment());
		this.getmBeanDepartment().delete();

	}
	
	@Test
	public void typeOperationAssociateShouldSucceed() throws Exception{
		
		List<User> userList = this.getMyUserList();
		userList.add(new User(2L, "Pamela", "Manager"));		
		
		Mockito.when(this.getUserService().list()).thenReturn(userList);
		Mockito.when(this.getUserService().findAllByDepartmentId(1L)).thenReturn(this.getMyUserList());
		
		this.getmBeanDepartment().typeOperationAssociate(this.getMyDepartment());
		
		Assert.assertNotNull(this.getmBeanDepartment().getUsers());
		Assert.assertFalse(this.getmBeanDepartment().getUsers().getSource().isEmpty());
		Assert.assertFalse(this.getmBeanDepartment().getUsers().getTarget().isEmpty());
	}
	
	@Test
	public void typeOperationAssociateShouldThrowGenericError() throws Exception{
		
		Mockito.doThrow(Exception.class).when(this.getUserService()).list();
		
		this.getmBeanDepartment().typeOperationAssociate(this.getMyDepartment());
		
	}
	
	@Test
	public void associateUserShouldSucceed() throws Exception{
		Mockito.when(this.getUserService().findAllByDepartmentId(1L)).thenReturn(this.getMyUserList());
		Mockito.doNothing().when(this.getUserService()).saveAll(this.getMyUserList());
		
		this.getmBeanDepartment().setDepartment(this.getMyDepartment());
		this.getmBeanDepartment().associateUser();
		
		
	}
	
	@Test
	public void associateUserShouldThrowGenericError() throws Exception{
		Mockito.doThrow(Exception.class).when(this.getUserService()).findAllByDepartmentId(1L);
		
		this.getmBeanDepartment().setDepartment(this.getMyDepartment());
		this.getmBeanDepartment().associateUser();
		
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	private List<Department> getMyDepartmentList(){
		
		List<Department> departmentList = new ArrayList<Department>(0);
		departmentList.add(this.getMyDepartment());
		return departmentList;
	}
	
	private Department getMyDepartment(){
		Department department = new Department();
		department.setId(1L);
		department.setName("Admin");
		return department;
	}
	
	private List<User> getMyUserList(){
		List<User> userList = new ArrayList<User>(0);
		userList.add(this.getMyUser());
		return userList;
	}
	
	private User getMyUser(){
		User user = new User();
		user.setId(1L);
		user.setName("Felipe");
		return user;
	}
	
	public MBeanDepartment getmBeanDepartment() {
		return mBeanDepartment;
	}

	public void setmBeanDepartment(MBeanDepartment mBeanDepartment) {
		this.mBeanDepartment = mBeanDepartment;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public RequestContext getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}
	
	

}
