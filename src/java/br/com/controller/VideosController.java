/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.dao.VideosDAO;

/**
 *
 * @author carleandro
 */
public class VideosController {
    public boolean setCVideo(String video, String jogador_id, String latitude, String longitude, String cvideos_id){
		System.out.println("Enviando para o GIT");
                return VideosDAO.getInstance().setCVideo(video, jogador_id, latitude, longitude, cvideos_id);
    }
}
