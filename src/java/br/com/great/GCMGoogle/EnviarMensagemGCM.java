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
 * Classe responsavel por enviar mensagem GCM para os dispositivos dos jogos
 * @author carleandro
 */
public class EnviarMensagemGCM {
    
    /**
    * Método responsável por enviar mensagem para unico dispositivo
    *
     * @param user nome do jogador que esta enviando a mensagem
     * @param mensagem mensagem que será enviada
     * @param DEVICE_REGISTRATION_ID registro do dispositivo que vai receber a mensagem
    * @return boolean
    * @author Carleandro Noleto
    * @version 1.0
    */
    public boolean enviarManual(String user, String mensagem, String DEVICE_REGISTRATION_ID){
        return new EnviarMensagemManualParaDevice().enviarMensagem(user, mensagem, DEVICE_REGISTRATION_ID);
    }  
    
    /**
    * Método responsável por enviar mensagem para varios dispositivos
    *
     * @param user nome do jogador que esta enviando a mensagem
     * @param mensagem mensagem que será enviada
     * @param DEVICE_REGISTRATION_ID lista de registro dos dispositivos que vai receber a mensagem
    * @return boolean
    * @author Carleandro Noleto
    * @version 1.0
    */
    public MulticastResult enviarParaDevice(String user, String mensagem, List<String> DEVICE_REGISTRATION_ID){
        return new EnviarMensagemParaDevice().enviarMensagem(user,mensagem, DEVICE_REGISTRATION_ID);
    }

    /**
    * Método responsável por enviar mensagem para varios dispositivos
    *
     * @param user nome do jogador que esta enviando a mensagem
     * @param mensagem mensagem que será enviada
     * @param DEVICE_REGISTRATION_ID lista de registro dos dispositivos que vai receber a mensagem
    * @return boolean
    * @author Carleandro Noleto
    * @version 1.0
    */
    public MulticastResult enviarParaDeviceBck(String user, String mensagem, List<String> DEVICE_REGISTRATION_ID){
        return new EnviarMensagemParaDeviceBck().enviarMensagem(user,mensagem, DEVICE_REGISTRATION_ID);
    }
   
}
