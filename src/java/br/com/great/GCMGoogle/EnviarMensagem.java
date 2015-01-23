/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.GCMGoogle;

/**
 *
 * @author carleandro
 */
public class EnviarMensagem {
    
    public boolean enviarManual(String mensagem, String DEVICE_REGISTRATION_ID){
        return new EnviarMensagemManualParaDevice().enviarMensagem(mensagem, DEVICE_REGISTRATION_ID);
    }  
        
    public boolean enviarParaDevice(String mensagem, String DEVICE_REGISTRATION_ID){
        return new EnviarMensagemManualParaDevice().enviarMensagem(mensagem, DEVICE_REGISTRATION_ID);
    }  
    
    
}
