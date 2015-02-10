/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.controller;

import br.com.great.GCMGoogle.EnviarMensagemGCM;
import br.com.great.dao.JogadoresDAO;
import br.com.great.dao.JogosDAO;
import br.com.great.model.Jogador;
import br.com.great.model.Jogo;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Jogador> listJogador = new JogadoresDAO().getDeviceRegsID(jogo_id);
        List<String> regIdList = new ArrayList<String>();
        String user = "user";
        for (Jogador jogador : listJogador) {
            if (!jogador_id.equals(String.valueOf(jogador.getId()))) {
                regIdList.add(jogador.getIddispositivo());
            } else {
                user = jogador.getEmail();
            }
        }
        if(!regIdList.isEmpty()){
           new EnviarMensagemGCM().enviarParaDeviceBck(user,mensagem, regIdList);
           return true;
        }
        return true;
    } 
    
    public Jogo setNewJogo(int jogo_id, int jogador_id, String nome) {
        return JogosDAO.getInstance().setNewJogo(jogo_id, jogador_id, nome);
    }

    public ArrayList<Jogo> getJogosExecutando() {
        return JogosDAO.getInstance().getJogosExecutando();
    }
}
