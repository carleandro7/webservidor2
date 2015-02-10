/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.GCMGoogle;

import br.com.great.gcm.server.MulticastResult;
import java.util.List;

/**
 *
 * @author carleandro
 */
public class EnviarMensagemGCM {
    
    public boolean enviarManual(String user, String mensagem, String DEVICE_REGISTRATION_ID){
        return new EnviarMensagemManualParaDevice().enviarMensagem(user, mensagem, DEVICE_REGISTRATION_ID);
    }  
        
    public MulticastResult enviarParaDevice(String user, String mensagem, List<String> DEVICE_REGISTRATION_ID){
        return new EnviarMensagemParaDevice().enviarMensagem(user,mensagem, DEVICE_REGISTRATION_ID);
    }

    public MulticastResult enviarParaDeviceBck(String user, String mensagem, List<String> DEVICE_REGISTRATION_ID){
        return new EnviarMensagemParaDeviceBck().enviarMensagem(user,mensagem, DEVICE_REGISTRATION_ID);
    }
   
}
