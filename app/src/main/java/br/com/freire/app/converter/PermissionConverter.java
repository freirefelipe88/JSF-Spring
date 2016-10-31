package br.com.freire.app.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.freire.app.model.Permission;
import br.com.freire.app.service.PermissionService;

@ManagedBean
@RequestScoped
public class PermissionConverter implements Converter{

	@ManagedProperty(value = "#{permissionServiceImpl}")
	private PermissionService permissionService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if(value != null && !"".equals(value)){
			Permission permission = this.getPermissionService().findById(Long.parseLong(value));
			return permission;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Permission permission = (Permission) value;
			return String.valueOf(permission.getId());
		}
		return null;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}


}
