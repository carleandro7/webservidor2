package br.com.great.GCMGoogle;

import static br.com.great.helpful.Constants.API_KEY;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * Classe responsavel por enviar mensagem GCM para um unico dispositivo
 * @author Carleandro
 * 
 */
public class EnviarMensagemManualParaDevice {

	
        /**
        * Envia uma mensagem para os dispositivos
        * @param user nome do usuario
        * @param mensagem Mensagem enviada para os dispositivos moveis  
        * @param DEVICE_REGISTRATION_ID  Lista de resgistro dos dispositivos exemplo: device1,device2
        * @return boolean
        **/
	public boolean enviarMensagem(String user, String mensagem, String DEVICE_REGISTRATION_ID){
            try{
		Map<String, String> params = new HashMap<String, String>();
		params.put("message", mensagem);
                params.put("user", user);
		post(API_KEY, DEVICE_REGISTRATION_ID, params );
                return true;
            }catch(Exception e){
                System.err.println("Error ao enviar mensagem GCM:"+e.getMessage());
            }
            return false;
	}

	/**
         * Faz POST no servidor do Google
          * @param apiKey
          * @param deviceRegistrationId
          * @param params
	 */
	private static String post(String apiKey, String deviceRegistrationId, Map<String, String> params) throws IOException {

		// Par�metros necess�rios para o POST
		StringBuilder postBody = new StringBuilder();
		postBody.append("registration_id").append("=").append(deviceRegistrationId);
		
		// Cria os par�metros chave=valor
		Set<String> keys = params.keySet();
		for (String key : keys) {
			String value = params.get(key);
			postBody.append("&").append("data.").append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
		}

		// Cria a mensagem
		byte[] postData = postBody.toString().getBytes("UTF-8");

		// Faz POST
		URL url = new URL("https://android.googleapis.com/gcm/send");
		HttpsURLConnection.setDefaultHostnameVerifier(new CustomizedHostnameVerifier());
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
		conn.setRequestProperty("Authorization", "key=" + apiKey);

		// L� a resposta
		OutputStream out = conn.getOutputStream();
		out.write(postData);
		out.close();

		int responseCode = conn.getResponseCode();
		if(responseCode == 200) {
			// OK
			String response = conn.getResponseMessage();
			return response;
		} else {
			System.err.println(responseCode + ": " + conn.getResponseMessage());
		}
		
		return null;
	}

	private static class CustomizedHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
}
