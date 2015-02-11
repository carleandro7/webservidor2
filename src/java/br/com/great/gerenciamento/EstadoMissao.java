/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import br.com.great.controller.MecanicaController;
import br.com.great.model.Mecanica;
import br.com.great.model.Missao;
import java.util.ArrayList;

/**
 *
 * @author carleandro
 */
public class EstadoMissao {
    private Missao missao;
    private int status =0;

    
    private ArrayList<EstadoMecanica> listMecanicas;
    
    public void inicializaMissoesGrupos() {
        if (this.missao != null) {
            ArrayList<Mecanica> mecanicas = new MecanicaController().getMecaniaMissao(this.missao.getId());
            listMecanicas = new ArrayList<EstadoMecanica>();
            for (Mecanica mecanica : mecanicas) {
                EstadoMecanica estMecanicas = new EstadoMecanica();
                estMecanicas.setMecanica(mecanica);
                listMecanicas.add(estMecanicas);
            }
        }
    }
    
    public Missao getMissao() {
        return missao;
    }

    public void setMissao(Missao missao) {
        this.missao = missao;
    }

    public ArrayList<EstadoMecanica> getListMecanicas() {
        return listMecanicas;
    }

    public void setListMecanicas(ArrayList<EstadoMecanica> listMecanicas) {
        this.listMecanicas = listMecanicas;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
