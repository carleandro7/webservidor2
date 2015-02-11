/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.resource;

import br.com.great.controller.JogadoresController;
import br.com.great.controller.JogosController;
import br.com.great.dao.JogadoresDAO;
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
    public static void main(String[] args)  { 
        System.err.println(
        JogadoresDAO.getInstance().getTodosArquivos(12, "-3.705664919418148", "-38.55842011796876").toString()
        );
    }
    
}
