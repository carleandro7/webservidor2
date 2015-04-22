/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.controller;

import br.com.great.GCMGoogle.EnviarMensagemGCM;
import br.com.great.contexto.Jogador;
import br.com.great.contexto.Jogo;
import br.com.great.dao.ConfiMissaoDAO;
import br.com.great.dao.JogadoresDAO;
import br.com.great.dao.JogosDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

/**
 * Classe responsavel por fazer o controle entre os serviços oferecidos pelo jogos
 * no banco de dados com os eventos relacionados a cada jogo
 * @author carleandro
 */
public class JogosController {
    
    /**
     * Método responsável por listar todos os pjogos perto de um local com distancia definida
         *
         * @param latitude String
         * @param longitude String
         * @param distancia String
	 * @return JSONArray lista de jogos
     */
    public JSONArray getJogos(double latitude, double longitude,int distancia){
		System.out.println("Enviando para o GIT");
		return JogosDAO.getInstance().getJogos(latitude, longitude, distancia);
    }
    
    public Jogo getNewJogo(int jogo_id){
		System.out.println("Enviando para o GIT");
                Jogo jogo = JogosDAO.getInstance().getJogo(jogo_id);
                jogo.setConfiguracaoMissao(ConfiMissaoDAO.getInstance().getConfiMissao(jogo_id));
		return jogo;
    }

}
