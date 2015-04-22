/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import br.com.great.contexto.CapturarObjeto;
import br.com.great.contexto.Engajamento;
import br.com.great.contexto.Foto;
import br.com.great.contexto.IrLocal;
import br.com.great.contexto.Jogador;
import br.com.great.contexto.Jogo;
import br.com.great.contexto.MecanicaComposta;
import br.com.great.contexto.Missao;
import br.com.great.contexto.Objeto;
import br.com.great.contexto.Som;
import br.com.great.contexto.Texto;
import br.com.great.contexto.Video;
import java.util.ArrayList;

/**
 *
 * @author carleandro
 */
public class PlayJogo {

    private static ArrayList<Jogador> jogadores;
    private static ArrayList<Jogo> jogos;
    private static ArrayList<Missao> missoes;
    private static ArrayList<Texto> mecTextos;
    private static ArrayList<Video> mecVideos;
    private static ArrayList<Som> mecSons;
    private static ArrayList<Foto> mecFotos;
    private static ArrayList<IrLocal> irlocais;
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

    public static ArrayList<Texto> getMecTextos() {
        if (mecTextos == null) {
            mecTextos = new ArrayList<Texto>();
        }
        return mecTextos;
    }

    public static ArrayList<Video> getMecVideos() {
        if (mecVideos == null) {
            mecVideos = new ArrayList<Video>();
        }
        return mecVideos;
    }

    public static ArrayList<Som> getMecSons() {
        if (mecSons == null) {
            mecSons = new ArrayList<Som>();
        }
        return mecSons;
    }

    public static ArrayList<Foto> getMecFotos() {
        if (mecFotos == null) {
            mecFotos = new ArrayList<Foto>();
        }
        return mecFotos;
    }

    public static ArrayList<Missao> getMissoes() {
        if (missoes == null) {
            missoes = new ArrayList<Missao>();
        }
        return missoes;
    }

    public static ArrayList<IrLocal> getIrlocais() {
        if (irlocais == null) {
            irlocais = new ArrayList<IrLocal>();
        }
        return irlocais;
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
