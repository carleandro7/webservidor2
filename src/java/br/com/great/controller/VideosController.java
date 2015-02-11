/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.controller;

import br.com.great.dao.VideosDAO;

/**
 *
 * @author carleandro
 */
public class VideosController {

    /**
     * Método responsável por set os dados da CSons no banco de dados
     * @param video String
     * @param jogador_id String
     * @param latitude String
     * @param longitude String
     * @param cvideos_id String
     * @return boolean
     */
    public boolean setCVideo(String video, String jogador_id, String latitude, String longitude, String cvideos_id){
		System.out.println("Enviando para o GIT");
                return VideosDAO.getInstance().setCVideo(video, jogador_id, latitude, longitude, cvideos_id);
    }
}
