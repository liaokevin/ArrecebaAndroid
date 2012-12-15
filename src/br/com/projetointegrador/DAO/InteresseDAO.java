package br.com.projetointegrador.DAO;

import java.util.ArrayList;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import android.util.Log;
import br.com.projetointegrador.TO.Interesse;
import br.com.projetointegrador.TO.User;

public class InteresseDAO extends WebService {
	private static final String URL = "http://192.168.173.1:1922/Services/CategoriaWS.asmx";
	
	public static Interesse[] GetList(User user) {
		String MethodName = "ListSubcategoriasAndPreferences";
		
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
	
	public static boolean UpdatePreferences(User user , ArrayList<Integer> interesses) {
		String MethodName = "UpdateAndroidUserPreferences";
		
		SoapObject aux = new SoapObject("System.Int32" , "interesses");
		for (int i : interesses) {
			aux.addProperty("int" , i);
		}
		
		Hashtable<String , Object> parameters = new Hashtable<String , Object>();
		parameters.put("login" , user.Login);
		parameters.put("password" , user.Password);
		parameters.put("interesses" , aux);
		
		Object response = InvokeMethod(URL , MethodName , parameters);
		
		if (hasError(response)) {
			Log.d("UpdatePreferences" , "Error on response");
			return false;
		}
		
		return response.toString() == "true";
	}
	
	private static Interesse RetrieveFromSoap(SoapObject soap) {
		Interesse Interesse = new Interesse();
		Interesse.InteresseId = Integer.parseInt(soap.getProperty(0).toString());
		Interesse.Nome = soap.getProperty(1).toString();
		Interesse.checked = Boolean.parseBoolean(soap.getProperty(3).toString());
		
		return Interesse;
	}
	
	private static Interesse[] RetrieveListFromSoap(SoapObject soap) {
		Interesse[] AInteresse = new Interesse[soap.getPropertyCount()];
		
		for (int i = 0 ; i < AInteresse.length ; i++) {
			SoapObject so_Interesse = (SoapObject) soap.getProperty(i);
			
			Interesse Interesse = RetrieveFromSoap(so_Interesse);
			
			AInteresse[i] = Interesse;
		}
		
		return AInteresse;
	}
}
