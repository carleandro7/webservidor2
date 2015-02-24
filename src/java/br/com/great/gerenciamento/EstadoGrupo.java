/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import br.com.great.controller.GruposController;
import br.com.great.controller.JogadoresController;
import br.com.great.controller.MissoesController;
import br.com.great.model.Grupo;
import br.com.great.model.Jogador;
import br.com.great.model.Missao;
import br.com.great.util.Constants;
import br.com.great.util.OperacoesJSON;
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
            switch (acao) {
                case Constants.GRUPO_INSERIRPARTICIPANTE:
                    JSONObject jobj = json.getJSONObject(0);
                    jsonResult = setJogadorGrupo(jobj.getInt("jogador_id"));
                    break;
                case Constants.GRUPO_LISTAARQUIVOS:
                       jsonResult = jogoTodosArquivos(json);
                       
                    break;
                case Constants.GRUPO_MECANICAATUAL :
                        jsonResult = mecanicaAtualGrupo(json);
                    break;
                case Constants.GRUPO_SETSTATUSMECANICA :
                        jsonResult = setMecanicaAtualGrupo(json);
                    break;
                default:
                //comandos caso nenhuma das opções anteriores tenha sido escolhida
            }
        } catch (Exception ex) {
            System.err.println("Erro em acao Estado grupo:" + ex.getMessage());
        }
        return jsonResult;
    }
    private JSONArray mecanicaAtualGrupo(JSONArray json) {
        JSONArray jsonResult = new JSONArray();
        if(this.tipoOrdem==1){
                //se acao for um get na missao atual
                int missaoAtual = getMissaoAtual();
                if(missaoAtual != -1){
                    //jsonResult.put(listMissoes.get(missaoAtual).getMissaoJsonArray());
                    jsonResult.put(listMissoes.get(missaoAtual).getMecanicaJsonArray());
                }
                return jsonResult;
        }else if(this.tipoOrdem==0){
            
        }
        return jsonResult;
    }
    
    private JSONArray setMecanicaAtualGrupo(JSONArray json) {
        JSONArray jsonResult = new JSONArray();
        String valueResult="";
        //Se o jogo for do tipo ordenado
        if(this.tipoOrdem==1){
                //se acao for um get na missao atual
                int missaoAtual = getMissaoAtual();
                if(missaoAtual != -1){
                    int result = listMissoes.get(missaoAtual).setMecanicaRealiza(
                          Integer.valueOf(new OperacoesJSON().toJSONObject(json, 0, "mecanica_id")));
                    //ultima mecanica da missao
                    if(result == 2){
                        listMissoes.get(missaoAtual).setStatus(1);
                    }
                    valueResult = String.valueOf(result);
                    //ainda tem mecanica para realizar
                    if(getMissaoAtual() != -1){
                        new GruposController().enviarMensagem(grupo.getId(), "getMecanicaAtual");
                    }else{
                        new GruposController().enviarMensagem(grupo.getId(), "jogoFim");
                    }
                }else{
                    //se nao esxiste mais missoes para realizar
                    valueResult ="3";
                }
                String[][] value = {{valueResult}};
                String[][] key = {{"result"}};
                jsonResult = new OperacoesJSON().toJSONArray(key, value);
                return jsonResult;
        }else{
            
        }
        return jsonResult;
    }
    private JSONArray setJogadorGrupo(int jogador_id) {
        boolean result=false;
        int cadastrado = new GruposController().setGrupoParticipando(grupo.getJogo_id(), grupo.getId(), jogador_id);
        //jogador ainda não cadastrado
        if(cadastrado == 1){    
            EstadoJogador estJogador = new EstadoJogador();
            Jogador jogador = new JogadoresController().getJogador(jogador_id);
            estJogador.setJogador(jogador);
            listJogadores.add(estJogador);
            result = true;
        }else if(cadastrado == 2){
            result = true;
        }
        String[][] key = {{"result"}};
        String[][] valeu = {{String.valueOf(result)}};
        return new OperacoesJSON().toJSONArray(key, valeu);
    }
    
    /*
    Se as as missoes forem ordenadas retorna a que nao foi executada corrente
    */
    public int getMissaoAtual() {
        for(int i=0; i<this.listMissoes.size();i++){
            if(this.listMissoes.get(i).getStatus() == 0){
                return i;
            }
        }
       return -1; 
    }
    
    public int getTipoOrdem() {
        return tipoOrdem;
    }

    public ArrayList<EstadoJogador> getListJogadores() {
        return listJogadores;
    }

    public void setListJogadores(ArrayList<EstadoJogador> listJogadores) {
        this.listJogadores = listJogadores;
    }

    public ArrayList<EstadoMissao> getListMissoes() {
        return listMissoes;
    }

    public void setListMissoes(ArrayList<EstadoMissao> listMissoes) {
        this.listMissoes = listMissoes;
    }

    public void setTipoOrdem(int tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }

    private JSONArray jogoTodosArquivos(JSONArray json) {
      if(tipoOrdem == 0){
        return  new JogadoresController().
                               getTodosArquivos(grupo.getId(), 
                                   new OperacoesJSON().toJSONObject(json, 0, "latitude"),
                                   new OperacoesJSON().toJSONObject(json, 0, "longitude"));
      }else{
          return  new JogadoresController().getTodosArquivos(grupo.getId());
      }
    }

}
