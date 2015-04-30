package br.com.great.contexto;

import br.com.great.gerenciamento.PlayJogo;
import java.util.ArrayList;
import java.util.Collection;

public class Missao {

    private String nome;
    private int id, finMecanicas = 0;
    private Posicao posicaoInicial;

    private ArrayList<Mecanica> mecanicas;

    private ArrayList<Missao> reqMissao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Posicao getPosicaoInicial() {
        return posicaoInicial;
    }

    public void setPosicaoInicial(Posicao posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
    }

    public ArrayList<Mecanica> getMecanicas() {
        return mecanicas;
    }

    public void setMecanicas(ArrayList<Mecanica> mecanicas) {
        this.mecanicas = mecanicas;
    }

    public int getFinMecanicas() {
        return finMecanicas;
    }

    public void setFinMecanicas(int finMecanicas) {
        this.finMecanicas = finMecanicas;
    }

    public ArrayList<Missao> getReqMissao() {
        if (reqMissao == null) {
            reqMissao = new ArrayList<Missao>();
        }
        return reqMissao;
    }

    public void setReqMissao(ArrayList<Missao> reqMissao) {
        this.reqMissao = reqMissao;
    }

    public ArrayList<Mecanica> getMecanicasALL(Estado estado, boolean reqMecanica) {
        ArrayList<Mecanica> mecanicasAux = new ArrayList<Mecanica>();
        for (int j = 0; j < mecanicas.size(); j++) {
            if (mecanicas.get(j).getTipo() == 0) {
                Mecanica mecanica = mecanicas.get(j);
                if (reqMecanica) {
                    mecanica.getReqMecanicas().clear();
                }
                if (estado instanceof Bloqueado) {
                    mecanica.setEstado(new Bloqueado());
                }
                mecanicasAux.add(mecanica);
            } else if (mecanicas.get(j).getTipo() == 1) {
                Mecanica mecanica = mecanicas.get(j);
                if (estado instanceof Bloqueado) {
                    mecanica.setEstado(new Bloqueado());
                }
                mecanicasAux.addAll(getMecanicasComposta(((MecanicaComposta) mecanica), reqMecanica));
            }
        }
        return mecanicasAux;
    }

    public ArrayList<Mecanica> getMecanicasReqALL() {
        ArrayList<Mecanica> mecanicasAux = new ArrayList<Mecanica>();
        for (int j = 0; j < mecanicas.size(); j++) {
            if (mecanicas.get(j).getTipo() == 0) {
                mecanicasAux.add(mecanicas.get(j));
            } else if (mecanicas.get(j).getTipo() == 1) {
                mecanicasAux.addAll(getMecanicasComposta((MecanicaComposta) mecanicas.get(j)));
            }
        }
        return mecanicasAux;
    }

    private ArrayList<Mecanica> getMecanicasComposta(MecanicaComposta mecComposta, boolean reqMecanica) {
        ArrayList<Mecanica> mecanicasAux = new ArrayList<Mecanica>();
        for (int j = 0; j < mecComposta.getMecanica().size(); j++) {
            if (mecComposta.getMecanica().get(j).getTipo() == 0) {
                Mecanica mecanica = mecComposta.getMecanica().get(j);
                if (reqMecanica) {
                    mecanica.getReqMecanicas().clear();
                }
                if (mecComposta.getEstado() instanceof Bloqueado) {
                    mecanica.setEstado(new Bloqueado());
                }
                mecanicasAux.add(mecanica);
            } else if (mecComposta.getMecanica().get(j).getTipo() == 1) {
                Mecanica mecanica = mecComposta.getMecanica().get(j);
                mecanica.getReqMecanicas().clear();
                if (mecComposta.getEstado() instanceof Bloqueado) {
                    mecanica.setEstado(new Bloqueado());
                }
                mecanicasAux.addAll(getMecanicasComposta(((MecanicaComposta) mecanica), reqMecanica));
            }
        }
        return mecanicasAux;
    }

    private ArrayList<Mecanica> getMecanicasComposta(MecanicaComposta mecComposta) {
        ArrayList<Mecanica> mecanicasAux = new ArrayList<Mecanica>();
        for (int j = 0; j < mecComposta.getMecanica().size(); j++) {
            if (mecComposta.getMecanica().get(j).getTipo() == 0) {
                mecanicasAux.add(mecComposta.getMecanica().get(j));
            } else if (mecComposta.getMecanica().get(j).getTipo() == 1) {
                mecanicasAux.addAll(getMecanicasComposta((MecanicaComposta) mecComposta.getMecanica().get(j)));
            }
        }
        return mecanicasAux;
    }
}
