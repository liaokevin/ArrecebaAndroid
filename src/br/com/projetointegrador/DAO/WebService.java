package br.com.projetointegrador.DAO;

import java.util.Enumeration;
import java.util.Hashtable;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebService {
	private static final String NAMESPACE = "http://tempuri.org/";
	protected static final String APPTOKEN = "49849FF2-DC74-45EF-ABE4-A0A0FB3A08BD";

	public static Object InvokeMethod(String URL, String MethodName) {
		return InvokeMethod(URL, MethodName, null);
	}

	public static Object InvokeMethod(String URL, String MethodName, Hashtable<String, Object> parameters) {
		SoapObject request = GetSoapObject(MethodName, parameters);
		SoapSerializationEnvelope envelope = GetEnvelope(request);
		return MakeCall(URL, envelope, NAMESPACE, MethodName);
	}

	public static SoapObject GetSoapObject(String MethodName, Hashtable<String, Object> parameters) {
		SoapObject so = new SoapObject(NAMESPACE, MethodName);
		
		if (parameters != null) {
			Enumeration<String> Estring = parameters.keys();
			
			while (Estring.hasMoreElements()) {
				String key = (String) Estring.nextElement();
				
				so.addProperty(key, parameters.get(key));
			}
		}
		
		return so;
	}

	public static SoapSerializationEnvelope GetEnvelope(SoapObject Soap) {
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(Soap);
		return envelope;
	}

	/**
	 * 
	 * @param URL
	 *            - The complete URL where the web service resides
	 * @param Envelope
	 *            - The envelope to be passed
	 * @param NAMESPACE
	 *            - The web method namespace
	 * @param METHOD_NAME
	 *            - The method name
	 * @return - SoapObject containing the resultset
	 */
	public static Object MakeCall(String URL, SoapSerializationEnvelope Envelope, String NAMESPACE, String METHOD_NAME) {
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
		Object obj = null;
		try {
			androidHttpTransport.call(NAMESPACE + METHOD_NAME, Envelope);
			obj = Envelope.getResponse();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return obj;
	}

	protected static boolean hasError(Object response) {
		if (response instanceof SoapFault) {
			return true;
		}
		return false;
	}
	
}
