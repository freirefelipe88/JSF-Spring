package br.com.freire.app.service;

import java.util.List;

import br.com.freire.app.model.Permission;

public interface PermissionService {

	public Permission save(Permission permission);
	
	public List<Permission> list();
	
	public Permission update(Permission permission);
	
	public void remove(Permission permission);

	public List<Permission> findAllByUserId(Long id);

	public Permission findById(Long id);

}
