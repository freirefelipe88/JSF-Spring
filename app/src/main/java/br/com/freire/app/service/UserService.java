package br.com.freire.app.service;

import java.util.List;

import br.com.freire.app.model.User;

public interface UserService {

	public User findById(Long id);
	
	public List<User> list();
	
	public User save(User user);
	
	public User update(User user);
	
	public void remove(User user);
	
	public void saveAll(List<User> users);

	public List<User> findAllByPermissionId(Long id);

	public List<User> findAllByDepartmentId(Long id);
	
	
}
