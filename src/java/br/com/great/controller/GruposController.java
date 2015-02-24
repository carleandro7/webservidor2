/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.controller;

import br.com.great.GCMGoogle.EnviarMensagemGCM;
import br.com.great.dao.GruposDAO;
import br.com.great.dao.JogadoresDAO;
import br.com.great.model.Jogador;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 * Classe responsavel por fazer o controle entre os serviços oferecidos pelo jogos
 * no banco de dados com os eventos relacionados aos grupos dos jogos
 * @author carleandro
 */
public class GruposController {
    
    /**
     * Método responsável por listar todos os grupos de um jogo do banco
     * @param jogo_id ID do jogo
     * @return JSONArray Dados do grupo
     */
    public JSONArray getJogos(String jogo_id){
		System.out.println("Enviando para o GIT");
		return GruposDAO.getInstance().getTodos(jogo_id);
    }
     /**
     * Método responsável por adicionar jogador a um grupo se nao estiver cadastrado
     * @param jogo_id String
     * @param grupo_id String
     * @param jogador_id String
     * @return int se o jogador foi cadastrado return 1 ou se ja estiver return 2
     */
    public int setGrupoParticipando(int jogo_id, int grupo_id, int jogador_id){
	System.out.println("Enviando para o GIT");
        if(GruposDAO.getInstance().getGrupoParticipando(jogo_id, grupo_id, jogador_id)){
            return 2;  
        }else{
            return GruposDAO.getInstance().setGrupoParticipando(jogo_id, grupo_id, jogador_id);
        }
    }
    
    /**
     * Envia uma mensagem para todos os dipositivos que estão participando de um grupo
     * @param grupo_id int
     * @param mensagem mensagem que sera enviada para todos os dispositivos
     * @return boolean true se envou com sucesso
     */
    public boolean enviarMensagem(int grupo_id, String mensagem){
        ArrayList<Jogador> listJogador = new JogadoresDAO().getDeviceRegsID(grupo_id);
        List<String> regIdList = new ArrayList<String>();
        String user = "root";
        for (Jogador jogador : listJogador) {
            regIdList.add(jogador.getIddispositivo());
        }
        if(!regIdList.isEmpty()){
           new EnviarMensagemGCM().enviarParaDeviceBck(user,mensagem, regIdList);
           return true;
        }
        return true;
    } 
}

