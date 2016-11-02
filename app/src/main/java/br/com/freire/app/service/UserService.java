package br.com.freire.app.service;

import java.util.List;

import br.com.freire.app.model.User;

public interface UserService {

	public User findById(Long id) throws Exception ;
	
	public List<User> list() throws Exception;
	
	public User save(User user) throws Exception;
	
	public User update(User user) throws Exception;
	
	public void remove(User user) throws Exception;
	
	public void saveAll(List<User> users) throws Exception;

	public List<User> findAllByPermissionId(Long id) throws Exception;

	public List<User> findAllByDepartmentId(Long id) throws Exception;
	
	
}
