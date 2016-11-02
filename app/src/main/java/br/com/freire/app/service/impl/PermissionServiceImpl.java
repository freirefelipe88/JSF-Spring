package br.com.freire.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.freire.app.model.Permission;
import br.com.freire.app.repository.PermissionRepository;
import br.com.freire.app.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public Permission save(Permission permission) {
		return this.getPermissionRepository().save(permission);
	}
	
	@Override
	public List<Permission> list() {
		return this.getPermissionRepository().findAll();
	}
	
	@Override
	public Permission update(Permission permission) {
		return this.getPermissionRepository().save(permission);
	}
	
	@Override
	public void remove(Permission permission) {
		this.getPermissionRepository().delete(permission.getId());
	}

	@Override
	public List<Permission> findAllByUserId(Long id) throws Exception {
		return this.getPermissionRepository().findAllByUserId(id);
	}
	
	@Override
	public Permission findById(Long id) {
		return this.getPermissionRepository().findOne(id);
	}
	
	public PermissionRepository getPermissionRepository() {
		return permissionRepository;
	}

	public void setPermissionRepository(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

}
