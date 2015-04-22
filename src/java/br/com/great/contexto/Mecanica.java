package br.com.great.contexto;

import java.util.ArrayList;

public abstract class Mecanica {

    private String nome;
    private int id, tipo;

    private Missao missao;

    private ArrayList<Mecanica> reqMecanicas;

    private Estado estado;

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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Missao getMissao() {
        return missao;
    }

    public void setMissao(Missao missao) {
        this.missao = missao;
    }

    public ArrayList<Mecanica> getReqMecanicas() {
        return reqMecanicas;
    }

    public void setReqMecanicas(ArrayList<Mecanica> reqMecanicas) {
        this.reqMecanicas = reqMecanicas;
    }

    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


}
