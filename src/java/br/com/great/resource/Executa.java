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
import br.com.great.util.FuncoesCalculo;
import java.io.File;
import static java.lang.Math.abs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class Executa {
    public double PI(){
        return 3.14159265;
    }
    /**
     *
     * @param args String
     */
    public static void main(String[] args)  { 
        
        double LatA = -3.739410;  
        double LngA = -38.572350; 
        double LatB = -3.744918;
        double LngB = -38.573329;
        System.out.println("resultado:"+new FuncoesCalculo().distanciaKM(LatA, LngA, LatB, LngB));
    }
    
}
