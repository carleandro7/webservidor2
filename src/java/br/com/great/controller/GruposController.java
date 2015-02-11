/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.controller;

import br.com.great.dao.GruposDAO;
import java.io.File;
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
     * @return boolean se o jogador foi cadastrado ou se ja estiver
     */
    public boolean setGrupoParticipando(int jogo_id, int grupo_id, int jogador_id){
	System.out.println("Enviando para o GIT");
        if(GruposDAO.getInstance().getGrupoParticipando(jogo_id, grupo_id, jogador_id)){
            return true;  
        }else{
            return GruposDAO.getInstance().setGrupoParticipando(jogo_id, grupo_id, jogador_id);
        }
    }
}

