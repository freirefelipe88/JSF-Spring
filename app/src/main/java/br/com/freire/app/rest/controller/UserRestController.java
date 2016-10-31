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
	public @ResponseBody List<User> list(){
		
		List<User> users = this.getUserService().list();
		
		return users;
		
	}
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public @ResponseBody User getPeopleForTeam(@PathVariable long userId) {
        return this.getUserService().findById(userId);
    }

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
