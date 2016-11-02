package br.com.freire.app.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.freire.app.model.User;
import br.com.freire.app.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> list() throws Exception{
		
		List<User> users = this.getUserService().list();
		
		return users;
		
	}
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public @ResponseBody User findById(@PathVariable long userId) throws Exception {
        User user = this.getUserService().findById(userId);
		
        return user;
    }
	
	@RequestMapping(value = "/saveUser/{name}", method = RequestMethod.GET)
	public @ResponseBody User save(@PathVariable String name) throws Exception{
		
		User user = new User();
		user.setName(name);
		
		return this.getUserService().save(user);
		
		
	}
	
	@RequestMapping(value = "/editUser/{id}/{name}/{description}")
	public @ResponseBody User edit(@PathVariable("id") long id, @PathVariable("name") String name, @PathVariable("description") String description) throws Exception{
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setDescription(description);
		
		return this.getUserService().save(user);
	}
	
	@RequestMapping(value = "/deleteUser/{id}")
	public @ResponseBody String remove(@PathVariable("id") long id) throws Exception{
		
		User user = this.getUserService().findById(id);
		this.getUserService().remove(user);
		
		return "User " + user.getName() + " removed with success!";
	}
	
	

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
