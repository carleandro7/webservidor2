package br.com.great.contexto;

import br.com.great.util.FuncoesCalculo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Jogo {

    private int id, jogador_iniciou, status, codigo;
    private String nome, icone, nomeficticio, user;
    private Posicao posicao;

    private ArrayList<DadosPersonagem> dadosPersonagem;

    private ArrayList<ConfiguracaoMissao> configuracaoMissao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJogador_iniciou() {
        return jogador_iniciou;
    }

    public void setJogador_iniciou(int jogador_iniciou) {
        this.jogador_iniciou = jogador_iniciou;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeficticio() {
        return nomeficticio;
    }

    public void setNomeficticio(String nomeficticio) {
        this.nomeficticio = nomeficticio;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public ArrayList<DadosPersonagem> getDadosPersonagem() {
        return dadosPersonagem;
    }

    public void setDadosPersonagem(ArrayList<DadosPersonagem> dadosPersonagem) {
        this.dadosPersonagem = dadosPersonagem;
    }

    public ArrayList<ConfiguracaoMissao> getConfiguracaoMissao() {
        return configuracaoMissao;
    }

    public void setConfiguracaoMissao(ArrayList<ConfiguracaoMissao> configuracaoMissao) {
        this.configuracaoMissao = configuracaoMissao;
    }

    public ArrayList<Mecanica> getMecanicasAll(int grupo_id, boolean comRequisitos) {
        ArrayList<Mecanica> mecanicasAux = new ArrayList<Mecanica>();
        for (int i = 0; i < this.configuracaoMissao.size(); i++) {
            if (configuracaoMissao.get(i).getGrupo(grupo_id)) {
                    mecanicasAux.addAll(configuracaoMissao.get(i).getMecanicasALL(configuracaoMissao.get(i).getGrupoLista(grupo_id), comRequisitos));
            }
        }
        return mecanicasAux;
    }

    public ArrayList<Grupo> getGruposJogadores() {
        ArrayList<Grupo> grupos = new ArrayList<Grupo>();
        for (int i = 0; i < this.configuracaoMissao.size(); i++) {
            grupos.addAll(this.configuracaoMissao.get(i).getGrupo());
        }
        return grupos;
    }

    public boolean setJogadorGrupo(int grupo_id, Jogador jogador) {
        for (int i = 0; i < configuracaoMissao.size(); i++) {
            if (configuracaoMissao.get(i).getGrupo(grupo_id) && !configuracaoMissao.get(i).getGrupoLista(grupo_id).getJogador(jogador.getId())) {
                configuracaoMissao.get(i).getGrupoLista(grupo_id).getJogadores().add(jogador);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Missao> getMissaoGrupo(int grupo_id) {
        for (int i = 0; i < configuracaoMissao.size(); i++) {
            for (int j = 0; j < configuracaoMissao.get(i).getGrupo().size(); j++) {
                if (configuracaoMissao.get(i).getGrupo().get(j).getId() == grupo_id) {
                    return configuracaoMissao.get(i).getMissao();
                }
            }
        }
        return null;
    }

    public Grupo getGrupo(int grupo_id) {
        for (int i = 0; i < configuracaoMissao.size(); i++) {
            for (int j = 0; j < configuracaoMissao.get(i).getGrupo().size(); j++) {
                if (configuracaoMissao.get(i).getGrupo().get(j).getId() == grupo_id) {
                    return configuracaoMissao.get(i).getGrupo().get(j);
                }
            }
        }
        return null;
    }

    public Mecanica getMecanicaMissao(int mecanica_id, ArrayList<Missao> missoes) {
        for (int i = 0; i < missoes.size(); i++) {
            ArrayList<Mecanica> mecanicas = missoes.get(i).getMecanicasReqALL();
            for (int j = 0; j < mecanicas.size(); j++) {
                if (mecanicas.get(j).getId() == mecanica_id) {
                    return mecanicas.get(j);
                }
            }
        }
        return null;
    }

    public Missao getMissao(int mecanica_id, ArrayList<Missao> missoes) {
        for (int i = 0; i < missoes.size(); i++) {
            ArrayList<Mecanica> mecanicas = missoes.get(i).getMecanicasReqALL();
            for (int j = 0; j < mecanicas.size(); j++) {
                if (mecanicas.get(j).getId() == mecanica_id) {
                    return missoes.get(i);
                }
            }
        }
        return null;
    }

    private ArrayList<Mecanica> getMecanicasDistancia(int grupo_id, double latJ, double longJ, ArrayList<Integer> listaP) {
        ArrayList<Mecanica> listMecanicas = getMecanicasAll(grupo_id, true);
        ArrayList<Mecanica> bloqueadas = new ArrayList<Mecanica>();
        ArrayList<Mecanica> lista = new ArrayList<Mecanica>();
        for (int i = 0; i < listMecanicas.size(); i++) {
            String tipo = ((MecanicaSimples) listMecanicas.get(i)).getTiposimples();
            if (tipo.equals("vsons") || tipo.equals("vvideos") || tipo.equals("vfotos")) {
                if (listMecanicas.get(i).getEstado().getEstado() == new Pronto().getEstado()
                        || listMecanicas.get(i).getEstado().getEstado() == new Executando().getEstado()) {
                    lista.add(listMecanicas.get(i));
                    Posicao p1 = new Posicao(latJ, longJ);
                    Posicao p2 = ((MecanicaSimples) listMecanicas.get(i)).getObjeto().getPosicao();
                    int prioridade = new FuncoesCalculo().distanciaMecJogador(p1, p2);
                    listaP.add(prioridade);
                } else if (listMecanicas.get(i).getEstado().getEstado() == new Bloqueado().getEstado()) {
                    bloqueadas.add(listMecanicas.get(i));
                }
            }
        }

        return lista;
    }

    private void getPrioBloqueadas(ArrayList<Mecanica> bloqueadas, ArrayList<Mecanica> lista, ArrayList<Integer> listaP) {
        ArrayList<Mecanica> listaAux = new ArrayList<Mecanica>();
        ArrayList<Integer> listaPB = new ArrayList<Integer>();
        for (int i = 0; i < bloqueadas.size(); i++) {
            int prioMax = 1;
            for (int j = 0; j < bloqueadas.get(i).getReqMecanicas().size(); j++) {
                for (int h = 0; h < lista.size(); h++) {
                    if (bloqueadas.get(i).getReqMecanicas().get(j) == lista.get(h)) {
                        prioMax = listaP.get(h);
                        break;
                    }
                }
            }
            listaAux.add(bloqueadas.get(i));
            listaPB.add(prioMax);
        }
        lista.addAll(bloqueadas);
        listaP.addAll(listaPB);
    }

    public JSONArray getListaMecPrioridade(int grupo_id, double latJ, double longJ) {
        ArrayList<Integer> listaP = new ArrayList<Integer>();
        ArrayList<Mecanica> listMecanicas = getMecanicasDistancia(grupo_id, latJ, longJ, listaP);
        JSONArray json = new JSONArray();
        try {
            for (int i = 0; i < listMecanicas.size(); i++) {
                JSONObject jobj = new JSONObject();
                jobj.put("prioridade", listaP.get(i));
                String tipo = ((MecanicaSimples) listMecanicas.get(i)).getTiposimples();
                String arq = "";
                if (tipo.equals("vsons")) {

                } else if (tipo.equals("vvideos")) {

                } else if (tipo.equals("vfotos")) {
                    Objeto obj = ((MecanicaSimples) listMecanicas.get(i)).getObjeto();
                    arq = ((Foto) obj).getArqimage();
                }
                jobj.put("arquivo", arq);
                json.put(jobj);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;

    }

}
