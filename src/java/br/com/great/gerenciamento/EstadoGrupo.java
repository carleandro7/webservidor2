/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import br.com.great.controller.GruposController;
import br.com.great.controller.JogadoresController;
import br.com.great.controller.MissoesController;
import br.com.great.dao.MissoesDAO;
import br.com.great.model.Grupo;
import br.com.great.model.Jogador;
import br.com.great.model.Missao;
import static br.com.great.util.Constants.GRUPO_INSERIRPARTICIPANTE;
import static br.com.great.util.Constants.GRUPO_LISTAARQUIVOS;
import br.com.great.util.Operacoes;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class EstadoGrupo {

    private Grupo grupo;
    private int tipoOrdem;
    private ArrayList<EstadoJogador> listJogadores;
    private ArrayList<EstadoMissao> listMissoes;

    public ArrayList<EstadoJogador> getJogadores() {
        return listJogadores;
    }

    public void setJogadores(ArrayList<EstadoJogador> jogadores) {
        this.listJogadores = jogadores;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void inicializaJogadoresGrupos() {
        if (this.grupo != null) {
            ArrayList<Jogador> jogadores = new JogadoresController().getJogadores(this.grupo.getId());
            listJogadores = new ArrayList<EstadoJogador>();
            for (Jogador jogador : jogadores) {
                EstadoJogador estJogador = new EstadoJogador();
                estJogador.setJogador(jogador);
                listJogadores.add(estJogador);
            }
        }
    }
    public void inicializaMissoesGrupos() {
        if (this.grupo != null) {
            ArrayList<Missao> missoes = new MissoesController().getMissoesGrupo(this.grupo.getId());
            listMissoes = new ArrayList<EstadoMissao>();
            for (Missao missao : missoes) {
                EstadoMissao estMissao = new EstadoMissao();
                estMissao.setMissao(missao);
                estMissao.inicializaMissoesGrupos();
                listMissoes.add(estMissao);
            }
        }
    }

    public int getJogadorParticipando(int jogador_id) {
        if (listJogadores != null) {
            for (EstadoJogador estJogador : listJogadores) {
                if (estJogador.getJogador().getId() == jogador_id) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public JSONArray acao(int acao, JSONArray json) {
        JSONArray jsonResult = null;
        try {
            JSONObject jobj = json.getJSONObject(0);
            switch (acao) {
                case GRUPO_INSERIRPARTICIPANTE:
                    jsonResult = setJogadorGrupo(jobj.getInt("jogador_id"));
                    break;
                case GRUPO_LISTAARQUIVOS:
                       jsonResult = new JogadoresController().
                               getTodosArquivos(grupo.getId(), 
                                   new Operacoes().toJSONObject(json, 0, "latitude"),
                                   new Operacoes().toJSONObject(json, 0, "longitude"));
                    break;
                case 3 :
                    break;
                default:
                //comandos caso nenhuma das opções anteriores tenha sido escolhida
            }
        } catch (Exception ex) {
            System.err.println("Erro em acao Estado grupo:" + ex.getMessage());
        }
        return jsonResult;
    }

    private JSONArray setJogadorGrupo(int jogador_id) {
        String[][] key = {{"result"}};
        String[][] valeu = {{String.valueOf(new GruposController().setGrupoParticipando(grupo.getJogo_id(), grupo.getId(), jogador_id))}};
        return new Operacoes().toJSONArray(key, valeu);
    }
    
    
    public int getTipoOrdem() {
        return tipoOrdem;
    }

    public void setTipoOrdem(int tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }

}
