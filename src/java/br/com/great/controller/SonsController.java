/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.controller;

import br.com.great.dao.SonsDAO;

/**
 *
 * @author carleandro
 */
public class SonsController {

    /**
     * Método responsável por set os dados da CSons no banco de dados
     * @param som String
     * @param jogador_id String
     * @param latitude String
     * @param longitude String
     * @param csons_id String
     * @return boolean
     */
    public boolean setCSom(String som, String jogador_id, String latitude, String longitude, String csons_id){
		System.out.println("Enviando para o GIT");
                return SonsDAO.getInstance().setCSom(som, jogador_id, latitude, longitude, csons_id);
    }
}
