package br.com.freire.app.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.freire.app.model.User;
import br.com.freire.app.service.UserService;

@ManagedBean
@RequestScoped
public class UserConverter implements Converter {

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if (value != null && !"".equals(value)) {
				User user = this.getUserService().findById(Long.parseLong(value));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			User user = (User) value;
			return String.valueOf(user.getId());
		}
		return null;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
