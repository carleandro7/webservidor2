/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import br.com.great.dao.GruposDAO;
import br.com.great.model.Grupo;
import br.com.great.model.Jogo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class EstadoJogo extends Thread {

    private Jogo jogo;
    private int versao;
    private int acao = 0;
    private ArrayList<EstadoGrupo> listGrupo = new ArrayList<EstadoGrupo>();

    @Override
    public void run() {
        while (true) {
           if(jogo.getOrdMecanicas().equals("0")){
            switch (acao) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                //comandos caso nenhuma das opções anteriores tenha sido escolhida
                }
           }
        }
    }

    public void iniciaConfiguracoes() {
        ArrayList<Grupo> grupos = new GruposDAO().getTodosGrupos(String.valueOf(jogo.getId()));
        for (Grupo grupo : grupos) {
            EstadoGrupo estGrupo = new EstadoGrupo();
            estGrupo.setGrupo(grupo);
            estGrupo.setTipoOrdem(Integer.valueOf(jogo.getOrdMecanicas()));
            estGrupo.inicializaJogadoresGrupos();
            estGrupo.inicializaMissoesGrupos();
            listGrupo.add(estGrupo);
        }
    }

    public ArrayList<EstadoGrupo> getListGrupo() {
        return listGrupo;
    }

    public void setListGrupo(ArrayList<EstadoGrupo> listGrupo) {
        this.listGrupo = listGrupo;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public JSONArray getGrupos() {
        JSONArray json = new JSONArray();
        try {
            for (EstadoGrupo grupo : listGrupo) {
                JSONObject jobj = new JSONObject();
                jobj.put("id", grupo.getGrupo().getId());
                jobj.put("nome", grupo.getGrupo().getNome());
                json.put(jobj);
            }
        } catch (JSONException ex) {
            System.err.println("Erro getGrupos:" + ex.getMessage());
        }
        return json;
    }

    public int grupoJogadorParticipando(int jogador_id) {
        for (EstadoGrupo grupo : listGrupo) {
            if (grupo.getJogadorParticipando(jogador_id) == 1) {
                return grupo.getGrupo().getId();
            }
        }
        return 0;
    }

}
