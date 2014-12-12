/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.controller;

import br.com.dao.JogosDAO;

import org.json.JSONArray;

/**
 *
 * @author carleandro
 */
public class JogosController {
    
    public JSONArray listarTodos(){
		System.out.println("Enviando para o GIT");
		return JogosDAO.getInstance().listarTodos();
    }
    public JSONArray getJogos(String latitude, String longitude,String distancia){
		System.out.println("Enviando para o GIT");
		return JogosDAO.getInstance().getJogos(latitude, longitude, distancia);
    }
    
    public JSONArray getDadosIniciais(String jogo_id){
		System.out.println("Enviando para o GIT");
		return JogosDAO.getInstance().getDadosIniciais(jogo_id);
    }
    
}
