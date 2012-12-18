package br.com.projetointegrador.DAO;

import java.math.BigDecimal;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import android.util.Log;
import br.com.projetointegrador.TO.Produto;
import br.com.projetointegrador.TO.User;

public class ProdutoDAO extends WebService {
	private static final String URL = "http://192.168.43.5/ArrecebaWS/Services/ProdutoWS.asmx";
	
	public static Produto[] GetList(User user) {
		String MethodName = "ListByUserPreferences";
		
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
	
	private static Produto RetrieveFromSoap(SoapObject soap) {
		Produto produto = new Produto();
		produto.ProdutoId = Integer.parseInt(soap.getProperty(0).toString());
		produto.Nome = soap.getProperty(1).toString();
		produto.Descricao = soap.getProperty(2).toString();
		produto.Preco = new BigDecimal(soap.getProperty(3).toString());
		produto.URL = soap.getProperty(4).toString();
		produto.Foto = soap.getProperty(5).toString();
		if (produto.Foto.equals("anyType{}") || produto.Foto.equals(""))
			produto.Foto = null;
		
		return produto;
	}
	
	private static Produto[] RetrieveListFromSoap(SoapObject soap) {
		Produto[] Aproduto = new Produto[soap.getPropertyCount()];
		
		for (int i = 0 ; i < Aproduto.length ; i++) {
			SoapObject so = (SoapObject) soap.getProperty(i);
			
			Aproduto[i] = RetrieveFromSoap(so);
		}
		
		return Aproduto;
	}
}
