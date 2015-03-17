/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.management;

import br.com.great.controller.MecanicaController;
import br.com.great.model.Mecanica;
import org.json.JSONArray;

/**
 *
 * @author carleandro
 */
public class EstadoMecanica {
    private Mecanica mecanica;
    private int status = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Mecanica getMecanica() {
        return mecanica;
    }

    public void setMecanica(Mecanica mecanica) {
        this.mecanica = mecanica;
    }
    
    public JSONArray getMecanicaJSON(){
        return new MecanicaController().getMecania(String.valueOf(this.mecanica.getId()));
    }
    
}
