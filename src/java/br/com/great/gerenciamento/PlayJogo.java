/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import br.com.great.contexto.Engajamento;
import br.com.great.contexto.Jogador;
import br.com.great.contexto.Jogo;
import br.com.great.contexto.MecanicaComposta;
import br.com.great.contexto.MecanicaSimples;
import br.com.great.contexto.Missao;
import java.util.ArrayList;

/**
 *
 * @author carleandro
 */
public class PlayJogo {

    private static ArrayList<Jogador> jogadores;
    private static ArrayList<Jogo> jogos;
    private static ArrayList<Missao> missoes;
    private static ArrayList<MecanicaSimples> mecSimples;
    private static ArrayList<MecanicaComposta> mecCompostas;
    private static ArrayList<Engajamento> engajamento;

    public static ArrayList<Jogador> getJogadores() {
        if (jogadores == null) {
            jogadores = new ArrayList<Jogador>();
        }
        return jogadores;
    }

    public static void setJogadores(ArrayList<Jogador> jogadores) {
        PlayJogo.jogadores = jogadores;
    }

    public static ArrayList<Jogo> getJogos() {
        if (jogos == null) {
            jogos = new ArrayList<Jogo>();
        }
        return jogos;
    }

    public static ArrayList<MecanicaSimples> getMecSimples() {
        if (mecSimples == null) {
            mecSimples = new ArrayList<MecanicaSimples>();
        }
        return mecSimples;
    }

    public static void setMecSimples(ArrayList<MecanicaSimples> mecSimples) {
        PlayJogo.mecSimples = mecSimples;
    }


    public static ArrayList<Missao> getMissoes() {
        if (missoes == null) {
            missoes = new ArrayList<Missao>();
        }
        return missoes;
    }

    public static ArrayList<MecanicaComposta> getMecCompostas() {
        if (mecCompostas == null) {
            mecCompostas = new ArrayList<MecanicaComposta>();
        }
        return mecCompostas;
    }

    public static ArrayList<Engajamento> getEngajamento() {
        if (engajamento == null) {
            engajamento = new ArrayList<Engajamento>();
        }
        return engajamento;
    }

    public static void setEngajamento(ArrayList<Engajamento> engajamento) {
        PlayJogo.engajamento = engajamento;
    }

}
