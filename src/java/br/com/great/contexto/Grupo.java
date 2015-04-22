package br.com.great.contexto;

import java.util.ArrayList;
import java.util.Collection;

public class Grupo {

    private String nome;
    private int id;

    private Collection<Engajamento> engajamento;

    private Collection<ConfiguracaoMissao> configuracaoMissao;

    private ArrayList<Jogador> jogadores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Engajamento> getEngajamento() {
        return engajamento;
    }

    public void setEngajamento(Collection<Engajamento> engajamento) {
        this.engajamento = engajamento;
    }

    public Collection<ConfiguracaoMissao> getConfiguracaoMissao() {
        return configuracaoMissao;
    }

    public void setConfiguracaoMissao(Collection<ConfiguracaoMissao> configuracaoMissao) {
        this.configuracaoMissao = configuracaoMissao;
    }
    
    public ArrayList<Jogador> getJogadores() {
        if (jogadores == null) {
            jogadores = new ArrayList<Jogador>();
        }
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public boolean getJogador(int jogador_id) {
        if (jogadores != null) {
            for (int i = 0; i < jogadores.size(); i++) {
                if (jogadores.get(i).getId() == jogador_id) {
                    return true;
                }
            }
        }
        return false;
    }

}
