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
public class MBeanUser {

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;
	
	@ManagedProperty(value = "#{permissionServiceImpl}")
	private PermissionService permissionService;

	@ManagedProperty(value = "#{userHasPermissionsServiceImpl}")
	private UserHasPermissionsService userHasPermissionsService;

	private User user;

	private List<User> userList;
	private List<User> filteredUserList;

	private DualListModel<Permission> permissions = new DualListModel<Permission>();

	private boolean renderGlobalMessage;
	private String header;

	@PostConstruct
	public void init() {
		this.reloadView();

	}

	private void reloadView() {
		this.setUserList(this.getUserService().list());
	}

	public boolean validateForm() {
		boolean isValid = true;

		if ("".equals(this.getUser().getName())) {
			isValid = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Name field is mandatory!"));
		}

		return isValid;
	}

	public void typeOperationInsert() {
		this.setUser(new User());
		this.setRenderGlobalMessage(false);
	}

	public void save() {
		if (this.validateForm()) {

			this.user = this.getUserService().save(this.getUser());
			this.setRenderGlobalMessage(true);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(String.format("User [%s] saved with success!", this.getUser().getName())));
			RequestContext.getCurrentInstance().execute("PF('modalAddUser').hide();");
			this.reloadView();
		}
	}

	public void typeOperationUpdate(User user) {

		this.setHeader(String.format("Edit user %s", user.getName()));
		this.setUser(user);
		this.setRenderGlobalMessage(false);

	}

	public void update() {

		if (this.validateForm()) {
			this.user = this.getUserService().update(this.getUser());
			this.setRenderGlobalMessage(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					String.format("User [%s] updated with success!", this.getUser().getName())));
			RequestContext.getCurrentInstance().execute("PF('modalUpdateUser').hide();");
			this.reloadView();
		}

	}

	public void typeOperationDelete(User user) {
		this.setRenderGlobalMessage(true);
		this.setUser(user);

	}

	public void delete() {
		this.getUserService().remove(this.getUser());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				String.format("User [%s] removed with success!", this.getUser().getName())));
		this.reloadView();
	}

	public void typeOperationAssociate(User user) {
		this.setHeader(String.format("Associate users to user %s", user.getName()));
		this.setRenderGlobalMessage(false);
		this.setUser(user);

		List<Permission> permissionsSource = this.getPermissionService().list();
		List<Permission> permissionsTarget = new ArrayList<Permission>(0);

		permissions = new DualListModel<Permission>(permissionsSource, permissionsTarget);

		List<Permission> permissionList = this.getPermissionService().findAllByUserId(user.getId());

		this.getPermissions().setTarget(permissionList);

		for (Permission permission : permissionList) {
			if (this.getPermissions().getSource().contains(permission)) {
				this.getPermissions().getSource().remove(permission);
			}
		}

	}

	public void associateUser() {
		List<Permission> permissionList = this.getPermissions().getTarget();

		this.getUserHasPermissionsService().deleteByUser(this.getUser());

		List<UserHasPermissions> userHasPermissionsList = new ArrayList<UserHasPermissions>(0);
		for (Permission permission : permissionList) {

			UserHasPermissions userPermissions = new UserHasPermissions(
					new UserHasPermissionPK(this.getUser().getId(), permission.getId()));
			userPermissions.setUser(this.getUser());
			userPermissions.setPermission(permission);
			userHasPermissionsList.add(userPermissions);

		}

		if (!userHasPermissionsList.isEmpty()) {
			this.getUserHasPermissionsService().saveAll(userHasPermissionsList);
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Association(s) made with success!"));

	}

	//////////////////////////////////////////////
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getFilteredUserList() {
		return filteredUserList;
	}

	public void setFilteredUserList(List<User> filteredUserList) {
		this.filteredUserList = filteredUserList;
	}

	public DualListModel<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(DualListModel<Permission> permissions) {
		this.permissions = permissions;
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

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

}
