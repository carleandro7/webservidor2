/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.management;

import br.com.great.model.Jogador;
import java.util.ArrayList;

/**
 *
 * @author carleandro
 */
public class EstadoJogador {
    private Jogador jogador;
    private ArrayList<String> mensagens = new ArrayList<String>();
    private int atualizarLocalizacao = 0;
    
    public ArrayList<String> getMensagens() {
        return mensagens;
    }

    public void setMensagens(ArrayList<String> mensagens) {
        this.mensagens = mensagens;
    }
    
    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getAtualizarLocalizacao() {
        return atualizarLocalizacao;
    }

    public void setAtualizarLocalizacao(int atualizarLocalizacao) {
        this.atualizarLocalizacao = atualizarLocalizacao;
    }

}
