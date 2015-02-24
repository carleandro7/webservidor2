/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import br.com.great.controller.JogosController;
import br.com.great.model.Jogo;
import static br.com.great.util.Constants.JOGO_LISTAEXECUTANDO;
import static br.com.great.util.Constants.JOGO_LISTAGRUPOS;
import static br.com.great.util.Constants.JOGO_NEWJOGO;
import br.com.great.util.OperacoesJSON;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class GerenciamentoJogos extends Thread {

    ArrayList<EstadoJogo> lisJogos = new ArrayList<EstadoJogo>();
    private int acao = 0;

    @Override
    public void run() {
        while (!isInterrupted()) {
            switch (acao) {
                case 1:
                            //inicia um novo jogo
                    //newJogo();

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                //comandos caso nenhuma das opções anteriores tenha sido escolhida
                }
            try {
                Thread.sleep(9000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GerenciamentoJogos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public JSONArray acao(int acao, int jogo_id, JSONArray json) {
        JSONArray jsonResult = null;
        JSONObject jobj;
        try {
            switch (acao) {
                case JOGO_NEWJOGO:
                    jobj = json.getJSONObject(0);
                    jsonResult = newJogo(jobj.getInt("jogopai_id"), Integer.valueOf(jobj.getString("jogador_id")), jobj.getString("nomeficticio"));
                    break;
                case JOGO_LISTAGRUPOS:
                    jsonResult = listGrupos(jogo_id);
                    break;
                case JOGO_LISTAEXECUTANDO:
                    jsonResult = listJogosExecutando(jogo_id, Integer.valueOf(new OperacoesJSON().toJSONObject(json, 0, "jogador_id")));
                    break;
                
                default:
                //comandos caso nenhuma das opções anteriores tenha sido escolhida
            }
        } catch (JSONException ex) {
            System.err.println("Erro em acao:" + ex.getMessage());
        }
        return jsonResult;
    }

    public JSONArray acaoGrupo(int acao, int grupo_id, int jogo_id, JSONArray json) {
        JSONArray jsonResult = null;
        for (int i = 0; i < lisJogos.size(); i++) {
            if (lisJogos.get(i).getJogo().getId() == jogo_id) {
                for (int j = 0; j < lisJogos.get(i).getListGrupo().size(); j++) {
                    if (lisJogos.get(i).getListGrupo().get(j).getGrupo().getId() == grupo_id) {
                        /*String[] key={"id", "nome", "nomeficticio", "latitude", "longitude", "status"};
                        String[] value={String.valueOf(lisJogos.get(i).getJogo().getId()), 
                            lisJogos.get(i).getJogo().getNome(), lisJogos.get(i).getJogo().getNomeficticio(),
                            lisJogos.get(i).getJogo().getLatitude(), lisJogos.get(i).getJogo().getLongitude(), 
                            String.valueOf(lisJogos.get(i).getJogo().getStatus())};
                            json.put(new OperacoesJSON().toJSONObject(key, value));*/
                        jsonResult = lisJogos.get(i).getListGrupo().get(j).acao(acao, json);
                    }
                }
            }
        }
        return jsonResult;
    }

    public boolean stopJogos() {
        return true;
    }

    private JSONArray newJogo(int jogo_id, int jogador_id, String nome) throws JSONException {
        EstadoJogo estJogo = new EstadoJogo();
        Jogo jogo = new JogosController().setNewJogo(jogo_id, jogador_id, nome);
        estJogo.setJogo(jogo);
        estJogo.iniciaConfiguracoes();
        lisJogos.add(estJogo);
        return new JSONArray().put(new JSONObject().put("return", "true"));
    }

    private JSONArray listGrupos(int jogo_id) {
        for (EstadoJogo estJogo : lisJogos) {
            if (estJogo.getJogo().getId() == jogo_id) {
                return estJogo.getGrupos();
            }
        }
        return null;
    }

    public JSONArray carregaJogos() {
        try {
            ArrayList<Jogo> lista = new JogosController().getJogosExecutando();
            for (Jogo jogo : lista) {
                EstadoJogo estJogo = new EstadoJogo();
                estJogo.setJogo(jogo);
                estJogo.iniciaConfiguracoes();
                lisJogos.add(estJogo);
            }
            return new JSONArray().put(new JSONObject().put("return", "true"));
        } catch (JSONException ex) {
            System.err.println("Erro carregaJogos:" + ex.getMessage());
        }
        return null;
    }

    private JSONArray listJogosExecutando(int jogopai_id, int jogador_id) {
        JSONArray json = new JSONArray();
        try {
            for (EstadoJogo estJogo : lisJogos) {
                if (estJogo.getJogo().getJogopai_id() == jogopai_id) {
                    JSONObject jobj = new JSONObject();
                    jobj.put("id", estJogo.getJogo().getId());
                    jobj.put("nome", estJogo.getJogo().getNome());
                    jobj.put("icone", estJogo.getJogo().getIcone());
                    jobj.put("nomeficticio", estJogo.getJogo().getNomeficticio());
                    jobj.put("grupo_id", estJogo.grupoJogadorParticipando(jogador_id));
                    json.put(jobj);
                }
            }
        } catch (JSONException ex) {
            System.err.println("Erro listaJogosExecutando:" + ex.getMessage());
        }
        return json;
    }
    
    

}
