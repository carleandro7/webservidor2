/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.gerenciamento;

import br.com.great.contexto.Bloqueado;
import br.com.great.contexto.Engajamento;
import br.com.great.contexto.Estado;
import br.com.great.contexto.Executando;
import br.com.great.contexto.Finalizado;
import br.com.great.contexto.Grupo;
import br.com.great.contexto.Jogador;
import br.com.great.contexto.Jogo;
import br.com.great.contexto.Mecanica;
import br.com.great.contexto.MecanicaComposta;
import br.com.great.contexto.Missao;
import br.com.great.contexto.Pronto;
import br.com.great.controller.JogadoresController;
import br.com.great.controller.JogosController;
import br.com.great.dao.EngajamentoDAO;
import br.com.great.dao.MecanicasDAO;
import br.com.great.util.Constants;
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
public class GerenciadorJogos extends Thread {

    private ArrayList<Jogo> listJogos;
    private int codigo_jogo = 0;

    @Override
    public void run() {
        while (!isInterrupted()) {

            try {
                //             enviarLocalizacao();
                System.err.println("servidor executando");
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GerenciadorJogos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void iniciar() {
        this.listJogos = PlayJogo.getJogos();
        carregaJogadores();
    }

    public JSONArray acao(JSONArray json) {
        JSONArray jsonResult = null;
        try {
            JSONObject jobj;
            int executar;
            int acao = json.getJSONObject(0).getInt("acao");
            String[] value, key = {"result"};
            boolean result;
            switch (acao) {
                case Constants.JOGO_NEWJOGO:
                    jobj = json.getJSONObject(1);
                    result = newJogo(jobj.getInt("jogo_id"), jobj.getInt("jogador_id"), jobj.getString("nomeficticio"));
                    value = new String[]{String.valueOf(result)};
                    jsonResult = new JSONArray().put(new OperacoesJSON().toJSONObject(key, value));
                    break;
                case Constants.JOGO_LISTAJOGOS:
                    jobj = json.getJSONObject(1);
                    jsonResult = listarJogos(jobj.getDouble("latitude"), jobj.getDouble("longitude"), jobj.getInt("distancia"));
                    break;
                case Constants.JOGADOR_CADASTRAR_JOGO:
                    jobj = json.getJSONObject(1);
                    result = getJogo(jobj.getInt("jogo_id")).setJogadorGrupo(jobj.getInt("grupo_id"), getJogador(jobj.getInt("jogador_id")));
                    value = new String[]{String.valueOf(result)};
                    jsonResult = new JSONArray().put(new OperacoesJSON().toJSONObject(key, value));
                    break;
                case Constants.JOGADOR_LOGIN:
                    jobj = json.getJSONObject(1);
                    jsonResult = new JSONArray().put(login(jobj.getString("email"), jobj.getString("senha")));
                    break;
                case Constants.JOGADOR_CADASTRAR:
                    jobj = json.getJSONObject(1);
                    jsonResult = cadastrarJogador(jobj.getString("email"), jobj.getString("senha"));
                    break;
                case Constants.JOGO_MECANICAS:
                    jobj = json.getJSONObject(1);
                    jsonResult = new JSONArray().put(getJogo(jobj.getInt("jogo_id")).getMecanicasAll(jobj.getInt("grupo_id"), false));
                    break;
                case Constants.JOGO_GRUPOSJOGADORES:
                    jobj = json.getJSONObject(1);
                    jsonResult = new JSONArray().put(getJogo(jobj.getInt("jogo_id")).getGruposJogadores());
                    break;
                case Constants.JOGO_GRUPOSJOGADOR:
                    jobj = json.getJSONObject(1);
                    jsonResult = new JSONArray().put(getGruposJogador(jobj.getInt("jogo_id"), jobj.getInt("jogador_id")));
                    break;
                case Constants.JOGADOR_SETLOCALIZACAO:
                    jobj = json.getJSONObject(1);
                    getJogador(jobj.getInt("jogador_id")).setLocalizacao(jobj.getDouble("latitude"), jobj.getDouble("longitude"));
                    value = new String[]{String.valueOf(true)};
                    jsonResult = new JSONArray().put(new OperacoesJSON().toJSONObject(key, value));
                    break;
                case Constants.JOGO_LISTJOGOSEXEFILHO:
                    jobj = json.getJSONObject(1);
                    jsonResult = getJogos(jobj.getInt("jogo_id"));
                    break;
                case Constants.JOGADOR_REGDISPOSITIVO:
                    jobj = json.getJSONObject(1);
                    getJogador(jobj.getInt("jogador_id")).setIdDispositivo(jobj.getString("idDispositivo"));
                    value = new String[]{String.valueOf(true)};
                    jsonResult = new JSONArray().put(new OperacoesJSON().toJSONObject(key, value));
                    break;
                case Constants.JOGO_ESTADOMECANICA:
                    jobj = json.getJSONObject(1);
                    Jogo jogo = getJogo(jobj.getInt("jogo_id"));
                    int estado = jogo.getMecanicaMissao(jobj.getInt("mecanica_id"), jogo.getMissaoGrupo(jobj.getInt("grupo_id"))).getEstado().getEstado();
                    value = new String[]{String.valueOf(estado)};
                    jsonResult = new JSONArray().put(new OperacoesJSON().toJSONObject(key, value));
                    break;
                case Constants.JOGO_SET_ESTADOEXE:
                    jobj = json.getJSONObject(1);
                    executar = executarMecanica(jobj.getInt("jogo_id"), jobj.getInt("grupo_id"), jobj.getInt("mecanica_id"));
                    value = new String[]{String.valueOf(executar)};
                    jsonResult = new JSONArray().put(new OperacoesJSON().toJSONObject(key, value));
                    break;
                case Constants.JOGO_SET_ESTADOFINALIZAR:
                    jobj = json.getJSONObject(1);
                    result = finalizarMecanica(jobj.getInt("jogo_id"), jobj.getInt("grupo_id"), jobj.getInt("mecanica_id"));
                    value = new String[]{String.valueOf(result)};
                    jsonResult = new JSONArray().put(new OperacoesJSON().toJSONObject(key, value));
                    break;
                case Constants.JOGO_MECDOWNLOAD:
                    jobj = json.getJSONObject(1);
                    jsonResult = getJogo(jobj.getInt("jogo_id")).getListaMecPrioridade(jobj.getInt("grupo_id"), jobj.getDouble("latitude"), jobj.getDouble("longitude"));
                    break;    
                default:
                //comandos caso nenhuma das opções anteriores tenha sido escolhida
            }
        } catch (JSONException ex) {
            System.err.println("Erro em acao:" + ex.getMessage());
        }
        return jsonResult;
    }

    public void carregaJogadores() {
        PlayJogo.setJogadores(new JogadoresController().listarTodos());
    }

    private JSONArray cadastrarJogador(String email, String senha) {
        String[] value, key = {"result"};
        int result = new JogadoresController().cadastrarJogador(email, senha);
        if (result == 0) {
            value = new String[]{"erro ao salvar"};
        } else if (result == -1) {
            value = new String[]{"Jogador já existe"};
        } else {
            value = new String[]{"Salvo com sucesso!"};
            PlayJogo.getJogadores().add(new JogadoresController().getJogador(result));
        }
        return new JSONArray().put(new OperacoesJSON().toJSONObject(key, value));
    }

    private JSONObject login(String email, String senha) {
        JSONObject obj = new JSONObject();
        try {
            for (int i = 0; i < PlayJogo.getJogadores().size(); i++) {
                if (PlayJogo.getJogadores().get(i).getLogin().equals(email)
                        && PlayJogo.getJogadores().get(i).getSenha().equals(senha)) {
                    obj.put("id", PlayJogo.getJogadores().get(i).getId());
                    obj.put("nome", PlayJogo.getJogadores().get(i).getNome());
                    obj.put("idDispositivo", PlayJogo.getJogadores().get(i).getIdDispositivo());
                    return obj;
                }
            }
        } catch (JSONException ex) {
            System.err.println("Erro login jogador:" + ex.getMessage());
        }
        return obj;
    }

    private boolean newJogo(int jogo_id, int jogador_id, String nome) {
        Jogo jogo = new JogosController().getNewJogo(jogo_id);
        if (jogo != null) {
            jogo.setNomeficticio(nome);
            jogo.setJogador_iniciou(jogador_id);
            jogo.setCodigo(++codigo_jogo);
            listJogos.add(jogo);
            return true;
        } else {
            return false;
        }
    }

    private JSONArray listarJogos(double latitude, double longitude, int distancia) {
        return new JogosController().getJogos(latitude, longitude, distancia);
    }

    private boolean setJogadorJogo(int jogo_id, int grupo, int jogador_id) {
        for (int i = 0; i < listJogos.size(); i++) {
            if (listJogos.get(i).getCodigo() == jogo_id) {
                listJogos.get(i).setJogadorGrupo(grupo, getJogador(jogador_id));
                return true;
            }
        }
        return false;
    }

    private Jogador getJogador(int jogador_id) {
        for (int i = 0; i < PlayJogo.getJogadores().size(); i++) {
            if (PlayJogo.getJogadores().get(i).getId() == jogador_id) {
                return PlayJogo.getJogadores().get(i);
            }
        }
        return null;
    }

    private Jogo getJogo(int jogo_id) {
        for (int i = 0; i < listJogos.size(); i++) {
            if (listJogos.get(i).getCodigo() == jogo_id) {
                return listJogos.get(i);
            }
        }
        return null;
    }

    private ArrayList<Grupo> getGruposJogador(int jogo_id, int jogador_id) {
        ArrayList<Grupo> grupos = new ArrayList<Grupo>();
        grupos = getJogo(jogo_id).getGruposJogadores();
        for (int i = 0; i < grupos.size(); i++) {
            if (!grupos.get(i).getJogador(jogador_id)) {
                grupos.get(i).getJogadores().clear();
            }
        }
        return grupos;
    }

    private JSONArray getJogos(int jogo_id) {
        JSONArray jogos = new JSONArray();
        try {
            for (int i = 0; i < listJogos.size(); i++) {
                if (listJogos.get(i).getId() == jogo_id) {
                    JSONObject obj = new JSONObject();
                    obj.put("id", listJogos.get(i).getId());
                    obj.put("nome", listJogos.get(i).getNome());
                    obj.put("nomeficticio", listJogos.get(i).getNomeficticio());
                    obj.put("codigo", listJogos.get(i).getCodigo());
                    jogos.put(obj);
                }
            }
        } catch (JSONException ex) {
            System.err.println("Erro getJogos:" + ex.getMessage());
        }
        return jogos;
    }

    private boolean finalizarMecanica(int jogo_id, int grupo_id, int mecanica_id) {
        Jogo jogo = getJogo(jogo_id);
        ArrayList<Missao> missoes = jogo.getMissaoGrupo(grupo_id);
        Grupo grupo = jogo.getGrupo(grupo_id);
        Mecanica mecanica = jogo.getMecanicaMissao(mecanica_id, missoes);
        if (mecanica.getEstado().getEstado() == (new Executando()).getEstado()) {
            mecanica.setEstado(new Finalizado());
            reestEstMissoes(missoes, grupo);
            alterarEstadoMissao(missoes, grupo);
            return true;
        }
        return false;
    }

    private int executarMecanica(int jogo_id, int grupo_id, int mecanica_id) {
        Jogo jogo = getJogo(jogo_id);
        ArrayList<Missao> missoes = jogo.getMissaoGrupo(grupo_id);
        Mecanica mecanica = jogo.getMecanicaMissao(mecanica_id, missoes);
        Engajamento engajamento = EngajamentoDAO.getInstance().getEngajamento(jogo.getMissao(mecanica_id, missoes), jogo.getGrupo(grupo_id));
        if (engajamento.getEstado().getEstado() == (new Executando()).getEstado()
                || engajamento.getEstado().getEstado() == (new Pronto()).getEstado()) {
            if (mecanica.getEstado().getEstado() == (new Executando()).getEstado()) {
                return 1;
            } else {
                Estado estado = getEstSubMecanica(jogo.getMissao(mecanica_id, missoes).getMecanicas(), mecanica);
                if (estado.getEstado() == new Pronto().getEstado() || estado.getEstado() == (new Executando()).getEstado()) {
                    mecanica.setEstado(new Executando());
                    ArrayList<Mecanica> mecs = jogo.getMissao(mecanica_id, missoes).getMecanicas();
                    reestEstMecanicas(mecs, null, 0, mecs.size()-1);
                    if (engajamento.getEstado().getEstado() == (new Pronto()).getEstado()) {
                        engajamento.setEstado(new Executando());
                    }
                    return 2;
                }
            }
        }
        return 0;
    }

    private Estado getEstSubMecanica(ArrayList<Mecanica> mecanicas, Mecanica mecanica) {
        Estado estado = null;
        for (Mecanica mecanicaAux : mecanicas) {
            if (mecanicaAux.getTipo() == 1) {
                estado = getEstSubMecanica(((MecanicaComposta) mecanicaAux).getMecanica(), mecanica);
                if (estado.getEstado() == new Pronto().getEstado()) {
                    return mecanicaAux.getEstado();
                }
            } else if (mecanicaAux == mecanica) {
                return mecanica.getEstado();
            }
        }
        return estado;
    }

    private void alterarEstadoMissao(ArrayList<Missao> missoes, Grupo grupo) {
        for (int i = 0; i < missoes.size(); i++) {
            Engajamento engajamento = EngajamentoDAO.getInstance().getEngajamento(missoes.get(i), grupo);
            if (engajamento.getEstado().getEstado() != (new Finalizado()).getEstado()) {
                if (!missoes.get(i).getReqMissao().isEmpty()) {
                    if (engajamento.getEstado().getEstado() == (new Bloqueado()).getEstado()
                            || engajamento.getEstado().getEstado() == (new Executando()).getEstado()) {
                        boolean finalizado = true;
                        for (int j = 0; j < missoes.get(i).getReqMissao().size(); j++) {
                            if (missoes.get(i).getReqMissao().get(j).getFinMecanicas() != 1) {
                                finalizado = false;
                                break;
                            }
                        }
                        if (finalizado) {
                            if (engajamento.getEstado().getEstado() == (new Bloqueado()).getEstado()) {
                                engajamento.setEstado(new Pronto());
                            } else if (missoes.get(i).getFinMecanicas() == 1) {
                                engajamento.setEstado(new Finalizado());
                            }
                        }
                    }
                } else if (missoes.get(i).getFinMecanicas() == 1) {
                    engajamento.setEstado(new Finalizado());
                }
            }
        }
    }

    private void reestEstMissoes(ArrayList<Missao> missoes, Grupo grupo) {
        for (int i = 0; i < missoes.size(); i++) {
            if (missoes.get(i).getFinMecanicas() != 1) {
                Estado estado = reestEstMecanicas(missoes.get(i).getMecanicas(), null, 0, missoes.get(i).getMecanicas().size()-1);
                desbloquearMecanicas(missoes.get(i).getMecanicas());
                if (estado != null && (estado.getEstado() == new Finalizado().getEstado())) {
                    missoes.get(i).setFinMecanicas(1);
                }
            }
        }

    }

    private Estado reestEstMecanicas(ArrayList<Mecanica> mecs, Estado estado, int aux, int fim) {
        if(aux==fim){
            if(mecs.get(aux).getTipo() == 1){
               ArrayList<Mecanica> lista = ((MecanicaComposta) mecs.get(aux)).getMecanica();
               int tam = ((MecanicaComposta) mecs.get(aux)).getMecanica().size()-1;
               Estado estado_aux  = reestEstMecanicas(lista, null, 0, tam);
               mecs.get(aux).setEstado(estado_aux);
               
               return estado_aux;
            }else{
                return mecs.get(aux).getEstado();
            }
        }else{
            if(mecs.get(aux).getTipo() == 1){
               ArrayList<Mecanica> lista = ((MecanicaComposta) mecs.get(aux)).getMecanica();
               int tam = ((MecanicaComposta) mecs.get(aux)).getMecanica().size()-1;
               Estado estado_aux  = reestEstMecanicas(lista, null, 0, tam);
               mecs.get(aux).setEstado(estado);
               estado = reestEstMecanicas(mecs, null, aux+1, fim);
            }else{
                estado = reestEstMecanicas(mecs, null, aux+1, fim);
            }
        }
        if((mecs.get(aux).getEstado().getEstado() == new Pronto().getEstado()) && (estado.getEstado() == new Bloqueado().getEstado())){
           estado  = new Pronto();
        }else if((estado.getEstado() == new Pronto().getEstado()) && (mecs.get(aux).getEstado().getEstado() == new Bloqueado().getEstado())){
           estado  = new Pronto();
        }else if(mecs.get(aux).getEstado().getEstado() != estado.getEstado()){
            estado = new Executando();
        }
        
       return estado;
        
    }
    
    private void desbloquearMecanicas(ArrayList<Mecanica> mecs){
        for (Mecanica mecanicaAux : mecs) {
            if (mecanicaAux.getTipo() == 1) {
                desbloquearMecanicas(((MecanicaComposta) mecanicaAux).getMecanica());
                if(mecanicaAux.getEstado().getEstado() == new Bloqueado().getEstado())
                if (verificar(mecanicaAux.getReqMecanicas()).getEstado() == new Pronto().getEstado()) {
                    mecanicaAux.setEstado(new Pronto());
                }
            } else{
                if(mecanicaAux.getEstado().getEstado() == new Bloqueado().getEstado())
                if (verificar(mecanicaAux.getReqMecanicas()).getEstado() == new Pronto().getEstado()) {
                    mecanicaAux.setEstado(new Pronto());
                }
            }
        }
    }
    private Estado verificar(ArrayList<Mecanica> mecs){
        for(Mecanica mecanica: mecs){
            if(mecanica.getEstado().getEstado() != new Finalizado().getEstado()){
                return new Bloqueado();
            }
        }
        return new Pronto();
    }

}
