package br.com.great.contexto;

import java.util.ArrayList;

public class DadosPersonagem {

	private int vida = 100;

        private ArrayList<Objeto> objJogador;
        
	private Jogador jogador;

	private Jogo jogo;

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public ArrayList<Objeto> getObjJogador() {
        if(objJogador == null){
            objJogador = new ArrayList<Objeto>();
        }
        return objJogador;
    }

    public void setObjJogador(ArrayList<Objeto> objJogador) {
        this.objJogador = objJogador;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public void setLifeSoma(int pontos){
        this.vida = this.vida + pontos;
    }
        

}
