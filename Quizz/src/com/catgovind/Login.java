package com.catgovind;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("login") 
@RequestScoped
public class Login {

    private String username;
	private String password;
	    
	    
	 public void login() {
	        
	        FacesContext context = FacesContext.getCurrentInstance();
	        
	        int resultado = BDExample.verificaUser(this.username, this.password);

	        if(resultado == 1){
	            context.getExternalContext().getSessionMap().put("user", username);
	            try {
					context.getExternalContext().redirect("Home.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        else  {
	            context.addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));

	        } 
	    }

	    public void logout() {
	    	FacesContext context = FacesContext.getCurrentInstance();
	    	context.getExternalContext().invalidateSession();
	        try {
				context.getExternalContext().redirect("Index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    
		 public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}
}
