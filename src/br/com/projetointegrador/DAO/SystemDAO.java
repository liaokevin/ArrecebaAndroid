package br.com.projetointegrador.DAO;

import java.util.Hashtable;

import org.ksoap2.serialization.SoapObject;

import br.com.projetointegrador.TO.User;

public class SystemDAO extends WebService {
	private static final String URL = "http://10.0.2.2:1922/Services/UserWS.asmx";

	public static boolean CreateUser(User user) {
		String MethodName = "Create";
		
		Hashtable<String, Object> parameters = new Hashtable<String, Object>();
		parameters.put("name", user.Name);
		parameters.put("login", user.Login);
		parameters.put("password", user.Password);
		parameters.put("email", user.Email);
		parameters.put("sexo", String.valueOf(user.Sexo));
		parameters.put("apptoken", APPTOKEN);
		
		Object response = InvokeMethod(URL, MethodName, parameters);
		return RetrieveBooleanFromSoap(response);
	}
	
	public static User Login(User user) {
		String MethodName = "Login";
		
		Hashtable<String, Object> parameters = new Hashtable<String, Object>();
		parameters.put("login", user.Login);
		parameters.put("password", user.Password);
		
		Object response = InvokeMethod(URL, MethodName, parameters);
		
		return RetrieveUserFromSoap(response);
	}

	private static boolean RetrieveBooleanFromSoap(Object response) {
		return Boolean.parseBoolean(response.toString());
	}
	
	private static User RetrieveUserFromSoap(Object response) {
		SoapObject soap = (SoapObject) response;
		
		User user = new User();
		user.UserId = Integer.parseInt(soap.getProperty(0).toString());
		user.Name = soap.getProperty(1).toString();
		user.Login = soap.getProperty(2).toString();
		user.Password = soap.getProperty(3).toString();
		user.Email = soap.getProperty(4).toString();
		user.Sexo = soap.getProperty(5).toString().charAt(0);
		
		return user;
	}
	
}
