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
    private String nome, icone;
    private float latitude,longitude;
    private int user_id;

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
     * @return float
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude float
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return float
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude float
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
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
