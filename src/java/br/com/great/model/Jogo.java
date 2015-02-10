/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.model;

/**
 *
 * @author carleandro
 */
public class Jogo {
    private int id;
    private String nome, icone, ordMecanicas, nomeficticio;
    private String latitude,longitude;
    private int user_id, status, jogador_id, jogopai_id;

    public int getJogopai_id() {
        return jogopai_id;
    }

    public void setJogopai_id(int jogopai_id) {
        this.jogopai_id = jogopai_id;
    }

    public String getOrdMecanicas() {
        return ordMecanicas;
    }

    public void setOrdMecanicas(String ordMecanicas) {
        this.ordMecanicas = ordMecanicas;
    }

    public String getNomeficticio() {
        return nomeficticio;
    }

    public void setNomeficticio(String nomeficticio) {
        this.nomeficticio = nomeficticio;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getJogador_id() {
        return jogador_id;
    }

    public void setJogador_id(int jogador_id) {
        this.jogador_id = jogador_id;
    }
    

    /**
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome String
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return String
     */
    public String getIcone() {
        return icone;
    }

    /**
     *
     * @param icone String
     */
    public void setIcone(String icone) {
        this.icone = icone;
    }

    /**
     *
     * @return int
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     *
     * @param user_id int
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
