package br.com.freire.app.service;

import java.util.List;

import br.com.freire.app.model.Permission;
import br.com.freire.app.model.User;
import br.com.freire.app.model.UserHasPermissions;

public interface UserHasPermissionsService {

	public void deleteByPermission(Permission permission);

	public void deleteByUser(User user);
	
	public void saveAll(List<UserHasPermissions> userHasPermissionsList);
}
