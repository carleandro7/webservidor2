/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.controller;

import br.com.great.dao.TextosDAO;

/**
 *
 * @author carleandro
 */
public class TextosController {
    
    /**
     * Método responsável por set os dados da CTextos no banco de dados
     * @param texto String
     * @param jogador_id String
     * @param latitude String
     * @param longitude String
     * @param ctexto_id String
     * @return boolean
     */
    public boolean setCTexto(String texto, String jogador_id, String latitude, String longitude, String ctexto_id){
		System.out.println("Enviando para o GIT");
                return TextosDAO.getInstance().setCTexto(texto, jogador_id, latitude, longitude, ctexto_id);
    }
    
}
