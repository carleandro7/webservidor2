/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.resource;

import br.com.great.controller.JogadoresController;
import br.com.great.controller.JogosController;
import java.io.File;

/**
 *
 * @author carleandro
 */
public class Executa {
    
    /**
     *
     * @param args String
     */
    public static void main(String[] args) { 
       // File file = new File("/var/www/pervasiveed/app/webroot/img/academico.png");
       // System.out.println(""+file.exists());
        JogosController j = new JogosController();
        System.out.println(""+j.listarTodos());
        
    }
    
}
