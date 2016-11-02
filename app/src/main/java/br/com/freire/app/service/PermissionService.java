package br.com.freire.app.service;

import java.util.List;

import br.com.freire.app.model.Permission;

public interface PermissionService {

	public Permission save(Permission permission) throws Exception;
	
	public List<Permission> list() throws Exception;
	
	public Permission update(Permission permission) throws Exception;
	
	public void remove(Permission permission) throws Exception;

	public List<Permission> findAllByUserId(Long id) throws Exception;

	public Permission findById(Long id) throws Exception;

}
