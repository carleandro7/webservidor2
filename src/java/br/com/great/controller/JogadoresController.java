/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.controller;

import br.com.great.dao.JogadoresDAO;
import br.com.great.model.Jogador;
import java.util.ArrayList;

/**
 *
 * @author carleandro
 */
public class JogadoresController {
    
    /**
     *
     * @return ArrayList jogador
     */
    public ArrayList<Jogador> listarTodos(){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().listarTodos();
    }

    /**
     *
     * @param email String
     * @param password String
     * @return Jogador
     */
    public Jogador login(String email, String password){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().login(email, password);
    }

    /**
     *
     * @param id String
     * @return Jogador
     */
    public Jogador getJogador(String id){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().getJogador(id);
    }

    /**
     *
     * @param email String
     * @param password String
     * @return String
     */
    public String cadastrarJogador(String email, String password) {
        System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().cadastrar(email, password);
    }
}
