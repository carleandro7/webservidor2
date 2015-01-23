/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.controller;

import br.com.great.GCMGoogle.EnviarMensagem;
import br.com.great.GCMGoogle.EnviarMensagemManualParaDevice;
import br.com.great.dao.JogadoresDAO;
import br.com.great.dao.JogosDAO;
import br.com.great.model.Jogador;
import java.util.ArrayList;

import org.json.JSONArray;

/**
 *
 * @author carleandro
 */
public class JogosController {
    
    /**
     *
     * @return JSONArray
     */
    public JSONArray listarTodos(){
		System.out.println("Enviando para o GIT");
		return JogosDAO.getInstance().listarTodos();
    }

    /**
     *
     * @param latitude String
     * @param longitude String
     * @param distancia String
     * @return JSONArrau
     */
    public JSONArray getJogos(String latitude, String longitude,String distancia){
		System.out.println("Enviando para o GIT");
		return JogosDAO.getInstance().getJogos(latitude, longitude, distancia);
    }
    /**
     *
     * @param jogo_id String
     * @return JSONArrya
     */
    public JSONArray getDadosIniciais(String jogo_id){
		System.out.println("Enviando para o GIT");
		return JogosDAO.getInstance().getDadosIniciais(jogo_id);
    }
    /**
     * Envia uma mensagem para todos os dipositivos que estão participando do jogo
     * @param jogo_id String
     * @param mensagem mensagem que sera enviada para todos os dispositivos
     * @param jogador_id id do jogador que enviou a mensagem
     * @return boolean
     */
    public boolean enviarMensagem(String jogo_id, String mensagem, String jogador_id){
        ArrayList<Jogador> j = new JogadoresDAO().getDeviceRegsID(jogo_id);
        String resgDeviceID ="";
        for(int i=0; i<j.size(); i++){
           if(!jogador_id.equals(String.valueOf(j.get(i).getId()))){
                if((i+1)!=j.size()){
                    resgDeviceID +=j.get(i).getIddispositivo()+",";
                }else{
                    resgDeviceID +=j.get(i).getIddispositivo();
                }
           }
        }
        System.err.println("regs "+resgDeviceID);
        if(!resgDeviceID.equals(""))
            return new EnviarMensagem().enviarManual(mensagem, resgDeviceID);
        else 
            return true;
    } 
}
