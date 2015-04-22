/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.controller;

import br.com.great.contexto.Jogador;
import br.com.great.dao.JogadoresDAO;
import java.util.ArrayList;

/**
 * Classe responsavel por fazer o controle entre os serviços oferecidos pelo jogos
 * no banco de dados com os eventos relacionados aos jogadores
 * @author carleandro
 */
public class JogadoresController {
    
    /**
     * Método responsável por listar todos os jogadores do banco
     * @return ArrayList jogador
     */
    public ArrayList<Jogador> listarTodos(){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().listarTodos();
    }

    /**
      * Método responsável por fazer o login do jogador
     * @param email String
     * @param password String
     * @return Jogador retorna os dados do jogador
     */
    public Jogador login(String email, String password){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().login(email, password);
    }

    /**
     * Método responsável por get em todos os dados do jogador
     * @param id String
     * @return Jogador todos dos dados do jogador
     */
    public Jogador getJogador(int id){
		System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().getJogador(id);
    }

    /**
     * Método responsável cadastrar um jogador
     * @param email String
     * @param password String
     * @return String true se cadastrado com sucesso
     */
    public int cadastrarJogador(String email, String password) {
        System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().cadastrar(email, password);
    }
    
      /**
     * Metodo responsavel por verificar e alterar se o usuario estiver com outro
     * dispositivo
     * @param jogador_id id do jogador
     * @param device_id id que idencifica o dispositivo atraves de msg GCM
     * @return String
     */
    public boolean registroDevice(int jogador_id, String device_id) {
        System.out.println("Enviando para o GIT");
		return JogadoresDAO.getInstance().registroDevice(jogador_id, device_id);
    }
    
}
