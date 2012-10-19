package br.com.projetointegrador.DAO;

import org.ksoap2.serialization.SoapObject;

import br.com.projetointegrador.TO.Crud;

public class CrudDAO extends WebService {
	private static final String URL = "http://10.0.2.2:1922/Services/CrudWS.asmx";

	public static Crud[] GetList() {
		String MethodName = "Index";
		SoapObject response = InvokeMethod(URL, MethodName);
		return RetrieveListFromSoap(response);
	}


	private static Crud RetrieveFromSoap(SoapObject soap) {
		Crud crud = new Crud();
		crud.CrudId = Integer.parseInt(soap.getProperty(0).toString());
		crud.Name = soap.getProperty(1).toString();
		
		return crud;
	}

	private static Crud[] RetrieveListFromSoap(SoapObject soap) {
		Crud[] Acrud = new Crud[soap.getPropertyCount()];
		
		for (int i = 0; i < Acrud.length; i++) {
			SoapObject so_crud = (SoapObject) soap.getProperty(i);
			
			Crud crud = RetrieveFromSoap(so_crud);
			
			Acrud[i] = crud;
		}
		
		return Acrud;
	}
}
