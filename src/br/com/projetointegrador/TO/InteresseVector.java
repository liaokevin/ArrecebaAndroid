package br.com.projetointegrador.TO;

import org.ksoap2.serialization.DataSoapObject;

public class InteresseVector extends DataSoapObject {
	
	private static final String DATA_NAMESPACE = "http://tempuri.org/";
	private static final String CLASS_NAME = "InteresseVector";
	
	public int[] list;
	
	public static void initMapping() {
		System.setProperty(CLASS_NAME , "br.com.projetointegrador.TO.InteresseVector");
	}
	
	public InteresseVector() {
		super(DATA_NAMESPACE , CLASS_NAME);
	}
	
	public int size() {
		return list.length;
	}
	
	public int get(int i) {
		return list[i];
	}
	
	public String toString() {
		String tmp = "esami= { ";
		if (list != null) {
			for (int curr : list)
				tmp += curr;
		}
		tmp += "}";
		return tmp;
	}
	
}
