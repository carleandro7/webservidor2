/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.management;

import br.com.great.controller.MecanicaController;
import br.com.great.model.Mecanica;
import br.com.great.model.Missao;
import br.com.great.helpful.OperacoesJSON;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

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
    
    public JSONObject getMissaoJsonArray() {
        String[] key={"id", "ordem", "grupo_id", "nome", "latitude", "longitude"};
        String[] value={String.valueOf(missao.getId()), String.valueOf(missao.getOrdem()), 
            String.valueOf(missao.getGrupo_id()), missao.getNome(), missao.getLatitude(), missao.getLongitude()};
        return new OperacoesJSON().toJSONObject(key, value);
    }
   
    public JSONArray getMecanicaJsonArray() {
       for(int i=0; i< listMecanicas.size(); i++){
           if(listMecanicas.get(i).getStatus() == 0){
                return listMecanicas.get(i).getMecanicaJSON();
           }
       }
       return new JSONArray();
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
    
    public int setMecanicaRealiza(int mecanica_id) {
        for(int i=0; i< listMecanicas.size(); i++){
           if(listMecanicas.get(i).getMecanica().getId() == mecanica_id){
              listMecanicas.get(i).setStatus(1);
                //ultima mecanica
              if(listMecanicas.size() == (i+1))
                  return 2;
              return 1;
           }
       }
       return 0;
    }
}
