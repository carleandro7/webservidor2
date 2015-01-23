package br.com.great.GCMGoogle;

import static br.com.great.util.Constants.API_KEY;
import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

/**
 * @author Ricardo Lecheta
 */
public class EnviarMensagemParaDevice {
	
	public boolean enviarMensagem(String mensagem, String DEVICE_REGISTRATION_ID){
            try{
		Sender sender = new Sender(API_KEY);
		Message message = new Message.Builder().collapseKey("mobileconf").addData("msg", mensagem).build();
		sender.send(message, DEVICE_REGISTRATION_ID, 5);
		System.out.println("Mensagem enviada: " + message.getData().get("msg"));
	  return true;
            }catch(Exception e){
                System.err.println("Error ao enviar mensagem GCM:"+e.getMessage());
            }
            return false;
	}
}