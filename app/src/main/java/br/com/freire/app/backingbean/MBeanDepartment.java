package br.com.freire.app.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import br.com.freire.app.model.Department;
import br.com.freire.app.model.User;
import br.com.freire.app.service.DepartmentService;
import br.com.freire.app.service.UserService;

@ManagedBean
@ViewScoped
public class MBeanDepartment {

	@ManagedProperty(value = "#{departmentServiceImpl}")
	private DepartmentService departmentService;

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	private Department department;

	private List<Department> departmentList;
	private List<Department> filteredDepartmentList;

	private DualListModel<User> users = new DualListModel<User>();
	private boolean renderGlobalMessage;
	private String header;

	@PostConstruct
	public void init() {
		this.reloadView();
	}

	private void reloadView() {
		try {
			this.setDepartmentList(this.getDepartmentService().list());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public boolean validateForm() {
		boolean isValid = true;

		if ("".equals(this.getDepartment().getName())) {
			isValid = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Name field is mandatory!"));
		}

		return isValid;
	}

	public void typeOperationInsert() {
		this.setDepartment(new Department());
		this.setRenderGlobalMessage(false);
	}

	public void save() {
		try {
			if (this.validateForm()) {

				this.department = this.getDepartmentService().save(this.getDepartment());
				this.setRenderGlobalMessage(true);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						String.format("Department [%s] saved with success!", this.getDepartment().getName())));
				RequestContext.getCurrentInstance().execute("PF('modalAddDepartment').hide();");
				this.reloadView();
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public void typeOperationUpdate(Department department) {

		this.setHeader(String.format("Edit Department %s", department.getName()));
		this.setDepartment(department);
		this.setRenderGlobalMessage(false);

	}

	public void update() {
		try {
			if (this.validateForm()) {
				this.department = this.getDepartmentService().update(this.getDepartment());
				this.setRenderGlobalMessage(true);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						String.format("Department [%s] updated with success!", this.getDepartment().getName())));
				RequestContext.getCurrentInstance().execute("PF('modalUpdateDepartment').hide();");
				this.reloadView();
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public void typeOperationDelete(Department department) {
		this.setRenderGlobalMessage(true);
		this.setDepartment(department);

	}

	public void delete() {
		try {
			if (this.getDepartment().getUserList() != null && !this.getDepartment().getUserList().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						String.format("Department [%s]  can't be removed,  There are users associated with this department!", this.getDepartment().getName())));
			} else {
				this.getDepartmentService().remove(this.getDepartment());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						String.format("Department [%s] removed with success!", this.getDepartment().getName())));
				this.reloadView();

			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public void typeOperationAssociate(Department department) {
		try {
			this.setHeader(String.format("Associate users to department %s", department.getName()));
			this.setRenderGlobalMessage(false);
			this.setDepartment(department);

			List<User> usersSource = this.getUserService().list();
			List<User> usersTarget = new ArrayList<User>(0);

			users = new DualListModel<User>(usersSource, usersTarget);

			List<User> userList = this.getUserService().findAllByDepartmentId(this.getDepartment().getId());

			this.getUsers().setTarget(userList);

			for (User user : userList) {
				if (this.getUsers().getSource().contains(user)) {
					this.getUsers().getSource().remove(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public void associateUser() {
		try {
			List<User> users = this.getUserService().findAllByDepartmentId(this.getDepartment().getId());

			for (User user : users) {
				user.setDepartment(null);
			}

			this.getUserService().saveAll(users);

			List<User> userList = this.getUsers().getTarget();

			for (User user : userList) {
				user.setDepartment(this.getDepartment());
			}

			this.getUserService().saveAll(userList);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Association(s) made with success!"));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	//////////////////////////////////////////////////////////////////////
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Department> getFilteredDepartmentList() {
		return filteredDepartmentList;
	}

	public void setFilteredDepartmentList(List<Department> filteredDepartmentList) {
		this.filteredDepartmentList = filteredDepartmentList;
	}

	public DualListModel<User> getUsers() {
		return users;
	}

	public void setUsers(DualListModel<User> users) {
		this.users = users;
	}

	public boolean isRenderGlobalMessage() {
		return renderGlobalMessage;
	}

	public void setRenderGlobalMessage(boolean renderGlobalMessage) {
		this.renderGlobalMessage = renderGlobalMessage;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
