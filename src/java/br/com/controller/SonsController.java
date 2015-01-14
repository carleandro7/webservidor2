/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.dao.SonsDAO;

/**
 *
 * @author carleandro
 */
public class SonsController {
    public boolean setCSom(String som, String jogador_id, String latitude, String longitude, String csons_id){
		System.out.println("Enviando para o GIT");
                return SonsDAO.getInstance().setCSom(som, jogador_id, latitude, longitude, csons_id);
    }
}
