/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.controller;

import br.com.great.dao.FotosDAO;

/**
 * Classe responsavel por fazer o controle entre os serviços oferecidos pelo jogos
 * no banco de dados com os eventos relacionados as imagens
 * @author carleandro
 */
public class FotosController {
    
    /**
     * Método responsável por set os dados da CFotos no banco de dados
     * @param image String
     * @param jogador_id String
     * @param latitude String
     * @param longitude String
     * @param cfotos_id String
     * @return boolean
     */
    public boolean setCFoto(String image, String jogador_id, String latitude, String longitude, String cfotos_id){
		System.out.println("Enviando para o GIT");
                return FotosDAO.getInstance().setCFoto(image, jogador_id, latitude, longitude, cfotos_id);
    }
    
}
