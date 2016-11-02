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

import br.com.freire.app.model.Permission;
import br.com.freire.app.model.User;
import br.com.freire.app.model.UserHasPermissionPK;
import br.com.freire.app.model.UserHasPermissions;
import br.com.freire.app.service.PermissionService;
import br.com.freire.app.service.UserHasPermissionsService;
import br.com.freire.app.service.UserService;

@ManagedBean
@ViewScoped
public class MBeanPermission {

	@ManagedProperty(value = "#{permissionServiceImpl}")
	private PermissionService permissionService;

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	@ManagedProperty(value = "#{userHasPermissionsServiceImpl}")
	private UserHasPermissionsService userHasPermissionsService;

	private Permission permission;

	private List<Permission> permissionList;
	private List<Permission> filteredPermissionList;

	private DualListModel<User> users = new DualListModel<User>();

	private boolean renderGlobalMessage;
	private String header;

	@PostConstruct
	public void init() {
		this.reloadView();

	}

	private void reloadView() {
		try {
			this.setPermissionList(this.getPermissionService().list());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public boolean validateForm() {
		boolean isValid = true;

		if ("".equals(this.getPermission().getName())) {
			isValid = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Name field is mandatory!"));
		}

		return isValid;
	}

	public void typeOperationInsert() {
		this.setPermission(new Permission());
		this.setRenderGlobalMessage(false);
	}

	public void save() {
		try {
			if (this.validateForm()) {

				this.permission = this.getPermissionService().save(this.getPermission());
				this.setRenderGlobalMessage(true);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						String.format("Permission [%s] saved with success!", this.getPermission().getName())));
				RequestContext.getCurrentInstance().execute("PF('modalAddPermission').hide();");
				this.reloadView();
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public void typeOperationUpdate(Permission permission) {

		this.setHeader(String.format("Edit permission %s", permission.getName()));
		this.setPermission(permission);
		this.setRenderGlobalMessage(false);

	}

	public void update() {
		try {
			if (this.validateForm()) {
				this.permission = this.getPermissionService().update(this.getPermission());
				this.setRenderGlobalMessage(true);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						String.format("Permission [%s] updated with success!", this.getPermission().getName())));
				RequestContext.getCurrentInstance().execute("PF('modalUpdatePermission').hide();");
				this.reloadView();
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public void typeOperationDelete(Permission permission) {
		this.setRenderGlobalMessage(true);
		this.setPermission(permission);

	}

	public void delete() {
		try {
			this.getPermissionService().remove(this.getPermission());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					String.format("Permission [%s] removed with success!", this.getPermission().getName())));
			this.reloadView();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}
	}

	public void typeOperationAssociate(Permission permission) {
		try {
			this.setHeader(String.format("Associate users to permission %s", permission.getName()));
			this.setRenderGlobalMessage(false);
			this.setPermission(permission);

			List<User> usersSource = this.getUserService().list();
			List<User> usersTarget = new ArrayList<User>(0);

			users = new DualListModel<User>(usersSource, usersTarget);

			List<User> userList = this.getUserService().findAllByPermissionId(permission.getId());

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
			List<User> userList = this.getUsers().getTarget();

			this.getUserHasPermissionsService().deleteByPermission(this.getPermission());

			List<UserHasPermissions> userHasPermissionsList = new ArrayList<UserHasPermissions>(0);
			for (User user : userList) {

				UserHasPermissions userPermissions = new UserHasPermissions(
						new UserHasPermissionPK(user.getId(), this.getPermission().getId()));
				userPermissions.setUser(user);
				userPermissions.setPermission(this.getPermission());
				userHasPermissionsList.add(userPermissions);

			}

			if (!userHasPermissionsList.isEmpty()) {
				this.getUserHasPermissionsService().saveAll(userHasPermissionsList);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Association(s) made with success!"));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error happenned, please call the system administrator."));
		}

	}

	//////////////////////////////////////////////////////
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public boolean isRenderGlobalMessage() {
		return renderGlobalMessage;
	}

	public void setRenderGlobalMessage(boolean renderGlobalMessage) {
		this.renderGlobalMessage = renderGlobalMessage;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<Permission> getFilteredPermissionList() {
		return filteredPermissionList;
	}

	public void setFilteredPermissionList(List<Permission> filteredPermissionList) {
		this.filteredPermissionList = filteredPermissionList;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public DualListModel<User> getUsers() {
		return users;
	}

	public void setUsers(DualListModel<User> users) {
		this.users = users;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserHasPermissionsService getUserHasPermissionsService() {
		return userHasPermissionsService;
	}

	public void setUserHasPermissionsService(UserHasPermissionsService userHasPermissionsService) {
		this.userHasPermissionsService = userHasPermissionsService;
	}

}
