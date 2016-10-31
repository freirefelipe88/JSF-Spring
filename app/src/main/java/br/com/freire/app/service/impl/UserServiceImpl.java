package br.com.freire.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.freire.app.model.User;
import br.com.freire.app.repository.UserRepository;
import br.com.freire.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findById(Long id) {
		return this.getUserRepository().findOne(id);
	}

	@Override
	public List<User> list() {
		return this.getUserRepository().findAll();
	}

	@Override
	public User save(User user) {
		return this.getUserRepository().save(user);
	}

	@Override
	public User update(User user) {
		return this.getUserRepository().save(user);
	}

	@Override
	public void remove(User user) {
		this.getUserRepository().delete(user.getId());
	}

	@Override
	public void saveAll(List<User> users) {
		this.getUserRepository().save(users);
		
	}
	
	@Override
	public List<User> findAllByPermissionId(Long id) {
		return this.getUserRepository().findAllByPermissionId(id);
	}
	
	@Override
	public List<User> findAllByDepartmentId(Long id) {
		return this.getUserRepository().findAllByDepartmentId(id);
	}

	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	


	
	
}
