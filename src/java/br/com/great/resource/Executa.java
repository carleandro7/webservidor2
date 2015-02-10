/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.resource;

import br.com.great.controller.JogadoresController;
import br.com.great.controller.JogosController;
import br.com.great.gerenciamento.EstadoJogo;
import br.com.great.gerenciamento.ServidorJogo;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class Executa {
    
    /**
     *
     * @param args String
     */
    public static void main(String[] args) throws JSONException { 
       // File file = new File("/var/www/pervasiveed/app/webroot/img/academico.png");
       // System.out.println(""+file.exists());
        ServidorJogo.getInstance().StartServidor();
        JSONArray json = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("jogo_id", 2);
        obj.put("jogador_id", 1);
        obj.put("nome", "teste mil");
        json.put(obj);
        System.err.println("Erro em acao:"+ServidorJogo.getInstance().acao(2, 1, json).toString());
    }
    
}
