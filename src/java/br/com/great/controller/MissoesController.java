/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.controller;

import br.com.great.dao.MissoesDAO;
import br.com.great.model.Missao;
import java.util.ArrayList;

/**
 * Classe responsavel por fazer o controle entre os serviços oferecidos pelo jogos
 * no banco de dados com os eventos relacionados as Missoes
 * @author carleandro
 */
public class MissoesController {
    
    /**
    * Método responsável lista todas as missoes de um grupo
     * @param grupo_id String
     * @return JSONArray
     */
    public ArrayList<Missao> getMissoesGrupo(int grupo_id){
        System.out.println("Enviando para o GIT");
            return MissoesDAO.getInstance().getMissoesGrupo(grupo_id);
    }
    
}
