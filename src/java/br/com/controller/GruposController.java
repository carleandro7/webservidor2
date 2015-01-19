/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.controller;

import br.com.dao.GruposDAO;
import java.io.File;
import org.json.JSONArray;

/**
 *
 * @author carleandro
 */
public class GruposController {
    
    /**
     *
     * @param jogo_id String
     * @return JSONArray
     */
    public JSONArray getJogos(String jogo_id){
		System.out.println("Enviando para o GIT");
		return GruposDAO.getInstance().getTodos(jogo_id);
    }
}
