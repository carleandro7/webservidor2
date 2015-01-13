/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.resource;

import br.com.controller.JogadoresController;
import br.com.controller.JogosController;
import java.io.File;

/**
 *
 * @author carleandro
 */
public class Executa {
    
    public static void main(String[] args) { 
       // File file = new File("/var/www/pervasiveed/app/webroot/img/academico.png");
       // System.out.println(""+file.exists());
        JogosController j = new JogosController();
        System.out.println(""+j.listarTodos());
        
    }
    
}
