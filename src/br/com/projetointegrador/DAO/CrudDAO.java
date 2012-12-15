package br.com.projetointegrador.DAO;

import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import android.util.Log;
import br.com.projetointegrador.TO.Crud;
import br.com.projetointegrador.TO.User;

public class CrudDAO extends WebService {
	private static final String URL = "http://192.168.173.1:1922/Services/CrudWS.asmx";
	
	public static Crud[] GetList(User user) {
		String MethodName = "Index";
		
		Hashtable<String , Object> parameters = new Hashtable<String , Object>();
		parameters.put("login" , user.Login);
		parameters.put("password" , user.Password);
		
		Object response = InvokeMethod(URL , MethodName , parameters);
		
		if (hasError(response)) {
			Log.d("GetList" , "Error on response");
			return null;
		}
		
		return RetrieveListFromSoap((SoapObject) response);
	}
	
	private static Crud RetrieveFromSoap(SoapObject soap) {
		Crud crud = new Crud();
		crud.CrudId = Integer.parseInt(soap.getProperty(0).toString());
		crud.Name = soap.getProperty(1).toString();
		
		return crud;
	}
	
	private static Crud[] RetrieveListFromSoap(SoapObject soap) {
		Crud[] Acrud = new Crud[soap.getPropertyCount()];
		
		for (int i = 0 ; i < Acrud.length ; i++) {
			SoapObject so_crud = (SoapObject) soap.getProperty(i);
			
			Crud crud = RetrieveFromSoap(so_crud);
			
			Acrud[i] = crud;
		}
		
		return Acrud;
	}
}
