package com.example.android.drjardin.modelo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Santiago Celada González on 18/03/2017.
 */

public class ServicioWeb {

    private static String METHOD_NAME;
    private static final String NAMESPACE = "http://WebServiceDr/";
    private static String SOAP_ACTION;
    private static final String URL = "http://192.168.43.232:8080/DrJardinEA-war/DrJardinWebServices?WSDL";
    private SoapPrimitive response = null;

    public ServicioWeb(String metodo){
        METHOD_NAME = metodo;
        SOAP_ACTION = NAMESPACE + METHOD_NAME;
    }

    public SoapPrimitive llamadaServicio(){
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.setOutputSoapObject(request);

            HttpTransportSE http = new HttpTransportSE(URL);
            http.call(SOAP_ACTION, envelope);

            response = (SoapPrimitive) envelope.getResponse();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public SoapPrimitive llamadaServicio(String mensaje){
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("persona", mensaje);
            final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.setOutputSoapObject(request);
            HttpTransportSE http = new HttpTransportSE(URL);
            http.call(SOAP_ACTION, envelope);

            response = (SoapPrimitive) envelope.getResponse();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}
