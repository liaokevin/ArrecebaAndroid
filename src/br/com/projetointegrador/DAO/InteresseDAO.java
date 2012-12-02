package br.com.projetointegrador.DAO;

import java.io.IOException;
import java.util.Hashtable;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.DataSoapSerializationEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Log;
import br.com.projetointegrador.TO.InteresseVector;
import br.com.projetointegrador.TO.User;
import br.com.projetointegrador.View.Interesse;

public class InteresseDAO extends WebService {
	private static final String URL = "http://10.0.2.2:1922/Services/CategoriaWS.asmx";
	
	public static Interesse[] GetList(User user) {
		String MethodName = "ListSubcategorias";
		
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
	
	public static boolean UpdatePreferences(User user , InteresseVector interesses) {
		String MethodName = "UpdateAndroidUserPreferences";
		
		InteresseVector.initMapping();
		
//		Hashtable<String , Object> parameters = new Hashtable<String , Object>();
//		parameters.put("login" , user.Login);
//		parameters.put("password" , user.Password);
//		parameters.put("interesses" , interesses);
//		
//		Object response = InvokeMethod(URL , MethodName , parameters);
		
		SoapObject aux = new SoapObject("System.Int32" , "interesses");
		for (int i = 0 ; i < interesses.size() ; i++) {
			aux.addProperty("int" , interesses.get(i));
		}
		
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		SoapObject request = new SoapObject(NAMESPACE , MethodName);
		
		request.addProperty("login" , user.Login);
		request.addProperty("password" , user.Password);
		request.addProperty("interesses" , aux);
		
		DataSoapSerializationEnvelope envelope = new DataSoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		
		try {
			androidHttpTransport.call(NAMESPACE + MethodName , envelope);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object response = null;
		try {
			response = envelope.getResponse();
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
