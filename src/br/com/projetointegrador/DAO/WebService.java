package br.com.projetointegrador.DAO;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebService {
	private static final String NAMESPACE = "http://tempuri.org/";

	public static SoapObject InvokeMethod(String URL, String MethodName) {
		SoapObject request = GetSoapObject(MethodName);
		SoapSerializationEnvelope envelope = GetEnvelope(request);
		return MakeCall(URL, envelope, NAMESPACE, MethodName);
	}

	public static SoapObject GetSoapObject(String MethodName) {
		SoapObject so = new SoapObject(NAMESPACE, MethodName);
		so.addProperty("login", "admin");
		so.addProperty("password", "123");
		
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
	public static SoapObject MakeCall(String URL, SoapSerializationEnvelope Envelope, String NAMESPACE, String METHOD_NAME) {
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
		Object obj = null;
		try {
			androidHttpTransport.call(NAMESPACE + METHOD_NAME, Envelope);
			obj = Envelope.getResponse();
		} catch (Exception e) {
			e.printStackTrace();

		}
		SoapObject response = (SoapObject) obj;
		return response;
	}

}
