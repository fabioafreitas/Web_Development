package soap.web.service;

import javax.jws.WebService;

@WebService(endpointInterface = "soap.web.service.ILogin")
public class Login implements ILogin {
	
	private String username;
	private String password;
	
	
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	@Override
	public boolean autenticar(String username, String password) {
		if(this.username.equals(username) && this.password.equals(password)) {
			return true;
		}
		return false;
	}

}