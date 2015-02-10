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
 *
 * @author carleandro
 */
public class GruposController {
    
    /**
     *
     * @param jogo_id
     * @return JSONArray
     */
    public JSONArray getJogos(String jogo_id){
		System.out.println("Enviando para o GIT");
		return GruposDAO.getInstance().getTodos(jogo_id);
    }
     /**
     *
     * @param jogo_id String
     * @param grupo_id String
     * @param jogador_id String
     * @return boolean
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

