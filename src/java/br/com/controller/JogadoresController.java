/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.controller;

import br.com.dao.JogadoresDAO;
import br.com.model.Jogador;
import java.util.ArrayList;

/**
 *
 * @author carleandro
 */
public class JogadoresController {
    
    public ArrayList<Jogador> listarTodos(){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().listarTodos();
    }
   public Jogador login(String email, String password){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().login(email, password);
    }
   public Jogador getJogador(String id){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().getJogador(id);
    }

    public String cadastrarJogador(String email, String password) {
        System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().cadastrar(email, password);
    }
}
