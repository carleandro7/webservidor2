package br.com.great.GCMGoogle;

import static br.com.great.util.Constants.API_KEY;
import java.io.IOException;

import br.com.great.gcm.server.Message;
import br.com.great.gcm.server.MulticastResult;
import br.com.great.gcm.server.Sender;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ricardo Lecheta
 */
public class EnviarMensagemParaDeviceBck {
	
	public MulticastResult enviarMensagem(String user, String mensagem, List<String> DEVICE_REGISTRATION_ID){
            try{
		Sender sender = new Sender(API_KEY);
                Map<String, String> params = new HashMap<String, String>();
		params.put("message", mensagem);
                params.put("user", user);
		Message message = new Message.Builder().collapseKey("mobileconf").setData(params).build();
                return sender.send(message, DEVICE_REGISTRATION_ID, 5);
            }catch(Exception e){
                System.err.println("Error ao enviar mensagem GCM:"+e.getMessage());
            }
            return null;
	}
}