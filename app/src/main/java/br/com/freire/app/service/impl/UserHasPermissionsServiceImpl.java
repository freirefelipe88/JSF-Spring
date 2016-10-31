package br.com.freire.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.freire.app.model.Permission;
import br.com.freire.app.model.User;
import br.com.freire.app.model.UserHasPermissions;
import br.com.freire.app.repository.UserHasPermissionsRepository;
import br.com.freire.app.service.UserHasPermissionsService;

@Service
public class UserHasPermissionsServiceImpl implements UserHasPermissionsService {

	@Autowired
	private UserHasPermissionsRepository userHasPermissionsRepository;
	
	@Override
	public void deleteByPermission(Permission permission) {
		this.getUserHasPermissionsRepository().deleteUserHasPermissionsByPermissionId(permission.getId());
	}
	
	@Override
	public void deleteByUser(User user) {
		this.getUserHasPermissionsRepository().deleteUserHasPermissionsByUserId(user.getId());
	}

	@Override
	public void saveAll(List<UserHasPermissions> userHasPermissionsList) {
		this.getUserHasPermissionsRepository().save(userHasPermissionsList);
	}

	public UserHasPermissionsRepository getUserHasPermissionsRepository() {
		return userHasPermissionsRepository;
	}

	public void setUserHasPermissionsRepository(UserHasPermissionsRepository userHasPermissionsRepository) {
		this.userHasPermissionsRepository = userHasPermissionsRepository;
	}

	
}
