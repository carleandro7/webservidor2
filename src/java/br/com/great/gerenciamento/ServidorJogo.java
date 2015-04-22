/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import org.json.JSONArray;

/**
 *
 * @author carleandro
 */
public class ServidorJogo {

    private Thread servidor;
    private GerenciadorJogos gerJogos;

    private static ServidorJogo instance;

    /**
     *
     * Método responsável por criar uma instancia da classe ServidorJogo
     * (Singleton)
     *
     * @author Carleandro Noleto
     * @return static
     * @since 14/01/2015
     * @version 1.0
     */
    public static ServidorJogo getInstance() {
        if (instance == null) {
            instance = new ServidorJogo();
        }
        return instance;
    }

    public boolean StartServidor() {
        if (servidor == null) {
            gerJogos = new GerenciadorJogos();
            gerJogos.iniciar();
            //servidor = new Thread(gerJogos);
            //servidor.start();
        }
        return true;
    }

    public boolean StopServidor() {
        if (servidor != null) {
            //servidor.interrupt();     
            gerJogos = null;
        }
        //servidor.stop();
        servidor = null;
        return true;
    }

    public JSONArray acao(JSONArray json) {
        return gerJogos.acao(json);
    }
}
